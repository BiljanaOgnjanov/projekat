package Korisnici;

import Korisnici.Enums.Uloga;

public class Pacijent extends Korisnik {
	
	public Lekar izabranilekar;
	public Knjizica knjizica;
	
	public Pacijent() { 
			
			this.uloga=Uloga.pacijent;
			
		}

	
	
	
}
