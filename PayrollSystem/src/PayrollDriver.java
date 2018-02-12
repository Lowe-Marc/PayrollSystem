import java.io.IOException;

/**
 * 3/13/17
 * The purpose of this class is to handle the most general manipulation of the payroll system. 
 * This is primarily through performing transitions between windows.
 */
public class PayrollDriver {
	public static int numProjects = 0;
	
	public static void main(String[] args) throws IOException 
	{
		//new Driver();
		UserLogin loginScreen = new UserLogin();
		loginScreen.setup("");
	}
	
	private PayrollDriver()
	{
		
	}
	
}
