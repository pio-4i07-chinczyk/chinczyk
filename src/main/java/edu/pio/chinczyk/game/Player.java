package edu.pio.chinczyk.game;

public class Player {

    public enum Color {
        BLUE(0),
        GREEN(1),
        YELLOW(2),
        RED(3);

        private int index;

        Color(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    public static final int LOBBY_TILES_N = 4;
    public static final int HOME_TILES_N = 4;

    private final LobbyTile[] lobbyTiles;
    private StartingTile startingTile;
    private final HomeTile[] homeTiles;
    private final Pawn[] pawns;
    private Color color;

    public Player(Color color) {
        this.lobbyTiles = new LobbyTile[LOBBY_TILES_N];
        this.startingTile = null;
        this.homeTiles = new HomeTile[HOME_TILES_N];
        this.color = color;
        this.pawns = new Pawn[4];
    }

    public void setStartingTile(StartingTile startingTile) {
        this.startingTile = startingTile;
    }

    public StartingTile getStartingTile() {
        return startingTile;
    }

    public LobbyTile[] getLobbyTiles() {
        return lobbyTiles;
    }

    public HomeTile[] getHomeTiles() {
        return homeTiles;
    }

    public Pawn getPawn(int index) {
        return pawns[index];
    }

    public void setPawn(int index, Pawn pawn) {
        this.pawns[index] = pawn;
    }

    public Color getColor() {
        return color;
    }
}
