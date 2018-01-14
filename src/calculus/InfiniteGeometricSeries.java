/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculus;

import static calculus.CalculusInterface.*;
import javafx.animation.Animation;
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

/**
 *
 * @author George Lam
 */
public class InfiniteGeometricSeries extends CommonMethods
{
    protected int startTerm;
    protected double commonRatio;
    protected double addition;
    
    @Override
    public GridPane inputMenu()
    {
        sliderStartTerm.setMajorTickUnit(ONE);
        sliderStartTerm.setMinorTickCount(ZERO);
        sliderStartTerm.setValue(ONE);
        sliderStartTerm.setDisable(false);
        sliderStartTerm.setShowTickLabels(true);
        sliderStartTerm.setShowTickMarks(true);
        sliderStartTerm.setSnapToTicks(true);
        
        valueStartTerm.setText(String.format(formatString, sliderStartTerm.getValue()));
        sliderStartTerm.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                valueStartTerm.setText(String.format(formatString, new_val));
                displayCubey.setScaleX(sliderStartTerm.getValue());
                displayCubey.setScaleY(sliderStartTerm.getValue());
            }
        });
        
        comboBoxCommonRatio.setVisibleRowCount(4);
        comboBoxCommonRatio.getSelectionModel().select(stringOneThird);
        comboBoxCommonRatio.getItems().addAll(stringOneThird, stringOneHalf, stringTwo, stringThree);
        comboBoxCommonRatio.setDisable(false);
            
        
        paneStartTermI.getChildren().addAll(sliderStartTerm, valueStartTerm);
        paneStartTermII.getChildren().addAll(textStartTerm, paneStartTermI);
        paneCommonRatio.getChildren().addAll(textCommonRatio, comboBoxCommonRatio);
        
        partIV.setVgap(vGap);
        
        partIV.add(paneStartTermII, ZERO, ZERO, ONE, ONE);
        partIV.add(paneCommonRatio, ZERO, ONE, ONE, ONE);
        partIV.setAlignment(Pos.CENTER);
        return partIV;
    }
    
    @Override
    public GridPane drawGraph()
    {
        termNumberAxis.setLabel(stringTermNumber);
        termValueAxis.setLabel(stringTermValue);
        calIIGraph.setLegendVisible(false);
        calIIGraph.setCreateSymbols(true);
        calIIGraph.getData().add(series);
        partII.add(calIIGraph, ZERO, ZERO, ONE, ONE);
        partII.setAlignment(Pos.CENTER);
        return partII;
    }
    
    @Override
    public GridPane initializeAnimation()
    {
        displayCubey.setScaleX(ONE);
        displayCubey.setScaleY(ONE);
        partIII.add(displayCubey, ZERO, ZERO, ONE, ONE);
        partIII.setAlignment(Pos.CENTER);
        return partIII;
    }
    
    @Override
    public void readInputs()
    {
        startTerm = (int)sliderStartTerm.getValue();
        if(comboBoxCommonRatio.getSelectionModel().getSelectedItem().toString().equals(stringOneThird))
        {
            commonRatio = ONE1/THREE3;
        }
        else if(comboBoxCommonRatio.getSelectionModel().getSelectedItem().toString().equals(stringOneHalf))
        {
            commonRatio = ONE1/TWO2;
        }
        else if(comboBoxCommonRatio.getSelectionModel().getSelectedItem().toString().equals(stringTwo))
        {
            commonRatio = TWO2;
        }
        else if(comboBoxCommonRatio.getSelectionModel().getSelectedItem().toString().equals(stringThree))
        {
            commonRatio = THREE3;
        }
        sliderStartTerm.setDisable(true);
        comboBoxCommonRatio.setDisable(true);
        
    }
    
    @Override
    public void computeVariables()
    {
        addition = startTerm * commonRatio;
    }
    
    @Override
    public void createTimeline()
    {
        termNumberAxis.setAutoRanging(true);
        termValueAxis.setAutoRanging(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(ONE_THOUSAND), new EventHandler<ActionEvent>() {
        int xCord = ZERO;
        double yCord = startTerm;
        double additionFrame = addition;
            @Override
            public void handle(ActionEvent actionEvent) {
                series.getData().add(new XYChart.Data(xCord, yCord));;
                if(((yCord + additionFrame) - (yCord) >= FIVE_E_MINUS_THREE) && (yCord < ((WIDTH * HEIGHT)/ONE_THOUSAND_SIX_HUNDRED)))
                {
                    st.setToX(yCord);
                    st.setToY(yCord);
                    st.play();
                }
                System.out.println(yCord);
                xCord = xCord + ONE;
                yCord = yCord + additionFrame;
                additionFrame = additionFrame*commonRatio;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    @Override
    public void play()
    {
        timeline.play();
    }
    
    @Override
    public void resume()
    {
        play();
    }
    
    @Override
    public void pause()
    {
        timeline.pause();
    }
    
    @Override
    public void reset()
    {
        timeline.stop();
        timeline.getKeyFrames().clear();
        series.getData().clear();
        termNumberAxis.setAutoRanging(false);
        termValueAxis.setAutoRanging(false);
        termNumberAxis.setLowerBound(ZERO);
        termNumberAxis.setUpperBound(ZERO);
        termNumberAxis.setTickUnit(ZERO);
        termValueAxis.setLowerBound(ZERO);
        termValueAxis.setUpperBound(ZERO);
        termValueAxis.setTickUnit(ZERO);
        sliderStartTerm.setDisable(false);
        comboBoxCommonRatio.setDisable(false);
        st.stop();
        displayCubey.setScaleX(sliderStartTerm.getValue());
        displayCubey.setScaleY(sliderStartTerm.getValue());
    }
    
    @Override
    public void help()
    {
        JOptionPane.showMessageDialog(null, cal2Help, helpTitle, JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void remove()
    {
        partI.getChildren().removeAll(startBtn, done, pauseBtn, resetBtn, helpBtn, resumeBtn);
        timeline.stop();
        timeline.getKeyFrames().clear();
        series.getData().clear();
        calIIGraph.getData().clear();
        termNumberAxis.setAutoRanging(false);
        termValueAxis.setAutoRanging(false);
        termNumberAxis.setLowerBound(ZERO);
        termNumberAxis.setUpperBound(ZERO);
        termNumberAxis.setTickUnit(ZERO);
        termValueAxis.setLowerBound(ZERO);
        termValueAxis.setUpperBound(ZERO);
        termValueAxis.setTickUnit(ZERO);
        partII.getChildren().remove(calIIGraph);
        partIII.getChildren().remove(displayCubey);
        comboBoxCommonRatio.getItems().clear();
        paneStartTermI.getChildren().removeAll(sliderStartTerm, valueStartTerm);
        paneStartTermII.getChildren().removeAll(textStartTerm, paneStartTermI);
        paneCommonRatio.getChildren().removeAll(textCommonRatio, comboBoxCommonRatio);
        partIV.getChildren().removeAll(paneStartTermII, paneCommonRatio);
    }  
}
