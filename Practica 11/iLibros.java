
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz sobre los métodos remotos del servidor de libros.
 * @author José Miguel Aragón Jurado
 */
public interface iLibros extends Remote{
    
    /**
     * Inserta un libro en el servidor bibliotecario.
     * @param lib Libro a insertar(previamente inicializado).
     * @throws RemoteException 
     */
    public void insertar(Libro lib) throws RemoteException;
    
    /**
     *  Extrae un libro del servidor bibliotecario a partir de su titulo.
     * @param tit Titulo del libro a extraer.
     * @return Devuelve el libro extraído.
     * @throws RemoteException 
     */
    public Libro extraer(String tit) throws RemoteException;
    
    /**
     * Consulta un libro a partir de su titulo en el servidor bibliotecario.
     * @param tit Titulo del libro a consultar.
     * @return Devuelve una cadena de caracteres con todos los datos del libro.
     * @throws RemoteException 
     */
    public Libro consultar(String tit) throws RemoteException;
}
