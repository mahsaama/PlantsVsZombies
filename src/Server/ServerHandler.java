package Server;


public class ServerHandler {

    public static boolean sendMessageToClient(Message message) {
        for (Connection conn : Server.connectionList) {
            if (conn.username.equals(message.to)) {
                if ((System.currentTimeMillis() - conn.lastSeen) < 2000) {
                    try {
                        conn.send(message);
                        return true;
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
        return false;
    }


    public static int startGame(Message message) {
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

        if (p1con == null || p2con == null) {
            return 1;
        }
        if (p1con.inGame || p2con.inGame) {
            return 2;
        }
        if (((System.currentTimeMillis() - p1con.lastSeen) > 2000) || ((System.currentTimeMillis() - p2con.lastSeen) > 2000)) {
            return 3;
        }
        p1con.inGame = true;
        p2con.inGame = true;
        ServerGame game = new ServerGame(p1con, p2con);
        return 0;
    }

}
