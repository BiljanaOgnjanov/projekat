package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Korisnici.Korisnik;
import Packagemain.DbServis;

import gui.formeZaPrikaz.KorisniciProzor;




public class GlavniProzor extends JFrame {

	private JMenuBar mainMenu;
	private DbServis dbServis;
	private Korisnik prijavljeniKorisnik;
	private JMenu korisniciMeni;
	private JMenuItem pregledKorisnika;
	
	
	
	
	public GlavniProzor(DbServis dbServis, Korisnik prijavljeniKorisnik) {
		this.dbServis = dbServis;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		setTitle("Dom zdravlja - " + prijavljeniKorisnik.GetKorisnickoIme());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		
		this.mainMenu = new JMenuBar();
		this.korisniciMeni = new JMenu("Korisnici");
		this.pregledKorisnika = new JMenuItem("Pregled korisnika");
		this.korisniciMeni.add(this.pregledKorisnika);
		this.mainMenu.add(this.korisniciMeni);
		

		
		setJMenuBar(this.mainMenu);
	}
	
	private void initActions() {
	
		this.pregledKorisnika.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KorisniciProzor af = new KorisniciProzor(dbServis);
				af.setVisible(true);
			}
		});
		
	}
}
	
	
	



