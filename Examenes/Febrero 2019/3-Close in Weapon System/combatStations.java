
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que se encarga de modelar las estaciones de combate como tareas runnable
 * @author José Miguel Aragón Jurado
 */
public class combatStations implements Runnable{
    private int id;
    private CIWS armamento;
    /**
     * Constructor de la clase
     * @param id
     * @param armamento 
     */
    public combatStations(int id,CIWS armamento){
        this.id=id;
        this.armamento=armamento;
    }
    @Override
    public void run() {
        int i=armamento.cogerArmamento(id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(combatStations.class.getName()).log(Level.SEVERE, null, ex);
        }
        armamento.soltarArmamento(i,id);
    }
    
}
