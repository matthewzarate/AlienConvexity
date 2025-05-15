package main;

import object.ObjectKey;
import object.SuperObject;
import object.ObjectDoor;

public class PlacementManager {
    GamePanel gamePanel;
    public PlacementManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void placeObjects() {
        gamePanel.obj[0] = new ObjectKey();
        gamePanel.obj[0].worldX = 20 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 5 * gamePanel.tileSize;

        gamePanel.obj[1] = new ObjectKey();
        gamePanel.obj[1].worldX = 40 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 40 * gamePanel.tileSize;

        gamePanel.obj[2] = new ObjectKey();
        gamePanel.obj[2].worldX = 15 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 20 * gamePanel.tileSize;

        gamePanel.obj[3] = new ObjectDoor();
        gamePanel.obj[3].worldX = 15 * gamePanel.tileSize;
        gamePanel.obj[3].worldY = 24 * gamePanel.tileSize;

        gamePanel.obj[4] = new ObjectDoor();
        gamePanel.obj[4].worldX = 40 * gamePanel.tileSize;
        gamePanel.obj[4].worldY = 42 * gamePanel.tileSize;
        gamePanel.obj[4].collision = true;

    }
}
