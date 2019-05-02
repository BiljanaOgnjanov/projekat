package Korisnici;

import java.time.LocalDateTime;

import Korisnici.Enums.Statuspregleda;

public class Pregled {
	
	protected Pacijent pacijent;
	protected Lekar lekar;
	protected LocalDateTime termin;
	protected int soba;
	protected Statuspregleda status;


public Pregled (Pacijent pacijent, Lekar lekar, LocalDateTime termin, int soba, Statuspregleda status) {
	this.pacijent=pacijent;
	this.lekar = lekar;
	this.termin = termin;
	this.soba=soba;
	this.status=status;
}

public String toText()
{
	return "Pacijent: " + this.pacijent.ime + " " + this.pacijent.prezime + "\n" +
		   "Lekar: " + this.lekar.ime + " " + this.lekar.prezime + "\n" +
		   "Termin: " + this.termin.toString() + "\n" +
		   "Soba: " + String.valueOf(this.soba) + "\n" + 
		   "Status: " + String.valueOf(status) + "\n";
}

@Override
public String toString()
{
	return String.valueOf(this.pacijent.id) + "|" + String.valueOf(this.lekar.id) + "|"  +String.valueOf(this.termin) + "|" +String.valueOf(this.soba) + "|" +String.valueOf(this.status);
}

public Pacijent getPacijent() {
	return pacijent;
}

public void setPacijent(Pacijent pacijent) {
	this.pacijent = pacijent;
}

public Lekar getLekar() {
	return lekar;
}

public void setLekar(Lekar lekar) {
	this.lekar = lekar;
}

public LocalDateTime getTermin() {
	return termin;
}

public void setTermin(LocalDateTime termin) {
	this.termin = termin;
}

public int getSoba() {
	return soba;
}

public void setSoba(int soba) {
	this.soba = soba;
}

public Statuspregleda getStatus() {
	return status;
}

public void setStatus(Statuspregleda status) {
	this.status = status;
}
}