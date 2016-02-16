import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MainClass {

	public static void main(String[] args) {
		//Student student new Student(0, 0, null);
		String	dbHost="localhost";
		String	dbDatabase="softdev2";
		String	dbUser =	"root";
		String	dbPassword =	"";
		try {
//			register	driver
		Class.forName("com.mysql.jdbc.Driver");
//			Make	Connection	Url
		String	connectionUrl ="jdbc:mysql://" +	dbHost
		+	"/" +	dbDatabase
		+	"?user=" +	dbUser
		+	"&password=" +	dbPassword;
		System.out.println(connectionUrl);
		
//			open	Connection
		java.sql.Connection	conn =	DriverManager.getConnection(connectionUrl);	
//		create	SQL
		//String	sql =	"update	student	set firstname='Bob'	where id=2;";
		String sql = "delete from student where id=1";
		//			prepare	Statement
		java.sql.PreparedStatement	ps =	conn.prepareStatement(sql);
//			execute	SQL
		ps.executeUpdate();
		
		//			Code	to	create	sql	and	run	it	will	go	here
//			close	connection
		conn.close();
		}catch (ClassNotFoundException	cnfe){
		throw new RuntimeException(cnfe);
		}	catch (SQLException	sqle)	{
		throw new RuntimeException(sqle);
		}

	}

}
