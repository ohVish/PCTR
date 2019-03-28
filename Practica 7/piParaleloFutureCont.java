/**
*Programa que calcula el numero PI.
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class piParaleloFutureCont implements Callable<Integer>{
	private static volatile int cont=0;
	private static Object cerrojo = new Object();
	private long inicio;
	private long fin;
	private int conth=0;

	public piParaleloFutureCont(long i,long f){
		this.inicio=i;
		this.fin=f;
	}

	public Integer call(){
		for(long i=inicio;i<fin;i++){
			double x= Math.random();
			double y= Math.random();
			if(Math.pow(x,2)+Math.pow(y,2)<=1){ 
				conth++;
			}
		}
		return conth;
	}

	public static void main(String[] args) {
		long n=10;
		for(n=10;n<=100000;n*=10){
			double cb = Double.parseDouble(args[0]);
			int nCores = Runtime.getRuntime().availableProcessors();
			int nTareas = (int)(nCores/(1-cb));
			int bloque = (int)n/nTareas; 
			double tiempo= System.nanoTime();
			for(int j=0;j<1000;j++){
				ExecutorService ex = Executors.newFixedThreadPool(nTareas);
				List<Future<Integer>> futures = new ArrayList<Future<Integer>>();

				for(int i=0;i<nTareas-1;i++){
					Future<Integer> f=ex.submit(new piParaleloFutureCont(i*bloque,(i+1)*bloque));
					futures.add(f);
				}
				Future<Integer> f1;
				f1=ex.submit(new piParaleloFutureCont((nTareas-1)*bloque,n));
				futures.add(f1);
				ex.shutdown();
				while(!ex.isTerminated());
				for(int i=0;i<futures.size();i++){
					try{
						f1=futures.get(i);
						cont+=f1.get();
					}catch(Exception e){ System.out.println("ERROR");}
				}
			System.out.println("El valor de la integral es "+ 4*(double)cont/n);
			}
			tiempo = (System.nanoTime() -tiempo)/1.0e9;
			Escribir.grafica("FutureContnew.txt",n,tiempo);
		}
	}

}
