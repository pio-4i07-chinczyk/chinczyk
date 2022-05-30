package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static edu.pio.chinczyk.LudoApp.GAME_SELECTOR_FXML_FILE;
import static edu.pio.chinczyk.LudoApp.RULES_FXML_FILE;

public class MenuController extends RootController {
    @FXML
    public Parent root;

    @FXML
    protected void onPlayClick(MouseEvent mouseEvent) {
        route(GAME_SELECTOR_FXML_FILE);
    }

    public void onRulesClick(MouseEvent mouseEvent) {
        route(RULES_FXML_FILE);
    }

    public void onQuitClick(MouseEvent mouseEvent) {
        Stage stage = getApp().getStage();
        stage.hide();
    }
}