
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Programa que se encarga de calcular la cantidad de números perfectos en un rango dado.
 * @author José Miguel Aragón Jurado
 */
public class perfectNumbers implements Callable<Integer>{
    private int inicio;
    private int fin;
    private int cont;
    private static ArrayList<Future<Integer>> lista = new ArrayList<Future<Integer>>();
    /**
     * Constructor de las tareas Callable.
     * @param inicio
     * @param fin 
     */
    public perfectNumbers(int inicio,int fin){
        this.inicio=inicio;
        this.fin=fin;
        this.cont=0;
    }
    
    /**
     * Función privada que devuelve verdadero si el número pasado como parámetro es perfecto.
     * @param num
     * @return 
     */
    private boolean isPerfect(int num){
        boolean val=false;
        if(num!=0){
            int cont=0;
            for(int i=num-1;i>0;i--){
                if(num%i==0)cont+=i;
            }
        if(cont==num)val=true;
        }
        return val;
    }
    public static void main(String[] args){
        if(args.length<2){
            System.out.println("Introduce el numero hasta el que desea buscar numeros perfectos y de segundo parametro el numero de tareas.");
        }
        else{
            int nTareas=Integer.parseInt(args[1]);
            int numeros=Integer.parseInt(args[0]);
            if(numeros<0){
                System.out.println("Introduce el numero hasta el que desea buscar numeros perfectos y de segundo parametro el numero de tareas.");
            }
            else{
                if(nTareas<=0){
                    System.out.println("Introduce el numero de tareas como segundo parametro.(n>0)");
                }
                else{
                    paralelo(nTareas,numeros);
                }
        }

        }
    }
    /**
     * Función que se encarga de gestionar las tareas y calcular el problema.
     * @param nTareas
     * @param n 
     */
    public static void paralelo(int nTareas,int n){
        int contglobal=0;
        ExecutorService ex = Executors.newFixedThreadPool(nTareas);
        int bloque = n/nTareas;
        double time=System.currentTimeMillis();
        for(int i=0;i<nTareas;i++){
            if(i==nTareas-1){
                lista.add(ex.submit(new perfectNumbers(i*bloque,n+1)));
            }
            else{
                lista.add(ex.submit(new perfectNumbers(i*bloque,(i+1)*bloque)));
            }
        }
        ex.shutdown();
        for(int i=0;i<lista.size();i++){
            try {
                contglobal+=lista.get(i).get();
            } catch (InterruptedException | ExecutionException ex1) {
                Logger.getLogger(perfectNumbers.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        time=System.currentTimeMillis()-time;
        System.out.println("El numero de numeros perfectos hallados es: "+contglobal);
        System.out.println("El tiempo necesitado es de "+time+" ms.");
        
    }
    @Override
    public Integer call() throws Exception {
        for(int i=inicio;i<fin;i++){
            if(isPerfect(i))cont++;
        }
        return cont;
    }
}
