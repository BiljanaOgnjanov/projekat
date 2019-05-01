package Korisnici;

import Korisnici.Enums.Sluzba;
import Korisnici.Enums.Uloga;

public class Pacijent extends Korisnik {
	
	protected Lekar izabranilekar;
	protected Knjizica knjizica;
	
	public Pacijent (int id, String ime, String prezime, boolean pol, String adresa, String JMBG, String brojTelefona,String korisnickoIme,String lozinka,int plata,Sluzba sluzba)
	{
	        super(id,ime,prezime,pol,adresa,JMBG,brojTelefona,korisnickoIme,lozinka,Uloga.pacijent);
	  
	}   

	 public Lekar getIzabranilekar() {
		return izabranilekar;
	}

	public void setIzabranilekar(Lekar izabranilekar) {
		this.izabranilekar = izabranilekar;
	}

	public Knjizica getKnjizica() {
		return knjizica;
	}

	public void setKnjizica(Knjizica knjizica) {
		this.knjizica = knjizica;
	}

	@Override
 	public String toString()
 	{
 	        String baza = super.toString();
 	
 	
 	        return baza + "|" + String.valueOf(this.izabranilekar) + "|" + String.valueOf(this.knjizica);
 	}
	
	
}
