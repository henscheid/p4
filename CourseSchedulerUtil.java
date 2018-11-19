
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Iterator;


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
	GraphImpl<String> graph;

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	//
	//		Entity<String>[] entities = new Entity[10];
	//		// parsing file "JSONExample.json" 
	//		Object obj = new JSONParser().parse(new FileReader(fileName)); 
	//
	//		// typecasting obj to JSONObject 
	//		JSONObject jo = (JSONObject) obj; 
	//
	//		// getting phoneNumbers 
	//		JSONArray ja = (JSONArray) jo.get("courses"); 
	//
	//		// iterating phoneNumbers 
	//		Iterator itr2 = ja.iterator(); 
	//		int size = ja.size();
	//		entities = new Entity[size];
	//		//System.out.println(size);
	//		int i = 0;
	//		while (itr2.hasNext()) { 
	//			Entity temp = new Entity<T>();
	//			String name;
	//			Object [] prereqs = null ;
	//
	//			System.out.println();
	//			JSONObject temp1 = (JSONObject) itr2.next();
	//			name = (String) temp1.get("name");
	//
	//			JSONArray temp2 = (JSONArray) temp1.get("prerequisites");
	//			int size2 = temp2.size();
	//			prereqs = new Object[size2];
	//			for(int k=0; k<size2;k++) {
	//				prereqs[k] = temp2.get(k);
	//			}
	//
	//			//			System.out.println("Name: " + name);
	//			//			for(Object e : prereqs) {
	//			//				System.out.println("prereqs: " + e.toString());
	//			//			}
	//
	//			//JSONArray temp3 = (JSONArray) temp2.get("prerequisites");
	//			//System.out.println(temp2.get(0));
	//			System.out.println();
	//			temp.setName(name);
	//
	//			temp.setPrerequisites(prereqs);
	//
	//			entities[i] = temp;
	//
	//			i++;
	//			System.out.println();
	//		}
	//		Object[] printTest = entities[3].getPrerequisites();
	//		for(Object f : printTest) {
	//			System.out.println("prereqs: " + f.toString());
	//		}
	//		return entities;
	//	}

	//		System.out.println(temp2.getClass());
	//		Iterator itr3 = temp2.iterator();
	//		System.out.println("class name " + name);
	//		System.out.println("prereqs");
	//		int j = 0;
	//		while(itr3.hasNext()){
	//			int size5 = temp2.size();
	//			prereqs = new String[size2];
	//			
	//			String var = (String)itr3.next();
	//			System.out.println("j value" + j);
	//			System.out.println("course added" + var);
	//			prereqs[j] = var;
	//			j++;
	//		}




	//		String[] prereq1 ;
	//		for (int z=0; z<entities.length;z++) {
	//			Entity<String> print1 = entities[z];
	//			String name1 = print1.getName();
	//			System.out.println(name1);
	//			if (print1.getPrerequisites() != null) {
	//				prereq1 = print1.getPrerequisites();
	//			}
	//			prereq1 = new String[0];
	//			for(String f : prereq1) {
	//				System.out.println(f);
	//			}
	//		}
	//		
	//		


	/*
		Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator(); 
		while (itr1.hasNext()) { 
			Entity temp = new Entity<String>();
			int i = 0;

			Map.Entry prereqs= itr1.next(); 
			Map.Entry name =  itr1.next();

			Iterator<Object> test = prereqs.

			System.out.println("name" + name.getValue());
			System.out.println("Prereqs " + prereqs.getValue());
			temp.setName(name.getValue());
			System.out.println(temp.getName().toString());
			entities[i] = temp;
			//temp.setPrerequisites(prereqs.getValue());
			i++;
		} l

	 */




	/**
	 * Construct a directed graph from the created entity object 
	 * @param entities which has information about a single course 
	 * including its name and its prerequisites
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void constructGraph(Entity[] entities) {
		//TODO: implement this method
		//		int length1 = entities.length;
		//		for(int i=0; i<length1; i++) {
		//			String vertex1 = (String) entities[i].getName();
		//			//add the course name to the graph
		//			graphImpl.addVertex(vertex1);
		//			Object[] prereqs = entities[i].getPrerequisites();
		//			//Add all the pre req vertices to the graph
		//			// add edges from all the prereqs to teh vertices
		//			for (Object obj :  prereqs) {
		//				String obj2 = (String) obj;
		//				graphImpl.addVertex(obj2);
		//				graphImpl.addEdge(vertex1, obj2);
		//			}
		//		}

		int length1 = entities.length;
		for(int i=0; i<length1; i++) {
			T vertex1 = (T) entities[i].getName();
			//add the course name to the graph
			graphImpl.addVertex(vertex1);
			T[] prereqs = (T[]) entities[i].getPrerequisites();
			//Add all the pre req vertices to the graph
			// add edges from all the prereqs to teh vertices
			for (T obj :  prereqs) {
				graphImpl.addVertex(obj);
				graphImpl.addEdge(vertex1, obj);
			}
		}
		//graphImpl.printGraph();
	}

	/**
	 * Returns all the unique available courses
	 * @return the sorted list of all available courses
	 */
	public Set<T> getAllCourses() {
		//TODO: implement this method
		return graphImpl.getAllVertices();
	}


	/**
	 * To check whether all given courses can be completed or not
	 * @return boolean true if all given courses can be completed,
	 * otherwise false
	 * @throws Exception
	 */
