/**
*	Programa que utiliza y emplea la clase Elipse
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.*;

public class usaElipse{
	private static void puntoelipse(Scanner sc, Elipse elipse){
		System.out.println("Introduce las coordenadas del punto a comprobar.");
		System.out.println("Coordenada x: ");
		double x = sc.nextDouble();
		System.out.println("Coordenada y:");
		double y = sc.nextDouble();
		if(elipse.esUnPunto(x,y)==true){
			System.out.println("\n El punto se encuentra en la elipse.");
		}
		else{
			System.out.println("\n El punto no se encuentra en la elipse.");
		}
	}

	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		Elipse elipse = new Elipse();
		try{
			System.out.println("Introduce la longitud del semieje mayor:");
			elipse.seta(sc.nextDouble());
			System.out.println("Introduce la longitud del semieje menor:");
			elipse.setb(sc.nextDouble());
			System.out.println("Introduce el foco 1:");
			elipse.seto1(sc.nextDouble());
			System.out.println("Introduce el foco 2:");
			elipse.seto2(sc.nextDouble());
			int elec=0;
			do{
				System.out.println("\nElige una de las operaciones a realizar con la elipse:");
				System.out.println("\t1.Comprobar si un punto pertenece a la elipse.");
				System.out.println("\t2.Superficie de la elipse.");
				System.out.println("\t3.Perimetro de la elipse.");
				System.out.println("\t4.Salir.");
				elec=sc.nextInt();
				switch(elec){
					case 1:
						puntoelipse(sc,elipse);
						System.out.println();
						break;
					case 2:
						System.out.println("La superficie de la elipse es: "+elipse.superficie());
						System.out.println();
						break;
					case 3:
						System.out.println("El perimetro de la elipse es: "+elipse.perimetro());
						System.out.println();
						break;
				}

			}while(elec!=4);
		}catch(InputMismatchException e){
			System.out.println("Escribe un número, por favor");
		}
		finally{
			sc.close();
		}
	}
}