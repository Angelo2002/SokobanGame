package com.mycompany.sokoban.customObjects;

import com.mycompany.sokoban.MCSTGen.EvaluationParamethers;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Level {
    static final int tileSize = 50;
    ArrayList<ArrayList<ScreenTile>> levelMatrix;

    ScreenTile player;

    private float levelScore;
    public Level(){
        levelMatrix = new ArrayList<>();
        this.player = null;
        levelScore = 0;
    }

    public Level(int size, int playerx, int playery){
        levelMatrix = new ArrayList<>();
        int count = 0;
        for(int i=0;i<size;i++){
            ArrayList<ScreenTile> row = new ArrayList<>();
            for(int j=0;j<size;j++){
                ScreenTile tile =new ScreenTile(j,i,TileType.WALL,count);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                row.add(tile);
                count++;
            }
            levelMatrix.add(row);
        }

        //TODO arreglar este código horrendo
        //coordenadas (y,x) por como se manejan las matrices
        ScreenTile player = this.levelMatrix.get(playery).get(playerx);
        player.updateType(TileType.PLAYER);
        this.player = player;

        levelScore = 0;
    }

    public ArrayList<ArrayList<ScreenTile>> getLevelMatrix(){
        return this.levelMatrix;
    }


    /*
    Gets boxes in the level including those on goals
     */
    public ArrayList<ScreenTile> getBoxes(){
        ArrayList<ScreenTile> boxes = new ArrayList<>();
        for(ArrayList<ScreenTile> row: levelMatrix){
            for(ScreenTile tile:row){
                if(tile.getTileType()==TileType.BOX || tile.getTileType()==TileType.BOXONGOAL){
                    boxes.add(tile);
                }
            }
        }
        return boxes;
    }

    public ArrayList<ScreenTile>getEmptyTiles(){
        ArrayList<ScreenTile> emptyTiles = new ArrayList<>();
        for(ArrayList<ScreenTile> row: levelMatrix){
            for(ScreenTile tile:row){
                if(tile.getTileType()==TileType.EMPTY){
                    emptyTiles.add(tile);
                }
            }
        }
        return emptyTiles;
    }

    public ArrayList<ScreenTile> getWallsAdjacentToEmpty(){
        ArrayList<ScreenTile> walls = new ArrayList<>();
        for(ArrayList<ScreenTile> row: levelMatrix){
            for(ScreenTile tile:row){
                if(tile.getTileType()==TileType.WALL){
                    if(getUpTile(tile)!=null && (getUpTile(tile).getTileType()==TileType.EMPTY || getUpTile(tile).getTileType()==TileType.PLAYER)){
                        walls.add(tile);
                    }

                    else if(getDownTile(tile)!=null && (getDownTile(tile).getTileType()==TileType.EMPTY || getDownTile(tile).getTileType()==TileType.PLAYER)){
                        walls.add(tile);
                    }
                    else if(getLeftTile(tile)!=null && (getLeftTile(tile).getTileType()==TileType.EMPTY || getLeftTile(tile).getTileType()==TileType.PLAYER)){
                        walls.add(tile);
                    }
                    else if(getRightTile(tile)!=null && (getRightTile(tile).getTileType()==TileType.EMPTY || getRightTile(tile).getTileType()==TileType.PLAYER)){
                        walls.add(tile);
                    }
                }
            }
        }
        return walls;
    }

    public void addToStackPane(StackPane node){
        for(ArrayList<ScreenTile> row: levelMatrix){
            for(ScreenTile tile:row){
                node.getChildren().add(tile);
            }
        }
    }

    public void addRow(ArrayList<ScreenTile> row){
        levelMatrix.add(row);
    }


    public ScreenTile getTile(int x, int y){
        return levelMatrix.get(y).get(x);
    }

    public ScreenTile getUpTile(ScreenTile tile) {
        if(tile==null)
            return null;
        if ((int) tile.getCoordinates().getValue() == 0)
            return null;
        return getTile((int) tile.getCoordinates().getKey(), (int) tile.getCoordinates().getValue() - 1);

    }
    public ScreenTile getDownTile(ScreenTile tile){
        if(tile==null)
            return null;
        if ((int) tile.getCoordinates().getValue() == levelMatrix.size() - 1)
            return null;
        return getTile((int) tile.getCoordinates().getKey(), (int) tile.getCoordinates().getValue() + 1);
    }

    public ScreenTile getLeftTile(ScreenTile tile){
        if(tile==null)
            return null;
        if ((int) tile.getCoordinates().getKey() == 0)
            return null;
        return getTile((int) tile.getCoordinates().getKey() - 1, (int) tile.getCoordinates().getValue());
    }

    public ScreenTile getRightTile(ScreenTile tile){
        if(tile==null)
            return null;
        if ((int) tile.getCoordinates().getKey() == levelMatrix.get(0).size() - 1)
            return null;
        return getTile((int) tile.getCoordinates().getKey() + 1, (int) tile.getCoordinates().getValue());
    }

    public void setPlayer(ScreenTile player){ //TODO: might not work as expected
        /*if (this.player != null) {
            if (this.player.getTileType() == TileType.PLAYERONGOAL) {
                this.player.updateType(TileType.GOAL);
            } else if (this.player.getTileType() == TileType.PLAYER) {
                this.player.updateType(TileType.EMPTY);
            }
        }*/
        this.player = player;
        //this.player.updateType(TileType.PLAYER);
    }

    public void switchTiles(ScreenTile tile1, ScreenTile tile2){
        System.out.println("switching tiles"+tile1.type+" "+tile2.type);
        System.out.printf("tile1: %d, tile2: %d\n", tile1.getBoxPathid(), tile2.getBoxPathid());
        TileType tempType = tile1.type;
        int tempID = tile1.getBoxPathid();
        boolean tile1IsGoal = tile1.type == TileType.GOAL || tile1.type == TileType.BOXONGOAL || tile1.type == TileType.PLAYERONGOAL;
        tile1.type = tile1IsGoal? getGoalVersion(tile2.type): getNonGoalVersion(tile2.type);
        tile1.setBoxPathid(tile2.getBoxPathid());
        boolean tile2IsGoal = tile2.type == TileType.GOAL || tile2.type == TileType.BOXONGOAL || tile2.type == TileType.PLAYERONGOAL;
        tile2.type = tile2IsGoal ? getGoalVersion(tempType): getNonGoalVersion(tempType);
        tile2.setBoxPathid(tempID);



        tile1.updateImage();
        tile2.updateImage();
        tile1.setHasBeenMoved(true);
        tile2.setHasBeenMoved(true);

        System.out.println("switched tiles"+tile1.type+" "+tile2.type);
        System.out.printf("tile1: %d, tile2: %d\n", tile1.getBoxPathid(), tile2.getBoxPathid());
        System.out.printf("--------------------\n");
    }

    public TileType getGoalVersion(TileType type){
        if(type == TileType.BOX)
            return TileType.BOXONGOAL;
        else if(type == TileType.PLAYER)
            return TileType.PLAYERONGOAL;
        else if(type == TileType.EMPTY)
            return TileType.GOAL;
        else{
            return type;
        }
    }

    public TileType getNonGoalVersion(TileType type){
        if(type == TileType.BOXONGOAL)
            return TileType.BOX;
        else if(type == TileType.PLAYERONGOAL)
            return TileType.PLAYER;
        else if(type == TileType.GOAL)
            return TileType.EMPTY;
        else{
            return type;
        }
    }

    public void movePlayerLeft(){
        if (player == null) {
            System.out.println("player is null");
            return;
        }
        ScreenTile leftTile = getLeftTile(player);
        movePlayerTo(leftTile, getLeftTile(leftTile));
    }

    public void movePlayerRight(){
        if (player == null) {
            System.out.println("player is null");
            return;
        }
        ScreenTile rightTile = getRightTile(player);
        movePlayerTo(rightTile, getRightTile(rightTile));
    }

    public void removePlayerFromTile(){
        if(player == null)
            return;
        else if (player.type == TileType.PLAYER)
            player.updateType(TileType.EMPTY);
        else if (player.type == TileType.PLAYERONGOAL){
            player.updateType(TileType.GOAL);
        }
    }

    public void movePlayerUp(){
        if (player == null) {
            System.out.println("player is null");
            return;
        }
        ScreenTile upTile = getUpTile(player);
        movePlayerTo(upTile, getUpTile(upTile));
    }

    public void movePlayerDown(){
        if (player == null) {
            System.out.println("player is null");
            return;
        }
        ScreenTile downTile = getDownTile(player);
        movePlayerTo(downTile, getDownTile(downTile));
    }

    private void movePlayerTo(ScreenTile sideTile, ScreenTile sideSideTile) {
        if (sideTile == null || sideTile.type == TileType.WALL)
            return;
        if (sideTile.type == TileType.BOX || sideTile.type == TileType.BOXONGOAL) {
            if (sideSideTile == null)
                return;
            if (sideSideTile.type == TileType.WALL || sideSideTile.type == TileType.BOX || sideSideTile.type == TileType.BOXONGOAL)
                return;
            switchTiles(sideTile, sideSideTile);
        }
        switchTiles(player, sideTile);
        setPlayer(sideTile);
    }

    public boolean isLevelComplete(){
        for(ArrayList<ScreenTile> row : levelMatrix){
            for(ScreenTile tile : row){
                if(tile.type == TileType.BOX)
                    return false;
            }
        }
        return true;
    }


    /*
    *   Verifica si el jugador está en el array para debuggear
    *  @return true si el jugador está en el array
     */
    boolean playerIsOnArray(){
        for(ArrayList<ScreenTile> row : levelMatrix){
            for(ScreenTile tile : row){
                if(tile == player)
                    return true;
            }
        }
        return false;
    }

    public Level getClone() { //TODO review code. Sizes and other stuff might be wrong
        Level clone = new Level();
        for(ArrayList<ScreenTile> row : levelMatrix){
            ArrayList<ScreenTile> cloneRow = new ArrayList<>();
            for(ScreenTile tile : row){
                cloneRow.add(tile.getClone());
            }
            clone.levelMatrix.add(cloneRow);
        }
        int playerClonex=(int)player.getCoordinates().getKey();
        int playerCloney=(int)player.getCoordinates().getValue();
        ScreenTile playerClone = clone.levelMatrix.get(playerCloney).get(playerClonex);
        playerClone.updateType(player.type);
        clone.setPlayer(playerClone);
        return clone;
    }



    /*
    The value of Terrain term is computed by summing the
    number of obstacle neighbors of all empty tiles.
     */
    public float EvaluateTerrain(){
        float terrain = 0;
        for(ArrayList<ScreenTile> row : levelMatrix){
            for(ScreenTile tile : row){
                if(tile.type == TileType.EMPTY){
                    ScreenTile upTile = getUpTile(tile);
                    ScreenTile downTile = getDownTile(tile);
                    ScreenTile leftTile = getLeftTile(tile);
                    ScreenTile rightTile = getRightTile(tile);
                    if(upTile != null && upTile.type == TileType.WALL)
                        terrain++;
                    if(downTile != null && downTile.type == TileType.WALL)
                        terrain++;
                    if(leftTile != null && leftTile.type == TileType.WALL)
                        terrain++;
                    if(rightTile != null && rightTile.type == TileType.WALL)
                        terrain++;


                }
            }
        }
        return terrain;
    }

    //TODO review function
    public float EvaluateCongestion(){
        ArrayList<ScreenTile> boxes = getBoxes();
        ArrayList<ScreenTile> goals = getGoals();
        float congestion = 0;
        for(ScreenTile box : boxes){
           ScreenTile goal = getCorrespondingGoal(box, goals);
           if(goal == null) {
               System.out.println("goal is null in EvaluateCongestion X" + box.getCoordinates().getKey() + "Y" + box.getCoordinates().getValue());
               continue;
           }
           int[] boxCoordinates = {(int)box.getCoordinates().getKey(),(int) box.getCoordinates().getValue()};
           int[] goalCoordinates = {(int)goal.getCoordinates().getKey(),(int) goal.getCoordinates().getValue()};
           //make a square using a top left corner and a bottom right corner
          int topLeftX = Math.min(boxCoordinates[0], goalCoordinates[0]);
            int topLeftY = Math.min(boxCoordinates[1], goalCoordinates[1]);
            int bottomRightX = Math.max(boxCoordinates[0], goalCoordinates[0]);
            int bottomRightY = Math.max(boxCoordinates[1], goalCoordinates[1]);
            for(int i = topLeftX; i <= bottomRightX; i++){
                for(int j = topLeftY; j <= bottomRightY; j++){
                    if(levelMatrix.get(j).get(i).type == TileType.BOX || levelMatrix.get(j).get(i).type == TileType.BOXONGOAL)
                        congestion+=EvaluationParamethers.CONGESTION_BOX_WEIGHT;
                    else if (levelMatrix.get(j).get(i).type == TileType.GOAL || levelMatrix.get(j).get(i).type == TileType.PLAYERONGOAL) {
                        congestion += EvaluationParamethers.CONGESTION_GOAL_WEIGHT;
                    }
                    else if (levelMatrix.get(j).get(i).type == TileType.WALL){
                        congestion+=EvaluationParamethers.CONGESTION_WALL_WEIGHT;
                    }
                }
            }

        }
        return congestion;
    }

    public void updateScore(){
        levelScore = (float) (Math.sqrt( EvaluateTerrain() * EvaluateCongestion())/EvaluationParamethers.NORMALIZATION_FACTOR);
    }

    public float getLevelScore() {
        return levelScore;
    }

    public ScreenTile getCorrespondingGoal(ScreenTile box, ArrayList<ScreenTile> goals){
        //gets the goal that corresponds to the box with the boxpathid
        for(ScreenTile tile : goals){
            System.out.println("--------------------");
            if(tile.getBoxPathid() == box.getBoxPathid())
                return tile;
        }
        return null;
    }

    private ArrayList<ScreenTile> getGoals() {
        ArrayList<ScreenTile> goals = new ArrayList<>();
        for(ArrayList<ScreenTile> row : levelMatrix){
            for(ScreenTile tile : row){
                if(tile.type == TileType.GOAL || tile.type == TileType.BOXONGOAL)
                    goals.add(tile);
            }
        }
        return goals;
    }

    public ScreenTile getPlayer() {
        return player;
    }
}
