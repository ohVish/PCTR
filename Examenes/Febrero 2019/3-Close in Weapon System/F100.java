
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Clase que modela el problema de las fragatas.
 * @author José Miguel Aragón Jurado
 */
public class F100 {
    public static void main(String[] args){
        CIWS armamento = new CIWS();
        int n=16; //Numero de estaciones
        ExecutorService ex = Executors.newFixedThreadPool(n);
        for(int i=0;i<n;i++){
            ex.submit(new combatStations(i+1,armamento));
        }
        ex.shutdown();
        while(!ex.isTerminated());
        
    }
}
