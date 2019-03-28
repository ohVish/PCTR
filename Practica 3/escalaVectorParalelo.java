/**
* Programa que realiza el escalado de un vector de forma paralela.
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Random;

public class escalaVectorParalelo extends Thread{
	private int inicio,fin;
	private static int[] v = new int[100000];
	private static int k=100;

	public escalaVectorParalelo(int i, int j){
		this.inicio=i;
		this.fin=j;
	}

	public static void main(String[] Args)throws InterruptedException{
		rellenar(v);
		escalaVectorParalelo[] hilos = new escalaVectorParalelo[400];
		int bloque= v.length/hilos.length;
		for(int i=0;i<hilos.length-1;i++){
			hilos[i]=new escalaVectorParalelo(i*bloque,(i+1)*bloque);
			hilos[i].start();
		}
		hilos[hilos.length-1]=new escalaVectorParalelo((hilos.length-1)*bloque,v.length);
		hilos[hilos.length-1].start();
		for(int i=0;i<hilos.length;i++){
			hilos[i].join();
		}	

	}
	static void rellenar(int[] v){
		Random rnd = new Random();
		for(int i=0;i<v.length;i++){
			v[i]=rnd.nextInt(100);
		}
	}

	public void run(){
		for(int i=inicio;i<fin;i++){
			v[i]*=k;
		}
	}
	static void mostrar(int[] v){
		for(int i=0;i<v.length-1;i++){
			System.out.print(v[i]+",");
		}
		System.out.println(v[v.length-1]);
	}
}