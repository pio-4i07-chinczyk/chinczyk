package edu.pio.chinczyk.chinczyk;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameController {
    @FXML
    public VBox root;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Scene rulesScene = this.root.getScene();
        Game game = (Game)(rulesScene.getUserData());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("rulesScene.fxml"));
        stage.show();
    }
}