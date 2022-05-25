package edu.pio.chinczyk;

import edu.pio.chinczyk.game.Board;
import edu.pio.chinczyk.game.Pawn;
import edu.pio.chinczyk.game.Player;
import edu.pio.chinczyk.game.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends RootController implements Initializable {
    @FXML
    private Parent root;
    @FXML
    private Button end_button;
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

    int currentPlayer = 2;
    boolean selectPawn = false;

    public void onDiceClick() {
        selectPawn = true;
    }

    public void onPawnClick(MouseEvent mouseEvent) {
        if(!selectPawn)
            return;

        Board board = ((LudoApp)this.getApp()).getBoard();
        Player yellow = board.getPlayer(2);
        Pawn pawn = yellow.getPawn(0);

        Tile next = pawn.getTile().getNext();
        if(next == null)
            return;

        pawn.setTile(next);

        Board.Pos2D coords = board.getTileCoords(new Board.Pos2D(600, 600), next.getPos());

        yellow_pawn_1.setX(coords.x);
        yellow_pawn_1.setY(coords.y);

        if(dice.getRollResult() != 6) {
            // TODO: zmiana gracza
        }

        dice.setWaitingForRoll(true);
        selectPawn = false;
    }

    public void endGame() {

        LudoApp game = (LudoApp)(this.getApp());

        Stage stage = game.getStage();
        stage.setScene(game.getScene("menu.fxml"));
        stage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectPawn = false;
        dice.setWaitingForRoll(true);

        //yellow_pawn_1.setUserData();
    }
}
