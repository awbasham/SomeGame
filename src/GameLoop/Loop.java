package GameLoop;

public class Loop {

    /* Update game data 25 fps, render with a max fps (interpolation prediction applied) */

    private final int TICKS_PER_SECOND;
    private final int SKIP_TICKS;
    private final int MAX_FRAMESKIP;
    private final double INIT_TIME;

    private boolean isRunning;
    private int loops;
    private float interpolation;
    private double nextGameTick;

    private int fps;
    private double FPSTimer1;
    private double FPSTimer2;

    private Screen screen;

    public Loop(int WIDTH, int HEIGHT) {
        TICKS_PER_SECOND = 25;
        SKIP_TICKS = 1000 / 25;
        MAX_FRAMESKIP = 5;
        INIT_TIME = System.currentTimeMillis();
        nextGameTick = getTickCount();
        isRunning = true;
        screen = new Screen(WIDTH, HEIGHT);
        fps = 0;
        FPSTimer2 = System.currentTimeMillis();
    }

    public void run() {
        while(isRunning) {

            loops = 0;
            while(getTickCount() > nextGameTick && loops < MAX_FRAMESKIP) { //adds 1 / 25 to game ticks, doesn't allow nextGameTick to exceed time elapsed (caps at 25)
                //update_game(); Update game data/logic here

                nextGameTick += SKIP_TICKS;
                loops++;
            }

            if (getFPS(fps) <= 60) { // Cap Rendering FPS to 60

                interpolation = ((float) (getTickCount() + SKIP_TICKS - nextGameTick) / (float) (SKIP_TICKS));
                //display_game(interpolation); Render game here
                screen.render();

                fps++;
            }
        }
    }

    public double getTickCount() {
        double timeElapsed = System.currentTimeMillis() - INIT_TIME;
        return timeElapsed;
    }

    public double getFPS(int fps) {
        FPSTimer1 = System.currentTimeMillis();

        if (FPSTimer1 - FPSTimer2 >= 1000) {
            FPSTimer2 = FPSTimer1;
            this.fps = 0;
        }

        //System.out.println(fps / ((FPSTimer1 - FPSTimer2) / 1000));
        return (fps / ((FPSTimer1 - FPSTimer2) / 1000));
    }
}
