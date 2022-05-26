package edu.pio.chinczyk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSelectorController extends RootController {
    @FXML
    Pane root;

    private int players;

    public int getPlayers() {
        return players;
    }

    public void onSelectClicked(ActionEvent event) {
        Button btn = (Button) event.getTarget();
        players = Integer.parseInt((String) btn.getUserData());

        route("game.fxml");
    }

    public void onReturnClicked() {
        route("menu.fxml");
    }
}
