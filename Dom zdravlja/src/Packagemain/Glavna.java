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

public class Glavna {

	private static DbServis dbServis = new DbServis();
	
	public static void main(String[] args) {
		
		dbServis.CitajKorisnike();
		dbServis.UcitajPreglede();
		
		System.out.println("KORISNICI UCITANI IZ DATOTEKA:");
		System.out.println("----------------------------------------------");
		for(Korisnik k: dbServis.korisnici)
			System.out.println(k.toText());
		System.out.println("----------------------------------------------\n");
		
		System.out.println("PREGLEDI UCITANI IZ DATOTEKA:");
		System.out.println("----------------------------------------------");
		for(Pregled p: dbServis.pregledi)
			System.out.println(p.toText());
		System.out.println("----------------------------------------------\n");
		
		System.out.println("Dodavanje test podataka...");
		// TODO: Dodati neke test podatke
		
		System.out.println("Snimanje dodanih podataka...");
		dbServis.UpisiKorisnike();
		dbServis.UpisiPreglede();
	}

}
