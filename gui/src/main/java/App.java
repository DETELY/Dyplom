import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        try {
            String firstViewPath = "/views/startPage.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(firstViewPath));
            primaryStage.setScene(new Scene(root, root.prefWidth(1), root.prefHeight(1)));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setContentText(e.getMessage());
        }
    }
}
