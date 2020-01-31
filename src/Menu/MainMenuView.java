package Menu;

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

public class MainMenuView {
    private Group mainMenuRoot;
    private Scene mainMenuScene;
    private GaussianBlur blur= new GaussianBlur ();

    private Image buttonImage = new Image("pics/beforeclick.webp");
    private Image buttonImage1 = new Image("pics/afterclick.webp");
    private Image mainMenuBackgroundImage = new Image("pics/bg2.jpg");

    private Button playButton = new Button("Play");
    private Button profileButton = new Button("Profile");
    private Button shopButton = new Button("Shop");
    private Button exitButton = new Button("Exit");
    private Button helpButton = new Button("Help");

    private Label playLabel = new Label("Play");
    private Label profileLabel = new Label("Profile");
    private Label shopLabel = new Label("Shop");
    private Label exitLabel = new Label("Exit");
    private Label helpLabel = new Label("Help");

    private final int buttonSizeWidth = 200;
    private final int buttonSizeHeight = 200;
    private final int height = 700;
    private final int width = 1200;

    private ImageView playButtonImageView = new ImageView(buttonImage);
    private ImageView playButtonImageView1 = new ImageView(buttonImage1);
    private ImageView profileButtonImageView = new ImageView(buttonImage);
    private ImageView profileButtonImageView1 = new ImageView(buttonImage1);
    private ImageView shopButtonImageView = new ImageView(buttonImage);
    private ImageView shopButtonImageView1 = new ImageView(buttonImage1);
    private ImageView exitButtonImageView = new ImageView(buttonImage);
    private ImageView exitButtonImageView1 = new ImageView(buttonImage1);
    private ImageView helpButtonImageView = new ImageView(buttonImage);
    private ImageView helpButtonImageView1 = new ImageView(buttonImage1);
    private ImageView backgroundImageView = new ImageView(mainMenuBackgroundImage);

    private Media mouseClicked = new Media(getClass ( ).getClassLoader ().getResource ("audio/Button-SoundBible.com-1420500901.mp3" ).toExternalForm ());
    private Media mouseEntered= new Media (getClass().getClassLoader().getResource ("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm ());
    MediaPlayer clickedPlayer= new MediaPlayer (mouseClicked);
    MediaPlayer enteredPlayer= new MediaPlayer (mouseEntered);

    public MainMenuView() {

        enteredPlayer.setVolume(0.1);
        mainMenuRoot = new Group();
        backgroundImageView.setFitHeight(height);
        backgroundImageView.setFitWidth(width);
        mainMenuScene = new Scene(mainMenuRoot, width, height);
        mainMenuRoot.getChildren().add(backgroundImageView);


        //play
        setImageView(playButtonImageView, 0);
        setImageView(playButtonImageView1, 0);
        playButtonImageView1.setOpacity(0);
        setButton(playButton, 0);
        setLabel(playLabel, 0);
        mainMenuRoot.getChildren().add(playButtonImageView);
        mainMenuRoot.getChildren().add(playButtonImageView1);
        mainMenuRoot.getChildren().add(playButton);
        mainMenuRoot.getChildren().add(playLabel);

        //profile
        setImageView(profileButtonImageView, 1);
        setImageView(profileButtonImageView1, 1);
        profileButtonImageView1.setOpacity(0);
        setButton(profileButton, 1);
        setLabel(profileLabel, 1);
        mainMenuRoot.getChildren().add(profileButtonImageView);
        mainMenuRoot.getChildren().add(profileButtonImageView1);
        mainMenuRoot.getChildren().add(profileLabel);
        mainMenuRoot.getChildren().add(profileButton);

        //shop
        setImageView(shopButtonImageView, 2);
        setImageView(shopButtonImageView1, 2);
        shopButtonImageView1.setOpacity(0);
        setButton(shopButton , 2);
        setLabel(shopLabel, 2);
        mainMenuRoot.getChildren().add((shopButtonImageView));
        mainMenuRoot.getChildren().add((shopButtonImageView1));
        mainMenuRoot.getChildren().add(shopLabel);
        mainMenuRoot.getChildren().add(shopButton );

        //exit
        setImageView(exitButtonImageView, 3);
        setImageView(exitButtonImageView1, 3);
        exitButtonImageView1.setOpacity(0);
        setButton(exitButton, 3);
        setLabel(exitLabel, 3);
        mainMenuRoot.getChildren().add(exitButtonImageView);
        mainMenuRoot.getChildren().add(exitButtonImageView1);
        mainMenuRoot.getChildren().add(exitLabel);
        mainMenuRoot.getChildren().add(exitButton);

        //help
        setImageView(helpButtonImageView, 4);
        setImageView(helpButtonImageView1, 4);
        helpButtonImageView1.setOpacity(0);
        setButton(helpButton, 4);
        setLabel(helpLabel, 4);
        mainMenuRoot.getChildren().add(helpButtonImageView);
        mainMenuRoot.getChildren().add(helpButtonImageView1);
        mainMenuRoot.getChildren().add(helpLabel);
        mainMenuRoot.getChildren().add(helpButton);

        checkMovements();

    }

    public Scene getMainMenuScene() {
        return mainMenuScene;
    }

    public void checkMovements() {

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

                if ( playButtonImageView1.getOpacity() == 100)
                    playButtonImageView1.setOpacity(0);
                if ( playButtonImageView.getOpacity() == 0)
                    playButtonImageView.setOpacity(100);
            }
        });



        profileButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (profileButtonImageView.getOpacity() == 100)
                    profileButtonImageView.setOpacity(0);
                if (profileButtonImageView1.getOpacity() == 0)
                    profileButtonImageView1.setOpacity(100);
            }
        });


        profileButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (profileButtonImageView1.getOpacity() == 100)
                    profileButtonImageView1.setOpacity(0);
                if (profileButtonImageView.getOpacity() == 0)
                    profileButtonImageView.setOpacity(100);
            }
        });

        shopButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (shopButtonImageView.getOpacity() == 100)
                    shopButtonImageView.setOpacity(0);
                if (shopButtonImageView1.getOpacity() == 0)
                    shopButtonImageView1.setOpacity(100);
            }
        });


        shopButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (shopButtonImageView1.getOpacity() == 100)
                    shopButtonImageView1.setOpacity(0);
                if (shopButtonImageView.getOpacity() == 0)
                    shopButtonImageView.setOpacity(100);
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


        playButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.playMenuView();
            }
        });

        profileButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.profileMenuView();
            }
        });

        shopButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.shopMenuView();
            }
        });

        exitButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.mainMenu("exit");
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
        if (n <= 2) {
            buttonImageView.setX(500 + n*200);
            buttonImageView.setY(300);
        }
        else {
            buttonImageView.setX(600 + (n-3)*200);
            buttonImageView.setY(500);
        }

    }

    public void setButton(Button button, int n) {
        button.setOpacity(0);
        if (n <= 2) {
            button.relocate(500 + 200 * n, 300);
        }
        else {
            button.relocate(600 + (n-3)*200, 500);
        }
        button.setPrefSize(buttonSizeWidth, buttonSizeHeight);
    }

    public void setLabel(Label label, int n) {
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
            if (n <= 2) {
                label.relocate(550 + 200 * n, 410);
            }
            else{
                label.relocate(650 + (n-3)*200 ,610 );
            }
    }
    public void showHelpClicked(String s){
        mainMenuRoot.getChildren ().clear();
        mainMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(100, 600);
        mainMenuRoot.getChildren().add(back);
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
                "1)Press play button to play the game\n" +
                "2)Press profile button for doing these actions on username\n" +
                "\t 1)change\n" +
                "\t 2)delete\n" +
                "\t 3)rename\n" +
                "\t 4)create\n" +
                "\t 5)show\n" +
                "3)Press shop button to go to the shop\n" +
                "4)Press exit button to go to the login menu\n");
        taken.setFont(Font.font(30));
        taken.setFill(Color.WHITE);
        taken.relocate(200, 100);
        mainMenuRoot.getChildren().add(taken);
        Menu.mainMenu(s);
    }
}
