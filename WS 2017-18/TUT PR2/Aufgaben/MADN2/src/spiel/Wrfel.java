package spiel;

import java.util.Random;

public class Würfel {
	private int[] folge;
	private int anzWürfe;
	private Random zufallsgenerator;
	
	public Würfel(){
		folge=new int[0];
		anzWürfe=0;
		zufallsgenerator=new Random();
	}
	
	public Würfel(int[] folge){
		this();
		if (folge!=null)
				this.folge=folge;
	}
	
	public int würfele(){
		if (anzWürfe<folge.length){
			return folge[anzWürfe++];
		}
		anzWürfe++;
		return 1+zufallsgenerator.nextInt(6);
	}

	public int getAnzWürfe() {
		return anzWürfe;
	}
}