/**
*Programa que calcula el numero PI.
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.concurrent.*;

public class piParalelouniCont implements Runnable{
	private static volatile int cont;
	private static Object cerrojo = new Object();
	private long inicio;
	private long fin;

	public piParalelouniCont(long i,long f){
		this.inicio=i;
		this.fin=f;
	}

	public void run(){
		for(long i=inicio;i<fin;i++){
			double x= Math.random();
			double y= Math.random();
			if(Math.pow(x,2)+Math.pow(y,2)<=1){ 
				synchronized(cerrojo){
					cont++;
				}
			}
		}
	}

	public static void main(String[] args) {
		long n=10;
		double cb = Double.parseDouble(args[0]);
		int nCores = Runtime.getRuntime().availableProcessors();
		int nTareas = (int)(nCores/(1-cb));
		for(n=10;n<=100000;n*=10){
			double tiempo = System.nanoTime();
			for(int j=0;j<1000;j++){
				cont=0;
				int bloque = (int) n/nTareas; 
				ExecutorService ex = Executors.newFixedThreadPool(nTareas);
				for(int i=0;i<nTareas-1;i++){
					ex.submit(new piParalelouniCont(i*bloque,(i+1)*bloque));
				}
				ex.submit(new piParalelouniCont((nTareas-1)*bloque,n));
				ex.shutdown();
				while(!ex.isTerminated());
				//System.out.println("El valor de la integral es "+ 4*(double)cont/n);
			}
			tiempo = (System.nanoTime()-tiempo)/1.0e9;
			Escribir.grafica("piunicontnew.txt",n,tiempo);
		}
	}

}

