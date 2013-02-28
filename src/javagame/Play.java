package javagame;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import static javagame.Game.*;
import org.newdawn.slick.geom.Rectangle;

public class Play extends BasicGameState{
    
   boolean quit = false;
   public Player player;
   public Camera camera;
   public EntityHandler eh;
   public ArrayList<Entity> entities;
   
   public Play(int state){
   }
   
   public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
       player = new Player(gc, this,10,300,40,90);
       entities = new ArrayList<Entity>();
       eh = new EntityHandler(gc, this);
       eh.init();
       camera = new Camera(this);
   }
   
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
      
      eh.render();
      player.render();
      g.drawString("Entities: " + eh.sumOfEntites() , 10, 100);
   }
   
   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

	   	player.update(delta);
		eh.update(delta);
		handleInput(gc, sbg);
		camera.update();
   }
   
   private void handleInput(GameContainer gc, StateBasedGame sbg){
	   
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