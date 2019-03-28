/**
*Clase que utiliza hilos de la clase Hilo.java
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/
import java.util.Scanner;

public class Usa_Hilo{
	public static void main(String[] args)throws InterruptedException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el numero de iteraciones que desea realizar:");
		long  n = sc.nextInt();
		Hilo.setN(n);
		Hilo h1 = new Hilo(true);
		h1.start();
		Hilo h2 = new Hilo(false);
		h2.start();
		h1.join();
		h2.join();
		System.out.println("El numero resultante es: "+Hilo.getX());

	}
}