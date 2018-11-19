
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Filename:   CourseSchedulerUtil.java
 * Project:    p4
 * Authors:    Debra Deppeler
 * 
 * Use this class for implementing Course Planner
 * @param <T> represents type
 */

public class CourseSchedulerUtil<T> {
    
    // can add private but not public members
    
    /**
     * Graph object
     */
    private GraphImpl<T> graphImpl;

    
    /**
     * constructor to initialize a graph object
     */
    public CourseSchedulerUtil() {
        this.graphImpl = new GraphImpl<T>();
    }
    
    /**
    * createEntity method is for parsing the input json file 
    * @return array of Entity object which stores information 
    * about a single course including its name and its prerequisites
    * @throws Exception like FileNotFound, JsonParseException
    */
    @SuppressWarnings("rawtypes")
    public Entity[] createEntity(String fileName) throws Exception {
		//TODO: implement this method

		Entity<T>[] entities;
		// parsing file "JSONExample.json" 
		Object obj = new JSONParser().parse(new FileReader(fileName)); 

		// typecasting obj to JSONObject 
		JSONObject jo = (JSONObject) obj; 

		// getting phoneNumbers 
		JSONArray ja = (JSONArray) jo.get("courses"); 

		// iterating phoneNumbers 
		Iterator itr2 = ja.iterator(); 
		int size = ja.size();
		entities = new Entity[size];
		//System.out.println(size);
		int i = 0;
		while (itr2.hasNext()) { 
			Entity temp = new Entity<T>();
			T name;
			Object[] prereqTemp;
			T[] prereqs = null ;

			System.out.println();
			JSONObject temp1 = (JSONObject) itr2.next();
			name = (T) temp1.get("name");

			JSONArray temp2 = (JSONArray) temp1.get("prerequisites");
			int size2 = temp2.size();
			prereqTemp = new Object[size2];
			prereqs = (T[]) new  Object[size2];
			for(int k=0; k<size2;k++) {
				prereqs[k] = (T) temp2.get(k);
			}

			//			System.out.println("Name: " + name);
			//			for(Object e : prereqs) {
			//				System.out.println("prereqs: " + e.toString());
			//			}

			//JSONArray temp3 = (JSONArray) temp2.get("prerequisites");
			//System.out.println(temp2.get(0));
			System.out.println();
			temp.setName(name);

			temp.setPrerequisites(prereqs);

			entities[i] = temp;

			i++;
			System.out.println();
		}
		//		Object[] printTest = entities[3].getPrerequisites();
		//		for(Object f : printTest) {
		//			System.out.println("prereqs: " + f.toString());
		//		}
		return entities;
}
    
    
    /**
     * Construct a directed graph from the created entity object 
     * @param entities which has information about a single course 
     * including its name and its prerequisites
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void constructGraph(Entity[] entities) {
        
    		//for every entity
    		for (int i = 0; i < entities.length; i++) {
    			Entity entity = entities[i];
    			T vertex = (T) entity.getName();
    			
    			//set edges
    			T[] prereqs = (T[]) entity.getPrerequisites();
    			for (int j = 0; j < prereqs.length; j++) {
    				graphImpl.addEdge(vertex, prereqs[j]);
    			}
    		}

    }
    
    
    /**
     * Returns all the unique available courses
     * @return the sorted list of all available courses
     */
    public Set<T> getAllCourses() { 
        return graphImpl.getAllVertices();
    }

    /*
     * Helper method for canCoursesBeCompleted(), detects
     * if there are any cycles in the graph. If there are cycles the
     * courses cannot be completed in any order.
     * @return boolean true if contains any cycle, else returns false
     */
    private boolean isCyclic(T vertex, LinkedList<T> visited, Stack<T> stack) {
    		//if the vertex hasn't been visited, mark it visited and add to stack
    		if (!visited.contains(vertex)) {
    			visited.add(vertex);
    			stack.push(vertex);
    			
    			//for every adjacent course
    			for (T course: graphImpl.getAdjacentVerticesOf(vertex)) {
    				//if it's cyclic, return true
    				if ((!visited.contains(course)) && isCyclic(course, visited, stack))
    					return true;
    				//if it's still in the stack, return true
    				else if (stack.contains(course))
    					return true;
    			}
    		}
    		
    		stack.remove(vertex);
    		
    		return false;
    }
    
