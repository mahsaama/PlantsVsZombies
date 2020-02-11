package Server;

import com.google.gson.Gson;
import javafx.print.Printer;
import javafx.scene.control.Label;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class ClientReader {
    private Socket socket;
    private Scanner scanner;
    private ClientHandler clientHandler;

    public ClientReader(Socket socket, ClientHandler clientHandler) throws Exception {
        this.clientHandler = clientHandler;
        this.socket = socket;
        scanner = new Scanner(socket.getInputStream());
        scanner.useDelimiter("#");

        new Thread(() -> {
            try {
                while (true) {
                    String json = scanner.next();
                    System.err.println(json);
                    Gson gson = new Gson();
                    Message message = gson.fromJson(json, Message.class);
                    switch (message.command) {
                        case INVALID_USERNAME:
                            clientHandler.addResult(message);
                            break;
                        case SUCCESSFUL_LOGIN:
                            clientHandler.addResult(message);
                            break;
                        case SHOW_USERS:
                            clientHandler.showUsers(message);
                            break;
                        case CHAT:
                            clientHandler.chat(message);
                            break;
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

