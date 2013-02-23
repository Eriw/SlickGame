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
    private Rectangle feet;
    private Vector2f velocity;
    private Graphics g;
    private GameContainer gc;
    private Play world;
    private final float PLAYER_HORISONTAL_SPEED = 0.75f;
    private final float PLAYER_JUMP_SPEED = -10;
    private final float FRICTION = 0.1f;
    private final float GRAVITY = 0.03f;
    public final int MOVING_RIGHT = 1;
    public final int MOVING_LEFT = -1;
    private int direction = MOVING_RIGHT;
    
    public Player(GameContainer gc, Play play, float x, float y, float width, float height){
        
        this.g = gc.getGraphics();
        this.gc = gc;
        player = new Rectangle(x, y, width, height);
        velocity = new Vector2f(0, 0);
        this.world = play;
    }
    
    public void update(int delta){
    	input();
    	move(delta);
    	if(velocity.x > 0){
    		direction = MOVING_RIGHT;
    	}else if(velocity.x < 0){
    		direction = MOVING_LEFT;
    	}
    }
    
    private void input() {
    	Input input = gc.getInput();
        
        if( (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) && velocity.y == 0){
            velocity.y = PLAYER_JUMP_SPEED;
        }
        
        if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)){
        	velocity.x = -PLAYER_HORISONTAL_SPEED;
        }
        if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)){
        	velocity.x = PLAYER_HORISONTAL_SPEED;
        }
        
        if( input.isKeyDown(Input.KEY_SPACE)){
            world.entities.add(new Bullet(gc, world, player.getCenterX() + direction*player.getWidth(), player.getY(), 10, 10, new Vector2f(direction,0)));
        }
		
	}

    public void move(int delta){
		
		velocity.y += GRAVITY*delta;
		if(velocity.x > 0) velocity.x -= FRICTION;
		if(velocity.x < 0) velocity.x += FRICTION;
		if(velocity.x > -FRICTION && velocity.x < FRICTION) velocity.x = 0;
			
		player.setY(player.getY() + velocity.y);
			
		 for(Entity e : world.entities){
			if(e.getHitbox().getHeight() > 1){
				if(e.hitbox.intersects(player)){
					if(player.getCenterY() >= e.getHitbox().getCenterY()){
						player.setY(e.getHitbox().getMaxY() + 1f);
						velocity.y = 0.001f;
					}else{
						player.setY(e.getHitbox().getY() - player.getHeight() - 0.1f);
						velocity.y = 0;
					}
				}
			}else if(velocity.y > 0){
				if(e.hitbox.intersects(player)){
			        player.setY(e.getY() - player.getHeight() - 0.1f);
			        velocity.y = 0f;
			    }
			}
		 }

		
		if(player.getMaxY() > WINDOW_HEIGHT){
		    player.setY(WINDOW_HEIGHT - player.getHeight());
		    velocity.y = 0;
		}
		
		boolean intersectsY = false;		
		for(Entity e : world.entities){
           if(e.entity.intersects(player)){
        	   intersectsY = true;
               
           }
        }
		
		
		player.setX(player.getX() + delta*velocity.x );
		
        if(player.getX() < 0){
           player.setX(0); //dont let him keep going up if he reaches the top
        }
        if(player.getMaxX() > WINDOW_WIDTH){
            player.setX(WINDOW_WIDTH - player.getWidth()); //dont let him keep going up if he reaches the top
        }
        
        
        for(Entity e : world.entities){
           if(e.entity.intersects(player) && !intersectsY){
        	   if(player.getCenterX() >= e.entity.getCenterX()){
        		   player.setX(e.entity.getMaxX() + 2f);
        	   }else{
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
