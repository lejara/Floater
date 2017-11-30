import java.util.Iterator;
import java.util.Vector;

import collisionDetection.CollisionBox;
import gameObjects.BasePhyscisGameObject;

import java.awt.Point;

public final class PhyscisAffectManager {
	
	private static float GRAVITY_MAG;
	//Game Objects that will be affected by physics!
	Vector<BasePhyscisGameObject> objects;
	
	public PhyscisAffectManager(float Grav)
	{
		GRAVITY_MAG = Grav;
		objects = new Vector<BasePhyscisGameObject>(5);
	}
	
	//Make an gravityAffect function when wanting to use Gravity
	public static float getGravityMag(){
		return GRAVITY_MAG;
	} 
	
	public void setObjectAffect(BasePhyscisGameObject o){
		objects.add(o);
	}
	//loops thru all BasePhyscisGameObject that are added to the vector array
	public void  Gravity()
	{
		Iterator<BasePhyscisGameObject> itr = objects.iterator();
		while(itr.hasNext()){
			 itr.next().gravityAffect(getGravityMag());
		}
	}
	//TODO: Make this function within work in parallel
	//TODO: perform a radius scan before checking the collision
	//detect if the passed game object has collied with the level
	//If so, the function will call each object OnCollision function 
	public Boolean detectCollisionWithLevel(BasePhyscisGameObject obj, Level lvl)
	{
		Point foundColP = null;
		//Will keep the running track of indexes passed by itr. This is used when detecting which title the player has landed on.
		int count = 0;
		Iterator<CollisionBox> itr = lvl.col_boxes.publicGetColBoxes().iterator();
		
		while(itr.hasNext() && foundColP == null){
			foundColP  = obj.col.detectLevelCollision(itr.next());
			
			if(foundColP != null)
			{			
				//call each OnCollision function
				obj.onCollision(foundColP, lvl.id);
				lvl.onCollisionLevel(foundColP, obj.id,count );
			}
			count++;
		}
		return foundColP != null;
	}
	//This function detects an overlaping collision of two BasePhyscisGameObject objects
	//If so, the function will call each object OnCollision function 
	public Boolean detectCollisionWithOtherObject(BasePhyscisGameObject obj, BasePhyscisGameObject obj2){
		Point foundPoint = null;
		
		foundPoint = obj.col.detectCollision(obj2.col);
		foundPoint = obj2.col.detectCollision(obj.col);
		if(foundPoint != null){
			//call each OnCollision function
			obj.onCollision(foundPoint, obj2.id);
			obj2.onCollision(foundPoint, obj.id);
		}
		
		return foundPoint != null;
	}

}
