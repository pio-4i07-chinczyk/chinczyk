package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static edu.pio.chinczyk.LudoApp.MENU_FXML_FILE;

public class RulesController extends RootController {
    @FXML
    public AnchorPane root;

    public void onReturnClick() {
        route(MENU_FXML_FILE);
    }
}
