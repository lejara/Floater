package gameObjects;

import java.awt.Point;

import collisionDetection.CollisionBox;

public abstract class BasePhyscisGameObject {
	//the id is the name of the object
	public String id;
	
	public CollisionBox col;
	public CollisionBox prev_col;
	
	public Point location;
	public Point prev_Location;
	
	//TODO: gravityAffect should have its own abstract class
	public abstract void gravityAffect(float mag);
	public abstract void onCollision(Point collisionPoint, String whatObjectName);
}
