
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;
import java.util.Scanner;

public class Trasposicion{

	private static int[][] m;
	private static int n;

	private static void leerimagen(String nombre)throws Exception{
		File f = new File(nombre);
		BufferedImage img = null;
		img = ImageIO.read(f);
		n=img.getWidth();
		m= new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				Color coloreh = new Color(img.getRGB(i,j));
				m[i][j]=coloreh.getRGB();
			}
		}
	}

	public static void traspose(String nombre,int[][] m, int n) throws Exception{
		BufferedImage img = new BufferedImage(n,n,BufferedImage.TYPE_INT_RGB);
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				int a = m[i][j];
				img.setRGB(j,i,a);
			}
		}
		File output = new File(nombre);
		ImageIO.write(img,"png",output);
	}

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre del fichero a trasponer: ");
		String nombre = sc.nextLine();
		System.out.println("Nombre del fichero a guardar(FORMATO PNG)");
		String output = sc.nextLine();
		leerimagen(nombre);
		traspose(output,m,n);
		sc.close();
	}
}