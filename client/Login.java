import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {
    private ChatConnection connection;

    @FXML
    private TextField port;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin1;

    @FXML
    private Label wrongLogin2;

    @FXML
    private Label wrongLogin3;

    @FXML
    void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        wrongLogin1.setText("");
        wrongLogin2.setText("");
        wrongLogin3.setText("");

        if (username.getText().isEmpty() && port.getText().isEmpty()) {
            wrongLogin2.setText("Entrez un username et un port");
            wrongLogin1.setText("");
            wrongLogin3.setText("");
        } else {
            try {
        int portNumber = Integer.parseInt(port.getText());
        connection = new ChatConnection();
        connection.connect("localhost", portNumber, username.getText());

        Session.connection = connection;
        Session.username = username.getText();
        
        ChatClientGUI.changeScene("ChatScene.fxml");
       }
       catch (NumberFormatException e) {
        wrongLogin1.setText("Le port doit être un nombre");
        wrongLogin2.setText("");
        wrongLogin3.setText("");
       }
       catch (IOException e) {
        wrongLogin3.setText("Impossible de se connecter au serveur");
        wrongLogin2.setText("");
        wrongLogin1.setText("");
        e.printStackTrace();
       }
        }
       
    }
}
