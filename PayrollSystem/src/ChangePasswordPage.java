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
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class ChangePasswordPage extends JFrame implements ActionListener
{
	private JFrame confirmFrame;
	private JButton changeButton;
	private JButton confirmButton;
	private JButton cancelButton;
	private int HFrameDimension 		= 350;
	private int VFrameDimension 		= 150;
	private int HLoginDimension 		= 30;
	private int VLoginDimension 		= 10;
	private int VLoginOffset 			= 10;
	private JPasswordField changeField;
	private JPasswordField confirmField;
	private JLabel changeLabel;
	private JLabel confirmLabel;
	private JLabel errorLabel;
	private JLabel infoLabel;
	private JLabel lengthLabel;
	private GraphicsDevice screen 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int screenWidth 			= screen.getDisplayMode().getWidth();
	private int screenHeight 			= screen.getDisplayMode().getHeight();
	
	public ChangePasswordPage() throws IOException
	{
		super("Changing Password");
		if(EmployeeHomepage.loggedInEmployee.validatePassword("password")){
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
	
	public void setup(String label)
	{
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(2, 10, 2, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.weighty = 0;
		
		//change label
		infoLabel = new JLabel("Must be 7-15 characters." );
		constraints.weightx = 2.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add( infoLabel, constraints);
		
		//change label
		changeLabel = new JLabel("New Password: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( changeLabel, constraints);
		
		//change field
		changeField = new JPasswordField();
		changeField.setEchoChar('*');
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( changeField, constraints );
		constraints.weightx = 0;
		
		//confirm label
		confirmLabel = new JLabel("Confirm Password: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( confirmLabel, constraints);
		
		//confirm field
		confirmField = new JPasswordField();
		confirmField.setEchoChar('*');
		constraints.gridx = 1;
		constraints.weightx = 1;
		this.add( confirmField, constraints );
		constraints.weightx = 0;
		
		//change button
		changeButton = new JButton("Change");
		changeButton.setBounds( (HFrameDimension-HLoginDimension)/2, VFrameDimension - VLoginDimension + VLoginOffset,
				HLoginDimension, VLoginDimension );
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( changeButton, constraints );
		changeButton.addActionListener( this );
		
		//error label
		errorLabel = new JLabel(label );
		errorLabel.setForeground(Color.RED);
		constraints.gridx = 0;
		this.add( errorLabel, constraints);
		
		//Handle setup
		this.pack();
		this.setVisible( true );
		this.setBounds((screenWidth-HFrameDimension)/2, (screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		this.getContentPane().setBackground( Color.white );
		this.repaint();
	}
	
	
	@SuppressWarnings("deprecation")
	private void validateChange()
	{
		if( confirmField.getText().equals(changeField.getText()) && 
				confirmField.getText().length() > 6 && confirmField.getText().length() < 16
				&& !(changeField.getText().equals("password")))
		{
			confirmFrame = new JFrame("Confirm Change");
			confirmFrame.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.insets = new Insets(2, 10, 2, 10);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 0;
			constraints.weighty = 0;
			
			//id label
			JLabel idLabel = new JLabel("Are you sure you want to change your password?" );
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 2.0;
			confirmFrame.add( idLabel, constraints );
			
			//confirm button
			confirmButton = new JButton("Confirm");
			confirmButton.setBounds( (HFrameDimension-HLoginDimension)/2, VFrameDimension - VLoginDimension + VLoginOffset,
					HLoginDimension, VLoginDimension );
			constraints.weightx = 0.0;
			constraints.weighty = 0.0;
			constraints.gridx = 0;
			constraints.gridy = 1;
			confirmFrame.add( confirmButton, constraints );
			confirmButton.addActionListener( this );
			
			//cancel button
			cancelButton = new JButton("Cancel");
			cancelButton.setBounds( (HFrameDimension-HLoginDimension)/2, VFrameDimension - VLoginDimension + VLoginOffset,
					HLoginDimension, VLoginDimension );
			constraints.weightx = 0.0;
			constraints.weighty = 0.0;
			constraints.gridx = 0;
			constraints.gridy = 2;
			confirmFrame.add( cancelButton, constraints );
			cancelButton.addActionListener( this );
			
			//length label
			lengthLabel = new JLabel("Password does not meet length requirements." );
			constraints.gridx = 0;
			constraints.gridy++;
			constraints.weightx = 2.0;
			confirmFrame.add( lengthLabel, constraints );
			lengthLabel.setForeground( Color.WHITE );
			
			confirmFrame.pack();
			confirmFrame.setVisible( true );
			int offSet = 25;
			confirmFrame.setBounds((screenWidth-HFrameDimension+offSet)/2, (screenHeight-VFrameDimension)/2,
					HFrameDimension-offSet, VFrameDimension);
			confirmFrame.getContentPane().setBackground( Color.white );
			confirmFrame.repaint();
			errorLabel.setText("Password Changed");
			errorLabel.setForeground( Color.blue );
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else
		{
			changeField.setText("");
			confirmField.setText("");
			errorLabel.setText("Invalid Password Try Again");
			errorLabel.setForeground(Color.RED);
		}
	}
	
	private void cancelChange()
	{
		errorLabel.setText("Password Not Changed");
		errorLabel.setForeground(Color.RED);
		confirmFrame.dispose();
	}
	
	@SuppressWarnings("deprecation")
	private void changePassword()
	{
		try
		{
			if ( EmployeeHomepage.loggedInEmployee.setPassword( confirmField.getText() ) < 0 )
				lengthLabel.setForeground( Color.red );
			else 
				confirmFrame.dispose();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == changeButton )
			validateChange();
		else if( e.getSource() == confirmButton )
			changePassword();
		else if ( e.getSource() == cancelButton )
			cancelChange();
	}

}