package Packagemain;

import Korisnici.Lekar;
import Korisnici.Enums.Sluzba;

public class Glavna {

	private static DbServis dbServis = new DbServis();
	
	public static void main(String[] args) {

		//dbServis.korisnici.add(new Lekar(0, "Janko", "Patuljak", false, "Mirka Cokotica 12", "2206000820002", "0652145715", "janko", "janko123", 5000, Sluzba.sluzbaopstemedicine));
		
		System.out.println("dom zdravlja");
		dbServis.CitajKorisnike();
		//dbServis.UpisiKorisnike();
		System.out.println("korisnici iscitani!");
		System.out.println(dbServis.korisnici.get(0).toString());
	}

}
