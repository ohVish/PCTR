


import java.util.Random;
import java.util.Arrays;
import java.rmi.*;
import java.util.concurrent.locks.*;
import java.rmi.server.*;
import java.net.*;
import java.rmi.registry.*;

public class sHelion extends UnicastRemoteObject implements iHelion{
	private int ciudades;
	private int[] planeta;
	private ReentrantLock c;

	public sHelion() throws RemoteException{
		c = new ReentrantLock();
		planeta=new int[20000];
		Arrays.fill(planeta,0);
		ciudades=2500;
		Random r = new Random();
		int[] aux = new int[25000];
		for(int i=0;i<ciudades;i++){
			int j=r.nextInt(20000);
			Arrays.sort(aux);
			while(Arrays.binarySearch(aux,j)>=0){
				j=r.nextInt(20000);
			}
			planeta[j]=1;
			aux[i]=j;
		}
		int j = r.nextInt(20000);
		Arrays.sort(aux);
		while(Arrays.binarySearch(aux,j)>=0){
			j=r.nextInt(20000);
		}
		planeta[j]=2;
	}

	@Override
	public boolean ataque(int pos) throws RemoteException{
		if(planeta[pos]==1){
			c.lock();
			try{
				planeta[pos]=0;
				ciudades--;
			}finally{
				c.unlock();
			}
			System.out.println("Ciudad destruida. Quedan "+(ciudades));
			if(ciudades==100){
				System.out.println("Nos rendimos...");
				return true;
			}
		}
		else if(planeta[pos]==2){
			System.out.println("Planeta destruido");
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		try{
			iHelion servidor = new sHelion();
			LocateRegistry.createRegistry(1099);
			System.out.println("pepito");
			Naming.rebind("rmi:/localhost:1099/Guerra",servidor);
			System.out.println("Servidor listo");
		}catch(MalformedURLException | RemoteException e){
			System.out.println("Problemas");
		}
	}
}