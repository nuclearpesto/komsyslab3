package server;

import client.Client;
import client.Notifiable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(2020);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Command cmd;
        try {
            cmd = new CommandImpl(new ConcurrentHashMap<Notifiable,String>());
            Naming.rebind("rmi://localhost:2020/server", cmd);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