//	public boolean canCoursesBeCompleted() throws Exception {
//		//TODO: implement this method
//
//		//try and traverse through and if there is a cycle it can't be done
//
//		//create a visited map
//		Map<T, Boolean> visited = new HashMap<T, Boolean>();
//		//create an on the recursion stack 
//		Map<T, Boolean> onStack = new HashMap<T,Boolean>();
//
//		//encode all of the vertices as not visited
//		Set<T> vertices = graphImpl.getAllVertices();
//		for( T vert : vertices) {
//			visited.put(vert, false);
//			onStack.put(vert, false);
//		}
//
//		for( T vert : vertices) {
//			if(hasCycleHelper(vert,visited,onStack)) {
//				// if there is a cycle classes can't be take
//				System.out.println("Can courses be taken");
//				return false;
//			} 
//		}
//		return true;
//	}
	

    private boolean bfs(T vertex, T original) {
    List<T> adj = graphImpl.getAdjacentVerticesOf(vertex);
    for (T course: adj) {
    if (course.equals(original))
    return false;
    else
    return bfs(course, original);
//    if (!visitedNodes.contains(course))
//    visitedNodes.add(course);
     
    }
    return true;
    }
    
    /**
     * To check whether all given courses can be completed or not
     * @return boolean true if all given courses can be completed,
     * otherwise false
     * @throws Exception
     */
    public boolean canCoursesBeCompleted() throws Exception {
        Set<T> vertices = getAllCourses();
        for (T vertex: vertices) {
        if (!bfs(vertex, vertex))
        return false;
        }
        
        return true;	
    }
	
	private boolean hasCycleHelper(T vertex, Map<T,Boolean>visitedMap,
			Map<T,Boolean> onStackMap) {
		System.out.print(vertex);
		System.out.println(onStackMap.get(vertex));
		System.out.println(visitedMap.get(vertex));
		if (onStackMap.get(vertex))
			return true;
		if (visitedMap.get(vertex))
			return false;
		
		onStackMap.replace(vertex, true);
		visitedMap.replace(vertex,true);
		
		List<T> predecessors = graphImpl.getAdjacentVerticesOf(vertex);
		for (T pred : predecessors) {
			System.out.println(pred);
			if(hasCycleHelper(pred,visitedMap,onStackMap)) {
				return true;
			}
			onStackMap.replace(pred, false);
		}
		
		
		return true;
	}




	/**
	 * The order of courses in which the courses has to be taken
	 * @return the list of courses in the order it has to be taken
	 * @throws Exception when courses can't be completed in any order
	 */
	public List<T> getSubjectOrder() throws Exception {
		//TODO: implement this method
		//i dont get how to start? how do we find the beginning node?
		return null;

	}


	/**
	 * The minimum course required to be taken for a given course
	 * @param courseName 
	 * @return the number of minimum courses needed for a given course
	 */
	public int getMinimalCourseCompletion(T courseName) throws Exception {
		//TODO: implement this method
		//find the shortest path to the spot
		// idk how to do this?
		return -1;

	}

}
