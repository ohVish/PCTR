/**
*	Monitor para resolver el problema de las impresoras.
*
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*
*/
import java.util.Arrays;

public class monitorImpresion{
	private boolean[] libre;
	private int impresoras;

	public monitorImpresion(){
		libre = new boolean[3];
		Arrays.fill(libre,true);
		impresoras=3;
	}

	public synchronized int pedirImpresora(){
		while(impresoras==0){
			try{
				wait();
			}catch(InterruptedException e){}
		}
		int i=0;
		while(!libre[i]) i++;
		libre[i]=false;
		System.out.println("Impresora "+i+" concedida.");
		impresoras--;
		return i;
	}

	public void liberarImpresora(int i){
		libre[i]=true;
		impresoras++;
		System.out.println("Impresora "+i+" liberada.");
		notifyAll();
	}
}