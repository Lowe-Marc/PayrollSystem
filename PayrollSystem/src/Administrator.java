import java.io.IOException;

public class Administrator extends Manager {

	public Administrator(int pos, int sal, int date, String first, String last, 
			char middle, String str, String cty,String st, int z, String phone, 
			int b, int dept, int proj1, int proj2, int proj3, int deduct) throws IOException {
		super(pos, sal, date, first, last, middle, str, cty, st, z, phone, 
				b, dept, proj1, proj2, proj3, deduct);
		// TODO Auto-generated constructor stub
	}

	public Administrator(int ID) throws IOException {
		super(ID);
		// TODO Auto-generated constructor stub
	}
	
	public void createDepartment(String name){
		
	}

	public void promoteEmployee(int ID, int pos, int salary, 
			int date) throws IOException{
		Employee e = new Employee(ID);
		e.setPosition(pos);
		if( e.getPosition() == 4 )
		{
			Department dept = new Department( e.department );
			dept.addManager(e.getID());
		}
		e.setSalary(salary, date);
	}
	
}
