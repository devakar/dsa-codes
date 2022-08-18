import java.util.*;

public class Test2 extends Thread {
	public void run() {
		try{
			System.out.println("Sleeping in test2");
			Thread.sleep(20000);
			System.out.println("Exiting from test2");
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}