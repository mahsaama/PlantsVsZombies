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

public class ShopMenuView {
    private Group shopMenuRoot;
    private Scene shopMenuScene;
    private GaussianBlur blur= new GaussianBlur ();

    private Image buttonImage = new Image("pics/beforeclick.webp");
    private Image buttonImage1 = new Image("pics/afterclick.webp");
    private Image mainMenuBackgroundImage = new Image("pics/bg3.jpg");

    private Button showShopButton = new Button("Show shop");
    private Button collectionButton = new Button("Collection");
    private Button buyButton = new Button("Rename");
    private Button moneyButton = new Button("Money");
    private Button exitButton = new Button("Exit");
    private Button helpButton = new Button("Help");

    private Label showShopLabel = new Label("Show shop");
    private Label collectionLabel = new Label("Collection");
    private Label buyLabel = new Label("Rename");
    private Label moneyLabel = new Label("Money");
    private Label exitLabel = new Label("Exit");
    private Label helpLabel = new Label("Help");

    private final int buttonSizeWidth = 120;
    private final int buttonSizeHeight = 120;
    private final int height = 700;
    private final int width = 1200;

    private ImageView showShopButtonImageView = new ImageView(buttonImage);
    private ImageView showShopButtonImageView1 = new ImageView(buttonImage1);
    private ImageView collectionButtonImageView = new ImageView(buttonImage);
    private ImageView collectionButtonImageView1 = new ImageView(buttonImage1);
    private ImageView buyButtonImageView = new ImageView(buttonImage);
    private ImageView buyButtonImageView1 = new ImageView(buttonImage1);
    private ImageView moneyButtonImageView = new ImageView(buttonImage);
    private ImageView moneyButtonImageView1 = new ImageView(buttonImage1);
    private ImageView exitButtonImageView = new ImageView(buttonImage);
    private ImageView exitButtonImageView1 = new ImageView(buttonImage1);
    private ImageView helpButtonImageView = new ImageView(buttonImage);
    private ImageView helpButtonImageView1 = new ImageView(buttonImage1);
    private ImageView backgroundImageView = new ImageView(mainMenuBackgroundImage);

    private Media mouseClicked = new Media(getClass ( ).getClassLoader ().getResource ("audio/Button-SoundBible.com-1420500901.mp3" ).toExternalForm ());
    private Media mouseEntered= new Media (getClass().getClassLoader().getResource ("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm ());
    MediaPlayer clickedPlayer= new MediaPlayer (mouseClicked);
    MediaPlayer enteredPlayer= new MediaPlayer (mouseEntered);

    public ShopMenuView() {

        enteredPlayer.setVolume(0.1);
        shopMenuRoot = new Group();
        backgroundImageView.setFitHeight(height);
        backgroundImageView.setFitWidth(width);
        shopMenuScene = new Scene(shopMenuRoot, width, height);
        shopMenuRoot.getChildren().add(backgroundImageView);


        //Show shop
        setImageView(showShopButtonImageView, 0);
        setImageView(showShopButtonImageView1, 0);
        showShopButtonImageView1.setOpacity(0);
        setButton(showShopButton, 0);
        setLabel(showShopLabel, 0);
        shopMenuRoot.getChildren().add(showShopButtonImageView);
        shopMenuRoot.getChildren().add(showShopButtonImageView1);
        shopMenuRoot.getChildren().add(showShopButton);
        shopMenuRoot.getChildren().add(showShopLabel);

        //collection
        setImageView(collectionButtonImageView, 1);
        setImageView(collectionButtonImageView1, 1);
        collectionButtonImageView1.setOpacity(0);
        setButton(collectionButton, 1);
        setLabel(collectionLabel, 1);
        shopMenuRoot.getChildren().add(collectionButtonImageView);
        shopMenuRoot.getChildren().add(collectionButtonImageView1);
        shopMenuRoot.getChildren().add(collectionLabel);
        shopMenuRoot.getChildren().add(collectionButton);

        //buy
        setImageView(buyButtonImageView, 2);
        setImageView(buyButtonImageView1, 2);
        buyButtonImageView1.setOpacity(0);
        setButton(buyButton , 2);
        setLabel(buyLabel, 2);
        shopMenuRoot.getChildren().add((buyButtonImageView));
        shopMenuRoot.getChildren().add((buyButtonImageView1));
        shopMenuRoot.getChildren().add(buyLabel);
        shopMenuRoot.getChildren().add(buyButton);

        //money
        setImageView(moneyButtonImageView, 3);
        setImageView(moneyButtonImageView1, 3);
        moneyButtonImageView1.setOpacity(0);
        setButton(moneyButton , 3);
        setLabel(moneyLabel, 3);
        shopMenuRoot.getChildren().add((moneyButtonImageView));
        shopMenuRoot.getChildren().add((moneyButtonImageView1));
        shopMenuRoot.getChildren().add(moneyLabel);
        shopMenuRoot.getChildren().add(moneyButton);

        //exit
        setImageView(exitButtonImageView, 4);
        setImageView(exitButtonImageView1, 4);
        exitButtonImageView1.setOpacity(0);
        setButton(exitButton, 4);
        setLabel(exitLabel, 4);
        shopMenuRoot.getChildren().add(exitButtonImageView);
        shopMenuRoot.getChildren().add(exitButtonImageView1);
        shopMenuRoot.getChildren().add(exitLabel);
        shopMenuRoot.getChildren().add(exitButton);

        //help
        setImageView(helpButtonImageView, 5);
        setImageView(helpButtonImageView1, 5);
        helpButtonImageView1.setOpacity(0);
        setButton(helpButton, 5);
        setLabel(helpLabel, 5);
        shopMenuRoot.getChildren().add(helpButtonImageView);
        shopMenuRoot.getChildren().add(helpButtonImageView1);
        shopMenuRoot.getChildren().add(helpLabel);
        shopMenuRoot.getChildren().add(helpButton);

        checkMovements();

    }

    public Scene getShopMenuScene() {
        return shopMenuScene;
    }

    public void checkMovements() {

        showShopButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (showShopButtonImageView.getOpacity() == 100)
                    showShopButtonImageView.setOpacity(0);
                if (showShopButtonImageView1.getOpacity() == 0)
                    showShopButtonImageView1.setOpacity(100);
            }
        });


        showShopButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if ( showShopButtonImageView1.getOpacity() == 100)
                    showShopButtonImageView1.setOpacity(0);
                if ( showShopButtonImageView.getOpacity() == 0)
                    showShopButtonImageView.setOpacity(100);
            }
        });


        collectionButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (collectionButtonImageView.getOpacity() == 100)
                    collectionButtonImageView.setOpacity(0);
                if (collectionButtonImageView1.getOpacity() == 0)
                    collectionButtonImageView1.setOpacity(100);
            }
        });


        collectionButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (collectionButtonImageView1.getOpacity() == 100)
                    collectionButtonImageView1.setOpacity(0);
                if (collectionButtonImageView.getOpacity() == 0)
                    collectionButtonImageView.setOpacity(100);
            }
        });

        buyButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (buyButtonImageView.getOpacity() == 100)
                    buyButtonImageView.setOpacity(0);
                if (buyButtonImageView1.getOpacity() == 0)
                    buyButtonImageView1.setOpacity(100);
            }
        });


        buyButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (buyButtonImageView1.getOpacity() == 100)
                    buyButtonImageView1.setOpacity(0);
                if (buyButtonImageView.getOpacity() == 0)
                    buyButtonImageView.setOpacity(100);
            }
        });

        moneyButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (moneyButtonImageView.getOpacity() == 100)
                    moneyButtonImageView.setOpacity(0);
                if (moneyButtonImageView1.getOpacity() == 0)
                    moneyButtonImageView1.setOpacity(100);
            }
        });


        moneyButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (moneyButtonImageView1.getOpacity() == 100)
                    moneyButtonImageView1.setOpacity(0);
                if (moneyButtonImageView.getOpacity() == 0)
                    moneyButtonImageView.setOpacity(100);
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


        showShopButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                showShopButtonClicked();
            }
        });

        moneyButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                moneyButtonClicked();
            }
        });

        buyButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                buyButtonClicked();
            }
        });

        collectionButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                collectionButtonClicked();
            }
        });

        exitButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.shopMenu("exit");
            }
        });


        helpButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                showHelpClicked("help");
            }
        });

    }

    public void setImageView(ImageView buttonImageView, int n) {

        buttonImageView.setFitWidth(buttonSizeWidth);
        buttonImageView.setFitHeight(buttonSizeHeight);
        if (n < 2) {
            buttonImageView.setX( 300+ n*200);
            buttonImageView.setY(50);
        }
        else if (n>1 && n<4){
            buttonImageView.setX(400 + (n-2)*200);
            buttonImageView.setY(200);
        }
        else{
            buttonImageView.setX(500 + (n-4)*200);
            buttonImageView.setY(350);
        }

    }

    public void setButton(Button button, int n) {
        button.setOpacity(0);
        if (n < 2) {
            button.relocate(300 + 200 * n, 50);
        }
        else if (n>1 && n<4){
            button.relocate(400 + 200 * (n-2), 200);
        }
        else{
            button.relocate(500 + 200 * (n-4), 350);
        }
        button.setPrefSize(buttonSizeWidth, buttonSizeHeight);
    }

    public void setLabel(Label label, int n) {
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        if (n < 2) {
            label.relocate(330 + 200 * n, 120);
        }
        else if (n>1 && n<4){
            label.relocate(430 + 200 * (n-2), 270);
        }
        else{
            label.relocate(530 + 200 * (n-4), 420);
        }
    }
    public void showHelpClicked(String s){
        shopMenuRoot.getChildren ().clear();
        shopMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(100, 600);
        shopMenuRoot.getChildren().add(back);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.mainMenuView();
                    }
                });
            }
        };
        back.setOnAction(event);
        Text taken = new Text("Help:\n" +
                "1)Press show shop button to see the unboughten cards and their prices\n" +
                "2)Press collection button to see the boughten cards\n" +
                "3)Press buy button to buy card\n" +
                "4)Press money button to show your money\n");
        taken.setFont(Font.font(30));
        taken.setFill(Color.WHITE);
        taken.relocate(200, 100);
        shopMenuRoot.getChildren().add(taken);
        Menu.playMenu(s);
    }

    public void showShopButtonClicked(){//TODO
        shopMenuRoot.getChildren().clear();
        shopMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(350, 50);
        TextField username = new TextField();
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        shopMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        TextField pass = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.relocate(350, 150);
        passwordLabel.setTextFill(Color.BLACK);
        pass.relocate(350, 175);
        shopMenuRoot.getChildren().addAll(pass, passwordLabel);
        pass.setPrefSize(200, 50);
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setLabelFor(pass);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(600, 550);
        shopMenuRoot.getChildren().add(back);
        shopMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "showshop";
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(450, 400);
                        shopMenuRoot.getChildren().add(taken);
                        if (!Menu.checkUsername(username.getText())){
                            taken.setText("Username & password successfully changed!");
                            str += " "+username.getText()+" "+pass.getText();

                        } else {
                            taken.setText("Select another username");
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
        pass.setOnAction(event1);
        username.setOnAction(event1);
        ok.setOnAction(event1);
        back.setOnAction(event2);

    }

    public void buyButtonClicked(){//TODO
        shopMenuRoot.getChildren().clear();
        shopMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(350, 50);
        TextField username = new TextField();
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        shopMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        TextField pass = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.relocate(350, 150);
        passwordLabel.setTextFill(Color.BLACK);
        pass.relocate(350, 175);
        shopMenuRoot.getChildren().addAll(pass, passwordLabel);
        pass.setPrefSize(200, 50);
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setLabelFor(pass);
        Button back = new Button("Go to login menu");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(600, 550);
        shopMenuRoot.getChildren().add(back);
        shopMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "create";
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(450, 400);
                        shopMenuRoot.getChildren().add(taken);
                        if (!Menu.checkUsername(username.getText())){
                            taken.setText("New account created!");
                            str += " "+username.getText()+" "+pass.getText();
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
                        Menu.loginMenuView();
                    }
                });
            }
        };
        pass.setOnAction(event1);
        username.setOnAction(event1);
        ok.setOnAction(event1);
        back.setOnAction(event2);
    }

    public void moneyButtonClicked(){//TODO
        shopMenuRoot.getChildren().clear();
        shopMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(350, 50);
        Label username = new Label(Menu.getLoginUser().getUsername());
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        shopMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        Label pass = new Label(Menu.getLoginUser().getPassword());
        Label passwordLabel = new Label("Password:");
        passwordLabel.relocate(350, 150);
        passwordLabel.setTextFill(Color.BLACK);
        pass.relocate(350, 175);
        shopMenuRoot.getChildren().addAll(pass, passwordLabel);
        pass.setPrefSize(200, 50);
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setLabelFor(pass);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(600, 550);
        shopMenuRoot.getChildren().add(back);
        shopMenuRoot.getChildren().add(ok);
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

    public void collectionButtonClicked(){//TODO
        shopMenuRoot.getChildren().clear();
        shopMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(350, 50);
        TextField username = new TextField();
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        shopMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        TextField pass = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.relocate(350, 150);
        passwordLabel.setTextFill(Color.BLACK);
        pass.relocate(350, 175);
        shopMenuRoot.getChildren().addAll(pass, passwordLabel);
        pass.setPrefSize(200, 50);
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setLabelFor(pass);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(600, 550);
        shopMenuRoot.getChildren().add(back);
        shopMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "collection";
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(450, 400);
                        shopMenuRoot.getChildren().add(taken);
                        if (Menu.getLoginUser().getUsername().compareTo(username.getText())==0 && Menu.getLoginUser().getPassword().compareTo(pass.getText())==0){
                            taken.setText("This account deleted!");
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
                        } else {
                            taken.setText("Account deleted");
                            Menu.profileMenu(str);
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
                        }
                    }
                });
            }
        };
        pass.setOnAction(event1);
        username.setOnAction(event1);
        ok.setOnAction(event1);
    }
}
