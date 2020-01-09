package Menu;

import Creature.Plant;
import Creature.Zombie;
import Game.Game;
import Shop.Shop;
import User.User;
import User.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {

    private static Scanner scanner = new Scanner (System.in);
    private static User loginUser;
    private static User opponent;
    private static User tempUser;
    private static ArrayList<User> users = new ArrayList<User> ( );
    private static Shop shop = new Shop ( );
    private static int numberOfWaves;

    public static void loginMenu() {
        String order = "help";
        while (true) {
            if (order.compareToIgnoreCase ("create account") == 0) {
                String username = scanner.nextLine ( );
                String password = scanner.nextLine ( );
                createAccount (username, password);
            } else if (order.compareToIgnoreCase ("login") == 0) {
                String username = scanner.nextLine ( );
                String password = scanner.nextLine ( );
                if (login (username, password)) break;

            } else if (order.compareToIgnoreCase ("leaderboard") == 0) {
                leaderboard ( );
            } else if (order.compareToIgnoreCase ("help") == 0) {
                System.out.println ("create account\nlogin\nleaderboard\nhelp\nexit");
            } else if (order.compareToIgnoreCase ("exit") == 0) {
                return;
            } else
                System.out.println ("invalid command");
            order = scanner.nextLine ();
        }

    }

    private static void leaderboard() {
        users.sort (Comparator.comparing (User::getNumberOfZombiesKilled));
        for (User user : users) {
            System.out.println (user.getUsername ( ) + ": " + user.getNumberOfZombiesKilled ( ));
        }
    }

    private static boolean login(String username, String password) {
        if (checkUsername (username)) {
            if (checkPassword (username, password)) {
                System.out.println ("login successfully");
                loginUser = getUserByName (username);
                mainMenu ( );
                return true;
            } else
                System.out.println ("invalid password");
        } else
            System.out.println ("invalid username");
        return false;
    }

    private static void createAccount(String username, String password) {
        if(!checkUsername (username)){
            User user = new User (username, password);
            users.add (user);
            System.out.println ("account <" + user.getUsername ( ) + "> was created");
            Shop.setFirstCards (user);
        }
        else
            System.out.println ("invalid username" );

    }

    public static void mainMenu() {
        String order = "help";
        while (true) {
            if (order.compareToIgnoreCase ("Play") == 0) {
                playMenu ( );
                break;
            } else if (order.compareToIgnoreCase ("profile") == 0) {
                profileMenu ( );
                break;
            } else if (order.compareToIgnoreCase ("shop") == 0) {
                shopMenu ( );
                break;
            } else if (order.compareToIgnoreCase ("exit") == 0) {
                loginMenu ( );
                break;
            } else if (order.compareToIgnoreCase ("help") == 0) {
                System.out.println ("Play\nprofile\nshop\nexit\nhelp");
            } else
                System.out.println ("invalid command");
            order = scanner.nextLine ( );
        }
    }


    public static void profileMenu() {
        String order = "help";
        while (true) {
            if (order.compareToIgnoreCase ("Change") == 0) {
                String username = scanner.nextLine ( );
                String password = scanner.nextLine ( );
                if (login (username, password)) break;
            } else if (order.compareToIgnoreCase ("Delete") == 0) {
                String username = scanner.nextLine ( );
                String password = scanner.nextLine ( );
                removeUser (username, password);
            } else if (order.compareToIgnoreCase ("Rename") == 0) {
                String newUsername = scanner.nextLine ( );
                loginUser.setUsername (newUsername);
            } else if (order.compareToIgnoreCase ("Create") == 0) {
                String username = scanner.nextLine ( );
                String password = scanner.nextLine ( );
                if (!checkUsername (username)) {
                    User user = new User (username, password);
                    users.add (user);
                    mainMenu ( );
                    break;
                } else
                    System.out.println ("invalid username");
            } else if (order.compareToIgnoreCase ("Show") == 0) {
                System.out.println (loginUser.getUsername ( ));
            } else if (order.compareToIgnoreCase ("help") == 0) {
                System.out.println ("Change\nDelete\nRename\nCreate\nShow\nhelp\nexit");
            } else if (order.compareToIgnoreCase ("exit") == 0) {
                mainMenu ( );
                break;
            } else
                System.out.println ("invalid command");
            order = scanner.nextLine ( );
        }
    }

    private static void removeUser(String username, String password) {
        if (loginUser.getUsername ( ).compareTo (username) == 0) {
            if (checkPassword (username, password)) {
                users.remove (getUserByName (username));
            } else
                System.out.println ("invalid password");
        } else
            System.out.println ("invalid username");
    }

    public static void shopMenu() {
        String order = "help";
        while (true) {
            if (order.compareToIgnoreCase ("show shop") == 0) {
                for (Plant plant : shop.getPlantList ( )) {
                    System.out.println (plant.getName ( ) + " : " + plant.getPrice ( ));
                }
                for (Zombie zombie : shop.getZombieList ( )) {
                    System.out.println (zombie.getName ( ) + " : " + zombie.getPrice ( ));
                }
            } else if (order.compareToIgnoreCase ("collection") == 0) {
                for (Plant plant : loginUser.getCollection ( ).getPlants ( )) {
                    System.out.println (plant.getName ( ) + " : " + plant.getPrice ( ));
                }
                for (Zombie zombie : loginUser.getCollection ( ).getZombies ( )) {
                    System.out.println (zombie.getName ( ) + " : " + zombie.getPrice ( ));
                }
            } else if (order.compareToIgnoreCase ("buy") == 0) {
                String cardName = scanner.nextLine ( );
                shop.buy (cardName, loginUser);
            } else if (order.compareToIgnoreCase ("money") == 0) {
                System.out.println (loginUser.getCoins ( ));
            } else if (order.compareToIgnoreCase ("help") == 0) {
                System.out.println ("show shop\ncollection\nbuy\nmoney\nexit");
            } else if (order.compareToIgnoreCase ("exit") == 0) {
                mainMenu ( );
                break;
            } else
                System.out.println ("invalid command");
            order = scanner.nextLine ( );
        }
    }

    public static void playMenu() {
        String gameType = scanner.nextLine ( );
        tempUser = loginUser;
        switch (gameType) {
            case "Day":
                collectionMenu ("plant", "Day");
                break;
            case "Water":
                collectionMenu ("plant", "Water");
                break;
            case "Rail":
                prepareGame ("Rail");
                break;
            case "Zombie":
                collectionMenu ("zombie", "Zombie");
                break;
            case "PvP":
                String opponentUsername = scanner.nextLine ( );
                int waves = scanner.nextInt ( );
                numberOfWaves = waves;
                opponent = getUserByName (opponentUsername);
                scanner.nextLine ();
                collectionMenu ("plant", "PvP");
                break;
        }
    }

    public static void collectionMenu(String type, String typeOfGame) {
        String order = "help";
        while (true) {
            if (order.compareToIgnoreCase ("show hand") == 0) {
                if (type.compareTo ("plant") == 0) {
                    for (Plant plant : tempUser.getPlantHand ( )) {
                        System.out.println (plant.getName ( ));
                    }
                } else
                    for (Zombie zombie : tempUser.getZombieHand ( )) {
                        System.out.println (zombie.getName ( ));
                    }

            } else if (order.compareToIgnoreCase ("show collection") == 0) {
                if (type.compareTo ("plant") == 0) {
                    for (Plant plant : tempUser.getCollection ( ).getPlants ( )) {
                        System.out.println (plant.getName ( ));
                    }
                } else
                    for (Zombie zombie : tempUser.getCollection ( ).getZombies ( )) {
                        System.out.println (zombie.getName ( ));
                    }
            } else if (order.compareToIgnoreCase ("select") == 0) {
                String name = scanner.nextLine ( );
                if (type.compareTo ("plant") == 0) {
                    if (tempUser.getPlantHand ( ).size ( ) >= 7) {
                        System.out.println ("you have 7 plants already");
                    }
                    if (checkPlant (name)) {
                        Plant plant = getPlantByName (name);
                        tempUser.getPlantHand ( ).add (plant);
                        tempUser.getCollection ( ).getPlants ( ).remove (plant);
                    } else
                        System.out.println ("invalid plant");
                } else {
                    if (tempUser.getZombieHand ( ).size ( ) >= 7) {
                        System.out.println ("you have 7 zombies already");
                    }
                    if (checkZombie (name)) {
                        Zombie zombie = getZombieByName (name);
                        tempUser.getZombieHand ( ).add (zombie);
                        tempUser.getCollection ( ).getZombies ( ).remove (zombie);
                    } else System.out.println ("invalid zombie");
                }
            } else if (order.compareToIgnoreCase ("remove") == 0) {
                String name = scanner.nextLine ( );
                if (type.compareTo ("plant") == 0) {
                    if (tempUser.checkHandPlant (name)) {
                        Plant plant = getPlantByName (name);
                        tempUser.getPlantHand ( ).remove (plant);
                        tempUser.getCollection ( ).getPlants ( ).add (plant);
                    } else
                        System.out.println ("invalid plant");
                } else if (tempUser.checkHandZombie (name)) {
                    Zombie zombie = getZombieByName (name);
                    tempUser.getZombieHand ( ).remove (zombie);
                    tempUser.getCollection ( ).getZombies ( ).add (zombie);
                } else
                    System.out.println ("invalid zombie");
            } else if (order.compareToIgnoreCase ("help") == 0) {
                System.out.println ("show hand\nshow collection\nselect\nplay\nremove\nhelp\nexit");
            } else if (order.compareToIgnoreCase ("exit") == 0) {
                playMenu ( );
                break;
            } else if (order.compareToIgnoreCase ("play") == 0) {
                if (typeOfGame.compareToIgnoreCase ("PvP") == 0 && type.compareToIgnoreCase ("plant") == 0) {
                    tempUser = opponent;
                    collectionMenu ("zombie", "PvP");
                } else if (typeOfGame.compareToIgnoreCase ("PvP") == 0 && type.compareToIgnoreCase ("zombie") == 0) {
                    prepareTwoPersonGame ( );
                    break;
                } else if (typeOfGame.compareToIgnoreCase ("PvP") != 0) {
                    if (tempUser.getPlantHand ( ).size ( ) < 7 && typeOfGame.compareToIgnoreCase ("zombie") != 0) {
                        System.out.println ("you don't have enough cards");
                    } else if (tempUser.getZombieHand ( ).size ( ) < 7 && typeOfGame.compareToIgnoreCase ("zombie") == 0) {
                        System.out.println ("you don't have enough cards");
                    } else {
                        prepareGame (typeOfGame);
                        break;
                    }

                }
            }
            else
                System.out.println ("invalid command");
            order = scanner.nextLine ( );
        }

    }

    public static void prepareGame(String typeOfGame) {
        System.out.println ("Game started" );
        Game game = new Game ( );
        game.setTypeOfGame (typeOfGame);
        Player player = new Player (loginUser);
        if (typeOfGame.compareToIgnoreCase ("zombie") == 0) {
            game.setSecondPlayer (player);
            player.setTypeOfPlayer ("zombie");
        } else {
            player.setTypeOfPlayer ("plant");
            game.setFirstPlayer (player);
        }
        game.setGameEnvironment ();

    }

    public static void prepareTwoPersonGame() {
        System.out.println ("Game started" );
        Game game = new Game ( );
        game.setTypeOfGame ("PvP");
        game.setNumberOfWaves (numberOfWaves);
        Player player = new Player (loginUser);
        player.setTypeOfPlayer ("plant");
        Player opponentPlayer = new Player (opponent);
        opponentPlayer.setTypeOfPlayer ("zombie");
        game.setFirstPlayer (player);
        game.setSecondPlayer (opponentPlayer);
        game.setGameEnvironment ( );
    }


    public static User getUserByName(String username) {
        for (User user : users) {
            if (user.getUsername ( ).compareTo (username) == 0) {
                return user;
            }
        }
        return null;
    }

    public static boolean checkPassword(String username, String password) {
        if (getUserByName (username).getPassword ( ).compareTo (password) == 0) {
            return true;
        }
        return false;
    }

    public static boolean checkUsername(String username) {
        for (User user : users) {
            if (user.getUsername ( ).compareTo (username) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPlant(String name) {
        for (Plant plant : loginUser.getCollection ( ).getPlants ( )) {
            if (plant.getName ( ).compareToIgnoreCase (name) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkZombie(String name) {
        for (Zombie zombie : loginUser.getCollection ( ).getZombies ( )) {
            if (zombie.getName ( ).compareTo (name) == 0) {
                return true;
            }
        }
        return false;
    }


    public static Plant getPlantByName(String name) {
        for (Plant plant : loginUser.getCollection ( ).getPlants ( )) {
            if (plant.getName ( ).compareTo (name) == 0) {
                return plant;
            }
        }
        return null;
    }


    public static Zombie getZombieByName(String name) {
        for (Zombie zombie : loginUser.getCollection ( ).getZombies ( )) {
            if (zombie.getName ( ).compareTo (name) == 0) {
                return zombie;
            }
        }
        return null;
    }
}
