import java.util.Scanner;

public class suma_numeros
{
	public static void main(String[] args)
	{
		Scanner scanf = new Scanner(System.in);
		int a,b;
		System.out.println("Introduce dos numeros para sumar");
		a=scanf.nextInt();
		b=scanf.nextInt();
		System.out.println("La suma es "+(a+b));
	}
}