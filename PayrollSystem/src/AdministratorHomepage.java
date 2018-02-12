import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class AdministratorHomepage extends ManagerHomepage {
	
	public static Administrator loggedInAdministrator;
	
	private JButton promoteEmployeeButton, generateReportButton, createProjectButton;

	private int HEmployeeDimension 		= 30;
	private int VEmployeeDimension 		= 10;
	private int extraLength				= 25;
	private int VFrameDimension = super.VFrameDimension + extraLength;
	
	public AdministratorHomepage( Employee userParam) throws IOException 
	{
		//loggedInAdministrator = get admin based off ID from validate DB
		super( userParam );
		setTitle("Administrator - Homepage");
		loggedInAdministrator = (Administrator)userParam;
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
		

		//promoteEmployee button
		promoteEmployeeButton = new JButton("Promote an employee");
		promoteEmployeeButton.setBounds( (HFrameDimension-HEmployeeDimension)/2, VFrameDimension - VEmployeeDimension + VLogoutOffset,
				HEmployeeDimension, VEmployeeDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( promoteEmployeeButton, constraints );
		promoteEmployeeButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		//generateReport button
		createProjectButton = new JButton("Create a new project");
		createProjectButton.setBounds( (HFrameDimension-HEmployeeDimension)/2, VFrameDimension - VEmployeeDimension + VLogoutOffset,
				HEmployeeDimension, VEmployeeDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( createProjectButton, constraints );
		createProjectButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		//generateReport button
		generateReportButton = new JButton("Generate a Project Report");
		generateReportButton.setBounds( (HFrameDimension-HEmployeeDimension)/2, VFrameDimension - VEmployeeDimension + VLogoutOffset,
				HEmployeeDimension, VEmployeeDimension );
		constraints.weightx = 1;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( generateReportButton, constraints );
		generateReportButton.addActionListener( this );
		constraints.weightx = 0.0;
		
		this.setBounds((super.screenWidth-HFrameDimension)/2, (super.screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		this.repaint();
	}
	
	private void fireEmployee()
	{
		//Open JFrame asking for ID number, the frame should verify before submitting
		//It should also pull up a warning window if trying to fire someone
		//They're not allowed to fire.
		
		new FireEmployeePage( loggedInAdministrator );
	}
	
	private void promoteEmployee()
	{
		new PromoteEmployeePage( loggedInAdministrator );
	}
	
	private void generateReport()
	{
		new ProjectSelectorPage().setup();
	}
	
	private void createProject()
	{
		new ProjectCreationPage().setup();
	}
	
	public void actionPerformed( ActionEvent e )
	{
		super.actionPerformed(e);
		
		if( e.getSource() == promoteEmployeeButton )
			promoteEmployee();
		else if ( e.getSource() == fireEmployeeButton )
			fireEmployee();
		else if ( e.getSource() == generateReportButton )
			generateReport();
		else if ( e.getSource() == createProjectButton )
			createProject();
	}
}
