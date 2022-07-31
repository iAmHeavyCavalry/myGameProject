package main;

public class Game implements Runnable {

    //Created game Window & gamePanel objects

    private final Window window;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

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

    //add input focus, needed for keyboard input
    gamePanel.requestFocus();
    startGameLoop();

    }

    private void startGameLoop(){

        gameThread = new Thread(this);
    }

    //here I want to make the game loop run on
    //a separate thread, so it becomes more fluid

    @Override
    public void run() {

        double timePerFrame = 1_000_000_000 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        while(true){

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame){

                gamePanel.repaint();
                lastFrame = now;
            }
        }

    }
}
