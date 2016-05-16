package Levels;

import GameLoop.Screen;
import GameLoop.Updatable;
import GameObject.GameContext;

import java.io.File;
import java.awt.*;

public class Level1 extends Level {
    private Cloud cloud;
    private static int[][] level = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 2, 2, 3, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 2, 2, 2, 2, 2, 2, 2, 2, 3}};

    public Level1(GameContext ctx, Screen screen) {
        this.load(level, screen);
        File file = new File("C:\\Users\\Adam\\Desktop\\SomeGame\\assets\\testTileset.png");
        File cloudFile = new File("C:\\Users\\Adam\\Desktop\\SomeGame\\assets\\cloud.png");
        cloud = new Cloud(screen.getWidth(), screen.getHeight()/ 2);
        cloud.setCloud(cloudFile, screen);
        cloud.setY(screen.getHeight() / 2 - cloud.getCloud().getHeight(null) / 2);
        ctx.addUpdatable(cloud);

        this.setMapImage(file);
        this.generateMapImages(this.getMapImage(), screen, 70, 70);
    }

    @Override
    public synchronized Image getTileTexture(int row, int col) {
        int id = this.getTileset().getTile(row, col).getId();

        switch (id) {
            case 1: return getMapTiles().get(0);
            case 2: return getMapTiles().get(1);
            case 3: return getMapTiles().get(2);
            default: return null;
        }
    }

    @Override
    public void draw(Graphics2D g2d, float interpolation) {
        super.draw(g2d, interpolation);
        cloud.draw(g2d);
    }
}
