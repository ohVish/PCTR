/**
*Clase que crea hilos que multiplican una matriz por un vector de forma concurrente.
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/




public class prodMatConcurrente extends Thread{
	private int inicio;
	public static int[][] m1;
	public static int[][] m2;
	public static int[][] mr;
	public static int f1;
	public static int c1;
	public static int c2;
	public prodMatConcurrente(int i){
		this.inicio=i;
	}

	public void run(){
			for(int j=0;j<c2;++j){
				for(int k=0;k<c1;++k){
					mr[inicio][j]+=m1[inicio][k]*m2[k][j];
				}
			}
		}
}