/**
*	Clase que reune los métodos y atributos necesarios para simular una elipse
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

public class Elipse{
	double a;
	double b;
	double o1;
	double o2;

//Constructor de un objeto de clase Elipse, con sus 4 parametros reales
	public Elipse(double c,double d, double o3, double o4){
		this.a=c;
		this.b=d;
		this.o1=o3;
		this.o2=o4;
	}

//Constructor vacío
	public Elipse(){}

// Observadores de los parametros de la elipse
	public double geta(){
		return(this.a);
	}

	public double getb(){
		return(this.b);
	}

	public double geto1(){
		return(this.o1);
	}

	public double geto2(){
		return(this.o2);
	}

//Modificadores de los parametros de la elipse
	public void seta(double c){
		this.a=c;
	}

	public void setb(double d){
		this.b=d;
	}

	public void seto1(double o3){
			this.o1=o3;
	}

	public void seto2(double o4){
			this.o2=o4;
	}	
//Método que comprueba si un punto pertenece a la elipse
	public boolean esUnPunto(double x,double y){
		boolean comp = false;
		double p = Math.pow(x-this.o1,2)/Math.pow(this.a,2)+Math.pow(y-this.o2,2)/Math.pow(this.b,2);
		if(p<=1){
			comp=true;
		}
		return(comp);
	}
//Método que calcula la superficie de la elipse
	public double superficie(){
		return(Math.PI*this.a*this.b);
	}

//Método que calcula el perimetro de una elipse
	public double perimetro(){
		return(Math.PI*(3*(a+b)-Math.sqrt(3*(a+b)*(a+3*b))));
	}
}