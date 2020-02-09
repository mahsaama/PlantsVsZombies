package Game;

import Creature.Plant;
import Shop.Shop;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class RailModeOfGame extends Game{
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
        int zombieNextTurn = 0;
        ArrayList<Plant> cards = new ArrayList<> ( );
        new Thread(new Runnable() {
            @Override
            public void run() {
                SecureRandom rand = new SecureRandom ( );
                int plantNextTurn = 0;
                int i=0;
                if (turn == plantNextTurn) {
                    randomCard(cards);
                    Image image = new Image(cards.get(cards.size()-1).getName());
                    int randomNum = rand.nextInt (3) + 2;
                    plantNextTurn = turn + randomNum;
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(70);
                    imageView.relocate(10,i*50 +10);
                    railPlayRoot.getChildren().add(imageView);
                    i++;

                }
            }
        }).start();

    }
}
