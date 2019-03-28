/**
* Programa que realiza  el método de Newton-Raphson
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Scanner;

public class NewtonRaphson{
	 double f(double x){
		return(Math.cos(x)-Math.pow(x,3));
	}
	 double fdev(double x){
		return(-Math.sin(x)-3*Math.pow(x,2));
	}
	 double g(double x){
		return(Math.pow(x,2)-5);
	}
	 double gdev(double x){
		return(2*x);
	}

	static public void main(String[] Args){
		NewtonRaphson funciones = new NewtonRaphson();
		Scanner teclado = new Scanner(System.in);
		System.out.println("Elige la funcion a la que deseas aplicar el metodo:");
		System.out.println("1. cos(x)-x^3");
		System.out.println("2. x^2-5");
		int selec = teclado.nextInt();
		switch(selec){
			case 1:
				int ndef;
				double x0;
				System.out.println("Introduce el numero de iteraciones:");
				ndef =  teclado.nextInt();
				do{
					System.out.println("Introduce aproximacion entre [0,1]:");
					x0 = teclado.nextDouble();
				}while(x0<0 || x0>1);
				for(int i=0;i<ndef;i++){
					if(funciones.fdev(x0)!=0){
						x0=x0-funciones.f(x0)/funciones.fdev(x0);
						System.out.println("Iteracion: "+(i+1)+", Aprox: "+x0);
					}
				}
				System.out.println("Resultado final: "+x0);
				break;
			case 2:
				int ndeg;
				double x1;
				System.out.println("Introduce el numero de iteraciones:");
				ndeg =  teclado.nextInt();
				do{
					System.out.println("Introduce aproximacion entre [2,3]:");
					x1 = teclado.nextDouble();
				}while(x1<2 || x1>3);
				for(int i=0;i<ndeg;i++){
					if(funciones.gdev(x1)!=0){
						x1=x1-funciones.g(x1)/funciones.gdev(x1);
						System.out.println("Iteracion: "+(i+1)+", Aprox: "+x1);
					}
				}
				System.out.println("Resultado final: "+x1);
				break;			

		}

	}
}