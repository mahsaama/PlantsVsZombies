package Server;

import com.google.gson.Gson;
import com.sun.glass.ui.Pixels;
import javafx.print.Printer;
import javafx.scene.control.Label;

import java.io.*;
import java.net.Socket;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;


public class ServerReader {
    private Socket socket;
    private Scanner scanner;
    public Connection connection;

    public ServerReader(Socket socket) throws Exception {
        this.socket = socket;
        scanner = new Scanner(socket.getInputStream());
        scanner.useDelimiter("#");
        new Thread(() -> {
            try {
                while (true) {
                    if (!scanner.hasNext()) {
                        Thread.sleep(1);
                        continue;
                    }
                    String json = scanner.next();
                    System.err.println(json);
                    Gson gson = new Gson();
                    Message message = gson.fromJson(json, Message.class);


                    switch (message.command) {
                        case CHAT:
                            for (Connection conn : Server.connectionList) {
                                if (conn.username.equals(message.to)) {
                                    try {
                                        conn.send(Message.chat(message.from, message.to, message.data, message.replyTo));
                                    } catch (Exception e) {
                                        System.err.println(e.getMessage());
                                    }
                                }
                            }
                        case LOGIN:
                            boolean valid = true;
                            Connection founded = null;
                            for (Connection conn : Server.connectionList) {
                                if (conn.username.equals(message.data)) {
                                    if ((System.currentTimeMillis() - conn.lastSeen) > 2000) {
                                        founded = conn;
                                    } else {
                                        connection.send(Message.loginError("Duplicated Username").setMessageId(message.message_id));
                                        valid = false;
                                    }
                                }
                            }
                            if (founded != null) {
                                Server.connectionList.remove(founded);
                                /// اگر به این خط رسیدیم یعنی یک بار لاگین کرده قبلا بعد لاگاوت کرده و الان دوباره لاگین کرده
                            }
                            if (valid) {
                                /// یزور ولیده
                                this.connection.username = message.data;
                                connection.lastSeen = System.currentTimeMillis();
                                connection.send(Message.successfulLogin().setMessageId(message.message_id));
                            }
                            break;
                        case SHOW_USERS:
                            String ids = "";
                            for (Connection conn : Server.connectionList) {
                                if (!conn.username.equals("")) {
                                    if ((System.currentTimeMillis() - conn.lastSeen) > 2000)
                                        ids += conn.username + '\n' + "OFF" + '\n';
                                    else
                                        ids += conn.username + '\n' + "ON" + '\n';
                                }
                            }
                            connection.send(Message.showUsers(ids));
                            break;
                        case PING:
                            connection.lastSeen = message.time;
                            break;
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
