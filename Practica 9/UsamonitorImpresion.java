/**
*	Programa que utiliza la clase monitorImpresion
*
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*/

import java.util.concurrent.*;

public class UsamonitorImpresion implements Runnable{
	private monitorImpresion impresoras;
	private int id;

	public UsamonitorImpresion(monitorImpresion imp, int i){
		this.impresoras=imp;
		this.id=i;
	}

	public void run(){
		int n=impresoras.pedirImpresora();
		System.out.println("Soy el hilo "+this.id+" y tengo la impresora "+n);
		impresoras.liberarImpresora(n);
	}

	public static void main(String[] args) throws InterruptedException{
		double cb=0.5;
		monitorImpresion imp = new monitorImpresion();
		int nCores = Runtime.getRuntime().availableProcessors();
		int nh = (int)((double)nCores/(1-cb));
		ExecutorService ex = Executors.newFixedThreadPool(nh);
		for(int i=0;i<nh;i++){
			ex.submit(new UsamonitorImpresion(imp,i+1));
		}
		ex.shutdown();
		while(!ex.awaitTermination(1,TimeUnit.SECONDS));
	}
}