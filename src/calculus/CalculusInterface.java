/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculus;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import static main.Numbers.*;

/**
 *
 * @author George Lam
 */
public interface CalculusInterface 
{
    public final HBox paneProductionCostI = new HBox();
    public final VBox paneProductionCostII = new VBox();
    public final HBox paneUnitProducedI = new HBox();
    public final VBox paneUnitProducedII = new VBox();
    public final HBox paneSetupCostI = new HBox();
    public final VBox paneSetupCostII = new VBox();
    public final HBox panePriceI = new HBox();
    public final VBox panePriceII = new VBox();
    public final HBox paneStartTermI = new HBox();
    public final VBox paneStartTermII = new VBox();
    public final VBox paneCommonRatio = new VBox();
    public final GridPane paneBike = new GridPane();
    public final GridPane paneMoney = new GridPane();
    public final GridPane subPaneMoney1 = new GridPane();
    public final GridPane subPaneMoney2 = new GridPane();
    public final GridPane subPaneMoney3 = new GridPane();
    public final GridPane subPaneMoney4 = new GridPane();
    public final GridPane subPaneMoney5 = new GridPane();
    public final GridPane groupPaneMoney = new GridPane();
    public final GridPane groupPaneBike = new GridPane();
    
    public final Label textProductionCost = new Label("Production cost");
    public final Label textUnitProduced = new Label("Unit produced");
    public final Label textSetupCost = new Label("Set-up cost");
    public final Label textPrice = new Label("Price");
    public final Label textStartTerm = new Label("Start term (a)");
    public final Label textCommonRatio = new Label("Common ratio (r)");
    
    public final Label valueProductionCost = new Label();
    public final Label valueUnitProduced = new Label();
    public final Label valueSetupCost = new Label();
    public final Label valuePrice = new Label();
    public final Label valueStartTerm = new Label();
    public final Label valueCommonRatio = new Label();
    
    public final Label dollarsUnit1 =  new Label(" $");
    public final Label dollarsUnit2 =  new Label(" $");
    public final Label dollarsUnit3 =  new Label(" $");
    public final Label bikeUnit = new Label(" units"); 
    
    public final String stringUnitSold = "Units sold";
    public final String stringProfit = "Profit ($)";
    public final String stringTermNumber = "Term number";
    public final String stringTermValue = "Term value";
    
    public final String stringOneThird = "1/3";
    public final String stringOneHalf = "1/2";
    public final String stringTwo = "2";
    public final String stringThree = "3";
    
    public final Slider sliderProductionCost = new Slider(100, 150, 100);
    public final Slider sliderUnitProduced = new Slider(TEN_THOUSAND, SEVENTY_THOUSAND, TEN_THOUSAND);
    public final Slider sliderSetupCost = new Slider(ONE_HUNDRED_THOUSAND, SEVEN_HUNDRED_THOUSAND, ONE_HUNDRED_THOUSAND);
    public final Slider sliderPrice = new Slider(ZERO, THREE_HUNDRED_FIFTY, ZERO);
    public final Slider sliderStartTerm = new Slider(ONE, FOUR, ONE);
    
    public final ComboBox comboBoxCommonRatio = new ComboBox();
    
    public final NumberAxis unitSoldAxis = new NumberAxis(ZERO, SEVENTY_THOUSAND, TEN_THOUSAND);
    public final NumberAxis profitAxis = new NumberAxis(ZERO, THREE_MILLION_FIVE_HUNDRED_THOUSAND, FIVE_HUNDRED_THOUSAND);
    public final NumberAxis termNumberAxis = new NumberAxis(ZERO, ZERO, ZERO);
    public final NumberAxis termValueAxis = new NumberAxis(ZERO, ZERO, ZERO);
    
    public final LineChart calIGraph = new LineChart(unitSoldAxis, profitAxis);
    public final LineChart calIIGraph = new LineChart(termNumberAxis, termValueAxis);
    
