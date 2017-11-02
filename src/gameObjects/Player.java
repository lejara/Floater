package gameObjects;
import processing.core.*;
import java.awt.Point;

import collisionDetection.CollisionBox;
public class Player extends  BasePhyscisGameObject{
	
  private PApplet parent;
  
  private PImage sprite_;

  //size of player
  public int size_W;
  public int size_H;

  //Amount to move leave private 
  public int velocityX;
  public int velocityY;
  
  private int jumpCooldown = 400;
  private int curretMillsHold = 0;
  
  boolean hasLanded;
  boolean hasDied;
  public Player(PApplet p, String imageLocation, float w, float h, int moveAmo_X)
  {
	parent = p;  
	
	id = "Player";
	sprite_  = parent.loadImage(imageLocation);
	
	//start location
	location = new Point(100, 800);
	prev_Location = new Point(100, 800);
	
	velocityX = moveAmo_X;
	//set size of player 
	//TODO: make sure casting the size from float to int does not break the collision box size
	size_W = (int)(sprite_.width/w);
	size_H = (int)(sprite_.height/h);
	//set collisionBox 
	prev_col = new CollisionBox(prev_Location, null, size_W, size_H );
	col = new CollisionBox(location, prev_col, size_W, size_H );
	
	hasDied = false;
	hasLanded = false;
  }
  @Override
  public void gravityAffect(float mag){
	  if(!hasLanded && !hasDied){
		  prev_Location.setLocation(location); 
		  velocityY += mag;
		  location.translate(0, velocityY);
	  }
  }
  @Override
  public void onCollision(Point collisionPoint, String objectName){
	  if(objectName.equals("Level") && velocityY > 0){
		  hasLanded = true;
		  //end jump
		  velocityY = 0;
		  //correct y-axis
		  int y_diff = (int)col.bottomPointsRange.getLeftPoint().getY() - (int)collisionPoint.getY();
		  location.translate(0,(y_diff * -1 ));
	  }
	  if(objectName.equals("KillerBox")){
		  hasDied = true;	  
	  }
  }
  public void movePlayer(char key1, char key2, char key3)
  {
	//temp if statement
	if(!hasDied){  
		prev_Location.setLocation(location); 
		if(key1 == 'd'){	
			if(location.x < (parent.width - size_W))
			{
				location.translate(velocityX, 0);
			}
			
		}
		if (key2 == 'a')
		{
			if(location.x > 0)
			{
				location.translate(-velocityX, 0);
			}	
		}
		if (key3 == 'w')
		{
			//TODO: do like a Mario jump
			startJump();
			//location.translate(0, velocityY); //whY? when putting velocityY to -15 it breaks the collision
		}
		
		hasLanded = false;
	}
  }
  public void startJump(){
	  if(hasLanded && (parent.millis() - curretMillsHold ) > jumpCooldown){
		  velocityY  = -21;
		  curretMillsHold = parent.millis();
	  }
  }
  //Checks to see if the player has fallen off the level
  public boolean checkPlayerHasFallen(){
	  
	  if(location.y > parent.height + 200 || hasDied){
		return true;  
	  }
	  return false;
  }
  
  public void DrawPlayer()
  {
	  parent.image(sprite_, (float)location.getX(), (float)location.getY(), size_W, size_H);
	
  }
  
  
}