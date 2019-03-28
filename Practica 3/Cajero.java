/**
*Clase que simula un cajero automático.
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

public class Cajero implements Runnable{
	private boolean estado;
	private double cantidad;
	private Cuenta_Banca cuenta=new Cuenta_Banca();
	public Cajero(boolean a, Cuenta_Banca dato,double dinero){
		this.estado=a;
		this.cuenta=dato;
		this.cantidad=dinero;
	}
	public void run(){
		if(this.estado){
			this.cuenta.Deposito(this.cantidad);
		}
		else{
			this.cuenta.Reintegro(this.cantidad);
		}
	}
}