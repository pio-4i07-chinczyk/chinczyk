package edu.pio.chinczyk.game;

import javafx.geometry.Pos;

import java.util.ArrayList;

public class Board {
    private static final int PLAYERS_N = 4;
    public static final int TILES_N = 11;
    private final Pos2D size = new Pos2D(TILES_N, TILES_N);

    private final Player[] players;
    private final ArrayList<Tile> tiles;

    public static class Pos2D {
        public int x;
        public int y;

        public Pos2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos2D() {}
    }

    public Board() {
        this.tiles = new ArrayList<>();
        this.players = new Player[Board.PLAYERS_N];

        // order: blue, green, yellow, red
        Player player;
        Tile temp;

        // blue
        player = new Player();
        this.players[0] = player;
        // tiles
        addStartingTile(4, 10, player);
        addLobbyTiles(0,9, player);
        addHomeTiles(5, 6, true, player);

        // green
        player = new Player();
        this.players[1] = player;
        // tiles
        addStartingTile(6, 0, player);
        addLobbyTiles(9,0, player);
        addHomeTiles(5, 1, true, player);

        // yellow
        player = new Player();
        this.players[2] = player;
        // tiles
        addStartingTile(0, 4, player);
        addLobbyTiles(0,0, player);
        addHomeTiles(1, 5, false, player);

        // some first tiles
        temp = new Tile(4, 1);
        tiles.add(temp);
        temp = new Tile(4, 2, temp);
        tiles.add(temp);
        temp = new Tile(4, 3, temp);
        tiles.add(temp);
        temp = new Tile(4, 4, temp);
        tiles.add(temp);
        temp = new Tile(3, 4, temp);
        tiles.add(temp);
        temp = new Tile(2, 4, temp);
        tiles.add(temp);
        temp = new Tile(1, 4, temp);
        tiles.add(temp);
        player.getStartingTile().setNext(temp);
        // TODO: generate map

        // red
        player = new Player();
        this.players[3] = player;
        // tiles
        addStartingTile(10, 6, player);
        addLobbyTiles(9,9, player);
        addHomeTiles(6, 5, false, player);
    }

    private void addLobbyTiles(int x, int y, Player player) {
        LobbyTile[] playerLobbyTiles = player.getLobbyTiles();
        StartingTile startingTile = player.getStartingTile();

        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 2; ++j) {
                LobbyTile lobbyTile = new LobbyTile(x + j, y + i);
                tiles.add(lobbyTile);
                lobbyTile.setNext(startingTile);

                int index = (i * 2) + j;

                Pawn pawn = new Pawn();
                pawn.setTile(lobbyTile);

                player.setPawn(index, pawn);
                playerLobbyTiles[index] = lobbyTile;
            }
        }
    }

    private void addHomeTiles(int x, int y, boolean vertical, Player player) {
        if(vertical) {
            for(int i = 0; i < 4; ++i) {
                tiles.add(new HomeTile(x, y + i));
            }
        }
        else {
            for(int i = 0; i < 4; ++i) {
                tiles.add(new HomeTile(x + i, y));
            }
        }
    }

    private void addStartingTile(int x, int y, Player player) {
        StartingTile startingTile = new StartingTile(x, y);
        player.setStartingTile(startingTile);
        tiles.add(startingTile);
    }

    private Tile getTile(int x, int y) {
        for(Tile tile : tiles) {
            if(tile.getX() == x && tile.getY() == y)
                return tile;
        }

        return null;
    }

    public Player getPlayer(int id) {
        return this.players[id];
    }

    public Pos2D getTileCoords(Pos2D size, Pos2D pos) {
        return new Pos2D((size.x / TILES_N) * pos.x ,(size.y / TILES_N) * pos.y);
    }
}
