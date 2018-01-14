/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import static main.CommonInterface.*;
import static main.Numbers.*;

/**
 *
 * @author George Lam
 */
public class InterdisciplinaryCaseStudy extends Application {
    
    @Override
    public void start(Stage primaryStage) { 
        Scene scene = new Scene(root);
        menuBar.getMenus().addAll(mechanicsMenu, wavesMenu, calculusMenu, optionsMenu);
        mechanicsMenu.getItems().addAll(mechanicsItemI, mechanicsItemII);
        wavesMenu.getItems().addAll(wavesItemI, wavesItemII);
        calculusMenu.getItems().addAll(calculusItemI, calculusItemII);
        optionsMenu.getItems().addAll(exitItem);
        root.getChildren().addAll(menuBar, center);
        
        initializeGridPane();
        center.setGridLinesVisible(true);
        
        mechanicsItemI.setOnAction(perform);
        mechanicsItemII.setOnAction(perform);
        wavesItemI.setOnAction(perform);
        wavesItemII.setOnAction(perform);
        calculusItemI.setOnAction(perform);
        calculusItemII.setOnAction(perform);
        exitItem.setOnAction(perform);
        startBtn.setOnAction(perform);
        done.setOnAction(perform);
        pauseBtn.setOnAction(perform);
        resumeBtn.setOnAction(perform);
        resetBtn.setOnAction(perform);
        helpBtn.setOnAction(perform);
        
        primaryStage.setTitle(mainTitle);
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void initializeGridPane()
    {
        for (int i = ZERO; i < TWO; i++)
        {
            ColumnConstraints col = new ColumnConstraints(WIDTH/TWO);
            RowConstraints row = new RowConstraints(HEIGHT/TWO);
            center.getColumnConstraints().add(col);
            center.getRowConstraints().add(row);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
