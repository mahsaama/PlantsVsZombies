import Game.Player;
import User.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static User loginUser;
    static ArrayList<User> users = new ArrayList<User>();

    public static void main(String[] args) {


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
            if(order.compareToIgnoreCase ("Change") == 0){}
            else if(order.compareToIgnoreCase ("Delete") == 0){
                String username = scanner.nextLine ();
                String password = scanner.nextLine ();
                if(checkUsername (username)){
                    if(getUserByName (username).getPassword ().compareTo (password) == 0){
                        users.remove (getUserByName (username));
                    }
                    else
                        System.out.println ("wrong password" );
                }
                else
                    System.out.println ("wrong username ");
            }
            else if(order.compareToIgnoreCase ("Rename") == 0){
                String newUsername = scanner.nextLine ();
                loginUser.setUsername (newUsername);
            }
            else if(order.compareToIgnoreCase ("Create") == 0){
                //TODO
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

           //TODO
        }
    }

    public static void shopMenu(){}

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
