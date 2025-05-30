package characters;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX,worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle boundaryArea; //Creates invisible rectangle with x,y,width,height
    public int boundaryAreaDefaultX, boundaryAreaDefaultY;
    public boolean collisionOn = false;

    public Entity() {}
}
