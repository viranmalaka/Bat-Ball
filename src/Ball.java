
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
public class Ball {

    private int X;
    private int Y;
    private int size = 30;

    private int gameWidth;
    private int gameHight;

    private int dx, dy;
    private int speed;
    
    private int border;

    private int[] move = {1, 1};
    //[up=-1 or down=+1 , left = -1 or right = +1]
    private boolean up;
    //up = 0
    
    private BufferedImage pic;

    public Ball(int gameWidth, int gameHight, int ballX, int ballY, int speed,int border) {
        this.X = ballX;
        this.Y = ballY;
        this.gameWidth = gameWidth;
        this.gameHight = gameHight;
        this.speed = speed;
        this.dx = 5;
        this.dy = 5;
        this.border = border;
        
        try {
            pic = ImageIO.read(new File("./img/ball.png"));
        } catch (IOException ex) {
            System.out.println("ball not found");
        }
    }

    public void update() {

        if (X < border) { //left wall
            move[1] = -1 * move[1];
        }
        if (Y < border) { //top wall
            if (this.up) {
                this.up = false;
            } else {
                move[0] = -1 * move[0];
            }
        }
        if (X + size > gameWidth - border) { // right wall
            move[1] = -1 * move[1];
        }
        if (up) {
            X += 0;
            Y += -speed;
        } else {
            X += move[1] * speed;
            Y += move[0] * speed;
        }
    }

    public void shot(boolean up) {
        if (!up) {
            move[0] = -1 * move[0];
        } else {
            this.up = true;
        }
    }

    public void hitOnBrick() {
        if (this.up) {
            this.up = false;
        } else {
            move[0] = -1 * move[0];
        }
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getSize() {
        return size;
    }

    public BufferedImage getPic() {
        return pic;
    }
    
    
    

}
