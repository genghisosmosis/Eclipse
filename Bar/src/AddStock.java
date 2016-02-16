import javax.swing.JOptionPane;

public class AddStock {

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
}
