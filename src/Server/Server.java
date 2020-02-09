package Server;

import User.User;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Server{
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream in = null;
    static List<Connection> connectionList = new ArrayList<Connection>();

    public Server() {
        try{
            String id = "1";
            serverSocket = new ServerSocket(3456);
            while (true) {
                socket = serverSocket.accept();
                ServerReader serverReader = new ServerReader(socket, connectionList);
                connectionList.add(new Connection(id, socket, serverReader));
                id += "1";
            }
        }catch (IOException e1){
            e1.printStackTrace();
        }
    }
}
