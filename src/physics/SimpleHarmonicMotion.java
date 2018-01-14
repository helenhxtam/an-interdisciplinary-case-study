/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physics;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import static main.CommonInterface.*;
import static main.Numbers.*;
import static physics.PhysicsInterface.*;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import main.CommonMethods;

/**
 *
 * @author Yue Yang
 */
public class SimpleHarmonicMotion extends CommonMethods{
    
    protected final NumberAxis xAxis = new NumberAxis(ZERO, TWENTY_FIVE, FIVE); 
    protected final NumberAxis yAxis = new NumberAxis(MINUS_THREE, THREE, ONE_HALF);
    
    protected final ComboBox amplitudeBox;
    
    protected Label amplitudeValue;
    protected Label angularValue;
    
    protected double amplitude;
    protected double angularFrequency;
    protected double velocity;
    protected double position;
    protected double time;
    protected double period;
    protected double duration;
    
    protected LineChart<Number, Number> positionChart = new LineChart<Number, Number>(xAxis, yAxis);
    
    protected double xIncrement;
 
    protected ImageView animationMass;
    Group animationGroup;
    
    protected int columns; //number of columns in the picture containing the frames
    protected int count; //number of frames
    protected final int WIDTH    = THREE_SIXTY; //width of a frame
    protected final int HEIGHT   = FOUR_NINETY_SIX; //height of a frame
    
//    SpriteAnimation massSpring;
    
    /**No-arg constructor that initializes variables*/
    public SimpleHarmonicMotion(){
        amplitude = angularFrequency = velocity = position = time = period = duration = ZERO;
        animationMass = new ImageView();
        count = columns = ZERO;
        animationMass.setViewport(new Rectangle2D(ZERO, ZERO, WIDTH, HEIGHT)); 
        animationGroup = new Group(animationMass);
        angularValue = new Label (Double.toString(angularSlider.getValue()));
        amplitudeValue = new Label (Double.toString(ampSlider.getValue()));
        amplitudeBox = new ComboBox();
        
//        massSpring = new SpriteAnimation();
    }
        
    /**Adds all necessary components for user-input graphical interface 
     * @return partIV
     */
    @Override
    public GridPane inputMenu(){
        
        ampSlider.setShowTickMarks(true);
        ampSlider.setShowTickLabels(true);
        ampSlider.setSnapToTicks(true);
        ampSlider.setMinorTickCount(ZERO);
        ampSlider.setValue(ONE);
        
        angularSlider.setShowTickMarks(true);
        angularSlider.setShowTickLabels(true);
        angularSlider.setSnapToTicks(true);
        
        ampSlider.setMajorTickUnit(ONE);
        angularSlider.setMajorTickUnit(ONE_HALF);
        
        ampSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> num, Number val, Number new_val){
                amplitudeValue.setText(format.format(new_val).toString());
            }
        });
