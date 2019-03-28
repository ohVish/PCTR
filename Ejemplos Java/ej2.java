import java.util.Scanner;
public class ej2
{
	public static void main (String[] Args)
	{
		Scanner num = new Scanner(System.in);
		int suma=0;
		int n;
		System.out.println("A partir de que numero empieza la suma?");
		n = num.nextInt();
		for(int i=n+1;i<=100+n;i++){
			suma += Math.pow(i,2);
		}
		System.out.println("La suma es "+suma);
	}
}