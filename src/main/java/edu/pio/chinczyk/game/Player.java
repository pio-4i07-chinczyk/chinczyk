package edu.pio.chinczyk.game;

import java.util.Arrays;
import static edu.pio.chinczyk.GameController.PAWNS_PER_PLAYER;

public class Player {
    private final LobbyTile[] lobbyTiles;
    private StartingTile startingTile;
    private final HomeTile[] homeTiles;
    private final Pawn[] pawns;
    private final Color color;

    public Player(Color color) {
        this.lobbyTiles = new LobbyTile[PAWNS_PER_PLAYER];
        this.startingTile = null;
        this.homeTiles = new HomeTile[PAWNS_PER_PLAYER];
        this.color = color;
        this.pawns = new Pawn[PAWNS_PER_PLAYER];
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

    private boolean ownsPawn(Pawn unknownPawn) {
        return Arrays.stream(pawns).anyMatch(
                pawn -> (pawn == unknownPawn)
        );
    }

    public Tile movePawnBy(Pawn pawn, int steps) {
        if(!ownsPawn(pawn)) {
            return null;
        }

        Tile destination = pawn.getTileAfterSteps(steps);

        if(destination instanceof HomeTile) {
            boolean homeTileOccupied = Arrays.stream(pawns).anyMatch(
                    otherPawn -> otherPawn.getTile() == destination
            );

            if(homeTileOccupied) {
                return null;
            }
        }

        return destination;
    }

    public boolean canMoveBy(int steps) {
        return Arrays.stream(pawns).anyMatch(
                pawn -> (movePawnBy(pawn, steps) != null)
        );
    }

    public boolean isOnlyInLobby() {
        return Arrays.stream(pawns).allMatch(
                pawn -> (pawn.getTile() instanceof LobbyTile)
        );
    }

    public boolean hasWon() {
        return Arrays.stream(pawns).allMatch(
                pawn -> (pawn.getTile() instanceof HomeTile)
        );
    }
}
