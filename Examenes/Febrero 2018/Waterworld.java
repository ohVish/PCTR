import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmaj
 */
public class Waterworld implements Runnable{
    private static int nFilas = 1000;
    private static int nCol = 2000;
    private static int[][] world;
    private static int[][] neworld;
    private int inicio;
    private int fin;
    private int nCel;
    private static int nIter;
    private static Random rnd = new Random();
    private static Scanner sc = new Scanner(System.in);
    private static CyclicBarrier c;
    private static boolean op=true;
    
    public Waterworld(int inicio,int fin,int nCel){
        this.inicio=inicio;
        this.fin=fin;
        this.nCel=nCel;
    }
    public static void main(String[] args) {
        menu();
    }
    private static void menu(){
        System.out.println("Bienvenido a WaterWorld:");
        System.out.println("\t1.Automatico.");
        System.out.println("\t2.Manual.");
        int elec = sc.nextInt();
        switch(elec){
            case 1:
                long t1=auto(1);
                long tn=auto(Runtime.getRuntime().availableProcessors());
                long t2n=auto(2*Runtime.getRuntime().availableProcessors());
                System.out.println("t1: "+(double)t1/1e3);
                System.out.println("t2: "+(double)tn/1e3);
                System.out.println("t3: "+(double)t2n/1e3);
                System.out.println("Speed up(Tareas=Procesadores): "+(double)t1/tn);
                System.out.println("Speed up(Tareas=2*Procesadores)"+(double)t1/t2n);
                System.out.println("Tiempo total: "+(t1+t2n+t1)/1e3);
                break;
            case 2:
                manual();
                break;              
        }
    }
    private static void manual(){
        op=false;
        System.out.println("Introduce el número de filas del planeta:");
        nFilas=sc.nextInt();
        System.out.println("Introduce el número de columnas del planeta:");
        nCol=sc.nextInt();
        world=new int[nFilas][nCol];
        neworld=new int[nFilas][nCol];
        System.out.println("Introduce el número de iteraciones:");
        nIter=sc.nextInt();
        System.out.println("Comience a rellenar al planeta");
        rellenar();
        mostrar();
        int nTareas = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor ex = new ThreadPoolExecutor(nTareas, nTareas,0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        c=new CyclicBarrier(nTareas);
        ex.prestartAllCoreThreads();
        int bloque = (int)nFilas/nTareas;
        long tiempo = System.currentTimeMillis();
        for(int i=0;i<nTareas;i++){
            if(i==nTareas-1){
                ex.execute(new Waterworld(i*bloque,world.length,0));
            }
            else{
                ex.execute(new Waterworld(i*bloque, (i+1)*bloque,0));
            }
        }
        ex.shutdown();
        while(!ex.isTerminated());
        mostrar();
    }
    
    private  static void mostrar(){
        for(int i=0;i<nFilas;i++){
            for(int j=0;j<nCol;j++){
                System.out.print(world[i][j]+",");
            }
            System.out.println(" ");
        }
    }
    
    private static void rellenar(){
        for(int i=0;i<nFilas;i++){
            for(int j=0;j<nCol;j++){
                System.out.println(i+":"+j);
                world[i][j]=sc.nextInt();
            }
        }
    }
    
    private static long auto(int nTareas){
        world=new int[nFilas][nCol];
        neworld=new int[nFilas][nCol];
        nIter=1000;
        ThreadPoolExecutor ex = new ThreadPoolExecutor(nTareas, nTareas,0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        c=new CyclicBarrier(nTareas);
        ex.prestartAllCoreThreads();
        int bloque = (int)nFilas/nTareas;
        int tCel=nFilas*nCol;
        long tiempo = System.currentTimeMillis();
        for(int i=0;i<nTareas;i++){
            if(i==nTareas-1){
                ex.execute(new Waterworld(i*bloque,world.length,(world.length-i*bloque)*nCol));
            }
            else{
                ex.execute(new Waterworld(i*bloque, (i+1)*bloque,bloque*nCol));
            }
        }
        ex.shutdown();
        while(!ex.isTerminated());
        tiempo=System.currentTimeMillis()-tiempo;
        return tiempo;
        
    }

    @Override
    public void run() {
       if(op)generarPlaneta();
        for(int i=0;i<nIter;i++){
            nextGen();
            try {
                c.await();
                        } catch (InterruptedException | BrokenBarrierException ex) {
                Logger.getLogger(Waterworld.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void generarPlaneta(){
        int i,j;
        for(int k=0;k<nCel*0.5;k++){
            do{
                i=rnd.nextInt(fin-inicio)+inicio;
                if(i<inicio)i+=inicio;
                j=rnd.nextInt(world[0].length);
            }while(world[i][j]!=0);
            world[i][j]=1;
        }
        
        for(int k=0;k<nCel*0.25;k++){
            do{
                i=rnd.nextInt(fin-inicio)+inicio;
                j=rnd.nextInt(world[0].length);
            }while(world[i][j]!=0);
            world[i][j]=-1;
        }
    }
    
    private void nextGen(){
        for(int i=inicio;i<fin;i++){
            for(int j=0;j<world[0].length;j++){
              nextState(i,j);  
            }
        }
        try{
            c.await();
        }catch(InterruptedException | BrokenBarrierException e){
            System.out.println("Error en la barrera.");
        }
        for(int i=inicio;i<fin;i++){
            for(int j=0;j<world[0].length;j++){
              world[i][j]=neworld[i][j];
            }
        }
    }
    private void nextState(int a, int b){
        int actual=world[a][b];
        int controrcuales=0;
        int procrear=0;
        int procread=0;
        int contdragones=0;
        if(actual==0){
            for(int i=a-1;i<a+2;i++){
                int inew=i;
                if(i<0)inew=fin-1;
                if(i==fin)inew=0;
                for(int j=b-1;j<b+2;j++){
                    int jnew=j;
                    if(j==world[0].length)jnew=0;
                    if(j<0)jnew=fin-1;
                    if(world[inew][jnew]<0)contdragones++;
                    if(world[inew][jnew]>0 )controrcuales++;
                    if(world[inew][jnew]>1)procrear++;
                    if(world[inew][jnew]<-3)procread++;
                }
            }
            if(contdragones>3 && procread>2)neworld[a][b]=-1;
            if(controrcuales>3 && procrear>2)neworld[a][b]=1;
        }
        if(actual==10){
            neworld[a][b]=0;
        }else{
            if(actual>0){
                for(int i=a-1;i<a+2;i++){
                    int inew =i;
                    if(i<0)inew=fin-1;
                    if(i==fin)inew=0;
                    for(int j=b-1;j<b+2;j++){
                        int jnew=j;
                        if(j==world[0].length)jnew=0;
                        if(j<0)jnew=fin-1;
                        if(world[inew][jnew]<0)contdragones++;
                        if(world[inew][jnew]>0)controrcuales++;
                }  
            }
            controrcuales--; //Como él es un rorcual le restamos su valor que ha sido contado
            if(contdragones>5 || controrcuales==8)neworld[a][b]=0;
            else neworld[a][b]=actual+1;
            }
        }
        if(actual==-20){
            neworld[a][b]=0;
        }
        else{
            if(actual<0){
                for(int i=a-1;i<a+2;i++){
                    int inew=i;
                    if(i<0)inew=fin-1;
                    if(i==fin)inew=0;
                    for(int j=b-1;j<b+2;j++){
                        int jnew=j;
                        if(j==world[0].length)jnew=0;
                        if(j<0)jnew=fin-1;
                        if(world[inew][jnew]<0)contdragones++;
                        if(world[inew][jnew]>0)controrcuales++;
                  }  
                }
            }
            contdragones--; //Como él es un dragon le restamos su valor que ha sido contado
            if(contdragones>5 && controrcuales==0)neworld[a][b]=0;
            else{
                int prob  = rnd.nextInt(100);
                if(prob<30)neworld[a][b]=0;
                else neworld[a][b]=actual-1;
            }
        }  
    }
    
    
    
}
