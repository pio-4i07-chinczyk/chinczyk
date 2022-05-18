package edu.pio.chinczyk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends RootController implements Initializable {
    @FXML
    private Parent root;

    @FXML
    private ImageView board;

    @FXML
    private ImageView blue_pawn_1;

    @FXML
    private ImageView blue_pawn_2;

    @FXML
    private ImageView blue_pawn_3;

    @FXML
    private ImageView blue_pawn_4;

    @FXML
    private ImageView green_pawn_1;

    @FXML
    private ImageView green_pawn_2;

    @FXML
    private ImageView green_pawn_3;

    @FXML
    private ImageView green_pawn_4;

    @FXML
    private ImageView red_pawn_1;

    @FXML
    private ImageView red_pawn_2;

    @FXML
    private ImageView red_pawn_3;

    @FXML
    private ImageView red_pawn_4;

    @FXML
    private ImageView yellow_pawn_1;

    @FXML
    private ImageView yellow_pawn_2;

    @FXML
    private ImageView yellow_pawn_3;

    @FXML
    private ImageView yellow_pawn_4;

    @FXML
    private DiceController dice;

    public void onDiceClick() {
        System.out.println("los los");
    }

    public void onPawnClick() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dice.setWaitingForRoll(true);
    }
}
