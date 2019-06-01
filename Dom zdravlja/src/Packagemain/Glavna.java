package Packagemain;

import java.time.LocalDateTime;

import Korisnici.Knjizica;
import Korisnici.Korisnik;
import Korisnici.Lekar;
import Korisnici.MedicinskaSestra;
import Korisnici.Pacijent;
import Korisnici.Pregled;
import Korisnici.Enums.Sluzba;
import Korisnici.Enums.Statuspregleda;
import gui.LoginProzor;

public class Glavna {

	private static DbServis dbServis = new DbServis();
	
	public static void main(String[] args) {
		
		dbServis.CitajKorisnike();
		dbServis.UcitajPreglede();
		
		
		
	
		LoginProzor login = new LoginProzor(dbServis);
		login.setVisible(true);
	
		
	}
	
	
	

}
