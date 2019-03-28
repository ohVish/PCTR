/**
*	Programa que se encarga de utilizar el monitor lectorEscritor.java
*
*	@author	José Miguel Aragón Jurado
*	@version 1.0
*
*/

import java.util.concurrent.*;
import java.util.Scanner;

public class usalectorEscritor implements Runnable{
	private boolean escritor;
	private static lectorEscritor libro;

	//Constructor de la clase
	public usalectorEscritor(boolean val, lectorEscritor l){
		this.escritor=val;
		this.libro=l;
	}

	public void run(){
		try{
			if(escritor==true){
				libro.inicia_escritura();
				Thread.sleep(1000);
				libro.fin_escritura();
			}
			else{
				libro.inicia_lectura();
				Thread.sleep(500);
				libro.fin_lectura();
			}
		}catch(InterruptedException e){System.out.println("Error");}
	}

	public static void main(String[] args) {
		lectorEscritor libro = new lectorEscritor();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el numero de lectores:");
		int nlectores = sc.nextInt();
		System.out.println("Introduce el numero de escritores:");
		int nescritores = sc.nextInt();
		ExecutorService ex = Executors.newFixedThreadPool(nlectores+nescritores);
		for(int i=0;i<nescritores;i++){
			ex.submit(new usalectorEscritor(true,libro));
		}
		for(int i=0;i<nlectores;i++){
			ex.submit(new usalectorEscritor(false,libro));
		}
		ex.shutdown();
		while(!ex.isTerminated());
	}
}