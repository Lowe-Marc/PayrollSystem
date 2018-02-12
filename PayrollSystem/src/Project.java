import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Project 
{
	private final int nameLimit = 15, maxNumMembers = 20, maxNumDepts = 5;
	int projectNum, totalCost, otherCost, numDays, numMembers, numDepts, startDate, endDate;
	private String name;
	ArrayList<Integer> members;
	private ArrayList<Integer> departments;
	private RandomAccessFile projectFile;
	final static int blockSize = 146;
	
	public Project( int key, int other, String projName, ArrayList<Integer> membs, 
			ArrayList<Integer> depts, int start, int end ) throws IOException 
	{
		projectNum = key;
		name = projName;
		otherCost = other;
		members = membs;
		numMembers = members.size();
		departments = depts;
		numDepts = departments.size();
		startDate = start;
		endDate = end;
		numDays = calculateNumDays( start, end );
		System.out.println(PayrollDriver.numProjects++);
		writeProject( projectNum );
	}
	
	public Project( int key ) throws IOException
	{
		projectNum = key;
		
		projectFile = new RandomAccessFile( new File( "Project" + key + "DB" ), "rw");
		members = new ArrayList<Integer>();
		departments = new ArrayList<Integer>();
		projectFile.seek(0);
		name = "";
		for( int i = 0; i < nameLimit; i++ )
			name = name + projectFile.readChar();
		totalCost = projectFile.readInt();
		otherCost = projectFile.readInt();
		numMembers = projectFile.readInt();
		numDepts = projectFile.readInt();
		int j;
		for( int i = 0; i < maxNumMembers; i++ )
			if((j = projectFile.readInt()) > 0)
			members.add( j );
		for( int i = 0; i < maxNumDepts; i++ )
			departments.add(0, projectFile.readInt() );
		startDate = projectFile.readInt();
		endDate = projectFile.readInt();
	}
	
	private void writeProject( int key ) throws IOException
	{
		projectFile = new RandomAccessFile( new File( "Project" + key + "DB" ), "rw");
		projectFile.seek(0);
		for( int i = 0; i < nameLimit; i++ )
		{
			if( name.length() > i )
				projectFile.writeChar(name.charAt(i));
			else
				projectFile.writeChar(' ');
		}
		projectFile.writeInt(totalCost);
		projectFile.writeInt(otherCost);
		projectFile.writeInt(numMembers);
		projectFile.writeInt(numDepts);
		for( int i = 0; i < maxNumMembers; i++  )
		{
			if( members.size() > i ){
				projectFile.writeInt( members.get(i) );
				System.out.println("Wrote " + members.get(i));
			}
			else
				projectFile.writeInt(-1);
		}
		for( int i = 0; i < maxNumDepts; i++  )
		{
			if( departments.size() > i )
				projectFile.writeInt( departments.get(i) );
			else
				projectFile.writeInt(-1);
		}
		projectFile.writeInt( startDate );
		projectFile.writeInt( endDate );
	}
	
	private int calculateNumDays( int start, int end )
	{
		if( start == end )
			return 0;
		int returnVal = 0;
		String startString = "" + start;
		String endString = "" + end;
		
		if( startString.length() == 7 )
			startString = "0"+startString;
		if( endString.length() == 7 )
			endString = "0"+endString;
		/*
		Integer startDay = Integer.parseInt(startString.substring(0,2));
		Integer startMonth = Integer.parseInt(startString.substring(2,4));
		Integer startYear = Integer.parseInt(startString.substring(4,8));
		
		Integer endDay = Integer.parseInt(startString.substring(0,2));
		Integer endMonth = Integer.parseInt(startString.substring(2,4));
		Integer endYear = Integer.parseInt(startString.substring(4,8));
		*/
		SimpleDateFormat myFormat = new SimpleDateFormat("ddMMyyyy");
		
		String inputString1 = startString;
		String inputString2 = endString;

		try {
		    Date date1 =  myFormat.parse(inputString1);
		    Date date2 =  myFormat.parse(inputString2);
		    long diff = date2.getTime() - date1.getTime();
		    returnVal = (int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		/*
		if( endYear > startYear)
		{
			returnVal = returnVal + (endYear-startYear)*365;
			if( endMonth > startMonth)
			{
				returnVal = returnVal + (endMonth-startMonth)*30;
				if( endDay > endDay )
				{
					returnVal = returnVal + (endDay-startDay);
				}
			}
		}
		else if( endMonth > startMonth )
		{
			returnVal = returnVal + (endMonth-startMonth)*30;
			if( endDay > startDay )
			{
				returnVal = returnVal + (endDay-startDay);
			}
		}
		else if( endDay > startDay )
		{
			returnVal = returnVal + (endDay-startDay);
		}*/
		
		return returnVal;
	}
	
	private int calculateCost( int other ) throws IOException
	{
		ArrayList<Employee> employees = getMembers();
		double payRatio = numDays/365.0;
		
		if( !employees.isEmpty() )
		{
			for( Employee e : employees )
			{
				int temp = 1;
				for( int i : e.getProjects() )
				{
					if( i == projectNum && i == 1 )
						payRatio = calculateNumDays( e.proj1Start, e.proj1End )/365.0*e.getProj1Percent();
					else if( i == projectNum && i == 2 )
						payRatio = calculateNumDays( e.proj2Start, e.proj2End )/365.0*e.getProj2Percent();
					else if( i == projectNum && i == 3 )
						payRatio = calculateNumDays( e.proj3Start, e.proj3End )/365.0*e.getProj3Percent();
					temp++;
				}	
				//This may need to get a non-current salary if it has changed during the project duration
				other += e.currentSalary*payRatio;
			}
		}
		return other;
	}
	
	public int getTotalCost() throws IOException
	{
		totalCost = calculateCost( otherCost );
		return totalCost;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getTime()
	{
		return calculateNumDays(startDate, endDate);
	}
	
	public int getNum()
	{
		return projectNum;
	}
	
	public int getStart()
	{
		return startDate;
	}
	
	public int getEnd()
	{
		return endDate;
	}
	
	public ArrayList<Employee> getMembers() throws IOException
	{/*
		System.out.println("In Project.getMembers() Detecting number employees: " 
	                       + Driver.getNumEmployees());
		for( int i = 1; i < Driver.getNumEmployees(); i++ )
		{
			Employee e = new Employee( i );
			if( e.project1 == projectNum || e.project2 == projectNum || e.project3 == projectNum)
			{
				System.out.println("In Project.getMember(), the following employee is being added as a member: " + i);
				members.add( e.employeeID, 0 );
			}
		}*/
		ArrayList<Employee> employees = new ArrayList<Employee>();
		for( Integer i : members )
		{
			System.out.println("In Project" + projectNum + ".getMembers(), the following employee is a member: " + i);
			//if( i < 0 )
				//return employees;
			employees.add( new Employee(i) );
		}
		
		return employees;
	}
	
	public ArrayList<Department> getDepartments() throws IOException
	{
		ArrayList<Department> depts = new ArrayList<Department>();
		
		for( Employee e : getMembers() )
		{
			depts.add(new Department(e.department));
		}
		
		return depts;
	}
	
	public void removeMember( Employee memb ) throws IOException
	{
		members.remove( (Integer)memb.getID() );
		writeProject( projectNum );
	}
	
	public void addMember( int employeeID ) throws IOException
	{
		System.out.println("In Project.addMember(): " + employeeID);
		members.add( employeeID );
		numMembers++;
		writeProject( projectNum );
	}
	
	public void addDepartment( int deptNum ) throws IOException
	{
		departments.add( deptNum );
		writeProject( projectNum );
	}
	
	public void removeDepartment( int deptNum ) throws IOException
	{
		departments.remove( deptNum );
		writeProject( projectNum );
	}
	
	public static int nextProjectNumber() throws IOException{
		//Write an int in the beginning of the a project file that stores the max number of projects
		return PayrollDriver.numProjects + 1;
	}
	
	public int getOtherCost(){
		return otherCost;
	}
	
}
