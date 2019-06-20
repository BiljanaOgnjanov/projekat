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

import Korisnici.Knjizica;
import Korisnici.Korisnik;
import Korisnici.Lekar;
import Korisnici.MedicinskaSestra;
import Korisnici.Pacijent;
import Korisnici.Enums.Sluzba;
import Korisnici.Enums.Uloga;
import Packagemain.DbServis;
import gui.formeZaPrikaz.KorisniciProzor;
import net.miginfocom.swing.MigLayout;

public class KorisniciForma extends JFrame{

	private JLabel lblId = new JLabel("Identifikacioni kod");
	private JTextField txtId = new JTextField(20);
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezimea");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JRadioButton rbtnMuski = new JRadioButton("muski");
	private JRadioButton rbtnZenski = new JRadioButton("zenski");
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblbrojTelefona = new JLabel("broj telefona");
	private JTextField txtbrojTelefona = new JTextField(20);
	private JLabel lblkorisnickoIme = new JLabel("korisnicko ime");
	private JTextField txtkorisnickoIme = new JTextField(20);
	private JLabel lbllozinka = new JLabel("lozinka");
	private JTextField txtlozinka = new JTextField(20);
	private JLabel lbluloga = new JLabel("uloga");
	private JTextField txtuloga = new JTextField(20);
	private JButton btnOk = new JButton("snimi");
	private JButton btnCancel = new JButton("izadji");
	private JLabel lblplata = new JLabel("plata");
	private JTextField txtplata = new JTextField(20);
	private JLabel lblsluzba = new JLabel("sluzba");
	private JTextField txtsluzba = new JTextField(20);
	private JLabel lblspec = new JLabel("specijalizacija");
	private JTextField txtspec = new JTextField(30);
	private JLabel lblbrknj = new JLabel("broj knjizice");
	private JTextField txtbrknj = new JTextField(60);
	private JLabel lbldatumIsteka = new JLabel("datum isteka");
	private JTextField txtdatumIsteka = new JTextField(60);
	private JLabel lblkat = new JLabel("kategorija");
	private JTextField txtkat = new JTextField(60);
	private JLabel lbllekar = new JLabel("izabrani lekar");
	private JList lstLekar = new JList();
	
	private DbServis dbservis;
	private Korisnik korisnik, prijavljeni;

	
	public KorisniciForma(DbServis dbservis, Korisnik korisnik, Korisnik prijavljen) {
		this.dbservis = dbservis;
		this.korisnik = korisnik;
		this.prijavljeni =  prijavljen;
		if(korisnik != null) {
			setTitle(korisnik.GetIme() + " - Izmena podataka");
		}else {
			setTitle("Dodavanje artikala");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}

	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		/*if(korisnik != null) {
			popuniPolja();
		}else {
			rbtnMuski.setSelected(true);
		
		}*/
		DefaultListModel lista = new DefaultListModel();
		for(int i = 0; i < dbservis.korisnici.size(); i++)
		{
			Korisnik k = dbservis.korisnici.get(i);
			if(k.GetUloga() == Uloga.lekar)
			{
				
				Lekar l = (Lekar)k;
				lista.addElement(String.valueOf(l.GetId()) + " - " + l.GetIme() + " " + l.GetPrezime());
			}
		}
		lstLekar = new JList(lista);
		popuniPolja();
		
		add(lblId);
		add(txtId);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblPol);
		
		JPanel jPanel = new JPanel();
		
		ButtonGroup tipGroup = new ButtonGroup();
		
		
		tipGroup.add(rbtnMuski);
		tipGroup.add(rbtnZenski);
		
