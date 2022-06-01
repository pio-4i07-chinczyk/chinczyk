package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import static edu.pio.chinczyk.LudoApp.GAME_SELECTOR_FXML_FILE;
import static edu.pio.chinczyk.LudoApp.RULES_FXML_FILE;

public class MenuController extends RootController {
    @FXML
    protected void onPlayClick() {
        route(GAME_SELECTOR_FXML_FILE);
    }

    public void onRulesClick() {
        route(RULES_FXML_FILE);
    }

    public void onQuitClick() {
        Stage stage = getApp().getStage();
        stage.hide();
    }
}