/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physics;

/*
 * @Original author: Michael Heinrichs
 * The original code was modified to meet our needs
*/

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private ImageView imageView;
    private int count;
    private int columns;
    private int width;
    private int height;
//    private boolean pause;

    private int lastIndex;

    public SpriteAnimation(){
        imageView = new ImageView();
        count = columns = width = height = 0;
        setInterpolator(Interpolator.EASE_BOTH);
    }
    
    public void setVariables(ImageView imageView, Duration duration, 
            int count,   int columns,
            int width,   int height) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        
//        pause = false;
    }
    
    @Override
    protected void interpolate(double k) {
       final int index = Math.min((int) Math.floor(k *count), count - 1);
            if ((index != lastIndex)) {
                final int x = (index % columns) * width;
                final int y = (index / columns) * height;
                imageView.setViewport(new Rectangle2D(x, y, width, height));
                lastIndex = index;
            }
            
    }
}
