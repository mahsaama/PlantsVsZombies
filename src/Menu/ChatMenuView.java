package Menu;

import Server.Message;
import Server.Command;
import Server.Client;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ChatMenuView {
    final public static int HEIGHT = 400;
    final public static int WIDTH = 600;
    public Client client;
    public Group root = new Group();
    private VBox content = new VBox();
    private Label send = new Label();
    private TextField textField = new TextField();
    private TextField reply = new TextField();
    private boolean replied = false;
    private String other;
    private Label pic = new Label();
    Task<Void> task;
    Thread thread;
    Stage stage;

    public ChatMenuView(Stage stage, Client client, String other) {
        this.other = other;
        this.stage = stage;
        this.client = client;

        pic.setText("SEND PICTURE");
        pic.setOnMouseClicked(event -> sendPic());

        send.setText("SEND");
        send.setOnMouseClicked(event -> sendMessage());
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) sendMessage();
        });

        textField.relocate(20, 300 + HEIGHT / 2);
        textField.setMinSize(WIDTH - 90, 30);
        textField.setMaxSize(WIDTH - 90, 30);


        reply.relocate(20, 330 + HEIGHT / 2);
        reply.setMinSize(WIDTH - 60, 30);
        reply.setMaxSize(WIDTH - 60, 30);
        send.relocate(400 + WIDTH / 2 - 60, 300 + HEIGHT / 2);
        pic.relocate(400 + WIDTH / 2 - 60, 330 + HEIGHT / 2);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(content);

        scrollPane.setMaxHeight(HEIGHT);
        scrollPane.setMaxWidth(WIDTH);
        scrollPane.setMinHeight(HEIGHT);
        scrollPane.setMinWidth(WIDTH);
        reply.setDisable(true);

        scrollPane.relocate(20, 10);
//        scrollPane.translateXProperty().bind(scrollPane.widthProperty().divide(2).negate());
//        scrollPane.translateYProperty().bind(scrollPane.heightProperty().divide(2).negate());
        content.setSpacing(20);
        scrollPane.vvalueProperty().bind(content.heightProperty());
        clearReply();
        root.getChildren().addAll(scrollPane, pic, send, textField, reply);
        setContent();
    }


    private void sendPic() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Pic");
        File file = fileChooser.showOpenDialog(stage);
        try {
            client.sendPic(other, file.getAbsolutePath());
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void sendMessage() {
        if (!replied) {
            client.sendChat(other, textField.getText());
        } else {
            client.reply(other, textField.getText(), reply.getText());

        }
        clearReply();
        textField.setText("");
    }


    private void clearReply() {
        replied = false;
        reply.setText("");
        reply.setVisible(false);
    }


    public void setContent() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                content.getChildren().clear();

            }
        });
        System.out.println(this.client.getClientHandler().chatHistory.size());
        for (int i = 0; i < this.client.getClientHandler().chatHistory.size(); i++) {
            Message message = this.client.getClientHandler().chatHistory.get(i);
            System.err.println(i);

            if (!(message.from.equals(other)) && !(message.to.equals(other)))
                continue;

            System.err.println(i);

            VBox messageVBox = new VBox();
            messageVBox.setSpacing(20);
            HBox messageHBox = new HBox();
            Label sender = new Label(message.from);
            Label text = new Label();

            if (message.command == Command.PIC) {
                try {
                    Image image = new Image(new FileInputStream(message.data));
                    ImageView imageView = new ImageView(image);
                    text.setGraphic(imageView);
                } catch (Exception e) {

                }
            } else {
                text.setText(message.data);
            }

            messageHBox.setSpacing(25);
            messageHBox.setMinWidth(WIDTH);
            messageHBox.setMaxWidth(WIDTH);

            String name = message.from;


            messageHBox.getChildren().addAll(sender, text);
            text.setOnMouseClicked(mouseEvent -> addReply(text));
            if (message.replyTo != null) {
                Label replyLabel = new Label(message.replyTo);
                messageVBox.getChildren().add(replyLabel);
            }
            messageHBox.setAlignment(Pos.CENTER_LEFT);
            messageVBox.getChildren().add(messageHBox);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    content.getChildren().add(messageVBox);
                }
            });

        }
    }

    private void addReply(Label text) {
        reply.setVisible(true);
        replied = true;
        reply.setText(text.getText());
    }

    public Group getRoot() {
        return root;
    }
}
