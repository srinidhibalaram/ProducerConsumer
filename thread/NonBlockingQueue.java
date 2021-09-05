package thread;

import java.util.concurrent.atomic.*;

class Node
{
	int val;
	volatile Node next;
	Node(int x)
	{
		this.val=x;
		next=null;
		
	}
}
public class NonBlockingQueue {
	
	AtomicReference<Node> front,rear;
	int limit;
	static int count=0;
	AtomicInteger size;
	AtomicBoolean lock;
	NonBlockingQueue(int x)
	{
		front = new AtomicReference<Node>(null);
		rear = new AtomicReference<Node>(null);
		limit=x;
		size=new AtomicInteger(0);
		lock = new AtomicBoolean(false);
	}
	
	void produce(int n)
	{
		
		for(int i=0;i<n;i++)
		{
		while(size.get()==this.limit)
		{
			System.out.println("Producer is waiting");
		}
		
		while(!lock.compareAndSet(false, true))
		{
			
		}
		
		count++;
		Node node = new Node(count);
		
		if(size.get()==0)
		{
			front.set(node);
			rear.set(node);
		}
		else
		{
			Node prevRear = rear.get();
			rear.set(node);
			prevRear.next = node; 
			
		}
		size.incrementAndGet();
		System.out.println(count+" is produced");
		lock.set(false);
		
		}
	}
	
	public void consume(int n)
	{
		for(int i=0;i<n;i++)
		{
		while(size.get()==0)
		{
			System.out.println("Consumer is waiting");
		}
		
		while(!lock.compareAndSet(false, true))
		{
			
		}
		
		
		Node prevFront = front.get();
		if(size.get()==1)
		{
			front.set(null);
			rear.set(null);
			
		}
		else	
			front.set(prevFront.next);
			
		
		size.decrementAndGet();
		System.out.println(prevFront.val + " is consumed");
		lock.set(false);
		}
		
	}
	
	public void traverse(int n)
	{
		for(int i=0;i<n;i++)
		{
		while(front.get()==null)
		{
			System.out.println("Queue is empty!!!");
		}
		
		AtomicReference<Node> temp=new AtomicReference<Node>(front.get());
		
		while(temp.get()!=null)
		{
			System.out.println(temp.get().val+" ");
			temp.set(temp.get().next);
		}
		}
		
	}

}
