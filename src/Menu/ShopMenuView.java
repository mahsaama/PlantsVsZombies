package Menu;
import Creature.Plant;
import Creature.Zombie;
import Shop.Shop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ShopMenuView {
    private Group shopMenuRoot;
    private Scene shopMenuScene;
    private GaussianBlur blur= new GaussianBlur ();

    private Image buttonImage = new Image("pics/beforeclick.webp");
    private Image buttonImage1 = new Image("pics/afterclick.webp");
    Image mainMenuBackgroundImage = new Image("pics/bg3.jpg");

    private Button showShopButton = new Button("Show shop");
    private Button collectionButton = new Button("Collection");
    private Button buyButton = new Button("Buy");
    private Button moneyButton = new Button("Money");
    private Button exitButton = new Button("Exit");
    private Button customCardButton = new Button("Custom Card");

    private Label showShopLabel = new Label("Show shop");
    private Label collectionLabel = new Label("Collection");
    private Label buyLabel = new Label("Buy");
    private Label moneyLabel = new Label("Money");
    private Label exitLabel = new Label("Exit");
    private Label customCardLabel = new Label("Custom Card");

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
    private ImageView customCardButtonImageView = new ImageView(buttonImage);
    private ImageView customCardButtonImageView1 = new ImageView(buttonImage1);
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
        setImageView(customCardButtonImageView, 5);
        setImageView(customCardButtonImageView1, 5);
        customCardButtonImageView1.setOpacity(0);
        setButton(customCardButton, 5);
        setLabel(customCardLabel, 5);
        shopMenuRoot.getChildren().add(customCardButtonImageView);
        shopMenuRoot.getChildren().add(customCardButtonImageView1);
        shopMenuRoot.getChildren().add(customCardLabel);
        shopMenuRoot.getChildren().add(customCardButton);


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

        customCardButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                enteredPlayer.play();
                enteredPlayer.seek(Duration.ZERO);
                if (customCardButtonImageView.getOpacity() == 100)
                    customCardButtonImageView.setOpacity(0);
                if (customCardButtonImageView1.getOpacity() == 0)
                    customCardButtonImageView1.setOpacity(100);
            }
        });


        customCardButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (customCardButtonImageView1.getOpacity() == 100)
                    customCardButtonImageView1.setOpacity(0);
                if (customCardButtonImageView.getOpacity() == 0)
                    customCardButtonImageView.setOpacity(100);
            }
        });


        showShopButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                try {
                    showShopButtonClicked();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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


        customCardButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);
                showHelpClicked("help");
            }
        });

    }

    public void setImageView(ImageView buttonImageView, int n) {
        if (n < 2) {
            buttonImageView.setFitWidth(buttonSizeWidth);
            buttonImageView.setFitHeight(buttonSizeHeight);
            buttonImageView.setX( 300+ n*200);
            buttonImageView.setY(50);
        }
        else if (n<4){
            buttonImageView.setFitWidth(buttonSizeWidth);
            buttonImageView.setFitHeight(buttonSizeHeight);
            buttonImageView.setX(400 + (n-2)*200);
            buttonImageView.setY(200);
        }
        else if (n<6){
            buttonImageView.setFitWidth(buttonSizeWidth);
            buttonImageView.setFitHeight(buttonSizeHeight);
            buttonImageView.setX(500 + (n-4)*200);
            buttonImageView.setY(350);
        }else{
            buttonImageView.setFitWidth(buttonSizeWidth);
            buttonImageView.setFitHeight(buttonSizeWidth);
            buttonImageView.setX(500 + (n-6)*200);
            buttonImageView.setY(500);
        }

    }

    public void setButton(Button button, int n) {
        button.setOpacity(0);
        if (n < 2) {
            button.relocate(300 + 200 * n, 50);
        }
        else if (n<4){
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
        else if (n<4){
            label.relocate(430 + 200 * (n-2), 270);
        }
        else{
            label.relocate(530 + 200 * (n-4), 420);
        }
    }
    public void showHelpClicked(String s){
        Menu.shopMenu("help");
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
                        Menu.shopMenuView();
                    }
                });
            }
        };
        back.setOnAction(event);

        //Type
        Button plantButton = new Button ("plant");
        Button zombieButton = new Button("zombie");
        shopMenuRoot.getChildren ().addAll (plantButton,zombieButton);
        plantButton.relocate(500, 200);
        zombieButton.relocate(500, 300);
        plantButton.resize (100,60);
        zombieButton.resize (100,60);
        //if type = plant
        plantButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                shopMenuRoot.getChildren ().removeAll (plantButton,zombieButton);
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);

                //name
                Label nameLabel = new Label("Insert name");
                nameLabel.relocate(200, 100);
                TextField name = new TextField();
                name.relocate(480, 350);
                nameLabel.setFont(Font.font(20));
                nameLabel.setLabelFor(name);
                nameLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(name, nameLabel);
                name.setPrefSize(200, 40);


                //sun
                Label sunNumbersLabel = new Label("Insert Sun Numbers");
                sunNumbersLabel.relocate(200, 100);
                TextField sunNumbers = new TextField();
                sunNumbers.relocate(480, 100);
                sunNumbersLabel.setFont(Font.font(20));
                sunNumbersLabel.setLabelFor(sunNumbers);
                sunNumbersLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(sunNumbers, sunNumbersLabel);
                sunNumbers.setPrefSize(200, 40);

                //bullet type
                Label bulletTypeLabel = new Label("Insert bullet type");
                bulletTypeLabel.relocate(200, 150);
                TextField bulletType = new TextField();
                bulletType.relocate(480, 150);
                bulletTypeLabel.setFont(Font.font(20));
                bulletTypeLabel.setLabelFor(bulletType);
                bulletTypeLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(bulletType, bulletTypeLabel);
                bulletType.setPrefSize(200, 40);

                //coolDown
                Label coolDownLabel = new Label("Insert coolDown");
                coolDownLabel.relocate(200, 200);
                TextField coolDown = new TextField();
                coolDown.relocate(480, 200);
                coolDownLabel.setFont(Font.font(20));
                coolDownLabel.setLabelFor(coolDown);
                coolDownLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(coolDown, coolDownLabel);
                coolDown.setPrefSize(200, 40);

                //hurtOfBullet
                Label hurtOfBulletLabel = new Label("Insert hurtOfBullet");
                hurtOfBulletLabel.relocate(200, 250);
                TextField hurtOfBullet = new TextField();
                hurtOfBullet.relocate(480, 250);
                hurtOfBulletLabel.setFont(Font.font(20));
                hurtOfBulletLabel.setLabelFor(hurtOfBullet);
                hurtOfBulletLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(hurtOfBullet, hurtOfBulletLabel);
                hurtOfBullet.setPrefSize(200, 40);

                //price
                Label priceLabel = new Label("Insert Price");
                priceLabel.relocate(200, 300);
                TextField price = new TextField();
                price.relocate(480, 300);
                priceLabel.setFont(Font.font(20));
                priceLabel.setLabelFor(price);
                priceLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(price, priceLabel);
                price.setPrefSize(200, 40);

                Plant plant = new Plant();
                plant.setName(name.getText ());
                plant.setNumberOfSuns (Integer.parseInt (sunNumbers.getText ()));
                plant.setBulletType (bulletType.getText ());
                plant.setCoolDown (Integer.parseInt (coolDown.getText ()));
                plant.setHurtOfBullet (Integer.parseInt (hurtOfBullet.getText ()));
                plant.setPrice (Integer.parseInt (price.getText ()));
                Shop.getPlantList ().add (plant);
                Shop.getPlantListNumbers ().add (3);
                //TODO pic
                Gson gson = new GsonBuilder ().setPrettyPrinting ().create ();
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter ("PlantCard/" +plant.getName ()+".json");
                    fileWriter.write (gson.toJson (plant));
                    fileWriter.close ();
                } catch (IOException e) {
                    e.printStackTrace ( );
                }
            }
        });


        zombieButton.setOnMouseClicked (new EventHandler<MouseEvent> ( ) {
            @Override
            public void handle(MouseEvent event) {
                shopMenuRoot.getChildren ().removeAll (plantButton,zombieButton);
                clickedPlayer.play ();
                clickedPlayer.seek(Duration.ZERO);

                //name
                Label nameLabel = new Label("Insert name");
                nameLabel.relocate(200, 100);
                TextField name = new TextField();
                name.relocate(480, 350);
                nameLabel.setFont(Font.font(20));
                nameLabel.setLabelFor(name);
                nameLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(name, nameLabel);
                name.setPrefSize(200, 40);


                //life
                Label lifeLabel = new Label("Insert life");
                lifeLabel.relocate(200, 100);
                TextField life = new TextField();
                life.relocate(480, 100);
                lifeLabel.setFont(Font.font(20));
                lifeLabel.setLabelFor(life);
                lifeLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(life, lifeLabel);
                life.setPrefSize(200, 40);

                //speed
                Label speedLabel = new Label("Insert speed");
                speedLabel.relocate(200, 150);
                TextField speed = new TextField();
                speed.relocate(480, 150);
                speedLabel.setFont(Font.font(20));
                speedLabel.setLabelFor(speed);
                speedLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(speed, speedLabel);
                speed.setPrefSize(200, 40);

                //car
                Label carLabel = new Label("Insert car");
                carLabel.relocate(200, 200);
                TextField car = new TextField();
                car.relocate(480, 200);
                carLabel.setFont(Font.font(20));
                carLabel.setLabelFor(car);
                carLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(car, carLabel);
                car.setPrefSize(200, 40);

                //hat
                Label hatLabel = new Label("Insert hat");
                hatLabel.relocate(200, 250);
                TextField hat = new TextField();
                hat.relocate(480, 250);
                hatLabel.setFont(Font.font(20));
                hatLabel.setLabelFor(hat);
                hatLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(hat, hatLabel);
                hat.setPrefSize(200, 40);

                //price
                Label priceLabel = new Label("Insert Price");
                priceLabel.relocate(200, 300);
                TextField price = new TextField();
                price.relocate(480, 300);
                priceLabel.setFont(Font.font(20));
                priceLabel.setLabelFor(price);
                priceLabel.setTextFill(Color.BLACK);
                shopMenuRoot.getChildren().addAll(price, priceLabel);
                price.setPrefSize(200, 40);

                Zombie zombie = new Zombie ();
                zombie.setName(name.getText ());
                zombie.setLife (Integer.parseInt (life.getText ()));
                zombie.setSpeed (Integer.parseInt (speed.getText ()));
                if(speed.getText ().compareToIgnoreCase ("false") == 0){
                    zombie.setCar (false);
                }
                else
                    zombie.setCar (true);
                if(hat.getText ().compareToIgnoreCase ("false") == 0){
                    zombie.setHat (false);
                }
                else
                    zombie.setHat (true);
                zombie.setPrice (Integer.parseInt (price.getText ()));
                Shop.getZombieList ().add (zombie);
                Shop.getZombieListNumbers ().add (3);
                //TODO pic
                Gson gson = new GsonBuilder ().setPrettyPrinting ().create ();
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter ("ZombieCard/" +zombie.getName ()+".json");
                    fileWriter.write (gson.toJson (zombie));
                    fileWriter.close ();
                } catch (IOException e) {
                    e.printStackTrace ( );
                }
            }

        });
        Menu.playMenu(s);
    }


    public void showShopButtonClicked() throws FileNotFoundException {
        Menu.shopMenu("showshop");
        shopMenuRoot.getChildren().clear();
        shopMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Button back = new Button("Back");
        back.setPrefSize(200, 80);
        back.relocate(925, 570);
        shopMenuRoot.getChildren().add(back);
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.shopMenuView();
                    }
                });
            }
        };
        back.setOnAction(event2);
        for (int i=0 ; i< Shop.getPlantList().size() + Shop.getZombieList().size() ;i++){
            if (i < Shop.getPlantList().size()) {
                try {
                    Image listImage = new Image("pics/" + Shop.getPlantList().get(i).getName() + ".jpg");
                    ImageView listImageView = new ImageView(listImage);
                    listImageView.setFitWidth(100);
                    listImageView.setFitHeight(100);
                    listImageView.setX(25 + (i % 8) * 150);
                    listImageView.setY(10 + (i / 8) * 140);
                    shopMenuRoot.getChildren().add(listImageView);
                    Label priceLabel = new Label(Shop.getPlantList().get(i).getPrice() + "");
                    priceLabel.relocate(25 + (i % 8) * 150, 110 + (i / 8) * 140);
                    priceLabel.setFont(Font.font(20));
                    priceLabel.setTextFill(Color.WHITE);
                    priceLabel.setPrefSize(100, 30);
                    shopMenuRoot.getChildren().add(priceLabel);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println(Shop.getPlantList().get(i).getName());
                }
            }else{
                Image listImage = new Image("pics/"+Shop.getZombieList().get(i-Shop.getPlantList().size()).getName()+".jpg");
                ImageView listImageView = new ImageView(listImage);
                listImageView.setFitWidth(100);
                listImageView.setFitHeight(100);
                listImageView.setX(25+ (i%8)*150);
                listImageView.setY(10+ (i/8)*140);
                shopMenuRoot.getChildren().add(listImageView);
                Label priceLabel = new Label(Shop.getPlantList().get(i -Shop.getPlantList().size()).getPrice()+"");
                priceLabel.relocate(25+ (i%8)*150, 110 +(i/8)*140);
                priceLabel.setFont(Font.font(20));
                priceLabel.setTextFill(Color.WHITE);
                priceLabel.setPrefSize(100,30);
                shopMenuRoot.getChildren().add(priceLabel);
            }
        }

    }

    public void buyButtonClicked(){
        shopMenuRoot.getChildren().clear();
        shopMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Label cardNameLabel = new Label("Insert the card name:");
        cardNameLabel.relocate(350, 50);
        TextField cardName = new TextField();
        cardName.relocate(350, 75);
        cardNameLabel.setFont(Font.font(20));
        cardNameLabel.setLabelFor(cardName);
        cardNameLabel.setTextFill(Color.BLACK);
        shopMenuRoot.getChildren().addAll(cardName, cardNameLabel);
        cardName.setPrefSize(200, 50);
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(350, 150);
        Button ok = new Button("OK");
        ok.setPrefSize(80, 50);
        ok.relocate(450, 150);
        shopMenuRoot.getChildren().add(back);
        shopMenuRoot.getChildren().add(ok);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String str = "buy";
                        Text taken = new Text();
                        taken.setFont(Font.font(30));
                        taken.relocate(450, 250);
                        shopMenuRoot.getChildren().add(taken);
                        Boolean found = false;
                        for(int i = 0 ; i <  Shop.getPlantList().size (); i++){
                            if(Shop.getPlantList ().get (i).getName ().compareTo(cardName.getText()) == 0){
                                found = true;
                                if(Shop.getPlantListNumbers ().get (i) != 0) {
                                    if (Menu.getLoginUser ( ).getCoins ( ) >= Shop.getPlantList ( ).get (i).getPrice ( )) {
                                        str += " " + cardName.getText ( );
                                        taken.setText ("you bought this plant successfully");
                                        Shop.getPlantListNumbers ().set (i,Shop.getPlantListNumbers ().get (i) - 1);
                                        Menu.shopMenu (str);
                                    } else
                                        taken.setText ("not enough money");
                                }
                                else
                                    taken.setText ("not available");
                            }
                        }
                        if(!found){
                            for(int i = 0 ; i <  Shop.getZombieList ().size (); i++){
                                if(Shop.getZombieList ().get (i).getName ().compareTo (cardName.getText()) == 0){
                                    found = true;
                                    if(Shop.getZombieListNumbers ().get (i) != 0) {
                                        if (Menu.getLoginUser ( ).getCoins ( ) >= Shop.getZombieList ( ).get (i).getPrice ( )) {
                                            str += " " + cardName.getText ( );
                                            taken.setText ("you bought this zombie successfully");
                                            Shop.getZombieListNumbers ().set (i,Shop.getZombieListNumbers ().get (i) - 1);
                                            Menu.shopMenu (str);
                                        } else
                                            taken.setText ("not enough money");
                                    }
                                    else
                                        System.out.println ("not available" );

                                }
                            }
                        }
                        if(!found){
                            taken.setText("invalid name");
                            Menu.profileMenu(str);
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
                        Menu.shopMenuView();
                    }
                });
            }
        };
        cardName.setOnAction(event1);
        ok.setOnAction(event1);
        back.setOnAction(event2);
    }

    public void moneyButtonClicked(){
        Menu.shopMenu("money");
        shopMenuRoot.getChildren().clear();
        shopMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Image chestImage = new Image("pics/ch.png");
        ImageView chestImageView = new ImageView(chestImage);
        setImageView(chestImageView, 6);
        shopMenuRoot.getChildren().add(chestImageView);
        Label moneyLabel = new Label("Your money:");
        moneyLabel.relocate(450, 100);
        Label money = new Label(Menu.getLoginUser().getCoins() + "");
        money.relocate(450, 150);
        moneyLabel.setFont(Font.font(30));
        moneyLabel.setLabelFor(money);
        moneyLabel.setTextFill(Color.BLACK);
        shopMenuRoot.getChildren().addAll(money, moneyLabel);
        money.setPrefSize(300, 100);
        money.setFont(Font.font(40));
        Button back = new Button("Back");
        back.setPrefSize(80, 50);
        back.relocate(500, 550);
        shopMenuRoot.getChildren().add(back);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.shopMenuView();
                    }
                });
            }
        };
        back.setOnAction(event);
    }

    public void collectionButtonClicked(){
        Menu.shopMenu("collection");
        shopMenuRoot.getChildren().clear();
        shopMenuRoot.getChildren().add(backgroundImageView);
        backgroundImageView.setEffect(blur);
        Button back = new Button("Back");
        back.setPrefSize(200, 80);
        back.relocate(925, 570);
        shopMenuRoot.getChildren().add(back);
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Menu.shopMenuView();
                    }
                });
            }
        };
        back.setOnAction(event2);
        for (int i=0 ; i< Menu.getLoginUser().getCollection().getPlants().size() + Menu.getLoginUser().getCollection().getZombies().size() ;i++){
            if (i < Menu.getLoginUser().getCollection().getPlants().size()) {
                try{
                    Image listImage = new Image("/pics"+ Menu.getLoginUser().getCollection().getPlants().get(i).getName()+".jpg");
                    ImageView listImageView = new ImageView(listImage);
                    listImageView.setFitWidth(100);
                    listImageView.setFitHeight(100);
                    listImageView.setX(25+ (i%8)*150);
                    listImageView.setY(10+ (i/8)*140);
                    shopMenuRoot.getChildren().add(listImageView);
                    Label priceLabel = new Label(Menu.getLoginUser().getCollection().getPlants().get(i).getPrice()+"");
                    priceLabel.relocate(25+ (i%8)*150, 110 +(i/8)*140);
                    priceLabel.setFont(Font.font(20));
                    priceLabel.setTextFill(Color.WHITE);
                    priceLabel.setPrefSize(100,30);
                    shopMenuRoot.getChildren().add(priceLabel);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }else{
                Image listImage = new Image("pics/"+Menu.getLoginUser().getCollection().getZombies().get(i-Menu.getLoginUser().getCollection().getPlants().size()).getName()+".jpg");
                ImageView listImageView = new ImageView(listImage);
                listImageView.setFitWidth(100);
                listImageView.setFitHeight(100);
                listImageView.setX(25+ (i%8)*150);
                listImageView.setY(10+ (i/8)*140);
                shopMenuRoot.getChildren().add(listImageView);
                Label priceLabel = new Label(Menu.getLoginUser().getCollection().getZombies().get(i-Menu.getLoginUser().getCollection().getPlants().size()).getPrice()+"");
                priceLabel.relocate(25+ (i%8)*150, 110 +(i/8)*140);
                priceLabel.setFont(Font.font(20));
                priceLabel.setTextFill(Color.WHITE);
                priceLabel.setPrefSize(100,30);
                shopMenuRoot.getChildren().add(priceLabel);
            }
        }
    }
}
