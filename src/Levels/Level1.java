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

    private static int[][] level = {{2, 1, 1, 1, 1, 1, 1}, {2, 1, 1, 1, 1, 2, 1}, {2, 1, 1, 1, 1, 1, 1}, {2, 1, 1, 2, 2, 1, 1},
            {2, 1, 1, 1, 1, 1, 1}, {2, 1, 1, 1, 2, 1, 1}, {2, 1, 1, 1, 1, 1, 1}, {2, 1, 1, 2, 1, 1, 1}, {2, 1, 1, 1, 1, 1, 1}, {2, 1, 2, 1, 1, 1, 1}};

    public Level1(Screen screen) {
        scW = screen.getWidth();
        scH = screen.getHeight();

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
    public Image getTileTexture(int row, int col) {
        int id = tileset.getTile(row, col).getId();

        switch (id) {
            case 1: return mapTiles.get(0);
            case 2: return mapTiles.get(1);
            case 3: return mapTiles.get(2);
            default: return null;
        }
    }

    public void mapUpdate(Screen screen) {
        boolean done = false;
        int factorX = screen.getWidth() / scW;
        int factorY = screen.getHeight() / scH;
        scW = screen.getWidth();
        scH = screen.getHeight();

        for (int i = 0; i < tileset.getRows(); i++) {
            for (int j = 0; j < tileset.getCols(); j++) {
                int x = tileset.getTile(i, j).getX();
                int y = tileset.getTile(i, j).getY();
                tileset.getTile(i, j).setX(x * factorX);
                tileset.getTile(i, j).setY(y * factorY);
            }
        }

        for (int i = 0; i < mapTiles.size(); i++) {
            mapTiles.remove(i);
        }

        while (!done) {
            for (int i = 0; i * 70 < mapImage.getWidth(); i++) {
                mapTiles.add(mapImage.getSubimage(70 * i, 0, 70, 70).getScaledInstance(scW / 10, scH / 10, Image.SCALE_SMOOTH));
            }
            done = true;
        }
    }
}
