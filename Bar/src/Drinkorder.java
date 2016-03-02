import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
		TillDisplay.pushmessage("\nTOTAL    :"+TillDisplay.Price.getText()+"\n");
		String tendered;
		tendered = JOptionPane.showInputDialog("Please enter amount tendered ");
		TillDisplay.pushmessage("\nTendered  €"+tendered+"\n");
		float tenderedamt = Float.parseFloat(tendered);
		
		for(Bar bar:Drinksorder){
		BarDAO barDAO = new BarDAO();
		ordercost = ordercost + (bar.getprice()*bar.getServeMultiplier());
		float totalprice = (float) ordercost/100;
		float change = (totalprice - tenderedamt);
		
		currency=round(change,2);
		barDAO.decrement(bar);
		
		}
		TillDisplay.pushmessage("\nChange   €"+currency+"\n");
		TillDisplay.updateprice("€"+currency);
		cancelorder();
	}
		
	}

