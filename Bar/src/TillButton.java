import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TillButton extends JButton{
	int tillid;
	int tillcount;
	int tillitem;
	int tillalert;
Stock stockitem;

	public TillButton(Stock stockitem){


		JButton TB = new JButton();
		this.tillid = 7;
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				on7Clicked(ae);

			}




		});
	}


	public void on7Clicked(ActionEvent ae) {
		BarDAO barDAO = new BarDAO();
		Bar bar = new Bar();
		bar.setid(tillid);
		barDAO.update(bar);
		barDAO.check(bar);





		//StockControl.removefromDB();

		//StockControl.createlist();

	}

}
