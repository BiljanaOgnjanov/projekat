package Packagemain;

import Korisnici.Korisnik;
import Korisnici.Lekar;
import Korisnici.Enums.Sluzba;
import Korisnici.Enums.Uloga;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;


public class DbServis {
	
	public ArrayList<Korisnik> korisnici;
	
	public DbServis()
	{
		this.korisnici = new ArrayList<Korisnik>();
	}
	
	public void UpisiKorisnike() 
	{
		File kkf = new File("src/Fajlovi/korisnici.txt");
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(kkf));
			for (Korisnik korisnik : korisnici) {
				wr.write(korisnik.toString() + "\n");
			}
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void CitajKorisnike()
	{
		this.korisnici.clear();
		File kkf = new File("src/Fajlovi/korisnici.txt");
		try {
			BufferedReader rr = new BufferedReader(new FileReader(kkf));
			String line;
			while ((line = rr.readLine()) != null) {
				String[] split = line.split("\\|");
				int id = Integer.parseInt(split[0]);
				String ime = split[1];
				String prezime = split[2];
				boolean pol = Boolean.parseBoolean(split[3]);
				String adresa = split[4];
				String jmbg = split[5];
				String brTel = split[6];
				String user = split[7];
				String password = split[8];
				Uloga uloga = Uloga.valueOf(split[9]);
				
				switch(uloga) {
					case lekar:
						int plata = Integer.parseInt(split[10]);
						Sluzba sluzba = Sluzba.valueOf(split[11]);
						this.korisnici.add(new Lekar(id,ime,prezime,pol,adresa,jmbg,
													 brTel,user,password,plata,sluzba));
						break;
					default:
						break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
