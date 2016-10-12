package client;

import server.Command;
import server.MessageSender;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by archer on 2016-10-05.
 */
public class Main {
    public static void main(String... args) {
        Client client = null;
        Command obj = null;
        Scanner sc = null;
        try {

            obj = (Command) Naming.lookup("rmi://localhost:2020/server");
            client = new Client(obj);
            sc = new Scanner(System.in);
            obj.register(client);
            String line;
            while (sc.hasNext()) {
                    line = sc.nextLine();
                    if(client.isCommand(line))
                        client.evalCommand(line);
                    else
                        client.sendToAll(line);
            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            System.out.println("server disconnected");

        }
        catch (MalformedURLException e ){
            e.printStackTrace();
        }finally {
            if(sc!=null){

                sc.close();
            }

            System.exit(0);
        }
    }
}
