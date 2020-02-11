package Menu;

import Server.Client;
import javafx.scene.Scene;
import sun.security.pkcs11.wrapper.CK_ECDH1_DERIVE_PARAMS;

import java.security.acl.Group;

public class ChatMenuView {
    private Group chatMenuRoot;
    private Scene chatMenuScene;
    private Client client;
    public ChatMenuView(Client client){
        this.client = client;

    }
    public Scene getChatMenuScene(){ return chatMenuScene;}
}
