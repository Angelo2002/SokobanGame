package com.mycompany.sokoban;

import com.mycompany.sokoban.customObjects.Level;
import com.mycompany.sokoban.customObjects.TileType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

//todo: Replace system.out.println with logger and alert to user

public class Menu {
    @FXML
    private void openSaveDeleteWindow(ActionEvent actionEvent) {
    }

    @FXML
    private void openSaveSelector(ActionEvent actionEvent) {

    }

    @FXML
    private void openNewGameCreator(ActionEvent actionEvent) {
        try{
            App.setRoot("NewGameCreator");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void openTest(ActionEvent actionEvent) {
        try{
            FXMLLoader loader =  App.getLoader("LevelWindow");
            Parent root = loader.load();

            LevelWindow controller = loader.getController();
            Level testLevel = new Level(5,4,4);
            testLevel.movePlayerDown();
            testLevel.movePlayerLeft();

            testLevel.getTile(3,4).updateType(TileType.EMPTY);
            testLevel.getTile(2,4).updateType(TileType.EMPTY);
            testLevel.getTile(2,3).updateType(TileType.BOXONGOAL);
            testLevel.getTile(2,2).updateType(TileType.EMPTY);

            controller.loadLevel(testLevel);

            App.setRoot(root);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
