package Server;

import Menu.ChatMenuView;
import javafx.util.Pair;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;

public class ClientHandler {

    HashMap<Integer, Message> results = new HashMap<>();
    Client client;
    ChatMenuView chatMenuView;
    public ArrayList<Message> chatHistory = new ArrayList<>();
    public void setChatroom(ChatMenuView chatroom) {
        this.chatMenuView = chatroom;
    }


    ClientHandler(Client client) {
        this.client = client;
    }

    public void addResult(Message message) {
        this.results.put(message.message_id, message);
    }

    public void showUsers(Message message) {
        System.err.println(message.data);
    }

    public void chat(Message message) {
        this.chatHistory.add(message);
        if (this.chatMenuView != null) {
            chatMenuView.setContent();
        }
        System.out.println(message.from + " " + message.to + " " + message.data + " " + message.chatId + " " + message.replyTo);
    }

    public void chatPic(Message message) {

        this.chatHistory.add(message);
        try {
            BufferedImage image = null;
            byte[] imageByte;
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(message.data);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            String path = Long.toString(Message.r.nextLong());
            File outputfile = new File(path + ".jpg");
            message.data = path + ".jpg";
            ImageIO.write(image, "jpg", outputfile);
            bis.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        if (this.chatMenuView != null) {
            chatMenuView.setContent();
        }
    }

    public void gameRequest(Message message) {
//        this.client.accept(message.from);
    }

    public void enterGame(Message message) {
        System.err.println("game p1 " + message.from + " p2 " + message.to);
    }

    public void scoreBoard(Message message) {
        for (Pair<String, Boolean> x : message.scoreBoard) {
            System.err.print(x.getKey());
            System.err.print(" ");
            System.err.println(x.getValue());
        }
    }

    public void endGame(Message message) {
        System.err.println(message.winner + " " + message.loser);
    }
}
