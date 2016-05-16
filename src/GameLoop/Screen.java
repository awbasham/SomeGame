package GameLoop;

import GameObject.GameContext;
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
    Color skyBlue;
    GameContext ctx;

    public Screen(GameContext ctx, int WIDTH, int HEIGHT) {
        this.ctx = ctx;

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

        skyBlue = new Color(135, 206, 250);
    }

    public void render(float interpolation) {
        try {
            g2d = (Graphics2D) buffer.getDrawGraphics();
            g2d.setColor(skyBlue);
            g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            ctx.getLevel().draw(g2d, interpolation);

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
        if (ctx.getLevel() != null) {
            ctx.getLevel().mapUpdate(this);
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
