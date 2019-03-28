

import java.util.Random;
import java.rmi.*;
import java.net.*;

public class cHelion{
	public static void main(String[] args) {
		try{
			iHelion serv = (iHelion) Naming.lookup("rmi:/localhost:1099/Guerra");
			Random r = new Random();
			while(!serv.ataque(r.nextInt(20000)));
			System.out.println("GG Victoria");
		}catch(RemoteException | NotBoundException | MalformedURLException e){
			System.out.println("Error.");
		}
	}
}