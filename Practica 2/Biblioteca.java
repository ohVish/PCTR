/**
* Programa que realiza una lista de pacientes, para simular una consulta.
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Scanner;
import java.util.HashMap;

public class Biblioteca{
	public static void insertarpaciente(HashMap<Integer,Paciente> consulta,Scanner sc){
		Paciente pnew= new Paciente();
		System.out.println("Introduce el nombre:");
		pnew.setNombre(sc.nextLine());
		System.out.println("Introduce la Direccion:");
		pnew.setDireccion(sc.nextLine());
		System.out.println("Introduce el seguro:");
		pnew.setSeguro(sc.nextLine());
		System.out.println("Introduce el telefono: ");
		pnew.setTelefono(sc.nextInt());
		System.out.println("Introduce el DNI:");
		pnew.setDNI(sc.nextInt());
		consulta.put(pnew.getDNI(),pnew);
	}
	public static void consultarpaciente(HashMap<Integer,Paciente> consulta,Scanner sc){
		Paciente p= new Paciente();
		System.out.println("Introduce el DNI de la persona que deseas consultar: ");
		int dni=sc.nextInt();
		sc.nextLine();
		try{
			p=consulta.get(dni);
			System.out.println("Su nombre es: "+p.getNombre());
			System.out.println("Su DNI es: "+p.getDNI());
			System.out.println("Su direccion es: "+p.getDireccion());
			System.out.println("Su seguro es: "+p.getSeguro());
			System.out.println("Su telefono es: "+p.getTelefono());
		}catch(NullPointerException e){
			System.out.println("Ese DNI no existe....");
		}
		finally{
			System.out.println("\n\nIntroduce una tecla para continuar...");
			sc.nextLine();
		}
	}
	public static void borrarpaciente(HashMap<Integer,Paciente> consulta,Scanner sc){
		System.out.println("Introduce el DNI de la persona que deseas consultar: ");
		int dni=sc.nextInt();
		sc.nextLine();
		if(consulta.remove(dni)==null){
			System.out.println("Ese DNI no existe....");
		}
		else{
			System.out.println("||Eliminacion correcta||");
		}
		System.out.println("\n\nIntroduce una tecla para continuar...");
		sc.nextLine();
	}
	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		HashMap <Integer,Paciente> consulta = new HashMap<Integer,Paciente>();
		int elec=0;
		do{
			for (int i = 0; i < 50; ++i) System.out.println();
			System.out.println("Elija una opcion a realizar con la consulta:");
			System.out.println("\t 1. Insertar paciente.");
			System.out.println("\t 2. Eliminar paciente.");
			System.out.println("\t 3. Consultar paciente.");
			System.out.println("\t 4. Salir.");
			elec = sc.nextInt();
			switch(elec){
				case 1:
					insertarpaciente(consulta,sc);
					break;
				case 2:
					borrarpaciente(consulta,sc);
					break;
				case 3:	
					consultarpaciente(consulta,sc);
					break;
			}
		}while(elec!=4);
		sc.close();
	}

}