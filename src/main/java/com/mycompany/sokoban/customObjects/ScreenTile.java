package com.mycompany.sokoban.customObjects;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

import javafx.scene.image.ImageView;


public class ScreenTile extends StackPane {
    private int x;
    private int y;


    private boolean hasBeenMoved;

    private int BoxPathid;

    TileType type;


    public ScreenTile(int x, int y,TileType type){
        this.x = x;
        this.y = y;
        this.type = type;
        this.BoxPathid = -1;
        updateImage();
        hasBeenMoved = false;
    }

    public ScreenTile(int x, int y,TileType type, int id){
        this.x = x;
        this.y = y;
        this.type = type;
        this.BoxPathid = id;
        updateImage();
        hasBeenMoved = false;
    }

    public TileType getTileType(){
        return type;
    }


    public void updateType(TileType type){
        this.type = type;
        updateImage();
    }

    public void updateImage(){
        super.getChildren().setAll(new ImageView(type.getImage()));
    }

    public Pair getCoordinates(){
        return new Pair(x,y);
    }

    public void setHasBeenMoved(boolean b){
        hasBeenMoved = b;
    }

    public ScreenTile getClone() {
        ScreenTile clone = new ScreenTile(x, y, type, BoxPathid);
        clone.setTranslateX(this.getTranslateX());
        clone.setTranslateY(this.getTranslateY());
        return clone;
    }

    public boolean hasBeenMoved() {
        return hasBeenMoved;
    }

    public int getBoxPathid() {
        return BoxPathid;
    }

    public void setBoxPathid(int id) {
        BoxPathid = id;
    }



}
