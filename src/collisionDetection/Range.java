package collisionDetection;
import java.awt.Point;

public class Range {
	
	private Point startPoint;
	private int x_offset;
	private int y_offset;
	//types can be : t, b, r, l
	public RangeType rangeType;

	public Range(Point p, int x, int y, RangeType type)
	{
		startPoint = p;
		x_offset = x;
		y_offset = y;
		
		rangeType = type;	
		
	}
	
	public Point getLeftPoint()
	{
		Point p = new Point();
		switch (rangeType){
		case TOP:
			p = startPoint;
			break;
		case RIGHT:
			p = new Point((int)startPoint.getX() + x_offset, (int)startPoint.getY());
			break;
		case BOTTOM:
			p = new Point((int)startPoint.getX() + x_offset, (int)startPoint.getY() + y_offset);
			break;
		case LEFT:
			p =  new Point((int)startPoint.getX(), (int)startPoint.getY() + y_offset);
			break;
		}
		return p;
	}
	public Point getRightPoint()
	{
		Point p = new Point();
		switch (rangeType){
		case TOP:
			p = new Point((int)startPoint.getX() + x_offset, (int)startPoint.getY());
			break;
		case RIGHT:
			p = new Point((int)startPoint.getX() + x_offset, (int)startPoint.getY() + y_offset);
			break;
		case BOTTOM:
			p = new Point((int)startPoint.getX(), (int)startPoint.getY() + y_offset);
			break;
		case LEFT:
			p = startPoint;
			break;
		}
		return p;
	}
	
	@Override
	public String toString()
	{
		return "Range " + rangeType + ": " + getLeftPoint() + " : " + getRightPoint();
	}
}
