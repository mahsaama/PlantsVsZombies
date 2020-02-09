package Server;

import com.google.gson.Gson;

public class Message {
    public String from;
    public String to;
    public String data;
    public Command command;

    Message() {

    }

    static public String chat(String from, String to, String data) {
        Message message = new Message();
        message.from = from;
        message.to = to;
        message.data = data;
        message.command = Command.CHAT;
        Gson gson = new Gson();
        String jsonInString = gson.toJson(message);
        return jsonInString + "#";
    }

    static public String lst(){
        Message message = new Message();
        message.command = Command.LIST;
        Gson gson = new Gson();
        String jsonInString = gson.toJson(message);
        return jsonInString + "#";
    }

    static public String lst(String data) {
        Message message = new Message();
        message.data = data;
        message.command = Command.LIST;
        Gson gson = new Gson();
        String jsonInString = gson.toJson(message);
        return jsonInString + "#";
    }

    static public Message login(String from) {
        Message message = new Message();
        message.from = from;
        return message;
    }
}

