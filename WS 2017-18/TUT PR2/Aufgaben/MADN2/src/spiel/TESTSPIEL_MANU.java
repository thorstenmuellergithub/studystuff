package spiel;

import java.util.Scanner;

public class TESTSPIEL_MANU {
	
	public static void main(String[] args) {
		
		Spiel spiel=new Spiel(
				"Frank",Farbe.GELB,
				"Hans",Farbe.ROT);
		
		// manuelle Spielsteuerung:
		// bei mehreren mögichen Zügen
		// muss der Benutzer auswählen
		while (!spiel.zuEnde()){
			int zahl=spiel.startManuell();
			// zahl=0: man hat keine Wahl
			// zahl>0: die gewürfelte Zahl
			if (zahl!=0){ 
				int wahl=-1;
				do{
					wahl=leseInt();
					if (wahl<0) 
						System.out.println("Die Eingabe ist ungültig, bitte Wiederholen:");
				}while (wahl<0);
				try{
					spiel.zieheManuell(wahl,zahl); 						
				}
				catch (Exception e){
					System.out.println(e.getMessage());
					return;
				}
			}
		}			
	}
	
	
	private static int leseInt(){
		try{
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			String eingabe=scanner.nextLine();
			return Integer.parseInt(eingabe);			
		}
		catch (Exception e){
			return -1;
		}
	}
}