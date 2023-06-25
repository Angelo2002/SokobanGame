package com.mycompany.sokoban.customObjects;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

import javafx.scene.image.ImageView;


public class ScreenTile extends StackPane {
    private int x;
    private int y;



    TileType type;


    public ScreenTile(int x, int y,TileType type){
        this.x = x;
        this.y = y;
        this.type = type;
        updateImage();
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


}
