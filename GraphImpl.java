import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Filename:   GraphImpl.java
 * Project:    p4
 * Course:     cs400 
 * Authors:    
 * Due Date:   
 * 
 * T is the label of a vertex, and List<T> is a list of
 * adjacent vertices for that vertex.
 *
 * Additional credits: 
 *
 * Bugs or other notes: 
 *
 * @param <T> type of a vertex
 */
public class GraphImpl<T> implements GraphADT<T> {

    // YOU MAY ADD ADDITIONAL private members
    // YOU MAY NOT ADD ADDITIONAL public members

    /**
     * Store the vertices and the vertice's adjacent vertices
     */
    private Map<T, List<T>> verticesMap; 
    
    
    /**
     * Construct and initialize and empty Graph
     */ 
    public GraphImpl() {
        verticesMap = new HashMap<T, List<T>>();
        // you may initialize additional data members here
    }

    public void addVertex(T vertex) {
    
    		//determine if the vertex should be added
    		if (vertex != null && !hasVertex(vertex)) {
    			verticesMap.put(vertex, new ArrayList<T>());
    		}
    		
    }

    public void removeVertex(T vertex) {
        //if the vertex exists, clear it
    		if (hasVertex(vertex)) {
    			//get all adjacent vertices
    			ArrayList<T> adjacentVertices = (ArrayList<T>) getAdjacentVerticesOf(vertex);
    			
    			for (T adVert : adjacentVertices) {
    				//remove edges where vertex is predecessor
    				removeEdge(adVert, vertex);
    					
    			}
    			//remove the vertex from the graph
    			verticesMap.remove(vertex);
    		}
    }

    public void addEdge(T vertex1, T vertex2) {
    	
    		//if the vertices don't exist, add them
        if (!hasVertex(vertex1)) addVertex(vertex1);
        if (!hasVertex(vertex2)) addVertex(vertex2);
        
        //at vertex2 to vertex1 prereqs if it isn't already
        if (!verticesMap.get(vertex1).contains(vertex2))
        		verticesMap.get(vertex1).add(vertex2);      
    }
    
    public void removeEdge(T vertex1, T vertex2) {
        
    		//if vertex1 and 2 aren't null
    		if (vertex1 != null && vertex2 != null) {
    			//and they are both in the graph
    			if (hasVertex(vertex1) && hasVertex(vertex2)) {
    				//and the edge exists
    				if (verticesMap.get(vertex1).contains(vertex2))
    					//get vertex1's list and remove vertex2
    					verticesMap.get(vertex1).remove(vertex2);
    			}	
    		}
    		
    }    
    
    public Set<T> getAllVertices() {		
        return verticesMap.keySet();
    }

    public List<T> getAdjacentVerticesOf(T vertex) {
    		//add all of the vertex's predecessors to the adjacent list
    		ArrayList<T> adjacentVertices = new ArrayList<T>();
    		for (T otherVertex : verticesMap.get(vertex)) 
    			adjacentVertices.add(otherVertex);
        Set<T> vertices = getAllVertices();

        for (T otherVertex: vertices) {
        		//if vertex is predecessor of otherVertex
        		if (verticesMap.get(otherVertex).contains(vertex)) {
        			//and otherVertex isn't in adjacency list
        			if (!adjacentVertices.contains(otherVertex))
        				adjacentVertices.add(otherVertex);
        		}
        }

        return adjacentVertices;
    }
    
    public boolean hasVertex(T vertex) {
    		if (verticesMap.containsKey(vertex))
    			return true;
        return false;
    }

  
    public int order() {  
    		//number of vertices
        return verticesMap.size();
    }

    public int size() {
    		//number of edges
    		int count = 0;
    		Set<T> vertices = getAllVertices();
    		
    		//for every vertex in the graph
    		for (T vertex: vertices) {
    			//if it has predecessors
    			if (!verticesMap.get(vertex).isEmpty())
    			//add num edges to count
    				count += verticesMap.get(vertex).size();
    		}
    		return count;
    }
    
    private boolean hasCycle(T vertex) {
    		int order = order();
    		boolean[] visited = new boolean[order];
    		for (int i = 0; i < visited.length; i++) {
    			visited[i] = false;
    		}
    		ArrayList<T> queue = new ArrayList<T>();
    		
    }
    
    private int numEdges(T vertex) {
    		return verticesMap.get(vertex).size();
    }
    private String listedEdges(T vertex) {
    		String list = "";
    		for (T edge: verticesMap.get(vertex)) {
    			list += edge.toString() + " ";
    		}
    		return list;
    }
    
    
    /**
     * Prints the graph for the reference
     * DO NOT EDIT THIS FUNCTION
     * DO ENSURE THAT YOUR verticesMap is being used 
     * to represent the vertices and edges of this graph.
     */
    public void printGraph() {

        for ( T vertex : verticesMap.keySet() ) {
            if ( verticesMap.get(vertex).size() != 0) {
                for (T edges : verticesMap.get(vertex)) {
                    System.out.println(vertex + " -> " + edges + " ");
                }
            } else {
                System.out.println(vertex + " -> " + " " );
            }
        }
    }
    
    public static void main(String args[]) {
    		GraphImpl<String> graph = new GraphImpl<String>();
    		graph.addEdge("dog", "animal");
    		graph.addEdge("cat", "animal");
    		graph.addEdge("bird", "animal");
    		graph.addEdge("animal", "living being");
    		graph.addEdge("tree", "living being");
    		graph.addEdge("dog", "gross");
    		graph.printGraph();
    		System.out.println();
    		System.out.println(graph.listedEdges("animal"));
    		System.out.println("adjacent to animal: " 
    				+ graph.getAdjacentVerticesOf("animal").toString());
    		System.out.println(graph.listedEdges("animal"));
    		
    		
    		System.out.println("order: " + graph.order());
    		System.out.println("size: " + graph.size());
   
    		System.out.println("all vertices: " + graph.getAllVertices().toString());
    		for (String vertex: graph.getAllVertices()) {
    			System.out.println(vertex + " numEdges"
    					+ ": " + graph.numEdges(vertex));
    		}

    }
}
