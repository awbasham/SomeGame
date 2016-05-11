package GameLoop;

import Levels.Level1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;

// Still learning how to render with Swing.. This will be updated to handle rendering everything for the game
public class Screen implements ComponentListener {

    JFrame app;
    Canvas canvas;
    BufferStrategy buffer;
    Graphics2D g2d;
    Level1 level1;
    Color skyBlue;

    public Screen(int WIDTH, int HEIGHT) {
        app = new JFrame("Some Game");
        app.setIgnoreRepaint(true); // Ignore OS messages to repaint
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.addComponentListener(this);

        canvas = new Canvas();
        canvas.setIgnoreRepaint(true); // Ignore OS messages to repaint
        canvas.setSize(WIDTH, HEIGHT);

        app.add(canvas);
        app.pack();
        app.setVisible(true);

        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();

        g2d = null;
        level1 = new Level1(this);

        skyBlue = new Color(135, 206, 250);
    }

    public void render() {
        try {
            g2d = (Graphics2D) buffer.getDrawGraphics();
            g2d.setColor(skyBlue);
            g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            level1.draw(g2d);

            if (!buffer.contentsLost()) {
                buffer.show();
            }
        } finally {
            if (g2d != null) {
                g2d.dispose();
            }
        }
    }

    public int getWidth() {
        return canvas.getWidth();
    }

    public int getHeight() {
        return canvas.getHeight();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        //canvas.setSize(app.getContentPane().getWidth(), app.getContentPane().getHeight());
        if (level1 != null) {
            level1.mapUpdate(this);
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
