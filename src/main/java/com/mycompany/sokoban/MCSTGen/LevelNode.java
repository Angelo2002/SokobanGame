package com.mycompany.sokoban.MCSTGen;

import com.mycompany.sokoban.customObjects.Level;
import com.mycompany.sokoban.customObjects.ScreenTile;
import com.mycompany.sokoban.customObjects.TileType;

import java.util.ArrayList;
import java.util.Random;

public class LevelNode {
    private Level level;
    private Level frozenLevel;

    private Level finalLevel;
    private LevelNode parent;
    private int visits;
    private int score;

    private int depth;

    private boolean isTerminal;
    ArrayList<LevelNode> children;

    boolean frozen;



    public LevelNode(Level level, LevelNode parent, int depth){
        this.level = level;
        this.parent = parent;
        this.visits = 0;
        this.score = 0;
        this.depth = depth;
        frozen = false;
        isTerminal = false;
    }

    public LevelNode(Level level, int depth){
        this.level = level;
        this.parent = null;
        this.visits = 0;
        this.score = 0;
        this.depth = depth;
        frozen = false;
        isTerminal = false;
    }


    public void ActionDeleteWalls(int quantity){
        ArrayList<ScreenTile> wallsToBeDeleted = this.level.getWallsAdyacentToEmpty();
        int wallsDeleted = 0;
        for(int i = 0; i < wallsToBeDeleted.size() && wallsDeleted<quantity; i++){
            System.out.println("Walls to be deleted: " + wallsToBeDeleted.size() + " Walls deleted: " + wallsDeleted + " Quantity: " + quantity + " i: " + i + "");
            int randomWall = (int) (Math.random() * wallsToBeDeleted.size());
            ScreenTile tile = wallsToBeDeleted.get(randomWall);
            System.out.println("GOT A WALL");
            tile.updateType(TileType.EMPTY);
            wallsToBeDeleted.remove(randomWall);
            wallsDeleted++;
        }
    }

