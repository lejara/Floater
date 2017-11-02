package gameObjects;

import java.awt.Point;

import collisionDetection.CollisionBox;
import processing.core.PApplet;

public class KillerBox extends BasePhyscisGameObject{

	PApplet parent;
	int width;
	int height;
	
	int  moveX = 10;
	
	boolean turnRed;
	
	//temp
	boolean hasHit;
	public KillerBox(PApplet p, int startX, int startY, int w, int h, int speed)
	{
		parent = p;
		moveX = speed;
		this.location = new Point(startX, startY);
		this.prev_Location = new Point();
		
		width = w;
		height = h;
		
		hasHit = false;
		
		this.id = "KillerBox";
		
		this.prev_col = new CollisionBox(prev_Location, null, width, height);
		this.col = new CollisionBox(location, prev_col, width, height);
		
		turnRed = false;
		
	}
	
	public void move()
	{
		if(!hasHit){
			prev_Location.setLocation(location.x, location.y);
			//will only move on the x-axis
			location.translate(moveX, 0);
			
			if(location.x > (parent.width - width) || location.x < 0)
			{
				//swap movement
				moveX *= -1;
			}
		}
	}
	
	 @Override
	  public void gravityAffect(float mag){
		 //Nothing this object will not have a gravity Affect
	 }
	 
	 @Override
	  public void onCollision(Point collisionPoint, String objectName){
		if( objectName == "Player")
		{
			turnRed = true;
			hasHit = true;
		}
	 }
	
	 public void drawKillerBox()
	 {
		 parent.fill(100,100,100);
		 parent.rect(location.x, location.y, width, height);
		 
	 }
	
	
	
}
