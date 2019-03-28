/**
* Algoritmo de Lamport para el control de la exclusión mutua
*
*@author José Miguel A.J.
*@version 1.0
*/
import java.util.Arrays;
import java.util.concurrent.*;

public class algoLamport implements Runnable{
	private static int nh=4;
	private static int numero[]=new int[nh];
	private static boolean eligiendo[]=new boolean[nh];
	private int ih;
	private static int cont=0;

	public algoLamport(int indice){
		this.ih=indice;
	}

	public void run(){
		eligiendo[ih]=true;
		numero[ih]=1+max(numero);
		eligiendo[ih]=false;
		for(int j=0;j<nh;j++){
			while(eligiendo[j]) Thread.yield();
			while(numero[j]!=0 && (numero[j]<numero[ih])|| ((numero[j]==numero[ih]) && (j<ih))) Thread.yield();
		}

		/*Seccion Critica*/

		cont++;
		System.out.println("Hilos en la seccion critica: "+cont);
		cont--;

		/*Fin de Seccion Critica*/

		numero[ih]=0;
	}
	private static int max(int v[]){
		int m=v[0];
		for(int i=1;i<v.length;i++){
			if(m<v[i]){
				m=v[i];
			}
		}
		return m;
	}
	public static void main(String[] args) {
		Arrays.fill(numero,0);
		Arrays.fill(eligiendo,false);
		ExecutorService pool = Executors.newFixedThreadPool(nh);
		for(int i=0;i<nh;++i){
			Runnable tarea = new algoLamport(i);
			pool.submit(tarea);
		}
		pool.shutdown();
		while(!pool.isTerminated());
	}
}