import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			String sql= "insert into barstock ( item, count,alert) values (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, bar.getitem());
			ps.setInt(2, bar.getcount());
			ps.setInt(3,bar.getalert());

			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public void update(Bar bar){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			//String sql= "UPDATE barstock SET count = IF(count > 0, count - 1, 0) WHERE id = ?"; //stops at zero
			String sql= "UPDATE barstock SET count = (count - 1) WHERE id = ?"; // better to allow negative accounting for emergency drinking sessions
			
			PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, bar.getid());
		
			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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

		//	ps.setLong(1, bar.getId());

			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException sqle){
				sqle.printStackTrace();
			}
		}
	
	public void list(Bar bar){
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			String sql="select id,item,count,alert from barstock";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				
				int itemid=rs.getInt("id");
				
				String itemitem=rs.getString("Item");
				int itemcount=rs.getInt("count");
				int itemalert=rs.getInt("alert");
			
				System.out.println("ID:" + itemid +" ITEM:" + itemitem + " COUNT:" + itemcount + " ALERT:"+ itemalert);
			}
			connection.close();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
				
			}
		}
	}
		
	




