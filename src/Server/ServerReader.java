package Server;

import com.google.gson.Gson;

import java.net.Socket;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class ServerReader {
    private Socket socket;
    private Scanner scanner;
    static List<Connection> connectionList;
    private Formatter formatter;

    public ServerReader(Socket socket, List<Connection> connectionList) {
        this.connectionList = connectionList;
        this.socket = socket;
        try {
            formatter = new Formatter(socket.getOutputStream());
            scanner = new Scanner(socket.getInputStream());
            scanner.useDelimiter("#");
        } catch (Exception e) {

        }
        new Thread(() -> {
            try {
                while (true) {
                    if (!scanner.hasNext()) {
                        Thread.sleep(1);
                        continue;
                    }
                    String json = scanner.next();
//                    System.out.println(json);
                    Gson gson = new Gson();
                    Message message = gson.fromJson(json, Message.class);


                    switch (message.command) {
                        case CHAT:
                            for (Connection connection : connectionList) {
                                if (connection.id.equals(message.to)) {
                                    connection.serverReader.formatter.format(Message.chat(message.from, message.to, message.data));
                                    connection.serverReader.formatter.flush();
                                }
                            }
                            break;
                        case LIST:
                            String ids = "";
                            for (Connection connection : connectionList) {
                                ids += connection.id + '\n';
                            }
//                            System.out.println(Message.lst(ids));
                            formatter.format(Message.lst(ids));
                            formatter.flush();
                            break;
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
