package GameLoop;

import Levels.Level1;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

// Still learning how to render with Swing.. This will be updated to handle rendering everything for the game
public class Screen {

    JFrame app;
    Canvas canvas;
    BufferStrategy buffer;
    Graphics2D g2d;
    LevelRenderer levelRenderer;
    Level1 level1;

    public Screen(int WIDTH, int HEIGHT) {
        app = new JFrame("Some Game");
        app.setIgnoreRepaint(true); // Ignore OS messages to repaint
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setIgnoreRepaint(true); // Ignore OS messages to repaint
        canvas.setSize(WIDTH, HEIGHT);

        app.add(canvas);
        app.pack();
        app.setVisible(true);

        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();

        g2d = null;
        level1 = new Level1();
        levelRenderer = new LevelRenderer(level1.load());
    }

    public void render() {
        try {
            g2d = (Graphics2D) buffer.getDrawGraphics();
            g2d.setColor(Color.gray);
            g2d.fillRect(0, 0, 639, 479);

            levelRenderer.generateView(g2d);

            if (!buffer.contentsLost()) {
                buffer.show();
            }
        } finally {
            if (g2d != null) {
                g2d.dispose();
            }
        }
    }
}
