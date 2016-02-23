import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TillButton extends JButton{

	Bar bar;

	public TillButton(Bar bar){
		super(bar.Serving +" "+bar.item);
		this.bar = bar;
		
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				onButtonClicked(ae);
			}
		});
	}


	public void onButtonClicked(ActionEvent ae) {
		BarDAO barDAO = new BarDAO();
		
		barDAO.decrement(bar);
		barDAO.check(bar);

	

	}

}
