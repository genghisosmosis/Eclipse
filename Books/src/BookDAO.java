import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	final String username="root";
	final String password="";
	final String url="jdbc:mysql://localhost/";
	final String db = "softdev2";
	
	final String connectionUrl= url+""+db+"?"
			+ "user="+username+"&"
			+ "password="+password+"";
	
	public void insert(Book book){
		try {
			// register the driver
			Class.forName("com.mysql.jdbc.Driver");
			// get connection
			
			//System.out.println(connectionUrl);
			
			Connection connection = DriverManager.getConnection(connectionUrl);
					
			String sql= "insert into book (id, title, author) values (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, book.getId());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			 ps.executeUpdate();
			
			
			 connection.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public void update(Book book){
		
	}
	public void delete(long id){
		try {
			// register the driver
			Class.forName("com.mysql.jdbc.Driver");
			// get connection
			Connection connection = DriverManager.getConnection(connectionUrl);
					
			String sql= "delete from book where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			 ps.executeUpdate();
			
			
			 connection.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public void delete(Book book){
		this.delete(book.getId());
	}
	public Book findById(long id){
		Book book = null;
		return book;
	}
	public List<Book> getAll(){
		List<Book> books = new ArrayList<Book>();
		return books;
	}
}
