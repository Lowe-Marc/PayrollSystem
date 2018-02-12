import java.io.IOException;

public class Manager extends Employee {

	public Manager(int pos, int sal, int date, String first, String last, char middle, 
			String str, String cty, String st, int z, String phone, 
			int b, int dept, int proj1, int proj2, int proj3, int deduct) throws IOException {
		super(pos, sal, date, first, last, middle, str, cty, st, z, phone, b, 
				dept, proj1, proj2, proj3, deduct,0 ,0 ,0, 0, 0, 0, 0, 0, 0);
		// TODO Auto-generated constructor stub
	}

	public Manager(int ID) throws IOException {
		super(ID);
		// TODO Auto-generated constructor stub
	}
	
	public int fireEmployee(int ID, int date) throws IOException{
		Employee e = new Employee(ID);
		if(position <= e.getPosition()){
			return -1;
		}
		Department d;
		System.out.println("Employee" + e.getPosition());
		for(int i = 1; i < 6; i++){
			d = new Department(i);
			d.removeEmployee(ID);
			d.removeManager(ID);
		}
		e.setSalary(e.getSalary(), date);
		e.setStatus(false);
		e.proj1End = date;
		e.proj2End = date;
		e.proj3End = date;
		return 0;
	}

}
