package Server;

import com.google.gson.Gson;

import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Client {
    Socket socket;
    Formatter formatter;
    String username;
    ClientHandler clientHandler;
    public static Gson gson = new Gson();


    public void send(Message message) {
        String json = gson.toJson(message) + "#";
        formatter.format(json);
        formatter.flush();
    }

    public Message sendAndGetResult(Message message) throws Exception {
        String json = gson.toJson(message) + "#";
        formatter.format(json);
        formatter.flush();
        while (true) {
            if (clientHandler.results.containsKey(message.message_id)) {
                return clientHandler.results.get(message.message_id);
            } else {
                Thread.sleep(1);
            }
        }
    }


    public Message send_username() throws Exception {
        return this.sendAndGetResult(Message.loginCommand(this.username));
    }

    public void connect() throws Exception {
        socket = new Socket("localhost", 3456);
        formatter = new Formatter(socket.getOutputStream());
        clientHandler = new ClientHandler();
        ClientReader clientReader = new ClientReader(socket, clientHandler);
        this.pinging();
    }

    public boolean login(String username) throws Exception {
        this.username = username;
        Message message = send_username();

        if (message.command == Command.INVALID_USERNAME) {
            System.out.println(message.data);
            return false;
        }
        return true;
    }

    public void showUsers() {
        Message message = Message.showUsers();
        this.send(message);
    }

    public void pinging() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                this.send(Message.ping(System.currentTimeMillis()));
            }

        }).start();
    }

    public void sendChat(String to, String chat) {
        Message message = Message.chat(this.username, to, chat);
        send(message);
    }

    public void reply(String to, String chat, long replyTo) {
        Message message = Message.chat(this.username, to, chat, replyTo);
        send(message);
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.connect();
            System.out.println(client.login("test111"));
            client.showUsers();
            client.sendChat("test4", "hey zendegi");
        } catch (Exception e) {

        }

//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//
//        }
    }
}

