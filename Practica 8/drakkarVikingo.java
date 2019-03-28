/**
*	
*	Programa que simula la situación de los vikingos drakkar
*
*	@author José Miguel Aragón Jurado
*	@version 1.0
*/

import java.util.concurrent.*;


public class drakkarVikingo implements  Runnable{
	private boolean cocinero;
	private  Drakkar marmita;


	//Constructor de la clase
	public drakkarVikingo(boolean valor,Drakkar m){
		this.cocinero = valor;
		this.marmita=m;
	}


	public void run(){
		if(this.cocinero==true){
			while(true){
				marmita.rellenar();
			}
		}
		else{
			while(true){
				marmita.comer();
			}
		}
	}

	public static void main(String[] args) {
		int nvikingos = 7;
		Drakkar marmita = new Drakkar();
		ExecutorService ex = Executors.newFixedThreadPool(nvikingos);
		ex.submit(new drakkarVikingo(true,marmita));
		for(int i=0;i<nvikingos-1;i++){
			ex.submit(new drakkarVikingo(false,marmita));
		}
	}
}