import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Employee {
	RandomAccessFile file;
	RandomAccessFile pwFile;
	int employeeID;
	int position;
	ArrayList<SalaryNode> salaryHistory;
	boolean isCurrent;
	String street;
	String city;
	String state;
	int zip;
	String firstName, lastName;
	char middleInitial;
	String phoneNumber;
	int currentSalary, birthDate, startDate;
	final static int blockSize = 414;
	int department;
	int project1, project2, project3;
	int deductions;
	int proj1Start, proj1End, proj1Percent, proj2Start, proj2End, proj2Percent, 
	proj3Start, proj3End, proj3Percent;
	
	static final String employeeDBName = "EmployeeDB";
	
	private class SalaryNode{
		int salary;
		int dateChanged;
		
		private SalaryNode(int sal, int date){
			salary = sal;
			dateChanged = date;
		}
		
	}
	
	
	public Employee(int pos, int sal, int date, String first, String last, 
			char middle, String str, String cty, String st, int z, 
			String phone, int birth, int dept, int proj1, int proj2, int proj3, int deduct,
			int p1Start, int p1End, int p1Percent, int p2Start, int p2End, int p2Percent,
			int p3Start, int p3End, int p3Percent) throws IOException{
		//create a new employee
		file = new RandomAccessFile(new File(employeeDBName), "rw");
		pwFile = new RandomAccessFile(new File("passwordDB"), "rw");
		employeeID = nextEmployeeNumber();
		isCurrent = true;
		position = pos;
		salaryHistory = new ArrayList<>();
		salaryHistory.add(new SalaryNode(sal, date));
		firstName = first;
		lastName = last;
		middleInitial = middle;
		street = str;
		city = cty;
		state = st;
		zip = z;
		phoneNumber = phone;
		currentSalary = sal;
		birthDate = birth;
		startDate = date;
		department = dept;
		project1 = proj1;
		project2 = proj2;
		project3 = proj3;
		deductions = deduct;
		proj1Start = p1Start;
		proj1End = p1End;
		proj1Percent = p1Percent;
		proj2Start = p2Start;
		proj2End = p2End;
		proj2Percent = p2Percent;
		proj3Start = p3Start;
		proj3End = p3End;
		proj3Percent = p3Percent;
		writeEmployee();
		setPassword("password");
		if( Driver.isInitialized){
			if(position == 4 || position == 7){
				Department d = new Department(department);
				d.addManager(employeeID);
			}
			else{
				Department d = new Department(department);
				d.addEmployee(employeeID);
				
			}
		}
		
	}
	
	

	public int setPassword(String pw) throws IOException {
		// TODO Auto-generated method stub
		if(pw.length() < 7 || pw.length() > 15)
			return -1;
		pwFile.seek(employeeID * 30 - 30);
		for(int i = 0; i < pw.length(); i++)
			pwFile.writeChar(~(pw.charAt(i)));
		if(pw.length() < 15)
			pwFile.writeChar((char)130);
		return 0;
	}

	public Employee(int ID) throws IOException{
		if(ID < 1) return;
		file = new RandomAccessFile(new File("EmployeeDB"), "rw");
		pwFile = new RandomAccessFile(new File("passwordDB"), "rw");
		file.seek(ID * blockSize - blockSize);
		employeeID = ID;
		position = file.readInt();
		if(file.readChar() == 't')
			isCurrent = true;
		else isCurrent = false;
		char c;
		firstName = "";
		while(firstName.length() < 16 && ((c = file.readChar()) != (char)130))
			firstName += c;
		file.seek(ID * blockSize - blockSize + 36 );
		lastName = "";
		while(lastName.length() < 20 && ((c = file.readChar()) != (char)130))
			lastName += c;
		file.seek(ID * blockSize - blockSize + 76);
		middleInitial = file.readChar();
		street = "";
		while(street.length() < 100 && ((c = file.readChar()) != (char)130))
			street += c;
		file.seek(ID * blockSize - blockSize + 178);
		city = "";
		while(city.length() < 30 && ((c = file.readChar()) != (char)130))
			city += c;
		file.seek(ID * blockSize - blockSize + 238);
		state = "";
		state += file.readChar();
		state += file.readChar();
		zip = file.readInt();
		phoneNumber = "";
		for(int i = 0; i < 10; i++)
			phoneNumber += file.readChar();
		int numNodes = file.readInt();
		currentSalary = file.readInt();
		file.seek(ID * blockSize - blockSize + 270);
		salaryHistory = new ArrayList<>();
		for (int n = 0; n < numNodes; n++){
			SalaryNode s = new SalaryNode(file.readInt(),file.readInt());
			salaryHistory.add(s);
		}
		file.seek(ID * blockSize - blockSize + 350);
		birthDate = file.readInt();
		startDate = file.readInt();
		department = file.readInt();
		project1 = file.readInt();
		project2 = file.readInt();
		project3 = file.readInt();
		deductions = file.readInt();
		proj1Start = file.readInt();
		proj1End = file.readInt();
		proj1Percent = file.readInt();
		proj2Start = file.readInt();
		proj2End = file.readInt();
		proj2Percent = file.readInt();
		proj3Start = file.readInt();
		proj3End = file.readInt();
		proj3Percent = file.readInt();
	}
	
	
	public void writeEmployee() throws IOException{
		int i;
		file.seek(employeeID * blockSize - blockSize);
		file.writeInt(position);
		if(isCurrent) file.writeChar('t');
		else file.writeChar('f');
		for(i = 0; i < firstName.length(); i++){
			file.writeChar(firstName.charAt(i));
		}
		for(;i < 15; i++)
			file.writeChar((char)130);
		for(i = 0; i < lastName.length(); i++){
			file.writeChar(lastName.charAt(i));
		}
		for(;i < 20; i++)
			file.writeChar((char)130);
		file.writeChar(middleInitial);
		for(i = 0; i < street.length(); i++){
			file.writeChar(street.charAt(i));
		}
		for(;i < 50; i++)
			file.writeChar((char)130);
		for(i = 0; i < city.length(); i++){
			file.writeChar(city.charAt(i));
		}
		for(;i < 30; i++)
			file.writeChar((char)130);
		file.writeChar(state.charAt(0));
		file.writeChar(state.charAt(1));
		file.writeInt(zip);
		for(i = 0; i < 10; i++)
			file.writeChar(phoneNumber.charAt(i));
		file.writeInt(salaryHistory.size());
		for(SalaryNode n: salaryHistory){
			file.writeInt(n.salary);
			file.writeInt(n.dateChanged);
		}
		file.seek(employeeID * blockSize - blockSize + 350);
		file.writeInt(birthDate);
		file.writeInt(startDate);
		file.writeInt(department);
		file.writeInt( project1 );
		file.writeInt( project2 );
		file.writeInt( project3 );
		file.writeInt(deductions);
		file.writeInt( proj1Start );
		file.writeInt( proj1End );
		file.writeInt( proj1Percent );
		file.writeInt( proj2Start );
		file.writeInt( proj2End );
		file.writeInt( proj2Percent );
		file.writeInt( proj3Start );
		file.writeInt( proj3End );
		file.writeInt( proj3Percent );
	}
	
	public int nextEmployeeNumber() throws IOException{
		return (((int)(file.length())/blockSize)+1);
	}
	
	public int getID(){
		return employeeID;
	}
	
	public boolean getStatus(){
		return isCurrent;
	}
	
	public void setStatus(boolean current) throws IOException{
		isCurrent = current;
		writeEmployee();
	}
	
	public int getPosition(){
		return position;
	}
	
	public String getPositionString() {
		// TODO Auto-generated method stub
		String pos;
		switch (position){
			case 1 : 
				pos = "Software Developer";
				break;
			case 2 : 
				pos =  "Senior Software Developer";
				break;
			case 3 : 
				pos = "Project Leader";
				break;
			case 4 : 
				pos =  "Department Manager";
				break;
			case 5 : 
				pos = "HR Representative";
				break;
			case 6 : 
				pos =  "Vice President";
				break;
			case 7 : 
				pos = "President";
				break;
			default: 
				return null;
		}
		return pos;
	}
	
	public void setPosition(int pos) throws IOException{
		position = pos;
		writeEmployee();
	}
	
	public int getDepartment()
	{
		return department;
	}
	
	public void setDepartment( int dept )
	{
		department = dept;
	}
	
	public String getDepartmentString()
	{
		String dept;
		switch (department){
			case 1 : 
				dept = "Research";
				break;
			case 2 : 
				dept =  "Design";
				break;
			case 3 : 
				dept = "Development";
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
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String first) throws IOException{
		firstName = first;
		writeEmployee();
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String last) throws IOException{
		lastName = last;
		writeEmployee();
	}
	
	public char getMiddleInitiale(){
		return middleInitial;
	}
	
	public void setMiddleInitial(char middle) throws IOException{
		middleInitial = middle;
		writeEmployee();
	}
	
	public String getStreet(){
		return street;
	}
	
	public void setStreet(String strt) throws IOException{
		street = strt;
		writeEmployee();
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCity(String cty) throws IOException{
		city = cty;
		writeEmployee();
	}
	
	public String getState(){
		return state;
	}
	
	public void setState(String st) throws IOException{
		state = st;
		writeEmployee();
	}
	
	public int getZip(){
		return zip;
	}
	
	public void setZip(int z) throws IOException{
		zip = z;
		writeEmployee();
	}
	
	public String getPhone(){
		return phoneNumber;
	}
	
	public void setPhone(String phone) throws IOException{
		phoneNumber = phone;
		writeEmployee();
	}
	
	public int getSalary(){
		return currentSalary;
	}
	
		public int getSalary( int date )
	{	
		String dateString = date+"";
		if( salaryHistory.size() == 1 )
			return currentSalary;
		
		if(dateString.length() == 5 )
			dateString = "0"+dateString;
		SalaryNode returnNode = salaryHistory.get(0);
//		Integer tempDay = Integer.parseInt(((Integer)returnNode.dateChanged).toString().substring(0,2));
		Integer requestedMonth = Integer.parseInt(dateString.substring(0,2));
		Integer requestedYear = Integer.parseInt(dateString.substring(2));
		for( SalaryNode node : salaryHistory )
		{
//			Integer nodeDay = Integer.parseInt(((Integer)node.dateChanged).toString().substring(0,2));
			String nodeDate = ((Integer)node.dateChanged).toString();
			if( nodeDate.length() == 7 )
				nodeDate = "0"+nodeDate;
			
			Integer nodeMonth = Integer.parseInt(nodeDate.substring(2,4));
			Integer nodeYear = Integer.parseInt(nodeDate.substring(4));
			
			if( nodeYear.compareTo( requestedYear ) < 0 )
			{
				returnNode = node;
				break;
			}
			else if( nodeYear.equals(requestedYear) && nodeMonth.compareTo( requestedMonth ) <= 0)
			{
				returnNode = node;
				break;
			}
//			else if( nodeYear == tempYear && nodeMonth == tempMonth && nodeDay < tempDay )
//			{
//				returnNode = node;
//				break;
//			}
		}
		
		return returnNode.salary;
	}
		
	public int[] getProjects()
	{
		int[] returnArr = new int[3];
		returnArr[0] = project1;
		returnArr[1] = project2;
		returnArr[2] = project3;
		return returnArr;
	}
		
	public String getProject1() throws IOException
	{
		if( project1 < 0 )
			return "None";
		return new Project( project1 ).getName();
	}
	
	public int getProj1Start()
	{
		return proj1Start;
	}
	
	public void setProj1Start( int newDate ) throws IOException
	{
		proj1Start = newDate;
		writeEmployee();
	}
	
	public int getProj1End()
	{
		return proj1End;
	}
	
	public void setProj1End( int newDate ) throws IOException
	{
		proj1End = newDate;
		writeEmployee();
	}
	
	public double getProj1Percent()
	{
		return proj1Percent/100.0;
	}
	
	public void setProj1Percent( int newPercent ) throws IOException
	{
		proj1Percent = newPercent;
		writeEmployee();
	}
	
	public void setProject1( int newProjNum ) throws IOException
	{
		project1 = newProjNum;
		writeEmployee();
	}
	
	public String getProject2() throws IOException
	{
		if( project2 < 0 )
			return "None";
		return new Project( project2 ).getName();
	}
	
	public void setProject2( int newProjNum ) throws IOException
	{
		project2 = newProjNum;
		writeEmployee();
	}
	
	public int getProj2Start()
	{
		return proj2Start;
	}
	
	public void setProj2Start( int newDate ) throws IOException
	{
		proj2Start = newDate;
		writeEmployee();
	}
	
	public int getProj2End()
	{
		return proj2End;
	}
	
	public void setProj2End( int newDate ) throws IOException
	{
		proj2End = newDate;
		writeEmployee();
	}
	
	public double getProj2Percent()
	{
		return proj2Percent/100.0;
	}
	
	public void setProj2Percent( int newPercent ) throws IOException
	{
		proj2Percent = newPercent;
		writeEmployee();
	}
	
	public String getProject3() throws IOException
	{
		if( project3 < 0 )
			return "None";
		return new Project( project3 ).getName();
	}
	
	public void setProject3( int newProjNum ) throws IOException
	{
		project3 = newProjNum;
		writeEmployee();
	}
	
	public int getProj3Start()
	{
		return proj3Start;
	}
	
	public void setProj3Start( int newDate ) throws IOException
	{
		proj3Start = newDate;
		writeEmployee();
	}
	
	public int getProj3End()
	{
		return proj3End;
	}
	
	public void setProj3End( int newDate ) throws IOException
	{
		proj3End = newDate;
		writeEmployee();
	}
	
	public double getProj3Percent()
	{
		return proj3Percent/100.0;
	}
	
	public void setProj3Percent( int newPercent ) throws IOException
	{
		proj3Percent = newPercent;
		writeEmployee();
	}
	
	public int getDeductions(){
		return deductions;
	}
	
	public void setDeductions(int d) throws IOException{
		deductions = d;
		writeEmployee();
	}
	
	public Integer startMonth()
	{
		return Integer.parseInt(((Integer)startDate).toString().substring(2,4));
	}
	
	public Integer startDay()
	{
		return Integer.parseInt(((Integer)startDate).toString().substring(0,2));
	}
	
	public Integer startYear()
	{
		return Integer.parseInt(((Integer)startDate).toString().substring(4));
	}
	
	public int setSalary(int newSalary, int changeDate) throws IOException{
		int sal = validateRaise(newSalary);
		if(sal != 0)
			newSalary = sal;
		SalaryNode s = new SalaryNode(newSalary, changeDate);
		salaryHistory.add(0,s);
		currentSalary = newSalary;
		if(salaryHistory.size() == 11)
			salaryHistory.remove(10);
		writeEmployee();
		return 0;
	}

	private int validateRaise(int newSalary) {
		// TODO Auto-generated method stub
		if(newSalary < currentSalary)
			return currentSalary;
		switch(position){
		case 1:{
			if(newSalary > 60000)
				return 60000;
			if(newSalary < 40000)
				return 40000;
		}break;
		case 2:{
			if(newSalary > 80000)
				return 80000;
			if(newSalary < 60000)
				return 60000;
		}break;
		case 3:{
			if(newSalary > 85000)
				return 85000;
			if(newSalary < 70000)
				return 70000;
		}break;
		case 4:{
			if(newSalary > 95000)
				return 95000;
			if(newSalary < 70000)
				return 70000;
		}break;
		case 5:{
			if(newSalary > 95000)
				return 95000;
			if(newSalary < 70000)
				return 70000;
		}break;
		case 6:{
			if(newSalary > 150000)
				return 150000;
			if(newSalary < 100000)
				return 100000;
		}break;
		case 7:{
			if(newSalary < 150000)
				return 150000;
		}break;
		}
		return 0;
	}
	
	public boolean validatePassword(String pw) throws IOException{
		String password;
		
		pwFile.seek(employeeID * 30 - 30);
		password = "";
		char c = pwFile.readChar();
		while(c != (char)130){
			password += (char)~c;
			c = pwFile.readChar();
		}
		return (password.compareTo(pw) == 0);
	}

}
