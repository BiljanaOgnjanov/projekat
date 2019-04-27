package Korisnici;

import Korisnici.Enums.Sluzba;
import Korisnici.Enums.Uloga;

public class Lekar extends Korisnik {
	
	public int plata;
	public Sluzba sluzba;
	public String specijalizacija;
	
public Lekar() { 
		
		this.uloga=Uloga.lekar;
		
	}
	

}
