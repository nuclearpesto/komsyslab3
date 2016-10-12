package server;

import client.Notifiable;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Command extends Remote {
    public void help(Notifiable client) throws RemoteException;

    public void nick(String nick, Notifiable client) throws RemoteException;

    public void quit(Notifiable client) throws  RemoteException;

    public void who(Notifiable client) throws RemoteException;

    public void sendToClient(Notifiable client, String s) throws RemoteException;

    public void sendToAll(String message) throws RemoteException;

    public void sendToAllExceptSender(Notifiable sender, String message) throws RemoteException;

    public void deregister(Notifiable client) throws RemoteException;

    public void register(Notifiable client) throws RemoteException;
}
