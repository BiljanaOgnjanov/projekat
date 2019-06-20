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
import Korisnici.Knjizica;
import Korisnici.Korisnik;
import Korisnici.Lekar;
import Korisnici.MedicinskaSestra;
import Korisnici.Pacijent;


	
	public class  KorisniciProzor extends JFrame {

		private JToolBar mainToolbar = new JToolBar();
		private JButton btnAdd = new JButton();
		private JButton btnEdit = new JButton();
		private JButton btnDelete = new JButton();
		
		private DefaultTableModel tableModel;
		private JTable korisniciTabela;
		
		private DbServis dbServis;
		private Korisnik korisnik;

		public KorisniciProzor( DbServis dbServis, Korisnik korisnik) {
			this.dbServis = dbServis;
			this.korisnik = korisnik;
			setTitle("Korisnici");
			setSize(800, 300);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			initGUI();
			initActions();
		
		
}
		
		private void initGUI() {
			if (this.korisnik.GetUloga() == Uloga.medicinskaSestra) {
				ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
				btnAdd.setIcon(addIcon);
				mainToolbar.add(btnAdd);
			}
			ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
			btnEdit.setIcon(editIcon);
			mainToolbar.add(btnEdit);
			ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
			btnDelete.setIcon(deleteIcon);
			mainToolbar.add(btnDelete);
			add(mainToolbar, BorderLayout.NORTH);
			
			int brojKorisnika = dbServis.korisnici.size(); 
			String[] zaglavlje = new String[] {"id", "ime", " prezime",  "pol", " adresa", " JMBG", " brojTelefona"," korisnickoIme"," lozika"," uloga", "izabrani lekar", "zdravstvena knjizica",
											   "plata", "sluzba", "specijalizacija"};
			
			Object[][] podaci = new Object[brojKorisnika][zaglavlje.length];
			
			for(int i=0; i<dbServis.korisnici.size(); i++) {
				Korisnik korisnik = dbServis.korisnici.get(i);
				podaci[i][0] = korisnik.GetId();
				podaci[i][1] = korisnik.GetIme();
				podaci[i][2] = korisnik.GetPrezime();
				podaci[i][3] = korisnik.GetPol() ? "zensko" : "musko";
				podaci[i][4] = korisnik.GetAdresa();
				podaci[i][5] = korisnik.GetJMBG();
				podaci[i][6] = korisnik.GetBrojTelefona();
				podaci[i][7] = korisnik.GetKorisnickoIme();
				podaci[i][8] = korisnik.GetLozinka();
				podaci[i][9] = korisnik.GetUloga();
				podaci[i][10] = "-";
				podaci[i][11] = "-";
				podaci[i][12] = "-";
				podaci[i][13] = "-";
				podaci[i][14] = "-";
				if(korisnik.GetUloga() == Uloga.pacijent)
				{
					Lekar lek = ((Pacijent)(korisnik)).getIzabranilekar();
					Knjizica knjizica = ((Pacijent)(korisnik)).getKnjizica();
					podaci[i][10] = lek.GetIme() + " " + lek.GetPrezime();
					podaci[i][11] = knjizica.GetBroj() + "/ Kategorija #" + String.valueOf(knjizica.GetKategorijaOsiguranja()) + "/" + knjizica.GetDatumIsteka().toString();
				}
				if(korisnik.GetUloga() != Uloga.pacijent)
				{
					if(korisnik.GetUloga() == Uloga.medicinskaSestra)
					{
						MedicinskaSestra sestra = (MedicinskaSestra)korisnik;
						podaci[i][12] = sestra.getPlata();
						podaci[i][13] = sestra.getSluzba();
					}
					else 
					{
						Lekar lekar = (Lekar)korisnik;
						podaci[i][12] = lekar.getPlata();
						podaci[i][13] = lekar.getSluzba();
						podaci[i][14] = lekar.getSpecijalizacija();
					}
				}
			}	
			
			
			tableModel = new DefaultTableModel(podaci, zaglavlje);
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
					KorisniciForma kf = new KorisniciForma(dbServis,null, korisnik);
					kf.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
			
			btnEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int red = korisniciTabela.getSelectedRow();
					if(red == -1) {
						JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						int id = (int) korisniciTabela.getValueAt(red, 0);
						Korisnik k = dbServis.GetKorisnikById(id);
						if(k != null) {
							
							KorisniciForma kf = new KorisniciForma(dbServis,k, korisnik);
							kf.setVisible(true);
							setVisible(false);
							dispose();
							}
						else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog korisnika!", "Greska", JOptionPane.ERROR_MESSAGE);
						
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
							int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete korisnika?", korisnik.GetIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
							if(izbor == JOptionPane.YES_OPTION) {
								DefaultTableModel model = (DefaultTableModel) korisniciTabela.getModel();
								dbServis.korisnici.remove(korisnik);
								model.removeRow(red);
								dbServis.UpisiKorisnike();
							}
						}else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog korisnika!", "Greska", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}
	}