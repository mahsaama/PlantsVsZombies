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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;


public class ZombieModeOfGame {
    int turn = 0;
    private PlayGround playGround;
    private int coin = 50;
    private ImageView[][] zombieImageView = new ImageView[5][19];
    private ImageView[][] plantsImageView = new ImageView[5][19];
    private ArrayList<Button> arrayOfButtons = new ArrayList<>();
    private ArrayList<ImageView> arrayOfImageViews = new ArrayList<>();
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
    private Button move = new Button("MOVE");

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
    private MediaPlayer clickedPlayer = new MediaPlayer(mouseClicked);
    private MediaPlayer enteredPlayer = new MediaPlayer(mouseEntered);

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
        coinImageView.relocate(210, 20);
        coinImageView.setFitHeight(50);
        coinImageView.setFitWidth(50);
        coinAmount.relocate(210,20);
        coinAmount.setFont(Font.font("Verdana", FontWeight.BOLD, 5));
        ok.relocate(50, 620);
        done.relocate(90, 620);
        start.relocate(90, 650);
        move.relocate(50, 650);


        zombiePlayRoot.getChildren().add(menuImageView);
        zombiePlayRoot.getChildren().add(menuButton);
        zombiePlayRoot.getChildren().add(buyImageView);
        zombiePlayRoot.getChildren().add(buyButton);
        zombiePlayRoot.getChildren().add(coinImageView);
        zombiePlayRoot.getChildren().add(coinAmount);
        zombiePlayRoot.getChildren().add(ok);
        zombiePlayRoot.getChildren().add(done);
        zombiePlayRoot.getChildren().add(start);
        zombiePlayRoot.getChildren().add(move);
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
            clickedPlayer.play();
            clickedPlayer.seek(Duration.ZERO);
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
            Image plant1 = new Image("pics/Wall-nut.jpg");
            ImageView plant1ImageView = new ImageView(plant1);
            Plant randPlant1 = Shop.makeNewPlantByName("Wall-nut") ;
            randPlant1.setX(0);
            randPlant1.setY(3);
            playGround.getCells()[0][3].getPlantContent().add(randPlant1);
            plant1ImageView.setFitWidth(80);
            plant1ImageView.setFitHeight(40);
            plant1ImageView.relocate(320 + 3* 85, 550 - 4* 120);
            zombiePlayRoot.getChildren().add(plant1ImageView);

            Image plant2 = new Image("pics/Repeater.jpg");
            ImageView plant2ImageView = new ImageView(plant2);
            Plant randPlant2 = Shop.makeNewPlantByName("Repeater") ;
            randPlant2.setX(1);
            randPlant2.setY(3);
            playGround.getCells()[1][3].getPlantContent().add(randPlant2);
            plant2ImageView.setFitWidth(80);
            plant2ImageView.setFitHeight(40);
            plant2ImageView.relocate(320 + 3*85, 550 - 3*120);
            zombiePlayRoot.getChildren().add(plant2ImageView);

            Image plant3 = new Image("pics/Scaredy-shroom.jpg");
            ImageView plant3ImageView = new ImageView(plant3);
            Plant randPlant3 = Shop.makeNewPlantByName("Scaredy-shroom") ;
            randPlant3.setX(1);
            randPlant3.setY(2);
            playGround.getCells()[1][2].getPlantContent().add(randPlant3);
            plant3ImageView.setFitWidth(80);
            plant3ImageView.setFitHeight(40);
            plant3ImageView.relocate(320 + 2* 85, 550 - 3*120);
            zombiePlayRoot.getChildren().add(plant3ImageView);

            Image plant4 = new Image("pics/Tall-nut.jpg");
            ImageView plant4ImageView = new ImageView(plant4);
            Plant randPlant4 = Shop.makeNewPlantByName("Tall-nut") ;
            randPlant4.setX(4);
            randPlant4.setY(2);
            playGround.getCells()[4][2].getPlantContent().add(randPlant4);
            plant4ImageView.setFitWidth(80);
            plant4ImageView.setFitHeight(40);
            plant4ImageView.relocate(320 + 2* 85, 550);
            zombiePlayRoot.getChildren().add(plant4ImageView);


