/**
* Programa que realiza el escalado de un vector secuencialmente
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Random;

public class escalaVector {
	public static void main(String[] Args){
		int[] v = new int[100000];
		rellenar(v);
		//mostrar(v);
		escalar(v,100);
		//mostrar(v);

	}
	static void rellenar(int[] v){
		Random rnd = new Random();
		for(int i=0;i<v.length;i++){
			v[i]=rnd.nextInt(100);
		}
	}

	static void escalar(int[] v,int n){
		for(int i=0;i<v.length;i++){
			v[i]*=n;
		}	
	}
	static void mostrar(int[] v){
		for(int i=0;i<v.length-1;i++){
			System.out.print(v[i]+",");
		}
		System.out.println(v[v.length-1]);
	}
}