package GameLoop;

import Levels.Level;
import Levels.TileSet;

public class Camera {
    private Level level;
    private TileSet tileset;

    private int FOV_X;
    private int FOV_Y;

    public Camera(Level level, int x, int y) {
        this.level = level;
        this.tileset = level.getTileset();

        FOV_X = x;
        FOV_Y = y;
    }
}
