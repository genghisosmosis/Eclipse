import java.sql.DriverManager;

public class BMainClassGetAll {
	
public static void main(String args[]){
	try{
		Class.forName("com.mysql.jbdc.Driver");
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
	}catch(ClassNotFoundException cnfe){
		throw new RuntimeException(cnfe);
	}
}
}
