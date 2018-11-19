import java.util.NoSuchElementException; // expect to need
import static org.junit.Assert.*; 
import org.junit.Before;  // setUp method
import org.junit.After;   // tearDown method
import org.junit.Test; 

public class junittests {


	@Test
	public void test01() throws Exception {
		CourseSchedulerUtil<String> test = new CourseSchedulerUtil();
		test.createEntity("valid.json");
		}
	
	@Test
	public void test02graphTest() throws Exception{
//		CourseSchedulerUtil<String> test = new CourseSchedulerUtil();
//		Entity[] test2 = test.createEntity("valid.json");
//		test.constructGraph(test2);
//		System.out.println("order: " + test.graph.order());
//		System.out.println("size: " + test.graph.size());
//
//		System.out.println("all vertices: " + test.graph.getAllVertices().toString());
//		for (String vertex: test.graph.getAllVertices()) {
//			System.out.println(vertex + " numEdges"
//					+ ": " + test.graph.numEdges(vertex));
//		}
	}
	@Test
	public void test03createGraph() throws Exception{
		CourseSchedulerUtil<String> test = new CourseSchedulerUtil<String>();
		Entity[] test2 = test.createEntity("valid.json");
		test.constructGraph(test2);
	}
	
	@Test 
	public void test04hasCylce() throws Exception{
		CourseSchedulerUtil<String> test = new CourseSchedulerUtil<String>();
		Entity[] test2 = test.createEntity("valid.json");
		test.constructGraph(test2);
		if(test.canCoursesBeCompleted()) {
			System.out.println("no cycle");
		}
		else System.out.println("cycle");
		
		
		
	}
	
	}
	
	
	

