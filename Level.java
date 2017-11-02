import java.awt.Point;

import collisionDetection.CollisionBox;
import collisionDetection.CompoundCollisionBoxes;
import processing.core.*;
import utils.IndexTracker;

public class Level {
	
	PApplet parent;
	private int[][] _levelData;
	  
	//title sizes  
	public int titleWidth = 180;
	public int titleHeight = 160;
	// Number that is divided by titleHeight to cut off bottom title
	private int limitDrawHeight = 6; 
	//A parallel class of arrays to keep track of the current active titles that are in the current level
	IndexTracker in;
	
	//TODO: Give level an ID

	public CompoundCollisionBoxes col_boxes;
	
	//check if the a player has hit a winning title
	//TODO: only a temp if more features are added then adjust winningTitle
	boolean winningTitle;
	
	public Level(PApplet p, int[][] lData)
	{
		parent = p;
		winningTitle = false;
		_levelData = lData;
		//TODO: add a better way to detect the length for both classes!
		//temp start numbers
		col_boxes = new CompoundCollisionBoxes(50);
		in = new IndexTracker(50);
		//
		//populate a baseline of collisionBoxes
		for(int ctr = 0; ctr < _levelData.length ; ctr++)
		{
			for (int ctr2 = 0; ctr2 < _levelData[ctr].length; ctr2++)
			{
				if(_levelData[ctr][ctr2] > 0)
		        {
					Point newPoint = new Point(ctr2 * titleWidth,(ctr * titleHeight) + (titleHeight) - 10);
					col_boxes.add( new CollisionBox (newPoint, null, titleWidth, (int)(titleHeight/limitDrawHeight)));
					in.add(ctr, ctr2);
		        }
			}
		}
		
	}
	public void onCollisionLevel(Point collisionPoint, String objectName, int atIndex){
		if(objectName.equals("Player")){
			if(_levelData[in.getIndexOne(atIndex)][in.getIndexTwo(atIndex)] == 3){
				winningTitle = true;
			}
		}
	}
	
	public Boolean checkWinningTitleHit(){
		return winningTitle;
	}
	public void LevelDraw()
	  {   
	    for(int ctr = 0; ctr < _levelData.length ; ctr++)
	    {
	      for (int ctr2 = 0; ctr2 < _levelData[ctr].length; ctr2++)
	      {
	        if(_levelData[ctr][ctr2] == 1)
	        {
	        	parent.fill(125, 2, 2); // maybe add option to color
	        	parent.rect(ctr2 * titleWidth, (ctr * titleHeight) + (titleHeight) -10, titleWidth, (titleHeight/limitDrawHeight));
	        }
	        if(_levelData[ctr][ctr2] == 3){
	        	parent.fill(200, 500, 20); // maybe add option to color
	        	parent.rect(ctr2 * titleWidth, (ctr * titleHeight) + (titleHeight) -10, titleWidth, (titleHeight/limitDrawHeight));
	        }
	      }
	    }
	  }
	  
	public int[][] getLevelData()
	  {
	    return _levelData;
	  }

	}
