/**Ejemplo de servidor de sockets multihilo con pool.
 *@author José Miguel Aragón Jurado
 *@version 1.0
*/


import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class ServidorHilosinPool implements Runnable
{
    Socket enchufe;
    int nombre;
    public ServidorHilosinPool(Socket s,int numero){
    	enchufe = s;
    	nombre = numero;
   	}

    public void run()
    {
    try{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
        String datos = entrada.readLine();
        int j;
        int i = Integer.valueOf(datos).intValue();
        for(j=1; j<=20; j++){
        System.out.println("El hilo "+this.nombre+" escribiendo el dato "+i);
        Thread.sleep(1000);}
        enchufe.close();
        System.out.println("El hilo "+this.nombre+"cierra su conexion...");
    } catch(Exception e) {System.out.println("Error...");}
    }//run

public static void main (String[] args)
{
    int i=0;
    int puerto = 2001;
    List<Runnable> tareas= new ArrayList<Runnable>();

        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);

            while (true){
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                tareas.add(i,new ServidorHilosinPool(cable,i+1));
                new Thread(tareas.get(i)).start();
                i++;
      		}//while
      } catch (Exception e)
        {System.out.println("Error en sockets...");}
}//main

}//Servidor_Hilos

