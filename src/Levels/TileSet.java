package Levels;

// One object acting as a container for all the tiles in a level
public class TileSet {
    private Tile[][] tiles;

    public TileSet(int row, int col) {
        tiles = new Tile[row][col];
    }


    public boolean addTile(Tile tile, int row, int col) {
        if (row >= getRows() || col >= getCols() || row < 0 || col < 0) {
            return false;
        } else {
            tiles[row][col] = tile;
            return true;
        }
    }

    public boolean removeTile(int row, int col) {
        if (row >= getRows() || col >= getCols() || row < 0 || col < 0) {
            return false;
        } else {
            tiles[row][col] = null;
            return true;
        }
    }

    public int getRows() {
        return tiles.length;
    }

    public int getCols() {
        return tiles[0].length;
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }
}