		jPanel.add(rbtnMuski);
		jPanel.add(rbtnZenski);
		add(jPanel);
		add(lblAdresa);
		add(txtAdresa);
		add(lblJMBG);
		add(txtJMBG);
		add(lblbrojTelefona);
		add(txtbrojTelefona);
		add(lblkorisnickoIme);
		add(txtkorisnickoIme);
		add(lbllozinka);
		add(txtlozinka);
		if(korisnik == null)
		{
			add(lbluloga);
			add(txtuloga);
		}
		if(korisnik != null)
			if(korisnik.GetUloga() != Uloga.pacijent)
			{
				add(lblplata);
				add(txtplata);
				add(lblsluzba);
				add(txtsluzba);
				if(korisnik.GetUloga() == Uloga.lekar)
				{
					add(lblspec);
					add(txtspec);
				}
			}
			else
			{
				add(lblbrknj);
				add(txtbrknj);
				add(lbldatumIsteka);
				add(txtdatumIsteka);
				add(lblkat);
				add(txtkat);
				add(lbllekar);
				add(lstLekar);
			}
		add(btnOk);
		add(btnCancel);
		
	}
	private void popuniPolja() {
		if(korisnik == null)
			txtId.setText(String.valueOf(dbservis.korisnici.size()));
		else
			txtId.setText(String.valueOf(korisnik.GetId()));
		if(korisnik != null)
		{
			txtIme.setText(korisnik.GetIme());
			txtPrezime.setText(korisnik.GetPrezime());
			rbtnMuski.setSelected(!korisnik.GetPol());
			rbtnZenski.setSelected(korisnik.GetPol());
			txtAdresa.setText(korisnik.GetAdresa());	
			txtJMBG.setText(korisnik.GetJMBG());
			txtbrojTelefona.setText(korisnik.GetBrojTelefona());
			txtkorisnickoIme.setText(korisnik.GetKorisnickoIme());
			txtlozinka.setText(korisnik.GetLozinka());
			txtuloga.setText(String.valueOf(korisnik.GetUloga()));
			if(korisnik.GetUloga() != Uloga.pacijent)
			{
				if(korisnik.GetUloga() == Uloga.medicinskaSestra)
				{
					MedicinskaSestra sestra = (MedicinskaSestra)korisnik;
					txtplata.setText(String.valueOf(sestra.getPlata()));
					txtsluzba.setText(sestra.getSluzba().toString());
				}
				else 
				{
					Lekar lekar = (Lekar)korisnik;
					txtplata.setText(String.valueOf(lekar.getPlata()));
					txtsluzba.setText(lekar.getSluzba().toString());
					txtspec.setText(lekar.getSpecijalizacija());
				}
			}
			else
			{
				Knjizica knj = ((Pacijent)(korisnik)).getKnjizica();
				txtbrknj.setText(knj.GetBroj());
				txtdatumIsteka.setText(knj.GetDatumIsteka().toString());
				txtkat.setText(String.valueOf(knj.GetKategorijaOsiguranja()));
			}
		}
		else
		{
			rbtnMuski.setSelected(true);
			rbtnZenski.setSelected(false);
		}
		
	}

	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// proveri polja
				if(txtId.getText().isEmpty() ||
				   txtIme.getText().isEmpty() ||
				   txtPrezime.getText().isEmpty() ||
				   txtAdresa.getText().isEmpty() ||
				   txtJMBG.getText().isEmpty() ||
				   txtbrojTelefona.getText().isEmpty() ||
				   txtkorisnickoIme.getText().isEmpty() ||
				   txtlozinka.getText().isEmpty() ||
				   txtuloga.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Unesi sva polja!", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(korisnik != null) {
					try
					{
						Lekar novilekar = null;
						MedicinskaSestra novasestra = null;
						Pacijent novipacijent = null;
						// proveri sta je korisnik
						switch(korisnik.GetUloga())
						{
							case lekar:
								if(txtplata.getText().isEmpty() ||
								   txtsluzba.getText().isEmpty() ||
								   txtspec.getText().isEmpty())
								{
									JOptionPane.showMessageDialog(null, "Unesi sva polja!", "Greska", JOptionPane.ERROR_MESSAGE);
									return;
								}
								novilekar = new Lekar(Integer.parseInt(txtId.getText()), 
										 txtIme.getText(), txtPrezime.getText(),
										 !rbtnMuski.isSelected(),txtAdresa.getText(),
										 txtJMBG.getText(),txtbrojTelefona.getText(),
										 txtkorisnickoIme.getText(),txtlozinka.getText(),
										 Integer.parseInt(txtplata.getText()),
										 Sluzba.valueOf(txtsluzba.getText()),txtspec.getText());
								try
								{
									dbservis.korisnici.set(dbservis.korisnici.indexOf(korisnik), novilekar);
								}
								catch(IndexOutOfBoundsException x1)
								{
									dbservis.korisnici.add(novilekar);
								}
								dbservis.UpisiKorisnike();
								break;
							case medicinskaSestra:
								if(txtplata.getText().isEmpty() || txtsluzba.getText().isEmpty())
								{
									JOptionPane.showMessageDialog(null, "Unesi sva polja!", "Greska", JOptionPane.ERROR_MESSAGE);
									return;
								}
								novasestra = new MedicinskaSestra(Integer.parseInt(txtId.getText()), 
										 txtIme.getText(), txtPrezime.getText(),
										 !rbtnMuski.isSelected(),txtAdresa.getText(),
										 txtJMBG.getText(),txtbrojTelefona.getText(),
										 txtkorisnickoIme.getText(),txtlozinka.getText(),
										 Integer.parseInt(txtplata.getText()),
										 Sluzba.valueOf(txtsluzba.getText()));
								try
								{
									dbservis.korisnici.set(dbservis.korisnici.indexOf(korisnik), novasestra);
								}
								catch(IndexOutOfBoundsException x2)
								{
									dbservis.korisnici.add(novasestra);
								}
								dbservis.UpisiKorisnike();
								break;
							case pacijent:
								if(txtbrknj.getText().isEmpty() ||
								   txtdatumIsteka.getText().isEmpty() ||
								   txtkat.getText().isEmpty() ||
								   lstLekar.isSelectionEmpty())
								{
									JOptionPane.showMessageDialog(null, "Unesi sva polja!", "Greska", JOptionPane.ERROR_MESSAGE);
									return;
								}
								String[] d = ((String) lstLekar.getSelectedValue()).split("-");
								novipacijent = new Pacijent(Integer.parseInt(txtId.getText()), 
									 txtIme.getText(), txtPrezime.getText(),
									 !rbtnMuski.isSelected(),txtAdresa.getText(),
									 txtJMBG.getText(),txtbrojTelefona.getText(),
									 txtkorisnickoIme.getText(),txtlozinka.getText(),
									 (Lekar)dbservis.GetKorisnikById(Integer.parseInt(d[0].trim())),
									 new Knjizica(txtbrknj.getText(),LocalDateTime.parse(txtdatumIsteka.getText()),Integer.parseInt(txtkat.getText())));
								
								try
								{
									dbservis.korisnici.set(dbservis.korisnici.indexOf(korisnik), novipacijent);
								}
								catch(IndexOutOfBoundsException x3)
								{
									dbservis.korisnici.add(novipacijent);
								}
								dbservis.UpisiKorisnike();
								break;
							default:
								break;
							
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Unesi sva polja!", "Greska", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else
				{
					Uloga u = Uloga.valueOf(txtuloga.getText());
					switch(u)
					{
						case lekar:
						{
							Lekar novilekar = new Lekar(Integer.parseInt(txtId.getText()), 
									 txtIme.getText(), txtPrezime.getText(),
									 !rbtnMuski.isSelected(),txtAdresa.getText(),
									 txtJMBG.getText(),txtbrojTelefona.getText(),
									 txtkorisnickoIme.getText(),txtlozinka.getText(),
									 0,Sluzba.sluzbaopstemedicine,"nema");
							KorisniciForma kf = new KorisniciForma(dbservis,novilekar,prijavljeni);
							kf.setVisible(true);
							break;
						}
						case medicinskaSestra:
						{
							MedicinskaSestra novasestra = new MedicinskaSestra(Integer.parseInt(txtId.getText()), 
									 txtIme.getText(), txtPrezime.getText(),
									 !rbtnMuski.isSelected(),txtAdresa.getText(),
									 txtJMBG.getText(),txtbrojTelefona.getText(),
									 txtkorisnickoIme.getText(),txtlozinka.getText(),
									 0, Sluzba.sluzbaopstemedicine);
							KorisniciForma kf = new KorisniciForma(dbservis,novasestra,prijavljeni);
							kf.setVisible(true);
							break;
						}
						case pacijent:
						{
							Pacijent novipacijent = new Pacijent(Integer.parseInt(txtId.getText()), 
									 txtIme.getText(), txtPrezime.getText(),
									 !rbtnMuski.isSelected(),txtAdresa.getText(),
									 txtJMBG.getText(),txtbrojTelefona.getText(),
									 txtkorisnickoIme.getText(),txtlozinka.getText(),
									 null,
									 new Knjizica("",LocalDateTime.now(),1));
							
							KorisniciForma kf = new KorisniciForma(dbservis,novipacijent,prijavljeni);
							kf.setVisible(true);
							break;
						}
					}
					
					
				}
				setVisible(false);
				if(korisnik != null)
				{
					dbservis.CitajKorisnike();
				}
				KorisniciProzor kp = new KorisniciProzor(dbservis, prijavljeni);
				kp.setVisible(true);
				dispose();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});		
	}
}