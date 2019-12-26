import Creature.Plant;
import Creature.Zombie;
import Game.Player;
import Shop.Shop;
import User.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static User loginUser;
    static ArrayList<User> users = new ArrayList<User>();
    static Shop shop = new Shop();


    public static void main(String[] args) {
        loginMenu ();
    }

    public static void loginMenu(){
        String order = scanner.nextLine();
        boolean exit = false;
        while(!exit){
            if(order.compareToIgnoreCase("create account") == 0){
                String username = scanner.nextLine();
                String password = scanner.nextLine();
                loginUser = new User(username,password);
                users.add(loginUser);
            }
            else if(order.compareToIgnoreCase("login") == 0){
                String username = scanner.nextLine();
                String password = scanner.nextLine();
                if(checkUsername(username)){
                    if(checkPassword (username,password)){
                        loginUser = getUserByName(username);
                        exit = true;
                        mainMenu();
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
        }

    }

    public static void mainMenu(){
        String order = scanner.nextLine();
        boolean exit = false;
        while(!exit){
            if(order.compareToIgnoreCase("Play") == 0){
                //TODO
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
                //TODO
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
                collectionMenu ();
                break;
            case "Water":
                collectionMenu ();
                break;
            case "Rail":
                //TODO
                break;
            case "Zombie":
                collectionMenu ();
                break;
            case "PvP":
                String opponentUsername = scanner.nextLine ();
                int numberOfWaves = scanner.nextInt ();
                collectionMenu ();
        }
    }

    public static void collectionMenu(){
        //TODO zombie & plant seperated
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



}
