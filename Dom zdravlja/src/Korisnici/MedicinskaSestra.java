package Korisnici;

import java.time.LocalDateTime;

import Korisnici.Enums.Sluzba;
import Korisnici.Enums.Statuspregleda;
import Korisnici.Enums.Uloga;

public class MedicinskaSestra extends Korisnik {

	protected int plata;
	protected Sluzba sluzba;
	
	public MedicinskaSestra (int id, String ime, String prezime, boolean pol, String adresa, String JMBG, String brojTelefona,String korisnickoIme,String lozinka,int plata,Sluzba sluzba)
	{
	        super(id,ime,prezime,pol,adresa,JMBG,brojTelefona,korisnickoIme,lozinka,Uloga.medicinskaSestra);
	        this.plata = plata;
	        this.sluzba = sluzba;
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
	
	public Pregled ZakaziPregled(LocalDateTime termin,int soba, Pacijent pacijent, Lekar lekar,String opis)
	{
		Pregled p = this.ZakaziPregled(termin,soba,opis);
		p.pacijent = pacijent;
		p.lekar = lekar;
		return p;
	}
	
	@Override
	public Pregled ZakaziPregled(LocalDateTime termin,int soba,String opis)
	{
		return new Pregled(null,null,termin,soba,Statuspregleda.zakazan,opis);
	}
	
	@Override
	public String toText()
	{
		
		return super.toText() +
			   "Plata: " + String.valueOf(this.plata) + "\n" + 
			   "Sluzba: " + String.valueOf(this.sluzba) + "\n";
	}
	
	@Override
	public String toString()
	{
	        String baza = super.toString();
	
	
	        return baza + "|" + String.valueOf(this.plata) + "|" + String.valueOf(this.sluzba);
	}
}
	
	

