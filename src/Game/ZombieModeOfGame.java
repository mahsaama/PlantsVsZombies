package Game;

import Creature.Plant;
import Creature.Zombie;
import Map.PlayGround;
import Menu.Menu;
import Game.Game;
import Shop.Shop;
import User.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
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


public class ZombieModeOfGame {
    private PlayGround playGround;
    private int coin = 50;
    private ArrayList<Button> arrayOfButtons = new ArrayList<>();
    private ArrayList<ImageView> arrayOfImageViews = new ArrayList<>();
    private ArrayList<ImageView> zombiesImageView = new ArrayList<>();
    private User tempUser = Menu.getTempUser();

    private Game currentGame;
    private Group zombiePlayRoot;
    private Scene zombiePlayScene;

    private Image back2Image = new Image("pics/bg4.jpg");


    private Image backImage = new Image("pics/frontyard.png");
    private ImageView backImageView = new ImageView(backImage);
    private Button menuButton = new Button("MENU");
    private Button buyButton = new Button("BUY");
    private Button ok = new Button("PUT");
    private Button done = new Button("DONE");
    private Button start = new Button("START");

    private Text coinAmount = new Text("50");


    private Image menuImage = new Image("pics/menu.png");
    private Image buyImage = new Image("pics/ch.png");
    private Image putImage = new Image("pics/put.jpg");
    private Image coinImage  = new Image("pics/coin.jpg");

    private ImageView menuImageView = new ImageView(menuImage);
    private ImageView buyImageView = new ImageView(buyImage);
    private ImageView putImageView = new ImageView(putImage);
    private ImageView coinImageView = new ImageView(coinImage);

    Text target1 = new Text(1130, 90, "DRAG HERE");
    int tar1 = 0;
    Text target2 = new Text(1130, 210, "DRAG HERE");
    int tar2 = 0;
    Text target3 = new Text(1130, 320, "DRAG HERE");
    int tar3 = 0;
    Text target4 = new Text(1130, 430, "DRAG HERE");
    int tar4 = 0;
    Text target5 = new Text(1130, 530, "DRAG HERE");
    int tar5 = 0;

