package thread;

import java.util.concurrent.*;

class Task implements Runnable
{
	Task(int n)
	{
		this.number = n;
	}
	int number;
	public void run()
	{
		System.out.println("This is task:"+this.number);
	}
}

public class Pool {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Runnable [] tasks = new Task[10];
		
		ExecutorService service = Executors.newFixedThreadPool(5);

		
		for (int i = 0; i < 10; i++) 
        {  
          Runnable worker = new Task(i);  
          service.submit(worker);
        } 

     service.shutdown();
     
     while (!service.isTerminated()) 
     { 
    	// System.out.println("Waiting for threads to terminate");
     } 
     
     System.out.println("Threads have terminated");
     
     
     
	}

}
