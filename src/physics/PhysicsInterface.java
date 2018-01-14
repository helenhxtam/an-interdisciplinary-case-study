/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physics;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static main.Numbers.*;

/**
 *
 * @author Helen Tam, Yue Yang
 */
public interface PhysicsInterface 
{
    public final HBox paneForceSliderI = new HBox();
    public final VBox paneForceSliderII = new VBox();
    public final HBox paneVSliderI = new HBox();
    public final VBox paneVSliderII = new VBox();
    public final HBox paneMassSliderI = new HBox();
    public final VBox paneMassSliderII = new VBox();
    
    public final HBox paneArrowMassSliderI = new HBox();
    public final VBox paneArrowMassSliderII = new VBox();
    public final HBox paneSpringConstSliderI = new HBox();
    public final VBox paneSpringConstSliderII = new VBox();
    public final HBox paneExtensionSliderI = new HBox();
    public final VBox paneExtensionSliderII = new VBox();
    
    public final HBox amplitudePane = new HBox();
    public final HBox angularPane = new HBox();
    public final HBox surfacePane = new HBox();
    public final HBox photonPane = new HBox();
    public final VBox photonBox = new VBox();
    public final VBox electronBox = new VBox();
    public final HBox eVValueBox = new HBox();
    
    public final VBox paneMass = new VBox();
    public final StackPane paneAnimationI = new StackPane();
    public final HBox paneAnimationII = new HBox();
    
    final Pane basePane = new Pane();
    
    public final String wavesSeriesIILbl = "EVo Versus Frequency";
    public final String wavesSeriesI = "Position Vs Time";
    public final String timeString = "Time (s)";
    public final String positionString = "Position (m)";
    public final String keString = "Kinetic Energy (J)";
    public final String eeString = "Elastic Energy (J)";
    
    //Graph timelines
    public final Timeline mechITimeline = new Timeline();
    public final Timeline mechIITimeline = new Timeline();
    
    //Transitions
    public final TranslateTransition forceTranslation = new TranslateTransition();
    public final TranslateTransition cartTranslation = new TranslateTransition();
    public final TranslateTransition massTranslation = new TranslateTransition();
    public final TranslateTransition arrowTranslation = new TranslateTransition();
    
    //Sprite animation
    public final SpriteAnimation massSpring = new SpriteAnimation();
    final int INITIAL_X = TWENTY;
    final int INITIAL_Y = FIFTY;
    final int FINAL_X = 550;
    final int FINAL_Y = 220;
    public final ParticlesAnimation particles = new ParticlesAnimation(INITIAL_X, INITIAL_Y, FINAL_X, FINAL_Y);
    
    public final double PI = Math.PI;
    public final double phaseConst = PI/TWO;
    public final Label phaseConstLbl = new Label("Phase Constant: PI/2");
    
    // note that Planck's constant is actually 6.626 *10^(-34), but for the convenience of drawing the graph, the 10^(-34) is removed
    public final double PLANCK_CONSTANT = 6.626;
    final double E = 2.718;

    //Images
    public final Image cart = new Image("images/cart.gif");
    public final ImageView displayCart = new ImageView(cart);
    public final Image force = new Image("images/force.gif");
    public final ImageView displayForce = new ImageView(force);
    public final Image mass = new Image("images/cubey.gif");
    public final ImageView displayMass = new ImageView(mass);
    public final Image bow = new Image("images/bow.png");
    public final ImageView displayBow = new ImageView(bow);
    public final Image arrow = new Image("images/arrow.png");
    public final ImageView displayArrow = new ImageView(arrow);
    
    public final Image ANIMATION_V1 = new Image ("/images/MASSBLOCK.jpg");
    public final Image ANIMATION_V2 = new Image ("/images/MASSBLOCK_2.jpg");
    public final Image ANIMATION_V3 = new Image ("/images/MASSBLOCK_3.jpg");
    final Image MASS = new Image("/images/immobileMASS.jpg");
    final ImageView massView = new ImageView(MASS);
    final Image base = new Image("/images/bg.png");
    final ImageView baseView = new ImageView(); 
    
    //Labels
    public final Label errorMsg = new Label("Photon's energy was insufficient to make any electrons from the metal.");
    public final Label eVTextLbl = new Label("eV (10^(-34)J): ");
    public final Label frequencyLbl = new Label("Frequency (Hz)");
    public final Label amplitudeLbl = new Label("Amplitude (m)");
    public final Label angularLbl = new Label ("Angular Frequency(rads/s):");
    public final Label surfaceLbl = new Label("Metal Surface frequency(Hz): ");
    public final Label photonLbl = new Label("Photon frequency(Hz): ");
    public final Label eVValue = new Label();
            
    public final Label forceLabel = new Label("Force");
    public final Label initialVLabel = new Label("Initial Velocity");
    public final Label massLabel = new Label("Cart mass");
    public final Label arrowMassLabel = new  Label("Arrow mass");
    public final Label springConstLabel = new Label("Spring constant");
    public final Label extensionLabel = new Label("Bow extension");
    public final Label arrowFinalDLabel = new Label("Final distance of arrow");
    
