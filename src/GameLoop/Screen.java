package GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

// Still learning how to render with Swing.. This will be updated to handle rendering everything for the game
public class Screen {

    JFrame app;
    Canvas canvas;
    BufferStrategy buffer;
    Graphics graphics;
    Graphics2D g2d;

    public Screen(int WIDTH, int HEIGHT) {
        app = new JFrame("Some Game");
        app.setIgnoreRepaint(true); //Ignore OS messages to repaint
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setIgnoreRepaint(true); //Ignore OS messages to repaint
        canvas.setSize(WIDTH, HEIGHT);

        app.add(canvas);
        app.pack();
        app.setVisible(true);

        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();

        graphics = null;
        g2d = null;
    }

    public void render() {
            try {
                graphics = buffer.getDrawGraphics();
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, 639, 479);

                graphics.setColor(Color.BLUE);
                graphics.fillRect(300, 200, 100, 100);

                if (!buffer.contentsLost()) {
                    buffer.show();
                }
            } finally {
                if (graphics != null) {
                    graphics.dispose();
                }
            }
    }
}
