
import java.util.concurrent.CyclicBarrier;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jmaj
 */
public class aIncendioForestal implements Runnable{  
    private static int[][] bosque;
    private static int [][] bosquenuevo;
    private static CyclicBarrier c;
    private static Random rnd = new Random();
    private int inicio;
    private int fin;
    private int nFuegos;
    private int nArboles;
    private static int m;
    private static int n;
    private static int g;
            
    @Override
    public void run() {
        System.out.println(inicio+":"+fin);
        // generarBosque();
        /*try{
            c.await();
        }catch(InterruptedException | BrokenBarrierException e){}*/
        for(int i=0;i<g;i++){
            nuevaGeneracion();
        }
    }
    public aIncendioForestal(int inicio,int fin){
        this.inicio=inicio;
        this.fin=fin;
    }
    private static void generarBosqueS(){
        int i;
        int j;
        for(int k=0;k<m;k++){
            do{
               i = rnd.nextInt(bosque.length);
               j= rnd.nextInt(bosque[0].length);
            }while(bosque[i][j]!=0);
            bosque[i][j]=1;
        }
        for(int k=0;k<n;k++){
            do{
               i = rnd.nextInt(bosque.length);
               j= rnd.nextInt(bosque[0].length);
            }while(bosque[i][j]!=0);
            bosque[i][j]=2;
        }  
    }
    public static void main(String[] args) {
         m = Integer.parseInt(args[0]);
         n = Integer.parseInt(args[1]);
         g = Integer.parseInt(args[2]);
        if(m>400 || n>m || m+n>400){
            System.out.println("No cumple con las indicaciones del problema");
        }
        else{
            double t1=paralelo(1);
            double tn=paralelo(Runtime.getRuntime().availableProcessors());
            double tmitad=paralelo(((Runtime.getRuntime().availableProcessors()/2)));
            System.out.println("t1= "+t1/1e3);
            System.out.println("tn= "+tn/1e3);
            System.out.println("tmitad= "+tmitad/1e3);
            System.out.println("SpeedUp(n)= "+t1/tn);
            System.out.println("SpeedUp(n/2)= "+t1/tmitad);
        }
        
    }
    private static double paralelo(int nTareas){
        bosque=new int[20][20];
        bosquenuevo=new int[20][20];
        generarBosqueS();
        c = new CyclicBarrier(nTareas);
        ThreadPoolExecutor ex = new ThreadPoolExecutor(nTareas, nTareas, 0L, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>());
        int bloque = (int)bosque.length/nTareas;
        ex.prestartAllCoreThreads();
        double tiempo = System.currentTimeMillis();
        for(int i=0;i<nTareas;i++){
            if(i==nTareas-1){
                ex.execute(new aIncendioForestal(i*bloque, bosque.length));
            }
            else{
                ex.execute(new aIncendioForestal(i*bloque, (i+1)*bloque));
            }
        }
        ex.shutdown();
        while(!ex.isTerminated());
        tiempo=System.currentTimeMillis()-tiempo;
        return tiempo;
        
    }
    
    private void generarBosque(){
        int i;
        int j;
        for(int k=0;k<nArboles;k++){
            do{
               i = rnd.nextInt((fin-inicio))+inicio;
               j= rnd.nextInt(bosque[0].length);
            }while(bosque[i][j]!=0);
            bosque[i][j]=1;
        }
        for(int k=0;k<nFuegos;k++){
            do{
               i = rnd.nextInt((fin-inicio))+inicio;
               j= rnd.nextInt(bosque[0].length);
            }while(bosque[i][j]!=0);
            bosque[i][j]=2;
        }
    }
    private int nextState(int a,int b){
        int actual=bosque[a][b];
        int nuevo=actual,contfuego=0,prob;
        switch(actual){
            case 0:
                prob=rnd.nextInt(1000);
                if(prob==1)nuevo=1;
                break;
            
           case 1:
               for(int i=a-1;i<a+2;i++){
                   for(int j=b-1;j<b+2;j++){
                       try{
                           if(bosque[i][j]==2)contfuego++;
                       }catch(IndexOutOfBoundsException e){}
                   }
               }
               prob = rnd.nextInt(100000);
               if(contfuego>0 || prob==1)nuevo=2;
               break;
               
           case 2:
               nuevo=0;
               break;
        }
        return nuevo;
    }
    private void nuevaGeneracion(){
        for(int i=inicio;i<fin;i++){
            for(int j=0;j<bosque[0].length;j++){
                bosquenuevo[i][j]=nextState(i,j);
            }
        }
        try{
            c.await();
        }catch(InterruptedException | BrokenBarrierException e){}
        for(int i=inicio;i<fin;i++){
            for(int j=0;j<bosque[0].length;j++){
                bosque[i][j]=bosquenuevo[i][j];
            }
        }
    }
    
}
