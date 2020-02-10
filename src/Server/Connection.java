package Server;

import java.net.Socket;

public class Connection {
    String id;
    Socket socket;
    ServerReader serverReader;

    Connection(String id, Socket socket, ServerReader serverReader) {
        this.id = id;
        this.socket = socket;
        this.serverReader = serverReader;
    }
}