    /**
     * To check whether all given courses can be completed or not
     * @return boolean true if all given courses can be completed,
     * otherwise false
     * @throws Exception
     */
    public boolean canCoursesBeCompleted() throws Exception {
        Set<T> vertices = getAllCourses();
        LinkedList<T> visited = new LinkedList<T>();
        Stack<T> stack = new Stack<T>();
        
        //for every course
        for (T vertex: vertices)
        		//if any have a cycle, return false
        		if (isCyclic(vertex, visited, stack))
        			return false;
       
        
        return true;
    }
    
    /*
     * recursive helper method for dfs()
     */
    private void dfsUtil(T vertex, LinkedList<T> visited, Stack<T> stack) {
    		//mark visited
    		visited.add(vertex);
    		
    		//for every adjacent vertex, recursive call if havent been visited
    		for (T course: graphImpl.getAdjacentVerticesOf(vertex)) {
    			if (!visited.contains(course))
    				dfsUtil(course, visited, stack);
    		}
    		//add vertex to the ordered list
    		stack.push(vertex);
    }
    
    
    /*
     * this method performs a depth-first search to find the order
     * of courses in which they have to be taken.
     * @return the list of courses in the order they must be taken
     */
    
    private List<T> dfs() {
    		LinkedList<T> visited = new LinkedList<T>();
    		Stack<T> stack = new Stack<T>();
    		Set<T> vertices = getAllCourses();
    		LinkedList<T> orderedList = new LinkedList<T>();
    		// for every vertex
    		for (T vertex: vertices) {
    			//if it hasn't been visited, find all prereqs
    			if (!visited.contains(vertex))
    				dfsUtil(vertex, visited, stack);
    		}
    		//copy the stack to a list
    		while (!stack.empty())
    			orderedList.addFirst(stack.pop());
    		
    		return orderedList;
    }
    
    
    /**
     * The order of courses in which the courses has to be taken
     * @return the list of courses in the order it has to be taken
     * @throws Exception when courses can't be completed in any order
     */
    public List<T> getSubjectOrder() throws Exception {
    		// if there are no cycles, return course order
        if (canCoursesBeCompleted())
        		return dfs();
        else
        		throw new Exception("Courses can't be completed in any order");
       

    }
    /*
     * recursive method that finds the total prereqs for a course, called by
     * getMinimalCourseCompletion
     * @param courseName, visited
     * @return # of prereqs + 1 (which is the course itself)
     */

    private int getMinHelper(T courseName, LinkedList<T> visited) {
    		//if it hasn't been visited, add it to the list
    		if (!visited.contains(courseName))
    			visited.add(courseName);
    		
    		//add all of the prereq courses, and their prereqs, etc
    		for (T adj: graphImpl.getAdjacentVerticesOf(courseName))
    			getMinHelper(adj,visited);
    		
    		//total visited nodes is the # of prereqs
    		return visited.size();
    }
        
    /**
     * The minimum course required to be taken for a given course
     * @param courseName 
     * @return the number of minimum courses needed for a given course
     */
    public int getMinimalCourseCompletion(T courseName) throws Exception {
    		LinkedList<T> visited = new LinkedList<T>();
        return getMinHelper(courseName, visited) - 1;
        
    }
    
    public static void main (String args[]) {
    		CourseSchedulerUtil<String> scheduler = new CourseSchedulerUtil<String>();
    		try {
				Entity[] entity = scheduler.createEntity("valid.json");
				scheduler.constructGraph(entity);
				System.out.println(scheduler.canCoursesBeCompleted());
				System.out.println(scheduler.getAllCourses().toString());
				scheduler.graphImpl.printGraph();
				System.out.println(scheduler.getSubjectOrder().toString());
				System.out.println(scheduler.getMinimalCourseCompletion("CS400"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
    		
    	
    }
    
}
