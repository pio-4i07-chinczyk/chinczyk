package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameSelectorController {
    @FXML
    Pane root;

    public void onSelectClicked() {
        Scene rulesScene = this.root.getScene();
        LudoApp game = (LudoApp)(rulesScene.getUserData());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("game.fxml"));
        stage.show();
    }

    public void onReturnClicked() {
        Scene rulesScene = this.root.getScene();
        LudoApp game = (LudoApp)(rulesScene.getUserData());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("menu.fxml"));
        stage.show();
    }
}
