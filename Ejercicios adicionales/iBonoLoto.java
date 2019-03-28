
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz del servidor de la Bonoloto
 * @author José Miguel Aragón Jurado
 */

public interface iBonoLoto extends Remote{
    /** 
     * @param sol (De seis dígitos);
     * @return Devuelve verdadero si coincide con el número generado por la bonoloto
     * @throws RemoteException 
     */
    public boolean enviarSolucion(int [] sol) throws RemoteException;
    
}
