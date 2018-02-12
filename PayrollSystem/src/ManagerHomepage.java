import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ManagerHomepage extends EmployeeHomepage {

	public static Manager loggedInManager;
	
	protected JButton newEmployeeButton;
	protected JButton fireEmployeeButton;
	protected JButton assignEmployeeButton;
	
	private int HEmployeeDimension 		= 30;
	private int VEmployeeDimension 		= 10;
	private int extraLength				= 175;
	protected int VFrameDimension = super.VFrameDimension + extraLength;
	
	public ManagerHomepage( Employee loginParam ) throws IOException 
	{
		//loggedInManager = get admin based off ID from validate DB
		super(loginParam);
		setTitle("Manager - Homepage");
		loggedInManager = (Manager)loginParam;
	}
	
	public ManagerHomepage( String title )
	{
		super( title );
	}
	
	public void setup() throws IOException
	{
		super.setup();
		
		GridBagConstraints constraints = super.constraints;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(2, 10, 2, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.weighty = 0;
		
		//newEmployee button
		newEmployeeButton = new JButton("Hire a new employee");
		newEmployeeButton.setBounds( (HFrameDimension-HEmployeeDimension)/2, VFrameDimension - VEmployeeDimension + VLogoutOffset,
				HEmployeeDimension, VEmployeeDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy = super.numRows+2;
		this.add( newEmployeeButton, constraints );
		newEmployeeButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		//fireEmployee button
		fireEmployeeButton = new JButton("Fire an employee");
		fireEmployeeButton.setBounds( (HFrameDimension-HEmployeeDimension)/2, VFrameDimension - VEmployeeDimension + VLogoutOffset,
				HEmployeeDimension, VEmployeeDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( fireEmployeeButton, constraints );
		fireEmployeeButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		//assignEmployee button
		assignEmployeeButton = new JButton("Assign an employee");
		assignEmployeeButton.setBounds( (HFrameDimension-HEmployeeDimension)/2, VFrameDimension - VEmployeeDimension + VLogoutOffset,
				HEmployeeDimension, VEmployeeDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( assignEmployeeButton, constraints );
		assignEmployeeButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		this.setBounds((super.screenWidth-HFrameDimension)/2, (super.screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		this.repaint();
	}
	
	private void newEmployee()
	{
		NewHirePage page = new NewHirePage();
		page.setup();
		
		//May have to tweak this to create a new employee.
		//However, we may be able to get away with looking to see if the employee currently exists
		//in the DB, so the save changes would work for hiring as well as simply changing.
	}
	
	private void fireEmployee()
	{
		//Open JFrame asking for ID number, the frame should verify before submitting
		//It should also pull up a warning window if trying to fire someone
		//They're not allowed to fire.
		
		new FireEmployeePage( loggedInManager );
	}
	
	private void assignEmployee()
	{
		AssignEmployeePage page = new AssignEmployeePage( );
		page.setup();
	}
	
	private void getPersonalInfo()
	{
		firstField.setText( loggedInManager.getFirstName() );
		middleField.setText( loggedInManager.getMiddleInitiale() + "" );
		lastField.setText( loggedInManager.getLastName() );
		streetField.setText( loggedInManager.getStreet() );
		cityField.setText( loggedInManager.getCity() );
		zipField.setText( loggedInManager.getZip() + "" );
		phoneField.setText( loggedInManager.getPhone() );
		idField.setText( loggedInManager.getID() + "");
		positionField.setText( loggedInManager.getPosition() + "");
	}
	
	private void submitPersonalInfo() throws IOException
	{
		//Parse text fields and submit info
		loggedInEmployee.setFirstName( firstField.getText() );
		loggedInEmployee.setMiddleInitial( middleField.getText().charAt(0) );
		loggedInEmployee.setLastName( lastField.getText() );
		loggedInEmployee.setStreet( streetField.getText() );
		loggedInEmployee.setCity( cityField.getText() );
		loggedInEmployee.setZip( Integer.parseInt( zipField.getText() ) );
		loggedInEmployee.setPhone( phoneField.getText() );
	}
	
	public void actionPerformed( ActionEvent e )
	{
		super.actionPerformed(e);
		
		if( e.getSource() == newEmployeeButton )
			newEmployee();
		else if ( e.getSource() == fireEmployeeButton )
			fireEmployee();
		else if ( e.getSource() == assignEmployeeButton )
			assignEmployee();
	}
}
