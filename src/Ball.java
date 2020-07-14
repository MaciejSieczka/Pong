import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Ball {


    public int x;
    public int y;
    public int velX;
    public int velY;
    public int ballSize = 25;

    private int startingVel = 2;
    private int width = 1280;
    private int height = 720;

    private Random r = new Random();

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void siderand() {
        int side = r.nextInt(4);
        if (side == 0) {
            velX = startingVel;
            velY = -startingVel;
        } else if (side == 1) {
            velX = -startingVel;
            velY = startingVel;
        } else if (side == 2) {
            velX = startingVel;
            velY = startingVel;
        } else if (side == 3) {
            velX = -startingVel;
            velY = -startingVel;
        }
    }

    public void ballMovement() {
        x = x + velX;
        y = y + velY;

        if (x < 1) velX = startingVel;
        if (x > width - ballSize) velX = -startingVel;
        if (y < 1) velY = startingVel;
        if (y > height - 2 * ballSize - 10) velY = -startingVel;
    }

    public void ballReset() {
        siderand();
        x = width / 2 - ballSize / 2;
        y = height / 2 - ballSize / 2;
    }


    private Image getBallImage() {
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("Res/Ball1.png"));
        return i.getImage();
    }

    public void ballDrawing(Graphics2D g2d) {
        g2d.drawImage(getBallImage(), x, y, null);
    }

}
