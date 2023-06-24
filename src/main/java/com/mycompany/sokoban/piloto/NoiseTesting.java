package com.mycompany.sokoban.piloto;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class NoiseTesting {
    private GridPane gridPane;
    @FXML
    private AnchorPane root;

    @FXML
    public void initialize() {
        gridPane = new GridPane();
        int rows=5;
        int columns=8;
        gridPane.setGridLinesVisible(true);



        long seed = Math.round(Math.random()*1000000);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Canvas canvas = new Canvas(50, 50);
                canvas.getGraphicsContext2D().fillRect(0, 0, 50, 50);
                float value = OpenSimplex2S.noise2_ImproveX(seed,j,i);
                if( 0 <value && value < 0.3) {
                    System.out.println(value);

                    canvas.getGraphicsContext2D().setFill(javafx.scene.paint.Color.BLUE);
                }else{

                    canvas.getGraphicsContext2D().setFill(javafx.scene.paint.Color.RED);
                }
                canvas.getGraphicsContext2D().fillRect(0, 0, 50, 50);
                gridPane.add(canvas, j, i);
            }
        }
        root.getChildren().add(gridPane);
    }

}
