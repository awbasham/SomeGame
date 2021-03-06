package Levels;
import GameLoop.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Level {
    private TileSet tileset;
    private BufferedImage mapImage;
    private ArrayList<Image> mapTiles;

    // Convert int array to TileSet
    public void load(int[][] level, Screen screen) {
        tileset = new TileSet(level.length, level[0].length);
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++) {
                tileset.addTile(new Tile(level[i][j], j * screen.getWidth() / 10, i * screen.getHeight() / 10), i, j);
            }
        }
    }

    // Draws the tiles in the level TileSet
    public void draw(Graphics2D g2d, float interpolation) {
        for (int i = 0; i < tileset.getRows(); i++) {
            for (int j = 0; j < tileset.getCols(); j++) {
                g2d.drawImage(getTileTexture(i, j), tileset.getTile(i, j).getX(), tileset.getTile(i, j).getY(), null);
            }
        }
    }

    //OVERRIDE on level creation class i.e. Level1 (to choose which tile id gives which image)
    public Image getTileTexture(int row, int col) {
        int id = tileset.getTile(row, col).getId();

        switch (id) {
            default: return null;
        }
    }

    //Adds resized map tiles to an ArrayList to be accessed later
    public void generateMapImages(BufferedImage mapImage, Screen screen, int tileWidth, int tileHeight) {
        mapTiles = new ArrayList<Image>();
        for (int i = 0; i * tileHeight < mapImage.getHeight(); i++) {
            for (int j = 0; j * tileWidth < mapImage.getWidth(); j++) {
                mapTiles.add(mapImage.getSubimage(tileWidth * j, tileHeight * i, tileWidth, tileHeight).getScaledInstance(screen.getWidth() / 10, screen.getHeight() / 10, Image.SCALE_SMOOTH));
            }
        }
    }

    //Resets each Tile X and Y origin based on new screen size
    public synchronized void mapUpdate(Screen screen) {
        int originX = screen.getWidth() / 10;
        int originY = screen.getHeight() / 10;

        for (int i = 0; i < tileset.getRows(); i++) {
            for (int j = 0; j < tileset.getCols(); j++) {
                tileset.getTile(i, j).setX((originX * j));
                tileset.getTile(i, j).setY((originY * i));
            }
        }

        //Clears sized tile images and re-adds with the new correct size
        mapTiles.clear();
        generateMapImages(mapImage, screen, 70, 70);
    }

    //Method to set the tile sheet used for pulling all sub-images off of
    public void setMapImage(File file) {
        try {
            mapImage = ImageIO.read(file);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public ArrayList<Image> getMapTiles() {
        return mapTiles;
    }

    public BufferedImage getMapImage() {
        return mapImage;
    }

    public TileSet getTileset() {
        return tileset;
    }
}
