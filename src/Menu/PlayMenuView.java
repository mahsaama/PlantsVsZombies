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

public class PlayMenuView {
    private Group playMenuRoot;
    private Scene playMenuScene;
    private GaussianBlur blur= new GaussianBlur ();

    private Image buttonImage = new Image("pics/beforeclick.webp");
    private Image buttonImage1 = new Image("pics/afterclick.webp");
    private Image mainMenuBackgroundImage = new Image("pics/bg3.jpg");

    private Button dayButton = new Button("Day");
    private Button waterButton = new Button("Water");
    private Button railButton = new Button("Rail");
    private Button zombieButton = new Button("Zombie");
    private Button pvpButton = new Button("PvP");
    private Button exitButton = new Button("Exit");

    private Label dayLabel = new Label("Day");
    private Label waterLabel = new Label("Water");
    private Label railLabel = new Label("Rail");
    private Label zombieLabel = new Label("Zombie");
    private Label pvpLabel = new Label("PvP");
    private Label exitLabel = new Label("Exit");


    private final int buttonSizeWidth = 120;
    private final int buttonSizeHeight = 120;
    private final int height = 700;
    private final int width = 1200;

    private ImageView dayButtonImageView = new ImageView(buttonImage);
    private ImageView dayButtonImageView1 = new ImageView(buttonImage1);
    private ImageView waterButtonImageView = new ImageView(buttonImage);
    private ImageView waterButtonImageView1 = new ImageView(buttonImage1);
    private ImageView railButtonImageView = new ImageView(buttonImage);
    private ImageView railButtonImageView1 = new ImageView(buttonImage1);
    private ImageView zombieButtonImageView = new ImageView(buttonImage);
    private ImageView zombieButtonImageView1 = new ImageView(buttonImage1);
    private ImageView pvpButtonImageView = new ImageView(buttonImage);
    private ImageView pvpButtonImageView1 = new ImageView(buttonImage1);
    private ImageView exitButtonImageView = new ImageView(buttonImage);
    private ImageView exitButtonImageView1 = new ImageView(buttonImage1);
    private ImageView backgroundImageView = new ImageView(mainMenuBackgroundImage);

    private Media mouseClicked = new Media(getClass ( ).getClassLoader ().getResource ("audio/Button-SoundBible.com-1420500901.mp3" ).toExternalForm ());
    private Media mouseEntered= new Media (getClass().getClassLoader().getResource ("audio/zapsplat_multimedia_click_003_19369.mp3").toExternalForm ());
    MediaPlayer clickedPlayer= new MediaPlayer (mouseClicked);
    MediaPlayer enteredPlayer= new MediaPlayer (mouseEntered);

    public PlayMenuView() {

        enteredPlayer.setVolume(0.1);
        playMenuRoot = new Group();
        backgroundImageView.setFitHeight(height);
        backgroundImageView.setFitWidth(width);
        playMenuScene = new Scene(playMenuRoot, width, height);
        playMenuRoot.getChildren().add(backgroundImageView);


        //day
        setImageView(dayButtonImageView, 0);
        setImageView(dayButtonImageView1, 0);
        dayButtonImageView1.setOpacity(0);
        setButton(dayButton, 0);
        setLabel(dayLabel, 0);
        playMenuRoot.getChildren().add(dayButtonImageView);
        playMenuRoot.getChildren().add(dayButtonImageView1);
        playMenuRoot.getChildren().add(dayButton);
        playMenuRoot.getChildren().add(dayLabel);

        //water
        setImageView(waterButtonImageView, 1);
        setImageView(waterButtonImageView1, 1);
        waterButtonImageView1.setOpacity(0);
        setButton(waterButton, 1);
        setLabel(waterLabel, 1);
        playMenuRoot.getChildren().add(waterButtonImageView);
        playMenuRoot.getChildren().add(waterButtonImageView1);
        playMenuRoot.getChildren().add(waterLabel);
        playMenuRoot.getChildren().add(waterButton);

        //rail
        setImageView(railButtonImageView, 2);
        setImageView(railButtonImageView1, 2);
        railButtonImageView1.setOpacity(0);
        setButton(railButton , 2);
        setLabel(railLabel, 2);
        playMenuRoot.getChildren().add((railButtonImageView));
        playMenuRoot.getChildren().add((railButtonImageView1));
        playMenuRoot.getChildren().add(railLabel);
        playMenuRoot.getChildren().add(railButton);

        //zombie
        setImageView(zombieButtonImageView, 3);
        setImageView(zombieButtonImageView1, 3);
        zombieButtonImageView1.setOpacity(0);
        setButton(zombieButton , 3);
        setLabel(zombieLabel, 3);
        playMenuRoot.getChildren().add((zombieButtonImageView));
        playMenuRoot.getChildren().add((zombieButtonImageView1));
        playMenuRoot.getChildren().add(zombieLabel);
        playMenuRoot.getChildren().add(zombieButton);

        //pvp
        setImageView(pvpButtonImageView, 4);
        setImageView(pvpButtonImageView1, 4);
        pvpButtonImageView1.setOpacity(0);
        setButton(pvpButton , 4);
        setLabel(pvpLabel, 4);
        playMenuRoot.getChildren().add((pvpButtonImageView));
        playMenuRoot.getChildren().add((pvpButtonImageView1));
        playMenuRoot.getChildren().add(pvpLabel);
        playMenuRoot.getChildren().add(pvpButton);

        //exit
        setImageView(exitButtonImageView, 5);
        setImageView(exitButtonImageView1, 5);
        exitButtonImageView1.setOpacity(0);
        setButton(exitButton, 5);
        setLabel(exitLabel, 5);
        playMenuRoot.getChildren().add(exitButtonImageView);
        playMenuRoot.getChildren().add(exitButtonImageView1);
        playMenuRoot.getChildren().add(exitLabel);
        playMenuRoot.getChildren().add(exitButton);

        checkMovements();

    }

