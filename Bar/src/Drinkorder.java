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
			totalcost = totalcost - (bar.getprice()*bar.getServeMultiplier());

			Drinksorder.remove(bar);
		}

		else{
			Drinksorder.add(bar);
			totalcost = totalcost + (bar.getprice()*bar.getServeMultiplier());}

		float displayprice = (float) totalcost/100;
		BigDecimal currency;
		currency=round(displayprice,2);



		TillDisplay.updateprice("€"+currency);
		TillDisplay.cleardisplay();
		printlist();


	}

	public static void printlist(){
		for (Bar bar:Drinksorder){
			TillDisplay.pushmessage(bar.getitem()+" "+bar.getServing()+" "+bar.getServeMultiplier()+"\n");


		}

	}
	public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
        return bd;
    }
}
