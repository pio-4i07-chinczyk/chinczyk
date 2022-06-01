package edu.pio.chinczyk;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import static edu.pio.chinczyk.LudoApp.GAME_FXML_FILE;
import static edu.pio.chinczyk.LudoApp.MENU_FXML_FILE;

public class GameSelectorController extends RootController {
    private int players;

    public int getPlayers() {
        return players;
    }

    public void onSelectClicked(ActionEvent event) {
        Button button = (Button) event.getTarget();
        players = Integer.parseInt((String) button.getUserData());

        route(GAME_FXML_FILE);
    }

    public void onReturnClicked() {
        route(MENU_FXML_FILE);
    }
}
