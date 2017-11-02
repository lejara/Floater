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
	
	private int x_Offset;
	private int y_Offset;
	
	public CollisionBox(Point p, CollisionBox prev_loc ,int x_offset, int y_offset)
	{
		objectLocation = p;
		prev_location = prev_loc;
		ranges = new Vector<Range>(4);
		
		topPointsRange = new Range( objectLocation, x_offset, y_offset, RangeType.TOP);
		ranges.add(topPointsRange);
		leftPointsRange = new Range( objectLocation, x_offset, y_offset, RangeType.LEFT);
		ranges.add(leftPointsRange);
		rightPointsRange = new Range( objectLocation, x_offset, y_offset, RangeType.RIGHT);
		ranges.add(rightPointsRange);
		bottomPointsRange = new Range( objectLocation, x_offset, y_offset, RangeType.BOTTOM);
		ranges.add(bottomPointsRange);
		
		x_Offset = x_offset;
		y_Offset = y_offset;
	}
	
	//Returns - null if no Collision is detected.  or an range type if there is a collision
	public Point detectLevelCollision (CollisionBox b){
		Point detectionPoint = null;
		int foundY = 0;
		int foundX = 0;
		//Focus is bottom and top range
		int currentObjectRangeLeftPoint_Y = this.bottomPointsRange.getLeftPoint().y;
		int otherObjectRangeLeftPoint_Y = b.topPointsRange.getLeftPoint().y;
		//Find Y-axis collision. since this is a type (x- ranged, consent Y)		
		if(( currentObjectRangeLeftPoint_Y >=  otherObjectRangeLeftPoint_Y) && (prev_location.bottomPointsRange.getLeftPoint().getY() <= otherObjectRangeLeftPoint_Y)){
			foundY = otherObjectRangeLeftPoint_Y;
			//find the first X-axis collision point
			int currentObjectLocation_X = bottomPointsRange.getLeftPoint().x;
			for(int i = 0; i < x_Offset && foundX == 0; i++){
				
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
	//Detect collision with other objects
	///Overlapping collision type of detection
	//Returns - null if no Collision is detected.  or an range type if there is a collision	
	public Point detectCollision (CollisionBox b){
		Point detectionPoint = null;
		
		Iterator<Range> itr_Current_Ranges = this.ranges.iterator();
		while(itr_Current_Ranges.hasNext())
		{
			Point check_Point = itr_Current_Ranges.next().getLeftPoint();
			
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
