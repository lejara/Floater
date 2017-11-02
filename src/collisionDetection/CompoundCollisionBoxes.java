package collisionDetection;
import java.util.Vector;
import java.util.List;
public class CompoundCollisionBoxes {
	
	//These vectors must be in parallel!
	List<CollisionBox> boxes;
	public CompoundCollisionBoxes(int intialSize){
		boxes = new Vector<CollisionBox>(intialSize);
	}
	
	public void add (CollisionBox col){
		boxes.add(col);
	}
	
	public List<CollisionBox> publicGetColBoxes(){
		return boxes;
	}

	
}
