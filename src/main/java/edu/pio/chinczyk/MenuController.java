package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController extends RootController {
    @FXML
    public Parent root;

    @FXML
    protected void onPlayClick(MouseEvent mouseEvent) {
        route("game-selector.fxml");
    }

    public void onRulesClick(MouseEvent mouseEvent) {
        route("rules.fxml");
    }

    public void onQuitClick(MouseEvent mouseEvent) {
        Stage stage = getApp().getStage();
        stage.hide();
    }
}