import javax.swing.JOptionPane;

public class Bar {

	public int id;
	public String item;
	public int count;
	public int alert;


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

	public int getid(){
		return id;

	}
	public String getitem() {

		return item;
	}
	public int getcount() {

		return count;
	}
	public int getalert() {

		return alert;
	}


}

