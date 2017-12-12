package spiel;

import spiel.spielbrett.Spielfeld;

public class Spielzug {
	private Spielfeld von;
	private Spielfeld nach;
	
	public Spielzug(Spielfeld von,Spielfeld nach){
		setVon(von);
		setNach(nach);
	}
	
	public Spielfeld getVon() {
		return von;
	}
	private void setVon(Spielfeld von) {
		if (von==null)
			throw new RuntimeException("NULL ist nicht erlaubt!");
		this.von = von;
	}
	public Spielfeld getNach() {
		return nach;
	}
	private void setNach(Spielfeld nach) {
		if (nach==null)
			throw new RuntimeException("NULL ist nicht erlaubt!");
		this.nach = nach;
	}
	
	@Override
	public String toString(){
		return "Zug von "+getVon()+" nach "+getNach();
	}
	
	@Override
	public boolean equals(Object o){
		if (o==null) return false;
		if (o==this) return true;
		if (o.getClass()!=this.getClass()) return false;
		Spielzug z=(Spielzug)o;
		return (z.getVon().equals(this.getVon()) &&
						z.getNach().equals(this.getNach()));
	}
}