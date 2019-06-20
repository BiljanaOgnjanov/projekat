package Korisnici.Enums;

public enum Uloga {
	
		pacijent(0),lekar(1),medicinskaSestra(2);
	
		private final int value;
	    private Uloga(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
}
