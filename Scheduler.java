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
		int graphSize = 0;  
		int prereqCount = 0; 
		String course = "";
		ArrayList<String> courses = new ArrayList<String>(); 
		ArrayList<Integer> prereqs = new ArrayList<Integer>(); //count
		Graph<String> graph;  
		boolean scannerClose = false;
		if (!f.exists()){
			System.out.println("File does not exist.");
			return;
		} else {
			scannerClose = false; 
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
		for (int i = 0; i < graph.getSize(); i++){
			graph.setValue(i, courses.get(i)); 
		}

		for (int p = 0; p < graph.getSize(); p++){
			System.out.println(p + ": " + graph.getValue(p)); 
		}
		System.out.println("size: " + graph.getSize());
		System.out.println("CPSC 305: " + graph.lookup("CPSC305")); 
		s.close();
		scannerClose = true;

		//Second read over
		Scanner s2 = new Scanner(f);
		if (scannerClose){
			String line1 = s2.nextLine(); //throw away
			while (s2.hasNextLine()){
				String line2 = s2.nextLine(); 
				String[] split2 = line2.split(" "); //split2[1] = numberPrereqs might need regex to split half 
				prereqCount = Integer.parseInt(split2[1]);
				prereqs.add(prereqCount); 
			}
		}
		
		for (int c = 0; c < prereqs.size(); c++){
			System.out.println(prereqs.get(c)); 
		}		
	}
}
