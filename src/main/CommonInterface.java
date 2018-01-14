/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.text.DecimalFormat;
import javafx.animation.Timeline;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 *
 * @authors George Lam, Helen Tam
 */
public interface CommonInterface 
{
    public final double WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public final double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    public final double vGap = 0.02 * HEIGHT;
    
    DecimalFormat format = new DecimalFormat("#.##");
    
    public final Button startBtn = new Button("Start");
    public final Button resumeBtn = new Button("Resume");
    public final Button pauseBtn = new Button("Pause");
    public final Button resetBtn = new Button("Reset");
    public final Button done = new Button("Done");
    public final Button helpBtn = new Button("Help");
    
    public final String formatString = "%.0f";
    public final String formatStringD = "%.1f";
    
    public final VBox root = new VBox();
    public final GridPane center = new GridPane();
    public final HBox partI = new HBox(50);
    public final GridPane partII = new GridPane();
    public final GridPane partIII = new GridPane();
    public final GridPane partIV = new GridPane();
    
    public final Timeline wavesTimeline = new Timeline();
    public final XYChart.Series wavesSeries = new XYChart.Series();
    
    public final MenuBar menuBar = new MenuBar();
    public final Menu mechanicsMenu = new Menu("Mechanics");
    public final Menu wavesMenu = new Menu("Waves/Optics");
    public final Menu calculusMenu = new Menu("Calculus");
    public final Menu optionsMenu = new Menu("Options");
    public final MenuItem mechanicsItemI = new MenuItem("Newton's Second Law");
    public final MenuItem mechanicsItemII = new MenuItem("Mechanical Energy");
    public final MenuItem wavesItemI = new MenuItem("Simple Harmonic Motion");
    public final MenuItem wavesItemII = new MenuItem("Photoelectric Effect");
    public final MenuItem calculusItemI = new MenuItem("New Sport Bike");
    public final MenuItem calculusItemII = new MenuItem("Infinite Geometric Series");
    public final MenuItem exitItem = new MenuItem("Exit");
    public final String mainTitle = "Interdisciplinary Case Study";
    public final String helpTitle = "Help";

    public final ActionsPerformed perform = new ActionsPerformed();
}
