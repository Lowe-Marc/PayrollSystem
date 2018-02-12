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

public class PromoteEmployeePage extends JFrame implements ActionListener
{
	private Administrator user;
	
	private JFrame confirmFrame;
	
	private JButton fireButton;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private int HFrameDimension 		= 350;
	private int VFrameDimension 		= 200;
	private int HLoginDimension 		= 30;
	private int VLoginDimension 		= 10;
	private int VLoginOffset 			= 10;
	
	private JTextField idField, dateField, positionField, salaryField;
	private JLabel idLabel, dateLabel, positionLabel, salaryLabel;
	
	private GraphicsDevice screen 		= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int screenWidth 			= screen.getDisplayMode().getWidth();
	private int screenHeight 			= screen.getDisplayMode().getHeight();
	
	public PromoteEmployeePage( Administrator userParam )
	{
		super("Promoting an employee");
		user = userParam;
		setup();
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
		
		//id label
		idLabel = new JLabel("Employee ID: " );
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add( idLabel, constraints);
		
		//id field
		idField = new JTextField();
		constraints.weightx = 1;
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.add( idField, constraints );
		constraints.weightx = 0;
		
		//date label
		dateLabel = new JLabel("Promotion Date (dd/mm/yyyy): " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( dateLabel, constraints);
		
		//date field
		dateField = new JTextField();
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( dateField, constraints );
		constraints.weightx = 0;
		
		//position label
		positionLabel = new JLabel("New Position: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( positionLabel, constraints);
		
		//position field
		positionField = new JTextField();
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( positionField, constraints );
		constraints.weightx = 0;

		//salary label
		salaryLabel = new JLabel("New Salary: " );
		constraints.gridx = 0;
		constraints.gridy++;
		this.add( salaryLabel, constraints);
		
		//salary field
		salaryField = new JTextField();
		constraints.weightx = 1;
		constraints.gridx = 1;
		this.add( salaryField, constraints );
		constraints.weightx = 0;
		
		//Promote button
		fireButton = new JButton("Promote");
		fireButton.setBounds( (HFrameDimension-HLoginDimension)/2, VFrameDimension - VLoginDimension + VLoginOffset,
				HLoginDimension, VLoginDimension );
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.gridx = 1;
		constraints.gridy++;
		this.add( fireButton, constraints );
		fireButton.addActionListener( this );
		
		//Handle setup
		this.pack();
		this.setVisible( true );
		this.setBounds((screenWidth-HFrameDimension)/2, (screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		this.getContentPane().setBackground( Color.white );
		this.repaint();
	}
	
	private void validatePromotion()
	{
		confirmFrame = new JFrame("Confirm promotion");
		confirmFrame.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(2, 10, 2, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.weighty = 0;
		
		//id label
		JLabel idLabel = new JLabel("Are you sure you want to promote employee " + idField.getText() + "? \n" );
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
		
		confirmFrame.pack();
		confirmFrame.setVisible( true );
		confirmFrame.setBounds((screenWidth-HFrameDimension)/2, (screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		confirmFrame.getContentPane().setBackground( Color.white );
		confirmFrame.repaint();
	}
	
	private void cancelPromotion()
	{
		confirmFrame.dispose();
	}
	
	private void promoteEmployee( int id, int pos, int salary, int date )
	{
		try
		{
			user.promoteEmployee(id, pos, salary, date);
			confirmFrame.dispose();
			this.dispose();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == fireButton )
			validatePromotion();
		else if( e.getSource() == confirmButton )
			promoteEmployee( Integer.parseInt( idField.getText() ), Integer.parseInt( positionField.getText()),
					Integer.parseInt( salaryField.getText()), Integer.parseInt( dateField.getText().replaceAll("/", "")) );
		else if ( e.getSource() == cancelButton )
			cancelPromotion();
	}
}
