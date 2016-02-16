import javax.swing.JOptionPane;

public class Student	{
 long id;
 Integer age;
 String studentname;
 
 
	public Student(long id,int age,String studentname){


		String studentinput = JOptionPane.showInputDialog("Enter student name");
		String idinput = JOptionPane.showInputDialog("Enter student id");
		String ageinput = JOptionPane.showInputDialog("Enter student age");

		id = Long.parseLong(idinput);
		age = Integer.parseInt(ageinput);
		studentname = studentinput;

		
	}
}
