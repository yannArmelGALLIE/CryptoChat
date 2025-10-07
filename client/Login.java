import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private TextField port;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin1;

    @FXML
    private Label wrongLogin2;

    @FXML
    void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        wrongLogin1.setText("");
        wrongLogin2.setText("");

        ChatClientGUI m = new ChatClientGUI();
        if (username.getText().toString().equals("Yann") && port.getText().toString().equals("1234")) {
            m.changeScene("afterLogin.fxml");
        }

        else if (username.getText().isEmpty() && port.getText().isEmpty()) {
            wrongLogin2.setText("Entrez un username et un port");
        }

        else {
            wrongLogin1.setText("Nom ou port incorect");
        }
    }
}
