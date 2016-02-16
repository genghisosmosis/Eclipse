import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BMainClassGetAll {

	public static void main(String[] args) {
		try{
			// register Driver
			Class.forName("com.mysql.jdbc.Driver");
			// url
			String username="root";
			String password="";
			String url="jdbc:mysql://localhost/";
			String db = "softdev2";
			
			String connectionUrl= url+""+db+"?"
					+ "user="+username+"&"
					+ "password="+password+"";
			System.out.println(connectionUrl);
			// make connection
			Connection connection = DriverManager.getConnection(connectionUrl);
			
			//make SQL
			String sql = "select * from book;";
			System.out.println(sql);
			
			// prepare Statement
			PreparedStatement ps = connection.prepareStatement(sql);
			
			// run prepared Statement
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				
				System.out.println("book >"+id+": "+ title+" by "+author);
			}
			

			connection.close();


		}catch(ClassNotFoundException cnfe){
			throw new RuntimeException(cnfe);
		}
		catch(SQLException sqle){
			throw new RuntimeException(sqle);
		}
	}

}
