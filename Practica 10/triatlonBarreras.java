/**
*	Programa que simula un triatlon con hilos mediante el uso de CyclicBarrier
*
*	@author José Miguel Aragón Jurado
*	@version 1.0.
*/
import java.util.concurrent.*;
import java.util.Arrays;

public class triatlonBarreras implements Runnable{
	private CyclicBarrier barrera;
	private double cont;
	private int id;
	private static double[] tiemposTotales = new double[100];

	public triatlonBarreras(CyclicBarrier b,int i){
		this.barrera=b;
		this.cont=0;
		this.id=i;
	}

	public void run(){
		try{
			barrera.await();
		}catch(Exception e){
			System.out.println("ERROR");
		}
		double tiempo=System.nanoTime();
		System.out.println("Empieza la parte de natacion.");
		try{
			Thread.sleep(Math.round(Math.random()*10));
			this.cont+=(System.nanoTime()-tiempo);
			barrera.await();
		}catch(Exception e){
			System.out.println("ERROR");
		}	
		tiempo=System.nanoTime();
		System.out.println("Empieza la parte de ciclismo.");
		try{
			Thread.sleep(Math.round(Math.random()*10));
			this.cont+=(System.nanoTime()-tiempo);
			barrera.await();
		}catch(Exception e){
			System.out.println("ERROR");
		}
		tiempo=System.nanoTime();
		System.out.println("Empieza la parte de carrera a pie.");
		try{
			Thread.sleep(Math.round(Math.random()*10));
			this.cont+=(System.nanoTime()-tiempo);
		}catch(InterruptedException e){
			System.out.println("ERROR");
		}
		System.out.println("Finalizado.");
		tiemposTotales[id]=(this.cont)/1.0e6;
	}

	public static void main(String[] args) {
		int nh=100;
		ExecutorService ex = Executors.newFixedThreadPool(nh);
		CyclicBarrier triatlon = new CyclicBarrier(100);
		for(int i=0;i<nh;i++){
			ex.submit(new triatlonBarreras(triatlon,i));
		}
		ex.shutdown();
		try{
			while(!ex.awaitTermination(1000, TimeUnit.SECONDS));
		}catch(InterruptedException e){
			System.out.println("Error");
		}
		double[] copia=tiemposTotales.clone();
		Arrays.sort(tiemposTotales);
		int i=0;
		while(i<100 && copia[i]!=tiemposTotales[0]){
			i++;
		}
		System.out.println("Ganador:\n Hilo "+i+"\n t= "+tiemposTotales[0]);
	}
}