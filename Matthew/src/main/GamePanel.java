package main;
import characters.Player;

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
     public final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;
    int fps = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH); //passing in this GamePanel class & KeyH

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
        double drawInterval = (double) 1000000000 / fps; //16.66M nanosec,

        // We redraw the screen every 0.016sec
        double nextDrawTime = System.nanoTime() + drawInterval;
        //curr sys time in nanosec
        //When internal system time hits nextDrawTime, then draw screen again
        while(gameThread != null) {
            System.out.println("Running!");
            update();
            repaint(); //repaint() calls paintComponent for us

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                //subtract curr time from nextDrawTime
                // to get time left till we get next Draw Time
                remainingTime = remainingTime / 1000000; //ms units

                //In small case that update() and repaint() takes longer than the calculated drawInterval
                //Then, no time is left, therefore
                //Thread doesn't need to 'sleep'
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime); //sleep takes in ms units
                //Thread.sleep() may cause a few ms difference inaccuracy
                nextDrawTime = nextDrawTime + drawInterval;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //We change Graphics g to Graphics2D, more methods available to us
        player.draw(g2d);
        g2d.dispose(); //Saves memory
    }
}
