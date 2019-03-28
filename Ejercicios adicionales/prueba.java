
import java.util.concurrent.*;

public class prueba implements Runnable{
	private int id;
	private static Object cerrojo = new Object();

	public prueba(int i){
		this.id=i;
	}

	public void run(){
		if(id==1){
			synchronized(cerrojo){
				Thread h = new Thread(new prueba(2));
				h.start();
				try{
					h.join();
				}catch(Exception e){}
				System.out.println("Salio el hilo "+h);
				
			}
		}
		else{
			synchronized(cerrojo){
				System.out.println("el hilo 2 ta here");
			}

		}

	}

	public static void main(String[] args) throws Exception{
		Thread h2 = new Thread(new prueba(1));
		h2.start();
		h2.join();
	}	
}