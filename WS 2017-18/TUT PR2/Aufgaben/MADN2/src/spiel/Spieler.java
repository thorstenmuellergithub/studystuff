package spiel;

import spiel.spielbrett.Spielfigur;

public class Spieler {
	private String name;
	private Farbe farbe;
	private W�rfel w�rfel;
	private Spielfigur[] figuren;
	
	public Spieler(String name,Farbe farbe,W�rfel w�rfel){
		setName(name);
		setFarbe(farbe);
		setW�rfel(w�rfel);
		figuren=new Spielfigur[4];
	}
	
	public int w�rfele(){
		int zahl=w�rfel.w�rfele();
		System.out.println(getName()+" hat eine "+zahl+" gew�rfelt.");
		return zahl;
	}
	
	private void setW�rfel(W�rfel w�rfel) {
		if (w�rfel==null)
			throw new RuntimeException("W�rfel ung�ltig!");
		this.w�rfel = w�rfel;
	}

	public String getName() {
		return name;
	}
	private void setName(String name) {
		if ((name==null)||(name.length()<3))
			throw new RuntimeException("Name ung�ltig!");
		this.name = name;
	}

	public Farbe getFarbe() {
		return farbe;
	}
	private void setFarbe(Farbe farbe) {
		if (farbe==null)
			throw new RuntimeException("Farbe ung�ltig!");
		this.farbe = farbe;
	}
	
	public void addFigur(Spielfigur figur) {
		int i=0;
		while(figuren[i]!=null){
			i++;
		}
		figuren[i]=figur;
	}
	public Spielfigur[] getFiguren() {
		return figuren;
	}

	@Override
	public String toString(){
		return "Spieler "+getName()+" ("+getFarbe()+")";
	}
}