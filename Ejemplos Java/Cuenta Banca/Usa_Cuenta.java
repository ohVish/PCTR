/* Fichero Usa_Cuenta.java 
  *@author Antonio J. Tomeu
  *@version 1.0 
  *Dpto. Lenguajes y Sistemas Informáticos
  *Área de CC. de la Computación e I.A.
  *Ejemplo de uso de una clase predefinida...
*/

import java.util.Scanner;

public class Usa_Cuenta
{
 public static void main (String [] args)
 {
 	Scanner pause = new Scanner(System.in);
 	Cuenta_Banca Jozelito = new Cuenta_Banca(11111111,100);
 	Cuenta_Banca Pepito = new Cuenta_Banca(11111112,5000);
 	Jozelito.Reintegro(20);
 	Pepito.Deposito(5000);
 	System.out.println("Er dinerito de Jozelito actualmente es de "+ Jozelito.Saldo());
 	pause.nextLine();
 	System.out.println("Er numero cuenta jozelito es de "+ Jozelito.Codigo());
 	pause.nextLine();
 	System.out.println("Er dinerito de Pepito actualmente es de "+ Pepito.Saldo());
 	pause.nextLine();
 	System.out.println("Er numero cuenta Pepito es de "+ Pepito.Codigo());
 	pause.nextLine();
 }
}
