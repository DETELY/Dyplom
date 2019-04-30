package khai.detely.view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import khai.detely.AppConstants;
import khai.detely.view.ViewConstants;
import khai.detely.view.ViewUtil;
import org.apache.log4j.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import khai.detely.viewmodel.StartPageVM;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPage  implements Initializable {

    private static final Logger log = Logger.getLogger(StartPage.class);


    private StartPageVM viewModel;

    @FXML
    private ListView<String> lstProjects;

    @FXML
    private void btnCreate_Click(ActionEvent event) {
        try {
            ViewUtil.showView(ViewConstants.ViewPageName.CREATE_DIALOG.getName(),AppConstants.PROGRAM_NAME, false);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @FXML
    private void btnSettings_Click(ActionEvent event) {

    }

    @FXML
    private void btnOpen_Click(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(((Button) event.getSource()).getScene().getWindow());

    }

    @FXML
    private void lstProjects_DoubleClick(MouseEvent event) {
    }

    public void initialize(URL location, ResourceBundle resources) {

    }
}
