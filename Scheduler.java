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
		Scanner s = new Scanner(f);
		Scanner s2 = new Scanner(f);
		int graphSize = 0;  
		String course = "";
		ArrayList<String> courses = new ArrayList<String>(); 
		Graph<String> graph;  
		if (!f.exists()){
			System.out.println("File does not exist.");
			return;
		} else {
			System.out.println("Name: " + f); 
			graphSize = Integer.parseInt(s.nextLine());
			graph = new Graph<String>(graphSize);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String[] split = line.split(" "); 
				course = split[0];
				courses.add(course); 
			}
			
		}

		System.out.println("size: " + graph.getSize());
		for (int i = 0; i < graph.getSize(); i++){
			graph.setValue(i, courses.get(i)); 
		}
		
		for (int p = 0; p < graph.getSize(); p++){
			System.out.println(p + ": " + graph.getValue(p)); 
		}

		s.close();

	}
}
