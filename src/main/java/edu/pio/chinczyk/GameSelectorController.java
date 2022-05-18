package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameSelectorController extends RootController {
    @FXML
    Pane root;

    public void onSelectClicked() {
        LudoApp game = (LudoApp)(this.getApp());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("game.fxml"));
        stage.show();
    }

    public void onReturnClicked() {
        LudoApp game = (LudoApp)(this.getApp());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("menu.fxml"));
        stage.show();
    }
}
