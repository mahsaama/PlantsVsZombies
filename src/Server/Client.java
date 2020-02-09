package Server;

import Menu.Menu;
import Shop.Shop;
import com.google.gson.internal.bind.util.ISO8601Utils;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public Client(String address, int port){
        try {
            socket = new Socket(address, port);
            System.out.println("connected");
            input = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());

        }catch (UnknownHostException u){
            System.out.println(u);
        }catch (IOException i){
            System.out.println(i);
        }
    }

    public void sendMessage(){
        String line = "";
        while (!line.equals("bye")){
            try {
                line = input.readLine();
                out.writeUTF(line);
            }catch (IOException i){
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args){
        Client client = new Client("127.0.0.1", 8000);
    }

}
