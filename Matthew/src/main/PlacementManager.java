package main;

import object.ObjectKey;
import object.SuperObject;

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
        gamePanel.obj[1].worldX = 20 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 30 * gamePanel.tileSize;
    }
}
