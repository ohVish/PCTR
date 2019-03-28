/**
* Programa que realiza el cifrado de César
* 
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Scanner;

public class Cesar{
	protected static String cesar(String cad, int n){
		String cifrado = new String();
		for(int i=0; i<cad.length();i++){
			cifrado=cifrado+(char)(cad.charAt(i)+n%27);
		}
		return (cifrado);
	}
	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la cadena que quieres cifrar:");
		String cad = sc.nextLine();
		System.out.println("Introduce el desplazamiento del cifrado de Cesar:");
		int n= sc.nextInt();
		cad=cesar(cad,n);
		System.out.println("El mensaje cifrado resultante es: "+cad);

	}
}