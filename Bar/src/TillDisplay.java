import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TillDisplay extends JFrame {

	JFrame TillDisplay =new JFrame();
	TillButton tillbutton ;


	public TillDisplay(){
		super();
		CreateUI();
		PlaceComponents();

	}



	private void PlaceComponents() {

		Container mainPane = this.getContentPane();
		mainPane.setLayout(new FlowLayout());

		BarDAO barDAO = new BarDAO();

		List<Bar> tillList= barDAO.getAll();
		for (Bar bar:tillList){

			TillButton TB = new TillButton(bar);
			mainPane.add(TB, BorderLayout.CENTER);
		}



		
	}



	private void CreateUI() {
		this.setSize(800,600);
		this.setResizable(false);
		this.setTitle("BarMan V1.0");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);


	}



}