    public Scene getPlayMenuScene() {
        return playMenuScene;
    }

    public void checkMovements() {

        dayButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (dayButtonImageView.getOpacity() == 100)
                    dayButtonImageView.setOpacity(0);
                if (dayButtonImageView1.getOpacity() == 0)
                    dayButtonImageView1.setOpacity(100);
            }
        });


        dayButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if ( dayButtonImageView1.getOpacity() == 100)
                    dayButtonImageView1.setOpacity(0);
                if ( dayButtonImageView.getOpacity() == 0)
                    dayButtonImageView.setOpacity(100);
            }
        });


        waterButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (waterButtonImageView.getOpacity() == 100)
                    waterButtonImageView.setOpacity(0);
                if (waterButtonImageView1.getOpacity() == 0)
                    waterButtonImageView1.setOpacity(100);
            }
        });


        waterButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (waterButtonImageView1.getOpacity() == 100)
                    waterButtonImageView1.setOpacity(0);
                if (waterButtonImageView.getOpacity() == 0)
                    waterButtonImageView.setOpacity(100);
            }
        });

        railButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (railButtonImageView.getOpacity() == 100)
                    railButtonImageView.setOpacity(0);
                if (railButtonImageView1.getOpacity() == 0)
                    railButtonImageView1.setOpacity(100);
            }
        });


        railButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (railButtonImageView1.getOpacity() == 100)
                    railButtonImageView1.setOpacity(0);
                if (railButtonImageView.getOpacity() == 0)
                    railButtonImageView.setOpacity(100);
            }
        });

        zombieButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (zombieButtonImageView.getOpacity() == 100)
                    zombieButtonImageView.setOpacity(0);
                if (zombieButtonImageView1.getOpacity() == 0)
                    zombieButtonImageView1.setOpacity(100);
            }
        });


        zombieButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (zombieButtonImageView1.getOpacity() == 100)
                    zombieButtonImageView1.setOpacity(0);
                if (zombieButtonImageView.getOpacity() == 0)
                    zombieButtonImageView.setOpacity(100);
            }
        });

        pvpButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (pvpButtonImageView.getOpacity() == 100)
                    pvpButtonImageView.setOpacity(0);
                if (pvpButtonImageView1.getOpacity() == 0)
                    pvpButtonImageView1.setOpacity(100);
            }
        });


        pvpButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (pvpButtonImageView1.getOpacity() == 100)
                    pvpButtonImageView1.setOpacity(0);
                if (pvpButtonImageView.getOpacity() == 0)
                    pvpButtonImageView.setOpacity(100);
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

        dayButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.playMenu("Day");
            }
        });

        zombieButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.playMenu("Zombie");
            }
        });

        waterButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.playMenu("Water");
            }
        });

        railButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.playMenu("Rail");
            }
        });

        pvpButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                pvpButtonClicked();
            }
        });

        exitButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                Menu.mainMenuView();
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
            label.relocate(340 + 200 * n, 120);
        }
        else if (n>1 && n<4){
            label.relocate(440 + 200 * (n-2), 270);
        }
        else{
            label.relocate(540 + 200 * (n-4), 420);
        }
    }
    public void pvpButtonClicked(){
        playMenuRoot.getChildren().clear();
        playMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label usernameLabel = new Label("Opponent Username:");
        usernameLabel.relocate(350, 50);
        TextField username = new TextField();
        username.relocate(350, 75);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setLabelFor(username);
        usernameLabel.setTextFill(Color.BLACK);
        playMenuRoot.getChildren().addAll(username, usernameLabel);
        username.setPrefSize(200, 50);
        TextField wave = new TextField();
        Label waveLabel = new Label("Number of waves:");
        waveLabel.relocate(350, 150);
        waveLabel.setTextFill(Color.BLACK);
        wave.relocate(350, 175);
        playMenuRoot.getChildren().addAll(wave, waveLabel);
        wave.setPrefSize(200, 50);
        waveLabel.setFont(Font.font(20));
        waveLabel.setLabelFor(wave);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(350, 250);
        playMenuRoot.getChildren().add(back);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(450, 250);
        playMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "PvP";
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(450, 400);
                        playMenuRoot.getChildren().add(taken);
                        if (Menu.checkUsername(username.getText()) && Menu.getLoginUser().getUsername().compareToIgnoreCase(username.getText())!=0) {
                            str += " " + username.getText() + " " + wave.getText();
                            Menu.playMenu(str);
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
                        Menu.playMenuView();
                    }
                });
            }
        };
        back.setOnAction(event2);
        wave.setOnAction(event1);
        username.setOnAction(event1);
        ok.setOnAction(event1);
    }
}
