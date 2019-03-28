/**
*Programa que realiza el suavizado de imágenes para una matriz nxn pixeles
*
*@author José Miguel A.J.
*@version 1.0
*/

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class resImagenParGru implements Runnable{
	private static int n;
	private static int[][] m;
	private static int r[][];
	private int inicio;
	private int fin;

	private static void crearImagen()throws Exception{
		File f = new File("imagen.png");
		BufferedImage img = null;
		img = ImageIO.read(f);
		n= img.getWidth();
		m=new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				Color coloreh = new Color(img.getRGB(i,j));
				m[i][j] = ((coloreh.getRed()+coloreh.getGreen()+coloreh.getBlue())/3)*20/255;
				System.out.println(m[i][j]);
			}
		}
	}
	public resImagenParGru(int i,int f){
		this.inicio=i;
		this.fin=f;
	}
	public void run(){
		int iant;
		int jant;
		for(int i=inicio;i<fin;++i){
			for(int j=0;j<n;++j){
				iant=i-1;
				jant=j-1;
				if(iant<0) iant=n-1;
				if(jant<0) jant=n-1;
				r[i][j]=(4*m[i][j]-m[(i+1)%n][j]-m[i][(j+1)%n]-m[iant][j]-m[i][jant])/8;
				if(r[i][j]<0)r[i][j]=0;
			}
		}
	}
	public static void main(String[] args) throws Exception{
		/*Random rnd = new Random();
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				m[i][j]=rnd.nextInt(255);
			}
		}*/
		crearImagen();
		r= new int[n][n];
		ejecutar();
		//mostrar(r,n);
		try {
    	BufferedImage image=new BufferedImage(n,n,BufferedImage.TYPE_BYTE_GRAY);
	    	for(int i=0; i<n; i++) {
		        for(int j=0; j<n; j++) {
			            int a = (r[i][j]*250/20);
			            System.out.println(r[i][j]);
			            image.setRGB(i,j,a);
		        }
		   	}
		    File output = new File("suavizado.png");
		   	ImageIO.write(image, "png", output);
		}catch(Exception e) {}

		//mostrar(r,n);

	}
	private static void mostrar(int[][] m,int n){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(m[i][j]+",");
			}
			System.out.println();
		}
	}
	private static void ejecutar(){
		int nCores = Runtime.getRuntime().availableProcessors();
		int nTareas = nCores;
		if(nTareas>n){
			nTareas=n;
		}
		int bloque = n/nTareas;
		ExecutorService ex = Executors.newFixedThreadPool(nTareas);
		double tiempo= System.nanoTime();
		for(int i=0;i<nTareas-1;i++){
			ex.submit(new resImagenParGru(i*bloque,(i+1)*bloque));
		}
		ex.submit(new resImagenParGru((nTareas-1)*bloque,nTareas));
		ex.shutdown();
		while(!ex.isTerminated());
		tiempo=System.nanoTime()-tiempo;
		System.out.println("T= "+tiempo/1.0e6);
		System.out.println("Hebras: "+nTareas);

	}
}
