package Game;

import Menu.Menu;
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



public class ZombieModeOfGame {
    private Game currentGame;
    private Group zombiePlayRoot;
    private Scene zombiePlayScene;
    private GaussianBlur blur = new GaussianBlur();

    private Image back2Image = new Image("pics/bg4.jpg");
    private ImageView back2ImageView = new ImageView(back2Image);


    private Image backImage = new Image("pics/frontyard.png");
    private ImageView backImageView = new ImageView(backImage);
    private Button menuButton = new Button("MENU");
    private Button buyButton = new Button("BUY");
    private Button putButton = new Button("PUT");

    private Text coinAmount = new Text("50");


    private Image menuImage = new Image("pics/menu.png");
    private Image buyImage = new Image("pics/ch.png");
    private Image putImage = new Image("pics/put.jpg");
    private Image coinImage  = new Image("pics/coin.jpg");

    private ImageView menuImageView = new ImageView(menuImage);
    private ImageView buyImageView = new ImageView(buyImage);
    private ImageView putImageView = new ImageView(putImage);
    private ImageView coinImageView = new ImageView(coinImage);

    Text target1 = new Text(1150, 120, "DRAG HERE");
    int tar1 = 0;
    Text target2 = new Text(1150, 240, "DRAG HERE");
    int tar2 = 0;
    Text target3 = new Text(1150, 360, "DRAG HERE");
    int tar3 = 0;
    Text target4 = new Text(1150, 480, "DRAG HERE");
    int tar4 = 0;
    Text target5 = new Text(1150, 600, "DRAG HERE");
    int tar5 = 0;

