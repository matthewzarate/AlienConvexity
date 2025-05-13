package main;

import characters.Entity;

public class CollisionManager {
    GamePanel gamePanel;
    public CollisionManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void checkTile(Entity entity) {
        //These attributes all belong to the player, the Avatar
        int leftWorldX = entity.worldX + entity.boundaryArea.x;
        int rightWorldX = entity.worldX + entity.boundaryArea.x + entity.boundaryArea.width;

        int upperWorldY = entity.worldY + entity.boundaryArea.y;
        int bottomWorldY = entity.worldY + entity.boundaryArea.y + entity.boundaryArea.height;

        //Avatar's left side and right side
        int leftColumn = leftWorldX/gamePanel.tileSize;
        int rightColumn = rightWorldX/gamePanel.tileSize;

        int avatarUpperRow = upperWorldY/gamePanel.tileSize;
        int avatarBottomRow = bottomWorldY/gamePanel.tileSize;

        //We need only check two tiles in each direction
        int tileNumber1;
        int tileNumber2;

        switch(entity.direction) {
            case "up":
                avatarUpperRow = (upperWorldY - entity.speed) / gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[leftColumn][avatarUpperRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[rightColumn][avatarUpperRow];

                //if either tiles is a solid,  avatar cannot/shouldn't move in given direction
                if (gamePanel.tileManager.tiles[tileNumber1].collide
                    || gamePanel.tileManager.tiles[tileNumber2].collide) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                avatarBottomRow = (bottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[leftColumn][avatarBottomRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[rightColumn][avatarBottomRow];

                //if either tiles is a solid,  avatar cannot/shouldn't move in given direction
                if (gamePanel.tileManager.tiles[tileNumber1].collide
                        || gamePanel.tileManager.tiles[tileNumber2].collide) {
                    entity.collisionOn =  true;
                }
                break;
            case "left":
                leftColumn = (leftWorldX - entity.speed) / gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[leftColumn][avatarUpperRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[leftColumn][avatarBottomRow];

                //if either tiles is a solid,  avatar cannot/shouldn't move in given direction
                if (gamePanel.tileManager.tiles[tileNumber1].collide
                        || gamePanel.tileManager.tiles[tileNumber2].collide) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                rightColumn = (rightWorldX + entity.speed) / gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[rightColumn][avatarUpperRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[rightColumn][avatarBottomRow];

                //if either tiles is a solid,  avatar cannot/shouldn't move in given direction
                if (gamePanel.tileManager.tiles[tileNumber1].collide
                        || gamePanel.tileManager.tiles[tileNumber2].collide) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
