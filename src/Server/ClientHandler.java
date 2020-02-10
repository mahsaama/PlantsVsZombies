package Server;

import java.util.Dictionary;
import java.util.HashMap;

public class ClientHandler {

    HashMap<Integer, Message> results = new HashMap<>();

    public void addResult(Message message) {
        this.results.put(message.message_id, message);
    }

    public void showUsers(Message message) {
        System.err.println(message.data);
    }

    public void chat(Message message){

    }
}
