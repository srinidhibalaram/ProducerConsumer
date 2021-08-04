package thread;
import java.util.concurrent.locks.*;
import java.util.*;

public class BlockingQueue
{
	ReentrantReadWriteLock readWriteLock;
	Queue<Integer> blockingQueue;
	int capacity;
	public Lock readLock;
	public Lock writeLock;
	Condition full;
	Condition empty;
	String queueContents;
	public BlockingQueue(int n)
	{
		readWriteLock = new ReentrantReadWriteLock(false);
		blockingQueue = new LinkedList<Integer>();
		this.capacity = n;
		readLock = readWriteLock.readLock();
		writeLock = readWriteLock.writeLock();
		full = writeLock.newCondition();
		empty = writeLock.newCondition();
		queueContents = "";
		
	}
	
	public int size()
	{
		return blockingQueue.size();
	}
	
	public void add(int n)
	{
		for(int i=0;i<n;i++)
		{
		writeLock.lock();
		try {
		while(blockingQueue.size()==capacity)
		{
			System.out.println("Producer is Waiting!!!");
			full.await();
			
		}
		int x = blockingQueue.size();
		blockingQueue.add(x);
		System.out.println(x+" produced");
		Thread.sleep(100);
		empty.signalAll();
		}
		catch(InterruptedException ie)
		{
			
		}
		finally
		{
			writeLock.unlock();
		}
		}
	}
	
	
	public void remove(int n)
	{
		for(int i=0;i<n;i++)
		{
		writeLock.lock();
		try {
		while(blockingQueue.size()==0)
		{
			System.out.println("Consumer is Waiting!!!");
			empty.await();
			
		}
		int x = blockingQueue.remove();
		System.out.println(x+" consumed");
		Thread.sleep(100);
		full.signalAll();
		}
		catch(InterruptedException ie)
		{
			
		}
		finally
		{
			writeLock.unlock();
		}
		}
		}
	
	public void browse()
	{
		
		readLock.lock();
		try
		{
		Iterator<Integer> it = blockingQueue.iterator();
		 System.out.println("Browse in action!!");
		while(it.hasNext()){
           
            System.out.print(it.next() + "->");
            
        }
		System.out.print("null\n");
		
		Thread.sleep(100);
		}
		catch(InterruptedException ie)
		{
			
		}
		finally
		{
		readLock.unlock();
		}
		
	}
	
}


	
	

