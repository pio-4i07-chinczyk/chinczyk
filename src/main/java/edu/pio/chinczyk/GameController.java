package edu.pio.chinczyk;

import edu.pio.chinczyk.game.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

import static edu.pio.chinczyk.game.Board.TILES_N;
import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class GameController extends RootController implements Initializable {
    private static final int MAX_PLAYERS = 4;
    private static final int MIN_PLAYERS = 1;
    private static final int FIRST_PLAYER = 0;
    private static final int PAWNS_PER_PLAYER = 4;
    private static final int ENTRY_ROLL_VALUE = 6;
    private static final int REPEAT_ROLL_VALUE = 6;
    public static final String HTML_WHITE = "white";
    public static final String HTML_BLACK = "black";
    public static final String GAME_OVER = "Game over";
    public static final String MENU_FXML_FILE = "menu.fxml";

    @FXML
    private Label status;

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

    int currentPlayer;
    Color winner;
    int playersCount;
    int rollInSerie;
    private final SimpleBooleanProperty selectPawn;
    private final ImageView[][] pawns;

    public GameController() {
        selectPawn = new SimpleBooleanProperty();
        pawns = new ImageView[MAX_PLAYERS][PAWNS_PER_PLAYER];
    }

    @Override
    public void runBeforeStart() {
        bindPawnsToModel();
    }

    @Override
    public void runBeforeRoute(RootController previous) {
        if(previous instanceof GameSelectorController) {
            setPlayersCount(((GameSelectorController) previous).getPlayers());
            resetPawns();
            currentPlayer = 0;
            selectPawn.set(true);
            selectPawn.set(false);
        }
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;

        for(int i = 0; i < MAX_PLAYERS; ++i) {
            ImageView[] playerPawns = pawns[i];

            for(int j = 0; j < PAWNS_PER_PLAYER; ++j) {
                playerPawns[j].setVisible(i < playersCount);
            }
        }
    }

    private void resetPawns() {
        Board boardModel = getApp().getBoard();

        Vec2i boardSize = new Vec2i((int) board.getFitWidth(), (int) board.getFitHeight());
        Vec2i tileSize = new Vec2i(boardSize.x / TILES_N, boardSize.y / TILES_N);

        for(int p = 0; p < playersCount; ++p) {
            Player player = boardModel.getPlayer(p);
            LobbyTile[] lobby = player.getLobbyTiles();

            for(int q = 0; q < PAWNS_PER_PLAYER; ++q) {
                ImageView view = pawns[p][q];
                Pawn model = (Pawn) view.getUserData();

                Tile tile = lobby[q];
                model.setTile(tile);

                Vec2i coords = getApp().getBoard().getTileCoords(boardSize, tile.getPos());
                view.setFitWidth(tileSize.x);
                view.setFitHeight(tileSize.y);
                view.setLayoutX(coords.x);
                view.setLayoutY(coords.y);
            }
        }
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
        Board board = ((LudoApp)this.getApp()).getBoard();

        for(int playerID = 0; playerID < MAX_PLAYERS; ++playerID) {
            Player player = board.getPlayer(playerID);
            for(int pawnID = 0; pawnID < PAWNS_PER_PLAYER; ++pawnID) {
                Pawn pawn = player.getPawn(pawnID);
                pawns[playerID][pawnID].setUserData(pawn);
            }
        }
    }

    public void onDiceClick() {
        selectPawn.set(true);
    }

    public void roundPlayer() {
        switch(playersCount) {
            case 2:
                currentPlayer = switch(currentPlayer) {
                    case 0 -> 1;
                    default -> 0;
                };
                break;
            case 3:
                currentPlayer = switch(currentPlayer) {
                    case 0 -> 2;
                    case 2 -> 1;
                    default -> 0;
                };
                break;
            case 4:
                currentPlayer = switch(currentPlayer) {
                    case 0 -> 2;
                    case 1 -> 3;
                    case 2 -> 1;
                    default -> 0;
                };
                break;
            default:
                currentPlayer = 0;
        }
    }

    public boolean isPlayerOnBoard(int player) {
        ImageView[] playerPawns = pawns[player];

        for(int i = 0; i < PAWNS_PER_PLAYER; ++i) {
            Pawn pawn = (Pawn) playerPawns[i].getUserData();
            if(!(pawn.getTile() instanceof LobbyTile))
                return true;
        }

        return false;
    }

    private LobbyTile findTileForKilledPawn(Player pawnOwner) {
        if(pawnOwner != null) {
            for(LobbyTile lobbyTile : pawnOwner.getLobbyTiles()) {
                boolean free = true;
                for(int pawnID = 0; pawnID < PAWNS_PER_PLAYER; ++pawnID) {
                    if(pawnOwner.getPawn(pawnID).getTile() == lobbyTile) {
                        free = false;
                        break;
                    }
                }
                if(free)
                    return lobbyTile;
            }
        }

        return null;
    }

    public void onPawnClick(MouseEvent mouseEvent) {
        if(!selectPawn.get())
            return;

        int diceResult = dice.getRollResult();
        ImageView clickedPawnView = (ImageView) mouseEvent.getTarget();
        Pawn clickedPawnModel = (Pawn) clickedPawnView.getUserData();

        if(currentPlayer != clickedPawnModel.getColor().getIndex())
            return;

        Tile current = clickedPawnModel.getTile();

        if(current instanceof LobbyTile) {
            if(diceResult == ENTRY_ROLL_VALUE) {
                current = current.getNext();
                rollInSerie = 0;
            }
            else {
                if(isPlayerOnBoard(currentPlayer))
                    return;

                ++rollInSerie;
                if(rollInSerie >= 3) {
                    roundPlayer();
                    rollInSerie = 0;
                }

                selectPawn.set(false);
                return;
            }
        }
        else {
            for (int i = 0; i < dice.getRollResult(); ++i) {
                Tile next = current.getNext();
                Tile alt = current.getAlt();

                if((alt instanceof HomeTile) && ((HomeTile)alt).getColor().getIndex() == currentPlayer) {
                        current = alt;
                }
                else {
                    if (next == null) {
                        roundPlayer();
                        return;
                    }

                    current = next;
                }
            }
        }

        clickedPawnModel.setTile(current);

        List<ImageView> pawnsOnTile = new ArrayList<>();
        for(int i = 0; i < playersCount; ++i) {
            for(int j = 0; j < PAWNS_PER_PLAYER; ++j) {
                ImageView pawnView = pawns[i][j];
                Pawn pawnModel = (Pawn) pawnView.getUserData();
                if(pawnModel.getTile() == current)
                    pawnsOnTile.add(pawnView);
            }
        }

        Vec2i boardSize = new Vec2i((int) board.getFitWidth(), (int) board.getFitHeight());
        Vec2i tileSize = new Vec2i(boardSize.x / TILES_N, boardSize.y / TILES_N);

        if(!(current instanceof StartingTile)) {
            List<ImageView> pawnsToRemove = pawnsOnTile.stream().filter((testPawn) -> {
                return ((Pawn) testPawn.getUserData()).getColor() != clickedPawnModel.getColor();
            }).toList();

            Board board = ((LudoApp)this.getApp()).getBoard();
            Player pawnOwner = null;
            for(ImageView pawn : pawnsToRemove) {
                Pawn pawnToRemove = (Pawn)pawn.getUserData();

                for(int playerID = 0; playerID < playersCount; ++playerID) {
                    if(board.getPlayer(playerID).getColor() == pawnToRemove.getColor()) {
                        pawnOwner = board.getPlayer(playerID);
                    }
                }
                LobbyTile lobbyTile = findTileForKilledPawn(pawnOwner);
                if(lobbyTile != null) {
                    Vec2i coords = getApp().getBoard().getTileCoords(boardSize, lobbyTile.getPos());
                    pawn.setLayoutX(coords.x);
                    pawn.setLayoutY(coords.y);
                    pawnToRemove.setTile(lobbyTile);
                }
            }

            pawnsOnTile.removeAll(pawnsToRemove);
        }

        int tilesCount = pawnsOnTile.size();
        Vec2i coords = getApp().getBoard().getTileCoords(boardSize, current.getPos());
        int tileGrid = (int) ceil(sqrt(pawnsOnTile.size()));
        Vec2i gridSize = new Vec2i(tileSize.x / tileGrid, tileSize.y / tileGrid);

        for(int r = 0; r < tileGrid; ++r) {
            for(int c = 0; ((r * tileGrid) + c) < tilesCount; ++c) {
                ImageView temp = pawnsOnTile.get(r * tileGrid + c);
                temp.setFitWidth(gridSize.x);
                temp.setFitHeight(gridSize.y);
                temp.setLayoutX(coords.x + c * gridSize.x);
                temp.setLayoutY(coords.y + r * gridSize.y);
            }
        }

        if (diceResult != REPEAT_ROLL_VALUE) {
            roundPlayer();
        }

        selectPawn.set(false);
        onPawnMove();
    }

    public void endGame() {
        LudoApp game = (LudoApp)(this.getApp());

        Stage stage = game.getStage();
        stage.setScene(game.getScene(MENU_FXML_FILE));
        stage.show();
    }

    private void showWinAlert() {
        if(winner == null)
            return;
      
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(GAME_OVER);
            alert.setHeaderText(GAME_OVER);

            alert.setContentText(winner.toString() + " wygral!");

            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    LudoApp game = (LudoApp)(this.getApp());

                    Stage stage = game.getStage();
                    stage.setScene(game.getScene(MENU_FXML_FILE));
                    stage.show();
                }
            });
        });
    }

    private void onPawnMove() {
        if(hasAnyoneWon())
            showWinAlert();
    }

    private boolean hasAnyoneWon() {
        Function<ImageView[], Boolean> allPawnsOnHome = (pawns) -> {
            for(int pawnID = 0; pawnID < PAWNS_PER_PLAYER; ++pawnID) {
                Pawn pawn = (Pawn) pawns[pawnID].getUserData();

                if(!(pawn.getTile() instanceof HomeTile))
                    return false;
            }
            
            return true;
        };

        for(int playerID = 0; playerID < playersCount; ++playerID) {
            if(allPawnsOnHome.apply(pawns[playerID])) {
                winner = Color.values()[playerID];
                return true;
            }
        }

        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindPawns();
        currentPlayer = 0;
        rollInSerie = 0;
        playersCount = 2;  //always changed in beforeRoute

        selectPawn.addListener((ignored, from, to) -> {
            Color playerColor = Color.values()[currentPlayer];
            String currentName = playerColor.toString();

            if(to) {
                status.setText(currentName + " wybiera pionka!");
            }
            else {
                status.setText(currentName + " losuje!");
                dice.setWaitingForRoll(true);
            }

            Paint bgPaint = Paint.valueOf(currentName.toLowerCase());
            status.setBackground(Background.fill(bgPaint));
            status.setTextFill((playerColor.getPaint()));
        });

        winner = null;
        dice.setWaitingForRoll(true);
    }
}
