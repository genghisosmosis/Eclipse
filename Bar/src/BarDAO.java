import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarDAO {

	final String username="root";
	final String password="";
	final String url="jdbc:mysql://localhost/";
	final String db = "softdev2";

	final String connectionUrl= url+""+db+"?"
			+ "user="+username+"&"
			+ "password="+password+"";

	public void insert(Bar bar){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "insert into barstock ( item,alert,beverageclass,unitprice) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, bar.getitem());
			
			ps.setInt(2,bar.getalert());
			ps.setString(3, bar.getbeverageclass());
			ps.setInt(4, bar.getprice());
			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public void decrement(Bar bar){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "UPDATE barstock SET count = (count - ?) WHERE id = ?"; // better to allow negative accounting for emergency drinking sessions

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(2, bar.getid());
			ps.setInt(1, bar.getServeMultiplier());
			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public void delete(Bar bar){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "delete from barstock  where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, bar.getid());

			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}


	public void check(Bar bar) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "select count,item  from barstock where count<alert and id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, bar.getid());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String low=rs.getString("COUNT");
				String product=rs.getString("item");
				System.out.println("Stock level is low for " + product  + ".There are "+ low + " remaining.");

			}
			connection.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}

	public  List<Bar> getAll(){
		List<Bar> bars=new ArrayList<Bar>();
		try{

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			String sql="SELECT barstock.id,barstock.item,barstock.count,barstock.alert,barstock.unitprice,servesize.serving,servesize.servemultiplier,delivery.DeliveryUnit FROM barstock,servesize,delivery where barstock.beverageclass = servesize.BeverageClass and barstock.beverageclass = delivery.BeverageClass order by barstock.item";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				Bar bar = new Bar();
				bar.setid(rs.getInt("id"));
				bar.setitem(rs.getString("Item"));
				bar.setcount(rs.getInt("count"));
				bar.setalert(rs.getInt("alert"));
				bar.setServeMultiplier(rs.getInt("ServeMultiplier"));
				bar.setServing(rs.getString("Serving"));
				bar.setDeliveryUnit(rs.getString("DeliveryUnit"));
				int calculatedprice = rs.getInt("unitprice")*rs.getInt("ServeMultiplier");
				float decimatedprice = (float) calculatedprice/100;
				bar.setdisplayprice("�"+ decimatedprice);
				bar.setprice(rs.getInt("unitprice"));
				bars.add(bar);	

			}
			connection.close();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();

		}

		return bars;
	}
	public String  getminimum(String serving){
		
		String serving1 = new String();
	
		try{

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			String sql="SELECT Serving from servesize where BeverageClass=? and ServeMultiplier =1 ";
			PreparedStatement ps = connection.prepareStatement(sql);

			
			ps.setString(1, serving);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				serving1 = rs.getString("Serving");
			}
			connection.close();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();

		}
		
		return serving1;
		
	}
	public String  getdelunit(String delunit){
		
		

		String delunit1 = new String();
		try{

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			String sql="SELECT DeliveryUnit from delivery where beverageclass=?";
			PreparedStatement ps = connection.prepareStatement(sql);

			
			ps.setString(1, delunit);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				delunit1 = rs.getString("DeliveryUnit");
			}
			connection.close();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();

		}
		
		return delunit1;
		
	}
	public  List<Bar> getshort(){
		List<Bar> lowstocks=new ArrayList<Bar>();
		try{

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			String sql="SELECT barstock.id,barstock.item,round((barstock.count/delivery.DUnitQty),2)as alert,delivery.DeliveryUnit from barstock,delivery where barstock.beverageclass = delivery.BeverageClass and barstock.count <= barstock.alert";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				Bar bar = new Bar();
				bar.setid(rs.getInt("id"));
				bar.setitem(rs.getString("Item"));
				bar.setServing(rs.getString("alert"));
				bar.setDeliveryUnit(rs.getString("DeliveryUnit"));	
				lowstocks.add(bar);	

			}
			connection.close();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();

		}

		return lowstocks;
	}
	
	public List<Bar> getBeverageClassList(){
		List<Bar> BevClassList = new ArrayList<Bar>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			String sql="SELECT distinct beverageclass FROM `servesize`";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				Bar bar = new Bar();
				bar.setbeverageclass(rs.getString("beverageclass"));

				BevClassList.add(bar);	
			}
			connection.close();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}

		return BevClassList;
	}
	public void delivery(Bar bar) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "UPDATE barstock,delivery set barstock.COUNT=barstock.COUNT +(? * delivery.DUnitQty) WHERE barstock.beverageclass = delivery.BeverageClass and barstock.id = ?";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, bar.getdeliveryqty());
			ps.setInt(2, bar.getid());
			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public void increment(Bar bar) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "UPDATE barstock SET COUNT = (COUNT + ?) WHERE id = ?"; 

			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(2, bar.getid());
			ps.setInt(1, bar.getServeMultiplier());
			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public int getdelqty(String delquant) {
		int delquant1 = 0 ;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			String sql="SELECT DUnitQty from delivery where beverageclass=?";
			PreparedStatement ps = connection.prepareStatement(sql);

			
			ps.setString(1, delquant);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				delquant1 = rs.getInt("DUnitQty");
			}
			connection.close();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();

		}
		
		return delquant1;
	}
		
	}








