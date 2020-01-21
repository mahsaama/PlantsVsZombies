
import Menu.Menu;
import Shop.Shop;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        Image iconImage = new Image("pics/1671374.jpg");
        ImageView imageView = new ImageView(iconImage);
        imageView.setX(0);
        imageView.setY(0);
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.fitHeightProperty().bind(stage.heightProperty());
        Group group = new Group();
        group.getChildren().add(imageView);

        primaryStage.setTitle("PlantsVsZombies");
        primaryStage.setScene(new Scene( group,1200,600));
        primaryStage.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Shop shop = new Shop ();
                Menu.loginMenu();
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
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
}
