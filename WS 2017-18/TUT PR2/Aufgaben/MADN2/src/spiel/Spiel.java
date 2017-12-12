package spiel;

import spiel.spielbrett.Spielbrett;

public class Spiel {
	private Spieler[] spieler;
	private Spielbrett spielbrett;
	private int spielerAmZug;
	private int spielerAnzahl;
	private Spielzugliste erlaubteZ�geManuell;
	
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
		W�rfel w�rfel=new W�rfel();
		Spieler spieler=new Spieler(spieler_name,spieler_farbe,w�rfel);
		this.spieler[i]=spieler;
		spielerAnzahl++;
		spielbrett.initFiguren(spieler);
	}
	
	public Spieler getSpielerAmZug(){
		return spieler[spielerAmZug];
	}
	private void n�chsterSpieler(){
		if(spielerAmZug<spielerAnzahl)
			spielerAmZug++;
		else
			spielerAmZug=1;
	}
	
	public int startManuell() {
		System.out.println(this);
		Spieler amZug=getSpielerAmZug();
		int zahl=amZug.w�rfele();
		erlaubteZ�geManuell=Spielregeln.getErlaubteZ�ge(this,zahl);
		if (erlaubteZ�geManuell.getAnzahlZ�ge()==0){
			System.out.println("Leider kann "+amZug.getName()+" nicht ziehen.");
			if (zahl!=6) n�chsterSpieler();
			return 0;
		}
		else{
			if (erlaubteZ�geManuell.getAnzahlZ�ge()==1){
				// es gibt nur einen m�glichen Zug -> diesen tun
				Spielregeln.ziehe(this,erlaubteZ�geManuell.getZ�ge()[0]);
				if (zahl!=6) n�chsterSpieler();
				return 0;
			}
			else{
				// es gibt mehrere m�gliche Z�ge -> User entscheidet!
				System.out.println("Folgende Z�ge sind erlaubt:");
				for(int i=0;i<erlaubteZ�geManuell.getZ�ge().length;i++){
					System.out.println(i+": "+erlaubteZ�geManuell.getZ�ge()[i]);
				}
				System.out.println("W�hlen Sie Ihren Zug:");
				return zahl;				
			}
		}
	}
	public void zieheManuell(int wahl,int zahl) {
		if (wahl>=erlaubteZ�geManuell.getZ�ge().length)
			throw new RuntimeException("Dieser Zug ist nicht erlaubt! Das Spiel ist beendet!");
		Spielregeln.ziehe(this,erlaubteZ�geManuell.getZ�ge()[wahl]);
		if (zahl!=6) n�chsterSpieler();
	}
	
	public void startAutomatik(int automatisierteZugzahl) {
		for(int i=0;i<automatisierteZugzahl;i++){
			if (zuEnde()) break;
			System.out.println(this);
			Spieler amZug=getSpielerAmZug();
			int zahl=amZug.w�rfele();
			Spielzugliste erlaubteZ�ge=Spielregeln.getErlaubteZ�ge(this,zahl);
			if (erlaubteZ�ge.getAnzahlZ�ge()==0)
				System.out.println("Leider kann "+amZug.getName()+" nicht ziehen.");
			else
				Spielregeln.ziehe(this,erlaubteZ�ge.getZ�ge()[0]); // einfach den 1. erlaubten Zug nehmen
			if (zahl!=6)
				n�chsterSpieler();
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