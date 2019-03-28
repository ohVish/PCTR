

public class MySemaphore{
	private int S,bloqueados=0;

	public MySemaphore(int s){
		this.s=s;
	}

	public synchronized void acquire(){
		if(this.S>0){
			this.S--;
		}
		else{
			this.bloqueados++;
			wait();
		}
	}

	public synchronized void release(){
		if(bloqueados>0){
			bloqueados--;
			notify();
		}
		else{
			S++;
		}
	}
}