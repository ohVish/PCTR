
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Servidor que se encarga de calcular la aproximación de Pi mediante el método de Montecarlo.
 * @author José Miguel Aragón Jurado
 */

public class sPiMontecarlo extends UnicastRemoteObject implements iPiMontecarlo{
    private static volatile int puntos;
    private volatile int suma;
    private static double  integral;
    private ReentrantLock cerrojo;
    public sPiMontecarlo() throws RemoteException{
        puntos=0;
        integral=0;
        suma=0;
        cerrojo=new ReentrantLock();
    }

    /**
     * Reinicia la aproximación de servidor al instante inicial.
     * @throws RemoteException 
     */
    @Override
    public void reset() throws RemoteException {
        puntos=0;
        integral=0;
        suma=0;
    }

    /**
     * Aumenta el numero de puntos a calcular.
     * @param n
     * @return Devuelve el valor aproximado de la integral en ese instante.
     * @throws RemoteException 
     */
    
    @Override
    public void sumarPuntos(int n) throws RemoteException {
        cerrojo.lock();
        try {
            puntos+=n;
        } finally {
            cerrojo.unlock();
        }
        for(int i=0;i<n;i++){
            double x = Math.random();
            double y = Math.random();
            if(Math.pow(x, 2)+Math.pow(y, 2)<=1){
                cerrojo.lock();
                try {
                    suma++;
                } finally {
                    cerrojo.unlock();
                }
            }
        }
        cerrojo.lock();
        try{
            integral=(double)4*suma/puntos;
        }finally{
            cerrojo.unlock();
        }
    }
    
     /**
     * Consulta los valores actuales del servidor.
     * @return Devuelve una cadena de caracteres, donde muestra los valores.
     * @throws RemoteException 
     */
    @Override
    public String consultarvalores() throws RemoteException{
        return("-> Integral aprox. "+integral+"\n"+"-> Numero de puntos."+puntos);
    }
    
    public static void main(String[] args) {
        try{
            LocateRegistry.createRegistry(1099);
            iPiMontecarlo servidor = new sPiMontecarlo();
            Naming.rebind("rmi://localhost:1099/pimontecarlo", servidor);
            } catch (MalformedURLException | RemoteException ex) {
                Logger.getLogger(sPiMontecarlo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Servidor activo sin problemas");
        System.out.println("Valores iniciales:");
        System.out.println("-> Integral aprox. "+integral);
        System.out.println("-> Numero de puntos."+puntos);
    }
    
}
