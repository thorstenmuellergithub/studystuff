package spiel;

import java.util.Random;

public class W�rfel {
	private int[] folge;
	private int anzW�rfe;
	private Random zufallsgenerator;
	
	public W�rfel(){
		folge=new int[0];
		anzW�rfe=0;
		zufallsgenerator=new Random();
	}
	
	public W�rfel(int[] folge){
		this();
		if (folge!=null)
				this.folge=folge;
	}
	
	public int w�rfele(){
		if (anzW�rfe<folge.length){
			return folge[anzW�rfe++];
		}
		anzW�rfe++;
		return 1+zufallsgenerator.nextInt(6);
	}

	public int getAnzW�rfe() {
		return anzW�rfe;
	}
}