/**
*Programa en el que varios hilos escriben en un fichero en exclusión mutua.
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/
import java.util.concurrent.*;
import java.util.Scanner;
import java.io.*;

public class ficheroSeguro implements Runnable{
	private static RandomAccessFile f;
	private String mensaje;
	private static Object cerrojo= new Object();
	public ficheroSeguro(String a){
		mensaje = a;
	}
	public void run(){
		synchronized(cerrojo){
			try{
				f=new RandomAccessFile("mensajes.txt","rw");
				f.seek(f.length());
				f.writeChars(this.mensaje);
				f.close();
			}catch(IOException e){
				System.out.println("Error EOF");
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el numero de mensajes que quieres escribir:");
		int n=sc.nextInt();
		String mensajes[]= new String[n];
		sc.nextLine();
		for(int i=0;i<n;i++){
			System.out.println("Introduce el mensaje");
			mensajes[i]=sc.nextLine();
		}
		sc.close();
		ExecutorService ex = Executors.newFixedThreadPool(n);
		for(int i=0;i<n;i++){
			ex.submit(new ficheroSeguro(mensajes[i]));
		}
		ex.shutdown();
		while(!ex.isTerminated());
	}
}