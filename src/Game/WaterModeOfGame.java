package Game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class WaterModeOfGame {

    private Group waterPlayRoot;
    private Scene waterPlayScene;
    private GaussianBlur blur = new GaussianBlur();
    private Image backImage = new Image("pics/frontyard.png");
    private ImageView backImageView = new ImageView(backImage);
    private Button menuButton = new Button("MENU");
    private Button buyButton = new Button("BUY");
    private Button putButton = new Button("PUT");

    private Image menuImage = new Image("pics/menu.png");
    private Image buyImage = new Image("pics/buyZombie.png");
    private Image putImage = new Image("pics/put.jpg");

    private ImageView menuImageView = new ImageView(menuImage);
    private ImageView buyImageView = new ImageView(buyImage);
    private ImageView putImageView = new ImageView(putImage);

    private Media mouseClicked = new Media(getClass().getClassLoader().getResource("audio/Button-SoundBible.com-1420500901.mp3").toExternalForm());
    private Media mouseEntered = new Media(getClass().getClassLoader().getResource("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm());
    MediaPlayer clickedPlayer = new MediaPlayer(mouseClicked);
    MediaPlayer enteredPlayer = new MediaPlayer(mouseEntered);

    private int height = 700;
    private int width = 1200;

    public WaterModeOfGame(){
        waterPlayRoot = new Group();
        backImageView.setFitHeight(height);
        backImageView.setFitWidth(width);
        waterPlayScene = new Scene(waterPlayRoot, width, height);
        waterPlayRoot.getChildren().add(backImageView);

        menuImageView.relocate(1100,650);
        menuButton.relocate(1100, 650);
        buyImageView.relocate(1100, 50);
        buyButton.relocate(1100, 50);
        putImageView.relocate(1050, 50);
        putButton.relocate(1050,50);

        waterPlayRoot.getChildren().add(menuImageView);
        waterPlayRoot.getChildren().add(menuButton);
        waterPlayRoot.getChildren().add(buyImageView);
        waterPlayRoot.getChildren().add(buyButton);
        waterPlayRoot.getChildren().add(putImageView);
        waterPlayRoot.getChildren().add(putButton);

        checkMovements();
    }


    public Scene getWaterPlayScene(){ return waterPlayScene; }

    public void checkMovements(){
        menuButton.setOnMouseEntered(new EventHandler<MouseEvent> () {
            @Override
            public void handle(MouseEvent event) {
                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
            }
        });

        buyButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
            }
        });
        putButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
            }
        });
        menuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);

            }
        });
        buyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }

}
