package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

//extends is like adding JPanel inputs to GamePanel

public class GamePanel extends JPanel {

    //just like a painting, JFrame is the frame and
    //JPanel is the actual picture of the painting

    private final MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    //I should practice with 2 dimensional array's on shits [][]
    private BufferedImage[][] animations;
    //a stands for animation
    private int aTick, aIndex, aSpeed = 20; //the lower the value the faster the animation
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    //Constructor
    public GamePanel() {

        //having it on a separate file is much easier
        //to read than having all the KeyEvents here.
        //better structure

        mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void loadAnimations() {

        animations = new BufferedImage[8][17];

        for (int j = 0; j < animations.length; j++)
        //x = vertical || y = horizontal
        for (int i = 0; i < animations[j].length; i++) // i and j are the length of each frame
            animations[j][i] = img.getSubimage(i * 45, j * 24, 32, 24);
    }

    //one way of importing images
    private void importImg() {

    InputStream is = getClass().getResourceAsStream("/hobbit_sprite.png");

        // try and catch is like a stronger if statement
        // normally used to load something. Need to
        // document a little more about this

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //I set this in GamePanel and not Window because
    //when I set the size in Window we have 400x400
    //but that's including the border and the top bar

    private void setPanelSize() {

        //1280,800 because I will use images sized 32x32
        //so this will be a good size to fit those here
        //so this will even that out with 40 tiles wide
        //and 25 tiles height, so no tile is outside border

        Dimension size = new Dimension(1280,800);
        setBackground(Color.pink);

        setPreferredSize(size);


    }

    public void setDirection(int direction){

        this.playerDir = direction;
        moving = true;

    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    private void updateAnimationTick() {

        aTick++;
        if(aTick >= aSpeed){
            aTick = 0; //resets

            aIndex++;
            if(aIndex >= GetSpriteAmount(playerAction))
                aIndex = 0;
        }
    }

    private void setAnimation() {

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    private void updatePos() {

        if (moving){
            switch (playerDir) {
                case LEFT -> xDelta -= 5;
                case UP -> yDelta -= 5;
                case RIGHT -> xDelta += 5;
                case DOWN -> yDelta += 5;
            }
        }
    }



    //paintComponent is a method we use to draw, and
    //we need a graphics object as input (Graphics g)

    public void paintComponent(Graphics g) {

        //this line of coding is actually calling
        //JComponent's paintComponent, JComponent is
        //the superclass of JPanel

        super.paintComponent(g);

        updateAnimationTick();
        setAnimation();
        updatePos();
        g.drawImage(animations[playerAction][aIndex], (int)xDelta, (int)yDelta, 128 ,80,null); //size of character
    }

}
