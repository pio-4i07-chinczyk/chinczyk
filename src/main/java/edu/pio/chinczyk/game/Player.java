package edu.pio.chinczyk.game;

public class Player {
    public static final int LOBBY_TILES_N = 4;
    public static final int HOME_TILES_N = 4;

    private final LobbyTile[] lobbyTiles;
    private StartingTile startingTile;
    private final HomeTile[] homeTiles;
    private final Pawn[] pawns;

    public Player() {
        this.lobbyTiles = new LobbyTile[LOBBY_TILES_N];
        this.startingTile = null;
        this.homeTiles = new HomeTile[HOME_TILES_N];

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

    public Pawn[] getPawns() {
        return pawns;
    }
}
