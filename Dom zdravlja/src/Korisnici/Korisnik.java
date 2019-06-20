package Korisnici;

import java.time.LocalDateTime;

import Korisnici.Enums.Uloga;

public class Korisnik {

	protected int id;
	protected String ime;
	protected String prezime;
	protected boolean pol;
	protected String adresa;
	protected String JMBG;
	protected String brojTelefona;
	protected String korisnickoIme;
	protected String lozinka;
	protected Uloga uloga;


	public Korisnik (int id, String ime, String prezime, boolean pol, String adresa, String JMBG, String brojTelefona,String korisnickoIme,String lozika,Uloga uloga)
	{
		this.id=id;
		this.ime = ime;
		this.prezime = prezime;
		this.pol=pol;
		this.adresa=adresa;
		this.JMBG=JMBG;
		this.brojTelefona=brojTelefona;
		this.korisnickoIme=korisnickoIme;
		this.lozinka=lozika;
		this.uloga=uloga;
	}

	public int GetId()
	{ 
        return this.id;
	}
	
	public void SetId (int id)
	{
	        this.id = id;
	}
	
	public String GetIme()
	{ 
        return this.ime;
	}
	
	public void SetIme (String ime)
	{
	        this.ime = ime;
	}
	
	public String GetPrezime()
	{ 
        return this.prezime;
	}
	
	public void SetPrezime (String prezime)
	{
	        this.prezime = prezime;
	}
	public boolean GetPol()
	{ 
        return this.pol;
	}

	public void SetPol (boolean pol)
	{
	        this.pol = pol;
	}
	public String GetAdresa()
	{ 
        return this.adresa;
	}

	public void SetAdresa (String adresa)
	{
	        this.adresa = adresa;
	}
	public String GetJMBG()
	{ 
        return this.JMBG;
	}

	public void SetJMBG (String JMBG)
	{
	        this.JMBG = JMBG;
	}
	public String GetBrojTelefona()
	{ 
        return this.brojTelefona;
	}

	public void SetBrojTelefona (String brojtelefona)
	{
	        this.brojTelefona = brojtelefona;
	}
	public String GetKorisnickoIme()
	{ 
        return this.korisnickoIme;
	}
	public void SetKorisnickoIme (String korisnickoIme)
	{
	        this.korisnickoIme = korisnickoIme;
	}
	public String GetLozinka()
	{ 
        return this.lozinka;
	}
	public void SetLozninka (String lozinka)
	{
	        this.lozinka = lozinka;
	}
	public Uloga GetUloga()
	{ 
        return this.uloga;
	}
	
	public void SetUloga (Uloga uloga)
	{
	        this.uloga = uloga;
	}
	
	public Pregled ZakaziPregled(LocalDateTime termin,int soba, String opis)
	{
		return null;
	}
	
	public String toText()
	{
		return "ID: " + String.valueOf(this.id)+ "\n" +
			   "Ime: " + this.ime + "\n" +
			   "Prezime: " + this.prezime + "\n" +
			   "Pol: " + (this.pol == true ? "Zensko" : "Musko") + "\n" +
			   "Adresa: " + this.adresa + "\n" +
			   "JMBG: " + this.JMBG + "\n" + 
			   "Broj telefona: " + this.brojTelefona + "\n" +
			   "Username: " + this.korisnickoIme + "\n" + 
			   "Password: " + this.lozinka + "\n" +
			   "Uloga: " + String.valueOf(this.uloga) + "\n";
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(this.id) + "|" +
			       this.ime + "|" + this.prezime +
			       "|" + String.valueOf(this.pol) + "|"
			       + this.adresa + "|" + this.JMBG
			       + "|" + this.brojTelefona + "|"
			       + this.korisnickoIme + "|" +
			       this.lozinka + "|" + String.valueOf(this.uloga);
	}
	
	
}