    public final Image bike = new Image("images/bike.gif");
    public final Image money = new Image("images/money.gif");
    public final Image shadowMoney = new Image("images/bMoney.gif");
    public final Image cubey = new Image("images/cubey.gif");
    public final ImageView displayBike1 = new ImageView(bike);
    public final ImageView displayBike2 = new ImageView(bike);
    public final ImageView displayBike3 = new ImageView(bike);
    public final ImageView displayBike4 = new ImageView(bike);
    public final ImageView displayBike5 = new ImageView(bike);
    public final ImageView displayMoney1 = new ImageView(money);
    public final ImageView displayMoney2 = new ImageView(money);
    public final ImageView displayMoney3 = new ImageView(money);
    public final ImageView displayMoney4 = new ImageView(money);
    public final ImageView displayMoney5 = new ImageView(money);
    public final ImageView displayShadowMoney1 = new ImageView(shadowMoney);
    public final ImageView displayShadowMoney2 = new ImageView(shadowMoney);
    public final ImageView displayShadowMoney3 = new ImageView(shadowMoney);
    public final ImageView displayShadowMoney4 = new ImageView(shadowMoney);
    public final ImageView displayShadowMoney5 = new ImageView(shadowMoney);
    public final ImageView displayCubey = new ImageView(cubey);
    
    public final Timeline timeline = new Timeline();
    public final XYChart.Series series = new XYChart.Series();
    
    public final ScaleTransition st = new ScaleTransition(Duration.millis(FIVE_HUNDRED), displayCubey);
    public final TranslateTransition tt1 = new TranslateTransition(Duration.millis(ONE_THOUSAND), displayBike1);
    public final TranslateTransition tt2 = new TranslateTransition(Duration.millis(ONE_THOUSAND), displayBike2);
    public final TranslateTransition tt3 = new TranslateTransition(Duration.millis(ONE_THOUSAND), displayBike3);
    public final TranslateTransition tt4 = new TranslateTransition(Duration.millis(ONE_THOUSAND), displayBike4);
    public final TranslateTransition tt5 = new TranslateTransition(Duration.millis(ONE_THOUSAND), displayBike5);
    
    public final String cal1Help = "NEW SPORTS BIKE\n\n" + 
                                   "The new sports bike application allows you to experiment with a quadractic equation\n" +
                                   "Move the sliders to change the values of production cost, units produced, setup cost and price" +
                                   "These inputs will be used to compute the following values:\n\n" +
                                   "units sold = units produced - (200 * price)\n" +
                                   "costs = setup cost + (production cost * (units sold))\n" + 
                                   "sales = unit sold * price\n" +
                                   "profit = sales - costs\n\n" +
                                   "The price that will bring the max amount of profit can be found by isolating the price from the following quadratic equation:\n" +
                                   "-200PRICE^2 + (units produced + 200 * production cost)PRICE - (setup cost + (produced cost * unit produced))\n\n" +
                                   "The bikes in the animation will translate to the right if 20% of your units produced have been sold\n" +
                                   "If all five bikes have translated to the right, then you have effectively sold all of your units produced\n" +
                                   "Money bags will appear on the top of the animation if 20% of your max profit has been reached\n" +
                                   "If all five money bags have appeared, then you have effectively set your price to the best price";
    public final String cal2Help = "INFINITE GEOMETRIC SERIES\n\n" +
                                   "The infinite geometric series application allows you to experiment with an infinite geometric series\n" +
                                   "Move the slider and select an item from the combo box to change the values of the start term (a) and common ratio (r)\n" +
                                   "These two inputs will be used to compute the following infinite geometric series:\n\n" +
                                   "S = a + ar + ar^2 + ar^3 + ... + ar^n = (1 - r^n)/(1 - r)     (where n tends to infinity)\n\n" +
                                   "If the common ratio (r) is a value between 0 and 1, then the infinite geometric series will converge to a value\n" +
                                   "If the common ratio (r) is a value larger than 1, then the infinite geometric series will be divergent and approach infinity\n\n" +
                                   "By selecting a common ratio (r) smaller than 1, the cube will grow and eventually converge to a value\n" +
                                   "However, if a common ratio (r) larger than 1 is selected, the cube will grow infinitely and take over the interface until the reset or done button is clicked";
    
}
