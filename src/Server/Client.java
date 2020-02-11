package Server;

import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javax.imageio.*;


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
        clientHandler = new ClientHandler(this);
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

    public void gameRequest(String to) {
        Message message = Message.gameRequest(this.username, to);
        send(message);
    }

    public void accept(String to) {
        Message message = Message.accept(this.username, to);
        send(message);
    }

    public void sendChat(String to, String chat) {
        Message message = Message.chat(this.username, to, chat);
        send(message);
    }

    public void sendPic(String to, String jpgPath) throws Exception {
        ////     fghat jpg mishe ferestad!
        BufferedImage bufferimage = ImageIO.read(new File(jpgPath));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(bufferimage, "jpg", output);
        byte[] data = output.toByteArray();
        String base64 = Base64.encode(data);
//        System.out.println(base64);
        Message message = Message.chatPic(this.username, to, base64);
        send(message);


    }

    public void reply(String to, String chat, long replyTo) {
        Message message = Message.chat(this.username, to, chat, replyTo);
        send(message);
    }

    public void scoreBoard(){
        Message message = Message.scoreBoardClient();
        send(message);
    }


    public static void main(String[] args) {



        try {
            Client client1 = new Client();
            Client client2 = new Client();
            client1.connect();
            client2.connect();
            client1.login("1");
            client2.login("2");

            client1.showUsers();
            client2.gameRequest("1");
            client1.accept("2");
            while (true){
                Thread.sleep(1000);
                client1.scoreBoard();
                client2.sendChat("1", "hi loser!");
            }
//            client.connect();
//            System.out.println(client.login, users[c]);
//            if(c == 0){
//                client.gameRequest(users[1]);
//            }else{
//                client.accept(users[0]);
//            }
//            client.showUsers();
//            client.sendChat("test4", "hey zendegi");
//            client.sendPic("test", "F:\\phone\\pics\\1.jpg");
//            Thread.sleep(10000);
//            client.gameRequest("test2");
        } catch (Exception e) {

        }

//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//
//        }
    }
}
