package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

//extends is like adding JPanel inputs to GamePanel

public class GamePanel extends JPanel {

    //just like a painting, JFrame is the frame and
    //JPanel is the actual picture of the painting

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 0.003F, yDir = 0.003F;
    private int frames = 0;
    private long lastCheck = 0;
    private Color color = new Color(150, 20, 100);
    private Random random;

    //Constructor
    public GamePanel() {

        //having it on a separate file is much easier
        //to read than having all the KeyEvents here.
        //better structure
        random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));

        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    public void changeXDelta(int value) {
        this.xDelta += value;

        //The value is moving, but we don't see the rec
        //moving because there is no loop constantly
        //repainting the surface.


    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    //method to say at this position we draw rectangle
    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    //paintComponent is a method we use to draw, and
    //we need a graphics object as input (Graphics g)

    public void paintComponent(Graphics g) {

        //this line of coding is actually calling
        //JComponent's paintComponent, JComponent is
        //the superclass of JPanel

        super.paintComponent(g);

        //we still need to assemble GamePanel inside Window
        //to be able to see the rectangle.

        updateRectangle();
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);

        frames++;

        if (System.currentTimeMillis() - lastCheck >= 1_000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }

    }

    private void updateRectangle() {

        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir *= -1;
            color = getRndColor();
        }
        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0)
            yDir *= -1;
            color = getRndColor();
    }

    private Color getRndColor() {

        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r,g,b);

    }

}
