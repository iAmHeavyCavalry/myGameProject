package main;
import java.awt.Graphics;
import javax.swing.*;

public class GamePanel extends JPanel {

    //just like a painting, JFrame is the frame and
    //JPanel is the actual picture of the painting

    public GamePanel(){

    }

    //paintComponent is a method we use to draw, and
    //we need a graphics object as input (Graphics g)

    public void paintComponent(Graphics g){

        //this line of coding is actually calling
        //JComponent's paintComponent, JComponent is
        //the superclass of JPanel

        super.paintComponent(g);

        //we still need to assemble GamePanel inside Window
        //to be able to see the rectangle.
        g.fillRect(170,150,50,50);
    }
}
