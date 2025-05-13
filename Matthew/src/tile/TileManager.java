package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel; //initializing this w/ new GamePanel() caused StackOverflow
    public Tile[] tiles;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNumber = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
        getTileImg();
        loadMap("/maps/worldMap.txt");
    }
    public void getTileImg() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            //for next tiles I'll use
            //Let walls tile be a solid, collision = true
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walls.png"));
            tiles[1].collide = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass3.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bush.png"));
            tiles[6].collide = true;

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tiles[7].collide = true;
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d) {
        //Drawing a tile for testing purposes...
        //Automating tile development process
        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < gamePanel.maxWorldColumn && worldRow < gamePanel.maxWorldRow) {

            int tileNumber = mapTileNumber[worldColumn][worldRow];

            int worldX = worldColumn * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
            //finding tile's screen position
            //moves playerWorldX (left and up)
            // add playerScreenX & Y (offset difference & get correct coordinates for screenXY

            //draws tile as long as its within the boundary of the Screen!

            if (worldX + gamePanel.tileSize> gamePanel.player.worldX - gamePanel.player.screenX
                    && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                    && worldY + gamePanel.tileSize> gamePanel.player.worldY - gamePanel.player.screenY
                    && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                g2d.drawImage(tiles[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            worldColumn++;
            if (worldColumn == gamePanel.maxWorldColumn) {
                worldColumn = 0;
                worldRow++;
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

            while (col < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow) {
                String line = br.readLine();
                 while (col < gamePanel.maxWorldColumn) {
                     String[] numbers = line.split(" ");
                     int num = Integer.parseInt(numbers[col]);
                     mapTileNumber[col][row] = num;
                     col++;
                 }
                 if (col == gamePanel.maxWorldColumn) {
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
