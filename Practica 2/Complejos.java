/**
*Clase que simula a un número complejo
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/

public class Complejos{
	private double[] c= new double[2];

	public Complejos(){};

	public Complejos(double real, double imaginario){
		this.c[0]=real;
		this.c[1]=imaginario;
	}

	public double getReal(){
		return(c[0]);
	}

	public double getImaginario(){
		return(c[1]);
	}

	public void setReal(double real){
		this.c[0]=real;
	}

	public void setImaginario(double imaginario){
		this.c[1]=imaginario;
	}

	public Complejos sumaComplejos(Complejos c2){
		Complejos cnew = new Complejos();
		cnew.c[0]=(this.c[0]+c2.c[0]);
		cnew.c[1]=(this.c[1]+c2.c[1]);
		return(cnew);
	}

	public double moduloComplejos(){
		return(Math.sqrt(Math.pow(c[0],2)+Math.pow(c[1],2)));
	}
	public Complejos restaComplejos(Complejos c2){
		Complejos cnew = new Complejos();
		cnew.c[0]=(this.c[0]-c2.c[0]);
		cnew.c[1]=(this.c[1]-c2.c[1]);
		return(cnew);
	}
	public Complejos multiplicarComplejos(Complejos c2){
		Complejos cnew =new Complejos();
		cnew.c[0]=(this.c[0]*c2.c[0]-this.c[1]*c2.c[1]);
		cnew.c[1]=(this.c[1]*c2.c[0]+this.c[0]*c2.c[1]);
		return(cnew);
	}
	public Complejos multiplicarReal(double a){
		Complejos cnew = new Complejos();
		cnew.c[0]=this.c[0]*a;
		cnew.c[1]=this.c[1]*a;
		return(cnew);
	}

	public Complejos divisionComplejos(Complejos c2){ 
		Complejos cnew = new Complejos();
		if(c2.esComplejoNulo()==true){
			cnew=null;
		}
		else{
			cnew.c[0]=(this.c[0]*c2.c[0]+this.c[1]*c2.c[1])/(Math.pow(c2.c[0],2)+Math.pow(c2.c[1],2));
			cnew.c[1]=(this.c[1]*c2.c[0]-this.c[0]*c2.c[1])/(Math.pow(c2.c[0],2)+Math.pow(c2.c[1],2));
		}
		return (cnew);
	}

	public boolean esComplejoNulo(){
		boolean a = false;
		if(this.c[0]==this.c[1] && this.c[0]==0){
			a=true;
		}
		return(a);
	}

	public int cuadranteComplejo(){
		int a=0;
		if(this.c[0]>0 && this.c[1]>0){
			a=1;
		}
		else{
			if(this.c[0]<0 && this.c[1]>0){
					a=2;
			}
			else{
				if(this.c[0]<0 && this.c[1]<0){
					a=3;
				}
				else{
					if(this.c[0]>0 && this.c[1]<0){
						a=4;
					}
				}
			}
		}
		return(a);
	}

	public Complejos conjugadoComplejo(){
		Complejos cnew = new Complejos();
		cnew.c[0]=this.c[0];
		cnew.c[1]=-this.c[0];
		return(cnew);
	}

}