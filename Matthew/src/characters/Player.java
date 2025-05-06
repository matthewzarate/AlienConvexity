package characters;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultVals();
        getPlayerImg();
    }

    public void setDefaultVals() {
        x = 100;
        y = 100;
        speed = 3;
        direction = "up";
    }

    public void getPlayerImg() {
        try {
            up1 = ImageIO.read(getClass().getResource("/sprites/u1.png"));
            up2 = ImageIO.read(getClass().getResource("/sprites/u2.png"));
            down1 = ImageIO.read(getClass().getResource("/sprites/d1.png"));
            down2 = ImageIO.read(getClass().getResource("/sprites/d2.png"));
            left1 = ImageIO.read(getClass().getResource("/sprites/l1.png"));
            left2 = ImageIO.read(getClass().getResource("/sprites/l2.png"));
            right1 = ImageIO.read(getClass().getResource("/sprites/r1.png"));
            right2 = ImageIO.read(getClass().getResource("/sprites/r2.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyHandler.up) {
            direction = "up";
            y = y - speed;
        }
        if (keyHandler.down) {
            direction = "down";
            y = y + speed;
        }
        if (keyHandler.left) {
            direction = "left";
            x = x - speed;
        }
        if (keyHandler.right) {
            direction = "right";
            x = x + speed;
        }
    }

    public void draw(Graphics g2d) {
        //g2d.setColor(Color.white);
        //g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;

        switch(direction) {
            case "up":
                image = up1;
                break;

            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;

            case "right":
                image = right1;
                break;
        }
        g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}

