
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author malaka
 */
public abstract class GameEntity {
    
    public abstract void setSpeed(int speed);

    public abstract void update();
    
    public abstract int getX();
    
    public abstract int getY();
    
    public abstract BufferedImage getPic();
}
