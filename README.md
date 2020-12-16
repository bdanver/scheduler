# Course Scheduler
The project illustrates a Directed Acyclic Graph (DAG) in the form of a course scheduler. In this example, Computer Science courses have prerequisites,
so in order to advance to the next course, students have to complete the required prerequisite courses. The program performs a topological sorting
algorithm to produce the order of courses to take based on prerequisites, ie. A student must take Course A before Course B. 

The following is the topological sorting algorithm used:
1. Set the ordering to empty.
2. Find the set of nodes with no edges coming into them. Call this the active set.
3. While there are nodes in the active set:
    1. Move a node N from the active set to the ordering.
    2. For each edge coming out of N and into M:
         * Remove the edge from the graph.
         * If M now has no edges coming into it, add it to the active set.
4. If the graph has any edges left, then there is no topological ordering!
5. Otherwise, the topological ordering is in the “ordering” list.

Link to assignment from CPSC 340: Data Structures and Algorithms [here](https://www.dropbox.com/s/ylqoebjvaqhfzsw/Assignment%205%20-%20Course%20Scheduler.pdf?dl=0).  
