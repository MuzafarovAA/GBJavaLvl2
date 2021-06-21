package lesson4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("sample.fxml"));

        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Чат");
        primaryStage.setScene(new Scene(root));

        lesson4.Controller controller = fxmlLoader.getController();
        controller.userList.getItems().addAll("user1", "user2");

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
