package GameLoop;
import Levels.TileSet;

import java.io.File;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelRenderer {

    private TileSet tileset;
    private File file;
    private File file2;
    private BufferedImage img;
    private BufferedImage img2;
    private Image tile1;
    private Image tile2;

    public LevelRenderer(TileSet tileset) {
        this.tileset = tileset;
        file = new File("C:\\Users\\Adam\\Desktop\\SomeGame\\assets\\testTile.png");
        file2 = new File("C:\\Users\\Adam\\Desktop\\SomeGame\\assets\\testTile1.png");

        try {
            img = ImageIO.read(file);
            tile1 = img.getScaledInstance(img.getWidth() / 4, (Integer)img.getHeight() / 4, Image.SCALE_SMOOTH);
            img2 = ImageIO.read(file2);
            tile2 = img2.getScaledInstance(img2.getWidth() / 4, (Integer) img2.getHeight() / 4, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void generateView(Graphics2D g2d) {
        for (int i = 0; i < tileset.getRows(); i++) {
            for (int j = 0; j < tileset.getCols(); j++) {
                g2d.drawImage(getTileTexture(tileset, i, j),tileset.getTile(i, j).getX(), tileset.getTile(i, j).getY(), null);
            }
        }
    }

    private Image getTileTexture(TileSet tileSet, int row, int col) {
        int id = tileSet.getTile(row, col).getId();

        switch (id) {
            case 1: return tile1;
            case 2: return tile2;
            default: return null;
        }
    }
}
