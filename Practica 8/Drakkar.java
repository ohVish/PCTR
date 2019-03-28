/**
*	Monitor que encapsula los datos del problema de los vikingos drakkar
*
*	@author	José Miguel Aragón Jurado
*	@version 1.0
*
*/

public class Drakkar{
	private int m=5;
	private int anguilas;

	//Constructor de la clase
	public Drakkar(){
		anguilas=m;
	}

	public synchronized void comer(){
		try{
			while(anguilas==0){
				notifyAll();
				wait();
			}
			anguilas--;
			System.out.println("Vikingo servido.Quedan "+anguilas+" anguilas");
		}catch(InterruptedException e){ System.out.println("ERROR");}
	}

	public synchronized void rellenar(){
		try{
			while(anguilas>0){
				wait();
			}
			anguilas=m;
			System.out.println("Lista otra racion.");
			notifyAll();
		}catch(InterruptedException e){ System.out.println("Error");}

	}
}