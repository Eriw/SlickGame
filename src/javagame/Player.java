/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

import java.util.ArrayList;

import org.newdawn.slick.Color;
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
    private final float PLAYER_JUMP_SPEED = -7;
    private final float FRICTION = 10f;
    private final float GRAVITY = 0.03f;
    public final int MOVING_RIGHT = 1;
    public final int MOVING_LEFT = -1;
    private int direction = MOVING_RIGHT;
    private int delayNextBullet = 0;
    private boolean drop;
    
    public Player(GameContainer gc, Play play, float x, float y, float width, float height){
        
        this.g = gc.getGraphics();
        this.gc = gc;
        player = new Rectangle(x, y, width, height);
        velocity = new Vector2f(0, 0);
        this.world = play;
        drop = false;
    }
    
    public void update(int delta){
    	input();
    	if(velocity.x > 0){
    		direction = MOVING_RIGHT;
    	}else if(velocity.x < 0){
    		direction = MOVING_LEFT;
    	}
    	if(delayNextBullet > 0) --delayNextBullet;

    	move(delta);
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
        if(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)){
            drop = true;
        }else{
            drop = false;
        }
        
        if( input.isKeyDown(Input.KEY_SPACE)){
           if(delayNextBullet == 0){
        	   delayNextBullet = 42;
        	   world.eh.createBullet(new Vector2f(player.getCenterX() + direction*player.getWidth(),  player.getY()), new Vector2f(direction,0) );
           }
        }
		
	}

    public void move(int delta){
	
        velocity.y += GRAVITY*delta;

        player.setY(player.getY() + velocity.y*delta*0.25f);

        for(Entity e : world.eh.objects){
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
                }else if(velocity.y > 0 && player.getCenterY() < e.hitbox.getY() && !drop){
                        if(e.hitbox.intersects(player)){
                        player.setY(e.getY() - player.getHeight() - 0.1f);
                        velocity.y = 0f;
                    }
                }
         }


        if(player.getMaxY() > WINDOW_HEIGHT){
           // player.setY(WINDOW_HEIGHT - player.getHeight());
            player.setY(500f);
            player.setX(10);
            velocity.y = 0;
        }

        boolean intersectsY = false;		
        for(Entity e : world.eh.objects){
            if(e.entity.intersects(player)){
                    intersectsY = true;

            }
        }
		
        float oldX = player.getX();
        player.setX(player.getX() + delta*velocity.x );
		
        if(player.getX() < 0){
           player.setX(0);
        }
        if(player.getMaxX() > WORLD_WIDTH){
            player.setX(WORLD_WIDTH - player.getWidth());
        }
        
        
        for(Entity e : world.eh.objects){
           if(e.entity.intersects(player)){// && !intersectsY ){
        	   /*if(player.getCenterX() >= e.entity.getCenterX()){
        		   player.setX(e.entity.getMaxX() + 2f);
        	   }else{
        		   player.setX(e.entity.getX() - player.getWidth() - 1f);
        	   }*/
                   
                   player.setX(oldX);
           }
        }
        
        if(velocity.x > 0) velocity.x -= FRICTION;
        if(velocity.x < 0) velocity.x += FRICTION;
        if(velocity.x > -FRICTION && velocity.x < FRICTION) velocity.x = 0;
	        
    	
    	
    }
    
    public void render(){
        
    	g.setColor(new Color(1.0f, 0.0f, 0.0f));
        float x0 = player.getX() - world.camera.getPosition();
        g.drawRect(x0, player.getY(), player.getWidth(), player.getHeight());
        //g.drawString("Player pos:" + player.getX() + ", " + player.getY(), 10, 70);
        //g.drawString("x0:" + x0, 10, 50);
        
    }

    public float getX() {
        return player.getX();
    }

    public float getY() {
        return player.getY();
    }
    
    public float getCenterX(){
    	return player.getCenterX();
    }
    
}
