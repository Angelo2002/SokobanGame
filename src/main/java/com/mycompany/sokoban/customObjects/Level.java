package com.mycompany.sokoban.customObjects;

import com.mycompany.model.Tile;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Level {
    static final int tileSize = 50;
    ArrayList<ArrayList<ScreenTile>> levelMatrix;

    ScreenTile player;

    public Level(){
        levelMatrix = new ArrayList<>();
    }

    public Level(int size, int playerx, int playery){
        levelMatrix = new ArrayList<>();
        for(int i=0;i<size;i++){
            ArrayList<ScreenTile> row = new ArrayList<>();
            for(int j=0;j<size;j++){
                ScreenTile tile =new ScreenTile(j,i,TileType.WALL);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                row.add(tile);
            }
            levelMatrix.add(row);
        }

        //TODO arreglar este código horrendo
        //coordenadas (y,x) por como se manejan las matrices
        ScreenTile player = this.levelMatrix.get(playery).get(playerx);
        player.updateType(TileType.PLAYER);
        this.player = player;

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

    public void setPlayer(ScreenTile player){
        //if(this.player != null)
        //    this.player.type = TileType.EMPTY;
        this.player = player;
    }

    public void switchTiles(ScreenTile tile1, ScreenTile tile2){
        System.out.println("switching tiles"+tile1.type+" "+tile2.type);
        TileType temp = tile1.type;
        boolean tile1IsGoal = tile1.type == TileType.GOAL || tile1.type == TileType.BOXONGOAL || tile1.type == TileType.PLAYERONGOAL;
        tile1.type = tile1IsGoal? getGoalVersion(tile2.type): getNonGoalVersion(tile2.type);
        boolean tile2IsGoal = tile2.type == TileType.GOAL || tile2.type == TileType.BOXONGOAL || tile2.type == TileType.PLAYERONGOAL;
        tile2.type = tile2IsGoal ? getGoalVersion(temp): getNonGoalVersion(temp);

        tile1.updateImage();
        tile2.updateImage();

        System.out.println("switched tiles"+tile1.type+" "+tile2.type);
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
            System.out.printf("player is null");
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

}
