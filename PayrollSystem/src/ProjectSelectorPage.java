import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ProjectSelectorPage extends JFrame implements ActionListener
{
	private int employeeID, date;
	
	private JButton getStubButton;
	
	private int HFrameDimension 		= 350;
	private int VFrameDimension 		= 150;
	private int HLoginDimension 		= 30;
	private int VLoginDimension 		= 10;
	private int VLoginOffset 			= 10;
	
	private JTextField projectField, dateField;
	private JLabel projectLabel, dateLabel;
	private JLabel errorLabel;
	
	private GraphicsDevice screen 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int screenWidth 			= screen.getDisplayMode().getWidth();
	private int screenHeight 			= screen.getDisplayMode().getHeight();
	
	public ProjectSelectorPage()
	{
		super("Select Project");
		//Employeeid = 
		//get id from validate DB
	}
	
	public void setup()
	{
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(2, 10, 2, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.weighty = 0;
		
		//project label
		projectLabel = new JLabel("Select Project Number: " );
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add( projectLabel, constraints);
		
		//project field
		projectField = new JTextField();
		constraints.weightx = 1;
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.add( projectField, constraints );
		constraints.weightx = 0;
		
		//date label
		dateLabel = new JLabel("Enter Date: dd/mm/yyyy" );
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.add( dateLabel, constraints);
				
		//date field
		dateField = new JTextField();
		constraints.weightx = 1;
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.add( dateField, constraints );
		constraints.weightx = 0;
		
		//getStub button
		getStubButton = new JButton("Generate");
		getStubButton.setBounds( (HFrameDimension-HLoginDimension)/2, VFrameDimension - VLoginDimension + VLoginOffset,
				HLoginDimension, VLoginDimension );
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.add( getStubButton, constraints );
		getStubButton.addActionListener( this );
		
		//error label
		errorLabel = new JLabel("Project does not exist." );
		constraints.gridx = 0;
		this.add( errorLabel, constraints);
		errorLabel.setForeground( Color.white );
		
		//Handle setup
		this.pack();
		this.setVisible( true );
		this.setBounds((screenWidth-HFrameDimension)/2, (screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		this.getContentPane().setBackground( Color.white );
		this.repaint();
	}
	
	private void getReport( int projNum ) throws IOException
	{
		if( dateField.getText().matches("\\d{2}/\\d{2}/\\d{4}") )
		{
			date = Integer.parseInt( dateField.getText().replaceAll("/", "") );
			errorLabel.setForeground( Color.white );
			Report report = new Report( projNum );
			report.displayReportInFrame();
		}else{
			dateField.setForeground( Color.RED );
			dateField.setText("Improper format");
		}
		
	}
	
	public void actionPerformed( ActionEvent e )
	{
		try
		{
			if( e.getSource() == getStubButton )
				getReport( Integer.parseInt( projectField.getText() ) );
		}
		catch( IOException ioe )
		{
			errorLabel.setForeground( Color.RED );
		}
	}

}