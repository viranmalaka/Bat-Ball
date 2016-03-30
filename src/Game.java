
/**
 * Description	:Engine for the brick game
 * Copyright	:Copyright (c) 2014
 * Company		:Embla Software Innovations (Pvt) Ltd
 * Created on	:2014.09.01
 *
 * @author :Chandimal
 * @version :1.0
 */
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Game extends Canvas implements KeyListener {

    private static final long serialVersionUID = 1L;

    BufferedImage buffer; // Create the buffer
    BufferedImage background;
    Ball ball;
    Bat bat;
    ArrayList<Brick> bricks = new ArrayList();

    boolean isLeft;
    boolean isRight;
    boolean hit;
    Dimension d;

    /**
     * Create the game using the width and the height specified
     */
    public Game(Dimension dim) {
        buffer = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);

        this.setIgnoreRepaint(true); // Ignore repainting as we are doing all
        // the drawing stuff
        ball = new Ball(dim.width, dim.height, 185, dim.height - 60, 10,20);
        bat = new Bat(139, dim.height - 30, dim.width, dim.height, 5);
        d = dim;

        bricks = Brick.getBirckSet(1);

        try {
            background = ImageIO.read(new File("./img/bg.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Start the game
     */
    public void Start() {

        while (true) {

            if (isLeft) {
                bat.goLeft();
            }
            if (isRight) {
                bat.goRight();
            }

            detectCollition();
            brickCollition();

            if (!detectGameOver()) {
                drawGameOverScreen();
                break;
            }

            bat.update();
            ball.update();
            // Draw the buffer
            drawBuffer();
            // Paint the buffer on screen
            drawScreen();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Draw the image buffer
     */
    public void drawBuffer() {
        Graphics2D b = buffer.createGraphics();

        //draw the background
        b.drawImage(background, 0, 0, this);

        // draw the ball
        b.drawImage(ball.getPic(), ball.getX(), ball.getY(), this);

        //draw the bat
        b.drawImage(bat.getPic(), bat.getX(), bat.getY(), this);

        for (Brick brick : bricks) {
            b.drawImage(brick.getPic(), brick.getX(), brick.getY(),this);
        }

    }

    /**
     * Update it to the screen
     */
    public void drawScreen() {
        Graphics2D g = (Graphics2D) this.getGraphics();

        g.drawImage(buffer, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.finalize();
    }
    
    public void drawGameOverScreen(){
        Graphics2D g = buffer.createGraphics();
        try {
            BufferedImage gOver = ImageIO.read(new File("./img/over.png"));
            g.drawImage(gOver, 0,0,400,600, this);
            drawScreen();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            isLeft = true;
        }
        if (e.getKeyCode() == 39) {
            isRight = true;
        }
        if (e.getKeyCode() == 32) {
            hit = true;
            bat.setShot(hit);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            isLeft = false;
        }
        if (e.getKeyCode() == 39) {
            isRight = false;
        }
        if (e.getKeyCode() == 32) {
            hit = false;
            bat.setShot(hit);
        }
    }

    public void detectCollition() {
        Rectangle recBoll = new Rectangle(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
        Rectangle recBat = new Rectangle(bat.getX(), bat.getY(), bat.getW(), bat.getH());

        if (recBat.intersects(recBoll)) {
            ball.shot(hit);
        }
    }

    public void brickCollition() {
        Rectangle recBoll = new Rectangle(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
        Brick brickToRemove = null;
        for (Brick brick : bricks) {
            Rectangle rect = new Rectangle(brick.getX(), brick.getY(), brick.getW(), brick.getH());
            if (rect.intersects(recBoll)) {
                brickToRemove = brick;
                ball.hitOnBrick();
                break;
            }
        }
        if (brickToRemove != null) {
            bricks.remove(brickToRemove);
        }
        if (bricks.size() == 0) {
            System.out.println("you win");
        }
    }

    public boolean detectGameOver() {
        if (ball.getY() > d.getHeight()) {
            return false;
        }
        return true;
    }
}
