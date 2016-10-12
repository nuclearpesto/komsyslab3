package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by archer on 2016-10-05.
 */
public interface Notifiable extends Remote{
    public void printMessage(String msg) throws RemoteException;
    public void isAlive() throws RemoteException;

}
