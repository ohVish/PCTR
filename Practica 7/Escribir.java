


import java.io.*;

public class Escribir{
	private static File f;
	public static void grafica(String nombre,long x, double y){
		try{
			f=new File(nombre);
			if(f.exists()){
				BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
				bw.write(x+"\t"+y);
				bw.newLine();
				bw.close();			
			}
			else{
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write("#X\tY");
				bw.newLine();
				bw.write(x+"\t"+y);
				bw.newLine();
				bw.close();
			}
		}catch(IOException e){}
	}
}