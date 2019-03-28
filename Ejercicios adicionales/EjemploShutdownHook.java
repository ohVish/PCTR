/**
*	Ejemplo de uso del método addShutdownHook que permite agregar hilos que se ejecuten al finalizar el programa.(Runnable mediante expresion lambda)
*	@author José Miguel Aragón Jurado
*	@version 1.0
*/

public class EjemploShutdownHook{

	public static void main(String[] args) {
		int pepito=5;
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
			@Override
			public void run(){
				System.out.println("Me ejecuto al salir :) "+pepito);
			}
		})); //Añadimos un hilo para que se ejecute,una vez termine el programa.
		System.out.println("Terminando el programa...");
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){}
		System.exit(0);
	}
}