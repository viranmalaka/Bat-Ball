
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class Brick {

    private int x;
    private int y;
    private int w = 38;
    private int h = 18;

    private BufferedImage pic;

    public Brick() {
    }

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            pic = ImageIO.read(new File("./img/brick.png"));
        } catch (IOException ex) {
            System.out.println("brick not found");
        }
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
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

    public static ArrayList<Brick> getBirckSet(int level) {
        ArrayList<Brick> arr = new ArrayList<>();

        switch (level) {
            case 1:

                for (int j = 0; j < 3; j++) {
                    for (int i = 0; i < 9; i++) {
                        arr.add(new Brick(20 + i * 40, 2 * j * 20 + 25)); // 9 brick row
                    }
                    for (int i = 0; i < 8; i++) {
                        arr.add(new Brick(40 + i * 40, (2 * j + 1) * 20 + 25)); // 8 brick row
                    }
                }
                for (int i = 0; i < 9; i++) {
                    arr.add(new Brick(20 + i * 40, 25));
                }
                break;

            case 2:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        arr.add(new Brick(20 + j * 40, 40 * j + 25));
                    }
                }
//                for (int i = 8; i > -1; i--) {
//                    for (int j = 0; j < i; j++) {
//                        arr.add(new Brick(20 + j * 40, 40 * j + 25));
//                    }
//                }
                break;
        }

        return arr;
    }

}
