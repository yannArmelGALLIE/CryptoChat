import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatScene implements Initializable {

    @FXML
    private Button button_send;

    @FXML
    private Button button_logout;

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

        Session.connection.setOnMessageReceived(this::handleIncomingMessage);

        button_send.setOnAction(e -> sendMessage());
        tf_message.setOnAction(e -> sendMessage());
        button_logout.setOnAction(e -> logout());
    }

    private void sendMessage() {
        String msg = tf_message.getText().trim();
        if (!msg.isEmpty()) {
            Session.connection.sendMessage(msg);
            displayOwnMessage(msg, "Vous");
            tf_message.clear();
        }
    }

    private void displayOwnMessage(String message, String author) {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(vbox, new Insets(10, 0, 0, 0));

        Label label = new Label(author);
        label.setFont(Font.font("System", FontWeight.BOLD, 17));
        label.setPadding(new Insets(0, 20, 0, 0));

        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(190);
        textFlow.setPrefWidth(Region.USE_COMPUTED_SIZE);

        textFlow.setStyle("-fx-background-color: lightgreen; -fx-background-radius: 50; -fx-padding: 8px;");

        vbox.getChildren().addAll(label, textFlow);

        Platform.runLater(() -> vbox_message.getChildren().add(vbox));
    }

    private void displayIncomingMessage(String message, String author) {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_LEFT);
        VBox.setMargin(vbox, new Insets(10, 0, 0, 0));

        Label label = new Label(author);
        label.setFont(Font.font("System", FontWeight.BOLD, 17));
        label.setPadding(new Insets(0, 0, 0, 20));

        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(190);
        textFlow.setPrefWidth(Region.USE_COMPUTED_SIZE);

        textFlow.setStyle("-fx-background-color: lightblue; -fx-background-radius: 50; -fx-padding: 8px;");

        vbox.getChildren().addAll(label, textFlow);

        Platform.runLater(() -> vbox_message.getChildren().add(vbox));

    }

    private void handleIncomingMessage(String message) {
        String[] parts = message.split(":", 2);
        String author = parts.length > 1 ? parts[0].trim() : "Serveur";
        String msg = parts.length > 1 ? parts[1].trim() : message;
        displayIncomingMessage(msg, author);
    }

    private void logout() {
        try {
            Session.connection.close();
            Platform.runLater(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginScene.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) button_logout.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
