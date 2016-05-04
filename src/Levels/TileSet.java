package Levels;

// One object acting as a container for all the tiles in a level
public class TileSet {
    private Tile[][] tiles;

    public TileSet(int row, int col) {
        tiles = new Tile[row][col];
    }


    public boolean addTile(Tile tile, int row, int col) {
        /*TODO
        Handle adding a tile to the 2d array
        Return true if successful
         */

        tiles[row][col] = tile;
        return false;
    }

    public boolean removeTile(int row, int col) {
        /*TODO
        Handle removing tile from 2d array, given the row and column position
        Return true if successful
         */
        return false;
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
