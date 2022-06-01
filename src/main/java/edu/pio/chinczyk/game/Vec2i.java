package edu.pio.chinczyk.game;

public class Vec2i implements Comparable<Vec2i> {
    public int x;
    public int y;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i() {}

    @Override
    public int compareTo(Vec2i vec2i) {
        int compareResult = Integer.compare(this.x, vec2i.x);

        if(compareResult != 0) {
            return compareResult;
        }

        return Integer.compare(this.y, vec2i.y);
    }
}