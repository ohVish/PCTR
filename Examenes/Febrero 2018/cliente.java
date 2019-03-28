

public class cliente implements Runnable{
	private String nombre;
	private lineasCajas tienda;
	private int id;

	public cliente(String nombre, lineasCajas tienda){
		this.nombre=nombre;
		this.tienda=tienda;
	}

	@Override
	public void run(){
		id=tienda.entrarCaja();
		try{Thread.sleep(2000L);}catch(InterruptedException e){}
		tienda.salirCaja(id);
	}
}