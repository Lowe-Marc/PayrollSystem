import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Paystub {
	
	public static int date;
	private String dateString;

	private static int lineWidth 			= 80;
	private static int columnOnePos 		= 10;
	private static int columnTwoPos 		= 40;
	private static int columnThreePos		= 60;
	
	private double federalTaxRate			= 0.10;
	private double stateTaxRate				= 0.05;
	private double socSecTaxRate			= 0.05;
	private double deductionsRate			= 0.0667;
	private String federalTax;
	private String stateTax;
	private String socSecTax;
	private String totalYearFederal;
	private String totalYearState;
	private String totalYearSocSec;
	private String deductions;
	private String totalYearDeductions;
	private String pay;
	private String totalYearPay;
	
	private int HFrameDimension 			= 600;
	private int VFrameDimension 			= 400;
	
	private GraphicsDevice screen 			= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int screenWidth 				= screen.getDisplayMode().getWidth();
	private int screenHeight 				= screen.getDisplayMode().getHeight();
	
	private LinkedList<String> output 		= new LinkedList<String>();
	
	
	public Paystub() 
	{
		
	}
	
//	public void createPrintablePaystub()
//	{
//		try
//		{
//			FileWriter writer = new FileWriter( payStub = new File("paystub.txt"));
//			LinkedList<String> output = new LinkedList<String>();
//			
//			output.add( center("Company Name", '-') );
//			output.add( "\n" );
//			output.add( leftIndent("Name: " + EmployeeHomepage.loggedInEmployee.firstName + " " 
//					+ EmployeeHomepage.loggedInEmployee.getMiddleInitiale() + ". " 
//					+ EmployeeHomepage.loggedInEmployee.lastName, 10) );
//			output.add( leftIndent("ID: " + EmployeeHomepage.loggedInEmployee.employeeID, 10) );
//			output.add( leftIndent("Department: " + EmployeeHomepage.loggedInEmployee.getDepartment(), 10) );
//			output.add( leftIndent("Manager: " + EmployeeHomepage.loggedInEmployee.getManager(), 10) );
//			output.add("\n");
//			output.add( center("-",'-') );
//			output.add( threeColumns( "TaxType", "TaxAmount", "TotalYear") );
//			
//			for( String line : output )
//				writer.write(line);
//			
//			writer.flush();
//			writer.close();
//			
//			
//		}
//		catch( Exception e )
//		{
//			
//		}
//	}
	
	public void createPaystubView()
	{
		try
		{
			calculateTax( getSalary() );
			
			output.add( center("Company Name", '-') );
			output.add( "\n" );
			output.add( leftIndent("Name: " + EmployeeHomepage.loggedInEmployee.firstName + " " 
					+ EmployeeHomepage.loggedInEmployee.getMiddleInitiale() + ". " 
					+ EmployeeHomepage.loggedInEmployee.lastName, 10) );
			output.add( leftIndent("ID: " + EmployeeHomepage.loggedInEmployee.employeeID, 10) );
			output.add( leftIndent("Department: " + EmployeeHomepage.loggedInEmployee.getDepartmentString(), 10 ));
			
			Department dept = new Department(EmployeeHomepage.loggedInEmployee.department);
			int managerID = dept.managers.get(0);
			Employee manager = new Employee( managerID );
			String managerName = manager.firstName + " " + manager.middleInitial + ". " + manager.lastName; 
			String thisEmployee = EmployeeHomepage.loggedInEmployee.firstName + " " + EmployeeHomepage.loggedInEmployee.middleInitial
					+ ". " + EmployeeHomepage.loggedInEmployee.lastName;
			if( managerName.equals( thisEmployee ) )
				managerName = "Administration";
			
			output.add( leftIndent("Manager: " + managerName, 10 ));
			output.add( leftIndent("Position: " + EmployeeHomepage.loggedInEmployee.getPositionString(), 10 ));
			output.add( leftIndent("Salary: " + "$"+getSalary(), 10 ));
			output.add( leftIndent("Month: " + dateString.substring(0, 2) + "/" + dateString.substring(2), 10 ));
			output.add("\n");
			output.add( center("",'-') );
			output.add( threeColumns( "", "This Month:", "Total Year:") );
			output.add("\n");
			output.add( threeColumns( "State Income", "$"+stateTax, "$"+totalYearState) );
			output.add( threeColumns( "Federal Income", "$"+federalTax, "$"+totalYearFederal) );
			output.add( threeColumns( "Social Security", "$"+socSecTax, "$"+totalYearSocSec) );
			output.add( threeColumns( "Deductions", "$"+deductions, "$"+totalYearDeductions) );	
			output.add("\n");
			
			output.add( threeColumns("Pay:", "$"+pay, "$"+totalYearPay));
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	private static String center( String line, char padChar )
	{
		int padSize = (lineWidth - line.length())/2;
		
		for( int i = 0; i < padSize; i++ )
			line = padChar + line + padChar;
		
		return line + "\n";
	}
	
	private static String leftIndent( String line, int indentSize )
	{
		for ( int i = 0; i < indentSize; i++ )
			line = line + " ";
		
		return line + "\n";
	}
	
	private static String threeColumns( String first, String second, String third )
	{
		
		int firstSpaceSize = columnTwoPos - (columnOnePos + first.length());
		int secondSpaceSize = columnThreePos - (columnTwoPos + second.length());
		String line = "";
		
		for( int i = 0; i < columnOnePos; i++ )
			line = line + " ";
		line = line + first;
		
		for( int i = 0; i < firstSpaceSize; i++ )
			line = line + " ";
		line = line + second;
		
		for( int i = 0; i < secondSpaceSize; i++)
			line = line + " ";
		line = line + third + "\n";
		
		return line;
	}
	
	public void displayPaystubInFrame()
	{
		JFrame display = new JFrame("Paystub");
		JTextArea text = new JTextArea();
		text.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		createPaystubView();
		
		for( String line : output )
		{
			text.append( line);
		}
		text.setEditable(false);
		
		display.setBounds((screenWidth-HFrameDimension)/2, (screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		display.add( text, 0 );
		display.getContentPane().setBackground( Color.white );
		display.setVisible(true);
		display.repaint();
	}
	
	private void displayPaystub()
	{
		try {
	        // Get a file channel for the file
	        File file = new File("filename");
	        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();

	        // Use the file channel to create a lock on the file.
	        // This method blocks until it can retrieve the lock.
	        FileLock lock = channel.lock();

	        /*
	           use channel.lock OR channel.tryLock();
	        */

	        // Try acquiring the lock without blocking. This method returns
	        // null or throws an exception if the file is already locked.
	        try {
	            lock = channel.tryLock();
	        } catch (OverlappingFileLockException e) {
	            // File is already locked in this thread or virtual machine
	        }

	        // Release the lock - if it is not null!
	        if( lock != null ) {
	            lock.release();
	        }

	        // Close the file
	        channel.close();
	    } catch (Exception e) {
	    }
	}

	public int getSalary()
	{
		return EmployeeHomepage.loggedInEmployee.getSalary(date);
	}
	
	public int getDeductions(){
		return EmployeeHomepage.loggedInEmployee.getDeductions();
	}
	
	public void calculateTax( int salary )
	{
		int numDays = 30;
		dateString = ""+date;
		DecimalFormat decForm = new DecimalFormat("#.00");
		
		if( dateString.length() == 5 )
		{
			dateString = "0"+date;
		}
		
		Integer currentMonthYear = Integer.parseInt( new SimpleDateFormat("MMyyyy").format(Calendar.getInstance().getTime()) );
		Integer currentMonth = Integer.parseInt( new SimpleDateFormat("MM").format(Calendar.getInstance().getTime())) ;
		Integer currentDay = Integer.parseInt( new SimpleDateFormat("dd").format(Calendar.getInstance().getTime()) );
		
		Integer requestedMonth = Integer.parseInt(dateString.substring(0,2));
		Integer requestedYear = Integer.parseInt(dateString.substring(2));
		
		//Paystub for start month
		if( EmployeeHomepage.loggedInEmployee.startYear() == requestedYear 
				&& EmployeeHomepage.loggedInEmployee.startMonth() == requestedMonth)
		{
			//Employee started this month
			if( date == currentMonthYear )
				numDays = currentDay - EmployeeHomepage.loggedInEmployee.startDay();
			else
				numDays = 30 - EmployeeHomepage.loggedInEmployee.startDay();
		}
		//Paystub for this month
		else if( date == currentMonthYear)
			numDays = currentDay;
		else if( requestedYear <= EmployeeHomepage.loggedInEmployee.startYear() &&
				requestedMonth <= EmployeeHomepage.loggedInEmployee.startMonth())
		{
			numDays = 0;
			requestedMonth = 0;
		}
		
		//Set tax labels
		double federalTemp = numDays*federalTaxRate*(1-getDeductions()*.01)*(salary/365);
		federalTax = decForm.format(federalTemp);
		double stateTemp = numDays*stateTaxRate*(1-getDeductions()*.01)*(salary/365);
		stateTax = decForm.format(stateTemp);
		double socSecTemp = numDays*socSecTaxRate*(1-getDeductions()*.01)*(salary/365);
		socSecTax = decForm.format(socSecTemp);
		double deductionsTemp = numDays*deductionsRate*(1-getDeductions()*.01)*(salary/365);
		deductions = decForm.format(deductionsTemp);
		double payTemp = numDays*(salary/365)-socSecTemp - stateTemp - federalTemp + 
				deductionsTemp;
		pay = decForm.format(payTemp);
		
		double totalFederalTemp = (30*(requestedMonth-1)+numDays)*
				(federalTaxRate*(1-getDeductions()*.01))*(salary/365);
		totalYearFederal = decForm.format(totalFederalTemp);
		double totalStateTemp = (30*(requestedMonth-1)+numDays)*
				stateTaxRate*(1-getDeductions()*.01)*(salary/365);
		totalYearState = decForm.format(totalStateTemp);
		double totalSocSecTemp = (30*(requestedMonth-1)+numDays)*
				socSecTaxRate*(1-getDeductions()*.01)*(salary/365);
		totalYearSocSec = decForm.format(totalSocSecTemp);
		double totalDeductionsTemp = (30*(requestedMonth-1)+numDays)*
				deductionsRate*(1-getDeductions()*.01)*(salary/365);
		totalYearDeductions = decForm.format(totalDeductionsTemp);
		double totalPayTemp = (30*(requestedMonth-1)+numDays)*(salary/365)-totalSocSecTemp-totalStateTemp
				-totalFederalTemp + totalDeductionsTemp;
		totalYearPay = decForm.format(totalPayTemp);
	}
}
