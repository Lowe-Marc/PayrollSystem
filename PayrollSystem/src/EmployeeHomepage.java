import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EmployeeHomepage extends JFrame implements ActionListener 
{
	public static Employee loggedInEmployee;
	
	protected JButton editButton, logoutButton, paystubButton;
	private JButton saveButton, changePassButton;
	
	protected JTextField firstField, middleField, lastField, streetField, cityField, 
		zipField, idField, positionField, departmentField, phoneField, project1Field,
		project2Field, project3Field;
	private JLabel firstLabel, middleLabel, lastLabel, streetLabel, cityLabel, 
		zipLabel, idLabel, positionLabel, departmentLabel, phoneLabel, project1Label,
		project2Label, project3Label;
	
	protected int numRows 				= 0;
	protected int HFrameDimension 		= 400;
	protected int VFrameDimension 		= 575;
	protected int VLogoutOffset 		= 10;
	private int HLogoutDimension 		= 30;
	private int VLogoutDimension 		= 10;
	private int HEditDimension 			= 30;
	private int VEditDimension 			= 10;
	private int HSaveDimension 			= 30;
	private int VSaveDimension 			= 10;
	private int HPaystubDimension 		= 30;
	private int VPaystubDimension 		= 10;
	
	private boolean editable 			= false;
	
	private GraphicsDevice screen 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	protected int screenWidth 			= screen.getDisplayMode().getWidth();
	protected int screenHeight 			= screen.getDisplayMode().getHeight();
	
	protected GridBagConstraints constraints = new GridBagConstraints();
	
	public EmployeeHomepage( Employee loginParam ) throws IOException
	{
		super("Employee - Homepage");
		loggedInEmployee = loginParam;
	}
	
	//For subclasses
	protected EmployeeHomepage( String title )
	{
		super( title );
	}
	
	public void setup() throws IOException
	{
		this.setLayout(new GridBagLayout());
		
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(2, 10, 2, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.weighty = 0;
		
		//First name label
		firstLabel = new JLabel("First name: " );
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add( firstLabel, constraints);
		
		//First name field
		firstField = new JTextField();
		firstField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( firstField, constraints );
		constraints.weightx = 0;
		
		//Middle name label
		middleLabel = new JLabel("Middle name: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( middleLabel, constraints);
		
		//Middle name field
		middleField = new JTextField();
		middleField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( middleField, constraints );
		constraints.weightx = 0;
		
		//Last name label
		lastLabel = new JLabel("Last name: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( lastLabel, constraints);
		
		//Last name field
		lastField = new JTextField();
		lastField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( lastField, constraints );
		constraints.weightx = 0;
		
		//street label
		streetLabel = new JLabel("Street: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( streetLabel, constraints);
		
		//street field
		streetField = new JTextField();
		streetField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( streetField, constraints );
		constraints.weightx = 0;
		
		//city label
		cityLabel = new JLabel("City, State: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( cityLabel, constraints);
		
		//city field
		cityField = new JTextField();
		cityField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( cityField, constraints );
		constraints.weightx = 0;
		
		//zip label
		zipLabel = new JLabel("Zip: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( zipLabel, constraints);
		
		//zip field
		zipField = new JTextField();
		zipField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( zipField, constraints );
		constraints.weightx = 0;
		
		//phone label
		phoneLabel = new JLabel("Phone: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( phoneLabel, constraints);
		
		//phone field
		phoneField = new JTextField();
		phoneField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( phoneField, constraints );
		constraints.weightx = 0;
		
		//Edit button
		editButton = new JButton("Edit");
		editButton.setBounds( (HFrameDimension-HEditDimension)/2, VFrameDimension - VEditDimension + VLogoutOffset,
				HEditDimension, VEditDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( editButton, constraints );
		editButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		//Save button
		saveButton = new JButton("Save changes");
		saveButton.setBounds( (HFrameDimension-HSaveDimension)/2, VFrameDimension - VSaveDimension + VLogoutOffset,
				HSaveDimension, VSaveDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( saveButton, constraints );
		saveButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		//Save button
		changePassButton = new JButton("Change Password");
		changePassButton.setBounds( (HFrameDimension-HSaveDimension)/2, VFrameDimension - VSaveDimension + VLogoutOffset,
				HSaveDimension, VSaveDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( changePassButton, constraints );
		changePassButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		//Employee ID label
		idLabel = new JLabel("Employee ID: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( idLabel, constraints);
		
		//Employee ID field
		idField = new JTextField();
		idField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( idField, constraints );
		constraints.weightx = 0;
		
		//position label
		positionLabel = new JLabel("Position: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( positionLabel, constraints);
		
		//position field
		positionField = new JTextField();
		positionField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( positionField, constraints );
		constraints.weightx = 0;
		
		//Department label 
		departmentLabel = new JLabel("Department: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( departmentLabel, constraints);
		
		//Department field
		departmentField = new JTextField();
		departmentField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( departmentField, constraints );
		constraints.weightx = 0;
		
		//Project1 label 
		project1Label = new JLabel("Project 1: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project1Label, constraints);
		
		//Project1 field
		project1Field = new JTextField();
		project1Field.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project1Field, constraints );
		constraints.weightx = 0;
		
		//Project2 label 
		project2Label = new JLabel("Project 2: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project2Label, constraints);
		
		//Project2 field
		project2Field = new JTextField();
		project2Field.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project2Field, constraints );
		constraints.weightx = 0;
		
		//Project3 label 
		project3Label = new JLabel("Project 3: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project3Label, constraints);
		
		//Project3 field
		project3Field = new JTextField();
		project3Field.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project3Field, constraints );
		constraints.weightx = 0;
		
		//Paystub button
		paystubButton = new JButton("View Paystub");
		paystubButton.setBounds( (HFrameDimension-HPaystubDimension)/2, VFrameDimension - VPaystubDimension + VLogoutOffset,
				HPaystubDimension, VPaystubDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( paystubButton, constraints );
		paystubButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		//Logout button
		logoutButton = new JButton("Logout");
		logoutButton.setBounds( (HFrameDimension-HLogoutDimension)/2, VFrameDimension - VLogoutDimension + VLogoutOffset,
				HLogoutDimension, VLogoutDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( logoutButton, constraints );
		logoutButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		//For subclasses
		numRows = constraints.gridy;
		
		//Handle setup
		getPersonalInfo();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible( true );
		this.setBounds((screenWidth-HFrameDimension)/2, (screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		this.getContentPane().setBackground( Color.white );
		this.repaint();
		if(loggedInEmployee.validatePassword("password")){
			changePass("New Password Required");
		}
	}
	
	private void logout()
	{
		//Reverse whatever is done during the login process
		dispose();
		UserLogin loginScreen = new UserLogin();
		loginScreen.setup("");
	}
	
	private void getPersonalInfo() throws IOException
	{
		firstField.setText( loggedInEmployee.getFirstName() );
		middleField.setText( loggedInEmployee.getMiddleInitiale() + "" );
		lastField.setText( loggedInEmployee.getLastName() );
		streetField.setText( loggedInEmployee.getStreet() );
		cityField.setText( loggedInEmployee.getCity() );
		zipField.setText( loggedInEmployee.getZip() + "" );
		phoneField.setText( loggedInEmployee.getPhone() );
		idField.setText( loggedInEmployee.getID() + "");
		positionField.setText( loggedInEmployee.getPositionString() );
		departmentField.setText( loggedInEmployee.getDepartmentString());
		project1Field.setText( loggedInEmployee.getProject1() );
		project2Field.setText( loggedInEmployee.getProject2() );
		project3Field.setText( loggedInEmployee.getProject3() );
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
	
	private void viewPaystub()
	{
		new PaystubSelectorPage().setup();
	}
	
	private void changePass() throws IOException
	{
		new ChangePasswordPage().setup("");
	}
	
	private void changePass(String label) throws IOException
	{
		new ChangePasswordPage().setup(label);
	}
	
	public void actionPerformed( ActionEvent e )
	{
		try
		{
			if( e.getSource() == editButton )
			{
				if( editable )
				{
					editable = false;
					firstField.setEditable( false );
					middleField.setEditable( false );
					lastField.setEditable( false );
					cityField.setEditable( false );
					streetField.setEditable( false );
					zipField.setEditable( false ); 
					phoneField.setEditable( false );
				}
				else
				{
					editable = true;
					firstField.setEditable( true );
					middleField.setEditable( true );
					lastField.setEditable( true );
					cityField.setEditable( true );
					streetField.setEditable( true );
					zipField.setEditable( true ); 
					phoneField.setEditable( true );
				}
			}
			else if( e.getSource() == saveButton )
				submitPersonalInfo();
			else if( e.getSource() == paystubButton )
				viewPaystub();
			else if( e.getSource() == logoutButton )
				logout();
			else if( e.getSource() == changePassButton )
				changePass();
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}
	}
}
