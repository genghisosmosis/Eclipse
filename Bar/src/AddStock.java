import javax.swing.JOptionPane;

public class AddStock {

	public static void addtoDB(){
		BarDAO barDAO = new BarDAO();
		Bar bar = new Bar();
		String newitem;
		String itemprice;
		String newalert;

		newitem = JOptionPane.showInputDialog("Please enter item name: ");
		bar.setitem(newitem);
		itemprice = JOptionPane.showInputDialog("Please enter cost for smallest serve size in cents: ");

		int np =Integer.parseInt(itemprice);

		bar.setprice(np);
		newalert = JOptionPane.showInputDialog("Please enter alert level: ");
		int na = Integer.parseInt(newalert);
		bar.setalert(na);
		barDAO.insert(bar);
	}
}
