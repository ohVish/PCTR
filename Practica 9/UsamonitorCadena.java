/**
*	Programa que utiliza la clase monitorCadena_1.java mediante el empleo de hilos.
*
*	@author	José Miguel Aragón Jurado
*	@version 1.0
*/

import java.util.concurrent.*;

public class UsamonitorCadena implements Runnable{
	private int tipo;
	private monitorCadena_1 buffer1;
	private monitorCadena_1 buffer2;
	private Object cerrojo;

	public UsamonitorCadena(int t, monitorCadena_1 bf1 ,monitorCadena_1 bf2, Object c){
		this.tipo=t;
		this.buffer1=bf1;
		this.buffer2=bf2;
		this.cerrojo=c;
	}

	public void run(){
		switch(tipo){
			case 0:
				Matriz m = new Matriz(10);
				m.genRandomMatrix();
				buffer1.insertar(m);
				System.out.println("Matriz generada.");
				break;
			case 1:
				Matriz t = buffer1.extraer();
				t=t.traspuesta();
				buffer2.insertar(t);
				System.out.println("Traspuesta generada.");
				break;
			case 2:
				Matriz r = buffer2.extraer();
				double diag=1;
				for(int i=0;i<10;i++){
					diag*=r.getElemento(i,i);
				}
				System.out.println("El producto de la diagonal principal es "+diag);
			break;
		}
	}

	public static void main(String[] args) {
		int nh=10;
		Object lock = new Object();
		ExecutorService ex = Executors.newFixedThreadPool(nh);
		monitorCadena_1 bf1 = new monitorCadena_1(100);
		monitorCadena_1 bf2 = new monitorCadena_1(50);
		for(int i=0;i<nh;i++){
			ex.submit(new UsamonitorCadena(i%3,bf1,bf2,lock));
		}
		ex.shutdown();
		while(!ex.isTerminated());
	}

}