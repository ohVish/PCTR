
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 * Cliente que se conecta al servidor de la bonoloto.
 * 
 * @author José Miguel Aragón Jurado
 * @version 1.0.
 */
public class cBonoLoto {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try {
            iBonoLoto servidor = (iBonoLoto) Naming.lookup("rmi://localhost:1099/Bonoloto");
            int elec;
            do{
                System.out.println("Bienvenido al servicio de la Bonoloto:");
                System.out.println("1.Introducir numero.");
                System.out.println("2.Salir.");
                elec = sc.nextInt();
                if(elec==1){
                    int[] numero = new int[6];
                    for(int i=0;i<numero.length;i++){
                        numero[i]=sc.nextInt();
                    }
                    if(servidor.enviarSolucion(numero)){
                        System.out.println("Su numero ha sido premiado,felicidades.");
                    }
                    else{
                        System.out.println("Su numero no ha sido premiado, inténtelo de nuevo.");
                    }
                }
            }while(elec!=2);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(cBonoLoto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
