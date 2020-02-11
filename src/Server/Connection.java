package Server;

import com.google.gson.Gson;

import java.net.Socket;
import java.util.Formatter;

public class Connection {
    String username = "";
    Socket socket;
    ServerReader serverReader;
    Formatter formatter;
    long lastSeen = 0;
    public static Gson gson = new Gson();

    public void send(Message message) {
        String json = gson.toJson(message) + "#";
        formatter.format(json);
        formatter.flush();
    }

    Connection(Socket socket, ServerReader serverReader) throws Exception {
        this.socket = socket;
        this.serverReader = serverReader;
        this.formatter = new Formatter(socket.getOutputStream());
    }
}
