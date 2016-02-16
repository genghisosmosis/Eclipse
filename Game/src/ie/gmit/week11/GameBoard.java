package ie.gmit.week11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.swing.JPanel;

public class GameBoard extends JPanel {
	private static final double newBallEvery = 2000;
	private static final int initialBalls = 2;
	static final double MAXBALLSPEED = 2;
	static final int SHIPSPEED = 3;
	int initialGameWidth;
	int initialGameHeight;
	double timeLastBallAdded;
	double timeGameStarted;
	boolean gameOver = false;
	Set<Actor> actors ;
	Queue<Actor> newActors ;
	
	Set<Actor> balls ;
	Ship ship;
	int score = 0;
	Font fontScore = new Font("Courier", Font.BOLD, 20);
	
	
	Thread thread;
	
	public GameBoard(int initialGameWidth, int initialGameHeight){
		super();
		this.initialGameWidth = initialGameWidth;
		this.initialGameHeight = initialGameHeight;
		this.setBackground(Color.black);
		
		startGame();
		
	}
	
	public void startGame(){
		gameOver=false;
		actors = new HashSet<Actor>() ;
		newActors = new LinkedList<Actor>() ;
		balls = new HashSet<Actor>() ;
		for(int i = 0; i< initialBalls; i++){
			actors.add( new Ball(this));
			
		}
		score =0;
		ship = new Ship(this);
		actors.add(ship);
		
		actors.add(new Ore(this));
		thread = new Thread(new GameRunner(this));
		thread.start();
		timeLastBallAdded = System.currentTimeMillis();
		timeGameStarted = System.currentTimeMillis();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(fontScore);
		g2d.drawString(""+score, 100, 20);
		
		for(Actor actor : actors){
			actor.draw(g2d);
		}
		if (gameOver){
			g2d.setColor(Color.WHITE);
			Font font = new Font("Courier", Font.BOLD, 48);
			g2d.setFont(font);
			int locX = this.getWidth()/2 -120;
			int locY = this.getHeight()/2;
			g2d.drawString("GAME OVER", locX, locY);
		}
	}
	public void moveAll(){
		double elapsedTime = System.currentTimeMillis()- timeLastBallAdded;
		if (elapsedTime > newBallEvery){
			timeLastBallAdded = System.currentTimeMillis();
			actors.add(new Ball(this));
		}
		
		Iterator<Actor> itr = actors.iterator();
		System.out.println(actors);
		while (itr.hasNext()){
			Actor actor = itr.next();
			if (!actor.move()){
				itr.remove();
				System.out.println("remove actor "+actor.getClass()+":"+actor);
			};
		}
		while(!newActors.isEmpty()){
			actors.add(newActors.remove());
		}
		
		//for(Actor actor : actors){
		//}
		//score = (int)(System.currentTimeMillis()- timeGameStarted);
		this.repaint();
		
	}
	public void shipUp(boolean yes){
		ship.moveUp(yes);	
	}
	public void shipDown(boolean yes){
		ship.moveDown(yes);
	}
	public void shipLeft(boolean yes){
		ship.moveLeft(yes);
	}
	public void shipRight(boolean yes){
		ship.moveRight(yes);
	}
	public void gameOver(){
		gameOver=true;
		System.out.println("game over");
		thread.interrupt();
	}
	public void onMouseClicked(MouseEvent me){
		if (gameOver){
			this.startGame();
		}
	}

	public void gotOre(Ore ore) {
		score+= 100;
		//actors.remove(ore); can not do this here we need to use iterator.remove
		newActors.add(new Ore(this));
		
	}

	
	

}
