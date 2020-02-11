package Server;

import com.google.gson.Gson;

import java.util.Random;

public class Message {

    public int message_id;
    private static int counter = 0;
    private static Object lock = new Object();
    public String data;
    public Command command;
    public long time;

    public String from;
    public String to;
    public long chatId;
    public long replyTo=-1;
    static Random r = new Random();

    Message() {
        synchronized (lock) {
            this.message_id = counter;
            counter += 1;
        }
    }

    public Message setMessageId(int id) {
        this.message_id = id;
        return this;
    }

    static Message chat(String from, String to, String data) {
        Message message = new Message();
        message.from = from;
        message.to = to;
        message.data = data;
        message.chatId = r.nextLong();
        message.command = Command.CHAT;
        return message;
    }

    static Message chat(String from, String to, String data, long replyTo) {
        Message message = chat(from, to, data);
        message.replyTo = replyTo;
        return message;
    }

    static Message showUsers() {
        Message message = new Message();
        message.command = Command.SHOW_USERS;
        return message;
    }

    static Message showUsers(String users) {
        Message message = new Message();
        message.command = Command.SHOW_USERS;
        message.data = users;
        return message;
    }

    static Message ping(long time) {
        Message message = new Message();
        message.command = Command.PING;
        message.time = time;
        return message;
    }

    static Message loginError(String error) {
        Message message = new Message();
        message.command = Command.INVALID_USERNAME;
        message.data = error;
        return message;

    }

    static Message successfulLogin() {
        Message message = new Message();
        message.command = Command.SUCCESSFUL_LOGIN;
        return message;
    }

    static Message loginCommand(String username) {
        Message message = new Message();
        message.command = Command.LOGIN;
        message.data = username;
        return message;
    }
}
