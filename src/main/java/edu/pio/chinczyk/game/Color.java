package edu.pio.chinczyk.game;

import javafx.scene.paint.Paint;

import static edu.pio.chinczyk.GameController.HTML_BLACK;
import static edu.pio.chinczyk.GameController.HTML_WHITE;

public enum Color {
    BLUE(0, new Vec2i(4, 10), new Vec2i(0, 9), new Vec2i(5, 6), Direction.DOWN, Paint.valueOf(HTML_WHITE)),
    GREEN(1, new Vec2i(6, 0), new Vec2i(9, 0), new Vec2i(5, 4), Direction.UP, Paint.valueOf(HTML_WHITE)),
    YELLOW(2, new Vec2i(0, 4), new Vec2i(0, 0), new Vec2i(4, 5), Direction.LEFT, Paint.valueOf(HTML_BLACK)),
    RED(3, new Vec2i(10, 6), new Vec2i(9, 9), new Vec2i(6, 5), Direction.RIGHT, Paint.valueOf(HTML_WHITE));

    private final Paint paint;
    private final int index;
    private final Vec2i startingPosition;
    private final Vec2i lobbyPosition;
    private final Vec2i homePosition;
    private final Direction homeDirection;

    Color(int index, Vec2i startingPosition, Vec2i lobbyPosition, Vec2i homePosition, Direction homeDirection, Paint paint) {
        this.index = index;
        this.startingPosition = startingPosition;
        this.lobbyPosition = lobbyPosition;
        this.homePosition = homePosition;
        this.homeDirection = homeDirection;
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }

    public int getIndex() {
        return index;
    }

    public Vec2i getStartingPosition() {
        return startingPosition;
    }

    public Vec2i getLobbyPosition() {
        return lobbyPosition;
    }

    public Direction getHomeDirection() {
        return homeDirection;
    }

    public Vec2i getHomePosition() {
        return homePosition;
    }
}