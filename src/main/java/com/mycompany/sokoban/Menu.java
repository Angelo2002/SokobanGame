package com.mycompany.sokoban;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
}
