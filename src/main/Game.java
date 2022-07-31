package main;

public class Game {

    //Created game Window & gamePanel objects

    private Window window;
    private GamePanel gamePanel;

    //to be able to call any methods in this class,
    //I first need a constructor. A constructor is
    //the special class that can be considered like
    //the head method of the class, so when ever we
    //create an object out of this Game class, we
    //directly call any code inside the constructor.

    public Game(){
    //I have to create the GamePanel first before
    //using it as a value for Window.
    gamePanel = new GamePanel();
    window = new Window(gamePanel);
    }
}
