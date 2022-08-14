package main;

import entities.Player;
import levels.levelManager;

import java.awt.*;

public class Game implements Runnable {

    //Created game Window & gamePanel objects

    private final Window window;
    private final GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private levelManager levelM;

    private Player player;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5F;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

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
    gamePanel.setFocusable(true);
    gamePanel.requestFocus();
    startGameLoop();
    }

    private void intiClasses() {

        player = new Player(200,200,(int) (42 * SCALE),(int)(32 * SCALE));
        levelM = new levelManager(this);
    }

    private void startGameLoop(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){

        player.update();
        levelM.update();
    }

    public void render(Graphics g){

        levelM.draw(g);
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
