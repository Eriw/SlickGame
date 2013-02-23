package javagame;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import static javagame.Game.*;
import org.newdawn.slick.geom.Rectangle;

public class Play extends BasicGameState{
    
   boolean quit = false;
   private Player player;
    public ArrayList<Entity> entities;
   
   public Play(int state){
   }
   
   public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
       player = new Player(gc, this,10,300,20,20);
       entities = new ArrayList<Entity>();
       
       Random r = new Random();
       int i = 0;
       entities.add(new Platform(gc, this, r.nextInt(WINDOW_WIDTH-50) , r.nextInt(WINDOW_HEIGHT-50), 50));
       while(i < 10){
           Entity newE = new Platform(gc, this, r.nextInt(WINDOW_WIDTH-50) , r.nextInt(WINDOW_HEIGHT-50), 50);
           boolean colides = false;
           for(Entity e: entities){
               colides = colides || e.entity.intersects(newE.entity);
           }
           if(!colides){
               entities.add(newE);
               i++;
           }
       }
       i = 0;
       while(i < 10){
           Entity newE = new Box(gc, this, r.nextInt(WINDOW_WIDTH-50) , r.nextInt(WINDOW_HEIGHT-50), 50, 50);
           boolean colides = false;
           for(Entity e: entities){
               colides = colides || e.entity.intersects(newE.entity);
           }
           if(!colides){
               entities.add(newE);
               i++;
           }
       }
   }
   
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
      player.render();
      for(Entity e: entities){
          e.render();
      }
      g.drawString("Entities: " + entities.size(), 10, 100);
   }
   
   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
	   
	   for(int i = 0; i < entities.size(); ++i){
		   Entity e = entities.get(i);
		   e.update(delta);
	       if(e.getHitbox().getMaxX() < 0 || e.getHitbox().getX() > WINDOW_WIDTH || e.getHitbox().getMaxY() < 0 || e.getHitbox().getY() > WINDOW_HEIGHT){
	    	 entities.remove(i);
	       }
	   }
	   
	   
		player.update(delta);
		  
		   
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_M)){
			sbg.enterState(GAME_STATE_MENU);
		}   
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			System.exit(0);
		} 
   }
   
   public int getID(){
      return 1;
   }
}