package GameLoop;
import Levels.TileSet;

import java.io.File;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelRenderer {

    private TileSet tileset;

    public LevelRenderer(TileSet tileset) {
        this.tileset = tileset;
    }

    public void generateView(Graphics2D g2d) {
        for (int i = 0; i < tileset.getRows(); i++) {
            for (int j = 0; j < tileset.getCols(); j++) {
                g2d.drawImage(getTileTexture(tileset, i, j),tileset.getTile(i, j).getX(), tileset.getTile(i, j).getY(), null);
            }
        }
    }

    private Image getTileTexture(TileSet tileSet, int row, int col) {
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
            Image tmpImg = img.getScaledInstance(img.getWidth() / 4, (Integer)img.getHeight() / 4, Image.SCALE_SMOOTH);
            return tmpImg;
        } catch (IOException e) {
            e.getMessage();
        }
        return null;
    }
}
