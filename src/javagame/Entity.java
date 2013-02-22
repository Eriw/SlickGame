/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import static javagame.Game.*;

/**
 *
 * @author Wirebrand
 */
public class Entity {
    
    public Rectangle entity;
    private Vector2f velocity;
    private Graphics g;
    private GameContainer gc;
    private float gravity;
    
    public Entity(GameContainer gc, float x, float y, float width, float height){
        
        this.g = gc.getGraphics();
        this.gc = gc;
        entity = new Rectangle(x, y, width, height);
        velocity = new Vector2f(0, 0);
        gravity = 1f;
    }
    
    public void update(int delta){
        
    }
    
    public void render(){
        
        g.draw(entity);
        
    }

    public float getX() {
        return entity.getX();
    }

    public float getY() {
        return entity.getY();
    }
    
}
