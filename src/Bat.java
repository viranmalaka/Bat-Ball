
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author malaka
 */
public class Bat {

    private int X;
    private int Y;
    private int w = 122;
    private int h = 19;

    private int gameWidth;
    private int gameHight;
    private int speed;

    private boolean shot;
    private BufferedImage pic;

    int y;

    public Bat(int X, int Y, int gameWidth, int gameHight, int speed) {
        this.X = X;
        this.Y = Y;
        this.gameWidth = gameWidth;
        this.gameHight = gameHight;
        this.speed = speed;
        y = Y;
        
        try {
            pic = ImageIO.read(new File("./img/bat.png"));
        } catch (IOException ex) {
            System.out.println("bat not found");
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void update() {
        if (X + w > gameWidth) {
            X = gameWidth - w;
        }
        if (X < 0) {
            X = 0;
        }
    }

    public void setShot(boolean shot) {
        this.shot = shot;
        if (shot) {
            Y = y - 10;
        } else {
            Y = y;
        }
    }

    public void goLeft() {
        X -= speed;
    }

    public void goRight() {
        X += speed;
    }

    /**
     * @return the X
     */
    public int getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(int X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public int getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(int Y) {
        this.Y = Y;
    }

    /**
     * @return the w
     */
    public int getW() {
        return w;
    }

     /**
     * @return the h
     */
    public int getH() {
        return h;
    }

    public BufferedImage getPic() {
        return pic;
    }

    
}
