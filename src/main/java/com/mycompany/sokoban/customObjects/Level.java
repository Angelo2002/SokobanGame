package com.mycompany.sokoban.customObjects;

import com.mycompany.model.Player;
import javafx.util.Pair;

import java.util.ArrayList;

public class Level {
    static final int tileSize = 50;
    ArrayList<ArrayList<ScreenTile>> level;

    ScreenTile player;

    public Level(){
        level = new ArrayList<>();
    }

    public Level(int size, int playerx, int playery){
        level = new ArrayList<>();
        for(int i=0;i<size;i++){
            ArrayList<ScreenTile> row = new ArrayList<>();
            for(int j=0;j<size;j++){
                ScreenTile tile =new ScreenTile(j,i,TileType.WALL);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                row.add(tile);
            }
            level.add(row);
        }

        this.player = new ScreenTile(playerx,playery,TileType.PLAYER);

        //coordenadas (y,x) por como se manejan las matrices
        this.level.get(playery).set(playerx,player);

    }

    public void addRow(ArrayList<ScreenTile> row){
        level.add(row);
    }


    public ScreenTile getTile(int x, int y){
        return level.get(y).get(x);
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
        if ((int) tile.getCoordinates().getValue() == level.size() - 1)
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
        if ((int) tile.getCoordinates().getKey() == level.get(0).size() - 1)
            return null;
        return getTile((int) tile.getCoordinates().getKey() + 1, (int) tile.getCoordinates().getValue());
    }

    public void setPlayer(ScreenTile player){
        if(this.player != null)
            this.player.type = TileType.EMPTY;
        this.player = player;
    }

    public void switchTiles(ScreenTile tile1, ScreenTile tile2){
        TileType temp = tile1.type;
        tile1.type = tile2.type;
        tile2.type = temp;
        tile1.updateImage();
        tile2.updateImage();
    }

    public void movePlayerLeft(){
        if (player == null) {
            System.out.println("player is null");
            return;
        }
        ScreenTile leftTile = getLeftTile(player);
        if(leftTile == null)
            return;
        if(leftTile.type == TileType.BOX || leftTile.type == TileType.BOXONGOAL){
            ScreenTile leftLeftTile = getLeftTile(leftTile);
            if(leftLeftTile == null)
                return;
            if(leftLeftTile.type == TileType.WALL || leftLeftTile.type == TileType.BOX || leftLeftTile.type == TileType.BOXONGOAL)
                return;
            switchTiles(leftTile, leftLeftTile);
        }
        switchTiles(player, leftTile);
        setPlayer(leftTile);
    }

    public void movePlayerRight(){
        if (player == null) {
            System.out.println("player is null");
            return;
        }
        ScreenTile rightTile = getRightTile(player);
        if(rightTile == null)
            return;
        if(rightTile.type == TileType.BOX || rightTile.type == TileType.BOXONGOAL){
            ScreenTile rightRightTile = getRightTile(rightTile);
            if(rightRightTile == null)
                return;
            if(rightRightTile.type == TileType.WALL || rightRightTile.type == TileType.BOX || rightRightTile.type == TileType.BOXONGOAL)
                return;
            switchTiles(rightTile, rightRightTile);
        }
        switchTiles(player, rightTile);
        setPlayer(rightTile);
    }

    public void movePlayerUp(){
        if (player == null) {
            System.out.println("player is null");
            return;
        }
        ScreenTile upTile = getUpTile(player);
        if(upTile == null)
            return;
        if(upTile.type == TileType.BOX || upTile.type == TileType.BOXONGOAL){
            ScreenTile upUpTile = getUpTile(upTile);
            if(upUpTile == null)
                return;
            if(upUpTile.type == TileType.WALL || upUpTile.type == TileType.BOX || upUpTile.type == TileType.BOXONGOAL)
                return;
            switchTiles(upTile, upUpTile);
        }
        switchTiles(player, upTile);
        setPlayer(upTile);
    }

    public void movePlayerDown(){
        if (player == null) {
            System.out.printf("player is null");
            return;
        }
        ScreenTile downTile = getDownTile(player);
        if(downTile == null)
            return;
        if(downTile.type == TileType.BOX || downTile.type == TileType.BOXONGOAL){
            ScreenTile downDownTile = getDownTile(downTile);
            if(downDownTile == null)
                return;
            if(downDownTile.type == TileType.WALL || downDownTile.type == TileType.BOX || downDownTile.type == TileType.BOXONGOAL)
                return;
            switchTiles(downTile, downDownTile);
        }
        switchTiles(player, downTile);
        setPlayer(downTile);
    }

    public boolean isLevelComplete(){
        for(ArrayList<ScreenTile> row : level){
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
        for(ArrayList<ScreenTile> row : level){
            for(ScreenTile tile : row){
                if(tile.type == TileType.PLAYER)
                    return true;
            }
        }
        return false;
    }

}
