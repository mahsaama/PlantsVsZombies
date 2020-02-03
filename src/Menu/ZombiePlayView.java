package Menu;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ZombiePlayView implements GameModeView{
    private Group zombiePlayRoot;
    private Scene zombiePlayScene;
    private GaussianBlur blur = new GaussianBlur();
    private Image backImage = new Image("pics/frontyard.png");
    private ImageView backImageView = new ImageView(backImage);


    private int height = 700;
    private int width = 1200;

    public ZombiePlayView() {
        zombiePlayRoot = new Group();
        backImageView.setFitHeight(height);
        backImageView.setFitWidth(width);
        zombiePlayScene = new Scene(zombiePlayRoot, width, height);
        zombiePlayRoot.getChildren().add(backImageView);
        attack();

    }

    public Scene getZombiePlayScene(){ return zombiePlayScene; }

    public void attack(){
        Text text = new Text();
        text.setText("OK");
        text.relocate(600, 40);
        text.resize(20,20);

    }




}
