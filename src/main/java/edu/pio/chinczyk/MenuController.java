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
    protected void onHelloButtonClick() {
        Scene rulesScene = this.root.getScene();
        LudoApp game = (LudoApp)(rulesScene.getUserData());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("rules.fxml"));
        stage.show();
    }
}