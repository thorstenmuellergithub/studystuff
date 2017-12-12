package spiel.spielbrett;

import spiel.Farbe;
import spiel.Spieler;

public class Spielbrett {
	private Spielfeld[][] startfeld; 
	private Spielfeld[][] endfeld; 
	private Spielfeld[] lauffeld; 
	
	public Spielbrett(){
		// Standard-Lauffelder erzeugen
		lauffeld=new Spielfeld[41];
		for(int i=1;i<=40;i++){
			lauffeld[i]=new Spielfeld(""+i);
			if ((i>1)&&(i<=40))
				lauffeld[i-1].setNachfolgerLauffeld(lauffeld[i]);
			if (i==40)
				lauffeld[i].setNachfolgerLauffeld(lauffeld[1]);				
		}
		// Startfelder erzeugen und mit den Lauffeldern verdrahten
		startfeld=new Spielfeld[5][5];
		for(int i=1;i<=4;i++){
			Farbe farbe=Farbe.values()[i-1]; // 0:ROT,1:BLAU,2:GRÜN,3:GELB
			for(int j=1;j<=4;j++){
				startfeld[i][j]=new Spielfeld("S"+j,farbe,true,false);
				startfeld[i][j].setNachfolgerLauffeld(lauffeld[1+10*(i-1)]); // 1,11,21,31
			}
		}
		// Endfelder erzeugen und untereinander verdrahten
		endfeld=new Spielfeld[5][5];
		for(int i=1;i<=4;i++){
			Farbe farbe=Farbe.values()[i-1]; // 0:ROT,1:BLAU,2:GRÜN,3:GELB
			for(int j=1;j<=4;j++){
				endfeld[i][j]=new Spielfeld("E"+j,farbe,false,true);
			}
			endfeld[i][1].setNachfolgerEndfeld(endfeld[i][2]);
			endfeld[i][2].setNachfolgerEndfeld(endfeld[i][3]);
			endfeld[i][3].setNachfolgerEndfeld(endfeld[i][4]);
		}
		// Standard-Lauffelder und Endfelder verbinden
		lauffeld[40].setNachfolgerEndfeld(endfeld[1][1]); // rot
		lauffeld[10].setNachfolgerEndfeld(endfeld[2][1]); // blau
		lauffeld[20].setNachfolgerEndfeld(endfeld[3][1]); // grün
		lauffeld[30].setNachfolgerEndfeld(endfeld[4][1]); // gelb
	}

	public void initFiguren(Spieler spieler) {
		if (spieler==null) 
			throw new RuntimeException("Spieler ist ungültig!");
		Farbe farbe=spieler.getFarbe();
		for(int i=1;i<=4;i++){
			Spielfigur figur=new Spielfigur(farbe);
			spieler.addFigur(figur);
			startfeld[farbe.ordinal()+1][i].setFigur(figur);
			figur.setFeld(startfeld[farbe.ordinal()+1][i]);
		}
	}

	public void schlageFigur(Spielfigur zuSchlagendeFigur) {
		Spielfeld feld=zuSchlagendeFigur.getFeld();
		Farbe farbe=zuSchlagendeFigur.getFarbe();
		for(int i=1;i<=4;i++){
			// Figur wieder auf 1 freies Startfeld setzen
			Spielfeld feld_ablage=startfeld[farbe.ordinal()+1][i];
			if (feld_ablage.getFigur()==null){
				feld_ablage.setFigur(zuSchlagendeFigur);
				zuSchlagendeFigur.setFeld(feld_ablage);
			}
		}
		feld.setFigur(null);
	}

	public boolean volleEndfelder(Farbe farbe) {
		for(int i=1;i<=4;i++){
			if (endfeld[farbe.ordinal()+1][i].getFigur()==null)
				return false;
		}
		return true;
	}
}