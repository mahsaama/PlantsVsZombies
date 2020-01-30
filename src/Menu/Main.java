package Menu;
import Menu.Menu;
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
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<Stage> stages = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {

        stages.add(primaryStage);

        Image iconImage = new Image("pics/a.jpg");
        ImageView imageView = new ImageView(iconImage);
        imageView.setX(0);
        imageView.setY(0);
        imageView.fitWidthProperty().bind(stages.get(0).widthProperty());
        imageView.fitHeightProperty().bind(stages.get(0).heightProperty());
        Group group = new Group();
        group.getChildren().add(imageView);

        primaryStage.setTitle("PlantsVsZombies");
        primaryStage.setScene(new Scene( group,1200,700));
        primaryStage.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Shop shop = new Shop ();
                Menu.loginMenuView();
            }
        });
        thread.start();
//        Image menuImage = new Image("pics/menu.jpg");
//        ImageView menuView = new ImageView(menuImage);
//        menuView.setFitWidth(30);
//        menuView.setFitHeight(30);
//        Button button = new Button("", menuView);
//        button.setMaxSize(30, 30);
//
//        button.relocate(600,610);
//        group.getChildren().add(button);
//        button.setOnMouseClicked(event -> thread.start());

    }

    public static void main(String[] args) {
        launch(args);
    }
    public static void changeScene(Scene scene) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stages.get(0).setScene(scene);
            }
        });
        /*
        stage.setScene(scene);*/
    }

}
