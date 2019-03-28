
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente que se conecta al servidor bibliotecario.
 * @author José Miguel Aragón Jurado
 */
public class cLibros {
    public static void main(String[] args) {
        try {
            iLibros biblioteca = (iLibros)Naming.lookup("rmi://localhost:1099/biblioteca");
            int elec;
            Scanner sc = new Scanner(System.in);
            do{
                Libro lib;
                System.out.println("Bienvenido a la biblioteca.");
                System.out.println("1. Introduce un libro.");
                System.out.println("2. Extrae un libro.");
                System.out.println("3. Consulta un libro.");
                System.out.println("4. Salir");
                elec = sc.nextInt();
                sc.nextLine();
                switch(elec){
                    case 1:
                        lib = new Libro();
                        System.out.print("Introduzca el titulo: ");
                        lib.setTitulo(sc.nextLine());
                        System.out.print("Introduzca el autor: ");
                        lib.setAutor(sc.nextLine());
                        System.out.print("Introduzca la editorial: ");
                        lib.setEditorial(sc.nextLine());
                        System.out.println("Insertando...");
                        biblioteca.insertar(lib);
                        System.out.println("Insercion realizada con exito.");
                        break;
                    case 2:
                        System.out.print("Introduzca el titulo del libro a extraer: ");
                        lib =biblioteca.extraer(sc.nextLine());
                        System.out.println("Extrayendo...");
                        if(lib!=null) System.out.println("Libro extraido con exito.");
                        else System.out.println("El libro no se encuentra en la biblioteca.");
                        break;
                    case 3:
                        System.out.print("Introduzca el titulo del libro a consultar: ");
                        lib =biblioteca.consultar(sc.nextLine());
                        System.out.println("Consultando...");
                        if(lib!=null) {
                            System.out.println("Titulo: "+lib.getTitulo());
                            System.out.println("Autor: "+ lib.getAutor());
                            System.out.println("Editorial: "+ lib.getEditorial());
                        }
                        else System.out.println("El libro no se encuentra en la biblioteca.");                        
                        break;
                }
            }while(elec!=4);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(cLibros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
