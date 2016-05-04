package Levels;

public class Level1 {
    TileSet tileSet;
    int[][] level = {{1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}};

    public Level1() {
    }

    public TileSet load() {
        tileSet = new TileSet(level.length, level[0].length);
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++) {
                tileSet.addTile(new Tile(level[i][j], i * 64, j * 64), i, j);
            }
        }

        return tileSet;
    }
}
