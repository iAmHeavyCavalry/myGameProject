package main;

import javax.swing.*;

public class Window {
    //here I created a JFrame object as a global variable
    //it is one way of doing it and the other way is by
    //using extend.
    private JFrame frame;

    public Window (GamePanel gamePanel){

        frame = new JFrame();

        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setVisible(true);
    }
}
