import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ProjectCreationPage extends JFrame implements ActionListener 
{
	protected JButton editButton;
	private JButton saveButton;
	protected JButton logoutButton, paystubButton;
	private JTextField nameField, startField, endField, otherField, numField, dept1Field, dept2Field, dept3Field;
	private JLabel nameLabel, startLabel, endLabel, otherLabel, numLabel, dept1Label, dept2Label, dept3Label;
	
	protected int numRows 				= 0;
	protected int HFrameDimension 		= 400;
	protected int VFrameDimension 		= 350;
	protected int VLogoutOffset 		= 10;
	private int HSaveDimension 			= 30;
	private int VSaveDimension 			= 10;
	
	private GraphicsDevice screen 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	protected int screenWidth 			= screen.getDisplayMode().getWidth();
	protected int screenHeight 			= screen.getDisplayMode().getHeight();
	
	protected GridBagConstraints constraints = new GridBagConstraints();
	
	public ProjectCreationPage()
	{
		super("Creating a new project");
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
		
		//Name label
		nameLabel = new JLabel("Project Name: " );
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add( nameLabel, constraints);
		
		//Name field
		nameField = new JTextField();
		nameField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( nameField, constraints );
		constraints.weightx = 0;
		
		//start label
		startLabel = new JLabel("Start Date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( startLabel, constraints);
		
		//start field
		startField = new JTextField();
		startField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( startField, constraints );
		constraints.weightx = 0;
		
		//end label
		endLabel = new JLabel("End Date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( endLabel, constraints);
		
		//end field
		endField = new JTextField();
		endField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( endField, constraints );
		constraints.weightx = 0;
		
		//other label
		otherLabel = new JLabel("Other costs: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( otherLabel, constraints);
		
		//other field
		otherField = new JTextField();
		otherField.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( otherField, constraints );
		constraints.weightx = 0;
		
		//Dept1 label
		dept1Label = new JLabel("Department 1: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( dept1Label, constraints);
		
		//Dept1 field
		dept1Field = new JTextField();
		dept1Field.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( dept1Field, constraints );
		constraints.weightx = 0;
		
		//Dept3 label
		dept3Label = new JLabel("Department 2: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( dept3Label, constraints);
		
		//Dept2 field
		dept2Field = new JTextField();
		dept2Field.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( dept2Field, constraints );
		constraints.weightx = 0;
		
		//Dept3 label
		dept3Label = new JLabel("Department 3: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( dept3Label, constraints);
		
		//Dept3 field
		dept3Field = new JTextField();
		dept3Field.setEditable( true );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( dept3Field, constraints );
		constraints.weightx = 0;
		
		//Project Number label
		numLabel = new JLabel("Project Number: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( numLabel, constraints);
		
		//Project Number field
		numField = new JTextField();
		numField.setEditable( false );
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( numField, constraints );
		constraints.weightx = 0;

		
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
	
	private void submitInfo() throws IOException
	{
		if( startField.getText().matches("\\d{2}/\\d{2}/\\d{4}") && 
				endField.getText().matches("\\d{2}/\\d{2}/\\d{4}"))
		{
			//Parse text fields and submit info
			String name = nameField.getText();
			int startDate = Integer.parseInt( startField.getText().replaceAll("/", "") );
			int endDate = Integer.parseInt( endField.getText().replaceAll("/", "") );
			int otherCosts = Integer.parseInt( otherField.getText() );
			int projNum = Project.nextProjectNumber();
			numField.setText("" + projNum);
			Project p = new Project( projNum, otherCosts, name, 
					new ArrayList<Integer>(), new ArrayList<Integer>(), startDate, endDate);
			
			nameField.setEditable( false );
			startField.setEditable( false );
			endField.setEditable( false );
			otherField.setEditable( false );
			dept1Field.setEditable( false );
			dept2Field.setEditable( false );
			dept3Field.setEditable( false );
			
			if( !dept1Field.getText().equals("") )
				p.addDepartment( Integer.parseInt( dept1Field.getText() ));
			if( !dept2Field.getText().equals("") )
				p.addDepartment( Integer.parseInt( dept2Field.getText() ));
			if( !dept3Field.getText().equals("") )
				p.addDepartment( Integer.parseInt( dept3Field.getText() ));
		}
		else
		{
			if( !endField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				endField.setForeground( Color.RED );
				endField.setText("Improper format");
			}
			if( !startField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
			{
				startField.setForeground( Color.RED );
				startField.setText("Improper format");
			}
		}
	}
	
	public void actionPerformed( ActionEvent e )
	{
		try
		{
			if( e.getSource() == saveButton )
				submitInfo();
			else if( e.getSource() == logoutButton )
				logout();
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}
	}
}