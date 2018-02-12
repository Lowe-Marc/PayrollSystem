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
public class AssignEmployeePage extends JFrame implements ActionListener 
{
	int proj1 = -1, proj2 = -1, proj3 = -1;
	int proj1Percent = 0, proj2Percent = 0, proj3Percent = 0;
	int proj1Start = 0, proj1End = 0, proj2Start = 0, proj2End = 0, 
			proj3Start = 0, proj3End = 0;
	JFrame empNum;
	JTextField text;
	JButton button;
	Employee employee;
	protected JButton editButton;
	private JButton saveButton;
	protected JButton logoutButton, paystubButton;
	private JTextField employeeIDField, project1Field, project1PercentField, project1StartField, project1EndField,
		project2Field, project2PercentField, project2StartField, project2EndField, project3Field,project3PercentField,
		project3StartField, project3EndField;
	private JLabel employeeIDLabel, project1Label, project1PercentLabel, project1StartLabel, project1EndLabel, 
		project2Label, project2PercentLabel, project2StartLabel, project2EndLabel, project3Label, project3PercentLabel, warningLabel,
		project3StartLabel, project3EndLabel;
	boolean one = true, two = true, three = true;
	
	protected int numRows 				= 0;
	protected int HFrameDimension 		= 400;
	protected int VFrameDimension 		= 500;
	protected int VLogoutOffset 		= 10;
	private int HSaveDimension 			= 30;
	private int VSaveDimension 			= 10;
	
	private GraphicsDevice screen 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	protected int screenWidth 			= screen.getDisplayMode().getWidth();
	protected int screenHeight 			= screen.getDisplayMode().getHeight();
	
	protected GridBagConstraints constraints = new GridBagConstraints();
	
