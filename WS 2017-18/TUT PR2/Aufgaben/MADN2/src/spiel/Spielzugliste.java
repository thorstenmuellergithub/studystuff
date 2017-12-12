package spiel;

public class Spielzugliste {
	private Spielzug[] z�ge;
	
	public Spielzugliste(){
		z�ge=new Spielzug[0];
	}
	
	public void addZug(Spielzug zug){
		if (zug==null) return;
		for(Spielzug zugVorhanden:z�ge){
			// Zug bereits vorhanden?
			if(zugVorhanden.equals(zug)) return;
		}
		Spielzug[] z�geNeu=new Spielzug[z�ge.length+1];
		int i=0;
		while(i<z�ge.length){
			// Array koperen und neuen Zug hinzuf�gen
			z�geNeu[i]=z�ge[i];
			i++;
		}
		z�geNeu[i]=zug;
		z�ge=z�geNeu;
	}
	
	public void addZugliste(Spielzugliste z�ge){
		if (z�ge==null) return;
		if (z�ge.getAnzahlZ�ge()==0) return;
		Spielzug[] z�geExtern=z�ge.getZ�ge();
		for(Spielzug zug:z�geExtern){
			this.addZug(zug);
		}
	}
	
	public Spielzug[] getZ�ge(){
		return z�ge;
	}
	
	public int getAnzahlZ�ge(){
		return z�ge.length;
	}
	
	@Override
	public String toString(){
		String s="";
		for(int i=0;i<z�ge.length;i++){
			s+=z�ge[i];
			if(i<z�ge.length-1)s+="\n";
		}
		return s;
	}
}