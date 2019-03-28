/**
*Programa que realiza calculos estadísticos sobre una serie de números introducidos por teclado
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;

public class Estadistica{
	protected static double media(double suma, int n){
		return(suma/n);

	}
	protected static double varianza(double suma,double sumac,int n){
		return((sumac-Math.pow(media(suma,n),2))/n);
	}
	protected static double desviacion(double suma, double sumac,int n){
		return(Math.sqrt(varianza(suma,sumac,n)));
	}
	protected static double cmoda(Vector<Double> moda, int n){
		Collections.sort(moda);
		int rep=0,cont=1,j=0;
		for(int i=0;i<n-1;i++){
			if(moda.get(i)==moda.get(i+1)){
				cont++;
			}
			else{
				if(cont>rep){
					rep=cont;
					j=i;
				}
				cont=0;
			}
		}
			return(moda.get(j));
	}
	public static void  main(String[] Args){
		double suma=0,sumac=0;
		Scanner sc = new Scanner(System.in);
		try{
			int n=Integer.parseInt(Args[0]);
			Vector<Double> moda = new Vector<Double>();
			for(int i=0;i<n;i++){
				System.out.println("Numero "+(i+1)+":");
				double x=sc.nextDouble();
				suma=suma+x;
				sumac=sumac+Math.pow(x,2);
				moda.add(i,x);
			}
			int elec=0;
			do{
				System.out.println("Seleccione el calculo estadistico que desea:");
				System.out.println("\t1. Media aritmetica");
				System.out.println("\t2. Moda");
				System.out.println("\t3. Varianza");
				System.out.println("\t4. Desviacion");
				System.out.println("\t5. Salir");
				elec=sc.nextInt();
				switch(elec){
					case 1:
						System.out.println("La media es "+media(suma,n));
						break;
					case 2:
						System.out.println("La moda es "+cmoda(moda,n));
						break;
					case 3:
						System.out.println("La varianza es "+varianza(suma,sumac,n));
						break;
					case 4:
						System.out.println("La desviacion es "+desviacion(suma,sumac,n));
						break;
				}
			}while(elec!=5);

		}catch(Exception e){
			System.out.println("Introduce un numero, por favor.");
		}
		finally{
			sc.close();
		}

	}
}