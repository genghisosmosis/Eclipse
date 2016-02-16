package ie.gmit.week11;

public class GameRunner implements Runnable {
	GameBoard gameBoard;
	
	int delay = 1000/70;
	
	public GameRunner(GameBoard gameBoard){
		this.gameBoard = gameBoard;
		
	}
	@Override
	public void run() {
		try {
			
			while(true){
				gameBoard.moveAll();
				Thread.sleep(delay);
			}
			
			
		} catch (InterruptedException e) {
			System.out.println("Game Runner Thread stopped");
			
		}

	}

}
