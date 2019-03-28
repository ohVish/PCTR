/**
*	Programa que simula el problema de los filósofos comensales.
*
*	@author	José Miguel Aragón Jurado
*	@version 1.0.
*/

import java.util.concurrent.*;

public class usaFiloApiAn implements Runnable{
	private filoApiAN mesa;  
	private int indice;

	public usaFiloApiAn(filoApiAN f, int id){
		this.mesa=f;
		this.indice=id;

	}

	public void run(){
		//while(true){
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				System.out.println("ERROR");
			}
			mesa.cogerTenedores(this.indice);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				System.out.println("ERROR");
			}
			mesa.soltarTenedores(this.indice);
		//}
	}

	public static void main(String[] args) {
		int nfilosofos=5;
		filoApiAN mesa = new filoApiAN();
		ExecutorService ex = Executors.newFixedThreadPool(nfilosofos);
		for(int i=0;i<nfilosofos;i++){
			ex.submit(new usaFiloApiAn(mesa,i));
		}
		ex.shutdown();
		while(!ex.isTerminated());
	}

}