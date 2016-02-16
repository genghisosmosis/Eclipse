import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TillDisplay extends JFrame {

	JFrame TillDisplay =new JFrame();
	
	
	
	public TillDisplay(){
		super();
		CreateUI();
		PlaceComponents();
		
	}



	private void PlaceComponents() {
 Container mainPane = this.getContentPane();
 JButton tillbutton = new TillButton();
 mainPane.add(tillbutton, BorderLayout.CENTER);
		
	}



	private void CreateUI() {
		this.setSize(800,600);
		this.setResizable(false);
		this.setTitle("BarMan V1.0");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
		
	

}
