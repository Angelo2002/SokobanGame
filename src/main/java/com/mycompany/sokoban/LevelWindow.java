package com.mycompany.sokoban;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class LevelWindow {


    @FXML
    private StackPane spane_game;

    @FXML
    private void backToMenu(ActionEvent actionEvent) {
        try{
            App.setRoot("Menu");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
