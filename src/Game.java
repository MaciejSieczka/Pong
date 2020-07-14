import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game extends JPanel implements KeyListener, Runnable {

    private Paddle p1;
    private Paddle p2;
    private Ball b;
    private boolean running;
    private boolean ending;
    private boolean starting = true;
    private int t;
    private int finalScore = 1;
    private final int userRenderTime;


    private Thread thread;

    Game(int t) {
        this.userRenderTime = t;
        this.t = userRenderTime;

        setFocusable(true);
        p1 = new Paddle(50, 230);
        p2 = new Paddle(1280 - 90, 230);
        b = new Ball(1280 / 2 - 12, 620 / 2 - 12);
        b.siderand();

        addKeyListener(p1);
        addKeyListener(p2);
        addKeyListener(this);
        setBackground(Color.BLACK);
    }


    private void ballPaddleCollision() {
        if (b.x >= p1.x + b.ballSize - 5 && b.x <= p1.x + b.ballSize && b.y + b.ballSize >= p1.y && b.y < p1.y + p1.paddleHeight && b.velX < 0) {
            b.velX = -b.velX;
            if (b.velX <= 4) b.velX++;
        }
        if (p2.x >= b.x + b.ballSize - 5 && p2.x <= b.x + b.ballSize && b.y + b.ballSize >= p2.y && b.y < p2.y + p2.paddleHeight && b.velX > 0) {
            b.velX = -b.velX;
            if (b.velX >= -4) b.velX--;
        }
    }


    private void points() {
        if (b.x <= 5 && b.velX < 0) {
            p2.score++;
            b.ballReset();
        }
        if (b.x >= 1240 && b.velX > 0) {
            p1.score++;
            b.ballReset();
        }
    }

    private void gameFinish() {
        if (p1.score == finalScore || p2.score == finalScore) {
            ending = true;
            running = false;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;


        p1.drawing(g2d);
        p2.drawing(g2d);
        b.ballDrawing(g2d);


        g2d.setColor(Color.WHITE);

        g2d.setFont(new Font("Fipps", Font.PLAIN, 50));
        g2d.drawString(String.valueOf(p1.score), 400, 100);
        g2d.drawString(String.valueOf(p2.score), 800, 100);

        if (starting && !running) {
            g2d.setFont(new Font("Fipps", Font.PLAIN, 18));
            g2d.setColor(Color.WHITE);
            g2d.drawString("PRESS SPACE TO BEGIN", 440, 190);
        }


        if (ending && p1.score == finalScore) {
            g2d.setFont(new Font("Fipps", Font.PLAIN, 18));
            g2d.setColor(Color.WHITE);
            g2d.drawString("PLAYER 1 WON", 320, 190);
            g2d.drawString("REMATCH? PRESS: T", 320, 300);
            g2d.drawString("END? PRESS: N", 320, 400);
        }
        if (ending && p2.score == finalScore) {

            g2d.setFont(new Font("Fipps", Font.PLAIN, 18));
            g2d.setColor(Color.WHITE);
            g2d.drawString("PLAYER 2 WON", 750, 190);
            g2d.drawString("REMATCH? PRESS: T", 750, 300);
            g2d.drawString("END? PRESS: N", 750, 400);
        }
        for (int i = 0; i < 720; i += 50) {
            g2d.fillRect(1280 / 2 - 10, i, 10, 10);
        }


    }


    @Override
    public void run() {
        thread = Thread.currentThread();
        while (true) {
            try {
                if (running) {
                    p1.movement1();
                    p2.movement2();

                    ballPaddleCollision();
                    points();
                    b.ballMovement();
                    gameFinish();
                }
                SwingUtilities.invokeLater(() -> repaint());
                Thread.sleep(t);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            if (!ending) {
                starting = false;
                ending = false;
                running = true;
            }
        } else if (key == KeyEvent.VK_T && ending) {
            ending = false;
            starting = true;
            t = userRenderTime;
            p1.score = 0;
            p2.score = 0;
            p1.y = 230;
            p2.y = 230;
            thread.interrupt();
        } else if (key == KeyEvent.VK_N && ending) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
