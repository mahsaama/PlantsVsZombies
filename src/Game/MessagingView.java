package Game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MessagingView {
    private Group messageRoot;
    private Scene messageScene;

    private Image bgImage = new Image("pics/bg4.png");
    private ImageView bgImageView = new ImageView(bgImage);
    private int height = 700;
    private int width = 1200;

    public Scene getMessageScene(){ return messageScene; }

    public MessagingView() {
        messageRoot = new Group();
        bgImageView.setFitHeight(height);
        bgImageView.setFitWidth(width);
        messageScene = new Scene(messageRoot, width, height);
        messageRoot.getChildren().add(bgImageView);

    }
}
