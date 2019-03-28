/**
*Programa que calcula el numero PI.
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.concurrent.*;

public class piParalelomultiCont implements Runnable{
	private static volatile int cont=0;
	private static Object cerrojo = new Object();
	private int inicio;
	private int fin;
	private int conth=0;

	public piParalelomultiCont(int i,int f){
		this.inicio=i;
		this.fin=f;
	}

	public void run(){
		for(int i=inicio;i<fin;i++){
			double x= Math.random();
			double y= Math.random();
			if(Math.pow(x,2)+Math.pow(y,2)<=1){ 
				conth++;
			}
		}
		synchronized(cerrojo){
			cont+=conth;
		}
	}

	public static void main(String[] args) {
		int n=200000;
		double cb = Double.parseDouble(args[0]);
		int nCores = Runtime.getRuntime().availableProcessors();
		int nTareas = (int)(nCores/(1-cb));
		int bloque = n/nTareas; 
		ExecutorService ex = Executors.newFixedThreadPool(nTareas);
		for(int i=0;i<nTareas-1;i++){
			ex.submit(new piParalelomultiCont(i*bloque,(i+1)*bloque));
		}
		ex.submit(new piParalelomultiCont((nTareas-1)*bloque,n));
		ex.shutdown();
		while(!ex.isTerminated());
		System.out.println("El valor de la integral es "+ 4*(double)cont/n);
	}

}
