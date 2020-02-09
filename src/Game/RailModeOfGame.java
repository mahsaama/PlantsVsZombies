package Game;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.awt.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class RailModeOfGame {
    private Group railPlayRoot;
    private Scene railPlayScene;

    private Image bgImage = new Image("pics/frontyard.png");
    private ImageView bgImageView = new ImageView(bgImage);
    private int height = 700;
    private int width = 1200;

    public Scene getRailPlayScene(){ return railPlayScene; }

    public RailModeOfGame() {
        railPlayRoot = new Group();
        bgImageView.setFitHeight(height);
        bgImageView.setFitWidth(width);
        railPlayScene = new Scene(railPlayRoot, width, height);
        railPlayRoot.getChildren().add(bgImageView);


    }
}
