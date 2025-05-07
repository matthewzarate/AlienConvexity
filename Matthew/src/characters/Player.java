package characters;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;

        setDefaultVals();
        getPlayerImg();
    }

    public void setDefaultVals() {
        worldX = gamePanel.tileSize * 23; //player's starting point on WorldMap
        worldY = gamePanel.tileSize * 21;
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
        if (keyHandler.down || keyHandler.up || keyHandler.left || keyHandler.right) {
            avatarWalks();
        }
    }
    public void avatarWalks() {
        if (keyHandler.up) {
            direction = "up";
            worldY -= speed;
        }
        if (keyHandler.down) {
            direction = "down";
            worldY += speed;
        }
        if (keyHandler.left) {
            direction = "left";
            worldX -= speed;
        }
        if (keyHandler.right) {
            direction = "right";
            worldX += speed;
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            }
            else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }


    public void draw(Graphics g2d) {
        //g2d.setColor(Color.white);
        //g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;

            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;

            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;

            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}

