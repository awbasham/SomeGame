package GameLoop;

import Levels.TileSet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Still learning how to render with Swing.. This will be updated to handle rendering everything for the game
public class Screen {

    JFrame app;
    Canvas canvas;
    BufferStrategy buffer;
    Graphics graphics;
    Graphics2D g2d;
    LevelRenderer levelRenderer;

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
        levelRenderer = new LevelRenderer();
    }

    public void render() {
            try {
                g2d = (Graphics2D)buffer.getDrawGraphics();
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

    private BufferedImage getTileTexture() {
        File file;
        String imgPath = "/assets/testFile.png";

        try {
            file = new File(imgPath);
            BufferedImage img = ImageIO.read(file);
            return img;
        } catch (IOException e) {
            System.out.println("Failed to get file");
            e.getMessage();
        }
        return null;
    }
}
