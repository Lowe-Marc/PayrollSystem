import java.io.IOException;

public class Test {
	
	public Test() throws IOException{
		Project p1 = new Project(4);
		Employee e = new Employee(3);
		System.out.println(p1.members.get(0));
	}

	public static void main(String[] args) throws IOException {
		new Test();

	}

}
