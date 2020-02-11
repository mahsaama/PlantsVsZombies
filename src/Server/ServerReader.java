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
                        case SCORE_BOARD:
                            Message scb = Message.scoreBoard();
                            connection.send(scb.setMessageId(message.message_id));
                            break;
                        case PIC:
                            ServerHandler.sendMessageToClient(message);
                            connection.send(message);
                            break;
                        case ACCEPT_GAME_REQUEST:
                            int result = ServerHandler.startGame(message);
                            String error = null;
                            if (result == 2) {
                                error = "player is in game";
                            }
                            if (result == 3) {
                                error = "played disconnected";
                            }
                            if (error != null) {
                                connection.send(Message.error(error).setMessageId(message.message_id));
                            } else {
                                connection.send(new Message().setMessageId(message.message_id));
                            }
                            break;
                        case CHAT:
                            ServerHandler.sendMessageToClient(message);
                            connection.send(message);
                            break;
                        case GAME_REQUEST:
                            if (!ServerHandler.sendMessageToClient(message)) {
                                connection.send(Message.error("no such username or username is disconnected").setMessageId(message.message_id));
                            } else {
                                connection.send(new Message().setMessageId(message.message_id));
                            }
//                            connection.send(message);
                            break;
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
                            connection.send(Message.showUsers(ids).setMessageId(message.message_id));
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
