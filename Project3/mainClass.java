
import java.util.Scanner;

public class mainClass {
	public static void main(String[] args){
		Initializer Initializer;
		//Scanner sc = new Scanner(System.in);
		System.out.println("Enter file name: ");
		System.out.print("> ");
		//String input = sc.nextLine();
		//if (input.trim().length() !=0){
			//System.out.println("Reading from file: " + input + " ...");
			//Manager = new Manager(input);
			Initializer = new Initializer("E:\\input1.txt","E:\\input2.txt");
			Initializer.run();
		//}
		//sc.close();
	}
}