
import java.io.Serializable;

/**
 * Clase que simula el objeto libro del mundo real.
 * @author José Miguel Aragón Jurado
 */
public class Libro implements Serializable{
    private String titulo;
    private String autor;
    private String editorial;
    
    public Libro(){}
    
    public Libro(String t,String a,String e){
        this.titulo=t;
        this.autor=a;
        this.editorial=e;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public String getAutor(){
        return this.autor;
    }
    
    public String getEditorial(){
        return this.editorial;
    }
    
    public void setTitulo(String t){
        this.titulo=t;
    }
    
    public void setAutor(String a){
        this.autor=a;
    }
    
    public void setEditorial(String e){
        this.editorial=e;
    }
}
