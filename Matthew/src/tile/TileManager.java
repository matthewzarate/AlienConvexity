package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TileManager {
    GamePanel gamePanel; //initializing this w/ new GamePanel() caused StackOverflow
    Tile[] tiles;
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        getTileImg();
    }
    public void getTileImg() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            //for next tiles I'll use
            //tiles[0] = new Tile;
            //tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d) {
        //Drawing a tile for testing purposes...
        g2d.drawImage(tiles[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
