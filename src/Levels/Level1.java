package Levels;
import GameLoop.Screen;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Level1 extends Level {
    private TileSet tileset;
    private File file;
    private BufferedImage mapImage;
    private ArrayList<Image> mapTiles;
    private int scW;
    private int scH;
    private float factorX;
    private float factorY;

    private static int[][] level = {{2, 1, 1, 1, 1, 1, 1, 1, 2, 3}, {2, 1, 1, 1, 1, 2, 1, 0, 0, 0}, {2, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {2, 1, 1, 2, 2, 1, 1, 0, 0, 0},
            {2, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {2, 1, 1, 1, 2, 1, 1, 0, 0, 0}, {2, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {2, 1, 1, 2, 1, 1, 1, 0, 0, 0}, {2, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {2, 1, 2, 1, 1, 1, 1, 0, 0, 0}};

    public Level1(Screen screen) {
        scW = screen.getWidth();
        scH = screen.getHeight();
        factorX = 0;
        factorY = 0;

        tileset = this.load(level, screen);
        file = new File("C:\\Users\\Adam\\Desktop\\SomeGame\\assets\\testTileset.png");
        mapTiles = new ArrayList<Image>();

        try {
            mapImage = ImageIO.read(file);
        } catch (IOException e) {
            e.getMessage();
        }

        for (int i = 0; i * 70 < mapImage.getWidth(); i++) {
            mapTiles.add(mapImage.getSubimage(70*i,0,70,70).getScaledInstance(screen.getWidth() / 10, screen.getHeight() / 10, Image.SCALE_SMOOTH));
        }
    }

    @Override
    public synchronized Image getTileTexture(int row, int col) {
        int id = tileset.getTile(row, col).getId();

        switch (id) {
            case 1: return mapTiles.get(0);
            case 2: return mapTiles.get(1);
            case 3: return mapTiles.get(2);
            default: return null;
        }
    }

    public synchronized void mapUpdate(Screen screen) {
        int originX = screen.getWidth() / 10;
        int originY = screen.getHeight() / 10;
        factorX = (screen.getWidth() / (float)scW);
        factorY = (screen.getHeight() / (float)scH);

        for (int i = 0; i < tileset.getRows(); i++) {
            for (int j = 0; j < tileset.getCols(); j++) {
                tileset.getTile(i, j).setX((int)(originX * i));
                tileset.getTile(i, j).setY((int)(originY * j));
            }
        }

        mapTiles.clear();

        for (int i = 0; i * 70 < mapImage.getWidth(); i++) {
            mapTiles.add(mapImage.getSubimage(70 * i, 0, 70, 70).getScaledInstance(screen.getWidth() / 10, screen.getHeight() / 10, Image.SCALE_SMOOTH));
        }
    }
}
