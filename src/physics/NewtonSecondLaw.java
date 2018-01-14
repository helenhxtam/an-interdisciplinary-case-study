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
public class NewtonSecondLaw extends CommonMethods
{
    protected double forceNum;
    protected double initialVNum;
    protected double cartMassNum;
    protected double acceleration;
    protected double distance;
    protected double discriminant;
    protected double sqrtOfDiscriminant;
    protected double time;
    
    protected double finalX;
    
    @Override
    public GridPane inputMenu()
    {
        forceSlider.setMajorTickUnit(ONE);
        forceSlider.setMinorTickCount(ZERO);
        forceSlider.setValue(ONE1);
        forceSlider.setDisable(false);
        forceSlider.setShowTickLabels(true);
        forceSlider.setShowTickMarks(true);
        forceSlider.setSnapToTicks(true);
        
        initialVSlider.setMajorTickUnit(ONE);
        initialVSlider.setValue(ONE1);
        initialVSlider.setDisable(false);
        initialVSlider.setShowTickLabels(true);
        initialVSlider.setShowTickMarks(true);
        
        cartMassSlider.setMajorTickUnit(ONE);
        cartMassSlider.setMinorTickCount(ZERO);
        cartMassSlider.setValue(ONE1);
        cartMassSlider.setDisable(false);
        cartMassSlider.setShowTickLabels(true);
        cartMassSlider.setShowTickMarks(true);
        cartMassSlider.setSnapToTicks(true);
        
        forceValueLabel.setText(Double.toString(forceSlider.getValue()));
        forceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                forceValueLabel.setText(String.format(formatStringD, new_val));
            }
        });
        initialVValueLabel.setText(Double.toString(initialVSlider.getValue()));
        initialVSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                initialVValueLabel.setText(String.format(formatStringD, new_val));
            }
        });
        
        cartMassValueLabel.setText(Double.toString(cartMassSlider.getValue()));
        cartMassSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                cartMassValueLabel.setText(String.format(formatStringD, new_val));
            }
        });
        
        paneForceSliderI.getChildren().addAll(forceSlider, forceValueLabel, newtonsUnit);
        paneForceSliderII.getChildren().addAll(forceLabel, paneForceSliderI);
        
        paneVSliderI.getChildren().addAll(initialVSlider, initialVValueLabel, vUnit);
        paneVSliderII.getChildren().addAll(initialVLabel, paneVSliderI);
        
        paneMassSliderI.getChildren().addAll(cartMassSlider, cartMassValueLabel, mUnit);
        paneMassSliderII.getChildren().addAll(massLabel, paneMassSliderI);
        
        partIV.setVgap(vGap);
        
        partIV.add(paneForceSliderII, ZERO, ZERO, ONE, ONE);
        partIV.add(paneVSliderII, ZERO, ONE, ONE, ONE);
        partIV.add(paneMassSliderII, ZERO, TWO, ONE, ONE);
        partIV.setAlignment(Pos.CENTER);
        
        return partIV;
    }
    
    @Override
    public void readInputs()
    {
        forceNum = forceSlider.getValue();
        initialVNum = initialVSlider.getValue();
        cartMassNum = cartMassSlider.getValue();
    }
    
    @Override
    public void computeVariables()
    {
        acceleration = forceNum / cartMassNum;
        distance = (WIDTH/TWO - displayCart.getFitWidth()/TWO - displayForce.getFitWidth()/TWO) / ONE_HUNDRED;
        discriminant = (initialVNum * initialVNum - TWO * acceleration * (-(distance)));
        sqrtOfDiscriminant = Math.sqrt(discriminant);
        time = (-initialVNum - sqrtOfDiscriminant)/acceleration;
        if (time < ZERO)
        {
            time = (-initialVNum + sqrtOfDiscriminant)/acceleration;
        }
    }
    
    @Override
    public GridPane drawGraph() {
        mechIXAxis.setLabel(timeString);
        mechIYAxis.setLabel(positionString);
        
        mechIXAxis.setAutoRanging(false);
        mechIYAxis.setAutoRanging(false);
        
        mechIGraph.setLegendVisible(false);
        mechIGraph.setCreateSymbols(false);
        mechIGraph.setAnimated(false);
        mechIGraph.getData().add(mechISeries);
        
        partII.getChildren().add(mechIGraph);
        partII.setAlignment(Pos.CENTER);
        
        return partII;
    }
    
    @Override
    public GridPane initializeAnimation()
    {
        displayForce.setFitWidth(EIGHT_PERCENT * WIDTH);
        displayCart.setFitWidth(EIGHT_PERCENT * WIDTH);
        
        displayForce.setScaleX(ONE_EIGHTH);
        displayForce.setScaleY(ONE_EIGHTH);
        
        displayMass.setScaleX(ONE_FOURTH);
        displayMass.setScaleY(ONE_FOURTH);
        
        displayForce.setTranslateX(ZERO);
        displayCart.setTranslateX(ZERO);
        displayMass.setTranslateX(ZERO);
        
        displayForce.setPreserveRatio(true);
        displayCart.setPreserveRatio(true);
        displayMass.setPreserveRatio(true);
        
        forceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                displayForce.setScaleX(ONE_EIGHTH * forceSlider.getValue());
                displayForce.setScaleY(ONE_EIGHTH * forceSlider.getValue());
            }
        });
        
        cartMassSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                displayMass.setScaleX(cartMassSlider.getValue()/FOUR);
                displayMass.setScaleY(cartMassSlider.getValue()/FOUR);
            }
        });
        
        paneMass.getChildren().add(displayMass);
        paneMass.setTranslateY(-20);
        paneMass.setAlignment(Pos.TOP_CENTER);
        paneAnimationI.getChildren().addAll(paneMass, displayCart);
        paneAnimationII.getChildren().addAll(displayForce, paneAnimationI);
        paneAnimationI.setAlignment(Pos.CENTER);

        partIII.getChildren().addAll(paneAnimationII);
        partIII.setAlignment(Pos.CENTER_LEFT);
        
        return partIII;
    }
    
    @Override
    public void createTimeline() {
        mechITimeline.getKeyFrames().add(new KeyFrame(Duration.millis(ONE_HUNDRED), new EventHandler<ActionEvent>() {
            double xValue = ZERO;
            double yValue = ZERO;
            double frames = distance / (time * TEN);

            @Override
            public void handle(ActionEvent actionEvent) {
                mechISeries.getData().add(new XYChart.Data(xValue, yValue));
                if (yValue >= distance) {
                    mechITimeline.stop();
                }
                
                xValue = xValue + POINT_ONE;
                yValue = yValue + frames;
            }
        }));
        
        mechITimeline.setCycleCount(Animation.INDEFINITE);
    }
    
    @Override
    public void play() {
        forceSlider.setDisable(true);
        initialVSlider.setDisable(true);
        cartMassSlider.setDisable(true);
        
        finalX = WIDTH/TWO - displayCart.getFitWidth() - displayForce.getFitWidth();
        
        forceTranslation.setDuration(Duration.millis(time * ONE_THOUSAND));
        forceTranslation.setNode(displayForce);
        
        cartTranslation.setDuration(Duration.millis(time * ONE_THOUSAND));
        cartTranslation.setNode(displayCart);
        
        massTranslation.setDuration(Duration.millis(time * ONE_THOUSAND));
        massTranslation.setNode(displayMass);
        
        forceTranslation.setToX(finalX);
        forceTranslation.setInterpolator(Interpolator.LINEAR);
        
        cartTranslation.setToX(finalX);
        cartTranslation.setInterpolator(Interpolator.LINEAR);
        
        massTranslation.setToX(finalX);
        massTranslation.setInterpolator(Interpolator.LINEAR);
        
        forceTranslation.play();
        cartTranslation.play();
        massTranslation.play();
        mechITimeline.play();
    }
    
    @Override
    public void pause() {
        forceTranslation.pause();
        cartTranslation.pause();
        massTranslation.pause();
        mechITimeline.pause();
    }

    @Override
    public void resume() {
        forceTranslation.play();
        cartTranslation.play();
        massTranslation.play();
        mechITimeline.play();
    }
    
    @Override
    public void reset() {
        mechITimeline.stop();
        mechITimeline.getKeyFrames().clear();
        mechISeries.getData().clear();
        
        forceSlider.setDisable(false);
        initialVSlider.setDisable(false);
        cartMassSlider.setDisable(false);
        
        forceTranslation.stop();
        cartTranslation.stop();
        massTranslation.stop();
        
        displayForce.setTranslateX(ZERO);
        displayCart.setTranslateX(ZERO);
        displayMass.setTranslateX(ZERO);
    }
    
    @Override
    public void help() {
        JOptionPane.showMessageDialog(null, mechanicsIHelp, helpTitle, JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void remove()
    {
        partI.getChildren().removeAll(startBtn, done, pauseBtn, resetBtn, helpBtn, resumeBtn);
        mechITimeline.stop();
        forceTranslation.stop();
        cartTranslation.stop();
        massTranslation.stop();
        mechITimeline.getKeyFrames().clear();
        mechISeries.getData().clear();
        mechIGraph.getData().remove(mechISeries);
        partII.getChildren().remove(mechIGraph);
        partIII.getChildren().remove(paneAnimationII);
        paneAnimationII.getChildren().removeAll(displayForce, paneAnimationI);
        paneAnimationI.getChildren().removeAll(paneMass, displayCart);
        paneMass.getChildren().remove(displayMass);
        partIV.getChildren().removeAll(paneForceSliderII, paneVSliderII, paneMassSliderII);
        paneForceSliderII.getChildren().removeAll(forceLabel, paneForceSliderI);
        paneForceSliderI.getChildren().removeAll(forceSlider, forceValueLabel, newtonsUnit);
        paneVSliderII.getChildren().removeAll(initialVLabel, paneVSliderI);
        paneVSliderI.getChildren().removeAll(initialVSlider, initialVValueLabel, vUnit);
        paneMassSliderII.getChildren().removeAll(massLabel, paneMassSliderI);
        paneMassSliderI.getChildren().removeAll(cartMassSlider, cartMassValueLabel, mUnit);
    }
}