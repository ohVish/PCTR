
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jmaj
 */
public class cVector {
    private static Scanner sc = new Scanner(System.in);
    private static float [] u;
    private static float [] v;
    
    private static void rellenarvector(float[] v){
        for(int i=0;i<v.length;i++){
            System.out.println("Elemento->"+(i+1));
            v[i]=sc.nextFloat();
        }
    }
    private static void mostrarvector(float[] v){
        for(int i=0;i<v.length;i++){
            System.out.println(v[i]+",");
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        try {
            iVector servidor=(iVector)Naming.lookup("rmi://localhost:2010/Vector");
            System.out.println("Conexión con el servidor establecida...");
            System.out.println("Introduce el tamaño de ambos vectores:");
            int n=sc.nextInt();
            v=new float[n];
            u=new float[n];
            int elec;
            do{
                System.out.println("Elija la operacion a realizar:");
                System.out.println("\t1. Producto escalar entre dos vectores.");
                System.out.println("\t2. Producto de un escalar por un vector.");
                System.out.println("\t3. Suma de vectores.");
                System.out.println("\t4. Modulo de vectores.");
                elec=sc.nextInt();
                switch(elec){
                    case 1:
                        System.out.println("Vector 1:");
                        rellenarvector(u);
                        System.out.println("Vector 2");
                        rellenarvector(v);
                        System.out.println("Resultado= "+servidor.prodEscalar(u, v));
                        break;
                    case 2:
                        System.out.println("Vector:");
                        rellenarvector(u);
                        System.out.println("Escalar:");
                        float num = sc.nextFloat();
                        System.out.println("Resultado= "+servidor.prodporescalar(u, num));
                        break;
                    case 3:
                        System.out.println("Vector 1:");
                        rellenarvector(u);
                        System.out.println("Vector 2");
                        rellenarvector(v);
                        System.out.println("Resultado:");
                        mostrarvector(servidor.vecSuma(u, v));
                        break;   
                    case 4:
                        System.out.println("Vector:");
                        rellenarvector(u);
                        System.out.println("Modulo= "+servidor.vectModulo(u));
                        break;
                }
            }while(elec!=5);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(cVector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
