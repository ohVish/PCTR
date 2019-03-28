
import java.rmi.*;
import java.rmi.server.*;

public interface iHelion extends Remote{
	public boolean ataque(int pos) throws RemoteException;
}