
public class sumacuadrados
{
	public static void main (String[] Args)
	{
		int suma=0;
		for(int i=1;i<=100;i++){
			suma += Math.pow(i,2);
		}
		System.out.println("La suma es "+suma);
	}
}