import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// Graph.java

class Graph<Type> {
    // the matrix stores the edge information
    private boolean[][] matrix;

    // this stores the values being stored by this graph
    private Type[] values;

    // the size of the graph
    private int size;

    // set the graph as empty
    public Graph(int size) {
        matrix = new boolean[size][size];
        this.size = size;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = false;
            }
        }

        // make space for the values (and ignore the cast warning)
        @SuppressWarnings("unchecked")
        Type[] values = (Type[]) new Object[size];
        this.values = values;
    }

    // lookup a node number by value
    public int lookup(Type value) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null && values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    // insert an edge by index
    public void insertEdge(int from, int to) {
        matrix[from][to] = true;
    }

    // insert an edge by value
    public void insertEdge(Type from, Type to) {
        int fromIndex = lookup(from);
        int toIndex = lookup(to);
        insertEdge(fromIndex, toIndex);
    }

    // remove an edge
    public void removeEdge(int from, int to) {
        matrix[from][to] = false;
    }

    // return whether these are connected
    public boolean isEdge(int from, int to) {
        return matrix[from][to];
    }

    // add a node's data to the graph
    public void setValue(int node, Type value) {
        values[node] = value;
    }

    // return the size of the graph
    public int getSize() {
        return size;
    }

    // get the value of a node
    public Type getValue(int index) {
        return values[index];
    }

    //Visual representation. Helps show which node does not have incoming edges 
    //If entire ROW is false, then that numbered node does not have incoming edges 
    //Col: incoming
    //Row: destination
    public void printGraph() {
        //Prints out col numbers that are also node numbers 
        for (int i = 0; i < size; i++){
            System.out.print("\t"  + i);
        }
        System.out.println();

        for (int i = 0; i < size; i++){
            //Prints row numbers
            System.out.print(i + "\t");
            for (int j = 0; j < size ; j++){
                //Prints whether node numbers are connected (true/false)
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //Checks to see if graph has any edges in it 
    public boolean isEmpty() {
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if (matrix[i][j] == true){
                    return false; 
                }
            }
        }
        return true; 
    }

    //Checks to see if node number has any incoming edges 
    //ie. nodeNumber = 4. Lets see which nodes enter 4 
    public boolean hasIncomingEdge(int nodeNumber) {
        //Storing the node's edge values 
        ArrayList<Boolean> nodeEdgesValues = new ArrayList<>(); 

        //Adding all of the true/false values from each node to nodeNumber to arraylist 
        for (int i = 0; i < size; i++){
            nodeEdgesValues.add(matrix[nodeNumber][i]);
        }

        //If there are not any true values in list, then the node does not have incoming nodes 
        for (boolean b : nodeEdgesValues){
            if (!nodeEdgesValues.contains(true)){
                return false; 
            }
        }
        return true; 
    }

    //Same as above but assigns -1 to nodes that do have incoming edges 
    //Simply returns nodeNumber if it doesn't have incoming edges 
    public int getNodeWithNoIncoming(int nodeNumber) {
        ArrayList<Boolean> nodeEdgesValues = new ArrayList<>(); 
        for (int i = 0; i < size; i++){
            nodeEdgesValues.add(matrix[nodeNumber][i]);
        }
        for (boolean b : nodeEdgesValues){
            if (nodeEdgesValues.contains(true)){
                return -1; 
            } 
        }
        return nodeNumber; 
    }
}

