package Menu;

import Game.DayModeOfGame;
import Game.Game;
import Game.ZombieModeOfGame;
import Game.RailModeOfGame;
import Map.PlayGround;
import Shop.Shop;
import User.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CollectionMenuView {
    private Group collectionMenuRoot;
    private Scene collectionMenuScene;
    private GaussianBlur blur = new GaussianBlur();

    private Image buttonImage = new Image("pics/beforeclick.webp");
    private Image buttonImage1 = new Image("pics/afterclick.webp");
    private Image mainMenuBackgroundImage = new Image("pics/bg3.jpg");

    private Button playButton = new Button("play");
    private Button showHandButton = new Button("ShowHand");
    private Button showCollectionButton = new Button("ShowCollection");
    private Button selectButton = new Button("Select");
    private Button removeButton = new Button("Remove");
    private Button exitButton = new Button("Exit");
    private Button helpButton = new Button("Help");

    private Label playLabel = new Label("Play");
    private Label showHandLabel = new Label("Show hand");
    private Label showCollectionLabel = new Label("ShowCollection");
    private Label selectLabel = new Label("Select");
    private Label removeLabel = new Label("Remove");
    private Label exitLabel = new Label("Exit");
    private Label helpLabel = new Label("Help");

    private final int buttonSizeWidth = 120;
    private final int buttonSizeHeight = 120;
    private final int height = 700;
    private final int width = 1200;

    private ImageView playButtonImageView = new ImageView(buttonImage);
    private ImageView playButtonImageView1 = new ImageView(buttonImage1);
    private ImageView showHandButtonImageView = new ImageView(buttonImage);
    private ImageView showHandButtonImageView1 = new ImageView(buttonImage1);
    private ImageView selectButtonImageView = new ImageView(buttonImage);
    private ImageView selectButtonImageView1 = new ImageView(buttonImage1);
    private ImageView showCollectionButtonImageView = new ImageView(buttonImage);
    private ImageView showCollectionButtonImageView1 = new ImageView(buttonImage1);
    private ImageView removeButtonImageView = new ImageView(buttonImage);
    private ImageView removeButtonImageView1 = new ImageView(buttonImage1);
    private ImageView exitButtonImageView = new ImageView(buttonImage);
    private ImageView exitButtonImageView1 = new ImageView(buttonImage1);
    private ImageView helpButtonImageView = new ImageView(buttonImage);
    private ImageView helpButtonImageView1 = new ImageView(buttonImage1);
    private ImageView backgroundImageView = new ImageView(mainMenuBackgroundImage);

    private Media mouseClicked = new Media(getClass().getClassLoader().getResource("audio/Button-SoundBible.com-1420500901.mp3").toExternalForm());
    private Media mouseEntered = new Media(getClass().getClassLoader().getResource("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm());
    MediaPlayer clickedPlayer = new MediaPlayer(mouseClicked);
    MediaPlayer enteredPlayer = new MediaPlayer(mouseEntered);

    public CollectionMenuView(String s1, String s2) {

        enteredPlayer.setVolume(0.1);
        collectionMenuRoot = new Group();
        backgroundImageView.setFitHeight(height);
        backgroundImageView.setFitWidth(width);
        collectionMenuScene = new Scene(collectionMenuRoot, width, height);
        collectionMenuRoot.getChildren().add(backgroundImageView);

        //play
        setImageView(playButtonImageView, 0);
        setImageView(playButtonImageView1, 0);
        playButtonImageView1.setOpacity(0);
        setButton(playButton, 0);
        setLabel(playLabel, 0);
        collectionMenuRoot.getChildren().add(playButtonImageView);
        collectionMenuRoot.getChildren().add(playButtonImageView1);
        collectionMenuRoot.getChildren().add(playButton);
        collectionMenuRoot.getChildren().add(playLabel);

        //show hand
        setImageView(showHandButtonImageView, 1);
        setImageView(showHandButtonImageView1, 1);
        showHandButtonImageView1.setOpacity(0);
        setButton(showHandButton, 1);
        setLabel(showHandLabel, 1);
        collectionMenuRoot.getChildren().add(showHandButtonImageView);
        collectionMenuRoot.getChildren().add(showHandButtonImageView1);
        collectionMenuRoot.getChildren().add(showHandLabel);
        collectionMenuRoot.getChildren().add(showHandButton);

        //show collection
        setImageView(showCollectionButtonImageView, 2);
        setImageView(showCollectionButtonImageView1, 2);
        showCollectionButtonImageView1.setOpacity(0);
        setButton(showCollectionButton, 2);
        setLabel(showCollectionLabel, 2);
        collectionMenuRoot.getChildren().add(showCollectionButtonImageView);
        collectionMenuRoot.getChildren().add(showCollectionButtonImageView1);
        collectionMenuRoot.getChildren().add(showCollectionButton);
        collectionMenuRoot.getChildren().add(showCollectionLabel);

        //select
        setImageView(selectButtonImageView, 3);
        setImageView(selectButtonImageView1, 3);
        selectButtonImageView1.setOpacity(0);
        setButton(selectButton, 3);
        setLabel(selectLabel, 3);
        collectionMenuRoot.getChildren().add(selectButtonImageView);
        collectionMenuRoot.getChildren().add(selectButtonImageView1);
        collectionMenuRoot.getChildren().add(selectLabel);
        collectionMenuRoot.getChildren().add(selectButton);

        //remove
        setImageView(removeButtonImageView, 4);
        setImageView(removeButtonImageView1, 4);
        removeButtonImageView1.setOpacity(0);
        setButton(removeButton, 4);
        setLabel(removeLabel, 4);
        collectionMenuRoot.getChildren().add(removeButtonImageView);
        collectionMenuRoot.getChildren().add(removeButtonImageView1);
        collectionMenuRoot.getChildren().add(removeLabel);
        collectionMenuRoot.getChildren().add(removeButton);

        //exit
        setImageView(exitButtonImageView, 5);
        setImageView(exitButtonImageView1, 5);
        exitButtonImageView1.setOpacity(0);
        setButton(exitButton, 5);
        setLabel(exitLabel, 5);
        collectionMenuRoot.getChildren().add(exitButtonImageView);
        collectionMenuRoot.getChildren().add(exitButtonImageView1);
        collectionMenuRoot.getChildren().add(exitLabel);
        collectionMenuRoot.getChildren().add(exitButton);

        //help
        setImageView(helpButtonImageView, 6);
        setImageView(helpButtonImageView1, 6);
        helpButtonImageView1.setOpacity(0);
        setButton(helpButton, 6);
        setLabel(helpLabel, 6);
        collectionMenuRoot.getChildren().add(helpButtonImageView);
        collectionMenuRoot.getChildren().add(helpButtonImageView1);
        collectionMenuRoot.getChildren().add(helpLabel);
        collectionMenuRoot.getChildren().add(helpButton);

        checkMovements(s1, s2);

    }

    public Scene getCollectionMenuScene() {
        return collectionMenuScene;
    }

    public void checkMovements(String s1, String s2) {

        playButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (playButtonImageView.getOpacity() == 100)
                    playButtonImageView.setOpacity(0);
                if (playButtonImageView1.getOpacity() == 0)
                    playButtonImageView1.setOpacity(100);
            }
        });


        playButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (playButtonImageView1.getOpacity() == 100)
                    playButtonImageView1.setOpacity(0);
                if (playButtonImageView.getOpacity() == 0)
                    playButtonImageView.setOpacity(100);
            }
        });


        showHandButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (showHandButtonImageView.getOpacity() == 100)
                    showHandButtonImageView.setOpacity(0);
                if (showHandButtonImageView1.getOpacity() == 0)
                    showHandButtonImageView1.setOpacity(100);
            }
        });


        showHandButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (showHandButtonImageView1.getOpacity() == 100)
                    showHandButtonImageView1.setOpacity(0);
                if (showHandButtonImageView.getOpacity() == 0)
                    showHandButtonImageView.setOpacity(100);
            }
        });

        selectButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (selectButtonImageView.getOpacity() == 100)
                    selectButtonImageView.setOpacity(0);
                if (selectButtonImageView1.getOpacity() == 0)
                    selectButtonImageView1.setOpacity(100);
            }
        });


        selectButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (selectButtonImageView1.getOpacity() == 100)
                    selectButtonImageView1.setOpacity(0);
                if (selectButtonImageView.getOpacity() == 0)
                    selectButtonImageView.setOpacity(100);
            }
        });

        showCollectionButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (showCollectionButtonImageView.getOpacity() == 100)
                    showCollectionButtonImageView.setOpacity(0);
                if (showCollectionButtonImageView1.getOpacity() == 0)
                    showCollectionButtonImageView1.setOpacity(100);
            }
        });


        showCollectionButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (showCollectionButtonImageView1.getOpacity() == 100)
                    showCollectionButtonImageView1.setOpacity(0);
                if (showCollectionButtonImageView.getOpacity() == 0)
                    showCollectionButtonImageView.setOpacity(100);
            }
        });

        removeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (removeButtonImageView.getOpacity() == 100)
                    removeButtonImageView.setOpacity(0);
                if (removeButtonImageView1.getOpacity() == 0)
                    removeButtonImageView1.setOpacity(100);
            }
        });


        removeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (removeButtonImageView1.getOpacity() == 100)
                    removeButtonImageView1.setOpacity(0);
                if (removeButtonImageView.getOpacity() == 0)
                    removeButtonImageView.setOpacity(100);
            }
        });

        exitButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (exitButtonImageView.getOpacity() == 100)
                    exitButtonImageView.setOpacity(0);
                if (exitButtonImageView1.getOpacity() == 0)
                    exitButtonImageView1.setOpacity(100);
            }
        });


        exitButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (exitButtonImageView1.getOpacity() == 100)
                    exitButtonImageView1.setOpacity(0);
                if (exitButtonImageView.getOpacity() == 0)
                    exitButtonImageView.setOpacity(100);
            }
        });

        helpButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (helpButtonImageView.getOpacity() == 100)
                    helpButtonImageView.setOpacity(0);
                if (helpButtonImageView1.getOpacity() == 0)
                    helpButtonImageView1.setOpacity(100);
            }
        });


        helpButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (helpButtonImageView1.getOpacity() == 100)
                    helpButtonImageView1.setOpacity(0);
                if (helpButtonImageView.getOpacity() == 0)
                    helpButtonImageView.setOpacity(100);
            }
        });


        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                playButtonClicked(s1,s2);
            }
        });

        selectButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                selectButtonClicked(s1,s2);
            }
        });

        removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                removeButtonClicked(s1,s2);
            }
        });

        showCollectionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                showCollectionButtonClicked(s1,s2);
            }
        });

        showHandButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                showHandButtonClicked(s1,s2);
            }
        });

        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                Menu.collectionMenu("", "", "exit");
            }
        });


        helpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                showHelpClicked("help");
            }
        });

    }

    public void setImageView(ImageView buttonImageView, int n) {
        buttonImageView.setFitWidth(buttonSizeWidth);
        buttonImageView.setFitHeight(buttonSizeHeight);
        if (n < 2) {
            buttonImageView.setX(300 + n * 200);
            buttonImageView.setY(50);
        } else if (n > 1 && n < 4) {
            buttonImageView.setX(400 + (n - 2) * 200);
            buttonImageView.setY(200);
        } else if (n > 3 && n < 6) {
            buttonImageView.setX(500 + (n - 4) * 200);
            buttonImageView.setY(350);
        } else {
            buttonImageView.setX(600);
            buttonImageView.setY(500);
        }

    }

    public void setButton(Button button, int n) {
        button.setOpacity(0);
        if (n < 2) {
            button.relocate(300 + 200 * n, 50);
        } else if (n > 1 && n < 4) {
            button.relocate(400 + 200 * (n - 2), 200);
        } else if (n > 3 && n < 6) {
            button.relocate(500 + 200 * (n - 4), 350);
        } else {
            button.relocate(600, 500);
        }
        button.setPrefSize(buttonSizeWidth, buttonSizeHeight);
    }

    public void setLabel(Label label, int n) {
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        if (n < 2) {
            label.relocate(330 + 200 * n, 110);
        } else if (n > 1 && n < 4) {
            label.relocate(430 + 200 * (n - 2), 260);
        } else if (n > 3 && n < 6) {
            label.relocate(530 + 200 * (n - 4), 410);
        } else {
            label.relocate(630, 560);
        }
    }

    public void showHelpClicked(String s) {
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(100, 600);
        collectionMenuRoot.getChildren().add(back);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.profileMenuView();
                    }
                });
            }
        };
        back.setOnAction(event);
        Text taken = new Text("Help:\n" +
                "1)Press play button to start the game\n" +
                "2)Press show hand button to show selected cards\n" +
                "3)Press show collection button to show unselected cards\n" +
                "4)Press select button to select a card\n" +
                "5)Press remove to remove a card\n");
        taken.setFont(Font.font(30));
        taken.setFill(Color.WHITE);
        taken.relocate(200, 100);
        collectionMenuRoot.getChildren().add(taken);
        Menu.profileMenu(s);
    }

    public void playButtonClicked(String s1,String s2) {
        Text taken = new Text();
        taken.setFont(Font.font(30));
        taken.relocate(400, 620);
        collectionMenuRoot.getChildren().add(taken);
//        if (s2.compareToIgnoreCase("PvP") != 0) {
//            if (Menu.getTempUser().getPlantHand().size() < 7 && s2.compareToIgnoreCase("zombie") != 0) {
//                taken.setText("you don't have enough cards");
//            } else if (Menu.getTempUser().getZombieHand().size() < 7 && s2.compareToIgnoreCase("zombie") == 0) {
//               taken.setText("you don't have enough cards");
//            }
//        }
        if(s2.compareToIgnoreCase("zombie") == 0){
            PlayGround playGround = new PlayGround();
            Game game = new Game();
            ZombieModeOfGame zombieGame = game.zombieGame("", 1, playGround);
            zombieGame.getTheGame(game);
            Main.changeScene(zombieGame.getZombiePlayScene());
        }
        else if (s2.compareToIgnoreCase("day") == 0){
            Game game = new Game ();
            game.setFirstPlayer (Menu.getLoginPlayer ());
            DayModeOfGame dayGame = game.dayGame (1);
            dayGame.getTheGame(game);
            Main.changeScene(dayGame.getDayPlayScene ());

        }
        //TODO

    }

    public void selectButtonClicked(String s1,String s2) {
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Button back = new Button("Back");
        back.setPrefSize(100, 80);
        back.relocate(20, 610);
        collectionMenuRoot.getChildren().add(back);

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.collectionMenuView(s1,s2);
                    }
                });
            }
        };
        back.setOnAction(event2);
        if (s1.compareTo("plant") == 0) {
            for (int i = 0; i < Menu.getTempUser().getCollection().getPlants().size(); i++) {
                Button button = new Button(Menu.getTempUser().getCollection().getPlants().get(i).getName());
                Image buttonImage = new Image("pics/cards/" + Menu.getTempUser().getCollection().getPlants().get(i).getName() + ".jpg");
                ImageView buttonImageView = new ImageView(buttonImage);
                buttonImageView.setFitWidth(200);
                buttonImageView.setFitHeight(100);
                buttonImageView.setX(20 + (i % 5) * 240);
                buttonImageView.setY(10 + (i / 5) * 120);
                button.setOpacity(0);
                button.relocate(20 + (i % 5) * 240, 10 + (i / 5) * 120);
                button.setPrefSize(200, 100);
                collectionMenuRoot.getChildren().add(buttonImageView);
                collectionMenuRoot.getChildren().add(button);
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        clickedPlayer.play();
                        clickedPlayer.seek(Duration.ZERO);
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(150, 610);
                        collectionMenuRoot.getChildren().add(taken);
                        if (Menu.getTempUser().getPlantHand().size() >= 7) {
                            taken.setText("you have 7 plants already");
                        }
                        else{
                            Menu.collectionMenu(s1,s2,"select "+button.getText());
                        }
                        button.setDisable(true);
                    }
                });
            }
        } else {
            for (int i = 0; i < Menu.getTempUser().getCollection().getZombies().size(); i++) {
                Button button = new Button(Menu.getTempUser().getCollection().getZombies().get(i).getName());
                Image buttonImage = new Image("pics/" + Menu.getTempUser().getCollection().getZombies().get(i).getName() + ".jpg");
                ImageView buttonImageView = new ImageView(buttonImage);
                buttonImageView.setFitWidth(200);
                buttonImageView.setFitHeight(100);
                buttonImageView.setX(20 + (i % 5) * 240);
                buttonImageView.setY(10 + (i / 5) * 120);
                button.setOpacity(0);
                button.relocate(20 + (i % 5) * 240, 10 + (i / 5) * 120);
                button.setPrefSize(200, 100);

                collectionMenuRoot.getChildren().add(buttonImageView);
                collectionMenuRoot.getChildren().add(button);
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        clickedPlayer.play();
                        clickedPlayer.seek(Duration.ZERO);
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(150, 610);
                        collectionMenuRoot.getChildren().add(taken);
                        if (Menu.getTempUser().getPlantHand().size() >= 7) {
                            taken.setText("you have 7 zombies already");
                        }
                        else{
                            Menu.collectionMenu(s1,s2,"select "+button.getText());
                        }
                        button.setDisable(true);
                    }
                });
            }
        }
    }

    public void removeButtonClicked(String s1,String s2) {
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Button back = new Button("Back");
        back.setPrefSize(100, 80);
        back.relocate(20, 610);
        collectionMenuRoot.getChildren().add(back);

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.collectionMenuView(s1,s2);
                    }
                });
            }
        };
        back.setOnAction(event2);
        if (s1.compareTo("plant") == 0) {
            for (int i = 0; i < Menu.getTempUser().getPlantHand().size(); i++) {
                Button button = new Button(Menu.getTempUser().getPlantHand().get(i).getName());
                Image buttonImage = new Image("pics/" + Menu.getTempUser().getPlantHand().get(i).getName() + ".jpg");
                ImageView buttonImageView = new ImageView(buttonImage);
                buttonImageView.setFitWidth(200);
                buttonImageView.setFitHeight(100);
                buttonImageView.setX(20 + (i % 5) * 240);
                buttonImageView.setY(10 + (i / 5) * 120);
                button.setOpacity(0);
                button.relocate(20 + (i % 5) * 240, 10 + (i / 5) * 120);
                button.setPrefSize(200, 100);
                collectionMenuRoot.getChildren().add(buttonImageView);
                collectionMenuRoot.getChildren().add(button);
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        clickedPlayer.play();
                        clickedPlayer.seek(Duration.ZERO);
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(150, 610);
                        collectionMenuRoot.getChildren().add(taken);
                        Menu.collectionMenu(s1,s2,"remove "+button.getText());
                        button.setDisable(true);
                    }
                });
            }
        } else {
            for (int i = 0; i < Menu.getTempUser().getZombieHand().size(); i++) {
                Button button = new Button(Menu.getTempUser().getZombieHand().get(i).getName());
                Image buttonImage = new Image("pics/" + Menu.getTempUser().getZombieHand().get(i).getName() + ".jpg");
                ImageView buttonImageView = new ImageView(buttonImage);
                buttonImageView.setFitWidth(200);
                buttonImageView.setFitHeight(100);
                buttonImageView.setX(20 + (i % 5) * 240);
                buttonImageView.setY(10 + (i / 5) * 120);
                button.setOpacity(0);
                button.relocate(20 + (i % 5) * 240, 10 + (i / 5) * 120);
                button.setPrefSize(200, 100);
                collectionMenuRoot.getChildren().add(buttonImageView);
                collectionMenuRoot.getChildren().add(button);
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        clickedPlayer.play();
                        clickedPlayer.seek(Duration.ZERO);
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(150, 610);
                        collectionMenuRoot.getChildren().add(taken);
                        Menu.collectionMenu(s1,s2,"remove "+button.getText());
                        button.setDisable(true);
                    }
                });
            }
        }
    }

    public void showCollectionButtonClicked(String s1,String s2) {
        Menu.collectionMenu(s1,"","showCollection");
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Button back = new Button("Back");
        back.setPrefSize(100, 80);
        back.relocate(20, 610);
        collectionMenuRoot.getChildren().add(back);

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.collectionMenuView(s1,s2);
                    }
                });
            }
        };
        back.setOnAction(event2);
        if (s1.compareTo("plant") == 0) {
            for (int i = 0; i < Menu.getTempUser().getCollection().getPlants().size(); i++) {
                Image listImage = new Image("pics/" + Menu.getTempUser().getCollection().getPlants().get(i).getName() + ".jpg");
                ImageView listImageView = new ImageView(listImage);
                listImageView.setFitWidth(200);
                listImageView.setFitHeight(100);
                listImageView.setX(20 + (i % 5) * 240);
                listImageView.setY(10 + (i / 5) * 120);
                collectionMenuRoot.getChildren().add(listImageView);
            }
        } else {
            for (int i = 0; i < Menu.getTempUser().getCollection().getZombies().size(); i++) {
                Image listImage = new Image("pics/" + Menu.getTempUser().getCollection().getZombies().get(i).getName() + ".jpg");
                ImageView listImageView = new ImageView(listImage);
                listImageView.setFitWidth(200);
                listImageView.setFitHeight(100);
                listImageView.setX(20 + (i % 5) * 240);
                listImageView.setY(10 + (i / 5) * 120);
                collectionMenuRoot.getChildren().add(listImageView);
            }
        }
    }

    public void showHandButtonClicked(String s1,String s2) {
        Menu.collectionMenu(s1,"","showHand");
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Button back = new Button("Back");
        back.setPrefSize(100, 80);
        back.relocate(20, 610);
        collectionMenuRoot.getChildren().add(back);

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.collectionMenuView(s1,s2);
                    }
                });
            }
        };
        back.setOnAction(event2);
        if (s1.compareTo("plant") == 0) {
            for (int i = 0; i < Menu.getTempUser().getPlantHand().size(); i++) {
                Image listImage = new Image("pics/" + Menu.getTempUser().getPlantHand().get(i).getName() + ".jpg");
                ImageView listImageView = new ImageView(listImage);
                listImageView.setFitWidth(200);
                listImageView.setFitHeight(100);
                listImageView.setX(20 + (i % 5) * 240);
                listImageView.setY(10 + (i / 5) * 120);
                collectionMenuRoot.getChildren().add(listImageView);
            }
        } else {
            for (int i = 0; i < Menu.getTempUser().getZombieHand().size(); i++) {
                Image listImage = new Image("pics/" + Menu.getTempUser().getZombieHand().get(i).getName() + ".jpg");
                ImageView listImageView = new ImageView(listImage);
                listImageView.setFitWidth(200);
                listImageView.setFitHeight(100);
                listImageView.setX(20 + (i % 5) * 240);
                listImageView.setY(10 + (i / 5) * 120);
                collectionMenuRoot.getChildren().add(listImageView);
            }
        }
    }


}
