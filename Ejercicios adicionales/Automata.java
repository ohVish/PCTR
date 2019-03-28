
import java.util.Random;

public class Automatacelular{
	public static void main(String[] args){
		Random rnd = new Random();
		int[] A_t= new int[10];
		for(int i=0;i<A_t.length;i++){
			A_t[i]=rnd.nextInt(3);
			System.out.println(A_t[i]);
		}
		System.out.println("");
	}
}