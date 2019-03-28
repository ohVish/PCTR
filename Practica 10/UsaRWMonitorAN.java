/**
*	Programa que utiliza el monitor RWMonitorAN.java
*	
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*/

import java.util.concurrent.*;
import java.io.*;

public class UsaRWMonitorAN implements Runnable{
	private RWMonitorAN fichero;
	private boolean escritor;

	public UsaRWMonitorAN(RWMonitorAN f, boolean es){
		this.fichero=f;
		this.escritor=es;
	}

	public void run(){
		if(this.escritor==true){
			RandomAccessFile f=fichero.StartWrite();
			try{
				f.seek(f.length());
				f.writeChars("Hilo escritor");
				f.write(0x0d);
				f.write(0x0a);
			}catch(IOException e){}
			f=fichero.EndWrite();
		}
		else{
			RandomAccessFile f = fichero.StartRead();
			try{
				System.out.println(f.readLine());
			}catch(IOException e){}
			f = fichero.EndRead();
		}

		
	}

	public static void main(String[] args) {
		RWMonitorAN f = new RWMonitorAN();
		int nh=10;
		ExecutorService ex = Executors.newFixedThreadPool(nh);
		for(int i=0;i<3;i++){
			ex.submit(new UsaRWMonitorAN(f,true));
		}
		for(int i=0;i<nh-3;i++){
			ex.submit(new UsaRWMonitorAN(f,false));
		}
		ex.shutdown();
		while(!ex.isTerminated());
	}


}