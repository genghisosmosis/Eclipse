import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MgmtPanel extends JPanel{
	JButton Addstockitem,Login,Logout,Newsale,Cancelsale,Delivery,Delete,CancelItem;
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
				Logout.setEnabled(true);
				Login.setEnabled(false);
				Newsale.setEnabled(false);
				Addstockitem.setEnabled(true);
				Delivery.setEnabled(true);
				Delete.setEnabled(true);
				
				UserControl.login();



			}
		});
		this.add(Login);
		
		CancelItem = new JButton("Cancel item");
		CancelItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				TillButton.setreversetransaction(true);
				TillDisplay.pushmessage("The next item is cancelled\n");
			}
		});
		this.add(CancelItem);

		Logout = new JButton("Manager log out");
		Logout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				onLogoutClicked(ae);
			}


			private void onLogoutClicked(ActionEvent ae) {
				UserControl.logout();
				TillDisplay.setinterface(false);
				TillButton.setdeletemode(false);
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
				TillDisplay.cleardisplay();
				TillDisplay.pushmessage("New drink order\n");

			}


			private void onAddstockitemClicked(ActionEvent ae) {
				UserControl.logout();


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
				Newsale.setEnabled(true);
				Cancelsale.setEnabled(false);
			}


			private void onAddstockitemClicked(ActionEvent ae) {
				UserControl.logout();


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
				Newsale.setEnabled(false);
				Delivery.setEnabled(false);
				Delete.setEnabled(false);
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
			Addstockitem.setEnabled(false);
			Delivery.setEnabled(false);
			TillButton.setdeletemode(true);
			TillDisplay.setinterface(true);
			

		}
	});
	this.add(Delete);

	}
	
}

