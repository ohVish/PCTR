
import java.util.Scanner;
import java.util.Random;
public class UsaprodMatConcurrente{
	private static int f1;


	public static void main(String[] Args)throws InterruptedException{
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		System.out.println("Introduce las filas de la matriz 1:");
		prodMatConcurrente.f1 = sc.nextInt();
		System.out.println("Introduce las columnas de la matriz 1:");
		prodMatConcurrente.c1 = sc.nextInt();
		System.out.println("Introduce las columnas de la matriz 2:");
		prodMatConcurrente.c2 = sc.nextInt();
		f1=prodMatConcurrente.f1;
		int c1=prodMatConcurrente.c1;
		int c2=prodMatConcurrente.c2;
		prodMatConcurrente.m1 = new int[f1][c1];
		prodMatConcurrente.m2 = new int[c1][c2];
		prodMatConcurrente.mr = new int[f1][c2];
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
						prodMatConcurrente.m1[i][j]=rnd.nextInt(10);
					}
				}
				for(int i=0;i<c1;i++){
					for(int j=0;j<c2;j++){
						prodMatConcurrente.m2[i][j]=rnd.nextInt(10);
					}
				}				
				//mostrar(prodMatConcurrente.m1,f1,c1);
				//System.out.println("*");
				//mostrar(prodMatConcurrente.m2,c1,c2);
				//System.out.println("-");
				double tiempo=System.nanoTime();
				ejecutar();
				//mostrar(prodMatConcurrente.mr,f1,c2);
				tiempo=System.nanoTime()-tiempo;
				System.out.println("T="+tiempo/1000000);

			}
			else if(elec==2){
				rellenar(prodMatConcurrente.m1,f1,c1);
				rellenar(prodMatConcurrente.m2,c1,c2);
				mostrar(prodMatConcurrente.m1,f1,c1);
				System.out.println("*");
				mostrar(prodMatConcurrente.m2,c1,c2);
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
	private static void ejecutar()throws InterruptedException{
		prodMatConcurrente[] hilos = new prodMatConcurrente[f1];
		for(int i=0;i<f1;++i){
			hilos[i]=new prodMatConcurrente(i);
			hilos[i].start();
		}
		for(int i=0;i<f1;++i){
			hilos[i].join();
		}
	}
}