package Menu;

import Creature.Plant;
import Creature.Zombie;
import Game.Game;
import Map.PlayGround;
import Shop.Shop;
import User.User;
import User.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static javafx.application.Platform.runLater;

public class Menu extends Application {

    @Override
    public void start(Stage PrimaryStage) throws Exception {

    }

    private static Scanner scanner = new Scanner(System.in);
    private static User loginUser;
    private static User opponent;
    private static User tempUser;
    private static ArrayList<User> users = new ArrayList<User>();
    private static Shop shop = new Shop();
    private static int numberOfWaves;
    static String input = new String();

    public static User getLoginUser() {
        return loginUser;
    }

    public static User getTempUser() {
        return tempUser;
    }

    public static void loginMenuView() {
        LoginMenuView menuView = new LoginMenuView(input);
        Main.changeScene(menuView.getLoginMenuScene());
        input = "";
    }

    public static void loginMenu(String input) {
        String order = input;
        String[] array = input.split(" ");
        //while (true) {
            if (array[0].compareToIgnoreCase("create") == 0 && array[1].compareToIgnoreCase("account") == 0) {
                //String username = scanner.nextLine();
                //String password = scanner.nextLine();
                createAccount(array[2], array[3]);
            } else if (array[0].compareToIgnoreCase("login") == 0) {
                //String username = scanner.nextLine();
               // String password = scanner.nextLine();
                if (login(array[1], array[2])){
                    //break;
                }
            } else if (order.compareToIgnoreCase("leaderboard") == 0) {
                leaderboard();
            } else if (order.compareToIgnoreCase("help") == 0) {
                System.out.println("create account\nlogin\nleaderboard\nhelp\nexit");
            } else if (order.compareToIgnoreCase("exit") == 0) {
                return;
            } else
                System.out.println("invalid command");
           // order = scanner.nextLine();
       // }

    }

    private static void leaderboard() {
        users.sort(Comparator.comparing(User::getNumberOfZombiesKilled));
        for (User user : users) {
            System.out.println(user.getUsername() + ": " + user.getNumberOfZombiesKilled());
        }
    }

    private static boolean login(String username, String password) {
        if (checkUsername(username)) {
            if (checkPassword(username, password)) {
                System.out.println("login successfully");
                loginUser = getUserByName(username);
                mainMenuView();
                return true;
            } else
                System.out.println("invalid password");
        } else
            System.out.println("invalid username");
        return false;
    }

    private static void createAccount(String username, String password) {
        if (!checkUsername(username)) {
            User user = new User(username, password);
            users.add(user);
            System.out.println("account <" + user.getUsername() + "> was created");
            Shop.setFirstCards(user);
        } else
            System.out.println("invalid username");

    }

    public static void mainMenuView(){
        MainMenuView menuView = new MainMenuView();
        Main.changeScene(menuView.getMainMenuScene());
    }
    public static void mainMenu(String s) {
        String order = s;
        //while (true) {
            if (order.compareToIgnoreCase("Play") == 0) {
                playMenuView();
                //break;
            } else if (order.compareToIgnoreCase("profile") == 0) {
                profileMenuView();
               // break;
            } else if (order.compareToIgnoreCase("shop") == 0) {
                shopMenuView();
                //break;
            } else if (order.compareToIgnoreCase("exit") == 0) {
                loginMenuView();
                //break;
            } else if (order.compareToIgnoreCase("help") == 0) {
                System.out.println("Play\nprofile\nshop\nexit\nhelp");
            } //else
               // System.out.println("invalid command");
            //order = scanner.nextLine();
        //}
    }

    public static void profileMenuView(){
        ProfileMenuView menuView = new ProfileMenuView();
        Main.changeScene(menuView.getProfileMenuScene());
    }
    public static void profileMenu(String s) {
        String order = s;
        String[] array = s.split(" ");
        //while (true) {
            if (array[0].compareToIgnoreCase("Change") == 0) {
                //String username = scanner.nextLine();
                //String password = scanner.nextLine();
                loginUser.setUsername(array[1]);
                loginUser.setPassword(array[2]);
            } else if (array[0].compareToIgnoreCase("Delete") == 0) {
                //String username = scanner.nextLine();
                //String password = scanner.nextLine();
                removeUser(array[1], array[2]);
            } else if (array[0].compareToIgnoreCase("Rename") == 0) {
                //String newUsername = scanner.nextLine();
                loginUser.setUsername(array[1]);
            } else if (array[0].compareToIgnoreCase("Create") == 0) {
               // String username = scanner.nextLine();
                //String password = scanner.nextLine();
                if (!checkUsername(array[1])) {
                    User user = new User(array[1], array[2]);
                    users.add(user);
                    profileMenuView();
                    //break;
                } else
                    System.out.println("invalid username");
            } else if (order.compareToIgnoreCase("Show") == 0) {
                System.out.println(loginUser.getUsername());
            } else if (order.compareToIgnoreCase("help") == 0) {
                System.out.println("Change\nDelete\nRename\nCreate\nShow\nhelp\nexit");
            } else if (order.compareToIgnoreCase("exit") == 0) {
                mainMenuView();
                //break;
            //} else
               // System.out.println("invalid command");
           // order = scanner.nextLine();
            }
    }

