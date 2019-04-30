package khai.detely.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public final class ViewUtil {

    private static Stage currentStage;

    private ViewUtil() {
    }

    public static void showView(String fxmlFileName, String title, boolean resizable) throws IOException {
        showView(fxmlFileName, title, resizable, false);
    }

    public static void showModalView(String fxmlFileName, String title, boolean resizable) throws IOException {
        showView(fxmlFileName, title, resizable, true);
    }

    private static void showView(String fxmlFileName, String title, boolean resizable, boolean isModal) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ViewUtil.class.getResource(fxmlFileName));
        Parent root = loader.load();
        stage.setScene(new Scene(root, root.prefWidth(1), root.prefHeight(1)));
        stage.setResizable(resizable);
        stage.setTitle(title);

        if (isModal) {
            stage.initOwner(currentStage);
            stage.initModality(Modality.WINDOW_MODAL);
        }

        if (currentStage != null && !isModal) {
            currentStage.close();
        }

        if (!isModal) {
            currentStage = stage;
        }
        stage.show();
    }


    public static void setCurrentStage(Stage newStage) {
        currentStage = newStage;
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void showError(String header, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(msg);

        alert.showAndWait();
    }
}
