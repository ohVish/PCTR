/**
*Programa que calcula la integral del seno en el intervalo [0,1] por el método de Montecarlo.
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.concurrent.*;
import java.util.*;

public class intParaleloFutureCont implements Callable<Integer>{
	private int conth;
	private int inicio;
	private int fin;

	public intParaleloFutureCont(int i,int f){
		this.inicio=i;
		this.fin=f;
		this.conth=0;
	}

	public Integer call(){
		for(int i=inicio;i<fin;i++){
			double x= Math.random();
			double y= Math.random();
			if(y<=Math.sin(x)){
					conth++;
			}
		}
		return conth;
	}

	public static void main(String[] args) throws Exception{
		int n=100000;
		double cb = Double.parseDouble(args[0]);
		int nCores = Runtime.getRuntime().availableProcessors();
		int nTareas = (int)(nCores/(1-cb));
		int bloque = n/nTareas; 
		ExecutorService ex = Executors.newFixedThreadPool(nTareas);
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		for(int i=0;i<nTareas-1;i++){
			Future<Integer> f = ex.submit(new intParaleloFutureCont(i*bloque,(i+1)*bloque));
			futures.add(f);
		}
		Future<Integer> f1 = ex.submit(new intParaleloFutureCont((nTareas-1)*bloque,n));
		futures.add(f1);
		ex.shutdown();
		while(!ex.isTerminated());
		Future<Integer> f;
		int cont=0;
		for(int i=0;i<futures.size();i++){
			f=futures.get(i);
			cont+=f.get();
		}
		System.out.println("El valor de la integral es "+ (double)cont/n);
	}

}

