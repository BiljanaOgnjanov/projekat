package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Korisnici.Korisnik;
import Korisnici.Enums.Uloga;
import Packagemain.DbServis;

import gui.formeZaPrikaz.KorisniciProzor;
import gui.formeZaPrikaz.PreglediProzor;




public class GlavniProzor extends JFrame {

	private JMenuBar mainMenu;
	private DbServis dbServis;
	private Korisnik prijavljeniKorisnik;
	private JMenu korisniciMeni;
	private JMenuItem pregledKorisnika;
	private JMenu preglediMeni;
	private JMenuItem pregledPregleda;
	
	
	
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
		this.preglediMeni = new JMenu("Pregledi");
		this.pregledPregleda = new JMenuItem("Pregled pregleda");
		this.korisniciMeni = new JMenu("Korisnici");
		this.pregledKorisnika = new JMenuItem("Pregled korisnika");
		this.korisniciMeni.add(this.pregledKorisnika);
		if(this.prijavljeniKorisnik.GetUloga() == Uloga.medicinskaSestra)
			this.mainMenu.add(this.korisniciMeni);
		this.preglediMeni.add(this.pregledPregleda);
		this.mainMenu.add(this.preglediMeni);

		
		setJMenuBar(this.mainMenu);
	}
	
	private void initActions() {
	
		this.pregledKorisnika.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KorisniciProzor af = new KorisniciProzor(dbServis,prijavljeniKorisnik);
				af.setVisible(true);
			}
		});
		
		this.pregledPregleda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PreglediProzor pp = new PreglediProzor(dbServis,prijavljeniKorisnik);
				pp.setVisible(true);
			}
		});
	}
}
	
	
	



