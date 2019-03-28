/**
*
*
*
*/

import java.util.*;

public class conVolSecuencial{
	private static int[][] m;
	private static int[][] resultado;

	private static int[][] k_enfoque={{0,-1,0},
	{-1,5,-1},
	{0,-1,0}};

	private static int[][] k_desenfoque={{1,1,1},
	{1,1,1},
	{1,1,1}};
	private static int[][] k_realce={{0,0,1},
	{-1,1,0},
	{0,0,0}};
	private static int[][] k_detec={{0,1,0},
	{1,-4,1},
	{0,1,0}};
	private static int[][] k_sobel={{-1,0,1},
	{-2,0,2},
	{-1,0,1}};
	private static int[][] k_sharpen={{1,-2,1},
	{-2,5,-2},
	{1,-2,1}};

	private static void rellenarMatriz(){
		Random r = new Random();
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				m[i][j]=r.nextInt(41)-20;
			}
		}
	}

	private static void mostrarMatriz(int[][] m){
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				System.out.print(m[i][j]+",");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void convolucion(int[][] m,int[][] r,int[][] kernel){
		long tiempo = System.nanoTime();
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				nextState(i,j,m,r,kernel);
			}
		}
		tiempo=System.nanoTime()-tiempo;
		System.out.println("t:"+tiempo/1e09);
	}


	private static void nextState(int a,int b,int[][] m,int[][] r,int[][] kernel){
		int z=0;
		for(int i=a-1;i<=a+1;i++){
			int k=0;
			for(int j=b-1;j<=b+1;j++){ 
				try{
					r[a][b]=r[a][b]+m[i][j]*kernel[z][k];
				}catch(IndexOutOfBoundsException e){}
				finally{
					k++;
				}
			}
			z++;
		}
	}
	public static void main(String[] args) {
		m=new int[10000][10000];
		resultado=new int[10000][10000];
		rellenarMatriz();
		//mostrarMatriz(m);
		Scanner sc = new Scanner(System.in);
		System.out.println("Elige que kernel desea utilizar:");
		System.out.println("1. Enfoque.");
		System.out.println("2. Desenfoque.");
		System.out.println("3. Realce de bordes.");
		System.out.println("4. Deteccion de bordes.");
		System.out.println("5. Sobel.");
		System.out.println("6. Sharpen.");
		int elec=sc.nextInt();
		switch(elec){
			case 1:
			convolucion(m,resultado,k_enfoque);
			//mostrarMatriz(resultado);
			break;
			case 2:
			convolucion(m,resultado,k_desenfoque);
			//mostrarMatriz(resultado);
			break;
			case 3:
			convolucion(m,resultado,k_realce);
			//mostrarMatriz(resultado);
			break;
			case 4:
			convolucion(m,resultado,k_detec);
			//mostrarMatriz(resultado);
			break;
			case 5:
			convolucion(m,resultado,k_sobel);
			//mostrarMatriz(resultado);
			break;
			case 6:
			convolucion(m,resultado,k_sharpen);
			//mostrarMatriz(resultado);
			break;

		}

	}
}