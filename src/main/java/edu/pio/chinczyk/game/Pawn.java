package edu.pio.chinczyk.game;

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
}
