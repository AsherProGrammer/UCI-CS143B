
import java.util.Scanner;

public class Mainclass {
	public static void main(String[] args){
		Manager Manager;
		//Scanner sc = new Scanner(System.in);
		System.out.println("Enter file name: ");
		System.out.print("> ");
		//String input = sc.nextLine();
		//if (input.trim().length() !=0){
			//System.out.println("Reading from file: " + input + " ...");
			//Manager = new Manager(input);
			Manager = new Manager("E:\\input.txt");
			Manager.run();
		//}
		//sc.close();
	}
}