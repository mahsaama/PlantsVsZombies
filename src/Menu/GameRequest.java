package Menu;

import Server.Client;
import Server.Command;
import Server.Message;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class GameRequest {
    Client client;
    Label refresh = new Label();
    public Group root = new Group();
    final public static int HEIGHT = 400;
    final public static int WIDTH = 600;
    public Label users = new Label();
    public Label sb = new Label();
    public TextField textField = new TextField();
    public Label sendRequest = new Label();
    public VBox requests = new VBox();
    public Label player = new Label();

    public GameRequest(Client client) {

        player.setText(client.getUsername());

        this.client = client;
        refresh.setText("Refresh");
        refresh.setOnMouseClicked(event -> refresh());
        refresh.relocate(10, 50);
        Label header = new Label("users");
        header.relocate(100, 10);
        root.getChildren().add(header);
        users.relocate(100, 50);
        header = new Label("score board");
        header.relocate(200, 10);
        root.getChildren().add(header);
        sb.relocate(200, 50);
        sendRequest.setText("SendRequest");
        textField.relocate(400, 50);
        sendRequest.relocate(400, 100);
        header = new Label("requests");
        header.relocate(700, 10);
        root.getChildren().add(header);
        requests.relocate(700, 50);
        root.getChildren().addAll(refresh, users, sb, textField, requests, sendRequest, player);
        sendRequest.setOnMouseClicked(event -> sendRequest());

        new Thread(() -> {
            try {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            refresh();
                            scoreBoadrd();
                            requests();
                        }
                    });
                    Thread.sleep(500);
                }
            } catch (Exception e) {

            }
        }).start();
    }

    public void sendRequest() {
        try {
            Message message = client.gameRequest(textField.getText());
            if(message.command == Command.ERROR){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(message.data);
                alert.showAndWait();
            }
        } catch (Exception e) {

        }
    }

    public void requests() {
        requests.getChildren().clear();
        for (String r : client.getClientHandler().gameRequest) {
            Label label = new Label(r);
            label.setOnMouseClicked(event -> acc(r));
            requests.getChildren().add(label);
        }
    }

    public void acc(String to) {
        try {
            Message message = client.accept(to);
            if (message.command == Command.ERROR){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(message.data);
                alert.showAndWait();
            }else {
                client.getClientHandler().gameRequest.remove(to);
            }
        }catch (Exception e){

        }
    }

    public void scoreBoadrd() {
        Message message = Message.scoreBoardClient();
        try {
            message = this.client.sendAndGetResult(message);
            String board = "";
            HashMap<String, Integer> wins = new HashMap<>();
            HashMap<String, Integer> loses = new HashMap<>();
            String[] users;
            Message m = this.client.sendAndGetResult(Message.showUsers());
            users = m.data.split("\n");
            for (int i = 0; i < users.length; i += 2) {
                wins.put(users[i], 0);
                loses.put(users[i], 0);
            }
            for (Pair<String, Boolean> p : message.scoreBoard) {
                if (p.getValue()) {
                    wins.replace(p.getKey(), wins.get(p.getKey()) + 1);
                } else {
                    loses.replace(p.getKey(), loses.get(p.getKey()) + 1);
                }
            }
            String b = "";
            for (int i = 0; i < users.length; i += 2) {
                b += users[i] + " WINS : " + wins.get(users[i]) + " LOSES : " + loses.get(users[i]) + "\n";
            }
            sb.setText(b);
        } catch (Exception e) {

        }
    }

    public void refresh() {
        Message message = Message.showUsers();
//        System.err.println(message.message_id);
        try {
            message = this.client.sendAndGetResult(message);
            users.setText(message.data);
        } catch (Exception e) {
            System.err.print(Arrays.toString(e.getStackTrace()));
        }

    }

    public Group getRoot() {
        return this.root;
    }
}
