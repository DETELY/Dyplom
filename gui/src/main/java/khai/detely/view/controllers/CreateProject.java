package khai.detely.view.controllers;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;
import khai.detely.AppConstants;
import khai.detely.view.ViewConstants;
import khai.detely.view.ViewUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateProject implements Initializable {

    private static final Logger log = Logger.getLogger(CreateProject.class);


    private final PseudoClass error = PseudoClass.getPseudoClass("error");


    @FXML
    private TextField txtProjectName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnBack;

    @FXML
    private void btnCreate_Click(ActionEvent event) {
        if (!validateFields()) {
            return;
        }
        try {
            ViewUtil.showView(ViewConstants.ViewPageName.MAIN.getName(),AppConstants.PROGRAM_NAME, true);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @FXML
    private void btnBack_Click(ActionEvent event) throws IOException {
        Window window = ((Node) event.getSource()).getScene().getWindow();
        ViewUtil.showView(ViewConstants.ViewPageName.CREATE_DIALOG.START_PAGE.getName(), AppConstants.PROGRAM_NAME, false);
    }

    @FXML
    private void someTxt_KeyPressed(KeyEvent event) {
        offRed(event);
    }


    @FXML
    private void enterHandler(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            btnCreate_Click(null);
        }
    }

    private boolean validateFields() {
        boolean result = true;
        String name = txtProjectName.getText();
        if (name == null || name.isEmpty()) {
            makeRed(txtProjectName);
            result = false;
        }

        return result;
    }

    private void offRed(KeyEvent event) {
        ((TextField) event.getTarget()).setStyle("-fx-control-inner-background: white;");
    }

    private void makeRed(Control txt) {
        txt.setStyle("-fx-control-inner-background: red;");
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

}
