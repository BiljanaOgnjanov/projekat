package gui.formeZaIzmenuIDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Korisnici.Korisnik;
import Korisnici.Lekar;
import Korisnici.Pacijent;
import Korisnici.Pregled;
import Korisnici.Enums.Statuspregleda;
import Korisnici.Enums.Uloga;
import Packagemain.DbServis;
import gui.formeZaPrikaz.PreglediProzor;
import net.miginfocom.swing.MigLayout;

public class PreglediForma extends JFrame {
	/* GUI */
	private JLabel lblopis = new JLabel("opis");
	private JTextField txtopis = new JTextField(50);
	private JLabel lblsoba = new JLabel("soba");
	private JTextField txtsoba = new JTextField(50);
	private JLabel lbllekar = new JLabel("lekar");
	private JList lstLekar = new JList();
	private JLabel lblpacijent = new JLabel("pacijent");
	private JList lstPacijent = new JList();
	private JLabel lblstatus = new JLabel("status");
	private JTextField txtstatus = new JTextField(50);
	private JLabel lbltermin = new JLabel("termin");
	private JTextField txttermin = new JTextField(50);
	private JButton btnOk = new JButton("Snimi");
	private JButton btnCancel = new JButton("Izlaz");
	/* Polje */
	private DbServis dbServis;
	private Pregled pregled;
	private Korisnik korisnik;
	
	public PreglediForma(DbServis dbServis,Pregled pregled,Korisnik korisnik) {
		this.dbServis = dbServis;
		this.pregled = pregled;
		this.korisnik = korisnik;
		setTitle("Pregled");
		setSize(800, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		if(this.pregled != null)
			popuniPolja();
		else
			txtstatus.setText("zakazan");
	}
	
	private void popuniPolja()
	{
		txtsoba.setText(String.valueOf(pregled.getSoba()));
		txtstatus.setText(String.valueOf(pregled.getStatus()));
		txtopis.setText(pregled.opis);
		txttermin.setText(pregled.getTermin().toString());
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		DefaultListModel listaLekara = new DefaultListModel(),
						 listaPacijenata = new DefaultListModel();
		for(int i = 0; i < dbServis.korisnici.size(); i++)
		{
			Korisnik k = dbServis.korisnici.get(i);
			if(k.GetUloga() == Uloga.lekar)
			{
				Lekar l = (Lekar)k;
				listaLekara.addElement(String.valueOf(l.GetId()) + " - " + l.GetIme() + " " + l.GetPrezime());
			}
			else if(k.GetUloga() == Uloga.pacijent)
			{
				Pacijent p = (Pacijent)k;
				listaPacijenata.addElement(String.valueOf(p.GetId()) + " - " + p.GetIme() + " " + p.GetPrezime());
			}
		}
		if(korisnik.GetUloga() == Uloga.pacijent)
		{
			listaPacijenata = new DefaultListModel();
			Pacijent p = (Pacijent)korisnik;
			listaPacijenata.addElement(String.valueOf(p.GetId()) + " - " + p.GetIme() + " " + p.GetPrezime());
		}
		lstLekar = new JList(listaLekara);
		lstPacijent = new JList(listaPacijenata);
		
		if(korisnik.GetUloga() != Uloga.pacijent) 
		{
			add(lblpacijent);
			add(lstPacijent);
			add(lbllekar);
			add(lstLekar);
			add(lblsoba);
			add(txtsoba);
			add(lbltermin);
			add(txttermin);
		}
		add(lblopis);
		add(txtopis);
		if(this.pregled != null)
		{
			add(lblstatus);
			add(txtstatus);
		}
		add(btnOk);
		add(btnCancel);
	}
	
	private void initActions()
	{
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					
					Lekar l = null;
					Pacijent p = null;
					
					if(korisnik.GetUloga() == Uloga.pacijent)
					{
						txtsoba.setText("0");
						txtstatus.setText(String.valueOf(Statuspregleda.zatrazen));
						txttermin.setText(LocalDateTime.now().toString());
						p = (Pacijent)korisnik;
						l = p.getIzabranilekar();
					}
					else
					{
						String[] d = ((String) lstLekar.getSelectedValue()).split("-");
						String[] d2 = ((String) lstPacijent.getSelectedValue()).split("-");
						l = (Lekar)dbServis.GetKorisnikById(Integer.parseInt(d[0].trim()));
						p = (Pacijent)dbServis.GetKorisnikById(Integer.parseInt(d2[0].trim()));
					}
					
					if(p == null ||
					   l == null ||
					   txtsoba.getText().isEmpty() ||
					   txttermin.getText().isEmpty() ||
					   txtopis.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Unesi sva polja!", "Greska", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
					int soba = Integer.parseInt(txtsoba.getText());
					Statuspregleda status = Statuspregleda.valueOf(txtstatus.getText());
					String opis = txtopis.getText();
					
					LocalDateTime termin = LocalDateTime.parse(txttermin.getText());
					
					if(pregled == null)
					{
						if(korisnik.GetUloga() == Uloga.pacijent)
						{
							status = Statuspregleda.zatrazen;
						}
						pregled = new Pregled(p, l, termin, soba, status , opis);
						dbServis.pregledi.add(pregled);
					}
					else
					{
						if(korisnik.GetUloga() == Uloga.pacijent)
						{
							if(status != Statuspregleda.zatrazen && status != Statuspregleda.otkazan)
							{
								JOptionPane.showMessageDialog(null, "Pacijent moze samo da zatrazi i otkaze pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
								return;
							}
							status = Statuspregleda.zatrazen;
						}
						dbServis.pregledi.remove(pregled);
						pregled = new Pregled(p, l, termin, soba, status , opis);
						dbServis.pregledi.add(pregled);
						//int i = dbServis.pregledi.indexOf(pregled);
						//dbServis.pregledi.set(i, pregled);
					}
					dbServis.UpisiPreglede();
					dbServis.UcitajPreglede();
					
					PreglediProzor pp = new PreglediProzor(dbServis,korisnik);
					pp.setVisible(true);
					
					setVisible(false);
					dispose();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Proverite unos!", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PreglediProzor pp = new PreglediProzor(dbServis,korisnik);
				pp.setVisible(true);
				
				setVisible(false);
				dispose();
			}
		});
	}
}
