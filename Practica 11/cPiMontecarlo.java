
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente que se conecta al servidor de Montecarlo.
 * @author José Miguel Aragón Jurado
 */

public class cPiMontecarlo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            iPiMontecarlo serv = (iPiMontecarlo)Naming.lookup("rmi://localhost:1099/pimontecarlo");
            int elec;
            do{
                System.out.println("Bienvenido al servidor Montecarlo para el calculo del numero PI.");
                System.out.println("Valores Actuales:");
                System.out.println(serv.consultarvalores());
                System.out.println("1. Aumentar precision.");
                System.out.println("2. Reiniciar servidor.");
                System.out.println("3. Actualizar valores.");
                System.out.println("4. Salir.");
                elec = sc.nextInt();
                if(elec==1){
                    System.out.println("Introduce el numero de puntos en el que quiere aumentar la precision:");
                    int p = sc.nextInt();
                    System.out.println("Realizando el calculo...");
                    serv.sumarPuntos(p);
                    System.out.println("Calculo realizado.");
                }
                else if(elec==2){
                    System.out.println("Reiniciando servidor...");
                    serv.reset();
                    System.out.println("Servidor reiniciado.");
                }
            }while(elec!=4);
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(cPiMontecarlo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
