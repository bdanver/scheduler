import java.io.*;
import java.util.*; 

public class Scheduler {
	public static void main(String args[]) throws FileNotFoundException, IOException{
		//File handling 
		if (args.length == 0){
			System.out.println("Must pass file name.");
			return;
		}

		File f = new File(args[0]);       
		if (!f.exists()){
			System.out.println("File does not exist.");
			return;
		} else {
			System.out.println("Name: " + f); 
		}
	}
}
