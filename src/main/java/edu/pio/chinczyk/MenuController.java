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
        LudoApp game = (LudoApp)(this.getApp());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("game-selector.fxml"));
        stage.show();
    }

    public void onRulesClick(MouseEvent mouseEvent) {
        LudoApp game = (LudoApp)(this.getApp());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("rules.fxml"));
        stage.show();
    }

    public void onQuitClick(MouseEvent mouseEvent) {
        LudoApp game = (LudoApp)(this.getApp());

        Stage stage = game.getStage();
        stage.hide();
    }
}