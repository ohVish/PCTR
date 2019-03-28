/**
*Programa que calcula la raiz de una funcion en un intervalo [a,b] por el método de la bisección.
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/



public class raizporBiseccion{
	static double f(double x){
		return(Math.pow(x,2)-5);
	}

	public static void main(String[] Args){
		double a=2, b=3,error=0.1,c=0,fa,fc;
		while(b-a>error){
			c= (a+b)/2;
			fa=f(a);
			fc=f(c);
			if(fa*fc<0){
				b=c;
			}
			else if(fa*fc>0){
				a=c;
			}
			else{
				a=b=c;
			}
		}
		System.out.println("La solución es "+a);
	}
}