/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physics;

import static physics.PhysicsInterface.*;
import static main.CommonInterface.*;
import static main.Numbers.*;
import main.CommonMethods;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Yue Yang
 */
public class PhotoelectricEffect extends CommonMethods{
    
    protected double eVo;
    protected double photonFrequency;
    protected double surfaceFrequency;
    
    protected double velocity; // velocity of particles animation
    
    protected final NumberAxis xAxis = new NumberAxis(ZERO, FIVE_HUNDRED, TWENTY_FIVE); 
    protected final NumberAxis yAxis = new NumberAxis(-ONE_THOUSAND, ONE_THOUSAND, ONE_HUNDRED);
    protected final LineChart<Number, Number> eVoChart = new LineChart<Number, Number>(xAxis, yAxis);
    
    protected Label photonLabel;
    protected Label surfaceLabel;
    
    public PhotoelectricEffect() {
        baseView.setImage(base);
        eVo = velocity = photonFrequency = surfaceFrequency = ZERO;
        photonLabel = new Label(Double.toString(photonSlider.getValue()));
        surfaceLabel = new Label(Double.toString(surfaceSlider.getValue()));
        baseView.setImage(base);      
    }
    
    @Override
    public GridPane inputMenu(){       
        
        surfaceSlider.setShowTickMarks(true);
        surfaceSlider.setShowTickLabels(true);
        surfaceSlider.setSnapToTicks(true);
        surfaceSlider.setValue(ONE);
        
        photonSlider.setShowTickMarks(true);
        photonSlider.setShowTickLabels(true);
        photonSlider.setSnapToTicks(true);
        photonSlider.setValue(photonSlider.getValue());
        
        surfaceSlider.setMajorTickUnit(FIFTY);
        photonSlider.setMajorTickUnit(TWENTY_FIVE);
        
       photonSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> num, Number val, Number new_val){
                photonLabel.setText(String.format(formatString, new_val));
            }
        });
                
        surfaceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> num, Number val, Number new_val){
                surfaceLabel.setText(String.format(formatString, new_val));
            }
        });
        
        photonPane.getChildren().addAll(photonLbl, photonSlider, photonLabel);
        surfacePane.getChildren().addAll(surfaceLbl, surfaceSlider, surfaceLabel);
        
        partIV.setAlignment(Pos.CENTER);
        partIV.setVgap(THIRTY);
        partIV.add(photonPane, ZERO , ZERO);
        partIV.add(surfacePane, ZERO, ONE);
        return partIV;
    }
  
    /**Sets the background for the animation in partII
     * @return the initial grid pane
     */
    @Override
    public GridPane initializeAnimation(){
        basePane.getChildren().add(baseView);
        partIII.getChildren().add(basePane);
        partIII.setAlignment(Pos.CENTER);
        return partIII;
    }
    
    @Override
    public void readInputs(){
        photonFrequency = photonSlider.getValue();
        surfaceFrequency = surfaceSlider.getValue();
    }
    
    /**
     * Calculates kinetic energy with Einstein's photoelectric equation
     * @return 
     */
    protected double getEVo(double f){
        eVo = PLANCK_CONSTANT * (f - surfaceFrequency);
        return eVo;
    }
    
    /**This sets the velocity, which is used to determine how fast the photons and 
     * electrons will move. Please note that the actual maximum speed would be equal to
     * sqrt((eV*2)/mass of particle), but since that gives a very high value impossible to simulate,
     * the speed is simplified here with the show formulas which still take into account the frequency of 
     * both surface and photon.
     * @return velocity
     */
    @Override
    public void computeVariables(){
        if(surfaceFrequency >= photonFrequency){ // if no electrons are dejected
            velocity = TWO_THOUSAND;
        }
        
        else {
            velocity = (TWO_SIXTY-(photonFrequency-surfaceFrequency))*TWENTY;
        }
    }
    
    /**
     * Draws the base of the graph, without any animation, and puts it in partII
     * @ return partII containing the graph
     */
    @Override
    public GridPane drawGraph(){
        xAxis.setLabel(frequencyLbl.getText());
        yAxis.setLabel(eVTextLbl.getText());
        wavesSeries.setName(wavesSeriesIILbl);
        eVoChart.setCreateSymbols(false);
        eVoChart.getData().add(wavesSeries);
        partII.getChildren().add(eVoChart);
        partII.setAlignment(Pos.CENTER);
        return partII;   
    }
    
    /**Creates the timeline that will be animated
     the x increments at a constant rate, and every y is calculated using the getEvo method*/ 
    @Override
    public void createTimeline(){
        wavesTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(THREE_HUNDRED_FIFTY), new EventHandler<ActionEvent>() {
        double x = ZERO;
        double y = getEVo(x);
            @Override
            public void handle(ActionEvent actionEvent) {
                wavesSeries.getData().add(new XYChart.Data(x, y));
                x = x+TWENTY;
                y = getEVo(x);
            }
        }));
        wavesTimeline.setCycleCount(Animation.INDEFINITE);       
    }
    
    /**This method initiates both the timeline animation and
     * the particles animation
     * if the frequency of the photon surpasses the frequency of the metal's surface, 
     * electrons will be shown to be dejected from the surface
     * else, no electrons will be dejected
     */
    @Override
    public void play(){
        partIII.getChildren().clear();
        particles.setVelocity(velocity);
        particles.play(surfaceFrequency, photonFrequency);
        partIII.getChildren().add(particles.getPane());
        wavesTimeline.play();
        eVValue.setText(String.format(formatString, getEVo(photonFrequency)));
        eVValueBox.getChildren().addAll(eVTextLbl, eVValue);
        partIV.add(eVValueBox, ZERO, TWO);
    }
    
    /**This method pauses the timeline animation and the particles animation (of type particlesAnimation)
     */
    @Override
    public void pause(){
        wavesTimeline.pause();
        particles.pause();
    }
    
    /**This method is called when the timeline animation and particles animation need to resume*/
    public void resume(){
        wavesTimeline.play();
        particles.resume();
    }
    
    @Override
    public void reset(){
        remove();
    }
    
    @Override
    public void help(){
        JOptionPane.showMessageDialog(null, wavesIIHelp, helpTitle, JOptionPane.INFORMATION_MESSAGE);
    }
   
    /**Calls parent class' reset method, removes all added elements, clears the graph, so they can be re-added later on,
     * if needed, as well as resetting variables so they can be re-used*/
    @Override
    public void remove(){
        super.remove();
        wavesSeries.getData().clear();
        eVoChart.getData().clear();
        
        particles.remove();   
        eVo = velocity = photonFrequency = surfaceFrequency = ZERO;
        basePane.getChildren().clear();
        eVValueBox.getChildren().clear();
        surfaceLabel.setText(Double.toString(surfaceSlider.getValue()));
        photonLabel.setText(Double.toString(photonSlider.getValue()));
        surfacePane.getChildren().clear();
        photonPane.getChildren().clear();
    }
}
