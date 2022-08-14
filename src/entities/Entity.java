package entities;
//this class can be accessed by player and NPC's
public abstract class Entity {

    protected float x,y;
    protected int width, height;

    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
}
