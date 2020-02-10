package Server;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Client {
    static Socket socket;
    static Formatter formatter;
    static String username;

    public static void chat(String chat_message, String to) {
        String message = Message.chat(username, to, chat_message);
        formatter.format(message);
        formatter.flush();
    }

    public static void get_list() {
        String message = Message.lst();
        formatter.format(message);
        formatter.flush();
    }

    public static void main(String[] args) {
        username = "1";
        try {
            socket = new Socket("localhost", 3456);
            formatter = new Formatter(socket.getOutputStream());
            ClientReader clientReader = new ClientReader(socket);
        } catch (Exception e) {

        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String id = scanner.nextLine();
            if (id.equals("list")) {
                get_list();
            } else {
                String data = scanner.nextLine();
                chat(data, id);
            }
        }
    }
}
