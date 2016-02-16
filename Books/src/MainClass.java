import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainClass {

	public static void main(String[] args) {
		try {
			// register the driver
			Class.forName("com.mysql.jdbc.Driver");
			// get connection
			String username="root";
			String password="";
			String url="jdbc:mysql://localhost/";
			String db = "softdev2";
			
			String connectionUrl= url+""+db+"?"
					+ "user="+username+"&"
					+ "password="+password+"";
			System.out.println(connectionUrl);
			
			Connection connection = DriverManager.getConnection(connectionUrl);
					
			String sql= "insert into book values (1,'2','3')";
			PreparedStatement ps = connection.prepareStatement(sql);
			 ps.executeUpdate();
			
			System.out.println("good");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}

	}

}
