/**
* Programa que realiza la función de Ackermann
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/


public class Ack{
	private static int ackermann(int m,int n){
		if(m==0){
			return(n+1);
		}
		else{
			if(n==0){
				return(ackermann(m-1,1));
			}
			else{
				return(ackermann(m-1,ackermann(m,n-1)));
			}
		}
	}
	public static void main(String[] Args) {
		try{
			int m = Integer.parseInt(Args[0]);
			int n = Integer.parseInt(Args[1]);
			System.out.println("El numero de la funcion de Ackermann es "+ ackermann(m,n));

		}catch(NumberFormatException e){
			System.out.println("Introduce un numero entero");
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Introduce dos parametros por consola");
		}
	}
}