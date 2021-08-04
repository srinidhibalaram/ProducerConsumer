package thread;
import java.util.*;

class PC
{
	List<Integer> items;
	int limit;
	String model;
	PC(String s,int n)
	{
		items = new ArrayList<Integer>();
		this.model=s;
		this.limit=n;
		
		
	}
	public void produce()
	{
		int i=0;
		
		while(true)
		{
			synchronized(this)
			{
		
			try{
				while(this.items.size()==this.limit)
				{
				this.wait();
				System.out.println("Producer is waiting!!!!!!");
				}
				
				items.add(this.items.size());
				System.out.println(items.size()-1+" produced");
			    this.notify();	
				//Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				
			}
			
		}
			
			
		
		}
	}
	
	public void consume()
	{
		int i=0;
		
		while(true)
		{i++;
		synchronized(this)
		{
		
			try{
				while(this.items.size()==0)
			{
				this.wait();
				System.out.println("Consumer is waiting!!!!!!!!");
			}
				
				items.remove(this.items.size()-1);
				System.out.println(this.items.size() +" consumed"); 
				
				this.notify();
				//Thread.sleep(1000);
				
			}
			catch(InterruptedException e)
			{
				
			}
			
		}
			
			
		}
	}

}
public class ProducerConsumer {
	public static void main(String []s)
	{
		PC pc = new PC("one",5);
		
		
		{
		Thread t1 = new Thread(()->{pc.produce();});
		Thread t2 = new Thread(()->{pc.consume();});
		t1.start();
		t2.start();
		
		//t1.join();
		//t2.join();
		}
		/*catch(InterruptedException e)
		{
			
		}*/
		
		
		
	}

}
