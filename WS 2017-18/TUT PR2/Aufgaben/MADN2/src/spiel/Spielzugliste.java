package spiel;

public class Spielzugliste {
	private Spielzug[] züge;
	
	public Spielzugliste(){
		züge=new Spielzug[0];
	}
	
	public void addZug(Spielzug zug){
		if (zug==null) return;
		for(Spielzug zugVorhanden:züge){
			// Zug bereits vorhanden?
			if(zugVorhanden.equals(zug)) return;
		}
		Spielzug[] zügeNeu=new Spielzug[züge.length+1];
		int i=0;
		while(i<züge.length){
			// Array koperen und neuen Zug hinzufügen
			zügeNeu[i]=züge[i];
			i++;
		}
		zügeNeu[i]=zug;
		züge=zügeNeu;
	}
	
	public void addZugliste(Spielzugliste züge){
		if (züge==null) return;
		if (züge.getAnzahlZüge()==0) return;
		Spielzug[] zügeExtern=züge.getZüge();
		for(Spielzug zug:zügeExtern){
			this.addZug(zug);
		}
	}
	
	public Spielzug[] getZüge(){
		return züge;
	}
	
	public int getAnzahlZüge(){
		return züge.length;
	}
	
	@Override
	public String toString(){
		String s="";
		for(int i=0;i<züge.length;i++){
			s+=züge[i];
			if(i<züge.length-1)s+="\n";
		}
		return s;
	}
}