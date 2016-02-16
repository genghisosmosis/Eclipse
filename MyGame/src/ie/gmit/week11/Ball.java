package ie.gmit.week11;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Ball extends java.awt.geom.Ellipse2D.Double implements Actor{
	
	
	private static final Color[] colors = {Color.blue,
			Color.cyan,
			//Color.DARK_GRAY,
			Color.GRAY,
			Color.green,
			Color.MAGENTA,
			Color.ORANGE,
			Color.PINK,
			Color.RED
	};
	
	
	GameBoard gameBoard;
	double size;
	public double vx, absVx;
	public double vy, absVy;
	Color color = Color.red;


	public boolean bouncing;
	
	
	public Ball(GameBoard gameBoard){
		super();
		this.gameBoard = gameBoard;
		
		Random randomGenerator = new Random();
		this.size = 30 + randomGenerator.nextDouble()*70;
		this.width = this.size;
		this.height = this.size;
		this.bouncing = false;
		
	
		
		//this.x = randomGenerator.nextDouble()* (gameBoard.initialGameWidth-this.size);
		//this.y = randomGenerator.nextDouble()* (gameBoard.initialGameHeight-this.size);
		
		// move to an edge
		if (randomGenerator.nextInt(2) ==0){
			// to the side
			
			// this maths puts the ball over the edge so that it comes in from the sides
			// ie 0- ballSize
			// or boardWidth+size-size = boardWidth
			this.x = randomGenerator.nextInt(2)* (gameBoard.initialGameWidth+this.size)-this.size;
			this.y = randomGenerator.nextDouble()* (gameBoard.initialGameHeight-this.size);
			
		}else{
			// top or bottom
			this.x = randomGenerator.nextDouble()* (gameBoard.initialGameWidth-this.size);
			this.y = randomGenerator.nextInt(2)* (gameBoard.initialGameHeight+this.size)-this.size;
			
		}
		
		
		absVx = gameBoard.MAXBALLSPEED * 50 * randomGenerator.nextDouble()/this.size;
		vx = absVx * (-1 + (randomGenerator.nextInt(2)*2)); // absVx * either -1 or 1
		absVy = gameBoard.MAXBALLSPEED * 50 * randomGenerator.nextDouble()/this.size;
		vy = absVy * (-1 + (randomGenerator.nextInt(2)*2)); // absVx * either -1 or 1
		
		color = colors[randomGenerator.nextInt(colors.length)];
		
		gameBoard.balls.add(this);
		
	}
	
	@Override
	public boolean move() {	
	
			this.x += vx;
			this.y += vy;
		
		vx*=1.0001;
		vy*=1.0001;
		
			absVx*=1.0001;
			absVy*=1.0001;	
		
		this.size *=.999 ;
		this.width = this.size;
		this.height = this.size;
		
	
			
	
		if (this.size <5) return false;
		
		if (x< 0 ){vx = absVx;}
		if (x > gameBoard.getWidth()-size ){vx = -absVx;}
		if (y< 0 ){vy = absVy;}
		if (y > gameBoard.getHeight()-size ){vy = -absVy;}
		return true;
		
		
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.color);
		g.fill(this);
		
	}

	@Override
	public void bounce() {
		vx = vx/-1;
		vy = vy/-1;
	
		
	}


	

	
}
