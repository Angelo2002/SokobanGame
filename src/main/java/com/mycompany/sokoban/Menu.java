package com.mycompany.sokoban;

import com.mycompany.sokoban.MCSTGen.LevelNode;
import com.mycompany.sokoban.MCSTGen.MCSTLevelGenerator;
import com.mycompany.sokoban.customObjects.Level;
import com.mycompany.sokoban.customObjects.TileType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.ArrayList;

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

            Level generatedLevel = test03();
            System.out.println("Level score "+generatedLevel.getLevelScore());
            controller.loadLevel(generatedLevel);
            App.setRoot(root);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static Level test03() {
        LevelNode startNode = new LevelNode(new Level(6,2,2),0);

        MCSTLevelGenerator generator = new MCSTLevelGenerator(startNode,10,0,30000);
        generator.startGeneration();
        ArrayList<Level> levels = generator.getLevels();
        levels.forEach(level -> System.out.println(level.getLevelScore()));
        generator.printImmediateChildrenVisits();
        System.out.println("------------------");
        generator.printVisits();
        return generator.getLevelWithHighestScore();

        //return generator.getLevelWithHighestScore();

        //testNode.turnLevelBoxesIntoGoals();
        //testNode.turnUnmovedGoalsIntoWalls();


    }

    private static LevelNode test02() {
        LevelNode testNode = new LevelNode(new Level(10,4,4),-1);
        testNode.ActionDeleteWalls(5);
        testNode.ActionDeleteWalls(10);
        testNode.ActionDeleteWalls(10);
        testNode.ActionDeleteWalls(10);
        testNode.ActionDeleteWalls(10);
        testNode.ActionPlaceBoxes(6);

        testNode.ActionFreezeLevel();

        testNode.ActionMovePlayer(100);
        testNode.mergeWithFrozenLevel();


        //testNode.turnLevelBoxesIntoGoals();
        //testNode.turnUnmovedGoalsIntoWalls();

        return testNode;
    }

    private static Level test001() {
        Level testLevel = new Level(5,4,4);
        testLevel.movePlayerDown();
        testLevel.movePlayerLeft();

        testLevel.getTile(3,4).updateType(TileType.EMPTY);
        testLevel.getTile(2,4).updateType(TileType.EMPTY);
        testLevel.getTile(2,3).updateType(TileType.BOXONGOAL);
        testLevel.getTile(2,2).updateType(TileType.EMPTY);
        return testLevel;
    }
}
