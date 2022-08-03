package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity{

    private BufferedImage[][] animations;
    private int aTick, aIndex, aSpeed = 20; //the lower the value the faster the animation
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private final float playerSpeed = 2.0F;


    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }
    public void update(){

        updatePos();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g){

        g.drawImage(animations[playerAction][aIndex], (int)x, (int)y, 128 ,80,null);//256, 160

    }


    private void updateAnimationTick() {

        aTick++;
        if(aTick >= aSpeed){
            aTick = 0; //resets

            aIndex++;
            if(aIndex >= GetSpriteAmount(playerAction))
                aIndex = 0;
                attacking = false;
        }
    }

    private void setAnimation() {

        int startAnimation = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (attacking)
            playerAction = ATTACK;

        if (startAnimation != playerAction)
            resetATick();
    }

    private void resetATick() {
        aTick = 0;
        aIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if (left && !right){
            x -= playerSpeed;
            moving = true;
        } else if (right && !left){
            x += playerSpeed;
            moving = true;
        }
        if (up && !down){
            y -= playerSpeed;
            moving = true;
        } else if (down && !up){
            y += playerSpeed;
            moving = true;
        }
    }
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/hobbit_sprite.png");

        // try and catch is like a stronger if statement
        // normally used to load something. Need to
        // document a little more about this

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[8][17];
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++) // i and j are the length of each frame
                    animations[j][i] = img.getSubimage(i * 45, j * 24, 32, 24);

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

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

}
