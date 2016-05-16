package Levels;

import GameLoop.Screen;
import GameLoop.Updatable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Cloud implements Updatable {
    private int INIT_X;
    private int x;
    private int y;
    private Image cloud;
    private int speedX;

    public Cloud(int x, int y) {
        this.INIT_X = x;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getCloud() {
        return cloud;
    }

    public void setCloud(File file, Screen screen) {
        try {
            cloud = ImageIO.read(file).getScaledInstance(screen.getWidth() / 5, screen.getHeight() / 5, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.getMessage();
        }

        speedX = screen.getWidth() / 200;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(getCloud(), getX(), getY(), null);
    }

    public void update(float interpolation) {
        int moveX = (int)(speedX * interpolation);
        this.setX(this.getX() - moveX);

        if (this.getX() <= 0 - getCloud().getWidth(null)) {
            this.setX(INIT_X);
        }
    }
}
