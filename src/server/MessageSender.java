package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by archer on 2016-10-05.
 */
public interface MessageSender extends Remote {
    public void hello() throws RemoteException;
}
