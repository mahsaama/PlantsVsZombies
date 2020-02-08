package Game;

import Creature.Zombie;
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

import java.util.ArrayList;


public class ZombieModeOfGame {
    private int coin = 50;
    private ArrayList<Button> arrayOfButtons = new ArrayList<>();
    private User tempUser = Menu.getTempUser();

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
    private Button ok = new Button("OK");

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
        ok.relocate(10, 690);

        zombiePlayRoot.getChildren().add(menuImageView);
        zombiePlayRoot.getChildren().add(menuButton);
        zombiePlayRoot.getChildren().add(buyImageView);
        zombiePlayRoot.getChildren().add(buyButton);
        zombiePlayRoot.getChildren().add(ok);

        

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

                zombiePlayRoot.getChildren().add(buttonImageView);
                zombiePlayRoot.getChildren().add(button);


            }catch (Exception e){
                System.out.println(e.getMessage());
            }


        }



        putting(arrayOfButtons);




    }

    private void dragAndDrop(Button button, int i){
            button.setOnDragDetected(event -> {
                Dragboard db = button.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString("TEXT");
                db.setContent(content);
                zombiePlayRoot.getChildren().add(target1);
                zombiePlayRoot.getChildren().add(target2);
                zombiePlayRoot.getChildren().add(target3);
                zombiePlayRoot.getChildren().add(target4);
                zombiePlayRoot.getChildren().add(target5);

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
                        Image image = new Image("pics/" + button.getText() + ".jpg");
                        ImageView imageView = new ImageView(image);
                        imageView.relocate(target1.getX() - tar1*5, target1.getY());
                        zombiePlayRoot.getChildren().add(imageView);
                        tempUser.getZombieHand().get(i).setX(0);
                        tempUser.getZombieHand().get(i).setY(18);

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
                        Image image = new Image("pics/" + button.getText() + ".jpg");
                        ImageView imageView = new ImageView(image);
                        imageView.relocate(target2.getX() - tar2*5, target2.getY());
                        zombiePlayRoot.getChildren().add(imageView);
                        tempUser.getZombieHand().get(i).setX(1);
                        tempUser.getZombieHand().get(i).setY(18);

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
                        Image image = new Image("pics/" + button.getText() + ".jpg");
                        ImageView imageView = new ImageView(image);
                        imageView.relocate(target3.getX() - tar3*5, target3.getY());
                        zombiePlayRoot.getChildren().add(imageView);
                        tempUser.getZombieHand().get(i).setX(2);
                        tempUser.getZombieHand().get(i).setY(18);

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
                        Image image = new Image("pics/" + button.getText() + ".jpg");
                        ImageView imageView = new ImageView(image);
                        imageView.relocate(target4.getX() - tar4*5, target4.getY());
                        zombiePlayRoot.getChildren().add(imageView);
                        tempUser.getZombieHand().get(i).setX(3);
                        tempUser.getZombieHand().get(i).setY(18);

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
                        Image image = new Image("pics/" + button.getText() + ".jpg");
                        ImageView imageView = new ImageView(image);
                        imageView.relocate(target5.getX() - tar5*5, target5.getY());
                        zombiePlayRoot.getChildren().add(imageView);
                        tempUser.getZombieHand().get(i).setX(4);
                        tempUser.getZombieHand().get(i).setY(18);

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
    public void putting(ArrayList<Button> buttons){

        while(true){
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

            ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    return;
                }
            });

        }
    }


}
