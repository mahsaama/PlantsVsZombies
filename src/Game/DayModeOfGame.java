package Game;

import Creature.Plant;
import Creature.Zombie;
import Menu.Menu;
import Shop.Shop;
import User.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.security.SecureRandom;
import java.util.ArrayList;


public class DayModeOfGame {
    private int coin = 50;
    private ArrayList<Button> arrayOfButtons = new ArrayList<>();
    private ArrayList<ImageView> arrayOfImageViews = new ArrayList<>();
    private User tempUser = Menu.getTempUser();
    private Game currentGame;
    private Group dayPlayRoot;
    private Scene dayPlayScene;
    private ArrayList<Zombie> gameZombies = new ArrayList<> ();
    private ArrayList<ImageView> zombieImageViews = new ArrayList<> ();
    private ArrayList<Image>  zombieImages = new ArrayList<> ();
    private Image back2Image = new Image("pics/bg4.jpg");

    private Image backImage = new Image("pics/frontyard.png");
    private ImageView backImageView = new ImageView(backImage);
    private Button ok = new Button("PUT");
    private Button done = new Button("DONE");

    private Text coinAmount = new Text("50");

    private Image putImage = new Image("pics/put.jpg");
    private Image coinImage  = new Image("pics/coin.jpg");

    private ImageView putImageView = new ImageView(putImage);
    private ImageView coinImageView = new ImageView(coinImage);

    Text target1 = new Text(300, 90, "DRAG HERE");
    int tar1 = 0;
    Text target2 = new Text(300, 210, "DRAG HERE");
    int tar2 = 0;
    Text target3 = new Text(300, 320, "DRAG HERE");
    int tar3 = 0;
    Text target4 = new Text(300, 430, "DRAG HERE");
    int tar4 = 0;
    Text target5 = new Text(300, 530, "DRAG HERE");
    int tar5 = 0;

