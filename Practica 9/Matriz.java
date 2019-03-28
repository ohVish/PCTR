/**
*	Clase que simula una matriz cuadrada de double;
*
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*/

public class Matriz{
	private double[][]	elementos;
	private int n;

	public Matriz(int tam){
		this.n=tam;
		elementos = new double[n][n];
	}

	public double getElemento(int f, int c){
		return(this.elementos[f][c]);
	}

	public void setElemento(int f,int c,double valor){
		this.elementos[f][c]=valor;
	}
	public void genRandomMatrix(){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				elementos[i][j]=Math.random();
			}
		}
	}

	public Matriz traspuesta(){
		Matriz m = new Matriz(n);
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				m.elementos[j][i]=elementos[i][j];
			}
		}
		return m;		
	}

}