package com.mycompany.sokoban.customObjects;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;

import static javafx.scene.paint.Color.rgb;



public class Sprites {
    static final Image wallImage;
    static final Image emptyImage;
    static final Image boxImage;
    static final Image playerImage;
    static final Image goalImage;
    static final Image boxOnGoalImage;
    static final Image playerOnGoalImage;

    static {

        File file;

        file = new File("./src/main/resources/com/mycompany/sokoban/images/empty.png");
        if (file.exists()){
            emptyImage = new Image(file.toURI().toString());
        }
        else emptyImage = colorCodedImage(102, 102, 153);

        file = new File("./src/main/resources/com/mycompany/sokoban/images/wall.png");
        if (file.exists()){
            Canvas canvas = new Canvas(50, 50);
            canvas.getGraphicsContext2D().drawImage(emptyImage,0,0);
            canvas.getGraphicsContext2D().drawImage(new Image(file.toURI().toString(),50,50,false,false),0,0);
            wallImage = canvas.snapshot(null,null);
        }
        else wallImage = colorCodedImage(255,0,0);



        file = new File("./src/main/resources/com/mycompany/sokoban/images/box.png");
        if (file.exists()){
            Canvas canvas = new Canvas(50, 50);
            canvas.getGraphicsContext2D().drawImage(emptyImage,0,0);
            canvas.getGraphicsContext2D().drawImage(new Image(file.toURI().toString(),50,50,false,false),0,0);
            boxImage = canvas.snapshot(null,null);
        }
        else boxImage = colorCodedImage(150,75,0);

        file = new File("./src/main/resources/com/mycompany/sokoban/images/player.png");
        if (file.exists()){
            playerImage = new Image(file.toURI().toString());
        }
        else playerImage = colorCodedImage(0,0,255);

        file = new File("./src/main/resources/com/mycompany/sokoban/images/goal.png");
        if (file.exists()){

            goalImage = new Image(file.toURI().toString());
        }
        else goalImage = colorCodedImage(0,255,0);

        file = new File("./src/main/resources/com/mycompany/sokoban/images/boxOnGoal.png");
        if (file.exists()){
            boxOnGoalImage = new Image(file.toURI().toString(),50,50,false,false);
        }
        else {

            Canvas canvas = new Canvas(50, 50);
            canvas.getGraphicsContext2D().drawImage(goalImage,0,0);
            canvas.getGraphicsContext2D().drawImage(boxImage,0,0);
            //put a glowing effect on the image
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.2);
            canvas.getGraphicsContext2D().setEffect(colorAdjust);
            boxOnGoalImage = canvas.snapshot(null,null);

        }

        file = new File("./src/main/resources/com/mycompany/sokoban/images/playerOnGoal.png");
        if (file.exists()){
            playerOnGoalImage = new Image(file.toURI().toString());
        }
        else playerOnGoalImage = colorCodedImage(255,0,255);

    }

    static private Image colorCodedImage(int r, int g, int b){
        Canvas canvas = new Canvas(50, 50);
        canvas.getGraphicsContext2D().setFill(rgb(r,g,b));
        canvas.getGraphicsContext2D().fillRect(0, 0, 50, 50);
        return canvas.snapshot(null,null);
    }


    //TODO delete this obsolete method
    static public Image getTileCorrespondingImage(TileType type){
        switch (type){
            case WALL:
                return wallImage;
            case EMPTY:
                return emptyImage;
            case BOX:
                return boxImage;
            case PLAYER:
                return playerImage;
            case GOAL:
                return goalImage;
            case BOXONGOAL:
                return boxOnGoalImage;
            case PLAYERONGOAL:
                return playerOnGoalImage;
            default:
                return null;
        }
    }

}
