import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MgmtPanel extends JPanel{
	JButton Addstockitem,Login,Logout,Newsale,Cancelsale,Delivery,Delete,CancelItem,Confirm,Check;
	//TillButton TillButton;
	public MgmtPanel(){
		super();
		generatecontent();
		initialise_state();


	}

	private void initialise_state() {
		Addstockitem.setEnabled(false);
		Login.setEnabled(true);
		Logout.setEnabled(false);
		Newsale.setEnabled(true);
		Cancelsale.setEnabled(false);
		Delivery.setEnabled(false);
		Delete.setEnabled(false);
		CancelItem.setEnabled(false);
		Confirm.setEnabled(false);
		Check.setEnabled(false);
		TillButton.setdeliverymode(false);
	}

	private void generatecontent() {
		this.setBackground(Color.BLACK);
		this.setLayout(new GridLayout(6,2));
		Addstockitem = new JButton("Add stock item");
		Addstockitem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				onAddstockitemClicked(ae);
			}


			private void onAddstockitemClicked(ActionEvent ae) {
				TillDisplay.pushmessage("************ENTRY MODE**************\n");
				StockControl.addtoDB();


			}
		});
		this.add(Addstockitem);

		Login = new JButton("Manager log in");
		Login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				onLoginClicked(ae);
			}


			private void onLoginClicked(ActionEvent ae) {
				setMgmtmode();
				TillDisplay.cleardisplay();
				TillDisplay.pushmessage("Management mode\n");
			}
		});
		this.add(Login);
		Check = new JButton("Low stock check");
		Check.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				TillDisplay.pushmessage("************LOW STOCK CHECK**************\n");
				StockControl.createshortlist();
			}
		});
		this.add(Check);
		CancelItem = new JButton("Cancel item");
		CancelItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				TillButton.setreversetransaction(true);
				TillDisplay.pushmessage("The next item is cancelled\n");
			}
		});
		this.add(CancelItem);
		Confirm  = new JButton("Confirm Sale");
		Confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				CancelItem.setEnabled(false);
				Cancelsale.setEnabled(false);
				Confirm.setEnabled(false);
				Newsale.setEnabled(true);
				
				Drinkorder.processorder();
			}
		});
		this.add(Confirm);
		Logout = new JButton("Manager log out");
		Logout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				onLogoutClicked(ae);
			}


			private void onLogoutClicked(ActionEvent ae) {
				UserControl.logout();
				TillDisplay.cleardisplay();
				TillDisplay.setinterface(false);
				TillButton.setdeletemode(false);
				Drinkorder.cancelorder();
				initialise_state();

			}
		});
		this.add(Logout);

		Newsale = new JButton("New Sale");
		Newsale.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				onNewsaleClicked(ae);
			}


			private void onNewsaleClicked(ActionEvent ae) {
				TillDisplay.ResetUI();
				TillDisplay.setinterface(true);
				Newsale.setEnabled(false);
				Cancelsale.setEnabled(true);
				CancelItem.setEnabled(true);
				Confirm.setEnabled(true);
				TillDisplay.cleardisplay();
				TillDisplay.pushmessage("New drink order\n");
				TillDisplay.clearprice(null);
			}
		});
		
		this.add(Newsale);
		
		Cancelsale = new JButton("Cancel Sale");
		Cancelsale.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				onCancelsaleClicked(ae);
			}


			private void onCancelsaleClicked(ActionEvent ae) {
				TillDisplay.setinterface(false);
				TillDisplay.clearprice(null);
				TillDisplay.cleardisplay();
				Drinkorder.cancelorder();
				Newsale.setEnabled(true);
				Cancelsale.setEnabled(false);
				CancelItem.setEnabled(false);
				Confirm.setEnabled(false);
			}

		});
		this.add(Cancelsale);
		Delivery = new JButton("Delivery");
		Delivery.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				onDeliveryClicked(ae);

			}

			private void onDeliveryClicked(ActionEvent ae) {
				TillDisplay.pushmessage("*********DELIVERY MODE*************\n");
				TillDisplay.pushmessage("Select button for delivered product\n");
				Newsale.setEnabled(false);
				Delivery.setEnabled(false);
				Delete.setEnabled(false);
				Addstockitem.setEnabled(false);
				TillButton.setdeliverymode(true);
				TillDisplay.ResetUI();
				TillDisplay.setinterface(true);



			}
		});
		this.add(Delivery);
	
	Delete = new JButton("Delete");
	Delete.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent ae) {
			onDeleteClicked(ae);
		}


		private void onDeleteClicked(ActionEvent ae) {
			TillDisplay.pushmessage("**************Delete mode************\n");
			TillDisplay.pushmessage("Select button of product for deletion\n");
			Addstockitem.setEnabled(false);
			Delivery.setEnabled(false);
			TillButton.setdeletemode(true);
			TillDisplay.setinterface(true);
			
			

		}
	});
	this.add(Delete);

	}
	private void setMgmtmode(){
		Addstockitem.setEnabled(true);
		Login.setEnabled(false);
		Logout.setEnabled(true);
		Newsale.setEnabled(false);
		Cancelsale.setEnabled(false);
		Delivery.setEnabled(true);
		Delete.setEnabled(true);
		CancelItem.setEnabled(false);
		Confirm.setEnabled(false);
		Check.setEnabled(true);
		TillButton.setdeliverymode(false);
		TillDisplay.setinterface(false);
	}
}

