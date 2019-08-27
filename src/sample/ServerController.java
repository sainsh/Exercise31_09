package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ServerController  {

    Server server;

    @FXML
    TextArea chatWindowText;
    @FXML
    TextField chatText;

    public ServerController(){

        server = new Server(this);
        (new Thread(server)).start();

    }

    public void onKeyReleased(KeyEvent keyEvent) {

        if(keyEvent.getCode() == KeyCode.ENTER){

            chatWindowText.appendText(chatText.getText() + "\n");
            server.send(chatText.getText());
            chatText.setText("");
        }
    }
}
