/**
*	Clase que simula los datos de un conductor en la DGT.
*
*	@author José Miguel Aragón Jurado
*	@version 1.0
*/

public class Conductor{
	private String nombre,dni,direccion,comp,identificador;
	private int telefono;

	//	Constructor
	public Conductor(String n, String d, String dir, String cmp, String iden, int tel){
		this.nombre=n;
		this.dni=d;
		this.direccion=dir;
		this.comp=cmp;
		this.identificador=iden;
		this.telefono=tel;
	}

	public Conductor(){}
	//	Observadores y modificadores.
	public String getNombre(){	return this.nombre;}
	public String getDNI(){ return this.dni;}
	public String getDireccion(){ return this.direccion;}
	public String getCompania(){ return this.comp;}
	public String getIdentificador(){return this.identificador;}
	public int getTelefono(){return this.telefono;}

	public void setNombre(String name){this.nombre=name;}
	public void setDNI(String d){ this.dni=d;}
	public void setDireccion(String dir){this.direccion=dir;}
	public void setCompania(String cmp){this.comp=cmp;}
	public void setIdentificador(String iden){this.identificador=iden;}
	public void setTelefono(int tel){this.telefono=tel;}


	//	Mostrar datos del conductor.
	public String toString(){
		return("Nombre: "+this.nombre+"\nDNI: "+this.dni+"\nDireccion: "+this.direccion+"\nCompania: "+this.comp+"\nIdentificador: "+this.identificador+"\nTelefono: "+this.telefono);
	}

}