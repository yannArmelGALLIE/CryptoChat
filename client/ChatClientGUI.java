import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatClientGUI extends Application {
    private static Stage stg; 

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage; 

        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("CryptoChat");
        primaryStage.show();
    }
  
    public static void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(ChatClientGUI.class.getResource(fxml));
        stg.getScene().setRoot(pane);  
    }

    public static void main(String[] args) {
        launch(args);
    }
}
