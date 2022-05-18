package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RulesController {
    @FXML
    public AnchorPane root;

    public void onButtonClick() {
        Scene rulesScene = this.root.getScene();
        LudoApp game = (LudoApp)(rulesScene.getUserData());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("hello-view.fxml"));
        stage.show();
    }
}
