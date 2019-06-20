package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Korisnici.Korisnik;
import Korisnici.Lekar;
import Korisnici.Pregled;
import Korisnici.Enums.Statuspregleda;
import Korisnici.Enums.Uloga;
import Packagemain.DbServis;
import gui.formeZaIzmenuIDodavanje.PreglediForma;

public class PreglediProzor extends JFrame {
	/* GUI */
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable preglediTabela;
	/* Polja */
	private DbServis dbServis;
	private Korisnik korisnik;
	private ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
	
	public PreglediProzor(DbServis servis,Korisnik k) {
		this.dbServis = servis;
		this.korisnik = k;
		setTitle("Pregledi - " + this.korisnik.GetKorisnickoIme());
		setSize(800, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.dbServis.UcitajPreglede();
		ucitajPreglede();
		initGUI();
		initActions();
	}
	
	private void ucitajPreglede()
	{
		pregledi.clear();
		if(this.korisnik.GetUloga() == Uloga.lekar)
		{
			for(int i = 0; i < this.dbServis.pregledi.size(); i++)
			{
				Pregled p = this.dbServis.pregledi.get(i);
				if(p.getLekar().GetId() == this.korisnik.GetId())
					pregledi.add(p);
			}
		}
		else if (this.korisnik.GetUloga() == Uloga.pacijent)
		{
			for(int i = 0; i < this.dbServis.pregledi.size(); i++)
			{
				Pregled p = this.dbServis.pregledi.get(i);
				if(p.getPacijent().GetId() == this.korisnik.GetId())
					pregledi.add(p);
			}
		}
		else
		{
			this.pregledi = this.dbServis.pregledi;
		}
	}
	
	public void initGUI()
	{
		if(this.korisnik.GetUloga() != Uloga.lekar)
		{
			ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
			btnAdd.setIcon(addIcon);
			mainToolbar.add(btnAdd);
			
			ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
			btnEdit.setIcon(editIcon);
			mainToolbar.add(btnEdit);
		}
			
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {"ID","Soba","Termin","Pacijent","Status","Lekar","Opis"};
		Object[][] podaci = new Object[pregledi.size()][zaglavlje.length];
		
		for(int i = 0; i < this.dbServis.pregledi.size(); i++)
		{
			Pregled p = this.dbServis.pregledi.get(i);
			podaci[i][0] = i;
			podaci[i][1] = p.getSoba();
			podaci[i][2] = p.getTermin().toString();
			podaci[i][3] = p.getPacijent().GetIme() + " " + p.getPacijent().GetPrezime();
			podaci[i][4] = String.valueOf(p.getStatus());
			podaci[i][5] = p.getLekar().GetIme() + " " + p.getLekar().GetPrezime();
			podaci[i][6] = p.opis;
		}
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		preglediTabela = new JTable(tableModel);
		preglediTabela.setRowSelectionAllowed(true);
		preglediTabela.setColumnSelectionAllowed(false);
		preglediTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		preglediTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(preglediTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions()
	{
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PreglediForma pf = new PreglediForma(dbServis,null,korisnik);
				pf.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = (int) preglediTabela.getValueAt(red, 0);
					Pregled p = pregledi.get(id);
					if(korisnik.GetUloga() == Uloga.pacijent && p.getStatus() != Statuspregleda.zatrazen)
					{
						JOptionPane.showMessageDialog(null, "Ne mozete menjati ovaj pregled.", "Greska", JOptionPane.WARNING_MESSAGE);
						return;
					}
					PreglediForma pf = new PreglediForma(dbServis,p,korisnik);
					pf.setVisible(true);
					setVisible(false);
					dispose();
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = (int) preglediTabela.getValueAt(red, 0);
					Pregled p = pregledi.get(id);
					if(p != null) {
						String tekst = korisnik.GetUloga() != Uloga.medicinskaSestra ? "otkazete" : "obrisete";
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da "+tekst+" pregled?", korisnik.GetIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							if(korisnik.GetUloga() != Uloga.medicinskaSestra) 
							{
								dbServis.pregledi.remove(p);
								p.setStatus(Statuspregleda.otkazan);
								dbServis.pregledi.add(p);
								dbServis.UpisiPreglede();
								
								PreglediProzor pp = new PreglediProzor(dbServis,korisnik);
								pp.setVisible(true);
								
								setVisible(false);
								dispose();
							}
							else
							{
								DefaultTableModel model = (DefaultTableModel) preglediTabela.getModel();
								dbServis.pregledi.remove(p);
								pregledi.remove(p);
								model.removeRow(red);
								dbServis.UpisiPreglede();
								
								PreglediProzor pp = new PreglediProzor(dbServis,korisnik);
								pp.setVisible(true);
								
								setVisible(false);
								dispose();
							}
						}
						else 
						{
							izbor = JOptionPane.showConfirmDialog(null,"Da li zelite da zavrsite pregled?", korisnik.GetIme() + " - Potvrda zavrsetka", JOptionPane.YES_NO_OPTION);
							if(izbor == JOptionPane.YES_OPTION) {
								dbServis.pregledi.remove(p);
								p.setStatus(Statuspregleda.zavrsen);
								dbServis.pregledi.add(p);
								dbServis.UpisiPreglede();
								
								PreglediProzor pp = new PreglediProzor(dbServis,korisnik);
								pp.setVisible(true);
								
								setVisible(false);
								dispose();
							}
						}
					}
				}
			}
		});
	}
}
