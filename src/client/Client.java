package client;

import server.Command;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by archer on 2016-10-05.
 */
public class Client extends UnicastRemoteObject implements Notifiable {
    private Command command;

    public Client(Command command) throws RemoteException {
        super();
        this.command = command;
    }

    @Override
    public void printMessage(String msg) throws RemoteException {
        System.out.println(msg);
    }

    public void evalCommand(String cmd) throws IOException, RemoteException {
        System.out.println("got " +cmd);

            if (cmd.equals("/help")) {
                command.help(this);
            } else if (cmd.startsWith("/nick")) {
                String s = new String();
                s = cmd.substring("/nick ".length(), cmd.length());
                command.nick(s, this);
            } else if (cmd.equals("/who")) {
                command.who(this);
            } else if (cmd.equals("/quit")) {
                command.quit(this);
            } else {
                command.sendToClient(this, "Unknown command");
            }
    }

    public boolean isCommand(String msg)  throws RemoteException{
        return msg.startsWith("/");
    }

    public void sendToAll(String line) throws RemoteException {
        command.sendToAllExceptSender(this, line);
    }
}
