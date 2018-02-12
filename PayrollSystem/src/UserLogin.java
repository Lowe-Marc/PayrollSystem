import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserLogin extends JFrame implements ActionListener
{
	private int employeeID;
	
	private JButton loginButton;
	
	private int HFrameDimension 		= 300;
	private int VFrameDimension 		= 150;
	private int HLoginDimension 		= 30;
	private int VLoginDimension 		= 10;
	private int VLoginOffset 			= 10;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel, errorLabel;
	private JLabel passwordLabel;
	
	private GraphicsDevice screen 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int screenWidth 			= screen.getDisplayMode().getWidth();
	private int screenHeight 			= screen.getDisplayMode().getHeight();
	
	public UserLogin()
	{
		super("User Login");
		//Employeeid = 
		//get id from validate DB
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
		
		//username label
		usernameLabel = new JLabel("ID Number: " );
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add( usernameLabel, constraints);
		
		//username field
		usernameField = new JTextField();
		constraints.weightx = 1;
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.add( usernameField, constraints );
		constraints.weightx = 0;
		
		//password label
		passwordLabel = new JLabel("Password: ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.add( passwordLabel, constraints );
		
		//password Field
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		constraints.weightx = 1;
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.add( passwordField, constraints );
		constraints.weightx = 0;
		
		//Login button
		loginButton = new JButton("Login");
		loginButton.setBounds( (HFrameDimension-HLoginDimension)/2, VFrameDimension - VLoginDimension + VLoginOffset,
				HLoginDimension, VLoginDimension );
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.add( loginButton, constraints );
		loginButton.addActionListener( this );
		
		//error label
				errorLabel = new JLabel(label );
				errorLabel.setForeground(Color.RED);
				constraints.gridx = 0;
				this.add( errorLabel, constraints);
		
		//Handle setup
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible( true );
		this.setBounds((screenWidth-HFrameDimension)/2, (screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		this.getContentPane().setBackground( Color.white );
		this.repaint();
	}
	
	@SuppressWarnings("deprecation")
	private void login() throws IOException
	{
		employeeID = Integer.parseInt( usernameField.getText() );
		RandomAccessFile f = new RandomAccessFile(new File("EmployeeDB"), "r");
		if(f.length()/Employee.blockSize < employeeID || employeeID < 1)
			new UserLogin().setup("ID Does Not Exist");
		else{
			Employee loggedIn = new Employee( employeeID );
			
			//Pull up proper homepage depending on rank of employee
			dispose();
			if( !(loggedIn.validatePassword( passwordField.getText()) ))
				new UserLogin().setup("Incorrect Password");
			if(!loggedIn.getStatus())
					new UserLogin().setup("Employee Not Current");
			
				
			if( loggedIn.validatePassword(passwordField.getText() ) && loggedIn.getStatus()){
				if(loggedIn.position < 4)
					new EmployeeHomepage( loggedIn ).setup();
				else if( loggedIn.position == 4 )
					new ManagerHomepage( new Manager( employeeID ) ).setup();
				else 
					new AdministratorHomepage( new Administrator( employeeID ) ).setup();
			}
		}
	}
	public void actionPerformed( ActionEvent e )
	{
		try
		{
			if( e.getSource() == loginButton )
			{
				login();
			}
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}
	}

}
