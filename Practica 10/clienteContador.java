

import java.net.*;
import java.io.*;

public class clienteContador
{
	public static void main (String[] args)
	{

		int puerto = 1099;
		for(int i=0;i<10000;i++){
			try{
				Socket cliente = new Socket("localhost",puerto);

			}catch (Exception e){
				System.out.println("Error en sockets...");
			}
		}
	}

}//Cliente_Hilos

