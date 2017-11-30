import processing.core.PApplet;
import gameObjects.KillerBox;
import gameObjects.Player;


public class MainGameManager extends PApplet {

	int[][] TestDataOne = {
	                 {1,1,0,0,1,0,1,0,1,3},
	                 {1,0,0,0,0,0,0,0,0,0},
	                 {1,0,0,1,0,0,1,1,0,1},
	                 {0,0,0,0,0,0,0,0,1,0},
	                 {0,0,0,0,1,0,0,1,0,0},
	                 {1,0,1,1,0,0,0,0,0,0}}; 
	Level level;   		
	Player player;
	PhyscisAffectManager p;
	//test
	KillerBox kB1;
	KillerBox kB2;
	KillerBox kB3;
	KillerBox kB4;
	KillerBox kB5;
	//end test
	char keyPressed1;
	char keyPressed2;
	char keyPressed3;
	int playermoveAmmount_X = 9;
	public static void main(String[] args) 
	{		
		PApplet.main("MainGameManager");
	}
	
	public void settings()
	{
		size(1920,1080);		
		smooth(5);
		//fullScreen();
	}
	
	public void setup()
	{  
		frameRate(60);
		
		gameStart();
	}

	public void gameStart(){
		player = new Player(this, "../assets/pep.png", 7, 7, playermoveAmmount_X);
		kB1 = new KillerBox(this, 0, 495, 90, 20, 20);
		kB2 = new KillerBox(this, 1600, 100, 60, 50, 30);
		kB3 = new KillerBox(this, 0, 450, 90, 20, 20);
		kB4 = new KillerBox(this, 0, 710, 90, 20, 20);
		kB5 = new KillerBox(this, 0, 290, 90, 20, 20);
		level = new Level(this, TestDataOne);
		p = new PhyscisAffectManager(1);
		p.setObjectAffect(player);
	}
	public void draw()
	{
		background(200,200,200);
		
		p.detectCollisionWithLevel(player, level);
		player.movePlayer(keyPressed1, keyPressed2, keyPressed3);
		//killer boxes move
		kB1.move();
		kB2.move();
		kB3.move();
		kB4.move();
		kB5.move();
		//collision detection on collision boxes
		p.detectCollisionWithOtherObject(player, kB1);
		p.detectCollisionWithOtherObject(player, kB2);
		p.detectCollisionWithOtherObject(player, kB3);
		p.detectCollisionWithOtherObject(player, kB4);
		p.detectCollisionWithOtherObject(player, kB5);
		//draws
		level.LevelDraw();
		kB1.drawKillerBox();
		kB2.drawKillerBox();
		kB3.drawKillerBox();
		kB4.drawKillerBox();
		kB5.drawKillerBox();
		player.DrawPlayer();
		
		//affects 
		p.Gravity();
		//checks
		if(CheckWinState())
		{
			//temp
			noLoop();
		}else{
			CheckGameOverState();
		}
		
		
	}
	
	public boolean CheckWinState()
	{
		//only does a title hit for now
		if (level.checkWinningTitleHit() == true)
		{
			//display faded screen
			fill(0,0,100, 150);
			rect(0,0, width, height);
			//winning text
			textSize(100);
			fill(255, 255, 255);
			text("You Win!", (width/2) - 200, height/2);
			
			
			//TODO: add next level, restart etc more here
			
			return true;
		}
		return false;
	}
	
	public boolean CheckGameOverState(){
		if(player.checkPlayerHasFallen() == true){
			//display faded screen
			fill(0,0,0, 150);
			rect(0,0, width, height);
			//restart text
			textSize(100);
			fill(255, 255, 255);
			text("Restart?", (width/2) - 200, height/2);
			
			
			textSize(80);
			//checks if mouse is hovering over the yes text
			if((mouseX > (width/2) - 200) && mouseX < (width/2) && mouseY > (height/2) && mouseY < (height/2) + 200){
				fill(255,0,0);
				if(mousePressed == true){
					restart();
				}
			}
			else{
				fill(255, 255, 255);
			}
			text("Yes", (width/2) - 200,  (height/2) + 200, 80);
			
			//checks if mouse is hovering over the no text
			if((mouseX > (width/2) + 100) && mouseX < (width/2) + 200 && mouseY > (height/2) && mouseY < (height/2) + 200){
				fill(255,0,0);
				if(mousePressed == true){
					exit();
				}
			}
			else{
				fill(255, 255, 255);
			}
			text("No", (width/2) + 100,  (height/2) + 200);
			return true;
		}
		return false;
	}
	
	public void restart(){
		player = null;
		level = null;
		p = null;
		kB1 = null;
		kB2 = null;
		kB3 = null;
		kB4 = null;
		gameStart();
	}
	
	public void keyPressed(){
		//Calls the player move function a either WASD is pressed
		//TODO: allow the rebind of keys
		if( key == 'd' )
		{
			keyPressed1 = key;
		}
		if(key == 'a'){
			
			keyPressed2 = key;
		}
		else if(key == 'w')
		{
			keyPressed3 = key;
		}
	}
	public void keyReleased(){
		
		if( key == 'd' )
		{
			keyPressed1 = 0;
		}
		if(key == 'a'){
			
			keyPressed2 = 0;
		}
		else if(key == 'w')
		{
			keyPressed3 = 0;
		}
	}
	

	
}
