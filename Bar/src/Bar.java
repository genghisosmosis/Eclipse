import javax.swing.JOptionPane;

public class Bar {

	public int id;
	public String item;
	public int count;
	public int alert;
	public String Serving;
	public int ServeMultiplier;
	public String beverageclass;
	public int deliveryqty;
	public String DeliveryUnit;
	public int price;
	public String displayprice;
	public void setid(int id){
		this.id=id;
	}
	public void setbeverageclass(String beverageclass){
		this.beverageclass=beverageclass;
	}

	public void setitem(String item) {
		this.item=item;
	}
	public void setprice(int price){
		this.price=price;
	}
	public void setdisplayprice(String displayprice){
		this.displayprice=displayprice;
	}

	public void setDeliveryUnit(String DeliveryUnit){
		this.DeliveryUnit=DeliveryUnit;
	}
	public void setdeliveryqty(int deliveryqty){
		this.deliveryqty=deliveryqty;
	}
	public void setcount(int count) {
		this.count=count;
	}
	public void setalert(int alert){
		this.alert=alert;

	}
	public void setServing(String Serving){
		this.Serving=Serving;
	}

	public void setServeMultiplier(int ServeMultiplier){
		this.ServeMultiplier=ServeMultiplier;
	}
	public int getid(){
		return id;

	}
	public String getitem() {

		return item;
	}
	public String getServing(){
		return Serving;

	}
	public String getdisplayprice(){
		return displayprice;
	}
	public String getDeliveryUnit(){
		return DeliveryUnit;
	}
	public int getcount() {

		return count;
	}
	public int getalert() {

		return alert;
	}
	public int getServeMultiplier(){
		return ServeMultiplier;

	}
	public String getbeverageclass(){
		return beverageclass;
	}
	public int getdeliveryqty() {


		return deliveryqty;
	}
	public int getprice(){
		return price;
	}

}

