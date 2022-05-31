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

    public Tile getTileAfterSteps(int rollResult) {
        Tile targetTile = tile;

        if(tile instanceof LobbyTile) {
            if(rollResult == ENTRY_ROLL_VALUE)
                targetTile = targetTile.getNext();
            else
                return null;
        }
        else {
            for (int i = 0; i < rollResult; ++i) {
                Tile next = targetTile.getNext();
                Tile alt = targetTile.getAlt();

                if((alt instanceof HomeTile) && ((HomeTile)alt).getColor() == this.color) {
                    targetTile = alt;
                }
                else {
                    if (next == null)
                        return null;

                    targetTile = next;
                }
            }
        }

        return targetTile;
    }
}
