package ie.gmit.week11;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Set;

@SuppressWarnings("serial")
public class Ship  extends Polygon implements Actor {
	int vx=0;
	int vy=0;
	GameBoard gameBoard;
	
	static final int[] initxPoints = {0,5,5,9,9,14,7};
	static final int[] inityPoints = {10,5,2,2,5,10,15};
	//static final int[] initxPoints = {0,15,15,0};
	//static final int[] inityPoints = {0,0,15,15};
		
	public Ship(GameBoard gameBoard){
		super(initxPoints,inityPoints, inityPoints.length);
		this.translate(gameBoard.initialGameWidth/2, gameBoard.initialGameHeight/2);
		this.gameBoard = gameBoard;
	}
	
	@Override
	public boolean move() {
		
		this.translate(vx, vy);
		
		Rectangle bound = this.getBounds();
		
		if ((bound.x+ bound.getWidth()) < 0){
			this.translate((int) (gameBoard.getWidth()+bound.getWidth()), 0);
		}
		if (bound.x > gameBoard.getWidth()){
			this.translate(-(int) (gameBoard.getWidth()+bound.getWidth()), 0);
		}
		if ((bound.y+ bound.getHeight()) < 0){
			this.translate(0,(int) (gameBoard.getHeight()+bound.getHeight()));
		}
		if (bound.y > gameBoard.getHeight()){
			this.translate(0, -(int) (gameBoard.getHeight()+bound.getHeight()));
		}
		
		for (Actor actor : gameBoard.balls){
			if (actor != this){
				for (int i = 0 ; i< inityPoints.length ;i++){
					if (actor.contains((double)xpoints[i],(double)ypoints[i])){
						gameBoard.gameOver();
					}
				}
			}
		}
		
		return true;
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillPolygon(this);
		
	}
	public void moveUp(boolean yes){
		if (yes) {
			vy = - GameBoard.SHIPSPEED+1;
		}else{
			vy=0;
		}
	}
	public void moveDown(boolean yes){
		if (yes) {
			vy =  GameBoard.SHIPSPEED-1;
		}else{
			vy=0;
		}
	}
	public void moveLeft(boolean yes){
		if (yes) {
			vx =  -GameBoard.SHIPSPEED+1;
		}else{
			vx=0;
		}
	}
	public void moveRight(boolean yes){
		if (yes) {
			vx =  +GameBoard.SHIPSPEED-1;
		}else{
			vx=0;
		}
	}

	
}
