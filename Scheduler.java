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

	//Never used 
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

		//A lot of variables 
		File f = new File(args[0]); 
		Scanner s = new Scanner(f);
		int graphSize = 0;  
		String course = "";
		int prereq = 0; 
		ArrayList<Course> c = new ArrayList<Course>(prereq);
		Graph<String> graph;  
		boolean scannerClose = false;
		ArrayList<Integer> ordering = new ArrayList<>(); 
		ArrayList<Integer> activeSet = new ArrayList<>(); 
		int mNode = 0;
		boolean topologicalOrder = false;

		//First read over. Makes Course object 
		if (!f.exists()){
			System.out.println("File does not exist.");
			return;
		} else {
			scannerClose = false; 
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

		//Insert nodes in graph 
		for (int i = 0; i < graph.getSize(); i++){
			graph.setValue(i, c.get(i).getName()); 
		}
		s.close();
		scannerClose = true;

		//Second read over. Insert edges in graph 
		Scanner s2 = new Scanner(f);
		if (scannerClose){
			String line1 = s2.nextLine(); //throw away
			while (s2.hasNextLine()){
				String line2 = s2.nextLine(); 
				String[] splitPrereq = line2.split(" "); 
				for (int i = 2; i < splitPrereq.length; i++){
					graph.insertEdge(splitPrereq[0], splitPrereq[i]);
				}
			}
		}

		//If a node has an incoming edge, it's -1
		//If it does not have any, then its node number is added to active set 
		for (Course crs: c){
			int nodeIndex = graph.getNodeWithNoIncoming(graph.lookup(crs.getName())); 
			if (nodeIndex != -1){
				activeSet.add(nodeIndex);
			}
		}

		//Topological ordering 
		//Selects random node (winner) from active set. Removes all the edges 
		//Once mNode does not have any incoming edges, add to active set
		//Repeat until active set is empty 
        while (!activeSet.isEmpty()){
            Random r = new Random(); 
			Integer winner = activeSet.get(r.nextInt(activeSet.size())); //node number 
			activeSet.remove(winner); 
            ordering.add(winner);
            for (int m = 0; m < graph.getSize(); m++){
				if (graph.isEdge(m, winner)){
					graph.removeEdge(m, winner);
					mNode = m;
					if (!graph.hasIncomingEdge(mNode)){
						activeSet.add(mNode);
					}
				}
			}	
		}

		//Graph has topological order once all of the edges are removed 
		if (graph.isEmpty()){
			topologicalOrder = true;
		} else {
			System.out.println("There is no topological order.");
		}
		
		//Prints out the order
		//hehe = numbering the courses from 1 to size 
		if (topologicalOrder){
			for (int o : ordering){
				int hehe = ordering.indexOf(o);
				System.out.println((hehe+=1) + ". " + graph.getValue(o)); 
			}	
		}
	}
}
