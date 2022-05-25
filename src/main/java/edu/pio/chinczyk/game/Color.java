package edu.pio.chinczyk.game;

public enum Color {
    BLUE(0, new Vec2i(4, 10), new Vec2i(0, 10), new Vec2i(5, 6), Direction.DOWN),
    GREEN(1, new Vec2i(6, 0), new Vec2i(10, 0), new Vec2i(5, 4), Direction.UP),
    YELLOW(2, new Vec2i(0, 4), new Vec2i(0, 0), new Vec2i(4, 5), Direction.LEFT),
    RED(3, new Vec2i(10, 6), new Vec2i(10, 10), new Vec2i(6, 5), Direction.RIGHT);

    private final int index;
    private final Vec2i startingPosition;
    private final Vec2i lobbyPosition;
    private final Vec2i homePosition;
    private final Direction homeDirection;

    Color(int index, Vec2i startingPosition, Vec2i lobbyPosition, Vec2i homePosition, Direction homeDirection) {
        this.index = index;
        this.startingPosition = startingPosition;
        this.lobbyPosition = lobbyPosition;
        this.homePosition = homePosition;
        this.homeDirection = homeDirection;
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