    public final Label forceValueLabel = new Label();
    public final Label initialVValueLabel = new Label();
    public final Label cartMassValueLabel = new Label();   
    public final Label arrowMassValueLabel = new Label();
    public final Label springConstValueLabel = new Label();
    public final Label extensionValueLabel = new Label();
    public final Label arrowFinalDValueLabel = new Label();
    
    public final Label newtonsUnit = new Label("N");
    public final Label vUnit = new Label("m/s");
    public final Label mUnit = new Label("kg");
    public final Label springConstUnit = new Label("N/m");
    public final Label dUnit = new Label("m");
    public final Label arrowFinalDUnit = new Label("m");
    public final Label JoulesLbl = new Label("J");
    
    //Sliders
    public final Slider forceSlider = new Slider(ONE1, EIGHT8, ONE1);
    public final Slider initialVSlider = new Slider(ONE1, FIVE5, ONE1);
    public final Slider cartMassSlider = new Slider(ONE1, SIX6, ONE1);
    public final Slider arrowMassSlider = new Slider(ONE1, FIVE5, ONE1);
    public final Slider springConstSlider = new Slider(ONE1, SIX6, ONE1);
    public final Slider extensionSlider = new Slider(ZERO0, ONE1, ZERO0);
    public final Slider arrowFinalDSlider = new Slider(ONE1, TEN10, ONE1);
    
    public final Slider ampSlider = new Slider (ONE , THREE, ONE);
    public final Slider angularSlider = new Slider(ONE_HALF, PI/THREE, POINT_ONE);
    public final Slider photonSlider = new Slider (FIFTY, THREE_HUNDRED, TWENTY_FIVE);
    public final Slider surfaceSlider = new Slider(ONE_HUNDRED, THREE_HUNDRED, TWENTY_FIVE);
    
    //LineCharts
    public final NumberAxis mechIXAxis = new NumberAxis(ZERO, FIVE_POINT_FIVE, ONE_HALF);
    public final NumberAxis mechIYAxis = new NumberAxis(ZERO, EIGHT, ONE_HALF);
    public final LineChart<Number, Number> mechIGraph = new LineChart<>(mechIXAxis, mechIYAxis);
    public final XYChart.Series mechISeries = new XYChart.Series();
    public final NumberAxis mechIIXAxis = new NumberAxis(ZERO, FIVE, ONE_HALF);
    public final NumberAxis mechIIYAxis = new NumberAxis(ZERO, FIVE, ONE_HALF);
    public final LineChart<Number, Number> mechIIGraph = new LineChart<>(mechIIXAxis, mechIIYAxis);
    public final XYChart.Series mechIISeries = new XYChart.Series();
    
    public final String mechanicsIHelp = "Newton's Second Law enables the user to choose certain values for \n"
                                       + "the force applied on the cart (N), the initial velocity of the cart (m/s), and the mass of the cart (kg).\n\n"
                                       + "By using Newton's Second Law, F = ma, the acceleration is used to find the time for the displacement of the cart in a single direction (Always from Left to Right of the screen, starting at position 0).\n"
                                       + "The graph plots a line that illustrates the Position of the cart as a function of Time, where the time is obtained by the quadratic equation:     x = vt + 1/2at^2.\n\n\n"
                                       + "Instructions: \n"
                                       + "Move the sliders to choose the values.\n"
                                       + "Click 'Start' to start the animations, 'Done' to go back to the main interface, 'Pause' to pause the animations,\n"
                                       + "'Resume' to play the animation from where it is paused, and 'Reset' to reset the animations.";
    public final String mechanicsIIHelp = "Mechanical Energy enables the user to choose certain values for "
                                        + "the mass of the arrow (kg), the spring constant of the string ((N/m), and the extension of the arrow (m).\n\n"
                                        + "The graph plots a line that illustrates the amount of elastic energy in the arrow as it gains kinetic energy (E = PE + KE).\n\n\n"
                                        + "Instructions: \n"
                                        + "Move the sliders to choose the values.\n"
                                        + "Click 'Start' to start the animations, 'Done' to go back to the main interface, 'Pause' to pause the animations,\n"
                                        + "'Resume' to play the animation from where it is paused, and 'Reset' to reset the animations.";
    public final String wavesIHelp = "This application will take into account the angular frequency, phase constant and amplitude \n"
                                   + "to draw the graph of position versus time of the simple harmonic motion. \n"
                                   + "The animation shows a masss oscillating up and down in simple harmonic motion."
                                   + "Amplitude is a value of your choosing between 1 and 3 inclusively. \n"
                                   + "Angular Frequency is a value of your choosing between 0.5 and PI/3 inclusively. \n"
                                   + "Phase Constant is a pre-set value of PI/2. \n";
    public final String wavesIIHelp = "This application will simulate the photoelectric effect. \n"
                                    + "The metal surface frequency is a value of your choosing between 100 to 300. \n"
                                    + "The photon frequency is a value of your choosing between 50 to 300. \n"
                                    + "The graph uses Einstein's photoelectric equation to calculate the kinetic energy, eV \n"
                                    + "The animation shows light photons hitting the surface of a metal. \n"
                                    + "If the metal's frequency is greater or equal to the photon's frequency, \n"
                                    + "no electrons will be dejected from the metal, as the energy was insufficient. \n"
                                    + "Else, electrons will be dejected from the surface of the metal.";
}
