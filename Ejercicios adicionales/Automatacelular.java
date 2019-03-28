
import java.util.Random;

public class Automatacelular{
	public static void main(String[] args){
		Random rnd = new Random();
		int[] A_t= new int[100000];
		for(int i=0;i<A_t.length;i++){
			A_t[i]=rnd.nextInt(2);
			System.out.print(A_t[i]);
		}
		System.out.println("");
		for(int gen=0; gen<100000;gen ++){
			int[] A_t1 = new int[A_t.length];
			for(int i=0;i<A_t.length;i++){
				if(i==0){
					A_t1[i]=(A_t[A_t.length-1]+A_t[i]+A_t[i+1])%2;
				}
				else{
					if(i==A_t.length-1){
						A_t1[i]=(A_t[i-1]+A_t[i]+A_t[0])%2;
					}
					else{
						A_t1[i]=(A_t[i-1]+A_t[i]+A_t[i+1])%2;
					}

				}
				System.out.print(A_t1[i]);
			}
				System.out.println("");
			A_t=A_t1;
		}
	}
}