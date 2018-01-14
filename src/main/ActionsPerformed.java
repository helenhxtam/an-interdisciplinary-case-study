/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static main.CommonInterface.*;
import static main.Numbers.*;
import physics.MechanicalEnergy;
import physics.NewtonSecondLaw;
import physics.SimpleHarmonicMotion;
import physics.PhotoelectricEffect;
import calculus.InfiniteGeometricSeries;
import calculus.NewSportBike;


/**
 *
 * @authors George Lam, Helen Tam
 */
public class ActionsPerformed implements EventHandler<ActionEvent>
{
    protected int choiceSelected = ZERO;
    
    @Override
    public void handle(ActionEvent event)
    {
        Object source = event.getSource();
        CommonMethods mechanicsI = new NewtonSecondLaw();
        CommonMethods mechanicsII = new MechanicalEnergy();
        SimpleHarmonicMotion wavesI = new SimpleHarmonicMotion();
        CommonMethods wavesII = new PhotoelectricEffect();
        CommonMethods calculusI = new NewSportBike();
        CommonMethods calculusII = new InfiniteGeometricSeries();
        
        if(source == mechanicsItemI)
        {
            center.getChildren().removeAll(partI, partII, partIII, partIV);
            mechanicsI.remove();
            
            startBtn.setDisable(false);
            pauseBtn.setDisable(true);
            resumeBtn.setDisable(true);
            
            if(choiceSelected == TWO)
            {
                mechanicsII.remove();
            }
            else if(choiceSelected == THREE)
            {
                wavesI.remove();
            }
            else if(choiceSelected == FOUR)
            {
                wavesII.remove();
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.remove();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.remove();
            }
            choiceSelected = ONE;
            
            center.add(mechanicsI.controlMenu(), ZERO, ZERO, ONE, ONE);
            center.add(mechanicsI.drawGraph(), ONE, ZERO, ONE, ONE);
            center.add(mechanicsI.initializeAnimation(), ZERO, ONE, ONE, ONE);
            center.add(mechanicsI.inputMenu(), ONE, ONE, ONE, ONE);
            
        }
        else if(source == mechanicsItemII)
        {
            center.getChildren().removeAll(partI, partII, partIII, partIV);
            mechanicsII.remove();
            
            startBtn.setDisable(false);
            pauseBtn.setDisable(true);
            resumeBtn.setDisable(true);
            
            if(choiceSelected == ONE)
            {
                mechanicsI.remove();
            }
            else if(choiceSelected == THREE)
            {
                wavesI.remove();
            }
            else if(choiceSelected == FOUR)
            {
                wavesII.remove();
            }
            else if (choiceSelected == FIVE)
            {
                calculusI.remove();
                center.getChildren().removeAll(partI, partII, partIII, partIV);
            }
            else if(choiceSelected == SIX)
            {
                calculusII.remove();
            }
            
            choiceSelected = TWO;
            center.add(mechanicsII.controlMenu(), ZERO, ZERO, ONE, ONE);
            center.add(mechanicsII.drawGraph(), ONE, ZERO, ONE, ONE);
            center.add(mechanicsII.initializeAnimation(), ZERO, ONE, ONE, ONE);
            center.add(mechanicsII.inputMenu(), ONE, ONE, ONE, ONE);
        }
        else if(source == wavesItemI)
        {
            center.getChildren().removeAll(partI, partII, partIII, partIV);
            wavesI.remove();
            
            startBtn.setDisable(false);
            pauseBtn.setDisable(true);
            resumeBtn.setDisable(true);
            
            if(choiceSelected == ONE)
            {
                mechanicsI.remove();
            }
            else if(choiceSelected == TWO)
            {
                mechanicsII.remove();
            }
            else if(choiceSelected == FOUR)
            {
                wavesII.remove();
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.remove();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.remove();
            }
            
            choiceSelected = THREE;
            center.add(wavesI.controlMenu(), ZERO, ZERO, ONE, ONE);
            center.add(wavesI.drawGraph(), ONE, ZERO, ONE, ONE);
            center.add(wavesI.initializeAnimation(), ZERO, ONE, ONE, ONE);
            center.add(wavesI.inputMenu(), ONE, ONE, ONE, ONE);
        }
        else if(source == wavesItemII)
        {
            center.getChildren().removeAll(partI, partII, partIII, partIV);
            wavesII.remove();
            
            startBtn.setDisable(false);
            pauseBtn.setDisable(true);
            resumeBtn.setDisable(true);

            if(choiceSelected == ONE)
            {
                mechanicsI.remove();
            }
            else if(choiceSelected == TWO)
            {
                mechanicsII.remove();
            }
            else if(choiceSelected == THREE)
            {
                wavesI.remove();
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.remove();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.remove(); 
            }
            choiceSelected = FOUR;
            center.add(wavesII.controlMenu(), ZERO, ZERO, ONE,ONE );
            center.add(wavesII.drawGraph(), ONE, ZERO, ONE, ONE);
            center.add(wavesII.initializeAnimation(), ZERO, ONE, ONE, ONE);
            center.add(wavesII.inputMenu(), ONE, ONE, ONE, ONE);
            
        }
        else if(source == calculusItemI)
        {
            center.getChildren().removeAll(partI, partII, partIII, partIV);
            calculusI.remove();
            
            startBtn.setDisable(false);
            pauseBtn.setDisable(true);
            resumeBtn.setDisable(true);
            

            if(choiceSelected == ONE)
            {
                mechanicsI.remove();
            }
            else if(choiceSelected == TWO)
            {
                mechanicsII.remove();
            }
            else if(choiceSelected == THREE)
            {
                wavesI.remove();
            }
            else if(choiceSelected == FOUR)
            {
                wavesII.remove();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.remove();
            }
            choiceSelected = FIVE;
            center.add(calculusI.initializeAnimation(), ZERO, ONE, ONE, ONE);
            center.add(calculusI.controlMenu(), ZERO, ZERO, ONE, ONE);
            center.add(calculusI.drawGraph(), ONE, ZERO, ONE, ONE);
            center.add(calculusI.inputMenu(), ONE, ONE, ONE, ONE);
            
        }
    
        else if(source == calculusItemII)
        {
            center.getChildren().removeAll(partI, partII, partIII, partIV);
            calculusII.remove();
            startBtn.setDisable(false);
            pauseBtn.setDisable(true);
            resumeBtn.setDisable(true);
            
            if(choiceSelected == ONE)
            {
                mechanicsI.remove();
            }
            else if(choiceSelected == TWO)
            {
                mechanicsII.remove();
            }
            else if(choiceSelected == THREE)
            {
                wavesI.remove();
            }
            else if(choiceSelected == FOUR)
            {
                wavesII.remove();
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.remove();
            }
            choiceSelected = SIX;
            
            center.add(calculusII.initializeAnimation(), ZERO, ONE, ONE, ONE);
            center.add(calculusII.controlMenu(), ZERO, ZERO, ONE, ONE);
            center.add(calculusII.drawGraph(), ONE, ZERO, ONE, ONE);
            center.add(calculusII.inputMenu(), ONE, ONE, ONE, ONE);
            
        }
        else if(source == exitItem)
        {
            System.exit(ZERO);
        }
        else if(source == startBtn)
        {
            startBtn.setDisable(true);
            pauseBtn.setDisable(false);
            resetBtn.setDisable(false);
            
            if(choiceSelected == ONE)
            {
                mechanicsI.starting();
            }
            else if(choiceSelected == TWO)
            {
                mechanicsII.starting();
            }
            else if(choiceSelected == THREE)
            {
                wavesI.starting();
                System.out.println(wavesI.getDuration());
            }
            else if(choiceSelected == FOUR)
            {
                wavesII.starting();
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.starting();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.starting();
            }
        }
        else if(source == done)
        {
            center.getChildren().removeAll(partI, partII, partIII, partIV);
            
            if(choiceSelected == ONE)
            {
                mechanicsI.remove();
            }
            else if(choiceSelected == TWO)
            {
                mechanicsII.remove();
            }
            else if(choiceSelected == THREE)
            {
                wavesI.remove();
            }
            else if(choiceSelected == FOUR)
            {
                wavesII.remove();
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.remove();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.remove();
            }
            choiceSelected = ZERO;
        }
        else if(source == pauseBtn)
        {
            pauseBtn.setDisable(true);
            resumeBtn.setDisable(false);
            
            if(choiceSelected == ONE)
            {
                mechanicsI.pause();
            }
            else if(choiceSelected == TWO)
            {
                mechanicsII.pause();
            }
            else if(choiceSelected == THREE)
            {
                wavesI.pause();
            }
            else if(choiceSelected == FOUR)
            {
                wavesII.pause();
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.pause();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.pause();
            }
        }
        else if(source == resumeBtn)
        {
            resumeBtn.setDisable(true);
            pauseBtn.setDisable(false);
            
            if(choiceSelected == ONE)
            {
                mechanicsI.resume();
            }
            if(choiceSelected == TWO)
            {
                mechanicsII.resume();
            }
            else if(choiceSelected == THREE)
            {
                wavesI.resume();
            }
            else if(choiceSelected == FOUR)
            {
                wavesII.resume();
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.resume();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.resume();
            }
        }
        else if(source == resetBtn)
        {
            startBtn.setDisable(false);
            pauseBtn.setDisable(true);
            resumeBtn.setDisable(true);
            resetBtn.setDisable(true);
            
            if(choiceSelected == ONE)
            {
                mechanicsI.reset();
            }
            if(choiceSelected == TWO)
            {
                mechanicsII.reset();
            }
            if(choiceSelected == THREE)
            {
                wavesI.reset();
                center.getChildren().removeAll(partI, partII, partIII, partIV);
                center.add(wavesI.controlMenu(), ZERO, ZERO, ONE, ONE);
                center.add(wavesI.inputMenu(), ONE, ONE, ONE, ONE);
                center.add(wavesI.initializeAnimation(), ZERO, ONE, ONE, ONE);
                center.add(wavesI.drawGraph(), ONE, ZERO, ONE, ONE);
            }
            if(choiceSelected == FOUR)
            {
                wavesII.reset();
                center.getChildren().removeAll(partI, partII, partIII, partIV);
                center.add(wavesII.controlMenu(), ZERO, ZERO, ONE, ONE);
                center.add(wavesII.inputMenu(), ONE, ONE, ONE, ONE);
                center.add(wavesII.initializeAnimation(), ZERO, ONE);
                center.add(wavesII.drawGraph(), ONE, ZERO, ONE, ONE);
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.reset();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.reset();
            }
        }
        else if(source == helpBtn)
        {
            if(choiceSelected == ONE)
            {
                mechanicsI.help();
            }
            if(choiceSelected == TWO)
            {
                mechanicsII.help();
            }
            if(choiceSelected == THREE)
            {
                wavesI.help();
            }
            if(choiceSelected == FOUR)
            {
                wavesII.help();
            }
            else if(choiceSelected == FIVE)
            {
                calculusI.help();
            }
            else if(choiceSelected == SIX)
            {
                calculusII.help();
            }
        }
    }
}
