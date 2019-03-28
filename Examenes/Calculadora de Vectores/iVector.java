
import java.rmi.*;


/**
 *
 * @author jmaj
 */
public interface iVector extends Remote{
    public float prodEscalar(float [] u,float [] v)throws RemoteException;
    
    public float prodporescalar(float []u,float k)throws RemoteException;
    
    public float[] vecSuma(float [] u,float [] v)throws RemoteException;
    
    public float vectModulo(float [] u)throws RemoteException;
}
