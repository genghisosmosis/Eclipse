import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StockControl {
	static int ri;




	public static void addtoDB(){
		BarDAO barDAO = new BarDAO();
		Bar bar = new Bar();
		String newitem;
		String itemprice;
		String newalert;
		String newbeverageclass;


		newitem = JOptionPane.showInputDialog("Please enter item name: ");
		bar.setitem(newitem);
		itemprice = JOptionPane.showInputDialog("Please enter cost for smallest serve size in cents: ");

		int np =Integer.parseInt(itemprice);

		bar.setprice(np);
		
		newalert = JOptionPane.showInputDialog("Please enter alert level: ");
		int na = Integer.parseInt(newalert);
		bar.setalert(na);

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Select Class");
		panel.add(label);

		BarDAO BevClassDAO= new BarDAO();

		List <Bar> BevClist=barDAO.getBeverageClassList();
		ArrayList<BevclassDialog> radioButtons = new ArrayList<BevclassDialog>();
		for (Bar bard:BevClist){
			BevclassDialog RB = new BevclassDialog(bard);
			radioButtons.add(RB);
			panel.add(RB);

		}

		JOptionPane.showMessageDialog(null, panel);
		for (int i=0;i<radioButtons.size();i++){
			BevclassDialog button = radioButtons.get(i);
			if (button.isSelected()){
				bar.setbeverageclass(button.bar.beverageclass);

			}
		}

		barDAO.insert(bar);

	}

	public static void removefromDB(){
		BarDAO barDAO = new BarDAO();
		Bar bar = new Bar();
		String remitem;
		remitem = JOptionPane.showInputDialog("Enter stock code for decrement");

		ri = Integer.parseInt(remitem);
		bar.setid(ri);
		barDAO.decrement(bar);
		barDAO.check(bar);




	}

	public static void createlist(){
		BarDAO barDAO = new BarDAO();
		Bar bar = new Bar();
		//   barDAO.list(bar);
	}



}
