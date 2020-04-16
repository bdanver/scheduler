import java.io.*;
import java.util.*; 

class Course {
    private String name;
    private int prereqs;
    public Course(){}

    public Course(String n, int p){
        name = n;
        prereqs = p;
    }

    public String getName(){
        return name; 
	}

	public int getPrereqCount(){
		return prereqs;
	}
}


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
		String course = "";
		int prereq = 0; 
		ArrayList<Course> c = new ArrayList<Course>(prereq);
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
				prereq = Integer.parseInt(split[1]);
				Course crs = new Course(course, prereq);
				c.add(crs); 
			}
		}
		for (int i = 0; i < graph.getSize(); i++){
			graph.setValue(i, c.get(i).getName()); 
		}

		System.out.println("size: " + graph.getSize());
		s.close();
		scannerClose = true;

		//Second read over
		Scanner s2 = new Scanner(f);
		if (scannerClose){
			String line1 = s2.nextLine(); //throw away
			while (s2.hasNextLine()){
				String line2 = s2.nextLine(); 
				String[] splitPrereq = line2.split(" "); 
				//if your prereq is 0 you have no edges 
				for (int i = 2; i < splitPrereq.length; i++){
					//System.out.println(splitPrereq[0] + " : " + splitPrereq[i]);
					graph.insertEdge(splitPrereq[0], splitPrereq[i]);
				}
			}
		}
		// System.out.println(graph.isEdge(graph.lookup("CPSC430"), graph.lookup("CPSC340")));
		// System.out.println(graph.isEdge(graph.lookup("CPSC430"), graph.lookup("CPSC110")));
	}
}
