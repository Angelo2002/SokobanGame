package com.mycompany.sokoban;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class LevelSelector {
    private String levelSetName;
    private ArrayList<Integer> levels;
    @FXML
    private HBox hbox_levelcontainer1;
    @FXML
    private HBox hbox_levelcontainer2;
    @FXML
    private AnchorPane apane_LevelSelector;


    @FXML
    public void initialize(){
        levelSetName = "default";
        levels = new ArrayList<>();
    }

    private void openLevel(ActionEvent actionEvent) {
        try{
            App.setRoot("Game");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void backToMenu(ActionEvent actionEvent) {
        try{
            App.setRoot("Menu");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
