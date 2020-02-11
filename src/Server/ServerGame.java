package Server;


import javafx.util.Pair;

public class ServerGame {
    public ServerGame(Connection p1, Connection p2) {
        new Thread(() -> {
            try {
                Message enterGame = Message.enterGame(p1.username, p2.username);
                p1.send(enterGame);
                p2.send(enterGame);

                Thread.sleep(5000);
                //winer is p1 and loser is p2
                Message endGame = Message.endGame(p1.username, p2.username);
                p1.send(endGame);
                p2.send(endGame);

                Server.gameHistory.add(new Pair<String, Boolean>(p1.username, true));
                Server.gameHistory.add(new Pair<String, Boolean>(p2.username, false));

                p1.inGame = false;
                p2.inGame = false;
            } catch (Exception e) {

            }
        }).start();
    }
}
