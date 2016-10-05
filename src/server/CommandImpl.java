package server;

import client.Notifiable;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by archer on 2016-09-22.
 */
public class CommandImpl extends UnicastRemoteObject implements Command {
    public static final String help = "Info!\n /help\n/who\n/nick <nickname>\n/quit";
    public static final Object lock = new Object();
    private ConcurrentHashMap<Notifiable,String> clientinfo;

    public CommandImpl(ConcurrentHashMap<Notifiable, String> clientinfo) throws RemoteException {
        super();
        this.clientinfo = clientinfo;
    }

    public void help(Notifiable client) throws RemoteException {
        client.printMessage(help);
    }

    public void nick(String nick, Notifiable client) throws RemoteException {
        synchronized (clientinfo) {
            if (clientinfo.containsKey(client)) {
                for (String c : clientinfo.values()) {
                    if (c!=null &&  c.equals(nick)) {
                        client.printMessage("Nick is already in use");
                        return;
                    }
                }
                clientinfo.put(client,nick);
            }
        }
    }

    @Override
    public void quit(Notifiable client) throws IOException, RemoteException {
       client.printMessage("GO AWAY!!");
        clientinfo.remove(client);
    }

    @Override
    public void who(Notifiable client) throws RemoteException{
        client.printMessage(clientinfo.values().toString());
    }

    @Override
    public void sendToClient(Notifiable client, String s) throws RemoteException {
        client.printMessage(s);
    }

    public void sendToAll(String message)  throws RemoteException{
        for(Notifiable client : clientinfo.keySet()){
            printToClient(client,message);
        }
    }

    public void sendToAllExceptSender(Notifiable sender, String message)  throws RemoteException{
        System.out.printf("sending to all");
        for(Notifiable client : clientinfo.keySet()){
            if(!client.equals(sender)) {
                System.out.println("gonna send to " + clientinfo.get(client));
                printToClient(client, clientinfo.get(sender)+ ":" + message);
            }

        }
    }

   @Override
    public void deregister(Notifiable client) throws RemoteException {
        clientinfo.remove(client);
    }

    @Override
    public void register(Notifiable client) throws RemoteException {
        System.out.println("registering: " + client);
        clientinfo.put(client,"Orvar den store" + UUID.randomUUID().toString());
    }

    private void printToClient(Notifiable n, String msg){
        try{
            n.printMessage(msg);
        }catch (RemoteException e){
           clientinfo.remove(n);
            System.out.println("REMOVING DISCONNECTED CLIENT " + n);
        }
    }
}



