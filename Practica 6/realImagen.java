/**
*Programa que realiza el suavizado de imágenes para una matriz nxn pixeles
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.Scanner;
import java.util.Random;

public class resImagen{
	private static int n=3;
	private static int[][] m;

	public static void main(String[] args) {
		Random rnd = new Random();
		m=new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				m[i][j]=rnd.nextInt(20);
			}
		}
		mostrar(m);
		int r[][]=new int[n][n];
		r=suavizado(m,n);
		mostrar(r);

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
		int [][]r = new int[n][n];
		for(int i=0;i<n;++i){
			for(int j=0;j<n;++j){
				m[i][j]=(4*m[i][j]-m[(i+1)%n][j]-m[i][(j+1)%n]-m[(i-1)%n][j]-m[i][(j-1)%n])/8;
			}
		}
		return r;
	}
}