    public void TestActionDeleteWalls(int quantity){
        ArrayList<ArrayList<ScreenTile>> map = this.level.getLevelMatrix();
        int wallRemovedCount = 0;
        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.get(i).size() && wallRemovedCount<quantity; j++){
                ScreenTile tile = map.get(i).get(j); //TODO: make random
                if(tile.getTileType() == TileType.WALL){
                    ScreenTile temp;
                    TileType uptile = (temp = level.getUpTile(tile)) != null ? temp.getTileType() : null;
                    TileType downtile = (temp = level.getDownTile(tile)) != null ? temp.getTileType() : null;
                    TileType lefttile = (temp = level.getLeftTile(tile)) != null ? temp.getTileType() : null;
                    TileType righttile =  (temp = level.getRightTile(tile)) != null ? temp.getTileType() : null;
                    if(i > 0 && (uptile == TileType.EMPTY || uptile == TileType.PLAYER)){
                        tile.updateType(TileType.EMPTY);
                    }
                    else if(downtile == TileType.EMPTY || downtile == TileType.PLAYER){
                        tile.updateType(TileType.EMPTY);
                    }
                    else if(righttile == TileType.EMPTY || righttile == TileType.PLAYER){
                        tile.updateType(TileType.EMPTY);
                    }
                    else if(lefttile == TileType.EMPTY || lefttile == TileType.PLAYER){
                        tile.updateType(TileType.EMPTY);
                    }
                    if(tile.getTileType() == TileType.EMPTY){
                        wallRemovedCount++;
                    }
                }
            }
        }
    }

    public void ActionPlaceBoxes(int quantity){
        ArrayList<ScreenTile> EmptyTiles = this.level.getEmptyTiles();
        int boxesPlaced = 0;
        for(int i = 0; i < EmptyTiles.size() && boxesPlaced<quantity; i++){
            int randomEmptyTile = (int) (Math.random() * EmptyTiles.size());
            ScreenTile tile = EmptyTiles.get(randomEmptyTile);
            tile.updateType(TileType.BOX);
            EmptyTiles.remove(randomEmptyTile);
            boxesPlaced++;
        }
    }

    public void ActionFreezeLevel(){
        this.frozen = true;
        this.frozenLevel = this.level.getClone();
    }

    /*
    Moves the player randomly in the level
     */
    public void ActionMovePlayer(int moves){
        for(int i=0;i<moves;i++){
            Random rand = new Random();
            int mov = rand.nextInt(4);
            switch (mov){
                case 0:
                    level.movePlayerUp();
                    break;
                case 1:
                    level.movePlayerDown();
                    break;
                case 2:
                    level.movePlayerLeft();
                    break;
                case 3:
                    level.movePlayerRight();
                    break;
            }
        }
    }

    public void ActionEvaluateLevel(){
        mergeWithFrozenLevel();
        isTerminal = true;
    }

    public float evaluateFunction(){
        return 0f;
    }

    public void turnLevelBoxesIntoGoals(){
        ArrayList<ArrayList<ScreenTile>> map = this.level.getLevelMatrix();
        for (ArrayList<ScreenTile> screenTiles : map) {
            for (ScreenTile screenTile : screenTiles) {
                if (screenTile.getTileType() == TileType.BOX) {
                    screenTile.updateType(TileType.GOAL);
                }
            }
        }
    }

    public void turnUnmovedGoalsIntoWalls(){
        ArrayList<ArrayList<ScreenTile>> map = this.level.getLevelMatrix();
        for (ArrayList<ScreenTile> screenTiles : map) {
            for (ScreenTile screenTile : screenTiles) {
                if (screenTile.getTileType() == TileType.GOAL && !screenTile.hasBeenMoved()) {
                    screenTile.updateType(TileType.WALL);
                }
            }
        }
    }



    public void mergeWithFrozenLevel(){
        turnLevelBoxesIntoGoals();
        turnUnmovedGoalsIntoWalls();
        ArrayList<ArrayList<ScreenTile>> map = this.level.getLevelMatrix();
        ArrayList<ArrayList<ScreenTile>> frozenMap = this.frozenLevel.getLevelMatrix();
        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.get(i).size(); j++){
                ScreenTile tile = map.get(i).get(j);
                ScreenTile frozenTile = frozenMap.get(i).get(j);
                if(tile.getTileType() == TileType.BOX && frozenTile.getTileType() == TileType.GOAL){
                    tile.updateType(TileType.BOXONGOAL);
                } else if (tile.getTileType() == TileType.EMPTY || tile.getTileType()== TileType.PLAYER){
                    tile.updateType(frozenTile.getTileType());
                }
            }
        }
        int playerX = (int)frozenLevel.getPlayer().getCoordinates().getKey();
        int playerY = (int)frozenLevel.getPlayer().getCoordinates().getValue();
        System.out.printf("Player X: %d, Player Y: %d\n", playerX, playerY);
        ScreenTile playerTile = map.get(playerY).get(playerX);
        if(playerTile.getTileType() == TileType.GOAL){
            playerTile.updateType(TileType.PLAYERONGOAL);
        }else{
            playerTile.updateType(TileType.PLAYER);
        }

        level.setPlayer(playerTile);
        //TODO: might be able to remove frozen level
    }

    public void rollout(){
        while(!frozen){
            Random rand = new Random();
            int action = rand.nextInt(3);
            switch (action){
                case 0:
                    TestActionDeleteWalls(1);
                    break;
                case 1:
                    ActionPlaceBoxes(1);
                    break;
                case 2:
                    ActionFreezeLevel();
                    break;
            }
        }
        while(!isTerminal){
            Random rand = new Random();
            int action = rand.nextInt(2);
            switch (action){
                case 0:
                    ActionMovePlayer(1);
                    break;
                case 1:
                    ActionEvaluateLevel();
                    break;
            }
        }
    }

    //TODO check if this is correct
    public LevelNode getClone(){
        LevelNode clone = new LevelNode(this.level.getClone(), this.depth);
        clone.parent = this.parent;
        clone.visits = this.visits;
        clone.score = this.score;
        clone.frozen = this.frozen;
        clone.isTerminal = this.isTerminal;
        clone.frozenLevel = this.frozenLevel.getClone();
        return clone;
    }

    boolean isLeaf(){
        return this.children.isEmpty();
    }


    public LevelNode getParent(){
        return this.parent;
    }

    public Level getLevel(){
        return this.level;
    }

    public int getVisits(){
        return this.visits;
    }

    public int getScore(){
        return this.score;
    }

    public int getDepth(){
        return this.depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }

    public void setFrozen(boolean frozen){
        this.frozen = frozen;
    }

    public boolean isFrozen(){
        return this.frozen;
    }


    public Level getFrozenLevel() {
        return frozenLevel;
    }
}
