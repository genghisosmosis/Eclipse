package ie.gmit.week11;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyJFrame extends JFrame {
	private static int keyUp = 38;
	private static int keyDown = 40;
	private static int keyLeft = 37;
	private static int keyRight = 39;
	
	
	int windowWidth = 400;
	int windowHeight = 400;
	
	GameBoard gameBoard = new GameBoard(windowWidth, windowHeight);
	
	public MyJFrame(){
		super();
		initUI();
		placeComponents();
		bindListeners();
	}

	private void initUI() {
		this.setTitle("game");
		this.setSize(windowWidth+10, windowWidth+10);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}

	private void placeComponents() {
		this.getContentPane().add(gameBoard);
		
	}

	private void bindListeners() {
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				gameBoard.onMouseClicked(e);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				onKey(e, true);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				onKey(e, false);
				
			}
			
		});
		
	}
	private void onKey(KeyEvent e, boolean yes){
		int keyCode = e.getKeyCode();
		if (keyCode == this.keyUp){
			gameBoard.shipUp(yes);
		}
		if (keyCode == this.keyDown){
			gameBoard.shipDown(yes);
		}
		if (keyCode == this.keyLeft){
			gameBoard.shipLeft(yes);
		}
		if (keyCode == this.keyRight){
			gameBoard.shipRight(yes);
		}
		
	}
	

}
