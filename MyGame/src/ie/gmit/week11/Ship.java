package ie.gmit.week11;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.ImageIcon;




@SuppressWarnings("serial")
public abstract class Ship  extends Polygon implements Actor {
	int vx=0;
	int vy=0;
	
	private static Image image;
	GameBoard gameBoard;
    static int x = 200;
    static int y = 200;
    
    		

	public Ship(GameBoard gameBoard){
		
		
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
				for (int i = 0 ; i< getY() ;i++){
					if (actor.contains((double)xpoints[i],(double)ypoints[i])){
						Rectangle whereballat = actor.getBounds();
						if ((bound.getCenterX()>whereballat.getCenterX())){vx = GameBoard.SHIPSPEED;}else{
							vx = -GameBoard.SHIPSPEED;
						}
						if ((bound.getCenterY()>whereballat.getCenterY())){vy = GameBoard.SHIPSPEED;}else{
							vy = -GameBoard.SHIPSPEED;
						}





						actor.bounce();
						this.bounce();
						this.gameBoard.shields--;
						Sounds.BLOP.play();
						if(this.gameBoard.shields<40){
							Sounds.KLAXON.play();
						}





						System.out.println(this.gameBoard.shields);
					}
					if (this.gameBoard.shields<= 0){

						gameBoard.gameOver();
					}
				}
			}
		}

		return true;

	}


	
	
	public void moveUp(boolean yes){
		if (yes) {
			vy = - GameBoard.SHIPSPEED;
		}else{
			vy=0;
		}
	}
	public void moveDown(boolean yes){
		if (yes) {
			vy =  GameBoard.SHIPSPEED;
		}else{
			vy=0;
		}
	}
	public void moveLeft(boolean yes){
		if (yes) {
			vx =  -GameBoard.SHIPSPEED;
		}else{
			vx=0;
		}
	}
	public void moveRight(boolean yes){
		if (yes) {
			vx =  GameBoard.SHIPSPEED;
		}else{
			vx=0;
		}
	}

	@Override
	public void bounce() {

		


	}
	  public static int getX() {
	        return x;
	    }

	    public static int getY() {
	        return y;
	    }
	    public static Image getImage() {
	        return image;
	    }


}
