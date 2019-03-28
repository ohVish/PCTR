/**
*Clase que simula un cuadrado heredando de la clase Poligono
*
*@author	José Miguel Aragón Jurado
*@version 	1.0
*/

public class Cuadrado extends Poligono{
	public Cuadrado(Punto[] points){
		super(points);
	}
	public double area(){
		return(Math.pow((this.puntos[0].x - this.puntos[1].x),2)+Math.pow((this.puntos[0].y - this.puntos[1].y),2));	
	}
	public double longdiagonal(){
		double l2=Math.pow((this.puntos[0].x - this.puntos[1].x),2)+Math.pow((this.puntos[0].y - this.puntos[1].y),2);
		return(Math.sqrt(l2+l2));
	}
}