/**
*Programa que multiplica una matriz por un vector de forma secuencial
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Scanner;
import java.util.Random;

public class matVector{
	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		System.out.println("Introduce la dimension de la matriz:");
		int n = sc.nextInt();
		int[][] m = new int[n][n];
		int[] v = new int[n];
		int elec=0;
		do{
			System.out.println("Elija una de las opciones:");
			System.out.println("\t 1. Rellenar automaticamente.");
			System.out.println("\t 2. Introducir datos.");
			System.out.println("\t 3. Salir.");
			elec=sc.nextInt();
			if(elec==1){
				for(int i=0;i<n;i++){
					v[i]=rnd.nextInt(10);
					for(int j=0;j<n;j++){
						m[i][j]=rnd.nextInt(10);
					}
				}
				mostrar(m,n);
				System.out.println("*");
				double tiempo = System.nanoTime();
				int[] r=multiplicar(m,v,n);
				tiempo=System.nanoTime()-tiempo;
				System.out.println("T="+(tiempo)/1.0e6);
				mostrarv(v,n);
				System.out.println("-");
				mostrarv(r,n);

			}
			else if(elec==2){
				rellenatodo(m,v,n,sc);
				mostrar(m,n);
				System.out.println("*");
				int[] r=multiplicar(m,v,n);
				mostrarv(v,n);
				System.out.println("-");
				mostrarv(r,n);
			}
		}while(elec!=3);
		sc.close();
	}
	private static void rellenatodo(int[][]m,int[]v,int n,Scanner sc){
				for(int i=0;i<n;i++){
					System.out.println("Introduce el elemento "+(i+1)+" del vector:");
					v[i]=sc.nextInt();
					for(int j=0;j<n;j++){
						System.out.println("Introduce el elemento ("+(i+1)+","+(j+1)+") de la matriz:");
						m[i][j]=sc.nextInt();
					}
				}	}
	private static void mostrar(int[][] m,int n){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(m[i][j]+",");
			}
			System.out.println();
		}
	}
	private static void mostrarv(int[] v,int n){
		for(int i=0;i<n;i++){
				System.out.println(v[i]);
		}
	}
	private static int[] multiplicar(int [][] m, int[] v, int n){
		int[] vr = new int[n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;++j){
				vr[i]+=m[i][j]*v[j];
			}
		}
		return vr;
	}
}