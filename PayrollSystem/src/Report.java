import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Report {
	
	public static int date;
	
	private Project project;
	

	private static int lineWidth 			= 80;
	private static int columnOnePos 		= 10;
	private static int columnTwoPos 		= 40;
	private static int columnThreePos		= 60;
	
	private int HFrameDimension 			= 600;
	private int VFrameDimension 			= 400;
	
	private GraphicsDevice screen 			= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int screenWidth 				= screen.getDisplayMode().getWidth();
	private int screenHeight 				= screen.getDisplayMode().getHeight();
	
	private LinkedList<String> output 		= new LinkedList<String>();
	
	public Report( int projNum ) throws IOException 
	{
		project = new Project( projNum );
	}
	
	public void createReportView()
	{
		try
		{
			
			output.add( center("Project " + project.getName().trim(), '-') );
			output.add( "\n" );
			output.add( leftIndent("Project Number: " + project.getNum(), 10) );
			output.add( leftIndent("Time Period (days): " + project.getTime(), 10) );
			output.add( leftIndent("Other Cost: $" + project.getOtherCost(), 10 ));
			output.add( leftIndent("Total Cost: $" + project.getTotalCost(), 10 ));
			
			//Below line
			output.add("\n");
			output.add( center("",'-') );
			
			output.add( leftIndent( "Departments:", 10 ));
			for( Department d : project.getDepartments() )
				output.add( center(d.getDepartmentString(), ' ') );
			
			output.add( leftIndent( "Employees:", 10 ));
			for( Employee e : project.getMembers() )
			{
				System.out.println("In report: " + e.firstName + " " + e.middleInitial + ". " + e.lastName);
				output.add( center(e.firstName + " " + e.middleInitial + ". " + e.lastName, ' ' ) );
			}
			
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	private static String center( String line, char padChar )
	{
		int padSize = (lineWidth - line.length())/2;
		
		for( int i = 0; i < padSize; i++ )
			line = padChar + line + padChar;
		
		return line + "\n";
	}
	
	private static String leftIndent( String line, int indentSize )
	{
		for ( int i = 0; i < indentSize; i++ )
			line = line + " ";
		
		return line + "\n";
	}
	
	private static String threeColumns( String first, String second, String third )
	{
		
		int firstSpaceSize = columnTwoPos - (columnOnePos + first.length());
		int secondSpaceSize = columnThreePos - (columnTwoPos + second.length());
		String line = "";
		
		for( int i = 0; i < columnOnePos; i++ )
			line = line + " ";
		line = line + first;
		
		for( int i = 0; i < firstSpaceSize; i++ )
			line = line + " ";
		line = line + second;
		
		for( int i = 0; i < secondSpaceSize; i++)
			line = line + " ";
		line = line + third + "\n";
		
		return line;
	}
	
	public void displayReportInFrame()
	{
		JFrame display = new JFrame("Project Report");
		JTextArea text = new JTextArea();
		text.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		createReportView();
		
		for( String line : output )
		{
			text.append( line);
		}
		text.setEditable(false);
		
		display.setBounds((screenWidth-HFrameDimension)/2, (screenHeight-VFrameDimension)/2,
				HFrameDimension, VFrameDimension);
		display.add( text, 0 );
		display.getContentPane().setBackground( Color.white );
		display.setVisible(true);
		display.repaint();
	}
	
}
