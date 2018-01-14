/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physics;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static main.CommonInterface.*;
import main.CommonMethods;
import static main.Numbers.*;
import static physics.PhysicsInterface.*;

/**
 *
 * @author Helen Tam
 */
public class MechanicalEnergy extends CommonMethods
{
    protected double arrowMass;
    protected double springConst;
    protected double extension;
    protected double elasticEnergy;
    protected double forceOfArrow;
    protected double arrowAcceleration;
    protected double arrowVelocity;
    
    protected double distance;
    protected double duration;
    protected double finalX;
    
    @Override
    public GridPane inputMenu()
    {
        arrowMassSlider.setMajorTickUnit(ONE);
        arrowMassSlider.setValue(ONE1);
        arrowMassSlider.setShowTickLabels(true);
        arrowMassSlider.setShowTickMarks(true);
        
        springConstSlider.setMajorTickUnit(ONE);
        springConstSlider.setValue(ONE1);
        springConstSlider.setShowTickLabels(true);
        springConstSlider.setShowTickMarks(true);
        
        extensionSlider.setMajorTickUnit(POINT_TWO);
        extensionSlider.setValue(ZERO0);
        extensionSlider.setShowTickLabels(true);
        extensionSlider.setShowTickMarks(true);
        
        arrowMassValueLabel.setText(Double.toString(arrowMassSlider.getValue()));
        arrowMassSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                    arrowMassValueLabel.setText(String.format(formatStringD, new_val));
                }
        });
         
        springConstValueLabel.setText(Double.toString(springConstSlider.getValue()));
        springConstSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                     springConstValueLabel.setText(String.format(formatStringD, new_val));
                }
        });
         
        extensionValueLabel.setText(Double.toString(extensionSlider.getValue()));
        extensionSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                    extensionValueLabel.setText(String.format(formatStringD, new_val));
                }
        });
        
        arrowMassSlider.setDisable(false);
        springConstSlider.setDisable(false);
        extensionSlider.setDisable(false);
        
        paneArrowMassSliderI.getChildren().addAll(arrowMassSlider, arrowMassValueLabel, mUnit);
        paneArrowMassSliderII.getChildren().addAll(arrowMassLabel, paneArrowMassSliderI);
        
        paneSpringConstSliderI.getChildren().addAll(springConstSlider, springConstValueLabel, springConstUnit);
        paneSpringConstSliderII.getChildren().addAll(springConstLabel, paneSpringConstSliderI);
        
        paneExtensionSliderI.getChildren().addAll(extensionSlider, extensionValueLabel, dUnit);
        paneExtensionSliderII.getChildren().addAll(extensionLabel, paneExtensionSliderI);
        
        partIV.setVgap(vGap);
        
        partIV.add(paneArrowMassSliderII, ZERO, ZERO, ONE, ONE);
        partIV.add(paneSpringConstSliderII, ZERO, ONE, ONE, ONE);
        partIV.add(paneExtensionSliderII, ZERO, TWO, ONE, ONE);
        
        partIV.setAlignment(Pos.CENTER);
        
        return partIV;
    }
    
    @Override
    public void readInputs()
    {
        arrowMass = arrowMassSlider.getValue();
        springConst = springConstSlider.getValue();
        extension = extensionSlider.getValue();
        
        arrowMassSlider.setDisable(true);
        springConstSlider.setDisable(true);
        extensionSlider.setDisable(true);
    }
    
    @Override
    public void computeVariables()
    {
        distance = (extension * TWO_HUNDRED + WIDTH/FOUR - arrow.getWidth()/TWO) / ONE_HUNDRED;
        
        elasticEnergy = ONE_HALF * springConst * (extension * extension);
        forceOfArrow = springConst * extension;
        arrowAcceleration = forceOfArrow / arrowMass;
        arrowVelocity = Math.sqrt((TWO) * arrowAcceleration * distance);
        
        duration = (distance / arrowVelocity) * ONE_THOUSAND;
    }
    
    @Override
    public GridPane drawGraph() {
        mechIIXAxis.setLabel(keString);
        mechIIYAxis.setLabel(eeString);
   
        mechIIXAxis.setAutoRanging(false);
        mechIIYAxis.setAutoRanging(false);

        mechIIGraph.setLegendVisible(false);
        mechIIGraph.setCreateSymbols(false);
        mechIIGraph.setAnimated(false);
        mechIIGraph.getData().add(mechIISeries);
        
        partII.getChildren().add(mechIIGraph);
        partII.setAlignment(Pos.CENTER);
        
        return partII;
    }
    
    @Override
    public GridPane initializeAnimation()
    {   
        displayBow.setFitWidth(EIGHT_PERCENT * WIDTH);
        displayArrow.setFitWidth(EIGHT_PERCENT * WIDTH);
        
        displayArrow.setTranslateX(ZERO);
        
        displayBow.setPreserveRatio(true);
        displayArrow.setPreserveRatio(true);
        
        extensionSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                displayArrow.setTranslateX(-(extensionSlider.getValue()) * TWO_HUNDRED);
            }
        });
        partIII.getChildren().addAll(displayBow, displayArrow);
        
        partIII.setAlignment(Pos.CENTER);
        
        return partIII;
    }
    
    @Override
    public void createTimeline() {
        mechIITimeline.getKeyFrames().add(new KeyFrame(Duration.millis(ONE_HUNDRED), new EventHandler<ActionEvent>() {
            double xValue = ZERO;
            double yValue = elasticEnergy;
            double frames = elasticEnergy / (((extension * TWO - arrow.getWidth()/TWO_HUNDRED) / arrowVelocity) * TEN);
        
            @Override
            public void handle(ActionEvent actionEvent) {
                mechIISeries.getData().add(new XYChart.Data(xValue, yValue));
                xValue = xValue + frames;
                yValue = yValue - frames;
            }
        }));
        
        mechIITimeline.setCycleCount(Animation.INDEFINITE);
    }
    
    @Override
    public void play()
    {
        finalX = WIDTH/FOUR - arrow.getWidth()/TWO;
        
        arrowTranslation.setDuration(Duration.millis(duration));
        arrowTranslation.setNode(displayArrow);
        
        arrowTranslation.setToX(finalX);
        arrowTranslation.setInterpolator(Interpolator.LINEAR);
        
        arrowTranslation.play();
        mechIITimeline.play();
    }
    
    @Override
    public void pause() {
        arrowTranslation.pause();
        mechIITimeline.pause();
    }
    
    @Override
    public void resume() {
        arrowTranslation.play();
        mechIITimeline.play();
    }
    
    @Override
    public void reset() {
        mechIITimeline.stop();
        mechIITimeline.getKeyFrames().clear();
        mechIISeries.getData().clear();
        
        arrowMassSlider.setDisable(false);
        springConstSlider.setDisable(false);
        extensionSlider.setDisable(false);
        
        arrowTranslation.stop();
        displayArrow.setTranslateX(-(extensionSlider.getValue()) * TWO_HUNDRED);
    }
    
    @Override
    public void help() {
        JOptionPane.showMessageDialog(null, mechanicsIIHelp, helpTitle, JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void remove()
    {
        partI.getChildren().removeAll(startBtn, done, pauseBtn, resetBtn, helpBtn, resumeBtn);
        mechIITimeline.stop();
        arrowTranslation.stop();
        mechIITimeline.getKeyFrames().clear();
        mechIISeries.getData().clear();
        partII.getChildren().remove(mechIIGraph);
        mechIIGraph.getData().remove(mechIISeries);
        partIII.getChildren().removeAll(displayBow, displayArrow);
        partIV.getChildren().removeAll(paneArrowMassSliderII, paneSpringConstSliderII, paneExtensionSliderII);
        paneArrowMassSliderII.getChildren().removeAll(arrowMassLabel, paneArrowMassSliderI);
        paneArrowMassSliderI.getChildren().removeAll(arrowMassSlider, arrowMassValueLabel, mUnit);
        paneSpringConstSliderII.getChildren().removeAll(springConstLabel, paneSpringConstSliderI);
        paneSpringConstSliderI.getChildren().removeAll(springConstSlider, springConstValueLabel, springConstUnit);
        paneExtensionSliderII.getChildren().removeAll(extensionLabel, paneExtensionSliderI);
        paneExtensionSliderI.getChildren().removeAll(extensionSlider, extensionValueLabel, dUnit);
    }
}
