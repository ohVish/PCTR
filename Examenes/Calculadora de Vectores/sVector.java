
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


/**
 *
 * @author jmaj
 */
public class sVector extends UnicastRemoteObject implements iVector{
    
    public sVector() throws RemoteException{
    }
    @Override
    public float prodEscalar(float[] u, float[] v) throws RemoteException {
        float resultado=0;
        for(int i=0;i<u.length;i++){
            resultado+=u[i]*v[i];
        }
        return resultado;
    }

    @Override
    public float prodporescalar(float[] u, float k) throws RemoteException {
        float resultado=0;
        for(int i=0;i<u.length;i++){
            resultado+=u[i]*k;
        }
        return resultado;
    }

    @Override
    public float[] vecSuma(float[] u, float[] v) throws RemoteException {
        float[] resultado = new float[u.length];
        for(int i=0;i<u.length;i++){
            resultado[i]=u[i]+v[i];
        }
        return resultado;
    }

    @Override
    public float vectModulo(float[] u) throws RemoteException {
        float resultado=0;
        for(int i=0;i<u.length;i++){
            resultado+=Math.pow(u[i],2);
        }
        return((float)Math.sqrt(resultado));
    }
    
    public static void main(String[] args) {
        try{
            LocateRegistry.createRegistry(2010);
            iVector servidor = new sVector();
            Naming.rebind("rmi://localhost:2010/Vector", servidor);
            System.out.println("Servidor listo para calcular vectores ;-)");
        }catch(RemoteException | MalformedURLException e){
            System.out.println("Servidor con problemas");
        }
      
    }
        
    
}
