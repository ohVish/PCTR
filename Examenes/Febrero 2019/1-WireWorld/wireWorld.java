
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Programa que modela el juego reticular WireWorld.
 * @author José Miguel Aragón Jurado
 */
public class wireWorld implements Runnable{
    /*Para este problema he considerado:
      0=Vacio.
      1=Frente de electrón.
      2=Cola de electrón.
      3=Conductor.
    Además como la frontera es libre, he utilizado frontera nula.
    */
    private static int[][] world;
    private static int[][] neworld;
    private static int nGen;
    private static CyclicBarrier c;
    private static Scanner sc = new Scanner(System.in);
    private static Random rnd = new Random();
    private int inicio;
    private int fin;
    private boolean manual;
    
    /**
     * Constructor de las hebras
     * @param inicio
     * @param fin
     * @param manual 
     */
    public wireWorld(int inicio,int fin,boolean manual){
        this.inicio=inicio;
        this.fin=fin;
        this.manual=manual;
    }
    
    @Override
    public void run() {
        if(!manual){
            for(int k=0;k<nGen;k++){
                for(int i=inicio;i<fin;i++){
                    for(int j=0;j<world[0].length;j++){
                        nextState(i,j,world[i][j]);
                    }
                }
                try{
                    c.await();
                }catch(InterruptedException | BrokenBarrierException e){}
                for(int i=inicio;i<fin;i++){
                    for(int j=0;j<world[0].length;j++){
                        world[i][j]=neworld[i][j];
                    }
                }
                try{
                    c.await();
                }catch(InterruptedException | BrokenBarrierException e){}

            }
        }
        else{
            for(int i=inicio;i<fin;i++){
                for(int j=0;j<world[0].length;j++){
                    nextState(i,j,world[i][j]);
                }
            }
            try{
                c.await();
            }catch(InterruptedException | BrokenBarrierException e){}
            for(int i=inicio;i<fin;i++){
                for(int j=0;j<world[0].length;j++){
                    world[i][j]=neworld[i][j];
                }
            }
        }
    }
    /**
     * Función utilizada para generar el nuevo estado de una celda siguiendo las reglas propuestas.
     * @param a
     * @param b
     * @param actual 
     */
    private void nextState(int a,int b,int actual){
        int nuevo=0,contfrente=0;
        if(actual!=0){
            if(actual==1)nuevo=2;
            else{
                if(actual==2)nuevo=3;
                else{
                    if(actual==3){
                        for(int i=a-1;i<a+2;i++){
                            for(int j=b-1;j<b+2;j++){
                                try{
                                    if(world[i][j]==1)contfrente++;
                                }catch(Exception e){}
                            }
                        }
                        if(contfrente==1 || contfrente==2) nuevo=1;
                        else nuevo=3; 
                    }
                }
            
            }
        }
        neworld[a][b]=nuevo;
    }
    
    /**
     * Genera el mundo de forma aleatoria.
     */
    private static void generarMundo(){
        for(int i=0;i<world.length;i++){
            for(int j=0;j<world[0].length;j++){
                world[i][j]=rnd.nextInt(4);
            }
        }
    }
    /**
     * Rellena el mundo de forma manual.
     */
    private static void rellenarMundo(){
        for(int i=0;i<world.length;i++){
            for(int j=0;j<world[0].length;j++){
                System.out.println("Introduce la celula ("+(i+1)+":"+(j+1)+").");
                world[i][j]=sc.nextInt();
            }
        }
    }
    /**
     * Método que se encarga del modo manual del programa.
     * @param nTareas
     * @param dim 
     */
    private static void manual(int nTareas,int dim){
        c=new CyclicBarrier(nTareas);
        world=new int[dim][dim];
        neworld=new int[dim][dim];
        rellenarMundo();
        int bloque = dim/nTareas;
        for(int j=0;j<nGen;j++){
            ThreadPoolExecutor ex = new ThreadPoolExecutor(nTareas,nTareas,0L,TimeUnit.DAYS,new LinkedBlockingQueue<Runnable>());
            ex.prestartAllCoreThreads();
            for(int i=0;i<nTareas;i++){
                if(i==nTareas-1){
                    ex.execute(new wireWorld(i*bloque, world.length,true));
                }
                else{
                    ex.execute(new wireWorld(i*bloque,(i+1)*bloque,true));
                }
            }
            ex.shutdown();
            while(!ex.isTerminated());
            System.out.println("Generacion numero "+(j+1)+":");
            mostrarMundo();
            System.out.println();
        }
    }
    /**
     * Muestra el mundo actual.
     */
    private static void mostrarMundo(){
        for(int i=0;i<world.length;i++){
            for(int j=0;j<world[0].length;j++){
                System.out.print(world[i][j]+",");
            }
            System.out.println();
        }
    }
    /**
     * Método que se encarga del modo automático del programa.
     * @param nTareas
     * @param dim 
     */
    private static void auto(int nTareas,int dim){
        c=new CyclicBarrier(nTareas);
        world=new int[dim][dim];
        neworld=new int[dim][dim];
        generarMundo();
        ThreadPoolExecutor ex = new ThreadPoolExecutor(nTareas,nTareas,0L,TimeUnit.DAYS,new LinkedBlockingQueue<Runnable>());
        ex.prestartAllCoreThreads();
        int bloque = dim/nTareas;
        for(int i=0;i<nTareas;i++){
            if(i==nTareas-1){
                ex.execute(new wireWorld(i*bloque, world.length,false));
            }
            else{
                ex.execute(new wireWorld(i*bloque,(i+1)*bloque,false));
            }
        }
        ex.shutdown();
        while(!ex.isTerminated());
    }
    
    public static void main(String[] args){
        int nTareas,dim,elec;
        do{
            System.out.println("Introduce la dimension de la reticula: (d>0)");
            dim=sc.nextInt();
        }while(dim<=0);
        do{
            System.out.println("Introduce el numero de tareas: (n>=0)");
            nTareas=sc.nextInt();
        }while(nTareas<=0);
        do{
            System.out.println("Introduce el numero de iteraciones:");
            nGen=sc.nextInt();
        }while(nGen<=0);
        System.out.println("Selecciona una de las opciones:");
        System.out.println("\t1. Manual.");
        System.out.println("\t2. Automatico.");
        elec=sc.nextInt();
        switch(elec){
            case 1:
                manual(nTareas,dim);
                break;
                
            case 2:
                auto(nTareas,dim);
                break;
        }
    }
    
}
