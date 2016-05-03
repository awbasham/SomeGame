import GameLoop.Loop;

// Entry point into game
public class Game {
    private final int WIDTH;
    private final int HEIGHT;
    private Loop loop; // Main game loop

    public Game() {
        WIDTH = 640;
        HEIGHT = 480;
        loop = new Loop(WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start() {
        loop.run();
    }
}
