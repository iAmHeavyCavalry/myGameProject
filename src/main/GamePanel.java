package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

//extends is like adding JPanel inputs to GamePanel

public class GamePanel extends JPanel {

    //just like a painting, JFrame is the frame and
    //JPanel is the actual picture of the painting

    private final MouseInputs mouseInputs;

    private Game game;
    //Constructor
    public GamePanel(Game game) {
        this.game = game;

        //having it on a separate file is much easier
        //to read than having all the KeyEvents here.
        //better structure

        mouseInputs = new MouseInputs(this);

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

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

        setPreferredSize(size);


    }

    public void updateGame(){

    }


    //paintComponent is a method we use to draw, and
    //we need a graphics object as input (Graphics g)

    public void paintComponent(Graphics g) {

        //this line of coding is actually calling
        //JComponent's paintComponent, JComponent is
        //the superclass of JPanel

        super.paintComponent(g);

        g.setColor(Color.pink);
        for (int i = 0; i < 64; i++)
        for (int j = 0; j < 40; j++)
        g.fillRect(i * 20, j * 20, 20, 20);

        game.render(g);
    }

    public Game getGame(){
        return game;
    }

}
