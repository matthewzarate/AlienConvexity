package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel; //initializing this w/ new GamePanel() caused StackOverflow
    Tile[] tiles;
    int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNumber = new int[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];
        getTileImg();
        loadMap("/maps/firstMap.txt");
    }
    public void getTileImg() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            //for next tiles I'll use
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walls.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass3.png"));
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

            int tileNumber = mapTileNumber[col][row];

            g2d.drawImage(tiles[tileNumber].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
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

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {
                String line = br.readLine();
                 while (col < gamePanel.maxScreenColumn) {
                     String[] numbers = line.split(" ");
                     int num = Integer.parseInt(numbers[col]);
                     mapTileNumber[col][row] = num;
                     col++;
                 }
                 if (col == gamePanel.maxScreenColumn) {
                     col = 0;
                     row++;
                 }
            }
            br.close();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
