import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Productpanel extends JPanel {


	public Productpanel(){
		super();
		placecomponents();



	}

	private void placecomponents() {

		
		this.setLayout(new FlowLayout());
		this.setSize(800,600);
		this.setBackground(Color.BLACK);
		
		BarDAO barDAO = new BarDAO();

		List<Bar> tillList= barDAO.getAll();
		for (Bar bar:tillList){

			TillButton TB = new TillButton(bar);
		
			this.add(TB);
			
			
		}
		
	}

	public void redo() {
		removeAll();
		placecomponents();
		
		
		
	}
	
}


