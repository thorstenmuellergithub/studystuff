package spiel.spielbrett;

import spiel.Farbe;

public class Spielfeld {
	private String id;
	private Farbe farbe; 
	private boolean istStartfeld;
	private boolean istEndfeld;
	private Spielfeld nachfolgerLauffeld;
	private Spielfeld nachfolgerEndfeld;
	private Spielfigur figur;

	public Spielfeld(String id){
		setId(id);
	}
	public Spielfeld(String id,Farbe farbe,boolean istStartfeld,boolean istEndfeld){
		setId(id);
		setFarbe(farbe);
		if (istStartfeld && istEndfeld)
			throw new RuntimeException("Ein Feld kann nicht gleichzeitig Start- und Endfeld sein!");
		setStartfeld(istStartfeld);
		setEndfeld(istEndfeld);
	}
	
	public String getId() {
		return id;
	}
	private void setId(String id) {
		if ((id==null)||(id.length()<1))
			throw new RuntimeException("ID ungültig!");
		this.id = id;
	}

	public Farbe getFarbe() {
		return farbe;
	}
	private void setFarbe(Farbe farbe) {
		this.farbe = farbe;
	}

	public boolean istStartfeld() {
		return istStartfeld;
	}
	private void setStartfeld(boolean istStartfeld) {
		this.istStartfeld = istStartfeld;
	}
	
	public boolean istEndfeld() {
		return istEndfeld;
	}
	private void setEndfeld(boolean istEndfeld) {
		this.istEndfeld = istEndfeld;
	}
	
	public Spielfeld getNachfolgerLauffeld() {
		return nachfolgerLauffeld;
	}
	public void setNachfolgerLauffeld(Spielfeld nachfolgerLauffeld) {
		this.nachfolgerLauffeld = nachfolgerLauffeld;
	}
	
	public Spielfeld getNachfolgerEndfeld() {
		return nachfolgerEndfeld;
	}
	public void setNachfolgerEndfeld(Spielfeld nachfolgerEndfeld) {
		this.nachfolgerEndfeld = nachfolgerEndfeld;
	}
	
	public Spielfigur getFigur() {
		return figur;
	}
	public void setFigur(Spielfigur figur) {
		this.figur = figur;
	}
	
	@Override
	public String toString(){
		String start_ende=": ";
		if (istStartfeld) start_ende+="Startfeld "+getFarbe()+" ";
		if (istEndfeld) start_ende+="Endfeld "+getFarbe()+" ";
		String nachfolger="";
		if (nachfolgerLauffeld!=null) nachfolger+="-> "+nachfolgerLauffeld.getId()+" ";
		if (nachfolgerEndfeld!=null) nachfolger+="-> "+nachfolgerEndfeld.getId()+" "+nachfolgerEndfeld.getFarbe()+" ";
		return "Feld "+getId()+start_ende+nachfolger;
	}
	
	@Override
	public boolean equals(Object o){
		if (o==null) return false;
		if (o==this) return true;
		if (o.getClass()!=this.getClass()) return false;
		Spielfeld f=(Spielfeld)o;
		return (
				(""+f.getFarbe()).equals(""+this.getFarbe()) &&
				f.getId().equals(this.getId()));
	}
}