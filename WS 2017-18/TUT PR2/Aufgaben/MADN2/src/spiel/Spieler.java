package spiel;

import spiel.spielbrett.Spielfigur;

public class Spieler {
	private String name;
	private Farbe farbe;
	private Würfel würfel;
	private Spielfigur[] figuren;
	
	public Spieler(String name,Farbe farbe,Würfel würfel){
		setName(name);
		setFarbe(farbe);
		setWürfel(würfel);
		figuren=new Spielfigur[4];
	}
	
	public int würfele(){
		int zahl=würfel.würfele();
		System.out.println(getName()+" hat eine "+zahl+" gewürfelt.");
		return zahl;
	}
	
	private void setWürfel(Würfel würfel) {
		if (würfel==null)
			throw new RuntimeException("Würfel ungültig!");
		this.würfel = würfel;
	}

	public String getName() {
		return name;
	}
	private void setName(String name) {
		if ((name==null)||(name.length()<3))
			throw new RuntimeException("Name ungültig!");
		this.name = name;
	}

	public Farbe getFarbe() {
		return farbe;
	}
	private void setFarbe(Farbe farbe) {
		if (farbe==null)
			throw new RuntimeException("Farbe ungültig!");
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