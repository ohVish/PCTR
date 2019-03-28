/**
* Programa que utiliza la clase Cajero.java
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Random;
public class Usa_Cajero{
	public static void main(String[] Args)throws Exception{
		Random rnd = new Random();
		Cuenta_Banca cuenta=new Cuenta_Banca(12345678,1000);
		int n=rnd.nextInt(10000);
		double cantidad=100;
		Runnable[] tareas = new Runnable[n];
		Thread[] hilos = new Thread[n];
		if(n%2!=0) n--;
		for(int i=0;i<n;i++){
			if(i%2==0){
				tareas[i] = new Cajero(true,cuenta,cantidad);
			}
			else{
				tareas[i]=new Cajero(false,cuenta,cantidad);
				
			}
				hilos[i]= new Thread(tareas[i]);
				hilos[i].start();
		}
		for(int i=0;i<n;i++){
			hilos[i].join();
		}
		System.out.println(cuenta.Saldo());
	}
}