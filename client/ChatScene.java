import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatScene implements Initializable {

    @FXML
    private Button button_send;

    @FXML
    private TextField tf_message;

    @FXML
    private VBox vbox_message;

    @FXML
    private ScrollPane sp_main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        vbox_message.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });
        
        Session.connection.setOnMessageReceived(this::displayIncomingMessage);
        
        button_send.setOnAction(e -> sendMessage());
        tf_message.setOnAction(e -> sendMessage());
    }

    private void sendMessage() {
        String msg = tf_message.getText().trim();
        if (!msg.isEmpty()) {
            Session.connection.sendMessage(msg);
            displayOwnMessage(Session.username + ": " + msg);
            tf_message.clear();
        }
    }

    private void displayOwnMessage(String message) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_RIGHT);
        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: lightgreen; -fx-padding: 8px; -fx-background-radius: 10; -fx-margin: 10px");
        hbox.getChildren().add(textFlow);

        Platform.runLater(() -> vbox_message.getChildren().add(hbox));
    }

    private void displayIncomingMessage(String message) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: lightblue; -fx-padding: 8px; -fx-background-radius: 10; -fx-margin: 10px;");
        hbox.getChildren().add(textFlow);

        Platform.runLater(() -> vbox_message.getChildren().add(hbox));
    }
}
