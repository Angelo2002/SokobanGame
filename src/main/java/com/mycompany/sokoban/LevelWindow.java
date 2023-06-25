package com.mycompany.sokoban;

import com.mycompany.sokoban.customObjects.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class LevelWindow {


    @FXML
    private StackPane spane_game;

    private Level level;
    @FXML
    private AnchorPane apane_levelwindow;

    @FXML
    public void initialize(){
        spane_game.requestFocus();
        spane_game.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
            switch (e.getCode()) {
                case UP: case W:
                    level.movePlayerUp();
                    break;
                case DOWN: case S:
                    level.movePlayerDown();
                    break;
                case LEFT: case A:
                    level.movePlayerLeft();
                    break;
                case RIGHT: case D:
                    level.movePlayerRight();
                    break;
            }
            updateLevel();
        });
    }

    @FXML
    private void backToMenu(ActionEvent actionEvent) {
        try{
            App.setRoot("Menu");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void loadLevel(Level level){
        this.level = level;
        spane_game.getChildren().clear();
        this.level.addToStackPane(spane_game);
    }




    private void updateLevel(){
        spane_game.getChildren().clear();
        this.level.addToStackPane(spane_game);
    }

}


