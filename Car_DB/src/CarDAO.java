import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDAO {
	final String username="root";
	final String password="";
	final String url="jdbc:mysql://localhost/";
	final String db = "softdev2";

	final String connectionUrl= url+""+db+"?"
			+ "user="+username+"&"
			+ "password="+password+"";

	public void insert(Car car){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "insert into car (id, regnum, make,enginecc) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, car.getId());
			ps.setString(2, car.getRegnum());
			ps.setString(3, car.getMake());
			ps.setInt(4,car.getEnginecc());

			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public void update(Car car){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "update car set regnum=?, make=?,enginecc=? where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, car.getRegnum());
			ps.setString(2, car.getMake());
			ps.setInt(3,car.getEnginecc());
			ps.setLong(4, car.getId());
			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public void delete(Car car){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "delete from car  where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setLong(1, car.getId());

			ps.executeUpdate();


			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}

	public Car findbyid(Long i){
		Car car = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(connectionUrl);		
			String sql= "select Id,Regnum,Make,Enginecc from car  where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setLong(1,Long  i());

			ResultSet rs = ps.executeQuery(sql);


			connection.close();

			if (rs.next()){
				car	=	new	Car();
				car.setId( rs.getLong("id"));
				car.setRegnum( rs.getString("regnum"));
				car.setMake( rs.getString("make"));
				car.setEnginecc(rs.getInt("enginecc"));

			}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException sqle){
				sqle.printStackTrace();
			}
			return car;
		}







	}














