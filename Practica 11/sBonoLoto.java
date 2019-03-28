
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;


/**
 *  Servidor que simula el sistema de la bonoloto.
 * @author José Miguel Aragón Jurado
 * @version 1.0
 */
public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto{
    private int[] numero;
    
    //Constructor de la clase
    public sBonoLoto() throws RemoteException{
        numero = new int[6];
        generarnumero();
    }
    
    /**
     * Comprueba si la solución coincide con el número generado.
     * @param sol (De seis dígitos)
     * @return Devuelve verdadero si coincide con el número generado por la bonoloto
     * @throws RemoteException 
     */
    @Override
    public boolean enviarSolucion(int [] sol)throws RemoteException{
        boolean valor = Arrays.equals(numero,sol);
         if(valor){
             System.out.println("Ha habido un ganador.");
             System.out.println("Reiniciando servidor...");
             generarnumero();
            try {
                Thread.sleep(1000); //Simulamos tiempo de reinicio.
            } catch (InterruptedException ex) {}
             System.out.println("Servidor activo de nuevo.");
         }
         return valor;
    }
    
    private void generarnumero(){
        for(int i=0;i<numero.length;i++){
            numero[i]=(int)Math.floor(1+Math.random()*49);
        }
        System.out.print("Este es el número ganador: ");
        for(int i=0;i<numero.length-1;i++){
            System.out.print(numero[i]+"-");
        }
        System.out.println(numero[numero.length-1]);
    }
    /**
     * Método main
     * @param args Parámetros de la línea de comandos.
     */
    public static void main(String[] args) {
        try {
            iBonoLoto servidor = new sBonoLoto();
            try {
                LocateRegistry.createRegistry(1099);
                Naming.rebind("rmi://localhost:1099/Bonoloto", servidor);
            } catch (MalformedURLException ex) {  }
        } catch (RemoteException ex) {        }
        System.out.println("\nServidor iniciado sin problemas.");
    }
    
}
