package Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class CollectionMenuView {//TODO
    private Group collectionMenuRoot;
    private Scene collectionMenuScene;
    private GaussianBlur blur = new GaussianBlur();

    private Image buttonImage = new Image("pics/beforeclick.webp");
    private Image buttonImage1 = new Image("pics/afterclick.webp");
    private Image mainMenuBackgroundImage = new Image("pics/bg3.jpg");

    private Button changeButton = new Button("Change");
    private Button deleteButton = new Button("Delete");
    private Button renameButton = new Button("Rename");
    private Button createButton = new Button("Create");
    private Button showButton = new Button("Show");
    private Button exitButton = new Button("Exit");
    private Button helpButton = new Button("Help");

    private Label changeLabel = new Label("Change");
    private Label deleteLabel = new Label("Delete");
    private Label renameLabel = new Label("Rename");
    private Label createLabel = new Label("Create");
    private Label showLabel = new Label("Show");
    private Label exitLabel = new Label("Exit");
    private Label helpLabel = new Label("Help");

    private final int buttonSizeWidth = 120;
    private final int buttonSizeHeight = 120;
    private final int height = 700;
    private final int width = 1200;

    private ImageView changeButtonImageView = new ImageView(buttonImage);
    private ImageView changeButtonImageView1 = new ImageView(buttonImage1);
    private ImageView deleteButtonImageView = new ImageView(buttonImage);
    private ImageView deleteButtonImageView1 = new ImageView(buttonImage1);
    private ImageView createButtonImageView = new ImageView(buttonImage);
    private ImageView createButtonImageView1 = new ImageView(buttonImage1);
    private ImageView renameButtonImageView = new ImageView(buttonImage);
    private ImageView renameButtonImageView1 = new ImageView(buttonImage1);
    private ImageView showButtonImageView = new ImageView(buttonImage);
    private ImageView showButtonImageView1 = new ImageView(buttonImage1);
    private ImageView exitButtonImageView = new ImageView(buttonImage);
    private ImageView exitButtonImageView1 = new ImageView(buttonImage1);
    private ImageView helpButtonImageView = new ImageView(buttonImage);
    private ImageView helpButtonImageView1 = new ImageView(buttonImage1);
    private ImageView backgroundImageView = new ImageView(mainMenuBackgroundImage);

    private Media mouseClicked = new Media(getClass().getClassLoader().getResource("audio/Button-SoundBible.com-1420500901.mp3").toExternalForm());
    private Media mouseEntered = new Media(getClass().getClassLoader().getResource("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm());
    MediaPlayer clickedPlayer = new MediaPlayer(mouseClicked);
    MediaPlayer enteredPlayer = new MediaPlayer(mouseEntered);

    public CollectionMenuView(String s1,String s2) {

        enteredPlayer.setVolume(0.1);
        collectionMenuRoot = new Group();
        backgroundImageView.setFitHeight(height);
        backgroundImageView.setFitWidth(width);
        collectionMenuScene = new Scene(collectionMenuRoot, width, height);
        collectionMenuRoot.getChildren().add(backgroundImageView);

        //change
        setImageView(changeButtonImageView, 0);
        setImageView(changeButtonImageView1, 0);
        changeButtonImageView1.setOpacity(0);
        setButton(changeButton, 0);
        setLabel(changeLabel, 0);
        collectionMenuRoot.getChildren().add(changeButtonImageView);
        collectionMenuRoot.getChildren().add(changeButtonImageView1);
        collectionMenuRoot.getChildren().add(changeButton);
        collectionMenuRoot.getChildren().add(changeLabel);

        //delete
        setImageView(deleteButtonImageView, 1);
        setImageView(deleteButtonImageView1, 1);
        deleteButtonImageView1.setOpacity(0);
        setButton(deleteButton, 1);
        setLabel(deleteLabel, 1);
        collectionMenuRoot.getChildren().add(deleteButtonImageView);
        collectionMenuRoot.getChildren().add(deleteButtonImageView1);
        collectionMenuRoot.getChildren().add(deleteLabel);
        collectionMenuRoot.getChildren().add(deleteButton);

        //create
        setImageView(createButtonImageView, 2);
        setImageView(createButtonImageView1, 2);
        createButtonImageView1.setOpacity(0);
        setButton(createButton, 2);
        setLabel(createLabel, 2);
        collectionMenuRoot.getChildren().add(createButtonImageView);
        collectionMenuRoot.getChildren().add(createButtonImageView1);
        collectionMenuRoot.getChildren().add(createLabel);
        collectionMenuRoot.getChildren().add(createButton);

        //rename
        setImageView(renameButtonImageView, 3);
        setImageView(renameButtonImageView1, 3);
        renameButtonImageView1.setOpacity(0);
        setButton(renameButton, 3);
        setLabel(renameLabel, 3);
        collectionMenuRoot.getChildren().add(renameButtonImageView);
        collectionMenuRoot.getChildren().add(renameButtonImageView1);
        collectionMenuRoot.getChildren().add(renameLabel);
        collectionMenuRoot.getChildren().add(renameButton);

        //show
        setImageView(showButtonImageView, 4);
        setImageView(showButtonImageView1, 4);
        showButtonImageView1.setOpacity(0);
        setButton(showButton, 4);
        setLabel(showLabel, 4);
        collectionMenuRoot.getChildren().add(showButtonImageView);
        collectionMenuRoot.getChildren().add(showButtonImageView1);
        collectionMenuRoot.getChildren().add(showLabel);
        collectionMenuRoot.getChildren().add(showButton);

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

        checkMovements();

    }

    public Scene getCollectionMenuScene() {
        return collectionMenuScene;
    }

    public void checkMovements() {

        changeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (changeButtonImageView.getOpacity() == 100)
                    changeButtonImageView.setOpacity(0);
                if (changeButtonImageView1.getOpacity() == 0)
                    changeButtonImageView1.setOpacity(100);
            }
        });


        changeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (changeButtonImageView1.getOpacity() == 100)
                    changeButtonImageView1.setOpacity(0);
                if (changeButtonImageView.getOpacity() == 0)
                    changeButtonImageView.setOpacity(100);
            }
        });


        deleteButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (deleteButtonImageView.getOpacity() == 100)
                    deleteButtonImageView.setOpacity(0);
                if (deleteButtonImageView1.getOpacity() == 0)
                    deleteButtonImageView1.setOpacity(100);
            }
        });


        deleteButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (deleteButtonImageView1.getOpacity() == 100)
                    deleteButtonImageView1.setOpacity(0);
                if (deleteButtonImageView.getOpacity() == 0)
                    deleteButtonImageView.setOpacity(100);
            }
        });

        createButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (createButtonImageView.getOpacity() == 100)
                    createButtonImageView.setOpacity(0);
                if (createButtonImageView1.getOpacity() == 0)
                    createButtonImageView1.setOpacity(100);
            }
        });


        createButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (createButtonImageView1.getOpacity() == 100)
                    createButtonImageView1.setOpacity(0);
                if (createButtonImageView.getOpacity() == 0)
                    createButtonImageView.setOpacity(100);
            }
        });

        renameButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (renameButtonImageView.getOpacity() == 100)
                    renameButtonImageView.setOpacity(0);
                if (renameButtonImageView1.getOpacity() == 0)
                    renameButtonImageView1.setOpacity(100);
            }
        });


        renameButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (renameButtonImageView1.getOpacity() == 100)
                    renameButtonImageView1.setOpacity(0);
                if (renameButtonImageView.getOpacity() == 0)
                    renameButtonImageView.setOpacity(100);
            }
        });

        showButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (showButtonImageView.getOpacity() == 100)
                    showButtonImageView.setOpacity(0);
                if (showButtonImageView1.getOpacity() == 0)
                    showButtonImageView1.setOpacity(100);
            }
        });


        showButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (showButtonImageView1.getOpacity() == 100)
                    showButtonImageView1.setOpacity(0);
                if (showButtonImageView.getOpacity() == 0)
                    showButtonImageView.setOpacity(100);
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


        changeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                changeButtonClicked();
            }
        });

        createButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                createButtonClicked();
            }
        });

        showButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                showButtonClicked();
            }
        });

        renameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                renameButtonClicked();
            }
        });

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                deleteButtonClicked();
            }
        });

        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play();
                clickedPlayer.seek(Duration.ZERO);
                Menu.profileMenu("exit");
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
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
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
                "1)Press change button to change username & password\n" +
                "2)Press delete button to delete your account\n" +
                "3)Press rename button to change your name\n" +
                "4)Press create button to create an account\n" +
                "5)Press show button to see your username and password\n");
        taken.setFont(Font.font(30));
        taken.setFill(Color.WHITE);
        taken.relocate(200, 100);
        collectionMenuRoot.getChildren().add(taken);
        Menu.profileMenu(s);
    }

    public void changeButtonClicked() {
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(350, 50);
        TextField username = new TextField();
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        collectionMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        TextField pass = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.relocate(350, 150);
        passwordLabel.setTextFill(Color.BLACK);
        pass.relocate(350, 175);
        collectionMenuRoot.getChildren().addAll(pass, passwordLabel);
        pass.setPrefSize(200, 50);
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setLabelFor(pass);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(600, 550);
        collectionMenuRoot.getChildren().add(back);
        collectionMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "change";
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(450, 400);
                        collectionMenuRoot.getChildren().add(taken);
                        if (!Menu.checkUsername(username.getText())) {
                            taken.setText("Username & password successfully changed!");
                            str += " " + username.getText() + " " + pass.getText();
                            Menu.profileMenu(str);
                        } else {
                            taken.setText("Select another username");
                        }
                    }
                });
            }
        };
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.profileMenuView();
                    }
                });
            }
        };
        pass.setOnAction(event1);
        username.setOnAction(event1);
        ok.setOnAction(event1);
        back.setOnAction(event2);

    }

    public void createButtonClicked() {
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(350, 50);
        TextField username = new TextField();
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        collectionMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        TextField pass = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.relocate(350, 150);
        passwordLabel.setTextFill(Color.BLACK);
        pass.relocate(350, 175);
        collectionMenuRoot.getChildren().addAll(pass, passwordLabel);
        pass.setPrefSize(200, 50);
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setLabelFor(pass);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(600, 550);
        collectionMenuRoot.getChildren().add(back);
        collectionMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "create";
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(450, 400);
                        collectionMenuRoot.getChildren().add(taken);
                        if (!Menu.checkUsername(username.getText())) {
                            taken.setText("New account created!");
                            str += " " + username.getText() + " " + pass.getText();
                            Menu.profileMenu(str);
                        } else {
                            taken.setText("invalid username");
                        }
                    }
                });
            }
        };
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.profileMenuView();
                    }
                });
            }
        };
        pass.setOnAction(event1);
        username.setOnAction(event1);
        ok.setOnAction(event1);
        back.setOnAction(event2);
    }

    public void showButtonClicked() {
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(350, 50);
        Label username = new Label(Menu.getLoginUser().getUsername());
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        collectionMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        Label pass = new Label(Menu.getLoginUser().getPassword());
        Label passwordLabel = new Label("Password:");
        passwordLabel.relocate(350, 150);
        passwordLabel.setTextFill(Color.BLACK);
        pass.relocate(350, 175);
        collectionMenuRoot.getChildren().addAll(pass, passwordLabel);
        pass.setPrefSize(200, 50);
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setLabelFor(pass);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(600, 550);
        collectionMenuRoot.getChildren().add(back);
        collectionMenuRoot.getChildren().add(ok);
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
    }

    public void renameButtonClicked() {
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(350, 50);
        TextField username = new TextField();
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        collectionMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(600, 550);
        collectionMenuRoot.getChildren().add(back);
        collectionMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "rename";
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(450, 400);
                        collectionMenuRoot.getChildren().add(taken);
                        if (!Menu.checkUsername(username.getText())) {
                            taken.setText("Name changed!");
                            str += " " + username.getText();
                        } else {
                            taken.setText("invalid username");
                        }
                        Menu.profileMenu(str);
                    }
                });
            }
        };
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.profileMenuView();
                    }
                });
            }
        };
        username.setOnAction(event1);
        ok.setOnAction(event1);
        back.setOnAction(event2);
    }

    public void deleteButtonClicked() {
        collectionMenuRoot.getChildren().clear();
        collectionMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(350, 50);
        TextField username = new TextField();
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        collectionMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        TextField pass = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.relocate(350, 150);
        passwordLabel.setTextFill(Color.BLACK);
        pass.relocate(350, 175);
        collectionMenuRoot.getChildren().addAll(pass, passwordLabel);
        pass.setPrefSize(200, 50);
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setLabelFor(pass);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(600, 550);
        collectionMenuRoot.getChildren().add(back);
        collectionMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "delete";
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(450, 400);
                        collectionMenuRoot.getChildren().add(taken);
                        if (Menu.getLoginUser().getUsername().compareTo(username.getText()) == 0) {
                            if (Menu.checkPassword(username.getText(), pass.getText())) {
                                taken.setText("Account deleted");
                                str += " "+username.getText()+" "+pass.getText();
                                Menu.profileMenu(str);
                                EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent e) {
                                        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {
                                                Menu.loginMenuView();
                                            }
                                        });
                                    }
                                };
                                back.setOnAction(event2);
                            }
                            else {
                                taken.setText("inavlid password!");
                            }
                        } else {
                            taken.setText("invalid username!");
                        }
                    }
                });
            }
        };
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.profileMenuView();
                    }
                });
            }
        };
        back.setOnAction(event2);
        pass.setOnAction(event1);
        username.setOnAction(event1);
        ok.setOnAction(event1);
    }
}

