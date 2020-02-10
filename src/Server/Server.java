package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    static Socket socket = null;
    static ServerSocket serverSocket;
    static List<Connection> connectionList = new ArrayList<Connection>() {
    };


    private static void start() {

        try {
            serverSocket = new ServerSocket(3456);
            while (true) {
                socket = serverSocket.accept();
                ServerReader serverReader = new ServerReader(socket);
                Connection conn = new Connection(socket, serverReader);
                connectionList.add(conn);
                serverReader.connection = conn;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        start();
    }
}
