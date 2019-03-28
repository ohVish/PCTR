/**
*Algoritmo de Eisemberg McGuire
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.concurrent.*;

public class algEisenbergMcGuire implements Runnable{
	private static enum Estado{
		I, E, A
	}
	private static volatile int nh=3;
	private static volatile Estado estado[]=new Estado[nh];
	private volatile static int turno=0;
	private int i=0;
	private static volatile int cont=0;
	private int ih;

	public algEisenbergMcGuire(int numero){
		this.ih=numero;
		this.i=0;
	}

	public void run(){
		do{
			estado[ih]=Estado.E;
			i=turno;
			while(i!=ih){
				//System.out.println("Hilo "+Thread.currentThread()+" "+i);
				if(estado[i]!=Estado.I){
					i=turno;
				}
				else{
					i=(i+1)%nh;
				}
			}
			estado[ih]=Estado.A;

			i=0;
			while((i<nh) && ((i==ih)|| (estado[i]!=Estado.A))){
				++i;
			}
		}while(!((i>=nh) && ((turno==ih) || (estado[turno]==Estado.I))));
		turno=ih;
		/* SECCION CRITICA */
		cont++;
		System.out.println("Numero de hilos en la seccion critica: "+cont);
		cont--;
		/* FIN SECCION CRITICA*/
		i=(turno+1)%nh;
		while(estado[i]==Estado.I){
			i=(i+1)%nh;
		}
		turno=i;
		estado[ih]=Estado.I;

	}


	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(nh);
		for(int i=0;i<nh;i++){
			Runnable tarea = new algEisenbergMcGuire(i);
			pool.execute(tarea);
		}
		pool.shutdown();
		while (!pool.isTerminated()) {
		}
	}
}