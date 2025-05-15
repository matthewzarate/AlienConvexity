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
    //Checks if Player is hitting an object, if yes, we return the index of the object
    //in order to process the reaction
    public int checkObject(Entity entity, boolean player) {
        int index = 999;
       // for (int i = 0; i < gamePanel.obj.length; i++) {
            for (int i = 0; i < 5; i++) {
            if (gamePanel.obj[i] != null) {
                //get Entity's boundary Area coordinates
                entity.boundaryArea.x = entity.worldX + entity.boundaryArea.x;
                entity.boundaryArea.y = entity.worldY + entity.boundaryArea.y;

                //Get the object's boundary Area coordinates
                gamePanel.obj[i].boundaryArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].boundaryArea.x;
                gamePanel.obj[i].boundaryArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].boundaryArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.boundaryArea.y -= entity.speed;
                        if (entity.boundaryArea.intersects(gamePanel.obj[i].boundaryArea)) {
                            System.out.println("Up collision");
                        }
                        break;
                    case "down":
                        entity.boundaryArea.y += entity.speed;
                        if (entity.boundaryArea.intersects(gamePanel.obj[i].boundaryArea)) {
                            System.out.println("Down collision");
                        }
                        break;
                    case "left":
                        entity.boundaryArea.x -= entity.speed;
                        if (entity.boundaryArea.intersects(gamePanel.obj[i].boundaryArea)) {
                            System.out.println("Left collision");
                        }
                        break;
                    case "right":
                        entity.boundaryArea.x += entity.speed;
                        if (entity.boundaryArea.intersects(gamePanel.obj[i].boundaryArea)) {
                            System.out.println("Right collision");
                        }
                        break;
                }
            }
            entity.boundaryArea.x = entity.boundaryAreaDefaultX;
            entity.boundaryArea.y = entity.boundaryAreaDefaultY;
            gamePanel.obj[i].boundaryArea.x = gamePanel.obj[i].boundaryAreaDefaultX;
            gamePanel.obj[i].boundaryArea.y = gamePanel.obj[i].boundaryAreaDefaultY;

        }
        return index;
    }
}
