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
        //Automating tile development process
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {
            g2d.drawImage(tiles[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x = x + gamePanel.tileSize;
            if (col == gamePanel.maxScreenColumn) {
                col = 0;
                x = 0;
                row++;
                y = y + gamePanel.tileSize;
            }
        }
    }
}
