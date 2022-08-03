package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {

    //Created game Window & gamePanel objects

    private final Window window;
    private final GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;

    //to be able to call any methods in this class,
    //I first need a constructor. A constructor is
    //the special class that can be considered like
    //the head method of the class, so when ever we
    //create an object out of this Game class, we
    //directly call any code inside the constructor.

    public Game(){
    intiClasses();
    //I have to create the GamePanel first before
    //using it as a value for Window.

    gamePanel = new GamePanel(this);
    window = new Window(gamePanel);

    //add input focus, needed for keyboard input
    gamePanel.requestFocus();

    startGameLoop();

    }

    private void intiClasses() {
        player = new Player(200,200);
    }

    private void startGameLoop(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
    }

    public void render(Graphics g){
        player.render(g);
    }

    @Override
    public void run() {

        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

        if (deltaF >= 1) {
            gamePanel.repaint();
            frames++;
            deltaF--;

        }

        if (System.currentTimeMillis() - lastCheck >= 1000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames + " | UPS: " + updates);
            frames = 0;
            updates = 0;
        }


        }

    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer(){
        return player;
    }
}
