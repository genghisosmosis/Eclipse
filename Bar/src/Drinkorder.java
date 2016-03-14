import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Drinkorder {
	private static int totalcost;
	private static int ordercost;
	private static BigDecimal currency;
	static List<Bar> Drinksorder = new ArrayList<Bar>();


	public List Drinklist(Bar bar){
		List<Bar> Drinklist=new ArrayList<Bar>();
		return Drinklist;
	}


	public static void additem(Bar bar){
		if (TillButton.getreversetransaction()){
			Drinksorder.remove(bar);
		}
		else{
			Drinksorder.add(bar);}
		printlist();
	}

	public static void printlist(){
		int totalcost = 0;
		TillDisplay.cleardisplay();
		for (Bar bar:Drinksorder){
			TillDisplay.pushmessage((bar.getServing()) + " " +(bar.getitem()) +" " +(bar.getdisplayprice())+ "0\n");
			totalcost = totalcost + (bar.getprice()*bar.getServeMultiplier());
			float displayprice = (float) totalcost/100;
			BigDecimal currency;
			currency=round(displayprice,2);
			TillDisplay.updateprice("€"+currency);


		}

	}
	public static BigDecimal round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
		return bd;
	}


	public static void cancelorder() {
		Drinksorder.clear();

	}


	public static  void processorder() {
		ordercost = 0;
		boolean valid = false;
		boolean valid2 = false;
		float tenderedamt = 0;
		float change = 0;
		float totalprice = 0;
		float remainingbalance =0;
		TillDisplay.pushmessage("\nTOTAL    :"+TillDisplay.Price.getText()+"\n");
		
		String tendered;
		tendered = JOptionPane.showInputDialog("Please enter amount tendered ");
		for(Bar bar:Drinksorder){
			BarDAO barDAO = new BarDAO();
			ordercost = ordercost + (bar.getprice()*bar.getServeMultiplier());
			totalprice = (float) ordercost/100;
		barDAO.decrement(bar);}
		while (!valid && !valid2){


			try{
				tenderedamt = Float.parseFloat(tendered);
				valid = true;		
					change = (totalprice - tenderedamt);
					remainingbalance = change;
					while (remainingbalance>0) {
						boolean valid3=false;
						while(!valid3){
						try{
						tenderedamt = Float.parseFloat(tendered);
						valid3=true;
						}catch (NumberFormatException e){tendered = JOptionPane.showInputDialog("Please enter amount tendered  \n NUMBERS ONLY PLEASE ");
						};
						
						
						currency=round(tenderedamt,2);
						
						TillDisplay.pushmessage("\nTendered  €"+currency+"\n");
						currency=round(remainingbalance,2);
						TillDisplay.pushmessage("\nBalance to pay  €"+currency+"\n");
						tendered = JOptionPane.showInputDialog("Another  €"+ currency + " please" );
						boolean valid4 = false;
						while (!valid4){
						try{
						tenderedamt = Float.parseFloat(tendered);
						remainingbalance = remainingbalance - tenderedamt;
						 valid4 = true;
						}catch (NumberFormatException e){tendered = JOptionPane.showInputDialog("Please enter NUMBERS!! ony \n Another €"+currency+" required.");}
						};
					valid2 = false;
						}
					}
					valid2=true;
				
			}catch (NumberFormatException e) {tendered = JOptionPane.showInputDialog("Please enter amount tendered \n NUMBERS ONLY PLEASE ");

			};
			
			}
		
		tenderedamt = Float.parseFloat(tendered);
		currency=round(tenderedamt,2);
		TillDisplay.pushmessage("\nTendered  €"+currency+"\n");
		
			currency=round(remainingbalance,2);
			

		
		TillDisplay.pushmessage("\nChange   €"+currency+"\n");
		TillDisplay.updateprice("€"+currency);
		cancelorder();
	
	}
}

