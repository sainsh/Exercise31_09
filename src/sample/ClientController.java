package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ClientController {


    Client client;

    @FXML
    TextArea chatWindowText;
    @FXML
    TextField chatText;

    public ClientController() {
        client = new Client(this);


    }
    @FXML
    public void initialize(){
        (new Thread(client)).start();
    }


    public void onKeyReleased(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {

            chatWindowText.appendText(chatText.getText() + "\n");
            client.send(chatText.getText());
            chatText.setText("");
        }
    }
}
