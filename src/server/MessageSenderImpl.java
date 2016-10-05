package server;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by archer on 2016-10-05.
 */
public class MessageSenderImpl extends UnicastRemoteObject implements MessageSender {

    protected MessageSenderImpl() throws RemoteException {
    }

    protected MessageSenderImpl(int port) throws RemoteException {
        super(port);
    }

    protected MessageSenderImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void hello() throws RemoteException{
        System.out.println("MessageSender World!");
    }
}
