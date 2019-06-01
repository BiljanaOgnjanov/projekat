package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Korisnici.Enums.Uloga;
import Packagemain.DbServis;
import gui.formeZaIzmenuIDodavanje.KorisniciForma;
import Korisnici.Korisnik;


	
	public class  KorisniciProzor extends JFrame {

		private JToolBar mainToolbar = new JToolBar();
		private JButton btnAdd = new JButton();
		private JButton btnEdit = new JButton();
		private JButton btnDelete = new JButton();
		
		private DefaultTableModel tableModel;
		private JTable korisniciTabela;
		
		private DbServis dbServis;

		public KorisniciProzor( DbServis dbServis) {
			this.dbServis = dbServis;
			setTitle("Korisnici");
			setSize(800, 300);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			initGUI();
			initActions();
		
		
}
		
		private void initGUI() {
			ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
			btnAdd.setIcon(addIcon);
			mainToolbar.add(btnAdd);
			ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
			btnEdit.setIcon(editIcon);
			mainToolbar.add(btnEdit);
			ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
			btnDelete.setIcon(deleteIcon);
			mainToolbar.add(btnDelete);
			add(mainToolbar, BorderLayout.NORTH);
			
			int brojKorisnika = dbServis.korisnici.size(); 
			String[] zaglavlje = new String[] {"id", "ime", " prezime",  "pol", " adresa", " JMBG", " brojTelefona"," korisnickoIme"," lozika"," uloga"};
			
			Object[][] podaci = new Object[brojKorisnika][zaglavlje.length];
			
			for(int i=0; i<dbServis.korisnici.size(); i++) {
				Korisnik korisnik = dbServis.korisnici.get(i);
				podaci[i][0] = korisnik.GetId();
				podaci[i][1] = korisnik.GetIme();
				podaci[i][2] = korisnik.GetPrezime();
				podaci[i][3] = korisnik.GetPol();
				podaci[i][4] = korisnik.GetAdresa();
				podaci[i][5] = korisnik.GetJMBG();
				podaci[i][6] = korisnik.GetBrojTelefona();
				podaci[i][7] = korisnik.GetKorisnickoIme();
				podaci[i][8] = korisnik.GetLozinka();
				podaci[i][9] = korisnik.GetUloga();
			}	
			
			
			tableModel = new DefaultTableModel(podaci, zaglavlje);
			korisniciTabela = new JTable(tableModel);
			korisniciTabela = new JTable(tableModel);
			korisniciTabela.setRowSelectionAllowed(true);
			korisniciTabela.setColumnSelectionAllowed(false);
			korisniciTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			korisniciTabela.setDefaultEditor(Object.class, null);
			
			JScrollPane scrollPane = new JScrollPane(korisniciTabela);
			add(scrollPane, BorderLayout.CENTER);
			
			
			
}
		
		private void initActions() {
			
			btnAdd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int red = korisniciTabela.getSelectedRow();
					if(red == -1) {
						JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						int id = (int) korisniciTabela.getValueAt(red, 0);
						Korisnik korisnik = dbServis.GetKorisnikById(id);
						if(korisnik != null) {
							
							KorisniciForma kf = new KorisniciForma(dbServis,korisnik);
							kf.setVisible(true);
							
								
							}
						else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani artikal!", "Greska", JOptionPane.ERROR_MESSAGE);
						
						}}}
				
			});
	
			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int red = korisniciTabela.getSelectedRow();
					if(red == -1) {
						JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						int id = (int) korisniciTabela.getValueAt(red, 0);
						Korisnik korisnik = dbServis.GetKorisnikById(id);
						if(korisnik != null) {
							int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete artikal?", korisnik.GetIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
							if(izbor == JOptionPane.YES_OPTION) {
								DefaultTableModel model = (DefaultTableModel) korisniciTabela.getModel();
								dbServis.korisnici.remove(korisnik);
								model.removeRow(red);
								dbServis.UpisiKorisnike();
							}
						}else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani artikal!", "Greska", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}
	}