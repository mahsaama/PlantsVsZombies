package Server;

import com.google.gson.Gson;

import java.net.Socket;
import java.util.Scanner;

public class ClientReader {
    private Socket socket;
    private Scanner scanner;

    public ClientReader(Socket socket) {
        this.socket = socket;
        try {
            scanner = new Scanner(socket.getInputStream());
            scanner.useDelimiter("#");
        } catch (Exception e) {

        }
        new Thread(() -> {
            try {
                while (true) {
                    String json = scanner.next();
//                    System.out.println(json);
                    Gson gson = new Gson();

                    Message message = gson.fromJson(json, Message.class);


                    switch (message.command) {
                        case LIST:
                            System.out.println(message.data);
                            break;
                        case CHAT:
                            System.out.println(message.data);
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

