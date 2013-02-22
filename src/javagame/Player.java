/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import static javagame.Game.*;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Wirebrand
 */
public class Player {
    
    private Rectangle player;
    private Vector2f velocity;
    private Graphics g;
    private GameContainer gc;
    private float gravity;
    private Play world;
    
    public Player(GameContainer gc, Play play, float x, float y, float width, float height){
        
        this.g = gc.getGraphics();
        this.gc = gc;
        player = new Rectangle(x, y, width, height);
        velocity = new Vector2f(0, 0);
        gravity = 1f;
        this.world = play;
    }
    
    public void update(int delta){
      Input input = gc.getInput();
          
      if(input.isKeyDown(Input.KEY_SPACE) && velocity.y == 0){
          velocity.y = -30;
      }
      velocity.y += gravity*delta/10;
      player.setY(player.getY() + velocity.y);
      
      if(velocity.y > 0){
        for(Entity e : world.entities){
          if(e.entity.intersects(player)){
              player.setY(e.getY() - player.getHeight() - 1f);
              velocity.y = 0f;
          }
        }
      }
      
      if(player.getMaxY() > WINDOW_HEIGHT){
          player.setY(WINDOW_HEIGHT - player.getHeight());
          velocity.y = 0;
      }

      if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)){
         player.setX(player.getX() - delta );//increase the Y coordinates of bucky (move him up)
         if(player.getX() < 0){
            player.setX(0); //dont let him keep going up if he reaches the top
         }
         for(Entity e : world.entities){
            if(e.entity.intersects(player)){
                player.setX(e.entity.getMaxX() + 2f);
            }
         }
      }
      if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)){
         player.setX(player.getX() + delta );//increase the Y coordinates of bucky (move him up)
         if(player.getMaxX() > WINDOW_WIDTH){
            player.setX(WINDOW_WIDTH - player.getWidth()); //dont let him keep going up if he reaches the top
         }
         for(Entity e : world.entities){
            if(e.entity.intersects(player)){
                player.setX(e.entity.getX() - player.getWidth() - 1f);
            }
         }
      }
        
    }
    
    public void render(){
        
        g.draw(player);
        g.drawString(":)", player.getCenterX()-10, player.getCenterY()-10);
        
    }

    public float getX() {
        return player.getX();
    }

    public float getY() {
        return player.getY();
    }
    
}
