package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    public Parent root;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onPlayClick() {
        Scene rulesScene = this.root.getScene();
        LudoApp game = (LudoApp)(rulesScene.getUserData());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("game-selector.fxml"));
        stage.show();
    }
}