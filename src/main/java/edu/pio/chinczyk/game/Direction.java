package edu.pio.chinczyk.game;

public enum Direction {
    UP(new Vec2i(0, -1)),
    DOWN(new Vec2i(0, 1)),
    LEFT(new Vec2i(-1, 0)),
    RIGHT(new Vec2i(1, 0));

    private final Vec2i vec;

    Direction(Vec2i vec) {
        this.vec = vec;
    }

    public Vec2i getVec() {
        return vec;
    }
}