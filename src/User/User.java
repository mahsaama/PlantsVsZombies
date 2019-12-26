package User;

public class User {
    private String username ;
    private String password ;
    private int coins = 0;
    private int numberOfZombiesKilledOverAll = 0;

    public User(String username , String password){
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumberOfZombiesKilled() {
        return numberOfZombiesKilledOverAll;
    }

    public void setNumberOfZombiesKilled(int numberOfZombiesKilled) {
        this.numberOfZombiesKilledOverAll = numberOfZombiesKilled;
    }
    public int getCoins(){ return coins;}
    public void setCoins(int amount){this.coins += amount;}


}
