/**
*	API que simula los métodos para una base de datos de la DGT
*
*	@author	José Miguel Aragón Jurado
*	@version 1.0
*/
import java.util.Map;
import java.util.HashMap;

public class Conductores{
	private volatile Map<String,Conductor> conductores;
	public Conductores(){
		this.conductores= new HashMap<String,Conductor>();
	}
	/**
	*	Añade un nuevo conductor al mapa de conductores.
	*@param Conductor c
	*@return
	*/
	public synchronized void nuevo(Conductor c){
		conductores.put(c.getDNI(),c);
		System.out.println("Agregado con exito");
	}

	/**
	*	Elimina un  conductor del mapa de conductores.
	*@param String dni
	*@return
	*/
	public synchronized void eliminar(String dni){
		if(conductores.containsKey(dni)){
			conductores.remove(dni);
			System.out.println("Eliminado con exito.");
		}
		else{
			System.out.println("No existe ese DNI");
		}
	}
	/**
	*	Muestra los datos de un conductor
	*@param String dni
	*@return
	*/
	public synchronized void buscar(String dni){
		if(conductores.containsKey(dni)){
			Conductor c = conductores.get(dni);
			System.out.println(c.toString());

		}
		else{
			System.out.println("No existe ese DNI");
		}
	}

}