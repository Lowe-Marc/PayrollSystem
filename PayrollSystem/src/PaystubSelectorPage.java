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

public class PaystubSelectorPage extends JFrame implements ActionListener
{
	private int employeeID;
	
	private JButton getStubButton;
	
	private int HFrameDimension 		= 300;
	private int VFrameDimension 		= 150;
	private int HLoginDimension 		= 30;
	private int VLoginDimension 		= 10;
	private int VLoginOffset 			= 10;
	
	private JTextField monthField;
	private JLabel monthLabel;
	private JLabel errorLabel;
	
	private GraphicsDevice screen 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int screenWidth 			= screen.getDisplayMode().getWidth();
	private int screenHeight 			= screen.getDisplayMode().getHeight();
	
	public PaystubSelectorPage()
	{
		super("Select month");
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
		
		//month label
		monthLabel = new JLabel("Month (mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add( monthLabel, constraints);
		
		//month field
		monthField = new JTextField();
		constraints.weightx = 1;
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.add( monthField, constraints );
		constraints.weightx = 0;
		
		//getStub button
		getStubButton = new JButton("Get Paystub");
		getStubButton.setBounds( (HFrameDimension-HLoginDimension)/2, VFrameDimension - VLoginDimension + VLoginOffset,
				HLoginDimension, VLoginDimension );
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.add( getStubButton, constraints );
		getStubButton.addActionListener( this );
		
		//error label
		errorLabel = new JLabel("Improper Format." );
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
	
	private void getStub( int date ) throws IOException
	{
		errorLabel.setForeground( Color.white );
		Paystub stub = new Paystub();
		stub.date = date;
		stub.displayPaystubInFrame();
	}
	
	public void actionPerformed( ActionEvent e )
	{
		try
		{
			if( monthField.getText().matches( "\\d{2}/\\d{4}" ) )
			{
				if( e.getSource() == getStubButton )
					getStub( Integer.parseInt( monthField.getText().replaceAll("/", "") ) );
			}
			else
				errorLabel.setForeground( Color.RED );
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}
		catch( NumberFormatException nfe )
		{
			monthField.setForeground( Color.RED );
			monthField.setText("Improper format");
		}
	}

}