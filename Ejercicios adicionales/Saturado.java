

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;

public class Saturado implements ActionListener{
	private static Color[][] m;
	private static int altura;
	private static int anchura;
	static JButton x;
	static JButton y;
	static JFrame frame;
	static  JLabel imagen;
	static JLabel resultado;
	
public static void traspose(String nombre, JFrame frame) throws Exception{
		BufferedImage img = new BufferedImage(altura,anchura,BufferedImage.TYPE_INT_RGB);
		int[][] t= new int[anchura][altura];
		for(int i=0;i<altura;++i){
			for(int j=0;j<anchura;j++){
				t[j][i]=m[i][j].getRGB();
			}
		}
		for(int i=0;i<anchura;++i){
			for(int j=0;j<altura;j++){
				img.setRGB(j,i,t[i][j]);
			}
		}
		frame.revalidate();
		frame.repaint();
		File output = new File(nombre);
		ImageIO.write(img,"png",output);
	}
	public Saturado(){
        frame = new JFrame("Saturado");      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        frame.setLayout(new FlowLayout());   
        frame.setSize(300, 300); 		
        x=new JButton("Saturar");
        y= new JButton ("Rotar");
		x.addActionListener(this);
		y.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					try{
					Saturado.traspose("juanan.png",frame);
					}catch(Exception f){}
			}
		});
        frame.add(x);
        frame.add(y);
        frame.pack();     
 		frame.setVisible(true); 
 	}

	private static void cargarImagen(String nombre,JFrame frame)throws Exception{
		BufferedImage img = null;
		File f = new File(nombre);
		img = ImageIO.read(f);
		imagen = new JLabel(new ImageIcon(img));
		frame.add(imagen);
		frame.pack();
		altura=img.getHeight();
		anchura=img.getWidth();
		m = new Color[altura][anchura];
		for(int i=0;i<altura;++i){
			for(int j=0;j<anchura;j++){
				m[i][j]=new Color(img.getRGB(j,i));
			}
		}
	}

	private static void saturar(double u){
		for(int i=0;i<altura;++i){
			for(int j=0;j<anchura;j++){
				int r=m[i][j].getRed();
				int g=m[i][j].getGreen();
				int b=m[i][j].getBlue();
				int r2= (int)((0.213+0.787*u)*r+0.715*(1-u)*g+0.072*(1-u)*b);
				int g2=(int)(0.213*(1-u)*r+(0.715+0.285*u)*g+0.072*(1-u)*b);
				int b2=(int)(0.213*(1-u)*r+0.715*(1-u)*g+(0.072+0.928*u)*b);
				m[i][j]=new Color(r2,g2,b2);
			}
		}
	}

	private static void guardarImagen(String nombre,String formato,JFrame frame) throws Exception{
		File f= new File(nombre);
		BufferedImage img = new BufferedImage(anchura,altura,BufferedImage.TYPE_INT_RGB);
		for(int i=0;i<altura;++i){
			for(int j=0;j<anchura;j++){
				img.setRGB(j,i,m[i][j].getRGB());
			}
		}
		resultado = new JLabel(new ImageIcon(img));
		frame.revalidate();
		frame.repaint();
		ImageIO.write(img,formato,f);
	}

	public static void main(String[] args) throws Exception{

        Saturado main = new Saturado();
  
		/*Scanner sc = new Scanner(System.in);
		double u;
		do{
			System.out.println("Introduce el coeficiente de saturacion que desea entre 1 y 0.\n(0->Saturacion nula | 1-> Imagen sin cambio)");
			u=sc.nextDouble();
		}while(u<0 ||u>1);
		sc.nextLine();
		System.out.println("Introduce el nombre del fichero a saturar:");
		String nombre = sc.nextLine();
		System.out.println("Introduce el nombre del fichero de guardado:");
		String savefile = sc.nextLine();
		System.out.println("Introduce la extension:");
		String tipo = sc.nextLine();*/
		cargarImagen("juanan.png",frame);

		//saturar(0.25);
		//guardarImagen("juananan.png","png",frame);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==x){
			saturar(0);
			try{
			if(resultado==null){
				guardarImagen("juananan.png","png",frame);
				frame.add(resultado);
			}
			else{
			guardarImagen("juananan.png","png",frame);
			frame.revalidate();

			}
			}catch(Exception f){}
		}	
	}

}
