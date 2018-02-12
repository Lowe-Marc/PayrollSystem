import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList; 
 
 
public class Department { 
	String departmentName; 
	int departmentNumber; 
	ArrayList<Integer> members; 
	ArrayList<Integer> managers; 
	RandomAccessFile deptFile;
 
	public Department(int deptNum) throws IOException{
		departmentNumber = deptNum;
		departmentName = getDepartmentString();
		members = new ArrayList<Integer>(); 
		managers = new ArrayList<Integer>(); 
		deptFile = new RandomAccessFile(new File("Department" + deptNum + "DB"), "rw"); 
		deptFile.seek(0); 
		int num = deptFile.readInt(); 
		while(num != -1){
			managers.add(num);
			num = deptFile.readInt();
		}
		num = deptFile.readInt(); 
		while(num != -1){
			members.add(num);
			num = deptFile.readInt();
		}
	}
	
	public Department(int deptNum, int mngr) throws IOException{
		departmentName = getDepartmentString();
		members = new ArrayList<Integer>(); 
		managers = new ArrayList<Integer>(); 
		deptFile = new RandomAccessFile(new File("Department" + deptNum + "DB"), "rw"); 
		deptFile.seek(0);
		managers.add(mngr);
		writeDepartment();
	}
	
	public void addEmployee(int e) throws IOException{ 
		members.add(e); 
		writeDepartment();
	} 
	
	public void addManager(int m) throws IOException{ 
		managers.add(m);
		writeDepartment();
	}
	
	public void removeEmployee(int e) throws IOException{
		members.remove((Integer)(e));
		writeDepartment();
	}
	
	public void removeManager(int m) throws IOException{ 
		managers.remove((Integer)(m)); 
		writeDepartment();
	}
	
	public void writeDepartment() throws IOException{
		deptFile.seek(0);
		deptFile.writeInt( departmentNumber );
		for(int m: managers)
			deptFile.writeInt(m);
		deptFile.writeInt(-1);
		for(int m: members)
			deptFile.writeInt(m);
		deptFile.writeInt(-1);
	}
	
	public String getDepartmentString(){
		String dept;
		switch (departmentNumber){
			case -1:
				dept = "None";
				break;
			case 1 : 
				dept = "Mobile Devevlopment";
				break;
			case 2 : 
				dept =  "Data App Development";
				break;
			case 3 : 
				dept = "Web Development";
				break;
			case 4 : 
				dept =  "Testing";
				break;
			case 5 :
				dept = "Administration";
				break;
			default: 
				return null;
		}
		return dept;
	}

	public ArrayList<Integer> getEmployees() {
		return members;
	}
	
	public ArrayList<Integer> getManagers() {
		return managers;
	}
} 
 