/**
* Programa que genera una secuencia de números aleatorios enteros, entre el 0 y el 100, de la longitud que requiera el usuario
* 
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Scanner;

public class aleatorios{

	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la longitud de la secuencia de numeros aleatorios:");
		int n = sc.nextInt();
		for(int i=0; i<n;i++){
			System.out.println("Numero "+(i+1)+": "+(int)(Math.floor(Math.random()*100)));
		}
	}
}