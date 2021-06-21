package lesson4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    @FXML
    private TextArea messageTextArea;
    @FXML
    private Button sendMessageButton;
    @FXML
    private TextArea chatTextArea;
    @FXML
    protected ListView userList;

    @FXML
    public void sendMessageButton(ActionEvent actionEvent) {
        sendMessage();
    }

    private void sendMessage() {
        chatTextArea.appendText(messageTextArea.getText());
        chatTextArea.appendText(System.lineSeparator());
        messageTextArea.clear();
    }

    public void sendMessageKeyboard(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            event.consume();
            if (event.isShiftDown()) {
                messageTextArea.appendText(System.lineSeparator());
            } else {
                sendMessage();
            }
        }
    }
}
