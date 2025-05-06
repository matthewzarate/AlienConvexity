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
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        //JPanel uses double buffering function by default
        //Enhances rendering
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        //passing in GamePanel class into Thread constructor (This is how we start a Thread)
        gameThread.start();
    }

    //Core of game
    @Override
    public void run() {

    }
}
