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

@Override
public String toString()
{
	return String.valueOf(this.pacijent) + "|" + String.valueOf(this.lekar) + "|"  +String.valueOf(this.termin) + "|" +String.valueOf(this.soba) + "|" +String.valueOf(this.status);
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