    private Media mouseClicked = new Media(getClass().getClassLoader().getResource("audio/Button-SoundBible.com-1420500901.mp3").toExternalForm());
    private Media mouseEntered = new Media(getClass().getClassLoader().getResource("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm());
    MediaPlayer clickedPlayer = new MediaPlayer(mouseClicked);
    MediaPlayer enteredPlayer = new MediaPlayer(mouseEntered);

    private int height = 700;
    private int width = 1200;

    public ZombieModeOfGame(){
        playGround = new PlayGround();
        zombiePlayRoot = new Group();
        backImageView.setFitHeight(height);
        backImageView.setFitWidth(width);
        zombiePlayScene = new Scene(zombiePlayRoot, width, height);
        zombiePlayRoot.getChildren().add(backImageView);


        menuImageView.relocate(1100,650);
        menuButton.relocate(1100, 650);
        buyImageView.relocate(100, 20);
        buyButton.relocate(100, 20);
        ok.relocate(50, 620);
        done.relocate(90, 620);
        start.relocate(90, 650);

        zombiePlayRoot.getChildren().add(menuImageView);
        zombiePlayRoot.getChildren().add(menuButton);
        zombiePlayRoot.getChildren().add(buyImageView);
        zombiePlayRoot.getChildren().add(buyButton);
        zombiePlayRoot.getChildren().add(ok);
        zombiePlayRoot.getChildren().add(done);
        buyButton.setOpacity(0);
        



        checkMovements();
    }


    public Scene getZombiePlayScene(){ return zombiePlayScene; }

    public void setCoin(int amount){ this.coin += amount; }


    public void getTheGame(Game game){ currentGame = game; }

    private void checkMovements(){
        menuButton.setOnMouseEntered(event -> {
            enteredPlayer.play();
            enteredPlayer.seek(Duration.ZERO);
        });

        buyButton.setOnMouseEntered(event -> {
            enteredPlayer.play();
            enteredPlayer.seek(Duration.ZERO);
        });
        menuButton.setOnMouseClicked(event -> {
            clickedPlayer.play();
            clickedPlayer.seek(Duration.ZERO);

        });

        for(int i = 0; i < tempUser.getZombieHand().size(); i++){
            try {
                Button button = new Button(tempUser.getZombieHand().get(i).getName());
                Image buttonImage = new Image("pics/" + tempUser.getZombieHand().get(i).getName() + ".jpg");
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

                zombiePlayRoot.getChildren().add(buttonImageView);
                zombiePlayRoot.getChildren().add(button);


            }catch (Exception e){
                System.out.println(e.getMessage());
            }


        }

        ok.setOnMouseClicked(event -> {
            zombiePlayRoot.getChildren().add(target1);
            zombiePlayRoot.getChildren().add(target2);
            zombiePlayRoot.getChildren().add(target3);
            zombiePlayRoot.getChildren().add(target4);
            zombiePlayRoot.getChildren().add(target5);
        });

        done.setOnMouseClicked(event -> {
            target1.setOpacity(0);
            target2.setOpacity(0);
            target3.setOpacity(0);
            target4.setOpacity(0);
            target5.setOpacity(0);
            SecureRandom rand = new SecureRandom ( );
            boolean[][] places = new boolean[5][3];
            for(int i = 0 ; i <  5; i++)
                for(int j = 0; j < 3; j++)
                    places[i][j] = false;

            for (int i = 0; i < 7; i++) {
                Plant newRandPlant;
                ImageView plantImageView;

                while (true){
                    int randInt1 = rand.nextInt (Shop.getPlantList ( ).size ( ));
                    if(Shop.getPlantList ( ).get (randInt1).getName ( ).equals("LilyPad"))
                        continue;
                    newRandPlant = Shop.makeNewPlantByName(Shop.getPlantList ( ).get (randInt1).getName ( ));
                    Image plantImage = new Image("pics/" + Shop.getPlantList ( ).get (randInt1).getName ( ) + ".jpg");
                    plantImageView = new ImageView(plantImage);
                    break;
                }

                while(true){
                    int randInt2 = rand.nextInt(5);
                    int randInt3 = rand.nextInt(3);
                    if(places[randInt2][randInt3] == true)
                        continue;

                    newRandPlant.setX(randInt2);
                    newRandPlant.setY(randInt3);
                    plantImageView.relocate(320 + randInt3 * 85, 550 - (4 - randInt2) * 120);
                    plantImageView.setFitWidth(80);
                    plantImageView.setFitHeight(40);
                    places[randInt2][randInt3] = true;
                    break;
                }
                zombiePlayRoot.getChildren().add(plantImageView);
            }
            done.setDisable(true);

        });
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentGame.zombieGame("start", 0, playGround);
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
                    if(Shop.makeNewZombieByName(button.getText()).getLife() * 10 <= coin){
                        Image image = new Image("pics/" + button.getText() + ".jpg");
                        ImageView imageView = new ImageView(image);
                        imageView.relocate(target1.getX() - tar1*10 - 30, target1.getY() );
                        imageView.setFitHeight(80);
                        imageView.setFitWidth(120);
                        zombiePlayRoot.getChildren().add(imageView);
                        tempUser.getZombieHand().get(i).setX(0);
                        tempUser.getZombieHand().get(i).setY(18);
                        playGround.getCells()[0][18].getZombieContent().add(tempUser.getZombieHand().get(i));

                        tempUser.getZombieHand().remove(i);
                        arrayOfImageViews.get(i).setOpacity(0);
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;

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
                    if(Shop.makeNewZombieByName(button.getText()).getLife() * 10 <= coin){
                        Image image = new Image("pics/" + button.getText() + ".jpg");
                        ImageView imageView = new ImageView(image);
                        imageView.relocate(target2.getX() - tar2*10 - 30, target2.getY() );
                        imageView.setFitHeight(80);
                        imageView.setFitWidth(120);
                        zombiePlayRoot.getChildren().add(imageView);
                        tempUser.getZombieHand().get(i).setX(1);
                        tempUser.getZombieHand().get(i).setY(18);
                        playGround.getCells()[1][18].getZombieContent().add(tempUser.getZombieHand().get(i));
                        tempUser.getZombieHand().remove(i);
                        arrayOfImageViews.get(i).setOpacity(0);
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;

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
                    if(Shop.makeNewZombieByName(button.getText()).getLife() * 10 <= coin){
                        Image image = new Image("pics/" + button.getText() + ".jpg");
                        ImageView imageView = new ImageView(image);
                        imageView.relocate(target3.getX() - tar3*10 - 30, target3.getY());
                        imageView.setFitHeight(80);
                        imageView.setFitWidth(120);
                        zombiePlayRoot.getChildren().add(imageView);
                        tempUser.getZombieHand().get(i).setX(2);
                        tempUser.getZombieHand().get(i).setY(18);
                        playGround.getCells()[2][18].getZombieContent().add(tempUser.getZombieHand().get(i));
                        tempUser.getZombieHand().remove(i);
                        arrayOfImageViews.get(i).setOpacity(0);
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;

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
                    if(Shop.makeNewZombieByName(button.getText()).getLife() * 10 <= coin){
                        Image image = new Image("pics/" + button.getText() + ".jpg");
                        ImageView imageView = new ImageView(image);
                        imageView.relocate(target4.getX() - tar4*10- 30, target4.getY());
                        imageView.setFitHeight(80);
                        imageView.setFitWidth(120);
                        zombiePlayRoot.getChildren().add(imageView);
                        tempUser.getZombieHand().get(i).setX(3);
                        tempUser.getZombieHand().get(i).setY(18);
                        playGround.getCells()[3][18].getZombieContent().add(tempUser.getZombieHand().get(i));
                        tempUser.getZombieHand().remove(i);
                        arrayOfImageViews.get(i).setOpacity(0);
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;

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
                        zombiePlayRoot.getChildren().add(imageView);
                        zombiesImageView.add(imageView);
                        tempUser.getZombieHand().get(i).setX(4);
                        tempUser.getZombieHand().get(i).setY(18);
                        playGround.getCells()[4 ][18].getZombieContent().add(tempUser.getZombieHand().get(i));
                        tempUser.getZombieHand().remove(i);
                        arrayOfImageViews.get(i).setOpacity(0);
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;

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
