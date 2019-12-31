import Creature.Plant;
import Creature.Zombie;
import Game.Game;
import Map.PlayGround;
import Shop.Shop;
import User.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;
import User.Player;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static User loginUser;
    static User opponent;
    static ArrayList<User> users = new ArrayList<User>();
    static Shop shop = new Shop();
    static int numberOfWaves;

    public static void main(String[] args) {

        Shop shop = new Shop ();
        loginMenu ();
    }

    public static void loginMenu(){
        String order = scanner.nextLine();
        while(true){
            if(order.compareToIgnoreCase("create account") == 0){
                String username = scanner.nextLine();
                String password = scanner.nextLine();
                loginUser = new User(username,password);
                users.add(loginUser);
                System.out.println ("account _" + loginUser.getUsername () + "_ was created");
                //TODO first zombies and plants
            }
            else if(order.compareToIgnoreCase("login") == 0){
                String username = scanner.nextLine();
                String password = scanner.nextLine();
                if(checkUsername(username)){
                    if(checkPassword (username,password)){
                        loginUser = getUserByName(username);
                        mainMenu();
                        break;
                    }
                    else
                        System.out.println ("invalid password" );
                }
                else
                    System.out.println("invalid username");

            }
            else if(order.compareToIgnoreCase("leaderboard") == 0){
                users.sort(Comparator.comparing(User::getNumberOfZombiesKilled));
                for(User user : users){
                    System.out.println(user.getUsername() + ": " + user.getNumberOfZombiesKilled());
                }
            }
            else if(order.compareToIgnoreCase("help") == 0){
                System.out.println("create account\nlogin\nleaderboard\nhelp\nexit");
            }
            else if(order.compareToIgnoreCase("exit") == 0){
                return;
            }
            else
                System.out.println("invalid command");
            order = scanner.nextLine ();
        }

    }

    public static void mainMenu(){
        String order = scanner.nextLine();
        boolean exit = false;
        while(!exit){
            if(order.compareToIgnoreCase("Play") == 0){
                playMenu ();
            }
            else if(order.compareToIgnoreCase("profile") == 0){
                exit = true;
                profileMenu();
            }
            else if(order.compareToIgnoreCase("shop") == 0){
                exit = true;
                shopMenu();
            }
            else if(order.compareToIgnoreCase("exit") == 0){
                exit = true;
                loginMenu();
            }
            else if(order.compareToIgnoreCase("help") == 0){
                System.out.println("Play\nprofile\nshop\nexit\nhelp\nexit");
            }
            else
                System.out.println("invalid command");
        }
    }


    public static void profileMenu(){
        String order = scanner.nextLine();
        boolean exit = false;
        while(!exit){
            if(order.compareToIgnoreCase ("Change") == 0){
                String username = scanner.nextLine ();
                String password = scanner.nextLine ();
                if(!checkUsername (username)){
                    loginUser.setUsername (username);
                    loginUser.setPassword (password);
                }
                else
                    System.out.println ("username already taken" );
            }
            else if(order.compareToIgnoreCase ("Delete") == 0){
                String username = scanner.nextLine ();
                String password = scanner.nextLine ();
                if(loginUser.getUsername ().compareTo (username) == 0){
                    if(checkPassword (username,password)){
                        users.remove (getUserByName (username));
                    }
                    System.out.println ("invalid password" );
                }
                System.out.println ("invalid username" );
            }

            else if(order.compareToIgnoreCase ("Rename") == 0){
                String newUsername = scanner.nextLine ();
                loginUser.setUsername (newUsername);
            }
            else if(order.compareToIgnoreCase ("Create") == 0){
                String username = scanner.nextLine ();
                String password = scanner.nextLine ();
                if(!checkUsername (username)){
                    User user = new User (username,password);
                    users.add (user);
                    loginUser = user;
                }
                else
                    System.out.println ("invalid username" );
            }
            else if(order.compareToIgnoreCase ("Show") == 0){
                System.out.println (loginUser.getUsername () );
            }
            else if(order.compareToIgnoreCase ("help") == 0 ){
                System.out.println ("Change\nDelete\nRename\nCreate\nShow\nhelp\nexit" );
            }
            else if(order.compareToIgnoreCase ("exit") == 0){
                mainMenu ();
                exit = true;
            }
            else
                System.out.println ("invalid command");
        }
    }

    public static void shopMenu(){
        String order = scanner.nextLine ();
        boolean exit = false;
        while(!exit){
            if(order.compareToIgnoreCase ("show shop") == 0){
                for(Plant plant : shop.getPlantList ()){
                    System.out.println (plant.getName () + " : " + plant.getPrice () );
                }
                for(Zombie zombie : shop.getZombieList ()){
                    System.out.println (zombie.getName () + " : " + zombie.getPrice ());
                }
            }
            else if(order.compareToIgnoreCase ("collection" ) == 0 ){
                for(Plant plant : loginUser.getCollection ().getPlants ()){
                    System.out.println (plant.getName () + " : " + plant.getPrice () );
                }
                for(Zombie zombie : loginUser.getCollection ().getZombies ()){
                    System.out.println (zombie.getName () + " : " + zombie.getPrice () );
                }
            }
            else if(order.compareToIgnoreCase ("buy") == 0){
                String cardName = scanner.nextLine ();
                shop.buy (cardName,loginUser);
            }
            else if(order.compareToIgnoreCase ("money") == 0){
                System.out.println (loginUser.getCoins () );
            }
            else if(order.compareToIgnoreCase ("help") == 0){
                System.out.println ("show shop\ncollection\nbuy\nmoney\nexit" );
            }
            else if(order.compareToIgnoreCase ("exit") == 0){
                exit = true;
                mainMenu ();
            }
            else
                System.out.println ("invalid command" );
        }
    }

    public static void playMenu(){
        String gameType = scanner.nextLine ();
        switch (gameType){
            case "Day":
                collectionMenu ("plant","Day");
                break;
            case "Water":
                collectionMenu ("plant","Water");
                break;
            case "Rail":
                //preparegame
                break;
            case "Zombie":
                collectionMenu ("zombie","Zombie");
                break;
            case "PvP":
                String opponentUsername = scanner.nextLine ();
                int waves = scanner.nextInt ();
                numberOfWaves = waves;
                opponent = getUserByName (opponentUsername);
                collectionMenu ("plant","PvP");
        }
    }

    public static void collectionMenu(String type , String typeOfGame){
        String order = scanner.nextLine ();
        String[] orderParts = order.split (" ");
        boolean exit = false;
        Pattern pattern1 = Pattern.compile ("select \\S+");
        Pattern pattern2 = Pattern.compile ("remove \\S+");
        if(type.compareTo ("plant") == 0){
            while(!exit){
                if(order.compareToIgnoreCase ("show hand") == 0){
                    if(type.compareTo ("plant") == 0){
                        for(Plant plant : loginUser.getPlantHand ()){
                            System.out.println (plant.getName () );
                        }
                    }
                    else
                        for (Zombie zombie : loginUser.getZombieHand ( )) {
                            System.out.println (zombie.getName ( ));
                        }

                }
                else if(order.compareToIgnoreCase ("show collection") == 0){
                    if(type.compareTo ("plant") == 0){
                        for(Plant plant : loginUser.getCollection ().getPlants ()){
                            System.out.println (plant.getName () );
                        }
                    }
                    else
                        for (Zombie zombie : loginUser.getCollection ( ).getZombies ( )) {
                            System.out.println (zombie.getName ( ));
                        }
                }

                else if(pattern1.matcher (order).matches ()){
                    if(type.compareTo ("plant") == 0){
                        if(checkPlant (orderParts[1])){
                            Plant plant = getPlantByName (orderParts[1]);
                            loginUser.getPlantHand ().add (plant);
                            loginUser.getCollection ().getPlants ().remove (plant);
                        }
                        System.out.println ("invalid plant" );
                    }
                    else
                    if (checkZombie (orderParts[1])) {
                        Zombie zombie = getZombieByName (orderParts[1]);
                        loginUser.getZombieHand ( ).add (zombie);
                        loginUser.getCollection ( ).getZombies ( ).remove (zombie);
                    }
                    System.out.println ("invalid zombie");
                }
                else if(pattern2.matcher (order).matches ()){
                    if(type.compareTo ("plant") == 0){
                        if(loginUser.checkHandPlant (orderParts[1])){
                            Plant plant = getPlantByName (orderParts[1]);
                            loginUser.getPlantHand ().remove (plant);
                            loginUser.getCollection ().getPlants ().add (plant);
                        }
                        else
                            System.out.println ("invalid plant" );
                    }
                    else
                    if (loginUser.checkHandZombie (orderParts[1])) {
                        Zombie zombie = getZombieByName (orderParts[1]);
                        loginUser.getZombieHand ( ).remove (zombie);
                        loginUser.getCollection ( ).getZombies ( ).add (zombie);
                    } else
                        System.out.println ("invalid zombie");
                }
                else if(order.compareToIgnoreCase ("help") == 0){
                    System.out.println ("show hand\nshow collection\nselect\nplay\nremove\nhelp\nexit" );
                }
                else if(order.compareToIgnoreCase ("exit") == 0){
                    exit = true;
                    playMenu ();
                }
                else if(order.compareToIgnoreCase ("play") == 0){
                    if(typeOfGame.compareToIgnoreCase ("PvP") == 0 && type.compareToIgnoreCase ("plant") == 0){
                        collectionMenu ("zombie","PvP");
                    }
                    else if(typeOfGame.compareToIgnoreCase ("PvP") == 0 && type.compareToIgnoreCase ("zombie") == 0){
                        prepareTwoPersonGame ();
                    }
                    else if(typeOfGame.compareToIgnoreCase ("PvP") != 0)
                        prepareGame (typeOfGame);

                }
                else
                    System.out.println ("invalid command" );
            }

        }
    }
    public static void prepareGame(String typeOfGame){
        Game game = new Game();
        game.setTypeOfGame (typeOfGame);
        Player player = (Player) loginUser;
        if(typeOfGame.compareToIgnoreCase ("zombie") == 0){
            game.setSecondPlayer (player);
            player.setTypeOfPlayer ("zombie");
        }
        else
            player.setTypeOfPlayer ("plant");
            game.setFirstPlayer (player);
    }

    public static void prepareTwoPersonGame(){
        Game game = new Game ();
        game.setTypeOfGame ("PvP");
        game.setNumberOfWaves (numberOfWaves);
        Player player =  (Player) loginUser;
        player.setTypeOfPlayer ("plant");
        Player opponentPlayer =  (Player) opponent;
        opponentPlayer.setTypeOfPlayer ("zombie");
        game.setFirstPlayer (player);
        game.setSecondPlayer (opponentPlayer);
        game.setGameEnvironment ();
    }




    public static User getUserByName(String username){
        for(User user : users){
            if(user.getUsername().compareTo(username) == 0){
                return user;
            }
        }
        return null;
    }

    public static boolean checkPassword(String username,String password){
        if(getUserByName (username).getPassword ().compareTo (password) == 0){
            return true;
        }
        return false;
    }

    public static boolean checkUsername(String username){
        for(User user : users){
            if(user.getUsername().compareTo(username) == 0){
                return true;
            }
        }
        return false;
     }

     public static boolean checkPlant(String name){
         for(Plant plant : loginUser.getCollection ().getPlants ()){
             if(plant.getName ().compareTo (name) == 0){
                 return true;
             }
         }
         return false;
     }

     public static boolean checkZombie(String name){
         for(Zombie zombie : loginUser.getCollection ().getZombies ()){
             if(zombie.getName ().compareTo (name) == 0){
                 return true;
             }
         }
         return false;
     }


     public static Plant getPlantByName(String name){
        for(Plant plant : loginUser.getCollection ().getPlants ()){
            if(plant.getName ().compareTo (name) == 0){
                return plant;
            }
        }
        return null;
     }


     public static Zombie getZombieByName(String name){
         for(Zombie zombie : loginUser.getCollection ().getZombies ()){
             if(zombie.getName ().compareTo (name) == 0){
                 return zombie;
             }
         }
         return null;
     }




}
