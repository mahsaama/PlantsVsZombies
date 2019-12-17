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
                    loginUser = getUserByName(username);
                    exit = true;
                    mainMenu();
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

    public static void mainMenu(){}


    public static User getUserByName(String username){
        for(User user : users){
            if(user.getUsername().compareTo(username) == 0){
                return user;
            }
        }
        return null;
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
