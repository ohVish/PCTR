//Ejercicio 1

public class Circulo{
	static double PI = 3.14;
	public static void main(String[] Args){
		double diametro = 14.2;
		double altura =20;
		double volumen=Volumencono(diametro/2,altura);
		System.out.println("El volumen del cono es "+volumen);

	}
	protected static double Volumencono(double r,double h){
		return(PI*Math.pow(r,2)*h/3);
	}

}