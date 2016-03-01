import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Drinkorder {
	private static int totalcost;


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
			
			TillDisplay.pushmessage(bar.getitem()+" "+bar.getServing()+" "+bar.getServeMultiplier()+"\n");
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
}
