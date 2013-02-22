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
       entities.add(new Entity(gc, r.nextInt(WINDOW_WIDTH-50) , r.nextInt(WINDOW_HEIGHT-50), 50, 50));
       while(i < 10){
           Entity newE = new Entity(gc, r.nextInt(WINDOW_WIDTH-50) , r.nextInt(WINDOW_HEIGHT-50), 50, 50);
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
   }
   
   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
      
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