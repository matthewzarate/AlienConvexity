import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class GamePanel extends JPanel implements Runnable {
    //We GamePanel will have more functionality than JPanel alone
    final int originalTileSize = 16;
    //Traditional game screens had characters/tiles
    // that were 16x16 size. Therefore, scaling required
    final int scale = 3;
    //actual tileSize to be displayed on game screen
    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    int px = 150;
    int py = 150;
    int pSpeed = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        //JPanel uses double buffering function by default
        //Enhances rendering
        this.addKeyListener(keyH); //so KeyHandle can recognize game input
        this.setFocusable(true);
         }

    public void startGameThread() {
        gameThread = new Thread(this);
        //passing in GamePanel class into Thread constructor (This is how we start a Thread)
        gameThread.start();
    }

    //game loop
    @Override
    public void run() {
        while(gameThread != null) {
            System.out.println("Running!");
            update();
            repaint(); //repaint() calls paintComponent for us
        }
    }
    public void update() {
        if (keyH.up) {py = py - pSpeed;}
        if (keyH.down) {py = py + pSpeed;}
        if (keyH.left) {px = px - pSpeed;}
        if (keyH.right) {px = px + pSpeed;}
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //We change Graphics g to Graphics2D, more methods available to us
        g2d.setColor(Color.white);
        g2d.fillRect(px, py, tileSize, tileSize);
        g2d.dispose(); //Saves memory
    }
}
