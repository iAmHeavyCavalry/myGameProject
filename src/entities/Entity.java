package entities;
//this class can be accessed by player and NPC's
public abstract class Entity {

    protected float x,y;
    public Entity(float x, float y){
        this.x = x;
        this.y = y;

    }
}
