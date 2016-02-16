package ie.gmit.week11;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;




public class Ore  extends Polygon implements Actor{

	GameBoard gameBoard;
	
	static final int[] initxPoints = {0,5,5,0};
	static final int[] inityPoints = {0,0,5,5};

	public Ore(GameBoard gameBoard){
		super(initxPoints,inityPoints, inityPoints.length);
		this.gameBoard = gameBoard;

		Random randomGenerator = new Random();

		int xPos = randomGenerator.nextInt(gameBoard.initialGameWidth-20); 
		int yPos = randomGenerator.nextInt(gameBoard.initialGameHeight-20); 
		if (xPos>(gameBoard.initialGameWidth/2)){xPos=xPos-20;}
		if (yPos>(gameBoard.initialGameHeight/2)){yPos=yPos-20;}


		this.translate(xPos, yPos);

	}

	@Override
	public boolean move() {
		for (int i = 0 ; i< xpoints.length ;i++){
			if (gameBoard.ship.contains((double)xpoints[i],(double)ypoints[i])){
				gameBoard.gotOre(this);

				return false;
			}
		}
		return true;
	}




	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(255,150,150));
		g.fillPolygon(this);
	}

	@Override
	public void bounce() {
		

	}



}
