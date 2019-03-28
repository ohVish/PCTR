
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servidor bibliotecario donde se almacenan los libros.
 * @author José Miguel Aragón Jurado
 */
public class sLibros extends UnicastRemoteObject implements iLibros{
    private volatile List<Libro> biblioteca;
    private final  ReentrantLock c;
    
    //Constructor de la clase
    public sLibros() throws RemoteException{
        c= new ReentrantLock();
        biblioteca = new ArrayList<Libro>();
        biblioteca.add(new Libro("Piensa en Java", "Bruce Eckel","PRENTICE HALL"));
    }
    /**
     * Inserta un libro en el servidor bibliotecario.
     * @param lib Libro a insertar(previamente inicializado).
     * @throws RemoteException 
     */    
    @Override
    public void insertar(Libro lib) throws RemoteException {
        c.lock();
        try {
            biblioteca.add(lib);
        } finally {
            c.unlock();
        }
    }

    /**
     *  Extrae un libro del servidor bibliotecario a partir de su titulo.
     * @param tit Titulo del libro a extraer.
     * @return Devuelve el libro extraído. Si no existe el libro, devuelve null.
     * @throws RemoteException 
     */   
    @Override
    public Libro extraer(String tit) throws RemoteException {
        int i=0;
        while(i<biblioteca.size() && !tit.equals(biblioteca.get(i).getTitulo())) i++;
        if(i==biblioteca.size()) return null;
        else{
        Libro lib = biblioteca.get(i);
        c.lock();
        try {
            biblioteca.remove(i);
        } finally {
            c.unlock();
        }
        return lib;
        }
        
    }

    /**
     * Consulta un libro a partir de su titulo en el servidor bibliotecario.
     * @param tit Titulo del libro a consultar.
     * @return Devuelve un objeto de la clase Libro. Si no existe el libro, devuelve null.
     * @throws RemoteException 
     */    
    @Override
    public Libro consultar(String tit) throws RemoteException {
        int i=0;
        while(i<biblioteca.size() && !tit.equals(biblioteca.get(i).getTitulo())) {
                i++;
        }
        if(i==biblioteca.size()) return null;
        else return biblioteca.get(i);
    }
    
    public static void main(String[] args) { 
        try{
            LocateRegistry.createRegistry(1099);
            iLibros servidor = new sLibros();
            Naming.rebind("rmi://localhost:1099/biblioteca", servidor);
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(sLibros.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Biblioteca lista.");
        
    }
    
}
