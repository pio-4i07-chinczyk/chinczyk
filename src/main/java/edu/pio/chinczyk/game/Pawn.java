package edu.pio.chinczyk.game;

import static edu.pio.chinczyk.GameController.*;

public class Pawn {
    private Tile tile;
    private final Color color;

    Pawn(Color color) {
        this.color = color;
    }

    public Tile getTile() {
        return tile;
    }
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Color getColor() {
        return color;
    }

    public Tile getTileAfterSteps(int steps) {
        if(tile instanceof LobbyTile) {
            if(steps == ENTRY_ROLL_VALUE) {
                return tile.getNext();
            }
            else {
                return null;
            }
        }

        Tile currentTile = tile;

        for (int i = 0; i < steps; ++i) {
            Tile next = currentTile.getNext();
            Tile alt = currentTile.getAlt();

            if((alt instanceof HomeTile) && ((HomeTile)alt).getColor() == this.color) {
                currentTile = alt;
            }
            else {
                if (next == null) {
                    return null;
                }

                currentTile = next;
            }
        }

        return currentTile;
    }

    public Vec2i getPos() {
        return tile.getPos();
    }
}
