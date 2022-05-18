package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RulesController extends RootController {
    @FXML
    public AnchorPane root;

    public void onReturnClick() {
        LudoApp game = (LudoApp)(this.getApp());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("menu.fxml"));
        stage.show();
    }
}
