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
	private static final double newBallEvery = 4000;
	private static final int initialBalls = 2;
	static final double MAXBALLSPEED = 2;
	static final int SHIPSPEED = 2;
	static final int FUELUSE = 1;
	public int replenish = 100;
	int initialGameWidth;
	int initialGameHeight;
	int shields  = 100;
	int multiplier = 1;
	double timeLastBallAdded;
	double timeGameStarted;
	boolean gameOver = false;
	
	int Hiscore = 0;

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
		
		gameOver = false;
		
		
		actors = new HashSet<Actor>() ;
		newActors = new LinkedList<Actor>() ;
		balls = new HashSet<Actor>() ;
		for(int i = 0; i< initialBalls; i++){
			actors.add( new Ball(this));

		}
		score =0;
		shields=100;
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

	
		for(Actor actor : actors){
			actor.draw(g2d);
		}
		g2d.setColor(Color.WHITE);
		g2d.setFont(fontScore);
		g2d.drawString("Hi-"+ Hiscore + " Score "+ score + " Shields "+ shields +" %", 20, 20);
		if (gameOver){
			Sounds.ENGINE.play();
			g2d.setColor(Color.WHITE);
			Font font = new Font("Courier", Font.BOLD, 48);
			g2d.setFont(font);
			int locX = this.getWidth()/2 -120;
			int locY = this.getHeight()/2;
			
			g2d.drawString("GAME OVER", locX, locY-30);
			g2d.drawString("Click mouse", locX-40, locY+40);
			g2d.drawString(" to start", locX-40, locY+80);
			if (score>Hiscore){
				g2d.drawString("New Hiscore!", locX-45, locY-90);
				
				Hiscore = score;
			}
		}
		
		
	
		
	}
	public void moveAll(){
	
		double elapsedTime = System.currentTimeMillis()- timeLastBallAdded;
		if (elapsedTime > newBallEvery){
			timeLastBallAdded = System.currentTimeMillis();
			actors.add(new Ball(this));
		}

		Iterator<Actor> itr = actors.iterator();
		
		while (itr.hasNext()){
			Actor actor = itr.next();
			if (!actor.move()){
				itr.remove();
			
			};
		}

		while(!newActors.isEmpty()){
			actors.add(newActors.remove());
		}

	
		this.repaint();

	}
	public void shipUp(boolean yes){
		ship.moveUp(yes);
		shields=shields-FUELUSE	;
	}
	public void shipDown(boolean yes){
		ship.moveDown(yes);
		shields=shields-FUELUSE;
	}
	public void shipLeft(boolean yes){
		ship.moveLeft(yes);
		shields=shields-FUELUSE;
	}
	public void shipRight(boolean yes){
		ship.moveRight(yes);
		shields=shields-FUELUSE;
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
		multiplier = score / 100;
		if (multiplier<=50){
		shields = 100 - multiplier;}
		else {shields = shields + 10;}
		
		Sounds.KERCHING.play();

		
		newActors.add(new Ore(this));

	}




}
