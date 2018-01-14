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
import javafx.scene.layout.*;
import javafx.util.Duration;
import javax.swing.JOptionPane;

import static main.CommonInterface.*;
import main.CommonMethods;
import static main.Numbers.*;

/**
 *
 * @author George Lam
 */
public class NewSportBike extends CommonMethods
{
    protected double unitSold;
    protected double unitProduced;
    protected double price;
    protected double setupCost;
    protected double productionCost;
    protected double costs;
    protected double sales;
    protected double profit;
    protected double noProfit1;
    protected double noProfit2;
    protected double maxProfitPrice;
    protected double maxProfit;
    protected double discriminant;
    protected double sqrtOfDiscriminant;
    
    @Override
    public GridPane inputMenu()
    {
        sliderProductionCost.setMajorTickUnit(TEN);
        sliderProductionCost.setValue(ONE_HUNDRED);
        sliderProductionCost.setShowTickLabels(true);
        sliderProductionCost.setShowTickMarks(true);
        
        sliderUnitProduced.setMajorTickUnit(TEN_THOUSAND);
        sliderUnitProduced.setMinorTickCount(ZERO);
        sliderUnitProduced.setValue(TEN_THOUSAND);
        sliderUnitProduced.setShowTickLabels(true);
        sliderUnitProduced.setShowTickMarks(true);
        sliderUnitProduced.setSnapToTicks(true);
        
        sliderSetupCost.setMajorTickUnit(ONE_HUNDRED_THOUSAND);
        sliderSetupCost.setValue(ONE_HUNDRED_THOUSAND);
        sliderSetupCost.setShowTickLabels(true);
        sliderSetupCost.setShowTickMarks(true);
        sliderSetupCost.setSnapToTicks(true);
        
        sliderPrice.setMajorTickUnit(FIFTY);
        sliderPrice.setValue(ZERO);
        sliderPrice.setShowTickLabels(true);
        sliderPrice.setShowTickMarks(true);
        
        valueProductionCost.setText(String.format(formatString, sliderProductionCost.getValue()));
        sliderProductionCost.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                valueProductionCost.setText(String.format(formatString, new_val));
            }
        });
        
        valueUnitProduced.setText(String.format(formatString, sliderUnitProduced.getValue()));
        sliderUnitProduced.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                valueUnitProduced.setText(String.format(formatString, new_val));
            }
        });
        
        valueSetupCost.setText(String.format(formatString, sliderSetupCost.getValue()));
        sliderSetupCost.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                valueSetupCost.setText(String.format(formatString, new_val));
            }
        });
        
        valuePrice.setText(String.format(formatString, sliderPrice.getValue()));
        sliderPrice.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
                valuePrice.setText(String.format(formatString, new_val));
            }
        });
        
        paneProductionCostI.getChildren().addAll(sliderProductionCost, valueProductionCost, dollarsUnit1);
        paneProductionCostII.getChildren().addAll(textProductionCost, paneProductionCostI);
        paneUnitProducedI.getChildren().addAll(sliderUnitProduced, valueUnitProduced, bikeUnit);
        paneUnitProducedII.getChildren().addAll(textUnitProduced, paneUnitProducedI);
        paneSetupCostI.getChildren().addAll(sliderSetupCost, valueSetupCost, dollarsUnit2);
        paneSetupCostII.getChildren().addAll(textSetupCost, paneSetupCostI);
        panePriceI.getChildren().addAll(sliderPrice, valuePrice, dollarsUnit3);
        panePriceII.getChildren().addAll(textPrice, panePriceI);
        
        partIV.setVgap(vGap);
        
        partIV.add(paneProductionCostII, ZERO, ZERO, ONE, ONE);
        partIV.add(paneUnitProducedII, ZERO, ONE, ONE, ONE);
        partIV.add(paneSetupCostII, ZERO, TWO, ONE, ONE);
        partIV.add(panePriceII, ZERO, THREE, ONE, ONE);
        partIV.setAlignment(Pos.CENTER);
        return partIV;
    }
    
    @Override
    public GridPane drawGraph()
    {
        unitSoldAxis.setLabel(stringUnitSold);
        profitAxis.setLabel(stringProfit);
        calIGraph.setLegendVisible(false);
        calIGraph.setCreateSymbols(false);
        calIGraph.setAnimated(false);
        calIGraph.getData().add(series);
        partII.add(calIGraph, ZERO, ZERO, ONE, ONE);
        partII.setAlignment(Pos.CENTER);
        return partII;
    }
    
    @Override
    public GridPane initializeAnimation()
    {   
        for (int i = ZERO; i < TWO; i++)
        {
            RowConstraints row = new RowConstraints(HEIGHT/FOUR);
            partIII.getRowConstraints().add(row);
        }
        ColumnConstraints col = new ColumnConstraints(WIDTH/TWO);
        partIII.getColumnConstraints().add(col);
        
        paneBike.getChildren().addAll(displayBike1, displayBike2, displayBike3, displayBike4, displayBike5);
        paneBike.setAlignment(Pos.CENTER_LEFT);
        subPaneMoney1.getChildren().addAll(displayShadowMoney1, displayMoney1);
        subPaneMoney2.getChildren().addAll(displayShadowMoney2, displayMoney2);
        subPaneMoney3.getChildren().addAll(displayShadowMoney3, displayMoney3);
        subPaneMoney4.getChildren().addAll(displayShadowMoney4, displayMoney4);
        subPaneMoney5.getChildren().addAll(displayShadowMoney5, displayMoney5);
        paneMoney.add(subPaneMoney1, ZERO, ZERO, ONE, ONE);
        paneMoney.add(subPaneMoney2, ONE, ZERO, ONE, ONE);
        paneMoney.add(subPaneMoney3, TWO, ZERO, ONE, ONE);
        paneMoney.add(subPaneMoney4, THREE, ZERO, ONE, ONE);
        paneMoney.add(subPaneMoney5, FOUR, ZERO, ONE, ONE);
        paneMoney.setAlignment(Pos.CENTER);
        
        displayBike1.setTranslateX(ZERO);
        displayBike2.setTranslateX(ZERO);
        displayBike3.setTranslateX(ZERO);
        displayBike4.setTranslateX(ZERO);
        displayBike5.setTranslateX(ZERO);
        
        displayMoney1.setVisible(false);
        displayMoney2.setVisible(false);
        displayMoney3.setVisible(false);
        displayMoney4.setVisible(false);
        displayMoney5.setVisible(false);
        
        partIII.add(paneMoney, ZERO, ZERO, ONE, ONE);
        partIII.add(paneBike, ZERO, ONE, ONE, ONE);
        return partIII;
    }
    
    @Override
    public void readInputs()
    {
        productionCost = Math.round(sliderProductionCost.getValue());
        unitProduced = Math.round(sliderUnitProduced.getValue());
        setupCost = Math.round(sliderSetupCost.getValue());
        price = Math.round(sliderPrice.getValue()); 
        sliderProductionCost.setDisable(true);
        sliderUnitProduced.setDisable(true);
        sliderSetupCost.setDisable(true);
        sliderPrice.setDisable(true);
    }
    
    @Override
    public void computeVariables()
    {
        unitSold = unitProduced - (TWO_HUNDRED * price);
        costs = setupCost + (productionCost * (unitProduced - (TWO_HUNDRED * price)));
        sales = unitSold * price;
        profit = sales - costs;
        
//      -200P^2 + (unitProduced + 200*productionCost)P - (setupCost + (productionCost * unitProduced)) 
        
        discriminant = ((unitProduced + (TWO_HUNDRED * productionCost))*(unitProduced + (TWO_HUNDRED * productionCost))) - (FOUR*(-TWO_HUNDRED)*(-(setupCost + (productionCost * unitProduced))));
        sqrtOfDiscriminant = Math.sqrt(discriminant);
        noProfit1 = (-(unitProduced + (TWO_HUNDRED * productionCost)) - sqrtOfDiscriminant)/(-TWO*TWO_HUNDRED);
        noProfit2 = (-(unitProduced + (TWO_HUNDRED * productionCost)) + sqrtOfDiscriminant)/(-TWO*TWO_HUNDRED);
        maxProfitPrice = (noProfit1 + noProfit2)/TWO;
        maxProfit = ((unitProduced - (TWO_HUNDRED * maxProfitPrice)) * maxProfitPrice) - (setupCost + (productionCost * (unitProduced - (TWO_HUNDRED * maxProfitPrice))));
    }
    
    @Override
    public void createTimeline()
    {
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
        double xCord = ZERO;
        double yCord = ZERO;
        double unitSoldFrame = SEVENTY_THOUSAND/ONE_HUNDRED;
        double profitFrame = THREE_MILLION_TWENTY_FIVE_THOUSAND/ONE_HUNDRED;
            @Override
            public void handle(ActionEvent actionEvent) {
                series.getData().add(new XYChart.Data(xCord, yCord));
                if(unitSold != ZERO && (xCord < unitSold || yCord < profit))
                {
                    if(xCord >= unitProduced/FIVE && xCord < (TWO*unitProduced)/FIVE)
                    {
                        tt1.setToX((WIDTH/TWO) - bike.getWidth());
                        tt1.play();
                    }
                    else if(xCord >= (TWO*unitProduced)/FIVE && xCord < (THREE*unitProduced)/FIVE)
                    {
                        tt2.setToX((WIDTH/TWO) - bike.getWidth());
                        tt2.play();
                    }
                    else if(xCord >= (THREE*unitProduced)/FIVE && xCord < (FOUR*unitProduced)/FIVE)
                    {
                        tt3.setToX((WIDTH/TWO) - bike.getWidth());
                        tt3.play();
                    }
                    else if(xCord >= (FOUR*unitProduced)/FIVE && xCord < unitProduced)
                    {
                        tt4.setToX((WIDTH/TWO) - bike.getWidth());
                        tt4.play();
                    }
                    
                    
                    if(profit > ZERO)
                    {
                        if(yCord >= maxProfit/FIVE)
                        {
                            displayMoney1.setVisible(true);
                        }
                        if(yCord >= (TWO*maxProfit)/FIVE)
                        {
                            displayMoney2.setVisible(true);
                        }
                        if(yCord >= (THREE*maxProfit)/FIVE)
                        {
                            displayMoney3.setVisible(true);
                        }
                        if(yCord >= (FOUR*maxProfit)/FIVE)
                        {
                            displayMoney4.setVisible(true);
                        }
                        
                        xCord = xCord + unitSoldFrame;
                        yCord = yCord + profitFrame;
                    }
                    else if(profit <= ZERO)
                    {
                        xCord = xCord + unitSoldFrame;
                        yCord = ZERO;
                    }
                            
                }
                else if(unitSold == ZERO || xCord >= unitSold || yCord >= profit)
                {
                    if(xCord >= unitProduced)
                    {
                        tt5.setToX((WIDTH/TWO) - bike.getWidth());
                        tt5.play();
                    }
                    if(yCord >= maxProfit && profit > ZERO)
                    {
                        displayMoney5.setVisible(true);
                    }
                    timeline.stop();
                    pauseBtn.setDisable(true);
                    resumeBtn.setDisable(true);
                }
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
        sliderProductionCost.setDisable(false);
        sliderUnitProduced.setDisable(false);
        sliderSetupCost.setDisable(false);
        sliderPrice.setDisable(false);
        tt1.stop();
        tt2.stop();
        tt3.stop();
        tt4.stop();
        tt5.stop();
        displayBike1.setTranslateX(ZERO);
        displayBike2.setTranslateX(ZERO);
        displayBike3.setTranslateX(ZERO);
        displayBike4.setTranslateX(ZERO);
        displayBike5.setTranslateX(ZERO);
        displayMoney1.setVisible(false);
        displayMoney2.setVisible(false);
        displayMoney3.setVisible(false);
        displayMoney4.setVisible(false);
        displayMoney5.setVisible(false);
    }
    
    @Override
    public void help()
    {
        JOptionPane.showMessageDialog(null, cal1Help, helpTitle, JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void remove()
    {
        partI.getChildren().removeAll(startBtn, done, pauseBtn, resetBtn, helpBtn, resumeBtn);
        timeline.stop();
        timeline.getKeyFrames().clear();
        series.getData().clear();
        calIGraph.getData().remove(series);
        partII.getChildren().remove(calIGraph);
        paneBike.getChildren().removeAll(displayBike1, displayBike2, displayBike3, displayBike4, displayBike5);
        subPaneMoney1.getChildren().removeAll(displayShadowMoney1, displayMoney1);
        subPaneMoney2.getChildren().removeAll(displayShadowMoney2, displayMoney2);
        subPaneMoney3.getChildren().removeAll(displayShadowMoney3, displayMoney3);
        subPaneMoney4.getChildren().removeAll(displayShadowMoney4, displayMoney4);
        subPaneMoney5.getChildren().removeAll(displayShadowMoney5, displayMoney5);
        paneMoney.getChildren().removeAll(subPaneMoney1, subPaneMoney2, subPaneMoney3, subPaneMoney4, subPaneMoney5);
        partIII.getColumnConstraints().clear();
        partIII.getRowConstraints().clear();
        partIII.getChildren().removeAll(paneBike, paneMoney);
        partIV.getChildren().removeAll(paneProductionCostII, paneUnitProducedII, paneSetupCostII, panePriceII);
        paneProductionCostII.getChildren().removeAll(textProductionCost, paneProductionCostI);
        paneProductionCostI.getChildren().removeAll(sliderProductionCost, valueProductionCost, dollarsUnit1);
        paneUnitProducedII.getChildren().removeAll(textUnitProduced, paneUnitProducedI);
        paneUnitProducedI.getChildren().removeAll(sliderUnitProduced, valueUnitProduced, bikeUnit);
        paneSetupCostII.getChildren().removeAll(textSetupCost, paneSetupCostI);
        paneSetupCostI.getChildren().removeAll(sliderSetupCost, valueSetupCost, dollarsUnit2);
        panePriceII.getChildren().removeAll(textPrice, panePriceI);
        panePriceI.getChildren().removeAll(sliderPrice, valuePrice, dollarsUnit3);
    }
}
