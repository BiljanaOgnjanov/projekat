package Korisnici;

import Korisnici.Enums.Sluzba;
import Korisnici.Enums.Uloga;

public class Lekar extends Korisnik {
	
	protected int plata;
	protected Sluzba sluzba;
	protected String specijalizacija;
	
	public Lekar (int id, String ime, String prezime, boolean pol, String adresa, String JMBG, String brojTelefona,String korisnickoIme,String lozinka,int plata,Sluzba sluzba)
	{
	        super(id,ime,prezime,pol,adresa,JMBG,brojTelefona,korisnickoIme,lozinka,Uloga.lekar);
	        this.plata = plata;
	        this.sluzba = sluzba;
	}
	
	@Override
	public String toString()
	{
	        String baza = super.toString();
	
	
	        return baza + "|" + String.valueOf(this.plata) + "|" + String.valueOf(this.sluzba) + "|" + String.valueOf(this.specijalizacija);
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public Sluzba getSluzba() {
		return sluzba;
	}

	public void setSluzba(Sluzba sluzba) {
		this.sluzba = sluzba;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
}