//                
        angularSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> num, Number val, Number new_val){
                angularValue.setText(format.format(new_val).toString());
            }
        });
        
        amplitudePane.getChildren().addAll(amplitudeLbl, ampSlider, amplitudeValue); //adds elements to HBox for amplitude input
        angularPane.getChildren().addAll(angularLbl, angularSlider, angularValue); //adds elements to HBox for angular velocity input
        
        partIV.setVgap(FIFTY);
        partIV.setAlignment(Pos.CENTER);
        partIV.add(amplitudePane, ZERO, ZERO, ONE, ONE);
        partIV.add(angularPane, ZERO, ONE, ONE, ONE);
        partIV.add(phaseConstLbl, ZERO, TWO, ONE, ONE);
        
        return partIV;
     
    }
    
    /**Takes in user inputs from the graphical interface
     * to determine amplitude, angularFrequency, count, columns, and Image to be used for animationMass
     */
    @Override
    public void readInputs(){

        amplitude= (Double)ampSlider.getValue();        
        angularFrequency = (Double)angularSlider.getValue();
        
        if (amplitude <=ONE){
            animationMass.setImage(ANIMATION_V3);
            count = SEVEN;
            columns = FOUR;
        }
        
        else if((amplitude) > ONE && (amplitude <= TWO)){
            animationMass.setImage(ANIMATION_V2);
            count = NINE;
            columns = FIVE;
        }
        
        else if(amplitude > TWO){
            animationMass.setImage(ANIMATION_V1);
            count = ELEVEN;
            columns = SIX;
        }
        massSpring.setVariables(animationMass,Duration.millis(calculatePeriod()*ONE_THOUSAND),count, columns,WIDTH, HEIGHT);
        
    }
    
    /**This calculates the speed the timeline must play out.
     * @return duration
     */
    @Override
    public void computeVariables(){
        duration = calculatePeriod()*EIGHTY;       
    }
    
    public double getDuration(){
        return duration;
    }

    /**Draws the positionChart, the reference point for the timeline, which will then be added to part II
     * @return part II
     */
    @Override
    public GridPane drawGraph(){
        xAxis.setLabel(timeString);
        yAxis.setLabel(positionString);
        wavesSeries.setName(wavesSeriesI);
        positionChart.setCreateSymbols(false);
        positionChart.getData().add(wavesSeries);
        partII.getChildren().add(positionChart);
        partII.setAlignment(Pos.CENTER);
        return partII;        
    }

    /**For the initial display of partIII without any animation
     * 
     * @return partIII
     */
    @Override
    public GridPane initializeAnimation(){
        partIII.getChildren().add(massView);
        partIII.setAlignment(Pos.CENTER);
        return partIII;
    }
    
    protected double calculateVelocity(double t){
        time = t;
        velocity = angularFrequency*amplitude*Math.cos(angularFrequency*time+phaseConst);
        return velocity;
    }
    
    /**Calculates position based on given arguments
     * @param t = time
     * @param a = amplitude
     * @return position (value which will be displayed on the positionChart)
     */
    protected double calculatePosition(double t, double a){
        time = t;
        amplitude = a;
        position = amplitude * Math.sin(angularFrequency*time + phaseConst);
        return position; 
    }
      
//    public double calculateAcceleration(){
//        acceleration = Math.pow(angularFrequency, 2)* amplitude* Math.sin(angularFrequency*time + phaseConst);
//        return acceleration;
//    }
    
    /**Calculates the period of the the motion, this will determine the speed of animation of both the timeline and the 
     * spriteAnimation. Note that the returned value reduces period 3 folds to increase animation speed, as
     * the amplitude present in the application cannot match the real amplitude(range from 1m to 3 m)
     * @return period/3
     */
    protected double calculatePeriod(){
        period = TWO*PI/angularFrequency;
        return period/THREE;
    }
    
    /**Creates the timeline that will traverse the positionChart. Every corresponding y coordinate is calculated with
     * the method calculatePosition
     */
    @Override
    public void createTimeline(){
        wavesTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
        double x = ZERO; //initial x
        double y = calculatePosition(x, amplitude); //initial y
            @Override
            public void handle(ActionEvent actionEvent) {
                wavesSeries.getData().add(new XYChart.Data(x, y));
                x = x+POINT_TWO*PI;
                y = calculatePosition(x, amplitude);
            }
        }));
        wavesTimeline.setCycleCount(Animation.INDEFINITE);//ensures that the timeline will go through entire chart
    }
      
    /**removes massView from partIII to replace it with animationGroup, which contains all frames to 
     * be animated
     */
    @Override
    public void play(){
        wavesTimeline.play();
        partIII.getChildren().remove(massView);
        partIII.getChildren().add(animationGroup);
        massSpring.setCycleCount(Animation.INDEFINITE);
        massSpring.play();     
//        massSpring.pause();
    }
    
    @Override
    public void pause(){
        wavesTimeline.pause();
        massSpring.pause();
    }
    
    @Override
    public void resume(){
        massSpring.play();
        wavesTimeline.play();
    }
    
    @Override
    public void reset(){
        remove();
    }
    
    @Override
    public void help(){
        JOptionPane.showMessageDialog(null, wavesIIHelp, helpTitle, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**Calls parent class' remove method, removes all added elements, clears the graph, so they can be re-added later on,
     * if needed, as well as resetting variables so they can be re-used*/
    @Override
    public void remove(){
        super.remove();
        wavesSeries.getData().clear();
        positionChart.getData().clear();
        massSpring.stop();
        
        amplitude = angularFrequency = velocity = position = time = period = duration = ZERO;
        count = columns = ZERO;
        angularValue.setText(Double.toString(angularSlider.getValue()));
        amplitudeValue.setText(Double.toString(ampSlider.getValue()));
        amplitudePane.getChildren().clear();
        angularPane.getChildren().clear();
    }
    
}
