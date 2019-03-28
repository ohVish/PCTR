/**
*Programa que realiza operaciones aritmeticas con números complejos utilizando la clase Complejos.
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Scanner;
import java.util.*;

public class usaComplejos{
	public static void sumaComplejos(Scanner sc){
		Complejos a = new Complejos();
		Complejos b = new Complejos();
		Complejos c = new Complejos();
		System.out.println("Introduzca parte real del primer complejo:");
		a.setReal(sc.nextDouble());
		System.out.println("Introduzca parte imaginaria del primer complejo:");
		a.setImaginario(sc.nextDouble());
		System.out.println("Introduzca parte real del segundo complejo:");
		b.setReal(sc.nextDouble());
		System.out.println("Introduzca parte imaginaria del segundo complejo:");
		b.setImaginario(sc.nextDouble());
		c=a.sumaComplejos(b);
		System.out.println("SUMA.\nParte real: "+c.getReal()+"\nParte imaginaria: "+c.getImaginario());
	}

	public static void restaComplejos(Scanner sc){
		Complejos a = new Complejos();
		Complejos b = new Complejos();
		Complejos c = new Complejos();
		System.out.println("Introduzca parte real del primer complejo:");
		a.setReal(sc.nextDouble());
		System.out.println("Introduzca parte imaginaria del primer complejo:");
		a.setImaginario(sc.nextDouble());
		System.out.println("Introduzca parte real del segundo complejo:");
		b.setReal(sc.nextDouble());
		System.out.println("Introduzca parte imaginaria del segundo complejo:");
		b.setImaginario(sc.nextDouble());
		c=a.restaComplejos(b);
		System.out.println("RESTA.\nParte real: "+c.getReal()+"\nParte imaginaria: "+c.getImaginario());
	}
	public static void moduloComplejos(Scanner sc){
		Complejos a = new Complejos();
		System.out.println("Introduzca parte real del complejo:");
		a.setReal(sc.nextDouble());
		System.out.println("Introduzca parte imaginaria del complejo:");
		a.setImaginario(sc.nextDouble());
		System.out.println("Modulo: "+a.moduloComplejos());
	}
	public static void productoComplejos(Scanner sc){
		Complejos a = new Complejos();
		Complejos b = new Complejos();
		Complejos c = new Complejos();
		System.out.println("Introduzca parte real del primer complejo:");
		a.setReal(sc.nextDouble());
		System.out.println("Introduzca parte imaginaria del primer complejo:");
		a.setImaginario(sc.nextDouble());
		System.out.println("Introduzca parte real del segundo complejo:");
		b.setReal(sc.nextDouble());
		System.out.println("Introduzca parte imaginaria del segundo complejo:");
		b.setImaginario(sc.nextDouble());
		c=a.multiplicarComplejos(b);
		System.out.println("MULTIPLICACION.\nParte real: "+c.getReal()+"\nParte imaginaria: "+c.getImaginario());
	}
	public static void divisionComplejos(Scanner sc){
		try{
			Complejos a = new Complejos();
			Complejos b = new Complejos();
			Complejos c = new Complejos();
			System.out.println("Introduzca parte real del primer complejo:");
			a.setReal(sc.nextDouble());
			System.out.println("Introduzca parte imaginaria del primer complejo:");
			a.setImaginario(sc.nextDouble());
			System.out.println("Introduzca parte real del segundo complejo:");
			b.setReal(sc.nextDouble());
			System.out.println("Introduzca parte imaginaria del segundo complejo:");
			b.setImaginario(sc.nextDouble());
			c=a.divisionComplejos(b);
			System.out.println("DIVISION.\nParte real: "+c.getReal()+"\nParte imaginaria: "+c.getImaginario());
		}catch(NullPointerException e){
			System.out.println("Error: Division por cero");
		}
	}
	public static void main(String[] args){
		int elec=0;
		Scanner sc =  new Scanner(System.in);
		try{
			do{
				System.out.print("\n\n\n");
				System.out.println("Bienvenido al menu de complejos. Seleccione una operacion a realizar");
				System.out.println("\t1. Suma");
				System.out.println("\t2. Resta");
				System.out.println("\t3. Modulo");
				System.out.println("\t4. Producto");
				System.out.println("\t5. Cociente");
				System.out.println("\t6. Salir");
				elec=sc.nextInt();
				switch(elec){
					case 1:
						sumaComplejos(sc);
						break;
					case 2:
						restaComplejos(sc);
						break;
					case 3:
						moduloComplejos(sc);
						break;
					case 4:
						productoComplejos(sc);
						break;
					case 5:
						divisionComplejos(sc);
						break;
				}
			}while(elec!=6);
		}catch(InputMismatchException e){
			System.out.println("Introduce un numero, por favor");
		}
		finally{
			sc.close();
		}
	}
}