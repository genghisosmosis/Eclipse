
public class TestBookDAO {

	public static void main(String[] args) {
		BookDAO bookDAO = new BookDAO();
		
		Book book = new Book();
		book.setId(3);
		book.setAuthor("jk Rowling");
		book.setTitle("Harry the wimp");

		bookDAO.insert(book);
	}

}
