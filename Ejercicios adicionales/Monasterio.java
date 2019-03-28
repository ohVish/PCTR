

import java.util.concurrent.*;

public class Monasterio implements Runnable{
	private static final int n=3;
	private static volatile int  r=n;
	private boolean cocinero;
	private static volatile int sc=0;
	private static Semaphore em = new Semaphore(1);
	private static Semaphore m = new Semaphore(n);
	private static Semaphore c = new Semaphore(0);
	public Monasterio(boolean valor){
		this.cocinero=valor;
	}

	public void run(){
		if(cocinero==true){
			while(true){
				try{
					c.acquire();
					r=n;
					Thread.sleep(10000);
					System.out.println("Andres: Servido Robe.");
					m.release(n);
				}catch(InterruptedException e){}
			}
		}
		else{
				try{
					m.acquire();
					System.out.println("*En la cola.*");
					Thread.sleep(1000);
					em.acquire();
					sc++;
					System.out.println("Robe: Que quiere de segundo");
					Thread.sleep(1000);
					r--;
					System.out.println("Paco THE FRUITER: San Jacobo con patatas,gracias.Quedan "+r+"raciones");
					Thread.sleep(1000);
					if(r==0){
						System.out.println("Robe: Marcha otra Andres.");
						Thread.sleep(1000);
						c.release();
					}
					sc--;
					em.release();
					System.out.println("Paco THE FRUITER: Vale pa cafe.");
					Thread.sleep(1000);
				}catch(InterruptedException e){}
		}
	}


	public static void main(String[] args) throws Exception{
		int hilos = 5;
		ScheduledExecutorService ex = Executors.newScheduledThreadPool(hilos);
		ex.execute(new Monasterio(true));
		for(int i=0;i<hilos;i++){
			//ex.execute(new Monasterio(false));

			ex.scheduleAtFixedRate(new Monasterio(false),0,500,TimeUnit.MILLISECONDS);
			Thread.sleep(500);
		}
	}
}