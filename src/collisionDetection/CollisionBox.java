package collisionDetection;
import java.awt.Point;
import java.util.Vector;
import java.util.Iterator;

public class CollisionBox {

	//will hold the Point reference 
	private Point objectLocation;
	//holds the reference of the previous location
	private CollisionBox prev_location;
	
	public Range topPointsRange;
	public Range leftPointsRange;
	public Range rightPointsRange;
	public Range bottomPointsRange;
	
	public Vector<Range> ranges;
	
	private int width;
	private int height;
	
	public CollisionBox(Point p, CollisionBox prev_loc ,int w, int h)
	{
		objectLocation = p;
		prev_location = prev_loc;
		
		width = w;
		height = h;
		
		ranges = new Vector<Range>(4);
		//set ranges and add them to the collection
		topPointsRange = new Range( objectLocation, width, height, RangeType.TOP);
		ranges.add(topPointsRange);
		leftPointsRange = new Range( objectLocation, width, height, RangeType.LEFT);
		ranges.add(leftPointsRange);
		rightPointsRange = new Range( objectLocation, width, height, RangeType.RIGHT);
		ranges.add(rightPointsRange);
		bottomPointsRange = new Range( objectLocation, width, height, RangeType.BOTTOM);
		ranges.add(bottomPointsRange);
	}
	
	//This function detect level collision by only detecting from the current object bottom range to the level's top range
	//Returns - a null Point object if no Collision is detected, or a object Point populated with a detection point 
	public Point detectLevelCollision (CollisionBox b){
		Point detectionPoint = null;
		int foundY = 0;
		int foundX = 0;
		//Current Object bottom range
		int currentObjectRangeLeftPoint_Y = this.bottomPointsRange.getLeftPoint().y;
		//Level Object top range
		int otherObjectRangeLeftPoint_Y = b.topPointsRange.getLeftPoint().y;
		//Find Y-axis collision. since this is a type (x- ranged, consent Y)		
		if(( currentObjectRangeLeftPoint_Y >=  otherObjectRangeLeftPoint_Y) && (prev_location.bottomPointsRange.getLeftPoint().getY() <= otherObjectRangeLeftPoint_Y)){
			foundY = otherObjectRangeLeftPoint_Y;
			//find only the  first X-axis collision point
			int currentObjectLocation_X = this.bottomPointsRange.getLeftPoint().x;
			for(int i = 0; i < width && foundX == 0; i++){
				
				int c_X = currentObjectLocation_X - i;
				
				if(c_X > b.topPointsRange.getLeftPoint().x  - 10 && c_X < b.topPointsRange.getRightPoint().x + 10){
					foundX = c_X;
				}
			}
		}
		//if both x, and y are found create a new object point
		if(foundX != 0 && foundY !=0 ){
			detectionPoint = new Point(foundX, foundY);
		}
		return detectionPoint;
	}

	//Detect collision with other objects. Detection done by doing a overlapping check
	//Returns - a null Point object if no Collision is detected, or a object Point populated with a detection point 
	public Point detectCollision (CollisionBox b){
		Point detectionPoint = null;
		
		Iterator<Range> itr_Current_Ranges = this.ranges.iterator();
		//loops thru all ranges of the current object
		while(itr_Current_Ranges.hasNext())
		{
			Point check_Point = itr_Current_Ranges.next().getLeftPoint();
			//detection is checked by checking all left points
			if(((check_Point.x >= b.topPointsRange.getLeftPoint().x) && 
				(check_Point.x <  b.bottomPointsRange.getLeftPoint().x)) &&
				((check_Point.y > b.rightPointsRange.getLeftPoint().y) &&
				(check_Point.y < b.leftPointsRange.getLeftPoint().y)))
			{
				detectionPoint = (Point) check_Point.clone();
				
			}
		}		
		return detectionPoint;
	}
	
	
}
