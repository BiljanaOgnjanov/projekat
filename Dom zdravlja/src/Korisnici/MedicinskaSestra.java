package Korisnici;

import Korisnici.Enums.Sluzba;
import Korisnici.Enums.Uloga;

public class MedicinskaSestra extends Korisnik {

	public int plata;
	public Sluzba sluzba;
	
public MedicinskaSestra() { 
		
		this.uloga=Uloga.medicinskaSestra;
		
	}
	
	
}
