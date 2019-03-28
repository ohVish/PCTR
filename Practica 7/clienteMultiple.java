/*Ejemplo de cliente de sockets
*@Antonio Tomeu
*@version 1.0
*/


import java.net.*;
import java.io.*;

public class clienteMultiple
{
    private static int n=10;
    public static void main (String[] args)
    {
        int i = (int)(Math.random()*10);
        int puerto = 2001;
        try{
        	for(int n=1;n<=200;n++){
	        	double tiempo = System.nanoTime();
	            for(int j=0;j<n;++j){
	                    System.out.println("Realizando conexion...");
	                    Socket cable = new Socket("localhost", 2001);
	                    System.out.println("Realizada conexion a "+cable);
	                    PrintWriter salida= new PrintWriter(new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
	                    salida.println(i);
	                    salida.flush();
	                    System.out.println("Cerrando conexion...");
	                    cable.close();
	              }
	              tiempo = (System.nanoTime()-tiempo)/1.0e6;
	              Escribir.grafica("clienteMultipleconpool.txt",n,tiempo);
	              //System.out.println("Para "+n+"peticiones:\nT= "+tiempo/1.0e6);
	        }
        }catch (Exception e){
                System.out.println("Error en sockets...");
            }
    }//main
}//Cliente_Hilos

