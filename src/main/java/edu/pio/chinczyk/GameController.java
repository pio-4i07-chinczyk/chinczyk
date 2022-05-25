package edu.pio.chinczyk;

import edu.pio.chinczyk.game.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends RootController implements Initializable {
    private static final int MAX_PLAYERS = 4;
    private static final int MIN_PLAYERS = 1;
    private static final int FIRST_PLAYER = 0;
    private static final int PAWNS_PER_PLAYER = 4;

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
    int playersCount = 4;
    boolean selectPawn = false;
    private final ImageView[][] pawns = new ImageView[MAX_PLAYERS][PAWNS_PER_PLAYER];

    private void bindPawns() {
        pawns[0][0] = blue_pawn_1;
        pawns[0][1] = blue_pawn_2;
        pawns[0][2] = blue_pawn_3;
        pawns[0][3] = blue_pawn_4;

        pawns[1][0] = green_pawn_1;
        pawns[1][1] = green_pawn_2;
        pawns[1][2] = green_pawn_3;
        pawns[1][3] = green_pawn_4;

        pawns[2][0] = yellow_pawn_1;
        pawns[2][1] = yellow_pawn_2;
        pawns[2][2] = yellow_pawn_3;
        pawns[2][3] = yellow_pawn_4;

        pawns[3][0] = red_pawn_1;
        pawns[3][1] = red_pawn_2;
        pawns[3][2] = red_pawn_3;
        pawns[3][3] = red_pawn_4;
    }

    private void bindPawnsToModel() {
        Board board = ((LudoApp)this.getApp()).getBoard();

        for(int playerID = 0; playerID < MAX_PLAYERS; ++playerID) {
            Player player = board.getPlayer(playerID);
            for(int pawnID = 0; pawnID < PAWNS_PER_PLAYER; ++pawnID) {
                Pawn pawn = player.getPawn(pawnID);
                pawns[playerID][pawnID].setUserData(pawn);
            }
        }
    }

    private void runBeforeShow() {
        bindPawnsToModel();
    }

    public void onDiceClick() {
        selectPawn = true;
    }

    public void onPawnClick(MouseEvent mouseEvent) {
        if (!selectPawn)
            return;

        Board board = ((LudoApp) this.getApp()).getBoard();
        Player yellow = board.getPlayer(2);
        Pawn pawn = yellow.getPawn(0);

        Tile next = pawn.getTile().getNext();
        if (next == null)
            return;

        pawn.setTile(next);

        Vec2i coords = board.getTileCoords(new Vec2i(600, 600), next.getPos());

        yellow_pawn_1.setX(coords.x);
        yellow_pawn_1.setY(coords.y);

        if (dice.getRollResult() != 6) {
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

    private void showWinAlert(Player.Color winner) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game over");
            alert.setHeaderText("Game over");

            alert.setContentText(winner.toString() + " wins!");

            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    LudoApp game = (LudoApp)(this.getApp());

                    Stage stage = game.getStage();
                    stage.setScene(game.getScene("menu.fxml"));
                    stage.show();
                }
            });
        });
    }

    private void onPawnMove() {
        checkIfWinHappened();
    }

    private boolean checkIfWinHappened() {
        for(int playerID = 0; playerID < playersCount; ++playerID) {
            for(int pawnID = 0; pawnID < PAWNS_PER_PLAYER; ++pawnID) {
                Pawn pawn = (Pawn)pawns[playerID][pawnID].getUserData();
                if(!(pawn.getTile() instanceof HomeTile)){
                    return false;
                }
            }
        }
      
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectPawn = false;
        dice.setWaitingForRoll(true);
    }
}
