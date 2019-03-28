/**
* Clase que simula un triángulo heredando de la clase Polígono
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

public class Triangulo extends Poligono{
	public Triangulo(Punto[] points){
			super(points);
	}

	public double area(){
		double s=(this.perimetro())/2;
		double area=1;
		int i=0;
		for(i=0;i<this.puntos.length-1;i++){
			area*=(s-Math.sqrt(Math.pow((this.puntos[i].x - this.puntos[i+1].x),2)+Math.pow((this.puntos[i].y - this.puntos[i+1].y),2)));
		}
		area*=(s-Math.sqrt(Math.pow((this.puntos[i].x - this.puntos[0].x),2)+Math.pow((this.puntos[i].y - this.puntos[0].y),2)));
		return(Math.sqrt(area*s));
	}
}