import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TillButton extends JButton{
	private static boolean deliverymode;
	private static boolean deletemode;
	private static boolean reversetransaction;
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
		if (!deliverymode){
		//	BarDAO barDAO = new BarDAO();

		//	barDAO.decrement(bar);
			TillDisplay.pushmessage((this.bar.getServing()) + " " +(this.bar.getitem()) +" " +(this.bar.getdisplayprice())+ "0\n");
			Drinkorder.additem(bar);
		//	barDAO.check(bar);
		}else{
			String newcount;
			BarDAO barDAO=new BarDAO();
			newcount = JOptionPane.showInputDialog("Please enter number of "+(this.bar.getDeliveryUnit()) +"s of "+(this.bar.getitem())+" delivered");
			Bar bar = new Bar();
			bar.setid(this.bar.getid());
			System.out.println(bar.id);
			int dq = Integer.parseInt(newcount);
			System.out.println(dq);
			bar.setdeliveryqty(dq);
			barDAO.delivery(bar);


		}
		
		if (deletemode){
			BarDAO barDAO=new BarDAO();
			Bar bar = new Bar();
			bar.setid(this.bar.getid());
			barDAO.delete(bar);
			this.setVisible(false);
		}
		if (reversetransaction){
			BarDAO barDAO=new BarDAO();
			barDAO.increment(bar);
			setreversetransaction(false);
			
			
		}
	}



	public static void setdeliverymode(boolean dm){
		deliverymode=dm;

	}
	public static void setdeletemode(boolean delx){
		deletemode=delx;
	}



	public static void setreversetransaction(boolean reverse) {
		reversetransaction=reverse;
		
		
	}
	public static boolean getreversetransaction(){
		return reversetransaction;
		
	}
}
