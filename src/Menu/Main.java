package Menu;

import Server.Client;
import Menu.Menu;
import Server.Server;
import Shop.Shop;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import sun.misc.Cleaner;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main extends Application {
    static Client client = new Client();
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Image iconImage = new Image("pics/a.jpg");

        ImageView imageView = new ImageView(iconImage);

        imageView.setX(0);
        imageView.setY(0);
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.fitHeightProperty().bind(stage.heightProperty());
        Group group = new Group();
        group.getChildren().add(imageView);

        primaryStage.setTitle("PlantsVsZombies");
        primaryStage.setScene(new Scene( group,1200,700));
        primaryStage.show();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Menu.loginMenuView();
            }
        });
        thread.start();

    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        client.connect();
        launch(args);

    }

    public static void changeScene(Scene scene) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.setScene(scene);
            }
        });

        /*
        stage.setScene(scene);*/
    }
    public static Client getClient(){ return client;}

}
