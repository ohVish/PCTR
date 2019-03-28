/**
*Programa que multiplica una matriz por un vector de forma secuencial
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Scanner;
import java.util.Random;

public class prodMat{
	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		System.out.println("Introduce las filas de la matriz 1:");
		int f1 = sc.nextInt();
		System.out.println("Introduce las columnas de la matriz 1:");
		int c1 = sc.nextInt();
		System.out.println("Introduce las columnas de la matriz 2:");
		int c2 = sc.nextInt();
		int[][] m1 = new int[f1][c1];
		int[][] m2 = new int[c1][c2];
		int[][] mr = new int[f1][c2];
		int elec=0;
		do{
			System.out.println("Elija una de las opciones:");
			System.out.println("\t 1. Rellenar automaticamente.");
			System.out.println("\t 2. Introducir datos.");
			System.out.println("\t 3. Salir.");
			elec=sc.nextInt();
			if(elec==1){
				for(int i=0;i<f1;i++){
					for(int j=0;j<c1;j++){
						m1[i][j]=rnd.nextInt(10);
					}
				}
				for(int i=0;i<c1;i++){
					for(int j=0;j<c2;j++){
						m2[i][j]=rnd.nextInt(10);
					}
				}				
				//mostrar(m1,f1,c1);
				//System.out.println("*");
				//mostrar(m2,c1,c2);
				//System.out.println("-");
				long tiempo = System.nanoTime();
				mr=multiplicar(m1,m2,f1,c1,c2);
				tiempo=System.nanoTime()-tiempo;
				//mostrar(mr,f1,c2);
				System.out.println("T="+tiempo/1000000);

			}
			else if(elec==2){
				rellenar(m1,f1,c1);
				rellenar(m2,c1,c2);
				mostrar(m1,f1,c1);
				System.out.println("*");
				mostrar(m2,c1,c2);
				System.out.println("-");
			}
		}while(elec!=3);
		sc.close();
	}
	private static void rellenar(int[][] m,int f,int c){
		for(int i=0;i<f;i++){
			for(int j=0;j<c;j++){
				System.out.print("Rellena el elemento ("+(i+1)+","+(j+1)+") de la matriz:");
			}
		}
	}	private static void mostrar(int[][] m,int f,int c){
		for(int i=0;i<f;i++){
			for(int j=0;j<c;j++){
				System.out.print(m[i][j]+",");
			}
			System.out.println();
		}
	}
	private static int[][] multiplicar(int [][] m1, int[][] m2, int f1,int c1,int c2){
		int[][] mr = new int[f1][c2];
		for(int i=0;i<f1;i++){
			for(int j=0;j<c2;++j){
				for(int k=0;k<c1;++k){
					mr[i][j]+=m1[i][k]*m2[k][j];
				}

			}
		}
		return mr;
	}
}