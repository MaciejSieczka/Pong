import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Paddle extends KeyAdapter {


    public int x;
    public int y;
    public int score = 0;
    public int paddleHeight = 150;
    private int velY = 0;
    private int velY1 = 0;
    private int maxVel = 3;
    private int height = 720;


    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void drawing(Graphics2D g2d) {
        g2d.drawImage(getImage(), x, y, null);
    }

    public void movement1() {
        y = y + velY;
        if (y < 10) y = 10;
        if (y > height - paddleHeight - 50) y = height - paddleHeight - 50;
    }

    public void movement2() {
        y = y + velY1;
        if (y < 10) y = 10;
        if (y > height - paddleHeight - 50) y = height - paddleHeight - 50;
    }


    private Image getImage() {
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("Res/Paddle1.png"));
        return i.getImage();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) velY = -maxVel;
        else if (key == KeyEvent.VK_S) velY = maxVel;
        else if (key == KeyEvent.VK_I) velY1 = -maxVel;
        else if (key == KeyEvent.VK_K) velY1 = maxVel;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) velY = 0;
        else if (key == KeyEvent.VK_S) velY = 0;
        else if (key == KeyEvent.VK_I) velY1 = 0;
        else if (key == KeyEvent.VK_K) velY1 = 0;
    }


}
