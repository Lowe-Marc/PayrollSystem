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
public class NewHirePage extends JFrame implements ActionListener 
{
	protected JButton editButton;
	private JButton saveButton;
	protected JButton logoutButton, paystubButton;
	private JTextField firstField, middleField, lastField, streetField, cityField, stateField, 
		zipField, bdayField, ssnField, positionField, salaryField, departmentField, phoneField, startDateField, idField,
		project1Field, project2Field, project3Field, deductField;
	private JLabel firstLabel, middleLabel, lastLabel, streetLabel, cityLabel, stateLabel,
		zipLabel, bdayLabel, ssnLabel, positionLabel, salaryLabel, departmentLabel, phoneLabel, startDateLabel, idLabel,
		project1Label, project2Label, project3Label, deductLabel;
	
	protected int numRows 				= 0;
	protected int HFrameDimension 		= 400;
	protected int VFrameDimension 		= 650;
	protected int VLogoutOffset 		= 10;
	private int HSaveDimension 			= 30;
	private int VSaveDimension 			= 10;
	
	private GraphicsDevice screen 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	protected int screenWidth 			= screen.getDisplayMode().getWidth();
	protected int screenHeight 			= screen.getDisplayMode().getHeight();
	
	protected GridBagConstraints constraints = new GridBagConstraints();
	
	public NewHirePage()
	{
		super("New Hire Information");
	}
	
	public void setup()
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
		firstField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( firstField, constraints );
		constraints.weightx = 0;
		
		//Middle name label
		middleLabel = new JLabel("Middle initial: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( middleLabel, constraints);
		
		//Middle name field
		middleField = new JTextField();
		middleField.setEditable( true );
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
		lastField.setEditable( true );
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
		streetField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( streetField, constraints );
		constraints.weightx = 0;
		
		//city label
		cityLabel = new JLabel("City: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( cityLabel, constraints);
		
		//city field
		cityField = new JTextField();
		cityField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( cityField, constraints );
		constraints.weightx = 0;
		
		//state label
		stateLabel = new JLabel("State: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( stateLabel, constraints);
		
		//state field
		stateField = new JTextField();
		stateField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( stateField, constraints );
		constraints.weightx = 0;
		
		//zip label
		zipLabel = new JLabel("Zip: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( zipLabel, constraints);
		
		//zip field
		zipField = new JTextField();
		zipField.setEditable( true );
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
		phoneField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( phoneField, constraints );
		constraints.weightx = 0;
		
		//SSN label
		ssnLabel = new JLabel("SSN: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( ssnLabel, constraints);
		
		//SSN field
		ssnField = new JTextField();
		ssnField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( ssnField, constraints );
		constraints.weightx = 0;
		
		//bday label
		bdayLabel = new JLabel("Birthday (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( bdayLabel, constraints);
		
		//SSN field
		bdayField = new JTextField();
		bdayField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( bdayField, constraints );
		constraints.weightx = 0;
		
		//position label
		positionLabel = new JLabel("Position: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( positionLabel, constraints);
		
		//position field
		positionField = new JTextField();
		positionField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( positionField, constraints );
		constraints.weightx = 0;
		
		//salary label
		salaryLabel = new JLabel("Salary: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( salaryLabel, constraints);
		
		//salary field
		salaryField = new JTextField();
		salaryField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( salaryField, constraints );
		constraints.weightx = 0;
		
		//deduct label
		deductLabel = new JLabel("Deductions: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( deductLabel, constraints);
				
		//deduct field
		deductField = new JTextField();
		deductField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( deductField, constraints );
		constraints.weightx = 0;
		
		//Department label 
		departmentLabel = new JLabel("Department: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( departmentLabel, constraints);
		
		//Department field
		departmentField = new JTextField();
		departmentField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( departmentField, constraints );
		constraints.weightx = 0;
		
		//startDate label 
		startDateLabel = new JLabel("Start Date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( startDateLabel, constraints);
		
		//startDate field
		startDateField = new JTextField();
		startDateField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( startDateField, constraints );
		constraints.weightx = 0;
		
		//ID label 
		idLabel = new JLabel("New ID: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( idLabel, constraints);
		
		//ID field
		idField = new JTextField();
		idField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( idField, constraints );
		constraints.weightx = 0;
        /*
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
		*/
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
		
		//For subclasses
		numRows = constraints.gridy;
		
		//Handle setup
		this.pack();
		this.setVisible( true );
		this.setBounds((screenWidth-HFrameDimension)/2, (screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		this.getContentPane().setBackground( Color.white );
		this.repaint();
	}
	
	private void logout()
	{
		//Reverse whatever is done during the login process
		dispose();
		UserLogin loginScreen = new UserLogin();
		loginScreen.setup("");
	}
	
	private void submitPersonalInfo() throws IOException
	{
		if( bdayField.getText().matches("\\d{2}/\\d{2}/\\d{4}") && 
				startDateField.getText().matches("\\d{2}/\\d{2}/\\d{4}"))
		{
			//Parse text fields and submit info
			int position = Integer.parseInt( positionField.getText() );
			int salary = Integer.parseInt( salaryField.getText() );
			int bday = Integer.parseInt( bdayField.getText().replaceAll("/", "") );
			String first = firstField.getText();
			String last = lastField.getText();
			char middle = middleField.getText().charAt(0);
			String street = streetField.getText();
			String city = cityField.getText();
			String state = stateField.getText();
			int zip = Integer.parseInt( zipField.getText() );
			String phone = phoneField.getText();
			int department = Integer.parseInt( departmentField.getText() );
			int startDate = Integer.parseInt( startDateField.getText().replaceAll("/", "") );
			int proj1 = -1; //Integer.parseInt( project1Field.getText() );
			int proj2 = -1; //Integer.parseInt( project2Field.getText() );
			int proj3 = -1; //Integer.parseInt( project3Field.getText() );
			int deduct = Integer.parseInt( deductField.getText() );
			
			Employee e = new Employee(position, salary, startDate, first, last, middle, 
					street, city, state, zip, phone, bday, department, 
					proj1, proj2, proj3, deduct, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			
			positionField.setEditable( false );
			salaryField.setEditable( false );
			bdayField.setEditable( false );
			ssnField.setEditable( false );
			firstField.setEditable( false );
			lastField.setEditable( false );
			middleField.setEditable( false );
			streetField.setEditable( false );
			cityField.setEditable( false );
			stateField.setEditable( false );
			zipField.setEditable( false );
			phoneField.setEditable( false );
			departmentField.setEditable( false );
			startDateField.setEditable( false );
			deductField.setEditable( false );
			//project1Field.setEditable( false );
			//project2Field.setEditable( false );
			//project3Field.setEditable( false );
			idField.setText( e.getID()+"" );
		}
		else
		{
			if( !bdayField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				bdayField.setForeground( Color.RED );
				bdayField.setText("Improper format");
			}
			if( !startDateField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				startDateField.setForeground( Color.RED );
				startDateField.setText("Improper format");
			}
		}
	}
	
	public void actionPerformed( ActionEvent e )
	{
		try
		{
			if( e.getSource() == saveButton )
				submitPersonalInfo();
			else if( e.getSource() == logoutButton )
				logout();
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}
	}
}

