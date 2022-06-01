package edu.pio.chinczyk.game;

public class Tile {
    private final Vec2i pos;

    private Tile next;
    private Tile alt;

    public Tile(int x, int y) {
        this.pos = new Vec2i(x, y);
        this.next = null;
        this.alt = null;
    }

    public Tile(int x, int y, Tile next) {
        this(x, y);
        this.next = next;
    }

    public Vec2i getPos() {
        return this.pos;
    }

    public Tile getNext() {
        return this.next;
    }

    public Tile getAlt() {
        return alt;
    }

    public void setNext(Tile next) {
        this.next = next;
    }

    public void setAlt(Tile tile) {
        this.alt = tile;
    }
}
