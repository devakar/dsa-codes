import java.util.*;
import java.util.concurrent.*;

public class ThreadTest {
	public static void main(String[] args) throws InterruptedException{
		
		// try {
		// 	Thread t1 = new Thread(()->{
		// 		System.out.println("Sleeping");
				
		// 	});
		// 	t1.start();
		// 	t1.join();
		// 	System.out.println("Exiting");
		// } catch(Exception e) {
		// 	System.out.println(e);
		// } 

		// Thread t1 = new Thread(new Test());
		// t1.start();
		// t1.join();
		Test test = new Test();
		Thread t1 = new Thread(test);
		t1.start();

		// t1.join();
		Test2 test2 = new Test2();
		Thread t2 = new Thread(test2);
		t2.start();

		t1.join();
		t2.join();

 
		ExecutorService executor = Executors.newFixedThreadPool(5);
		executor.submit(new Test());
		executor.submit(new Test());
		executor.submit(new Test());
		executor.submit(new Test());
		executor.submit(new Test());
		executor.submit(new Test());
		executor.shutdown();
		System.out.println("Exiting fom main");
	}
	
}

