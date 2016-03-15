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
		String itemprice = null;
		String newalert;
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Select Class");
		panel.add(label);
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
		newitem = JOptionPane.showInputDialog("Please enter item name: ");
		bar.setitem(newitem);
		BarDAO getserv = new BarDAO();
		String serving = getserv.getminimum(bar.getbeverageclass());
		BarDAO getship = new BarDAO();
		String delunit = getship.getdelunit(bar.getbeverageclass());


		boolean valid = false;
		int np=0;
		itemprice = JOptionPane.showInputDialog("Please enter cost for a "+serving +" in cents: ");
		while (!valid){

			

			try{
				np =Integer.parseInt(itemprice);
				valid = true ;
			}catch (NumberFormatException e){
				itemprice = JOptionPane.showInputDialog("Please enter cost for a "+serving +" in cents:\n ENTER A NUMBER ONLY ");
			};
		}

		bar.setprice(np);
		valid = false;
		int na=0;
		BarDAO getdelquant  = new BarDAO();
		int delquant = getdelquant.getdelqty(bar.getbeverageclass());
		
		newalert = JOptionPane.showInputDialog("What is the minimum number of " + delunit +"s of "+newitem+ " before alerting you?");
		while (!valid){
			try{
				na = Integer.parseInt(newalert);
				valid=true;
			}catch (NumberFormatException e){
				newalert = JOptionPane.showInputDialog("What is the minimum number of " + delunit +"s of "+newitem+ " before alerting you?\n ENTER A NUMBER ONLY");
			}
		}

		
		int alertlevel = na*delquant;
		bar.setalert(alertlevel);





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

	public static void createshortlist(){
		BarDAO barDAO = new BarDAO();

		List<Bar> shortages= barDAO.getshort();
		for (Bar bar:shortages){

			TillDisplay.pushmessage("There are "+bar.getServing() + " " + bar.getDeliveryUnit() + "s of " + bar.getitem() + " remaining.\n");
		}
	}



}
