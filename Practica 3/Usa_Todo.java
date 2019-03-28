/**
*Programa que utiliza todos los métodos de la clase Poligono,Cuadrado y Triangulo.
*
*@author José Miguel Aragón Jurado
*@version 1.0
*/
import java.util.Scanner;
public class Usa_Todo{
	static void poligono(Scanner sc){
		System.out.println("Introduce el numero de lados del poligono:");
		int lados = sc.nextInt();
		Punto[] puntos = new Punto[lados];
		for(int i=0;i<lados;i++){
			System.out.println("Introduce la coordenada x del punto "+(i+1));
			double x=sc.nextDouble();
			System.out.println("Introduce la coordenada y del punto "+(i+1));
			double y=sc.nextDouble();
			puntos[i]=new Punto(x,y);
		}
		Poligono pol = new Poligono(puntos);
		int elec=0;
		double desp=0;
		do{
			System.out.println("Seleccione la operacion que desea realizar:");
			System.out.println("\t 1.Numero de lados.");
			System.out.println("\t 2. Perimetro.");
			System.out.println("\t 3. Escalado.");
			System.out.println("\t 4. Desplazamiento en el eje x.");
			System.out.println("\t 5. Desplazamiento en el eje y.");
			System.out.println("\t 6. Salir.");
			switch(elec){
				case 1:
					System.out.println("El numero de lados es "+pol.nLados());
					break;
				case 2:
					System.out.println("El perimetro del poligono es "+pol.perimetro());
					break;
				case 3:
					System.out.println("Introduce el escalar que desea:");
					double k = sc.nextDouble();
					pol.escalado(k);
					System.out.println("Poligono escalado correctamente.");
					break;

				case 4:
					System.out.println("Introduce el desplazamiento que desea:");
					desp = sc.nextDouble();
					pol.despX(desp);
					System.out.println("Poligono desplazado correctamente.");
					break;

				case 5:
					System.out.println("Introduce el desplazamiento que desea:");
					desp = sc.nextDouble();
					pol.despY(desp);
					System.out.println("Poligono desplazado correctamente.");
					break;
			}
		}while(elec!=6);
	}
	static void cuadrado(Scanner sc){
		Punto[] puntos = new Punto[4];
		for(int i=0;i<4;i++){
			System.out.println("Introduce la coordenada x del punto "+(i+1));
			double x=sc.nextDouble();
			System.out.println("Introduce la coordenada y del punto "+(i+1));
			double y=sc.nextDouble();
			puntos[i]=new Punto(x,y);
		}
		Cuadrado cuad = new Cuadrado(puntos);
		int elec=0;
		do{
			System.out.println("Seleccione la figura que quiere introducir:");
			System.out.println("\t 1.Area del cuadrado.");
			System.out.println("\t 2. Diagonal");
			System.out.println("\t 3. Salir");
			elec=sc.nextInt();
			if (elec==1) {
				System.out.println("El area de este cuadrado es "+cuad.area());
			}
			else if (elec==2) {
				System.out.println("La longitud de la diagonal de este cuadrado es "+cuad.longdiagonal());
			}
		}while(elec!=3);
	}

	static void triangulo(Scanner sc){
		Punto[] puntos = new Punto[3];
		for(int i=0;i<3;i++){
			System.out.println("Introduce la coordenada x del punto "+(i+1));
			double x=sc.nextDouble();
			System.out.println("Introduce la coordenada y del punto "+(i+1));
			double y=sc.nextDouble();
			puntos[i]=new Punto(x,y);
		}
		Triangulo trian = new Triangulo(puntos);
		int elec=0;
		do{
			System.out.println("Seleccione la figura que quiere introducir:");
			System.out.println("\t 1.Area del triangulo.");
			System.out.println("\t 2. Salir");
			elec=sc.nextInt();
			if (elec==1) {
				System.out.println("El area de este triangulo es "+trian.area());
			}
		}while(elec!=2);

	}
	public static void main(String[] Args){
		Scanner sc = new Scanner(System.in);
		int elec=0;
		do{
			System.out.println("Seleccione la figura que quiere introducir:");
			System.out.println("\t 1.Poligono generico.");
			System.out.println("\t 2. Cuadrado.");
			System.out.println("\t 3. Triangulo.");
			System.out.println("\t 4. Salir");
			elec=sc.nextInt();
			switch(elec){
				case 1:
					poligono(sc);
					break;
				case 2:
					cuadrado(sc);
					break;
				case 3:
					triangulo(sc);
					break;
			}
		}while(elec!=4);
		sc.close();
	}

}