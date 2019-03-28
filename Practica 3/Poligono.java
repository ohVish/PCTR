/**
* Clase que simula un polígono
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

public class Poligono {
	Punto[] puntos;

	public Poligono(Punto[] points){
		this.puntos=points;
	}

	public int nLados(){
		return(this.puntos.length);
	}

	public double perimetro(){
		double suma=0;
		for(int i=0;i<this.nLados();i++){
			if(i==this.nLados()-1){
				suma+=Math.sqrt(Math.pow((this.puntos[i].x - this.puntos[0].x),2)+Math.pow((this.puntos[i].y - this.puntos[0].y),2));
			}
			else{
				suma+=Math.sqrt(Math.pow((this.puntos[i].x - this.puntos[i+1].x),2)+Math.pow((this.puntos[i].y - this.puntos[i+1].y),2));
			}
		}
		return suma;
	}
	private Punto media(){
		Punto media = new Punto(0,0);
		for(int i=0;i<this.puntos.length;i++){
			media.x+=this.puntos[i].x;
			media.y+=this.puntos[i].y;
		}
		media.x=media.x/this.puntos.length;
		media.y=media.y/this.puntos.length;
		return media;
	}
	public void escalado(double z){
		Punto media = new Punto(0,0);
		media=media();
		//Restamos la media para situar el polígono en el centro.
		this.despX(-media.x);
		this.despY(-media.y);
		for(int i=0;i<this.puntos.length;i++){
			this.puntos[i].x*=z;
			this.puntos[i].y*=z;
		}
		//Volvemos a sumar la media para situar el polígono en su correcta situación.
		this.despX(media.x);
		this.despY(media.y);
	}

	public void despX(double dx){
		for(int i=0;i<this.nLados();i++){
			this.puntos[i].x+=dx;
		}
	}

	public void despY(double dy){
		for(int i=0;i<this.nLados();i++){
			this.puntos[i].y+=dy;
		}
	}

	public String toString(){
		String pol = new String();
		for(int i=0;i<this.nLados();i++){
			pol+="Punto "+(i+1)+": ("+this.puntos[i].x+", "+this.puntos[i].y+")\n";
		}
		return pol;
    }

}
