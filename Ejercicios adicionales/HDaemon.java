/**
*
*
*
*
*/

public class HDaemon extends Thread{
	public void run(){
		while(true){
			try{sleep(100);}catch(InterruptedException e){}
			System.out.println("Me he reido.");
		}
	}

	public static void main(String[] args) throws InterruptedException{
		HDaemon hilo = new HDaemon();
		hilo.setDaemon(true);
		hilo.start();
		//hilo..join();
	}
}