import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/*
 * Driver class to initialize the Payroll System
 */

public class Driver {
	static boolean isInitialized = true;
	static RandomAccessFile employeeFile;
	
	public Driver() throws IOException{
		File path = new File("EmployeeDB");
		if(path.exists()) path.delete();
		path = new File("passwordDB");
		if(path.exists()) path.delete();
		for(int i = 0; i < 6; i++){
			path = new File("Department" + i + "DB");
			if(path.exists()) path.delete();
			path = new File("Project" + i + "DB");
			if(path.exists()) path.delete();
		}
		isInitialized = false;
		
		//Create Projects
		new Project( 1, 1000, "TestProj1", new ArrayList<Integer>(), 
				new ArrayList<Integer>(), 1012017, 12312017);
		new Project( 2, 2000, "TestProj2", new ArrayList<Integer>(), 
				new ArrayList<Integer>(), 1012017, 12312017); 
		new Project( 3, 3000, "TestProj3", new ArrayList<Integer>(), 
				new ArrayList<Integer>(), 1012017, 12312017);
		
		//Add President to DB (1)
		new Employee(7, 150000, 10132016, "Donald", "Trump", 'J',
				"12345 White House", "Washington", "DC", 54661, "6081234567", 
				6141946, 5, 1, 2, 3, 2, new Project(1).getStart(), new Project(1).getEnd(), 30,
				new Project(2).getStart(), new Project(2).getEnd(), 30,
				new Project(3).getStart(), new Project(3).getEnd(), 30);
		
		//Add Managers to DB (2-5)
		new Employee(4, 70000, 10132016, "Mr", "Manager", 'T', 
				"00000 Some Street", "La Crosse", "WI", 54601, "6085551234", 
				5211952, 3, -1, -1, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		new Employee(4, 70000, 10132016, "Captain", "Morgan", 'M', 
				"1234 Vine Street", "La Crosse", "WI", 54601, "6085551235", 
				5151635, 1, -1,-1,-1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		new Employee(4, 70000, 10132016, "Jose", "Cuervo", 'D', 
				"8564 Pine Street", "La Crosse", "WI", 54601, "6085551236", 
				8211795, 2, -1,-1,-1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		new Employee(4, 70000, 10132016, "Jim", "Beam", 'B', 
				"786 LaCrosse Street", "La Crosse", "WI", 54601, "6085551237", 
				10131864, 4, -1, -1, -1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		
		//Create Departments
		new Department(1, 3);
		new Department(2, 4);
		new Department(3, 2);
		new Department(4, 5);
		new Department(5, 1);
		
		isInitialized = true;
		
		//add Vice President to DB (6)
		new Employee(6, 100000, 10132016, "Jack", "Daniels", 'N', 
				"9567 3rd Street", "La Crosse", "WI", 54601, "6085551238", 
				1131849, 5, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		
		//add HR Representative (7)
		new Employee(5, 70000, 10132016, "Johnnie", "Walker", 'W', 
				"634 King Street", "La Crosse", "WI", 54601, "6085551239", 
				8231805, 5, -1, -1, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		
		//add employees to DB (8-11)
		new Employee(1, 50000, 10132016, "John", "Employee", 'E',
				"54321 Rose Street", "La Crosse", "WI", 54601, "6085554321", 
				1011900, 4, -1, -1, -1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		new Employee(2, 40000, 10132016, "Bill", "Employee", 'E',
				"1483 Gillette Street", "La Crosse", "WI", 54601, "6085557493", 
				11221996, 3, -1, -1, -1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		new Employee(3, 70000, 10132016, "Bob", "Employee", 'E',
				"23 Cass Street", "La Crosse", "WI", 54601, "6085559732", 
				3051993, 2, -1, -1, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		new Employee(2, 45000, 10132016, "Sally", "Employee", 'E',
				"945 Hood Street", "La Crosse", "WI", 54601, "6085557492", 
				6301990, 1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}
	
	public static void main(String args[]) throws IOException{
		new Driver();
	}

	public static int getNumEmployees() throws IOException
	{
		employeeFile = new RandomAccessFile(new File(Employee.employeeDBName), "rw");
		return (((int)(employeeFile.length())/Employee.blockSize));
	}
	
}
