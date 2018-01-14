/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox; 
import static main.CommonInterface.*;

/**
 *
 * @author Helen Tam
 */
public abstract class CommonMethods {
    
    public HBox controlMenu() {
        partI.getChildren().addAll(startBtn, done, pauseBtn, resumeBtn, resetBtn, helpBtn);
        partI.setAlignment(Pos.CENTER);
        return partI;
    }
    
    public void starting() {
        readInputs();
        computeVariables();
        createTimeline();
        play();
    }
    
    public void remove(){
        partI.getChildren().clear();
        partII.getChildren().clear();
        partIII.getChildren().clear();
        partIV.getChildren().clear();
        wavesTimeline.stop();
        wavesTimeline.getKeyFrames().clear();
    }
    
    public void disableBtns() {
        startBtn.setDisable(false);
        pauseBtn.setDisable(true);
        resumeBtn.setDisable(true);
        resetBtn.setDisable(true);
    }
    
    public abstract GridPane inputMenu();
    public abstract void readInputs();
    public abstract void computeVariables();
    public abstract GridPane drawGraph();
    public abstract GridPane initializeAnimation();
    public abstract void createTimeline();
    public abstract void play();
    public abstract void pause();
    public abstract void resume();
    public abstract void reset();
    public abstract void help();
}