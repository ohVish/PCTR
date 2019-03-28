/**
*Programa que realiza el suavizado de imágenes para una matriz nxn pixeles
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.*;

public class resImagenParFin implements Runnable{
	private static int n=10000;
	private static int[][] m=new int[n][n];
	private static int r[][]=new int[n][n];
	private int fila;

	public resImagenParFin(int f){
		this.fila=f;
	}
	public void run(){
		int iant;
		int jant;
		for(int j=0;j<n;++j){
			iant=fila-1;
			jant=j-1;
			if(iant<0) iant=n-1;
			if(jant<0) jant=n-1;
			r[fila][j]=(4*m[fila][j]-m[(fila+1)%n][j]-m[fila][(j+1)%n]-m[iant][j]-m[fila][jant])/8;
		}
	}
	public static void main(String[] args) {
		Random rnd = new Random();
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				m[i][j]=rnd.nextInt(20);
			}
		}
		//mostrar(m,n);
		double tiempo=System.nanoTime();
		ejecutar();
		tiempo=System.nanoTime()-tiempo;
		System.out.println("T="+tiempo/1.0e6);
		//mostrar(r,n);

	}
	private static void mostrar(int[][] m,int n){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(m[i][j]+",");
			}
			System.out.println();
		}
	}
	private static void ejecutar(){
		ExecutorService ex = Executors.newFixedThreadPool(n);
		for(int i=0;i<n;i++){
			ex.submit(new resImagenParFin(i));
		}
		ex.shutdown();
		while(!ex.isTerminated());
	}
}
