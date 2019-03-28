/**
*Programa que realiza el suavizado de imágenes para una matriz nxn pixeles
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.Scanner;
import java.util.Random;

public class resImagen{
	private static int n=10000;

	public static void main(String[] args) {
		Random rnd = new Random();
		int[][] m=new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				m[i][j]=rnd.nextInt(20);
			}
		}
		
		//mostrar(m,n);
		double tiempo= System.nanoTime();
		int r[][]=new int[n][n];
		r=suavizado(m,n);
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
	private static int[][] suavizado(int[][] m,int n){
		int iant,jant;
		int [][]r = new int[n][n];
		for(int i=0;i<n;++i){
			for(int j=0;j<n;++j){
				iant=i-1;
				jant=j-1;
				if(iant<0) iant=n-1;
				if(jant<0) jant=n-1;
				r[i][j]=(4*m[i][j]-m[(i+1)%n][j]-m[i][(j+1)%n]-m[iant][j]-m[i][jant])/8;
				if(r[i][j]<0) r[i][j]=0;
			}
		}
		return r;
	}
}
