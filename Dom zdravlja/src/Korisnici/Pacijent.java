package Korisnici;

import java.time.LocalDateTime;

import Korisnici.Enums.Sluzba;
import Korisnici.Enums.Statuspregleda;
import Korisnici.Enums.Uloga;

public class Pacijent extends Korisnik {
	
	protected Lekar izabranilekar;
	protected Knjizica knjizica;
	
	public Pacijent (int id, String ime, String prezime, boolean pol, String adresa, String JMBG, String brojTelefona,String korisnickoIme,String lozinka,Lekar izabraniLekar,Knjizica knjizica)
	{
	        super(id,ime,prezime,pol,adresa,JMBG,brojTelefona,korisnickoIme,lozinka,Uloga.pacijent);
	        this.izabranilekar = izabraniLekar;
	        this.knjizica = knjizica;
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
	public String toText()
	{
		return super.toText() +
			   "Knjizica: " + this.knjizica.GetBroj() + "\n" +
			   "Izabrani lekar: " + this.izabranilekar.ime + " " + this.izabranilekar.prezime + "\n";
	}
	
	@Override
	public Pregled ZakaziPregled(LocalDateTime termin,int soba)
	{
		return new Pregled(this,this.izabranilekar,termin,soba,Statuspregleda.zatrazen);
	}

	@Override
 	public String toString()
 	{
 	        String baza = super.toString();
 	
 	
 	        return baza + "|" + String.valueOf(this.izabranilekar.id) + "|" + String.valueOf(this.knjizica);
 	}
	
	
}
