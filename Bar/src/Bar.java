import javax.swing.JOptionPane;

public class Bar {

	public int id;
	public String item;
	public int count;
	public int alert;
	public String Serving;
	public int ServeMultiplier;

	public void setid(int id){
		this.id=id;
	}


	public void setitem(String item) {
		this.item=item;


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
	public int getcount() {

		return count;
	}
	public int getalert() {

		return alert;
	}
public int getServeMultiplier(){
	return ServeMultiplier;
	
}

}

