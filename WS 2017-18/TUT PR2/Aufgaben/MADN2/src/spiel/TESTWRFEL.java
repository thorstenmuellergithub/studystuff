package spiel;

public class TESTWÜRFEL {
	public static void main(String[] args) {

		int[] folge={1,2,3,4,5,6,6,5,4,3,2,1};
		Würfel wuerfel=new Würfel(folge);
		for(int i=0;i<100;i++)
			System.out.println(wuerfel.würfele());
	}
}