            Image plant5 = new Image("pics/Wall-nut.jpg");
            ImageView plant5ImageView = new ImageView(plant5);
            Plant randPlant5 = Shop.makeNewPlantByName("Wall-nut") ;
            randPlant5.setX(2);
            randPlant5.setY(0);
            playGround.getCells()[2][0].getPlantContent().add(randPlant5);
            plant5ImageView.setFitWidth(80);
            plant5ImageView.setFitHeight(40);
            plant5ImageView.relocate(320 + 85, 550 - 2* 120);
            zombiePlayRoot.getChildren().add(plant5ImageView);

            Image plant6 = new Image("pics/Threepeater.jpg");
            ImageView plant6ImageView = new ImageView(plant6);
            Plant randPlant6 = Shop.makeNewPlantByName("Threepeater") ;
            randPlant6.setX(3);
            randPlant6.setY(1);
            playGround.getCells()[3][1].getPlantContent().add(randPlant6);
            plant6ImageView.setFitWidth(80);
            plant6ImageView.setFitHeight(40);
            plant6ImageView.relocate(320 + 85, 550 - 120);
            zombiePlayRoot.getChildren().add(plant6ImageView);


            Image plant7 = new Image("pics/Wall-nut.jpg");
            ImageView plant7ImageView = new ImageView(plant7);
            Plant randPlant7 = Shop.makeNewPlantByName("Wall-nut") ;
            randPlant7.setX(4);
            randPlant7.setY(3);
            playGround.getCells()[4][3].getPlantContent().add(randPlant7);
            plant7ImageView.setFitWidth(80);
            plant7ImageView.setFitHeight(40);
            plant7ImageView.relocate(320 + 3*85, 550);
            zombiePlayRoot.getChildren().add(plant7ImageView);

