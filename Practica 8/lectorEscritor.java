/**
*	Monitor para el problema de los lectores y escritores
*
*	@author José Miguel Aragón Jurado
*	@version 1.0
*
*/


public class lectorEscritor{
	private int n;
	private boolean escribiendo;

	public lectorEscritor(){
		this.n=0;
		this.escribiendo=false;
	}

	public synchronized void inicia_lectura(){
		while(escribiendo){
			try{
				wait();
			}catch(InterruptedException e){System.out.println("ERROR");}
		}
		n++;
		System.out.println("Iniciando lectura.");
		notifyAll();
	}



	public synchronized void fin_lectura(){
		n--;
		System.out.println("Finalizando lectura.");
		if(n==0){
			notifyAll();
		}
	}

	public synchronized void inicia_escritura(){
		while(n!=0 || escribiendo){
			try{
				wait();
			}catch(InterruptedException e){System.out.println("ERROR");}
		}
		System.out.println("Iniciando escritura.");
		escribiendo=true;
	}

	public synchronized void fin_escritura(){
		System.out.println("Finalizando escritura.");
		escribiendo=false;
		notifyAll();
	}
}