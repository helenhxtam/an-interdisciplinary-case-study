/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static main.CommonInterface.WIDTH;

import static main.Numbers.*;

/**
 *
 * @author yue Yang
 */
public final class ParticlesAnimation{
    
    private List<Circle> photonParticles = new ArrayList<Circle>();
    private List<Circle> electronParticles = new ArrayList<Circle>();
    
    private List<TranslateTransition> translatePhoton = new ArrayList<TranslateTransition>();
    private List<TranslateTransition> translateElectron= new ArrayList<TranslateTransition>();
    
    private ParallelTransition parallelPhotons = new ParallelTransition();
    private ParallelTransition parallelElectrons = new ParallelTransition();
    
    Image base = new Image("/images/bg.png");
    ImageView imgView = new ImageView();
    
    GridPane particlePane = new GridPane();
    GridPane particlePane2 = new GridPane();
    Pane animationPane = new Pane();
    
    private final int initialX;
    private final int initialY;
    private final int finalX;
    private final int finalY;
    private int velocity;
    private boolean electronDejected;

   public ParticlesAnimation(int initialX, int initialY, int finalX, int finalY){
        this.initialX = initialX;
        this.initialY = initialY;
        this.finalX = finalX;
        this.finalY = finalY;
        velocity = ZERO;
        electronDejected = false;
        imgView.setImage(base);

    }
   
    public void play(double metalf, double photonf){ 
        populateParticles();
        translateParticle();
        int duration = velocity/TWO + FIVE_HUNDRED;
        
        for(int i = ZERO; i < SIX; i++){
            particlePane.add(photonParticles.get(i), ZERO, i);
        }
        if(isElectronDejected(metalf, photonf)){
            for(int i = ZERO; i < SIX; i++){
                particlePane2.add(electronParticles.get(i), ZERO, i);
             }
            parallelElectrons.setDelay(Duration.millis(duration));
            parallelElectrons.setCycleCount(Animation.INDEFINITE);
            parallelElectrons.play();
            animationPane.getChildren().addAll(particlePane, particlePane2, imgView);
        }
        
        else{
            JOptionPane.showMessageDialog(null, "No electrons were dejected", "Message", JOptionPane.INFORMATION_MESSAGE);
            animationPane.getChildren().addAll(particlePane, imgView);
        }
        
        parallelPhotons.setCycleCount(Animation.INDEFINITE);
        parallelPhotons.play();
       
    }
    
    public Pane getPane(){
        return animationPane;
    }
    
    public void setVelocity(double vel){       
        velocity = (int)vel;
    }
    
    public boolean isElectronDejected(double metalf, double photonf){
        if(photonf > metalf){
           electronDejected = true; 
        }
        else electronDejected = false;
        
        return electronDejected;
    }
    
    public void pause(){
        parallelPhotons.pause();
        parallelElectrons.pause();
    }
    
    public void resume(){
        parallelPhotons.play();
        parallelElectrons.play();
    }
    
    public void populateParticles(){
        for(int i = ZERO; i < SIX; i++){
            photonParticles.add(new Circle(TEN, Color.BLUE));
            electronParticles.add(new Circle(SEVEN, Color.YELLOW));
        }
    }
    
    public void remove(){
        parallelElectrons.stop();
        parallelPhotons.stop();
        animationPane.getChildren().clear();
        particlePane.getChildren().clear();
        particlePane2.getChildren().clear();
        parallelPhotons.getChildren().clear();
        parallelElectrons.getChildren().clear();
    }
    
    public void translateParticle(){
        int delay = ZERO;
        Random random = new Random();
        for(int i = ZERO; i < SIX; i++){
            translatePhoton.add(new TranslateTransition(Duration.millis(velocity), photonParticles.get(i)));
            translatePhoton.get(i).setFromX(initialX);
            translatePhoton.get(i).setFromY(initialY);
            translatePhoton.get(i).setToX(finalX);
            translatePhoton.get(i).setToY(finalY);
            translatePhoton.get(i).setDelay(Duration.millis(delay));
            
            translateElectron.add(new TranslateTransition(Duration.millis(velocity), electronParticles.get(i)));
            translateElectron.get(i).setFromX(finalX);
            translateElectron.get(i).setFromY(finalY);
            translateElectron.get(i).setToX(-300);
            translateElectron.get(i).setDelay(Duration.millis(delay));
                
            parallelPhotons.getChildren().add(translatePhoton.get(i));
            parallelElectrons.getChildren().add(translateElectron.get(i));
            delay = delay + random.nextInt(FOUR_HUNDRED)+ONE_HUNDRED;
        }
    }
     
}