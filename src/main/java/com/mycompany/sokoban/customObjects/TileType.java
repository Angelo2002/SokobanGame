package com.mycompany.sokoban.customObjects;

import javafx.scene.image.Image;



public enum TileType {
    WALL, EMPTY, BOX, PLAYER, GOAL, BOXONGOAL, PLAYERONGOAL;

    public Image getImage(){
        switch (this){
            case WALL:
                return Sprites.wallImage;
            case EMPTY:
                return Sprites.emptyImage;
            case BOX:
                return Sprites.boxImage;
            case PLAYER:
                return Sprites.playerImage;
            case GOAL:
                return Sprites.goalImage;
            case BOXONGOAL:
                return Sprites.boxOnGoalImage;
            case PLAYERONGOAL:
                return Sprites.playerOnGoalImage;
            default:
                return null;
        }
    }
}


