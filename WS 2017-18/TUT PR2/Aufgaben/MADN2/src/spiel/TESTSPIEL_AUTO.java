package spiel;

public class TESTSPIEL_AUTO {
	
	public static void main(String[] args) {

		Spiel spiel=new Spiel(
				"Frank",Farbe.ROT,
				"Hans",Farbe.GELB,
				"Anna",Farbe.BLAU);
		
		// automatische Spielsteuerung:
		// immer den 1. möglichen Zug nehmen
		// aus der Liste der erlaubten Züge
		spiel.startAutomatik(300);
	}
}
