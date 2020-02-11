package Server;


public class ServerHandler {
    public static void sendMessageToClient(Message message) {
        for (Connection conn : Server.connectionList) {
            if (conn.username.equals(message.to)) {
                try {
                    conn.send(message);
//                    if (message.command == Command.CHAT) {
//                        conn.send(Message.chat(message.from, message.to, message.data, message.replyTo));
//                    } else {
//                        conn.send(Message.chatPic(message.from, message.to, message.data));
//                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public static void startGame(Message message) {
        /// start a game between message.from & message.to
        String p1 = message.from;
        String p2 = message.to;
        Connection p1con = null;
        Connection p2con = null;
        for (Connection connection : Server.connectionList) {
            if (connection.username.equals(p1))
                p1con = connection;
            if (connection.username.equals(p2))
                p2con = connection;

        }
        ServerGame game = new ServerGame(p1con, p2con);


    }

}
