package GameObject;

import GameLoop.Camera;
import GameLoop.Controller;
import GameLoop.Screen;
import GameLoop.Updatable;
import Levels.Level;
import Levels.Level1;

import java.util.ArrayList;

/*
Context for current game state
    - Which level
    - Which camera
    - Etc.
 */
public class GameContext extends Game {
    private Screen screen;
    private Level level;
    private Camera camera;
    private Controller controller;
    private ArrayList<Updatable> updatables;

    public GameContext() {
        screen = new Screen(this, this.WIDTH, this.HEIGHT);
        updatables = new ArrayList<>();
        level = new Level1(this, screen);
    }

    public void update() {
        for (int i = 0; i < updatables.size(); i++) {
            updatables.get(i).update(this.loop.getInterpolation());
        }
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void addUpdatable(Updatable updatable) {
        updatables.add(updatable);
    }
}
