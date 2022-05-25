package edu.pio.chinczyk.game;

public class HomeTile extends Tile {
    public HomeTile(int x, int y) {
        super(x, y);
    }
    public HomeTile(int x, int y, Tile next) {
        super(x, y, next);
    }
}