    private Media mouseClicked = new Media(getClass().getClassLoader().getResource("audio/Button-SoundBible.com-1420500901.mp3").toExternalForm());
    private Media mouseEntered = new Media(getClass().getClassLoader().getResource("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm());
    MediaPlayer clickedPlayer = new MediaPlayer(mouseClicked);
    MediaPlayer enteredPlayer = new MediaPlayer(mouseEntered);

    private int height = 700;
    private int width = 1200;

    public ZombieModeOfGame(){
        zombiePlayRoot = new Group();
        backImageView.setFitHeight(height);
        backImageView.setFitWidth(width);
        zombiePlayScene = new Scene(zombiePlayRoot, width, height);
        zombiePlayRoot.getChildren().add(backImageView);

        menuImageView.relocate(1100,650);
        menuButton.relocate(1100, 650);
        buyImageView.relocate(1100, 50);
        buyButton.relocate(1100, 50);
        putImageView.relocate(1050, 50);
        putButton.relocate(1050,50);

        zombiePlayRoot.getChildren().add(menuImageView);
        zombiePlayRoot.getChildren().add(menuButton);
        zombiePlayRoot.getChildren().add(buyImageView);
        zombiePlayRoot.getChildren().add(buyButton);
        zombiePlayRoot.getChildren().add(putImageView);
        zombiePlayRoot.getChildren().add(putButton);

        checkMovements();
    }


    public Scene getZombiePlayScene(){ return zombiePlayScene; }


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
        putButton.setOnMouseEntered(event -> {
            enteredPlayer.play();
            enteredPlayer.seek(Duration.ZERO);
        });
        menuButton.setOnMouseClicked(event -> {
            clickedPlayer.play();
            clickedPlayer.seek(Duration.ZERO);

        });
        putButton.setOnMouseClicked(event -> {
            back2ImageView.setFitWidth(width);
            back2ImageView.setFitHeight(height);
            zombiePlayRoot.getChildren().add(target1);
            zombiePlayRoot.getChildren().add(target5);
            zombiePlayRoot.getChildren().add(target4);
            zombiePlayRoot.getChildren().add(target3);
            zombiePlayRoot.getChildren().add(target2);

            zombiePlayRoot.getChildren().add(back2ImageView);
            User tempUser = Menu.getTempUser();
            currentGame.zombieGame("put", 0);
            put(tempUser);

        });
    }
    private void put(User user){
        Button back  = new Button("Back");
        back.setPrefSize(80 , 50);
        back.relocate(100, 600);
        zombiePlayRoot.getChildren().add(back);
        EventHandler<ActionEvent> event = e -> back.setOnMouseClicked(event1 -> {
            zombiePlayRoot.getChildren().remove(back);
            zombiePlayRoot.getChildren().remove(back2ImageView);

        });

        back.setOnAction(event);

        for(int i = 0; i < user.getZombieHand().size(); i++){
            Button button = new Button(user.getZombieHand().get(i).getName());
            Image buttonImage = new Image("pics/" + user.getZombieHand().get(i).getName() + ".jpg");
            ImageView buttonImageView = new ImageView(buttonImage);
            buttonImageView.setFitWidth(200);
            buttonImageView.setFitHeight(100);
            buttonImageView.setX(20 + (i % 5) * 240);
            buttonImageView.setY(10 + (i / 5) * 120);
            button.setOpacity(0);
            button.relocate(20 + (i % 5) * 240, 10 + (i / 5) * 120);
            button.setPrefSize(200, 100);

            zombiePlayRoot.getChildren().add(buttonImageView);
            zombiePlayRoot.getChildren().add(button);

            button.setOnMouseClicked(event12 -> {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                dragAndDrop(button);
            });
        }

    }

    private void dragAndDrop(Button button){
        button.setOnDragDetected(event -> {
            Dragboard db = button.startDragAndDrop(TransferMode.COPY);
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
                if(tar1 == 2){
                    target1.setText("");
                }else {
                    Image zombie = new Image("pics/" + button.getText() + ".jpg");
                    ImageView zombieImageView = new ImageView(zombie);
                    Button button1 = new Button(button.getText());
                    zombieImageView.relocate(target1.getX() - tar1*5, target1.getY());
                    button1.relocate(target1.getX(), target1.getY());
                    zombiePlayRoot.getChildren().add(button1);
                    zombiePlayRoot.getChildren().add(zombieImageView);
                    tar1++;
                }
                success = true;
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
                if(tar2 == 2){
                    target2.setText("");
                }else {
                    Image zombie = new Image("pics/" + button.getText() + ".jpg");
                    ImageView zombieImageView = new ImageView(zombie);
                    Button button1 = new Button(button.getText());
                    zombieImageView.relocate(target2.getX() - tar2*5, target2.getY());
                    button1.relocate(target2.getX(), target2.getY());
                    zombiePlayRoot.getChildren().add(button1);
                    zombiePlayRoot.getChildren().add(zombieImageView);
                    tar2++;
                }
                success = true;
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
                if(tar3 == 2){
                    target3.setText("");
                }else {
                    Image zombie = new Image("pics/" + button.getText() + ".jpg");
                    ImageView zombieImageView = new ImageView(zombie);
                    Button button1 = new Button(button.getText());
                    zombieImageView.relocate(target3.getX() - tar3*5, target3.getY());
                    button1.relocate(target3.getX(), target3.getY());
                    zombiePlayRoot.getChildren().add(button1);
                    zombiePlayRoot.getChildren().add(zombieImageView);
                    tar3++;
                }
                success = true;
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
                if(tar4 == 2){
                    target4.setText("");
                }else {
                    Image zombie = new Image("pics/" + button.getText() + ".jpg");
                    ImageView zombieImageView = new ImageView(zombie);
                    Button button1 = new Button(button.getText());
                    zombieImageView.relocate(target4.getX() - tar4*5, target4.getY());
                    button1.relocate(target4.getX(), target4.getY());
                    zombiePlayRoot.getChildren().add(button1);
                    zombiePlayRoot.getChildren().add(zombieImageView);
                    tar4++;
                }
                success = true;
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
                if(tar5 == 2){
                    target5.setText("");
                }else{
                    Image zombie = new Image("pics/" + button.getText() + ".jpg");
                    ImageView zombieImageView = new ImageView(zombie);
                    Button button1 = new Button(button.getText());
                    zombieImageView.relocate(target5.getX() - tar5*5, target5.getY());
                    button1.relocate(target5.getX(), target5.getY());
                    zombiePlayRoot.getChildren().add(button1);
                    zombiePlayRoot.getChildren().add(zombieImageView);
                    tar5++;
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
