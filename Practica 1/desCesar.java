/**
* Programa que realiza el método de Monte Carlo.
* 
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Scanner;

public class desCesar{
	protected static String descesar(String cad, int n){
		String cifrado = new String();
		for(int i=0; i<cad.length();i++){
			cifrado=cifrado+(char)(cad.charAt(i)-n%27);
		}
		return (cifrado);
	}
	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la cadena que quieres descifrar:");
		String cad = sc.nextLine();
		System.out.println("Introduce el desplazamiento del descifrado de Cesar:");
		int n= sc.nextInt();
		cad=descesar(cad,n);
		System.out.println("El mensaje descifrado resultante es: "+cad);

	}
}