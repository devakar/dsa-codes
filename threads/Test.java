import java.util.*;

public class Test implements Runnable {
	public void run() {
		try{
			// gets the name of current thread
	        System.out.println("Current Thread Name: "+ Thread.currentThread().getName());
	        
	        // gets the ID of the current thread
	        System.out.println("Current Thread ID: "+ Thread.currentThread().getId());
	        
			System.out.println("Sleeping in test");
			Thread.sleep(20000);
			System.out.println("Exiting from test");
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}