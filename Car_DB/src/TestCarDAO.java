
public class TestCarDAO {


	public static void main(String[] args) {
		CarDAO carDAO = new CarDAO();
		//	Car car = new Car();
		//	car.setId(12345);
		//	car.setRegnum("00MO00");
		//	car.setMake("Ford");
		//	car.setEnginecc(1900);


		Car	car	=	carDAO.findbyid(12345);

		System.out.println(carDAO);
	}

}
