package GameLoop;
import Levels.Tile;
import Levels.TileSet;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelRenderer {

    private TileSet tileset;
    private Graphics2D g2d;

    private TileSet testSet;
    private Tile testTile;
    private Tile testTile2;

    public LevelRenderer() {
        testTile = new Tile(1, 0, 0);
        testTile2 = new Tile(1, 300, 0);
        tileset = new TileSet(1, 2);
        tileset.addTile(testTile, 1, 1);
        tileset.addTile(testTile2, 1, 2);
    }

    public LevelRenderer(TileSet tileset) {
        this.tileset = tileset;
    }

    public void generateView(Graphics2D g2d) {
        for (int i = 0; i < tileset.getRows(); i++) {
            for (int j = 0; j < tileset.getCols(); j++) {
                g2d.drawImage(getTileTexture(tileset, i, j), tileset.getTile(i, j).getX(), tileset.getTile(i, j).getY(), null);
            }
        }
    }

    private BufferedImage getTileTexture(TileSet tileSet, int row, int col) {
        File file;
        int id = tileSet.getTile(row, col).getId();
        String imgPath;

        switch (id) {
            case 1: imgPath = "C:\\Users\\Adam\\Desktop\\SomeGame\\assets\\testTile.png";
                break;
            default: imgPath = "";
                break;
        }

        try {
            file = new File(imgPath);
            BufferedImage img = ImageIO.read(file);
            return img;
        } catch (IOException e) {
            e.getMessage();
        }
        return null;
    }
}
