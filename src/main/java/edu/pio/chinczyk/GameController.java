package edu.pio.chinczyk;

import edu.pio.chinczyk.game.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

import static edu.pio.chinczyk.LudoApp.MENU_FXML_FILE;
import static edu.pio.chinczyk.game.Board.TILES_NUMBER;
import static edu.pio.chinczyk.game.Color.*;
import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class GameController extends RootController implements Initializable {
    private static final int MAX_PLAYERS = 4;
    private static final int MIN_PLAYERS = 2;
    private static final int FIRST_PLAYER = 0;
    private static final int FIRST_PAWN = 0;
    public static final int PAWNS_PER_PLAYER = 4;
    public static final int ROLL_IN_SERIES_INIT = 0;
    public static final int MAX_ROLL_IN_SERIES = 3;
    public static final int ENTRY_ROLL_VALUE = 6;
    private static final int REPEAT_ROLL_VALUE = 6;
    public static final String HTML_WHITE = "white";
    public static final String HTML_BLACK = "black";
    private static final String GAME_OVER = "Game over";
    private static final String PAWN_COMMUNICATE = " wybiera pionka!";
    private static final String ROLL_COMMUNICATE = " losuje!";
    private static final String WON_COMMUNICATE = "Zwyciezcy:";
    private static final int FIRST_RANK = 1;

    @FXML
    private Label status;

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

    int playersCount;

    GameState state;
    Player currentPlayer;
    int rollInSerie;
    private final Queue<Player> playersQueue;
    private final List<Player> winners;

    private final ImageView[][] pawns;

    public GameController() {
        playersCount = 0;
        state = GameState.WAITS_FOR_ROLL;
        currentPlayer = null;
        rollInSerie = 0;
        playersQueue = new LinkedList<>();
        winners = new ArrayList<>();

        pawns = new ImageView[MAX_PLAYERS][PAWNS_PER_PLAYER];
    }

    @Override
    public void runBeforeStart() {
        bindPawnsToModel();
    }

    @Override
    public void runBeforeRoute(RootController previous) {
        if(!(previous instanceof GameSelectorController)) {
            return;
        }

        this.playersCount = ((GameSelectorController) previous).getPlayers();

        resetPawns();
        hideUnusedPawns();
        initPlayersQueue();
        winners.clear();
        currentPlayer = playersQueue.poll();
        setState(GameState.WAITS_FOR_ROLL);
    }

    private void resetPawns() {
        Board boardModel = getApp().getBoard();

        Vec2i boardSize = new Vec2i((int) board.getFitWidth(), (int) board.getFitHeight());
        Vec2i tileSize = new Vec2i(boardSize.x / TILES_NUMBER, boardSize.y / TILES_NUMBER);

        for(int playerId = 0; playerId < playersCount; ++playerId) {
            Player player = boardModel.getPlayer(playerId);
            LobbyTile[] lobby = player.getLobbyTiles();

            for(int pawnId = 0; pawnId < PAWNS_PER_PLAYER; ++pawnId) {
                ImageView view = pawns[playerId][pawnId];
                Pawn model = (Pawn) view.getUserData();

                Tile tile = lobby[pawnId];
                model.setTile(tile);

                Vec2i coords = getApp().getBoard().getTilePos(boardSize, tile.getPos());
                view.setFitWidth(tileSize.x);
                view.setFitHeight(tileSize.y);
                view.setLayoutX(coords.x);
                view.setLayoutY(coords.y);
            }
        }
    }

    private void hideUnusedPawns() {
        for(int i = 0; i < MAX_PLAYERS; ++i) {
            ImageView[] playerPawns = pawns[i];

            for(int j = 0; j < PAWNS_PER_PLAYER; ++j) {
                playerPawns[j].setVisible(i < playersCount);
            }
        }
    }

    private void initPlayersQueue() {
        Board board = getApp().getBoard();
        playersQueue.clear();

        playersQueue.add(board.getPlayer(BLUE.getIndex()));
        if(playersCount > 2) {
            playersQueue.add(board.getPlayer(YELLOW.getIndex()));
        }
        playersQueue.add(board.getPlayer(GREEN.getIndex()));
        if(playersCount > 3) {
            playersQueue.add(board.getPlayer(RED.getIndex()));
        }
    }

    private void setState(GameState newState) {
        state = newState;
        Color playerColor = currentPlayer.getColor();
        String currentName = playerColor.toString();

        if(newState == GameState.WAITS_FOR_ROLL) {
            status.setText(currentName + ROLL_COMMUNICATE);
            dice.setWaitingForRoll(true);
        }

        if(newState == GameState.WAITS_FOR_CHOICE) {
            status.setText(currentName + PAWN_COMMUNICATE);
        }

        Paint backgroundPaint = Paint.valueOf(currentName.toLowerCase());
        status.setBackground(Background.fill(backgroundPaint));
        status.setTextFill((playerColor.getPaint()));
    }

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
        Board board = this.getApp().getBoard();

        for(int playerID = 0; playerID < MAX_PLAYERS; ++playerID) {
            Player player = board.getPlayer(playerID);
            for(int pawnID = 0; pawnID < PAWNS_PER_PLAYER; ++pawnID) {
                Pawn pawn = player.getPawn(pawnID);
                pawns[playerID][pawnID].setUserData(pawn);
            }
        }
    }

    public void onDiceClick() {
        if(state != GameState.WAITS_FOR_ROLL)
            return;

        if(currentPlayer.isOnlyInLobby()) {
            rollInSerie++;

            if(dice.getRollResult() == ENTRY_ROLL_VALUE) {
                setState(GameState.WAITS_FOR_CHOICE);
                return;
            }

            if(rollInSerie < MAX_ROLL_IN_SERIES) {
                setState(GameState.WAITS_FOR_ROLL);
                return;
            }

            rollInSerie = ROLL_IN_SERIES_INIT;
            enqueueCurrentPlayer();
        }
        else {
            if(currentPlayer.canMoveBy(dice.getRollResult())) {
                setState(GameState.WAITS_FOR_CHOICE);
                return;
            }

            if(dice.getRollResult() == REPEAT_ROLL_VALUE) {
                setState(GameState.WAITS_FOR_ROLL);
                return;
            }

            enqueueCurrentPlayer();
        }
    }

    void enqueueCurrentPlayer() {
        playersQueue.add(currentPlayer);

        if(playersQueue.size() >= MIN_PLAYERS) {
            currentPlayer = playersQueue.poll();
            setState(GameState.WAITS_FOR_ROLL);
            return;
        }

        showWinAlert();
    }

    private LobbyTile findTileForKilledPawn(Player pawnOwner) {
        if(pawnOwner == null) {
            return null;
        }

        for(LobbyTile lobbyTile : pawnOwner.getLobbyTiles()) {
            boolean free = true;

            for(int pawnID = FIRST_PAWN; pawnID < PAWNS_PER_PLAYER; ++pawnID) {
                if(pawnOwner.getPawn(pawnID).getTile() == lobbyTile) {
                    free = false;
                    break;
                }
            }

            if(free) {
                return lobbyTile;
            }
        }

        return null;
    }

    private void movePawnToTile(Pawn movedPawn, Tile destination) {
        movedPawn.setTile(destination);

        List<ImageView> pawnsOnTile = new ArrayList<>();
        for(int playerId = FIRST_PLAYER; playerId < playersCount; ++playerId) {
            for(int pawnId = FIRST_PAWN; pawnId < PAWNS_PER_PLAYER; ++pawnId) {
                ImageView pawnView = pawns[playerId][pawnId];
                Pawn pawnModel = (Pawn) pawnView.getUserData();

                if(pawnModel.getTile() == destination) {
                    pawnsOnTile.add(pawnView);
                }
            }
        }

        Vec2i boardSize = new Vec2i((int) board.getFitWidth(), (int) board.getFitHeight());
        Vec2i tileSize = new Vec2i(boardSize.x / TILES_NUMBER, boardSize.y / TILES_NUMBER);

        // if destinated tile does not protect against capturing a pawn
        if(!(destination instanceof StartingTile)) {
            List<ImageView> pawnsToCapture = pawnsOnTile.stream().filter( pawn -> {
                Pawn pawnModel = (Pawn) pawn.getUserData();
                return pawnModel.getColor() != movedPawn.getColor();
            }).toList();

            Board board = this.getApp().getBoard();
            Player pawnOwner = null;

            for(ImageView pawn : pawnsToCapture) {
                Pawn pawnToRemove = (Pawn)pawn.getUserData();

                for(int playerID = 0; playerID < playersCount; ++playerID) {
                    if(board.getPlayer(playerID).getColor() == pawnToRemove.getColor()) {
                        pawnOwner = board.getPlayer(playerID);
                    }
                }

                LobbyTile lobbyTile = findTileForKilledPawn(pawnOwner);
                if(lobbyTile != null) {
                    Vec2i coords = getApp().getBoard().getTilePos(boardSize, lobbyTile.getPos());
                    pawn.setLayoutX(coords.x);
                    pawn.setLayoutY(coords.y);
                    pawn.setFitWidth(tileSize.x);
                    pawn.setFitHeight(tileSize.y);
                    pawnToRemove.setTile(lobbyTile);
                }
            }

            pawnsOnTile.removeAll(pawnsToCapture);
        }

        int tilesCount = pawnsOnTile.size();
        Vec2i pos = getApp().getBoard().getTilePos(boardSize, movedPawn.getPos());
        int tileGrid = (int) ceil(sqrt(pawnsOnTile.size()));
        Vec2i gridSize = new Vec2i(tileSize.x / tileGrid, tileSize.y / tileGrid);

        for(int row = 0; row < tileGrid; ++row) {
            for(int col = 0; ((row * tileGrid) + col) < tilesCount; ++col) {
                ImageView pawnOnTile = pawnsOnTile.get(row * tileGrid + col);

                pawnOnTile.setFitWidth(gridSize.x);
                pawnOnTile.setFitHeight(gridSize.y);
                pawnOnTile.setLayoutX(pos.x + col * gridSize.x);
                pawnOnTile.setLayoutY(pos.y + row * gridSize.y);
            }
        }
    }

    public void onPawnClick(MouseEvent mouseEvent) {
        if(state != GameState.WAITS_FOR_CHOICE) {
            return;
        }

        int rollResult = dice.getRollResult();
        ImageView pawnView = (ImageView) mouseEvent.getTarget();
        Pawn pawnModel = (Pawn) pawnView.getUserData();

        Tile tileAfterSteps = currentPlayer.movePawnBy(pawnModel, rollResult);
        // has chosen not allowed pawn
        if(tileAfterSteps == null) {
            return;
        }

        movePawnToTile(pawnModel, tileAfterSteps);

        if(currentPlayer.hasWon()) {
            winners.add(currentPlayer);
        }
        else {
            if(rollResult == REPEAT_ROLL_VALUE) {
                setState(GameState.WAITS_FOR_ROLL);
                return;
            }
            else {
                playersQueue.add(currentPlayer);
            }
        }

        if(playersQueue.size() >= MIN_PLAYERS) {
            currentPlayer = playersQueue.poll();
            setState(GameState.WAITS_FOR_ROLL);
        }
        else {
            showWinAlert();
        }
    }

    public void endGame() {
        LudoApp app = this.getApp();

        Stage stage = app.getStage();
        stage.setScene(app.getScene(MENU_FXML_FILE));
        stage.show();
    }

    private void showWinAlert() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(GAME_OVER);
            alert.setHeaderText(GAME_OVER);

            int rank = FIRST_RANK;
            StringBuilder message = new StringBuilder(WON_COMMUNICATE).append('\n');

            for(Player winner : winners) {
                message.append(rank).append(". ");
                message.append(winner.getColor().toString());
                message.append('\n');
                ++rank;
            }

            if(playersQueue.peek() != null) {
                message.append(rank).append(". ");
                message.append(playersQueue.peek().getColor().toString());
                message.append('\n');
            }

            alert.setContentText(message.toString());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    LudoApp game = this.getApp();

                    Stage stage = game.getStage();
                    stage.setScene(game.getScene(MENU_FXML_FILE));
                    stage.show();
                }
            });
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindPawns();
    }
}
