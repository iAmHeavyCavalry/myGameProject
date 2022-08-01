package main;

import javax.swing.*;
import java.awt.*;

public class Window {

    //here I created a JFrame object as a global variable
    //it is one way of doing it and the other way is by
    //using extend.

    private JFrame frame;

    public Window (GamePanel gamePanel){

        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

        //visible recommended to be at last

        frame.setVisible(true);
    }
}
