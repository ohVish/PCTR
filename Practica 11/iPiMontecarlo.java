
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz que muestra los métodos remotos del servidor que puede llamar el cliente.
 * @author José Miguel Aragón Jurado
 */
public interface iPiMontecarlo extends Remote{
    /**
     * Reinicia la aproximación de servidor al instante inicial.
     * @throws RemoteException 
     */
    
    public void reset() throws RemoteException;
    
    /**
     * Aumenta el numero de puntos a calcular.
     * @param n
     * @return Devuelve el valor aproximado de la integral en ese instante.
     * @throws RemoteException 
     */
    
    public void sumarPuntos(int n) throws RemoteException;
    
    /**
     * Consulta los valores actuales del servidor.
     * @return Devuelve una cadena de caracteres, donde muestra los valores.
     * @throws RemoteException 
     */
    public String consultarvalores() throws RemoteException;
    
}
