/**Ejemplo de servidor de sockets multihilo con pool.
 *@author José Miguel Aragón Jurado
 *@version 1.0
*/


import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
public class atomicServer implements Runnable
{
    Socket enchufe;
    int nombre;
    private static AtomicLong n = new AtomicLong(0);
    public atomicServer(Socket s,int numero){
       enchufe = s;
       nombre = numero;
    }

   public void run()
   {
    try{
        System.out.println(n.addAndGet(1));
    } catch(Exception e) {System.out.println("Error...");}
    }//run

    public static void main (String[] args)
    {
        int i=0;
        int puerto = 1099;
        ExecutorService ex = Executors.newCachedThreadPool(); 
        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);

            while (true){
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                ex.submit(new atomicServer(cable,i+1));
                i++;
      		}//while
          } catch (Exception e)
          {System.out.println("Error en sockets...");}
}//main

}//atomicServer