            /*for (int i = 0; i < 7; i++) {
                Plant newRandPlant;
                ImageView plantImageView;

                while (true){
                    int randInt1 = rand.nextInt (Shop.getPlantList ( ).size ( ));
                    if(Shop.getPlantList ( ).get (randInt1).getName ( ).equals("LilyPad"))
                        continue;
                    newRandPlant = Shop.makeNewPlantByName(Shop.getPlantList ( ).get (randInt1).getName ( ));
                    String nameOfPlant = Shop.getPlantList ( ).get (randInt1).getName ( );
                    System.out.println(nameOfPlant);
                    Image plantImage = new Image("pics/" + nameOfPlant + ".jpg");
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
                    playGround.getCells()[randInt2][randInt3].getPlantContent().add(newRandPlant);
                    plantsImageView[randInt2][randInt3] = plantImageView;
                    plantImageView.relocate(320 + randInt3 * 85, 550 - (4 - randInt2) * 120);
                    plantImageView.setFitWidth(80);
                    plantImageView.setFitHeight(40);
                    places[randInt2][randInt3] = true;
                    break;
                }
                zombiePlayRoot.getChildren().add(plantImageView);
            }*/
            done.setDisable(true);

        });
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentGame.zombieGame("start", 0, playGround);
                try {
                    start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        move.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int r = 0; r < 5; r++){
                    for(int s = 0; s < 19; s++){
                        if (playGround.getCells ( )[r][s].getZombieContent ( ).size() != 0) {
                            for (Zombie zombie : playGround.getCells()[r][s].getZombieContent()) {
                                if (zombie != null && (zombie.getY ( ) - 1) > 0) {
                                    zombie.setY (zombie.getY ( ) - 1);
                                    try {
                                        reloc(zombieImageView[r][s], 1130 - (turn + 1)* zombie.getSpeed()*30, zombie.getSpeed());
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                    }
                }
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
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;
                        coinAmount.setText("" + coin + "");

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
                        zombieImageView[1][18] = imageView;
                        playGround.getCells()[1][18].getZombieContent().add(tempUser.getZombieHand().get(i));
                        tempUser.getZombieHand().remove(i);
                        arrayOfImageViews.get(i).setOpacity(0);
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;
                        coinAmount.setText("" + coin + "");

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
                        zombieImageView[2][18] = imageView;
                        playGround.getCells()[2][18].getZombieContent().add(tempUser.getZombieHand().get(i));
                        tempUser.getZombieHand().remove(i);
                        arrayOfImageViews.get(i).setOpacity(0);
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;
                        coinAmount.setText("" + coin + "");

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
                        zombieImageView[3][18] = imageView;
                        playGround.getCells()[3][18].getZombieContent().add(tempUser.getZombieHand().get(i));
                        tempUser.getZombieHand().remove(i);
                        arrayOfImageViews.get(i).setOpacity(0);
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;
                        coinAmount.setText("" + coin + "");

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
                        zombieImageView[4][18] = imageView;
                        tempUser.getZombieHand().get(i).setX(4);
                        tempUser.getZombieHand().get(i).setY(18);
                        playGround.getCells()[4 ][18].getZombieContent().add(tempUser.getZombieHand().get(i));
                        tempUser.getZombieHand().remove(i);
                        arrayOfImageViews.get(i).setOpacity(0);
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;
                        coin -= Shop.makeNewZombieByName(button.getText()).getLife() * 10;
                        coinAmount.setText("" + coin + "");

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
    private void start() throws InterruptedException {
        int q;
        while ( (q = checkWinnerForZombie (playGround, coin)) == -1) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 19; j++) {
                    for (Plant plant : playGround.getCells ( )[i][j].getPlantContent ( )) {
                        for (Zombie zombie : playGround.getCells ( )[i][j].getZombieContent ( )){
                            plant.attack (playGround,turn);
                            zombie.attack(playGround);
                        }
                        plant.attack (playGround,turn);
                    }
                    ArrayList<String> killedPlantsInTheTurn = new ArrayList<> ( );
                    for (int m = 0; m < 6; m++) {
                        for (int l = 0; l < 19; l++) {
                            if (playGround.getCells ( )[m][l].getZombieContent ( ).size() != 0) {
                                for (Zombie zombie : playGround.getCells ( )[m][l].getZombieContent ( )) {

                                    ArrayList<String> names = zombie.attack (playGround);
                                    if (!names.get(0).equals ("not")) {
                                        for (int o=0;0<names.size();o++){
                                            killedPlantsInTheTurn.add (names.get(o));
                                        }
                                    }

                                }
                            }
                        }
                    }
                    for (int m = 0; m < killedPlantsInTheTurn.size ( ); m++) {
                        coin += Shop.makeNewPlantByName (killedPlantsInTheTurn.get (m)).getLife ( ) * 10;
                    }
                    killedPlantsInTheTurn.clear ( );


                }
            }
            turn++;
        }
        Text text = new Text();
        if(q == 1){
            text.setText("ZOMBIES WON");
            text.relocate(400, 300);
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            zombiePlayRoot.getChildren().add(text);

        }else if (q== 2){
            text.setText("ZOMBIES LOST");
            text.relocate(400, 300);
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            zombiePlayRoot.getChildren().add(text);

        }else if(q == 0){
            text.setText("Buy new Zombies to fight");
            text.relocate(350, 300);
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            zombiePlayRoot.getChildren().add(text);

        }


    }
    public int checkWinnerForZombie(PlayGround playGround, int coin) {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 19; j++) {
                if (playGround.getCells ( )[i][j].getZombieContent ( ).size() != 0) {
                    for (int k = 0; k < 6; k++) {
                        for (int l = 0; l < 19; l++) {
                            if (playGround.getCells ( )[k][l].getPlantContent ( ).size() != 0)
                                return -1;
                        }
                    }

                    //it means there's no plants left
                    System.out.println("Zombies won!");
                    return 1;
                }
            }

        //it means there's no zombie left
        ArrayList<Zombie> zombies =  Shop.getZombieList();
        zombies.sort(Comparator.comparing(Zombie :: getLife));
        if(coin < (zombies.get(0).getLife() * 10) ){
            System.out.println("Zombies lost");
            return 2;
        }
        //now we should buy new zombies maybe use put item??
        System.out.println("Buy new Zombies to fight");
        return 0;

    }

    public void reloc(ImageView imageView, int destination, int speed) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (imageView.getX() != destination){
                    imageView.setX(imageView.getX() - speed);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();
    }
}
