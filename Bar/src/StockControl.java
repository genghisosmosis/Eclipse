import javax.swing.JOptionPane;

public class StockControl {
 static int ri;
 

	public static void addtoDB(){
		BarDAO barDAO = new BarDAO();
		Bar bar = new Bar();
		String newitem;
		String newcount;
		String newalert;

		newitem = JOptionPane.showInputDialog("Please enter item name: ");
		bar.setitem(newitem);
		newcount = JOptionPane.showInputDialog("Please enter stock amount: ");

		int nc =Integer.parseInt(newcount);

		bar.setcount(nc);
		newalert = JOptionPane.showInputDialog("Please enter alert level: ");
		int na = Integer.parseInt(newalert);
		bar.setalert(na);
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
