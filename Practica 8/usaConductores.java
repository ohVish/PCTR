/**
* 	Programa que utiliza la clas Conductores.
*	@author José Miguel Aragón Jurado
*	@version 1.0
*/

import java.util.concurrent.*;

public class usaConductores implements Runnable{
	private int funcion;
	private static  Conductores dgt = new Conductores();
	private Conductor c;
	private String dni;
	public usaConductores(int j,Conductor con,String nif){
		this.funcion=j;
		this.c=con;
		this.dni=nif;
	}
	public void run(){
		switch(funcion){
			case 0:
				dgt.nuevo(c);
				break;
			case 1:
				dgt.eliminar(dni);
				break;
			case 2:
				dgt.buscar(dni);
				break;
		}
	}

	public static void main(String[] args) {
		ExecutorService ex = Executors.newCachedThreadPool();
		int nhilos = 5;
		Conductor c = new Conductor("Pepito","123","Jerez","Mafre","12345678",78907687);
		for(int i=0;i<nhilos;i++){
			ex.submit(new usaConductores((i+1)%3,c,c.getDNI()));
		}
		ex.shutdown();
		while(!ex.isTerminated());
	}
}