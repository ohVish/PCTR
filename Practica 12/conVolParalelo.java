/**
*	Calculo de convoluciones de matrices de forma secuencial.
*	
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*/

import java.util.*;
import java.util.concurrent.*;

public class conVolParalelo implements Runnable{
	private static int[][] m;
	private static int[][] r;
	private static int[][] k;
	private int inicio;
	private int fin;

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

	public conVolParalelo(int inicio,int fin){
		this.inicio=inicio;
		this.fin=fin;
	}

	public void run(){
		for (int i=inicio;i<fin;i++){
			for(int j=0;j<m[0].length;j++){
				nextState(i,j);
			}		
		}
	}

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

	private static void convolucion(int[][]kernel,int nh){
		k=kernel;
		long tiempo = System.nanoTime();
		ExecutorService ex = Executors.newFixedThreadPool(nh);
		int bloque = m.length/nh;
		for(int i=0;i<nh;i++){
			if(i==nh-1){
				ex.execute(new conVolParalelo(i*bloque,m.length));
			}
			else{
				ex.execute(new conVolParalelo(i*bloque,(i+1*bloque)));
			}
		}
		ex.shutdown();
		while(!ex.isTerminated());
		tiempo=System.nanoTime()-tiempo;
		System.out.println("t:"+tiempo/1e09);
	}


	private static void nextState(int a,int b){
		int z=0;
		for(int i=a-1;i<=a+1;i++){
			int l=0;
			for(int j=b-1;j<=b+1;j++){ 
				try{
					r[a][b]=r[a][b]+m[i][j]*k[z][l];
				}catch(IndexOutOfBoundsException e){}
				finally{
					l++;
				}
			}
			z++;
		}
	}
	public static void main(String[] args) {
		m=new int[10000][10000];
		r=new int[10000][10000];
		k=new int[3][3];
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
			convolucion(k_enfoque,Integer.parseInt(args[0]));
			//mostrarMatriz(r);
			break;
			case 2:
			convolucion(k_desenfoque,Integer.parseInt(args[0]));
			//mostrarMatriz(r);
			break;
			case 3:
			convolucion(k_realce,Integer.parseInt(args[0]));
			//mostrarMatriz(r);
			break;
			case 4:
			convolucion(k_detec,Integer.parseInt(args[0]));
			//mostrarMatriz(r);
			break;
			case 5:
			convolucion(k_sobel,Integer.parseInt(args[0]));
			//mostrarMatriz(r);
			break;
			case 6:
			convolucion(k_sharpen,Integer.parseInt(args[0]));
			//mostrarMatriz(r);
			break;

		}

	}
}