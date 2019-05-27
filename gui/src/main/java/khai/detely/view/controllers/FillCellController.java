package khai.detely.view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import khai.detely.components.NumberTextField;
import khai.detely.view.ViewUtil;
import khai.detely.view.contexts.FillContext;

import java.net.URL;
import java.util.ResourceBundle;

public class FillCellController implements Initializable {
    public NumberTextField capacity;
    public NumberTextField windowWidth;
    public NumberTextField transmissionProbability;
    public NumberTextField acknowledgmentProbability;
    public NumberTextField delay;
    public NumberTextField channelTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        capacity.setText(String.valueOf(FillContext.capacity));
        windowWidth.setText(String.valueOf(FillContext.windowWidth));
        transmissionProbability.setText(String.valueOf(FillContext.transmissionProbability));
        acknowledgmentProbability.setText(String.valueOf(FillContext.acknowledgmentProbability));
        delay.setText(String.valueOf(FillContext.delay));
        channelTime.setText(String.valueOf(FillContext.channelTime));
    }

    public void btnDone_Click(ActionEvent actionEvent) {
        ((Stage) capacity.getScene().getWindow()).close();
    }

    public void btnCalculate_Click(ActionEvent actionEvent) {
        double Vtrn = transmissionProbability.getNumberAsDouble();
        double Vack = acknowledgmentProbability.getNumberAsDouble();
        double C = capacity.getNumberAsDouble();
        int w = windowWidth.getNumber().intValue();
        int m = FillContext.m;
        double Tm = channelTime.getNumberAsDouble();
        double t = ((1 - Vtrn)
            * (1 - Math.pow(1 - Vtrn, w))
            * (1 - (m - 1) * Math.pow(Vack, w) + (m - 2) * Vack)
            / (C * m * w * Vtrn)) - Tm / (m * w);
        FillContext.acknowledgmentProbability = Vack;
        FillContext.transmissionProbability = Vtrn;
        FillContext.capacity = C;
        FillContext.windowWidth = w;
        FillContext.channelTime = Tm;
        FillContext.delay = t;

        delay.setText(String.valueOf(t));
    }
}
