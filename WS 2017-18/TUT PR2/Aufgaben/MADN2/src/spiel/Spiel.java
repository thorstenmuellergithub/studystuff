package spiel;

import spiel.spielbrett.Spielbrett;

public class Spiel {
	private Spieler[] spieler;
	private Spielbrett spielbrett;
	private int spielerAmZug;
	private int spielerAnzahl;
	private Spielzugliste erlaubteZügeManuell;
	
	private Spiel(){
		spieler=new Spieler[5];
		spielbrett=new Spielbrett();
		spielerAmZug=1;
	}
	public Spiel(
			String spieler1_name,Farbe spieler1_farbe
			) {
		this();
		addSpieler(spieler1_name,spieler1_farbe);
	}
	public Spiel(
			String spieler1_name,Farbe spieler1_farbe,
			String spieler2_name,Farbe spieler2_farbe
			) {
		this(spieler1_name,spieler1_farbe);
		addSpieler(spieler2_name,spieler2_farbe);
	}
	public Spiel(
			String spieler1_name,Farbe spieler1_farbe,
			String spieler2_name,Farbe spieler2_farbe,
			String spieler3_name,Farbe spieler3_farbe
			) {		
		this(spieler1_name,spieler1_farbe,
				spieler2_name,spieler2_farbe);
		addSpieler(spieler3_name,spieler3_farbe);
	}
	public Spiel(
			String spieler1_name,Farbe spieler1_farbe,
			String spieler2_name,Farbe spieler2_farbe,
			String spieler3_name,Farbe spieler3_farbe,
			String spieler4_name,Farbe spieler4_farbe
			) {		
		this(spieler1_name,spieler1_farbe,
				spieler2_name,spieler2_farbe,
				spieler3_name,spieler3_farbe);
		addSpieler(spieler4_name,spieler4_farbe);
	}
	
	private void addSpieler(String spieler_name,Farbe spieler_farbe){
		int i=1;
		while(this.spieler[i]!=null){
			if ((""+this.spieler[i].getFarbe()).equals(""+spieler_farbe))
				throw new RuntimeException("Die Farbe darf nicht doppelt belegt sein!");
			i++;
		}
		Würfel würfel=new Würfel();
		Spieler spieler=new Spieler(spieler_name,spieler_farbe,würfel);
		this.spieler[i]=spieler;
		spielerAnzahl++;
		spielbrett.initFiguren(spieler);
	}
	
	public Spieler getSpielerAmZug(){
		return spieler[spielerAmZug];
	}
	private void nächsterSpieler(){
		if(spielerAmZug<spielerAnzahl)
			spielerAmZug++;
		else
			spielerAmZug=1;
	}
	
	public int startManuell() {
		System.out.println(this);
		Spieler amZug=getSpielerAmZug();
		int zahl=amZug.würfele();
		erlaubteZügeManuell=Spielregeln.getErlaubteZüge(this,zahl);
		if (erlaubteZügeManuell.getAnzahlZüge()==0){
			System.out.println("Leider kann "+amZug.getName()+" nicht ziehen.");
			if (zahl!=6) nächsterSpieler();
			return 0;
		}
		else{
			if (erlaubteZügeManuell.getAnzahlZüge()==1){
				// es gibt nur einen möglichen Zug -> diesen tun
				Spielregeln.ziehe(this,erlaubteZügeManuell.getZüge()[0]);
				if (zahl!=6) nächsterSpieler();
				return 0;
			}
			else{
				// es gibt mehrere mögliche Züge -> User entscheidet!
				System.out.println("Folgende Züge sind erlaubt:");
				for(int i=0;i<erlaubteZügeManuell.getZüge().length;i++){
					System.out.println(i+": "+erlaubteZügeManuell.getZüge()[i]);
				}
				System.out.println("Wählen Sie Ihren Zug:");
				return zahl;				
			}
		}
	}
	public void zieheManuell(int wahl,int zahl) {
		if (wahl>=erlaubteZügeManuell.getZüge().length)
			throw new RuntimeException("Dieser Zug ist nicht erlaubt! Das Spiel ist beendet!");
		Spielregeln.ziehe(this,erlaubteZügeManuell.getZüge()[wahl]);
		if (zahl!=6) nächsterSpieler();
	}
	
	public void startAutomatik(int automatisierteZugzahl) {
		for(int i=0;i<automatisierteZugzahl;i++){
			if (zuEnde()) break;
			System.out.println(this);
			Spieler amZug=getSpielerAmZug();
			int zahl=amZug.würfele();
			Spielzugliste erlaubteZüge=Spielregeln.getErlaubteZüge(this,zahl);
			if (erlaubteZüge.getAnzahlZüge()==0)
				System.out.println("Leider kann "+amZug.getName()+" nicht ziehen.");
			else
				Spielregeln.ziehe(this,erlaubteZüge.getZüge()[0]); // einfach den 1. erlaubten Zug nehmen
			if (zahl!=6)
				nächsterSpieler();
		}
	}

	public boolean zuEnde(){
		return Spielregeln.istSpielende(this);
	}

	public Spielbrett getSpielbrett(){
		return spielbrett;
	}
	
	@Override
	public String toString(){
		return "Spieler "+spielerAmZug+": "+getSpielerAmZug()+" ist am Zug.";
	}
}