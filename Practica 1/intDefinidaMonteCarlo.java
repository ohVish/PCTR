/**
* Programa que realiza el método de Monte Carlo.
* 
*@author José Miguel Aragón Jurado
*@version 1.0
*/
import java.util.Scanner;
public class intDefinidaMonteCarlo{

	public static void main(String[] Args){

		Scanner sc = new Scanner(System.in);
		System.out.println("Elige en cual de las funciones desea calcular la integral:");
		System.out.println("1. f(x)=sin(x)");
		System.out.println("2. g(x)=x");
		int elec=sc.nextInt();
		System.out.println("Introduce el numero de puntos  generar");
		int n= sc.nextInt();
		switch(elec){
			case 1:
				int cont1=0;
				for(int i=0;i<n;i++){
					double x1=Math.random();
					double y1=Math.random();
					if(y1<=Math.sin(x1)){
						cont1++;
					}
				}
				System.out.println("Integral aprox: "+(double)cont1/n);
				break;
			case 2:
				int cont2=0;
				for(int i=0;i<n;i++){
					double x2=Math.random();
					double y2=Math.random();
					if(y2<=x2){
						cont2++;
					}
				}
				System.out.println("Integral aprox: "+(double)cont2/n);

		}
	}
}