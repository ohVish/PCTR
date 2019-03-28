


import java.util.concurrent.*;

public class usaLineasCajas{


	public static void main(String[] args) {
		int nClientes=15;
		lineasCajas local = new lineasCajas();
		ThreadPoolExecutor ex = new ThreadPoolExecutor(nClientes,nClientes,0L,TimeUnit.DAYS,new LinkedBlockingQueue<Runnable>());
		for(int i=0;i<nClientes;i++){
			ex.execute(new cliente("i",local));
		}
		ex.shutdown();
		while(!ex.isTerminated());
	}
}