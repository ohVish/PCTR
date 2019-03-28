/**
* Clase que modela a un paciente.
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/
import java.util.Scanner;

public class Paciente{
	private String nombre;
	private int dni;
	private String direccion;
	private int telefono;
	private String seguro;
	private String diagnostico;
	private String sexo;
	private int edad;
	private int nss;

/**
*Constructor de la clase Paciente.
*@param nom
*@param ndni
*@param direc
*@param tel
*@param seg
*@param diag
*@param gen
*@param age
*@param newnss
*/
	public Paciente(){}
	public Paciente(String nom,int ndni, String direc, int tel,String seg,String diag,String gen,int age, int newnss){
		this.nombre=nom;
		this.dni=ndni;
		this.direccion=direc;
		this.telefono=tel;
		this.seguro=seg;
		this.diagnostico=diag;
		this.sexo=gen;
		this.edad=age;
		this.nss=newnss;
	}

	public String getNombre(){
		return nombre;
	}

	public int getDNI(){
		return dni;
	}
	public String getDireccion(){
		return direccion;
	}

	public int getTelefono(){
		return telefono;
	}
	public String getSeguro(){
		return seguro;
	}
	public String getDiagnostico(){
		return diagnostico;
	}
	public String getSexo(){
		return sexo;
	}
	public int  getEdad(){
		return edad;
	}
	public int getNSS(){
		return nss;
	}
	public void setNombre(String nom){
		this.nombre=nom;
	}
	public void setDNI(int ndni){
		this.dni=ndni;
	}
	public void setDireccion(String direc){
		this.direccion=direc;
	}
	public void setTelefono(int tel){
		if(tel>-1){
			this.telefono=tel;
		}
	}
	public void setSeguro(String seg){
		this.seguro=seg;
	}

	public void setDiagnostico(String diag){
		this.diagnostico=diag;
	}

	public void setSexo(String gen){
		this.sexo=gen;
	}
	public void setEdad(int age){
		if(age>-1){
			this.edad=age;
		}
	}
	public void setNSS(int newnss){
		if(newnss>-1){
			this.nss=newnss;
		}
	}

	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		Paciente pepito = new Paciente("Pepito",71623467,"C/Fuenlabrada",722980989,"Mafre","Gripe","Hombre",19,123456789);
		System.out.println(pepito.getNombre());
		System.out.println(pepito.getDNI());
		System.out.println(pepito.getDireccion());
		System.out.println(pepito.getTelefono());
		System.out.println(pepito.getSeguro());
		pepito.setNombre(sc.nextLine());
		System.out.println(pepito.getNombre());

	}
}