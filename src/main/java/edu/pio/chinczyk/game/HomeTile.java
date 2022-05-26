package edu.pio.chinczyk.game;

public class HomeTile extends Tile {
    private Color color;

    public HomeTile(int x, int y) {
        super(x, y);
    }
    public HomeTile(int x, int y, Color color, Tile next) {
        super(x, y, next);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