    private Media mouseClicked = new Media(getClass().getClassLoader().getResource("audio/Button-SoundBible.com-1420500901.mp3").toExternalForm());
    private Media mouseEntered = new Media(getClass().getClassLoader().getResource("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm());
    MediaPlayer clickedPlayer = new MediaPlayer(mouseClicked);
    MediaPlayer enteredPlayer = new MediaPlayer(mouseEntered);

    private int height = 700;
    private int width = 1200;

    public DayModeOfGame(){
        dayPlayRoot = new Group();
        backImageView.setFitHeight(height);
        backImageView.setFitWidth(width);
        dayPlayScene = new Scene(dayPlayRoot, width, height);
        dayPlayRoot.getChildren().add(backImageView);

        ok.relocate(50, 620);
        done.relocate(90, 620);
//320
        dayPlayRoot.getChildren().add(ok);
        dayPlayRoot.getChildren().add(done);

        checkMovements();
    }


    public Scene getDayPlayScene(){ return dayPlayScene; }


    public void getTheGame(Game game){ currentGame = game; }

    private void checkMovements(){


        for(int i = 0; i < tempUser.getPlantHand ().size(); i++){
            try {
                Button button = new Button(tempUser.getPlantHand ().get(i).getName());
                Image buttonImage = new Image("pics/cards/" +tempUser.getPlantHand ().get(i).getName() + ".jpg");
                ImageView buttonImageView = new ImageView(buttonImage);
                buttonImageView.setFitWidth(100);
                buttonImageView.setFitHeight(60);
                buttonImageView.setX(10);
                buttonImageView.setY(10 + 80*i);
                button.setOpacity(0);
                button.relocate(10, 10 + 80*i);
                button.setPrefSize(100, 60);
                arrayOfButtons.add(button);
                arrayOfImageViews.add(buttonImageView);
                dayPlayRoot.getChildren().add(buttonImageView);
                dayPlayRoot.getChildren().add(button);


            }catch (Exception e){
                System.out.println(e.getMessage());
            }


        }

        ok.setOnMouseClicked(event -> {
            dayPlayRoot.getChildren().add(target1);
            dayPlayRoot.getChildren().add(target2);
            dayPlayRoot.getChildren().add(target3);
            dayPlayRoot.getChildren().add(target4);
            dayPlayRoot.getChildren().add(target5);
        });

        done.setOnMouseClicked(event -> {
            SecureRandom rand = new SecureRandom ( );

            for(int j = 0 ; j < zombieImageViews.size () ; j ++){
                System.out.println (gameZombies.get (j).getX () +" " + gameZombies.get (j).getY () );
                zombieImageViews.get (j).relocate ( zombieImageViews.get(j).getX () - gameZombies.get (j).getSpeed (),zombieImageViews.get (j)
                        .getY ());
                gameZombies.get (j).setX (gameZombies.get (j).getX () - gameZombies.get (j).getSpeed () );
                System.out.println (gameZombies.get (j).getX () +" " + gameZombies.get (j).getY () );
            }

            for (int i = 0; i < 7; i++) {
                int randInt1 = rand.nextInt (Shop.getZombieList ( ).size ( ));
                Zombie newRandZombie = Shop.makeNewZombieByName (Shop.getZombieList ( ).get (randInt1).getName ( ));
                gameZombies.add (newRandZombie);
                Image zombieImage = new Image("pics/" + Shop.getZombieList ( ).get (randInt1).getName ( ) + ".jpg");
                ImageView zombieImageView = new ImageView(zombieImage);
                int randInt2 = rand.nextInt(5);
                int randInt3 = rand.nextInt(3);
                newRandZombie.setX(1050);
                newRandZombie.setY(randInt3);
                zombieImageView.relocate( 1050  , 590 - (4 - randInt2) * 120);
                zombieImageView.setFitWidth(80);
                zombieImageView.setFitHeight(40);
                zombieImageViews.add (zombieImageView);
                zombieImages.add ( zombieImage);
                //TODO
                //You can use a 2D array for it to detemine with boolean amount if there's a plant in it or not
                dayPlayRoot.getChildren().add(zombieImageView);
            }

        });



        arrayOfButtons.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragAndDrop(arrayOfButtons.get(0), 0);
            }
        });
        arrayOfButtons.get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragAndDrop(arrayOfButtons.get(1), 1);

            }
        });
        arrayOfButtons.get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragAndDrop(arrayOfButtons.get(2), 2);
            }
        });
        arrayOfButtons.get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragAndDrop(arrayOfButtons.get(3), 3);
            }
        });
        arrayOfButtons.get(4).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragAndDrop(arrayOfButtons.get(4), 4);
            }
        });
        arrayOfButtons.get(5).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragAndDrop(arrayOfButtons.get(5), 5);
            }
        });
        arrayOfButtons.get(6).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragAndDrop(arrayOfButtons.get(6), 6);
            }
        });

    }

    private void dragAndDrop(Button button, int i){
        button.setOnDragDetected(event -> {
            Dragboard db = button.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("TEXT");
            db.setContent(content);
            event.consume();
        });
        target1.setOnDragOver(event -> {
            if (event.getGestureSource() != target1 &&
                    event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });
        target2.setOnDragOver(event -> {
            if (event.getGestureSource() != target2 &&
                    event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        target3.setOnDragOver(event -> {
            if (event.getGestureSource() != target3 &&
                    event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        target4.setOnDragOver(event -> {
            if (event.getGestureSource() != target4 &&
                    event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        target5.setOnDragOver(event -> {
            if (event.getGestureSource() != target5 &&
                    event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        target1.setOnDragEntered(event -> {
            /* show to the user that it is an actual gesture target */
            if (event.getGestureSource() != target1 &&
                    event.getDragboard().hasString()) {
                target1.setFill(Color.GREEN);
            }
            event.consume();
        });
        target2.setOnDragEntered(event -> {
            /* show to the user that it is an actual gesture target */
            if (event.getGestureSource() != target2 &&
                    event.getDragboard().hasString()) {
                target2.setFill(Color.GREEN);
            }
            event.consume();
        });
        target3.setOnDragEntered(event -> {
            /* show to the user that it is an actual gesture target */
            if (event.getGestureSource() != target3 &&
                    event.getDragboard().hasString()) {
                target3.setFill(Color.GREEN);
            }

            event.consume();
        });
        target4.setOnDragEntered(event -> {
            /* show to the user that it is an actual gesture target */
            if (event.getGestureSource() != target4 &&
                    event.getDragboard().hasString()) {
                target4.setFill(Color.GREEN);
            }

            event.consume();
        });
        target5.setOnDragEntered(event -> {
            /* show to the user that it is an actual gesture target */
            if (event.getGestureSource() != target5 &&
                    event.getDragboard().hasString()) {
                target5.setFill(Color.GREEN);
            }

            event.consume();
        });
        target1.setOnDragExited(event -> {
            /* mouse moved away, remove the graphical cues */
            target1.setFill(Color.BLACK);

            event.consume();
        });
        target2.setOnDragExited(event -> {
            /* mouse moved away, remove the graphical cues */
            target2.setFill(Color.BLACK);

            event.consume();
        });
        target3.setOnDragExited(event -> {
            /* mouse moved away, remove the graphical cues */
            target3.setFill(Color.BLACK);

            event.consume();
        });
        target4.setOnDragExited(event -> {
            /* mouse moved away, remove the graphical cues */
            target4.setFill(Color.BLACK);

            event.consume();
        });
        target5.setOnDragExited(event -> {
            /* mouse moved away, remove the graphical cues */
            target5.setFill(Color.BLACK);

            event.consume();
        });
        target1.setOnDragDropped(event -> {
            /* if there is a string data on dragboard, read it and use it */
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                if(Shop.makeNewPlantByName (button.getText()).getLife() * 10 <= coin){
                    Image image = new Image("pics/" + button.getText() + ".jpg");
                    ImageView imageView = new ImageView(image);
                    imageView.relocate(target1.getX() - tar1*10 - 30, target1.getY() );
                    imageView.setFitHeight(80);
                    imageView.setFitWidth(120);
                    dayPlayRoot.getChildren().add(imageView);
                    tempUser.getPlantHand ().get(i).setX(0);
                    tempUser.getPlantHand().get(i).setY(18);
                    tempUser.getPlantHand().remove(i);
                    arrayOfImageViews.get(i).setOpacity(0);
                    coin -= Shop.makeNewPlantByName (button.getText()).getLife() * 10;

                    tar1++;
                    if(tar1 == 2){
                        target1.setText("");
                    }

                    success = true;
                }

            }
            /* let the source know whether the string was successfully
             * transferred and used */
            event.setDropCompleted(success);

            event.consume();
        });
        target2.setOnDragDropped(event -> {
            /* if there is a string data on dragboard, read it and use it */
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                if(Shop.makeNewPlantByName (button.getText()).getLife() * 10 <= coin){
                    Image image = new Image("pics/" + button.getText() + ".jpg");
                    ImageView imageView = new ImageView(image);
                    imageView.relocate(target2.getX() - tar2*10 - 30, target2.getY() );
                    imageView.setFitHeight(80);
                    imageView.setFitWidth(120);
                    dayPlayRoot.getChildren().add(imageView);
                    tempUser.getPlantHand().get(i).setX(1);
                    tempUser.getPlantHand().get(i).setY(18);
                    tempUser.getPlantHand().remove(i);
                    arrayOfImageViews.get(i).setOpacity(0);
                    coin -= Shop.makeNewPlantByName (button.getText()).getLife() * 10;

                    tar2++;
                    if(tar2 == 2){
                        target2.setText("");
                    }

                    success = true;
                }

            }
            /* let the source know whether the string was successfully
             * transferred and used */
            event.setDropCompleted(success);

            event.consume();
        });
        target3.setOnDragDropped(event -> {
            /* if there is a string data on dragboard, read it and use it */
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                if(Shop.makeNewPlantByName (button.getText()).getLife() * 10 <= coin){
                    Image image = new Image("pics/" + button.getText() + ".jpg");
                    ImageView imageView = new ImageView(image);
                    imageView.relocate(target3.getX() - tar3*10 - 30, target3.getY());
                    imageView.setFitHeight(80);
                    imageView.setFitWidth(120);
                    dayPlayRoot.getChildren().add(imageView);
                    tempUser.getPlantHand ().get(i).setX(2);
                    tempUser.getPlantHand().get(i).setY(18);
                    tempUser.getPlantHand().remove(i);
                    arrayOfImageViews.get(i).setOpacity(0);
                    coin -= Shop.makeNewPlantByName (button.getText()).getLife() * 10;

                    tar3++;
                    if(tar3 == 2){
                        target3.setText("");
                    }

                    success = true;
                }

            }
            /* let the source know whether the string was successfully
             * transferred and used */
            event.setDropCompleted(success);

            event.consume();
        });
        target4.setOnDragDropped(event -> {
            /* if there is a string data on dragboard, read it and use it */
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                if(Shop.makeNewPlantByName (button.getText()).getLife() * 10 <= coin){
                    Image image = new Image("pics/" + button.getText() + ".jpg");
                    ImageView imageView = new ImageView(image);
                    imageView.relocate(target4.getX() - tar4*10- 30, target4.getY());
                    imageView.setFitHeight(80);
                    imageView.setFitWidth(120);
                    dayPlayRoot.getChildren().add(imageView);
                    tempUser.getPlantHand().get(i).setX(3);
                    tempUser.getPlantHand().get(i).setY(18);
                    tempUser.getPlantHand().remove(i);
                    arrayOfImageViews.get(i).setOpacity(0);
                    coin -= Shop.makeNewPlantByName (button.getText()).getLife() * 10;

                    tar4++;
                    if(tar4 == 2){
                        target4.setText("");
                    }

                    success = true;
                }

            }
            /* let the source know whether the string was successfully
             * transferred and used */
            event.setDropCompleted(success);

            event.consume();
        });
        target5.setOnDragDropped(event -> {
            /* if there is a string data on dragboard, read it and use it */
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {


                Image image = new Image("pics/" + button.getText() + ".jpg");
                ImageView imageView = new ImageView(image);
                imageView.relocate(target5.getX() - tar5*10 - 30, target5.getY());
                imageView.setFitHeight(80);
                imageView.setFitWidth(120);
                dayPlayRoot.getChildren().add(imageView);
                tempUser.getPlantHand().get(i).setX(4);
                tempUser.getPlantHand().get(i).setY(18);
                tempUser.getPlantHand().remove(i);
                arrayOfImageViews.get(i).setOpacity(0);
                coin -= Shop.makeNewPlantByName (button.getText()).getLife() * 10;

                tar5++;
                if(tar5 == 2){
                    target5.setText("");
                }

                success = true;
            }
            /* let the source know whether the string was successfully
             * transferred and used */
            event.setDropCompleted(success);

            event.consume();
        });

        button.setOnDragDone(Event::consume);

    }

}
