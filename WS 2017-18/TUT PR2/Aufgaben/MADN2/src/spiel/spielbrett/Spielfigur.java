package spiel.spielbrett;

import spiel.Farbe;

public class Spielfigur {
	private Farbe farbe;
	private Spielfeld feld;
	
	public Spielfigur(Farbe farbe){
		setFarbe(farbe);
	}

	public void setFeld(Spielfeld feld) {
		if (feld==null)
			throw new RuntimeException("Das Feld darf nicht NULL sein!");
		this.feld=feld;
	}
	public Spielfeld getFeld() {
		return feld;
	}

	public Farbe getFarbe() {
		return farbe;
	}
	private void setFarbe(Farbe farbe) {
		if (farbe==null)
			throw new RuntimeException("Diese Farbe ist nicht erlaubt!");
		this.farbe = farbe;
	}

	@Override
	public String toString(){
		return "Spielfigur "+getFarbe();
	}
}