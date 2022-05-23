package edu.pio.chinczyk.game;

public class Pawn {
    private Tile tile;
    private final Player.Color color;

    Pawn(Player.Color color) {
        this.color = color;
    }

    public Tile getTile() {
        return tile;
    }
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Player.Color getColor() {
        return color;
    }
}
