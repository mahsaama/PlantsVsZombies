package Server;

import javafx.util.Pair;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;

public class ClientHandler {

    HashMap<Integer, Message> results = new HashMap<>();
    Client client;

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
        System.out.println(message.from + " " + message.to + " " + message.data + " " + message.chatId + " " + message.replyTo);
    }

    public void chatPic(Message message) {
        try {
            BufferedImage image = null;
            byte[] imageByte;
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(message.data);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            File outputfile = new File("saved.jpg");
            ImageIO.write(image, "jpg", outputfile);
            bis.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
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
    public void endGame(Message message){
        System.err.println(message.winner + " " + message.loser);
    }
}
