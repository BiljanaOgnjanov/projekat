package Korisnici;

import java.time.LocalDateTime;

public class Knjizica {

	private String broj;
	private LocalDateTime datumisteka;
	private int kategorijaosiguranja;

	public Knjizica (String broj, LocalDateTime vreme, int kategorijaosiguranja)
	{
		this.broj=broj;
		this.datumisteka = vreme;
		this.kategorijaosiguranja = kategorijaosiguranja;
	}

	public String GetBroj()
	{
	        return this.broj;
	}
	public void SetBroj (String broj)
	{
	        this.broj = broj; 
	}
	public LocalDateTime GetDatumIsteka()
	{
	        return this.datumisteka;
	}
	public void SetDatumIsteka (LocalDateTime datumisteka)
	{
	        this.datumisteka = datumisteka; 
	}
	public int GetKategorijaOsiguranja()
	{
	        return this.kategorijaosiguranja;
	}
	public void SetKategorijaOsiguranja (int kategorijaosiguranja)
	{
	        this.kategorijaosiguranja =kategorijaosiguranja; 
	}
	@Override
	public String toString()
	{
		return this.broj.toString() + "|" + this.datumisteka.toString() + "|" + String.valueOf(this.kategorijaosiguranja);
	}

}
	



