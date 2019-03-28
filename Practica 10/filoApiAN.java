/**
*	Monitor que resuelve el problema de los filósofos comensales, empleando la API avanzada de Java.
*
*	@author	José Miguel Aragón Jurado
*	@version 1.0.
*/

import java.util.concurrent.*;
import java.util.Arrays;
import java.util.concurrent.locks.*;

public  class filoApiAN{
	private volatile int[] tenedores;
	private volatile ReentrantLock l;
	private volatile Condition[] comer;

	public void cogerTenedores(int i){
		l.lock();
		try{
			while(tenedores[i]!=2){
				try{
					comer[i].await();
				}catch(InterruptedException e){}
			}
			System.out.println("Filosofo "+i+" coge tenedores.");
			tenedores[(i+1)%5]=tenedores[(i+1)%5]-1;
			if((i-1)<0){
				tenedores[4]=tenedores[4]-1;
			} 
			else{
				tenedores[(i-1)%5]=tenedores[(i-1)%5]-1;
			}
		}finally{
			l.unlock();
		}
	}

	public void soltarTenedores(int i){
		l.lock();
		try{
			tenedores[(i+1)%5]=tenedores[(i+1)%5]+1;
			int iant;
			if((i-1)<0) iant=4;
			else iant=(i-1);
			tenedores[iant]=tenedores[iant]+1;
			System.out.println("Filosofo "+i+" suelta tenedores.");
			if(tenedores[(i+1)%5]==2){
				comer[(i+1)%5].signal();
			}
			if(tenedores[iant]==2){
				comer[iant].signal();
			}
		}finally{
			l.unlock();
		}
	}
	public filoApiAN(){
		tenedores=new int[5];
		Arrays.fill(tenedores,2);
		l=new ReentrantLock();
		comer = new Condition[5];
		for(int i=0;i<5;i++){
			comer[i]=l.newCondition();
		}
	}
}