package spiel;

public class TESTW�RFEL {
	public static void main(String[] args) {

		int[] folge={1,2,3,4,5,6,6,5,4,3,2,1};
		W�rfel wuerfel=new W�rfel(folge);
		for(int i=0;i<100;i++)
			System.out.println(wuerfel.w�rfele());
	}
}