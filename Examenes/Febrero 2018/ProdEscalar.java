
package prodescalar;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmaj
 */
public class ProdEscalar implements Callable<Float>{
    static ArrayList<Future<Float>> lista = new ArrayList();
    static float[] vector1= new float[10000];
    static float[] vector2 = new float[10000];
    float resultado;
    int inicio;
    int fin;
    
    public ProdEscalar(int inicio,int fin){
        this.inicio=inicio;
        this.fin=fin;
        resultado=0;
    }
    private static void rellenarvectores(){
        for(int i=0;i<vector1.length;i++){
            vector1[i]=(float)Math.random()*10;
            vector2[i]=(float)Math.random()*10;
        }
    }
    private static void mostrarvectores(){
        for(int i=0;i<vector1.length;i++){
            System.out.print(vector1[i]+",");
        }
        System.out.println("");
        for(int i=0;i<vector2.length;i++){
            System.out.print(vector2[i]+",");
        }
        System.out.println("");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        rellenarvectores();
        mostrarvectores();
        int nTareas=4;
        int bloque=(int)vector1.length/nTareas;
        ExecutorService ex = Executors.newFixedThreadPool(nTareas);
        for(int i=0;i<nTareas;i++){
            if(i==nTareas-1){
                lista.add(ex.submit(new ProdEscalar(i*bloque, vector2.length)));         
            }
            else{
                lista.add(ex.submit(new ProdEscalar(i*bloque, (i+1)*bloque)));
            }
        }
        ex.shutdown();
        while(!ex.isTerminated());
        float suma=0;
        for(int i=0;i<lista.size();i++){
            try {
                suma+=lista.get(i).get();
            } catch (InterruptedException | ExecutionException ex1) {
                Logger.getLogger(ProdEscalar.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        System.out.println("Resultado: "+suma);
        
    }

    @Override
    public Float call() throws Exception {
        for(int i=inicio;i<fin;i++){
            resultado+=vector1[i]*vector2[i];
        }
        return resultado;
    }
    
}
