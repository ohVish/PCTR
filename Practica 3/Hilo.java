/**
* Clase que define el hilo que vamos a usar para sumar o restar.
*
*@author José Miguel Aragón Jurado 
*@version 1.0
*/

public class Hilo extends Thread{
	private boolean a;
	private static long n,x=0;
	public Hilo(boolean b){
		this.a=b;
	}
	public static void setN(long num){
		n=num;
	}
	public static long getX(){
		return(x);
	}
	public void run(){
		for(int i = 0 ;i<n;i++){
			if(a==true){
				x++;
			}
			else{
				x--;
			}
		}
	}
}