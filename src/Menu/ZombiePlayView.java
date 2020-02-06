package Menu;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button menuButton = new Button();
    private Button buyButton = new Button();

    private Image menuImage = new Image("pics/menu.png");
    private Image buyImage = new Image("pics/buyZombie.png");

    private ImageView menuImageView = new ImageView(menuImage);
    private ImageView buyImageView = new ImageView(buyImage);



    private int height = 700;
    private int width = 1200;

    public ZombiePlayView() {
        zombiePlayRoot = new Group();
        backImageView.setFitHeight(height);
        backImageView.setFitWidth(width);
        zombiePlayScene = new Scene(zombiePlayRoot, width, height);
        zombiePlayRoot.getChildren().add(backImageView);

        menuImageView.relocate(1100,650);
        menuButton.relocate(1100, 650);
        buyImageView.relocate(1100, 50);
        buyButton.relocate(1100, 50);
        zombiePlayRoot.getChildren().add(menuImageView);
        zombiePlayRoot.getChildren().add(menuButton);
        zombiePlayRoot.getChildren().add(buyImageView);
        zombiePlayRoot.getChildren().add(buyButton);


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
