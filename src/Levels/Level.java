package Levels;
import GameLoop.Screen;

import java.awt.*;

public class Level {
    private TileSet tileset;

    // Convert int array to TileSet
    public TileSet load(int[][] level, Screen screen) {
        tileset = new TileSet(level.length, level[0].length);
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++) {
                tileset.addTile(new Tile(level[i][j], i * screen.getWidth() / 10, j * screen.getHeight() / 10), i, j);
            }
        }
        return tileset;
    }

    public void draw(Graphics2D g2d) {
        for (int i = 0; i < tileset.getRows(); i++) {
            for (int j = 0; j < tileset.getCols(); j++) {
                g2d.drawImage(getTileTexture(i, j), tileset.getTile(i, j).getX(), tileset.getTile(i, j).getY(), null);
            }
        }
    }

    //OVERRIDE on level creation
    public Image getTileTexture(int row, int col) {
        int id = tileset.getTile(row, col).getId();

        switch (id) {
            default: return null;
        }
    }
}