	public AssignEmployeePage()
	{
		super("Assigning an Employee");
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
		
		//Employee ID label
		employeeIDLabel = new JLabel("Employee ID: " );
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add( employeeIDLabel, constraints);
		
		//Employee ID field
		employeeIDField = new JTextField();
		employeeIDField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( employeeIDField, constraints );
		constraints.weightx = 0;
		
		//Project1 label
		project1Label = new JLabel("Project 1: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project1Label, constraints);
		
		//Project1 field
		project1Field = new JTextField();
		project1Field.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project1Field, constraints );
		constraints.weightx = 0;
		
		//Project1 percent label
		project1PercentLabel = new JLabel("Project 1 percent (integer): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project1PercentLabel, constraints);
		
		//Project1 percent field
		project1PercentField = new JTextField();
		project1PercentField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project1PercentField, constraints );
		constraints.weightx = 0;
		
		//Project1 start label
		project1StartLabel = new JLabel("Project 1 start date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project1StartLabel, constraints);
		
		//Project1 start field
		project1StartField = new JTextField();
		project1StartField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project1StartField, constraints );
		constraints.weightx = 0;
		
		//Project1 end label
		project1EndLabel = new JLabel("Project 1 end date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project1EndLabel, constraints);
		
		//Project1 end field
		project1EndField = new JTextField();
		project1EndField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project1EndField, constraints );
		constraints.weightx = 0;
		
		//Project2 label
		project2Label = new JLabel("Project 2: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project2Label, constraints);
		
		//Project2 field
		project2Field = new JTextField();
		project2Field.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project2Field, constraints );
		constraints.weightx = 0;
		
		//Project2 percent label
		project2PercentLabel = new JLabel("Project 2 percent (integer): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project2PercentLabel, constraints);
		
		//Project2 percent field
		project2PercentField = new JTextField();
		project2PercentField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project2PercentField, constraints );
		constraints.weightx = 0;
		
		//Project2 start label
		project2StartLabel = new JLabel("Project 2 start date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project2StartLabel, constraints);
		
		//Project2 start field
		project2StartField = new JTextField();
		project2StartField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project2StartField, constraints );
		constraints.weightx = 0;
		
		//Project2 end label
		project2EndLabel = new JLabel("Project 2 end date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project2EndLabel, constraints);
		
		//Project2 end field
		project2EndField = new JTextField();
		project2EndField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project2EndField, constraints );
		constraints.weightx = 0;
		
		//Project3 label
		project3Label = new JLabel("Project 3: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project3Label, constraints);
		
		//Project3 field
		project3Field = new JTextField();
		project3Field.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project3Field, constraints );
		constraints.weightx = 0;
		
		//Project3 percent label
		project3PercentLabel = new JLabel("Project 3 percent (integer): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project3PercentLabel, constraints);
		
		//Project3 percent field
		project3PercentField = new JTextField();
		project3PercentField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project3PercentField, constraints );
		constraints.weightx = 0;
		
		//Project3 start label
		project3StartLabel = new JLabel("Project 3 start date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project3StartLabel, constraints);
		
		//Project3 start field
		project3StartField = new JTextField();
		project3StartField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project3StartField, constraints );
		constraints.weightx = 0;
		
		//Project3 end label
		project3EndLabel = new JLabel("Project 3 end date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( project3EndLabel, constraints);
		
		//Project3 end field
		project3EndField = new JTextField();
		project3EndField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( project3EndField, constraints );
		constraints.weightx = 0;
		
		//Allocation warning label
		warningLabel = new JLabel("Allocated to over 100%" );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( warningLabel, constraints);
		warningLabel.setForeground( Color.red );
		warningLabel.setVisible( false );
		
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
		
		empNum = new JFrame("Employee Number");
		empNum.pack();
		empNum.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		empNum.setSize(200, 75);
		text = new JTextField();
		text.setBounds(10,  10,  50,  20);
		empNum.add(text);
		empNum.setResizable( false );
        empNum.setLayout( null );
		empNum.setVisible(true);
		empNum.getContentPane().setBackground( Color.white );
        empNum.setLocation( 600, 300 );
		button = new JButton();
		button.setText("Enter");
		button.setBounds(100,  10,  50,  20);
		empNum.add(button);
		button.addActionListener(this);
	}
	
	private void logout()
	{
		//Reverse whatever is done during the login process
		dispose();
		UserLogin loginScreen = new UserLogin();
		loginScreen.setup("");
	}
	
	private void submitPersonalInfo() throws IOException
	{/*
		int proj1 = -1, proj2 = -1, proj3 = -1;
		int proj1Percent = 0, proj2Percent = 0, proj3Percent = 0;
		int proj1Start = 0, proj1End = 0, proj2Start = 0, proj2End = 0, 
				proj3Start = 0, proj3End = 0;*/
		boolean proj1StartValid = false, proj1EndValid = false, proj2StartValid = false, 
				proj2EndValid = false,proj3StartValid = false, proj3EndValid = false;
		
		//Parse text fields and submit info
		if(!project1Field.getText().equals("") )
			proj1 = Integer.parseInt( project1Field.getText() );
		if(!project2Field.getText().equals("") )
			proj2 = Integer.parseInt( project2Field.getText() );
		if(!project3Field.getText().equals("") )
			proj3 = Integer.parseInt( project3Field.getText() );
		
		if(!project1PercentField.getText().equals("") )
			proj1Percent = Integer.parseInt( project1PercentField.getText() );
		if(!project2PercentField.getText().equals("") )
			proj2Percent = Integer.parseInt( project2PercentField.getText() );
		if(!project3PercentField.getText().equals("") )
			proj3Percent = Integer.parseInt( project3PercentField.getText() );
		
		if( proj1 > 0 && one )
		{
			if( project1StartField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				proj1Start = Integer.parseInt( project1StartField.getText().replaceAll("/", "") );
				proj1StartValid = true;
			}
			else
			{
				project1StartField.setForeground( Color.RED );
				project1StartField.setText("Improper format");
			}
			
			if( project1EndField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				proj1End = Integer.parseInt( project1EndField.getText().replaceAll("/", "") );
				proj1EndValid = true;
			}
			else
			{
				project1EndField.setForeground( Color.RED );
				project1EndField.setText("Improper format");
			}
		}
		else
		{
			proj1StartValid = true;
			proj1EndValid = true;
		}
		
		if( proj2 > 0 && two )
		{
			if( project2StartField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				proj2Start = Integer.parseInt( project2StartField.getText().replaceAll("/", "") );
				proj2StartValid = true;
			}
			else
			{
				project2StartField.setForeground( Color.RED );
				project2StartField.setText("Improper format");
			}
			
			if( project2EndField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				proj2End = Integer.parseInt( project2EndField.getText().replaceAll("/", "") );
				proj2EndValid = true;
			}
			else
			{
				project2EndField.setForeground( Color.RED );
				project2EndField.setText("Improper format");
			}
		}
		else
		{
			proj2StartValid = true;
			proj2EndValid = true;
		}
		
		if( proj3 > 0 && three )
		{
			if( project3StartField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				proj3Start = Integer.parseInt( project3StartField.getText().replaceAll("/", "") );
				proj3StartValid = true;
			}
			else
			{
				project3StartField.setForeground( Color.RED );
				project3StartField.setText("Improper format");
			}
			
			if( project3EndField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				proj3End = Integer.parseInt( project3EndField.getText().replaceAll("/", "") );
				proj3EndValid = true;
			}
			else
			{
				project3EndField.setForeground( Color.RED );
				project3EndField.setText("Improper format");
			}
		}
		else
		{
			proj3StartValid = true;
			proj3EndValid = true;
		}
		
		if( proj1StartValid && proj1EndValid && proj2StartValid && proj2EndValid && 
				proj3StartValid && proj3EndValid )
		{
			if( (proj1Percent + proj2Percent + proj3Percent) <= 100 )
			{
				
				Employee employee = new Employee( Integer.parseInt( employeeIDField.getText() ) );
				
				employee.setProject1( proj1 );
				employee.setProj1Start( proj1Start );
				employee.setProj1End( proj1End );
				employee.setProj1Percent( proj1Percent );
				employee.setProject2( proj2 );
				employee.setProj2Start( proj2Start );
				employee.setProj2End( proj2End );
				employee.setProj2Percent( proj2Percent );
				employee.setProject3( proj3 );
				employee.setProj3Start( proj3Start );
				employee.setProj3End( proj3End );
				employee.setProj3Percent( proj3Percent );
				
				employeeIDField.setEditable( false );
				project1Field.setEditable( false );
				project1PercentField.setEditable( false );
				project1StartField.setEditable( false );
				project1EndField.setEditable( false );
				project2Field.setEditable( false );
				project2PercentField.setEditable( false );
				project2StartField.setEditable( false );
				project2EndField.setEditable( false );
				project3Field.setEditable( false );
				project3PercentField.setEditable( false );
				project3StartField.setEditable( false );
				project3EndField.setEditable( false );
				
				warningLabel.setVisible( false );
				
				if( proj1 > 0 && one )
				{
					Project p1 = new Project( proj1 );
					p1.addMember( employee.employeeID);
				}
				if( proj2 > 0 && two )
				{
					Project p2 = new Project( proj2 );
					p2.addMember( employee.employeeID);
				}
				if( proj3 > 0 && three )
				{
					Project p3 = new Project( proj3 );
					p3.addMember( employee.employeeID );
				}
			}
			else
			{
				warningLabel.setVisible( true );
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
			else if( e.getSource() == button )
				populate();
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}
	}

	private void populate() throws NumberFormatException, IOException {
		empNum.dispose();
		employee = new Employee(Integer.parseInt(text.getText()));
		proj1 = employee.project1; proj2 = employee.project2; proj3 = employee.project3;
		proj1Start = employee.proj1Start; proj1End = employee.proj1End; 
			proj2Start = employee.proj2Start; proj2End = employee.proj2End; 
			proj3Start = employee.proj3Start; proj3End = employee.proj3End;
		proj1Percent = employee.proj1Percent; proj2Percent = employee.proj2Percent;
			proj3Percent = employee.proj3Percent;
		
		employeeIDField.setText("" + employee.getID());
		employeeIDField.setEditable(false);
		
		if(employee.department != ManagerHomepage.loggedInManager.department){
			empNum = new JFrame("Cannot Assign!");
			empNum.pack();
			empNum.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			empNum.setSize(200, 75);
			empNum.setResizable( false );
	        empNum.setLayout( null );
			empNum.setVisible(true);
			empNum.getContentPane().setBackground( Color.red );
	        empNum.setLocation( 600, 300 );
	        this.dispose();
		}
		
		if(proj1 > 0){
			project1Field.setText("" + proj1);
			project1PercentField.setText("" + proj1Percent);
			project1StartField.setText("" + proj1Start);
			project1EndField.setText("" + proj1End);
			project1Field.setEditable(false);
			project1PercentField.setEditable(false);
			project1StartField.setEditable(false);
			project1EndField.setEditable(false);
			one = false;
		}
		if(proj2 >0){
			project2Field.setText("" + proj2);
			project2PercentField.setText("" + proj2Percent);
			project2StartField.setText("" + proj2Start);
			project2EndField.setText("" + proj2End);
			project2Field.setEditable(false);
			project2PercentField.setEditable(false);
			project2StartField.setEditable(false);
			project2EndField.setEditable(false);
			two = false;
		}
		if(proj3 > 0){
			project3Field.setText("" + proj3);
			project3PercentField.setText("" + proj3Percent);
			project3StartField.setText("" + proj3Start);
			project3EndField.setText("" + proj3End);
			project3Field.setEditable(false);
			project3PercentField.setEditable(false);
			project3StartField.setEditable(false);
			project3EndField.setEditable(false);
			three = false;
		}
	}
}

