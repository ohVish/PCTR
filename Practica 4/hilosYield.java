


import java.util.Scanner;
public class hilosYield extends Thread{
	public void run(){
		//Thread.yield();
		if(this.getName().equals("Limon")){
		System.out.println("Me he reido.");
		}
		System.out.println("Hilo "+this.getName()+" finaliza");
	}
	public static void main(String[] args) throws InterruptedException{
		Scanner sc = new Scanner(System.in);
		hilosYield[] hilos = new hilosYield[10];
		for(int i=0;i<hilos.length;++i){
			hilos[i]=new hilosYield();
			System.out.println("Introduce el nombre del hilo:");
			hilos[i].setName(sc.nextLine());
		}
		for(int i=0;i<hilos.length;++i){
			hilos[i].start();
		}
		for(int i=0;i<hilos.length;++i){
			hilos[i].join();
		}
	}
}