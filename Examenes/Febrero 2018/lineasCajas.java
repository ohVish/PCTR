

import java.util.concurrent.locks.*;
import java.util.concurrent.*;
import java.util.Arrays;

public class lineasCajas{
	private ReentrantLock c;
	private Condition lleno;
	private boolean[] caja;
	private int libres;


	public lineasCajas(){
		this.c=new ReentrantLock();
		this.lleno=c.newCondition();
		caja=new boolean[10];
		Arrays.fill(caja,true);
		libres=10;
	}

	public int entrarCaja(){
		c.lock();
		try{
			while(libres==0){
				try{
					lleno.await();
				}catch(InterruptedException e){}
			}
			int i=0;
			while(!caja[i]){
				i=(i+1)%10;
			}
			System.out.println("Entrando en la caja "+(i+1));
			caja[i]=false;
			libres--;
			return i;
		}finally{
			c.unlock();
		}
	}

	public void salirCaja(int id){
		c.lock();
		try{
			caja[id]=true;
			libres++;
			System.out.println("Saliendo de la caja "+(id+1));
			lleno.signal();
		}finally{
			c.unlock();
		}
	}
}