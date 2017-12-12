package spiel;

import spiel.spielbrett.Spielfeld;
import spiel.spielbrett.Spielfigur;

public class Spielregeln {

	public static boolean istSpielende(Spiel spiel){
		for(int i=0;i<Farbe.values().length;i++){
			Farbe farbe=Farbe.values()[i];
			if (spiel.getSpielbrett().volleEndfelder(farbe)){
				System.out.println(farbe+" HAT GEWONNEN");
				return true;
			}			
		}
		return false;
	}
	
	public static void ziehe(Spiel spiel,Spielzug spielzug) {
		if (spielzug==null)
			throw new RuntimeException("Der Zug ist nicht definiert!");
		Spielfeld von = spielzug.getVon();
		Spielfigur figur_von=von.getFigur();
		if (figur_von==null)
			throw new RuntimeException("Auf dem Startfeld ist keine Figur!");
		Spielfeld nach=spielzug.getNach();
		Spielfigur figur_nach=nach.getFigur();
		if (figur_nach==null){
			// Zielfeld ist leer
			// -> Figur einfach dahin bewegen
			System.out.println(figur_von+" zieht von "+von.getId()+" nach "+nach.getId());
		}
		else{
			// Zielfeld hat eine Figur des Gegners,
			// weil sonst dieser Zug nicht erlaubt wäre
			// -> diese Figur schlagen!			
			System.out.println(figur_von+" zieht von "+von.getId()+" nach "+nach.getId()+" und schlägt "+figur_nach);
			spiel.getSpielbrett().schlageFigur(figur_nach);
		}
		von.setFigur(null);
		figur_von.setFeld(nach);
		nach.setFigur(figur_von);
	}
	
	public static Spielzugliste getErlaubteZüge(Spiel spiel,int zahl){
		Spielzugliste züge=new Spielzugliste();
		Spieler spielerAmZug=spiel.getSpielerAmZug();
		Spielfigur[] figuren=spielerAmZug.getFiguren();
		for(Spielfigur figur:figuren){
			Spielzugliste erlaubt=getErlaubteZüge(figur,zahl);
			züge.addZugliste(erlaubt);
		}
		return züge;
	}
	
	public static Spielzugliste getErlaubteZüge(Spielfigur figur,int zahl) {
		Spielzugliste züge=new Spielzugliste();
		Spielfeld startfeld=figur.getFeld();
		Farbe farbe=figur.getFarbe();
		if (startfeld.istStartfeld()){
			// vom Startfeld gehts nur mit einer 6 weg
			if (zahl!=6) return züge;
			Spielfeld zielfeld=startfeld.getNachfolgerLauffeld();
			if (!Spielregeln.istMitEigenerFigurBelegt(farbe,zielfeld)){
				züge.addZug(new Spielzug(startfeld,zielfeld));
			}
		}
		else{
			try{
				// versuche, diese Anzahl an Feldern nach vorne zu kommen...
				Spielfeld zielfeld=getNächstesFeld(farbe,startfeld);
				for(int i=2;i<=zahl;i++){
					zielfeld=getNächstesFeld(farbe,zielfeld);
				}
				if (!Spielregeln.istMitEigenerFigurBelegt(farbe,zielfeld)){
					// wenn das klappt und das Zielfeld nicht mit einer eigenen Figur belegt ist,
					// dann ist das ein erlaubter Zug
					züge.addZug(new Spielzug(startfeld,zielfeld));
				}				
			}
			catch (Exception e){}
		}
		return züge;
	}
	
	// PRIVATE HILFSMETHODEN
	
	private static boolean istMitEigenerFigurBelegt(Farbe farbe,Spielfeld feld){
		Spielfigur figur_feld=feld.getFigur();
		if (figur_feld==null) return false;
		return (""+figur_feld.getFarbe()).equals(""+farbe);
	}

	private static Spielfeld getNächstesFeld(Farbe farbe,Spielfeld von){
		Spielfeld endfeld=von.getNachfolgerEndfeld();
		if (endfeld==null)
			return von.getNachfolgerLauffeld();
		Farbe farbe_endfeld=endfeld.getFarbe();
		if (!(""+farbe_endfeld).equals(""+farbe))
			return von.getNachfolgerLauffeld();
		else{
			// ich bin auf einem meiner Endfelder
			// Wenn dort bereits eine Figur von mir steht, 
			// dann darf ich die nicht überspringen
			Spielfigur figur_endfeld = endfeld.getFigur();
			if (figur_endfeld==null) return endfeld;
			throw new RuntimeException("Ich darf auf den Endfeldern meine eigenen Figuren nicht überspringen.");
		}

	}
}
