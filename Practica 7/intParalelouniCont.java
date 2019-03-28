/**
*Programa que calcula la integral del seno en el intervalo [0,1] por el método de Montecarlo.
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.concurrent.*;

public class intParalelouniCont implements Runnable{
	private static volatile int cont;
	private static Object cerrojo = new Object();
	private int inicio;
	private int fin;

	public intParalelouniCont(int i,int f){
		this.inicio=i;
		this.fin=f;
	}

	public void run(){
		for(int i=inicio;i<fin;i++){
			double x= Math.random();
			double y= Math.random();
			if(y<=Math.sin(x)){
				synchronized(cerrojo){
					cont++;
				}
			}
		}
	}

	public static void main(String[] args) {
		int n=1000;
		double cb = Double.parseDouble(args[0]);
		int nCores = Runtime.getRuntime().availableProcessors();
		int nTareas = (int)(nCores/(1-cb));
		int bloque = n/nTareas; 
		ExecutorService ex = Executors.newFixedThreadPool(nTareas);
		for(int i=0;i<nTareas-1;i++){
			ex.submit(new intParalelouniCont(i*bloque,(i+1)*bloque));
		}
		ex.submit(new intParalelouniCont((nTareas-1)*bloque,n));
		ex.shutdown();
		while(!ex.isTerminated());
		System.out.println("El valor de la integral es "+ (double)cont/n);
		
	}

}

