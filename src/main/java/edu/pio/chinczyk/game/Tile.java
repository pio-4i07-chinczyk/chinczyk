package edu.pio.chinczyk.game;

public class Tile {
    private final Vec2i pos;

    private Tile next;
    private Tile alt;

    public Tile(int x, int y) {
        this.pos = new Vec2i();
        this.pos.x = x;
        this.pos.y = y;

        this.next = null;
        this.alt = null;
    }

    public Tile(int x, int y, Tile next) {
        this.pos = new Vec2i();
        this.pos.x = x;
        this.pos.y = y;

        this.next = next;
        this.alt = null;
    }

    public int getX() {
        return this.pos.x;
    }

    public int getY() {
        return this.pos.y;
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

    public HomeTile tryHome() {
        if(alt instanceof HomeTile)
            return (HomeTile) alt;
        else
            return null;
    }

    public void setNext(Tile next) {
        this.next = next;
    }

    public void setAlt(Tile tile) {
        this.alt = tile;
    }
}
