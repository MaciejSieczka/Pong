import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;



public class MainScreen extends JPanel {

    public static void main(String[] args) {


        JFrame frame = new JFrame("PONG");

        AtomicInteger gameSpeed = new AtomicInteger(4);

        frame.setSize(600, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font myFont = null;
        try {
            myFont = Font.createFont(Font.TRUETYPE_FONT,
                    ClassLoader.getSystemResourceAsStream("Res/Fipps.otf")).deriveFont(90f);
            ge.registerFont(myFont);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 600, 720);
        panel.setLayout(null);

        JButton starting = new JButton("START");
        starting.setFont(new Font("Fipps", Font.PLAIN, 30));

        starting.setSize(200, 50);
        starting.setLocation(200, 400);

        JButton speed = new JButton("GAME SPEED " + (6-gameSpeed.get()));
        speed.setFont(new Font("Fipps", Font.PLAIN, 12));
        speed.setSize(200, 50);
        speed.setLocation(200, 500);

        speed.addActionListener(e -> {
            if (gameSpeed.get() <= 4) gameSpeed.incrementAndGet();
            else if (gameSpeed.get() == 5) gameSpeed.set(3);
            speed.setText("GAME SPEED " + (6-gameSpeed.get()));
        });

        starting.addActionListener(e -> {
            JFrame frame1 = new JFrame("PONG");
            Game g = new Game(gameSpeed.get());
            new Thread(g).start();
            frame1.setSize(1280, 720);
            frame1.setLocationRelativeTo(null);
            frame1.setResizable(false);
            frame1.add(g);
            frame1.setVisible(true);
            frame.setVisible(false);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });


        JLabel p1 = new JLabel();
        JLabel p2 = new JLabel();
        p1.setFont(new Font("Fipps", Font.PLAIN, 15));
        p2.setFont(new Font("Fipps", Font.PLAIN, 15));
        p1.setForeground(Color.WHITE);
        p1.setText("PLAYER 1:   UP: W     DOWN: S");
        p1.setBounds(130, 600, 370, 50);

        p2.setForeground(Color.WHITE);
        p2.setText("PLAYER 2:   UP: I      DOWN: K");
        p2.setBounds(130, 620, 370, 50);

        JLabel tekst = new JLabel();
        tekst.setForeground(Color.WHITE);

        tekst.setFont(myFont);
        tekst.setText("PONG");
        panel.add(starting);
        panel.add(speed);
        tekst.setBounds(120, 40, 500, 180);
        frame.add(panel);
        panel.add(tekst);
        panel.add(p1);
        panel.add(p2);


        frame.setVisible(true);


    }

}