    private static void removeUser(String username, String password) {
        if (loginUser.getUsername().compareTo(username) == 0) {
            if (checkPassword(username, password)) {
                users.remove(getUserByName(username));
            } else
                System.out.println("invalid password");
        } else
            System.out.println("invalid username");
    }
    public static void shopMenuView(){
        ShopMenuView menuView = new ShopMenuView();
        Main.changeScene(menuView.getShopMenuScene());
    }
    public static void shopMenu(String s) {
        String order = s;
        String[] array = s.split(" ");
       // while (true) {
            if (array[0].compareToIgnoreCase("showshop") == 0) {
                for (Plant plant : shop.getPlantList()) {
                    System.out.println(plant.getName() + " : " + plant.getPrice());
                }
                for (Zombie zombie : shop.getZombieList()) {
                    System.out.println(zombie.getName() + " : " + zombie.getPrice());
                }
            } else if (array[0].compareToIgnoreCase("collection") == 0) {
                for (Plant plant : loginUser.getCollection().getPlants()) {
                    System.out.println(plant.getName() + " : " + plant.getPrice());
                }
                for (Zombie zombie : loginUser.getCollection().getZombies()) {
                    System.out.println(zombie.getName() + " : " + zombie.getPrice());
                }
            } else if (array[0].compareToIgnoreCase("buy") == 0) {
                //String cardName = scanner.nextLine();
                shop.buy(array[1], loginUser);
            } else if (array[0].compareToIgnoreCase("money") == 0) {
                System.out.println(loginUser.getCoins());
            } else if (array[0].compareToIgnoreCase("help") == 0) {
                System.out.println("show shop\ncollection\nbuy\nmoney\nexit");
            } else if (array[0].compareToIgnoreCase("exit") == 0) {
                mainMenuView();
                //break;
           // } else
               // System.out.println("invalid command");
            //order = scanner.nextLine();
        }
    }
    public static void playMenuView(){
        PlayMenuView menuView = new PlayMenuView();
        Main.changeScene(menuView.getPlayMenuScene());
    }
    public static void playMenu(String s) {
        //String gameType = scanner.nextLine();
        String[] array = s.split(" ");
        tempUser = loginUser;
        switch (array[0]) {
            case "Day":
                collectionMenuView("plant", "Day");
                break;
            case "Water":
                collectionMenuView("plant", "Water");
                break;
            case "Rail":
                prepareGame("Rail");
                break;
            case "Zombie":
                collectionMenuView("zombie", "Zombie");
                break;
            case "PvP":
                //String opponentUsername = scanner.nextLine();
                //int waves = scanner.nextInt();
                numberOfWaves = Integer.parseInt(array[2]);
                opponent = getUserByName(array[1]);
                //scanner.nextLine();
                collectionMenuView("plant", "PvP");
                break;
        }
    }
    public static void collectionMenuView(String s1,String s2){
        CollectionMenuView menuView = new CollectionMenuView(s1,s2);
        Main.changeScene(menuView.getCollectionMenuScene());
    }
    public static void collectionMenu(String type, String typeOfGame,String s) {
        String order = s;
        String[] array = s.split(" ");
       // while (true) {
            if (array[0].compareToIgnoreCase("showHand") == 0) {
                if (type.compareTo("plant") == 0) {
                    for (Plant plant : tempUser.getPlantHand()) {
                        System.out.println(plant.getName());
                    }
                } else
                    for (Zombie zombie : tempUser.getZombieHand()) {
                        System.out.println(zombie.getName());
                    }

            } else if (array[0].compareToIgnoreCase("showCollection") == 0) {
                if (type.compareTo("plant") == 0) {
                    for (Plant plant : tempUser.getCollection().getPlants()) {
                        System.out.println(plant.getName());
                    }
                } else
                    for (Zombie zombie : tempUser.getCollection().getZombies()) {
                        System.out.println(zombie.getName());
                    }
            } else if (array[0].compareToIgnoreCase("select") == 0) {
                //String name = scanner.nextLine();
                String name = array[1];
                if (type.compareTo("plant") == 0) {
                    if (tempUser.getPlantHand().size() >= 7) {
                        System.out.println("you have 7 plants already");
                    }
                    if (checkPlant(name)) {
                        Plant plant = Shop.makeNewPlantByName(name);
                        tempUser.getPlantHand().add(plant);
                        for (int i=0;i<tempUser.getCollection().getPlants().size();i++){
                            if (tempUser.getCollection().getPlants().get(i).getName().equals(plant.getName())){
                                tempUser.getCollection().getPlants().remove(i);
                            }
                        }
                        System.out.println("Card Selected!");
                    } else
                        System.out.println("invalid plant");
                } else {
                    if (tempUser.getZombieHand().size() >= 7) {
                        System.out.println("you have 7 zombies already");
                    }
                    if (checkZombie(name)) {
                        Zombie zombie = Shop.makeNewZombieByName(name);
                        tempUser.getZombieHand().add(zombie);
                        for (int i=0;i<tempUser.getCollection().getZombies().size();i++){
                            if (tempUser.getCollection().getZombies().get(i).getName().equals(zombie.getName())){
                                tempUser.getCollection().getZombies().remove(i);
                            }
                        }
                        System.out.println("Card Selected!");
                    } else System.out.println("invalid zombie");
                }
            } else if (array[0].compareToIgnoreCase("remove") == 0) {
                //String name = scanner.nextLine();
                String name = array[1];
                if (type.compareTo("plant") == 0) {
                    if (tempUser.checkHandPlant(name)) {
                        Plant plant = Shop.makeNewPlantByName(name);
                        for (int i=0;i<tempUser.getPlantHand().size();i++){
                            if (tempUser.getPlantHand().get(i).getName().equals(plant.getName())){
                                tempUser.getPlantHand().remove(i);
                            }
                        }
                        tempUser.getCollection().getPlants().add(plant);
                        System.out.println("Card Removed!");
                    } else
                        System.out.println("invalid plant");
                } else if (tempUser.checkHandZombie(name)) {
                    Zombie zombie = Shop.makeNewZombieByName(name);
                    for (int i=0;i<tempUser.getZombieHand().size();i++){
                        if (tempUser.getZombieHand().get(i).getName().equals(zombie.getName())){
                            tempUser.getZombieHand().remove(i);
                        }
                    }
                    tempUser.getCollection().getZombies().add(zombie);
                    System.out.println("Card Removed!");
                } else
                    System.out.println("invalid zombie");
            } else if (array[0].compareToIgnoreCase("help") == 0) {
                System.out.println("show hand\nshow collection\nselect\nplay\nremove\nhelp\nexit");
            } else if (array[0].compareToIgnoreCase("exit") == 0) {
                playMenuView();
                //break;
            } else if (array[0].compareToIgnoreCase("play") == 0) {
                if (typeOfGame.compareToIgnoreCase("PvP") == 0 && type.compareToIgnoreCase("plant") == 0) {
                    tempUser = opponent;
                    collectionMenuView("zombie", "PvP");
                } else if (typeOfGame.compareToIgnoreCase("PvP") == 0 && type.compareToIgnoreCase("zombie") == 0) {
                    prepareTwoPersonGame();
                   // break;
                } else if (typeOfGame.compareToIgnoreCase("PvP") != 0) {
                    if (tempUser.getPlantHand().size() < 7 && typeOfGame.compareToIgnoreCase("zombie") != 0) {
                        System.out.println("you don't have enough cards");
                    } else if (tempUser.getZombieHand().size() < 7 && typeOfGame.compareToIgnoreCase("zombie") == 0) {
                        System.out.println("you don't have enough cards");
                    } else {
                        prepareGame(typeOfGame);
                       // break;
                    }
                }
            //} else
               // System.out.println("invalid command");
            //order = scanner.nextLine();
             }

    }

