package Menu;

import Game.Game;
import Shop.Shop;
import User.User;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LoginMenuView {
    private Group mainMenuRoot;
    private Scene mainMenuScene;
    private GaussianBlur blur= new GaussianBlur ();

    private Image buttonImage = new Image("pics/b.jpg");
    private Image buttonImage1 = new Image("pics/c.jpg");
    private Image menuBackgroundImage = new Image("pics/bg.jpg");

    private Button createAccountButton = new Button("Create New Account");
    private Button loginButton = new Button("login");
    private Button showLeaderBoardButton = new Button("show leaderboard");
    private Button helpButton = new Button("help");

    private Label createAccountLabel = new Label("Create Account");
    private Label loginLabel = new Label("login");
    private Label showLeaderBoardLabel = new Label("leaderboard");
    private Label helpLabel = new Label("help");

    private final int buttonSizeWidth = 200;
    private final int buttonSizeHeight = 200;
    private final int width = 1200;
    private final int height = 700;


    private ImageView createAccountButtonImageView = new ImageView(buttonImage);
    private ImageView createAccountButtonImageView1 = new ImageView(buttonImage1);
    private ImageView loginButtonImageView = new ImageView(buttonImage);
    private ImageView loginButtonImageView1 = new ImageView(buttonImage1);
    private ImageView showLeaderBoardButtonImageView = new ImageView(buttonImage);
    private ImageView showLeaderBoardButtonImageView1 = new ImageView(buttonImage1);
    private ImageView helpButtonImageView = new ImageView(buttonImage);
    private ImageView helpButtonImageView1 = new ImageView(buttonImage1);
    private ImageView backgroundImageView = new ImageView(menuBackgroundImage);

    private Media mouseClicked = new Media(getClass ( ).getClassLoader ().getResource ("audio/Button-SoundBible.com-1420500901.mp3" ).toExternalForm ());
    private Media mouseEntered= new Media (getClass().getClassLoader().getResource ("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm ());
    MediaPlayer clickedPlayer= new MediaPlayer (mouseClicked);
    MediaPlayer enteredPlayer= new MediaPlayer (mouseEntered);

//    Thread thread = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            Media duringGame= new Media (getClass().getClassLoader().getResource ("audio/02 Crazy Dave (Intro Theme).mp3").toExternalForm ());
//            MediaPlayer wholePlayer = new MediaPlayer(duringGame);
//            wholePlayer.setVolume(0.05);
//            wholePlayer.play();
//            wholePlayer.seek(Duration.INDEFINITE);
//        }
//    });

    public LoginMenuView(String input) {
        enteredPlayer.setVolume(0.1);
        mainMenuRoot = new Group();
        backgroundImageView.setFitHeight(height);
        backgroundImageView.setFitWidth(width);
        mainMenuScene = new Scene(mainMenuRoot, width, height);
        mainMenuRoot.getChildren().add(backgroundImageView);

        //create new account
        setImageView(createAccountButtonImageView, 0);
        setImageView(createAccountButtonImageView1, 0);
        createAccountButtonImageView1.setOpacity(0);
        setButton(createAccountButton, 0);
        setLabel(createAccountLabel, 0);
        mainMenuRoot.getChildren().add(createAccountButtonImageView);
        mainMenuRoot.getChildren().add(createAccountButtonImageView1);
        mainMenuRoot.getChildren().add(createAccountLabel);
        mainMenuRoot.getChildren().add(createAccountButton);

        //login button
        setImageView(loginButtonImageView, 1);
        setImageView(loginButtonImageView1, 1);
        loginButtonImageView1.setOpacity(0);
        setButton(loginButton, 1);
        setLabel(loginLabel, 1);
        mainMenuRoot.getChildren().add(loginButtonImageView);
        mainMenuRoot.getChildren().add(loginButtonImageView1);
        mainMenuRoot.getChildren().add(loginLabel);
        mainMenuRoot.getChildren().add(loginButton);

        //show LeaderBoard Button
        setImageView(showLeaderBoardButtonImageView, 2);
        setImageView(showLeaderBoardButtonImageView1, 2);
        showLeaderBoardButtonImageView1.setOpacity(0);
        setButton(showLeaderBoardButton, 2);
        setLabel(showLeaderBoardLabel, 2);
        mainMenuRoot.getChildren().add(showLeaderBoardButtonImageView);
        mainMenuRoot.getChildren().add(showLeaderBoardButtonImageView1);
        mainMenuRoot.getChildren().add(showLeaderBoardLabel);
        mainMenuRoot.getChildren().add(showLeaderBoardButton);

        //help button
        setImageView(helpButtonImageView, 3);
        setImageView(helpButtonImageView1, 3);
        helpButtonImageView1.setOpacity(0);
        setButton(helpButton, 3);
        setLabel(helpLabel, 3);
        mainMenuRoot.getChildren().add(helpButtonImageView);
        mainMenuRoot.getChildren().add(helpButtonImageView1);
        mainMenuRoot.getChildren().add(helpLabel);
        mainMenuRoot.getChildren().add(helpButton);

        checkMovements(input);

    }

    public Scene getMainMenuScene() {
        return mainMenuScene;
    }
    public void checkMovements(String input) {

        createAccountButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (createAccountButtonImageView.getOpacity() == 100)
                    createAccountButtonImageView.setOpacity(0);
                if (createAccountButtonImageView1.getOpacity() == 0)
                    createAccountButtonImageView1.setOpacity(100);
            }
        });


        createAccountButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (createAccountButtonImageView1.getOpacity() == 100)
                    createAccountButtonImageView1.setOpacity(0);
                if (createAccountButtonImageView.getOpacity() == 0)
                    createAccountButtonImageView.setOpacity(100);
            }
        });



        loginButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (loginButtonImageView.getOpacity() == 100)
                    loginButtonImageView.setOpacity(0);
                if (loginButtonImageView1.getOpacity() == 0)
                    loginButtonImageView1.setOpacity(100);
            }
        });


        loginButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (loginButtonImageView1.getOpacity() == 100)
                    loginButtonImageView1.setOpacity(0);
                if (loginButtonImageView.getOpacity() == 0)
                    loginButtonImageView.setOpacity(100);
            }
        });

        showLeaderBoardButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (showLeaderBoardButtonImageView.getOpacity() == 100)
                    showLeaderBoardButtonImageView.setOpacity(0);
                if (showLeaderBoardButtonImageView1.getOpacity() == 0)
                    showLeaderBoardButtonImageView1.setOpacity(100);
            }
        });


        showLeaderBoardButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (showLeaderBoardButtonImageView1.getOpacity() == 100)
                    showLeaderBoardButtonImageView1.setOpacity(0);
                if (showLeaderBoardButtonImageView.getOpacity() == 0)
                    showLeaderBoardButtonImageView.setOpacity(100);
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

        createAccountButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                createAccountOrLoginClicked("create account");
            }
        });

        loginButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                createAccountOrLoginClicked("login");
            }
        });

        showLeaderBoardButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                showLeaderBoardClicked("leaderboard");
            }
        });

        helpButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
            }
        });


    }

    public void setImageView(ImageView buttonImageView, int n) {

        buttonImageView.setFitWidth(buttonSizeWidth);
        buttonImageView.setFitHeight(buttonSizeHeight);
        if (n%2==0) {
            buttonImageView.setX(100);
            buttonImageView.setY(100 + n*100);
        }
        else {
            buttonImageView.setX(300);
            buttonImageView.setY(100 + (n-1)*100);
        }

    }

    public void setButton(Button button, int n) {
        button.setOpacity(0);
        if (n % 2 == 0) {
            button.relocate(100, 100 + n*100);
        }
        else {
            button.relocate(300, 100 + (n-1)*100);
        }
        button.setPrefSize(buttonSizeWidth, buttonSizeHeight);
    }

    public void setLabel(Label label, int n) {
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Helvetica", FontWeight.BOLD, 17));
        if (n % 2 == 0) {
            label.relocate(150, 150 + n*100);
        }
        else {
            label.relocate(350, 150 + (n-1)*100 );
        }
    }

    public void createAccountOrLoginClicked(String s) {
        mainMenuRoot.getChildren().clear();
        mainMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Username:");
        usernameLabel.relocate(100, 50);
        TextField username = new TextField();
        username.relocate(100, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        mainMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        TextField pass = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.relocate(100, 150);
        passwordLabel.setTextFill(Color.BLACK);
        pass.relocate(100, 175);
        mainMenuRoot.getChildren().addAll(pass, passwordLabel);
        pass.setPrefSize(200, 50);
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setLabelFor(pass);
        Button back = new Button("back");
        back.setPrefSize(80, 50);
        back.relocate(100, 250);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(200, 250);
        mainMenuRoot.getChildren().add(back);
        mainMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "";
                        if (s.equals("login")) {
                            if (Menu.checkUsername(username.getText())) {
                                if (Menu.checkPassword(username.getText(), pass.getText())) {
                                    Text taken = new Text("login successfully!");
                                    taken.setFont(Font.font(30));
                                    taken.relocate(100, 350);
                                    mainMenuRoot.getChildren().add(taken);
                                    str += "login ";
                                    str += username.getText();
                                    str += " ";
                                    str += pass.getText();
                                    Menu.loginMenu(str);
                                } else {
                                    Text taken = new Text("invalid password!");
                                    taken.setFont(Font.font(30));
                                    taken.relocate(100, 350);
                                    mainMenuRoot.getChildren().add(taken);
                                }
                            } else {
                                Text taken = new Text("invalid username!");
                                taken.setFont(Font.font(30));
                                taken.relocate(100, 350);
                                mainMenuRoot.getChildren().add(taken);
                            }

                        } else {
                            if (Menu.checkUsername(username.getText())) {
                                Text taken = new Text("invalid username!");
                                taken.setFont(Font.font(30));
                                taken.relocate(100, 350);
                                mainMenuRoot.getChildren().add(taken);
                            } else {
                                Text taken = new Text("account <" + username.getText() + "> was created");
                                taken.setFont(Font.font(30));
                                taken.relocate(100, 350);
                                mainMenuRoot.getChildren().add(taken);
                                str += "create account ";
                                str += username.getText();
                                str += " ";
                                str += pass.getText();
                                Menu.loginMenu(str);
                            }
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

    public void showLeaderBoardClicked(String s) {
        mainMenuRoot.getChildren ().clear();
        mainMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect (blur);
        Menu.getUsers().sort(Comparator.comparing(User::getNumberOfZombiesKilled));
        int changeY = 50;
        for (int i = 0; i < Menu.getUsers().size(); i++) {
            Text text = new Text(i + 1 + "- UserName : " + Menu.getUsers().get(i).getUsername() + "- kills : " + Menu.getUsers().get(i).getNumberOfZombiesKilled());
            text.relocate(100, changeY);
            changeY += 50;
            mainMenuRoot.getChildren().add(text);
        }
        Menu.loginMenu("leaderboard");
    }
}

