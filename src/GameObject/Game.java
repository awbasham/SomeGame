package GameObject;

import GameLoop.Loop;

// Entry point into game
public class Game {
    public final int WIDTH;
    public final int HEIGHT;
    public Loop loop; // Main game loop

    public Game() {
        WIDTH = 640;
        HEIGHT = 480;
        loop = new Loop((GameContext) this);
    }

    public static void main(String[] args) {
        Game game = new GameContext();
        game.start();
    }

    public void start() {
        loop.run();
    }
}
