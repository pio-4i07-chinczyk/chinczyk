package edu.pio.chinczyk.game;

import java.util.TreeMap;

public class Board {
    private static final int PLAYERS_N = 4;
    private static final int TILES_N = 11;
    private static final int LONG_SIDE_N = 5;
    private final Vec2i size = new Vec2i(TILES_N, TILES_N);

    private final Player[] players;
    private final TreeMap<Vec2i, Tile> tiles;

    public Board() {
        this.tiles = new TreeMap<>();
        this.players = new Player[PLAYERS_N];

        addPlayer(Color.BLUE);
        addPlayer(Color.GREEN);
        addPlayer(Color.YELLOW);
        addPlayer(Color.RED);

        for (Player player : players) {
            addStartingTile(player);
            addLobbyTiles(player);
        }

        addTilesBetweenTwoStarts(Direction.LEFT, Direction.DOWN, Color.GREEN.getStartingPosition());
        addTilesBetweenTwoStarts(Direction.UP, Direction.LEFT, Color.RED.getStartingPosition());
        addTilesBetweenTwoStarts(Direction.RIGHT, Direction.UP, Color.BLUE.getStartingPosition());
        addTilesBetweenTwoStarts(Direction.DOWN, Direction.RIGHT, Color.YELLOW.getStartingPosition());

        for (Player player : players) {
            addHomeTiles(player);
        }
    }

    private void addPlayer(Color color) {
        Player player = new Player(color);
        this.players[color.getIndex()] = player;
    }

    private void addLobbyTiles(Player player) {
        LobbyTile[] playerLobbyTiles = player.getLobbyTiles();
        StartingTile startingTile = player.getStartingTile();

        int x = player.getColor().getLobbyPosition().x;
        int y = player.getColor().getLobbyPosition().y;

        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 2; ++j) {
                LobbyTile lobbyTile = new LobbyTile(x + j, y + i);
                tiles.put(lobbyTile.getPos(), lobbyTile);
                lobbyTile.setNext(startingTile);

                int index = (i * 2) + j;

                Pawn pawn = new Pawn(player.getColor());
                pawn.setTile(lobbyTile);

                player.setPawn(index, pawn);
                playerLobbyTiles[index] = lobbyTile;
            }
        }
    }

    private void addHomeTiles(Player player) {
        int x = player.getColor().getHomePosition().x;
        int y = player.getColor().getHomePosition().y;
        int directionX = player.getColor().getHomeDirection().getVec().x;
        int directionY = player.getColor().getHomeDirection().getVec().y;

        Tile tile = new HomeTile(x, y);     // last home tile
        tiles.put(player.getColor().getHomePosition(), tile);

        for(int tileNumber = 0; tileNumber < PLAYERS_N; ++tileNumber) {
            Tile temp = tile;
            tile = new HomeTile(x + tileNumber * directionX, y + tileNumber * directionY, temp);
            tiles.put(tile.getPos(), tile);
        }

        Tile entryTile = tiles.get(new Vec2i( x+ PLAYERS_N * directionX, y + PLAYERS_N * directionY));
        entryTile.setAlt(tile);
    }

    private void addStartingTile(Player player) {
        StartingTile startingTile = new StartingTile(player.getColor().getStartingPosition().x,
                player.getColor().getStartingPosition().y);
        player.setStartingTile(startingTile);
        tiles.put(startingTile.getPos(), startingTile);
    }

    private void addTilesBetweenTwoStarts(Direction firstDirection, Direction secondDirection, Vec2i startPosition) {
        Tile nextTile = tiles.get(startPosition);
        Vec2i currentPosition = new Vec2i(startPosition.x + firstDirection.getVec().x,
                startPosition.y + firstDirection.getVec().y);
        Tile currentTile = new Tile(currentPosition.x, currentPosition.y, nextTile);
        tiles.put(currentTile.getPos(), currentTile);
        nextTile = currentTile;
        currentPosition.x = currentPosition.x + firstDirection.getVec().x;
        currentPosition.y = currentPosition.y + firstDirection.getVec().y;
        currentTile = new Tile(currentPosition.x, currentPosition.y, nextTile);
        tiles.put(currentTile.getPos(), currentTile);

        for(int tileInRow = 0; tileInRow < LONG_SIDE_N - 1; ++tileInRow) {
            nextTile = currentTile;
            currentPosition.x = currentPosition.x + secondDirection.getVec().x;
            currentPosition.y = currentPosition.y + secondDirection.getVec().y;
            currentTile = new Tile(currentPosition.x, currentPosition.y, nextTile);
            tiles.put(currentTile.getPos(), currentTile);
        }

        for(int tileInRow = 0; tileInRow < LONG_SIDE_N - 2; ++tileInRow) {
            nextTile = currentTile;
            currentPosition.x = currentPosition.x + firstDirection.getVec().x;
            currentPosition.y = currentPosition.y + firstDirection.getVec().y;
            currentTile = new Tile(currentPosition.x, currentPosition.y, nextTile);
            tiles.put(currentTile.getPos(), currentTile);
        }

        nextTile = currentTile;
        currentPosition.x = currentPosition.x + firstDirection.getVec().x; // get to the position of another starting Tile
        currentPosition.y = currentPosition.y + firstDirection.getVec().y;
        Tile destinationStartTile = tiles.get(currentPosition);    // this Tile already exists
        destinationStartTile.setNext(nextTile);
    }

    private Tile getTile(int x, int y) {
        return tiles.get(new Vec2i(x, y));
    }

    public Player getPlayer(int id) {
        return this.players[id];
    }

    public Vec2i getTileCoords(Vec2i size, Vec2i pos) {
        return new Vec2i((size.x / TILES_N) * pos.x ,(size.y / TILES_N) * pos.y);
    }
}
