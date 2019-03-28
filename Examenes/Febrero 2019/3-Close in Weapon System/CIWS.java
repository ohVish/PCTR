
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Monitor que se encarga de gestionar el recurso del armamento.
 * @author José Miguel Aragón Jurado
 */
public class CIWS {
    private ReentrantLock c;
    private Condition lleno;
    private boolean[] libre;
    private int armamento;
    /**
     * Constructor de la clase.
     */
    public CIWS(){
        libre = new boolean[4];
        c=new ReentrantLock();
        armamento = 4;
        lleno = c.newCondition();
        for(int i=0;i<armamento;i++){
            libre[i]=true;
        }
    }
    /**
     * Método que permite a una estación de trabajo coger el armamento.
     * @param id
     * @return 
     */
    public int cogerArmamento(int id){
        c.lock();
        try{
            while(armamento==0){
                try {
                    lleno.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(CIWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            armamento--;
            int i=0;
            while(!libre[i]){
                i=(i+1)%4;
            }
            libre[i]=false;
            System.out.println("Estacion "+id+" cogiendo armamento "+i+".");
            return i;
        }finally{
            c.unlock();
        }
    }
    /**
     * Método que se encarga de soltar el armamento cogido.
     * @param i
     * @param id 
     */
    public void soltarArmamento(int i, int id){
        c.lock();
        try{
            System.out.println("Estacion "+id+" soltando armamento "+i+".");
            armamento++;
            libre[i]=true;
            lleno.signal();
        }finally{
            c.unlock();
        }
    }
    
}