    public static void prepareGame(String typeOfGame) {
        System.out.println("Game started");
        Game game = new Game();
        game.setTypeOfGame(typeOfGame);
        Player player = new Player(loginUser);
        if (typeOfGame.compareToIgnoreCase("zombie") == 0) {
            game.setSecondPlayer(player);
            player.setTypeOfPlayer("zombie");
        } else {
            player.setTypeOfPlayer("plant");
            game.setFirstPlayer(player);
        }
        game.setGameEnvironment(1);

    }

    public static void prepareTwoPersonGame() {
        System.out.println("Game started");
        Game game = new Game();
        game.setTypeOfGame("PvP");
        game.setNumberOfWaves(numberOfWaves);
        Player player = new Player(loginUser);
        player.setTypeOfPlayer("plant");
        Player opponentPlayer = new Player(opponent);
        opponentPlayer.setTypeOfPlayer("zombie");
        game.setFirstPlayer(player);
        game.setSecondPlayer(opponentPlayer);
        game.setGameEnvironment(1);
    }


    public static User getUserByName(String username) {
        for (User user : users) {
            if (user.getUsername().compareTo(username) == 0) {
                return user;
            }
        }
        return null;
    }

    public static boolean checkPassword(String username, String password) {
        if (getUserByName(username).getPassword().compareTo(password) == 0) {
            return true;
        }
        return false;
    }

    public static boolean checkUsername(String username) {
        for (User user : users) {
            if (user.getUsername().compareTo(username) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPlant(String name) {
        for (Plant plant : loginUser.getCollection().getPlants()) {
            if (plant.getName().compareToIgnoreCase(name) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkZombie(String name) {
        for (Zombie zombie : loginUser.getCollection().getZombies()) {
            if (zombie.getName().compareTo(name) == 0) {
                return true;
            }
        }
        return false;
    }


    public static Plant getPlantByName(String name) {
        for (Plant plant : loginUser.getCollection().getPlants()) {
            if (plant.getName().compareTo(name) == 0) {
                return plant;
            }
        }
        return null;
    }


    public static Zombie getZombieByName(String name) {
        for (Zombie zombie : loginUser.getCollection().getZombies()) {
            if (zombie.getName().compareTo(name) == 0) {
                return zombie;
            }
        }
        return null;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }



}
