package test;

import static org.junit.Assert.*;

import org.junit.Test;
import thread.BlockingQueue;

public class BlockingQueueTest {

	
	@Test
	public void testAddBasic() {
		
		try
		{
			BlockingQueue queue = new BlockingQueue(5);
		Thread t1 = new Thread(()->{queue.add(3);});
		t1.start();
		t1.join();
		assertEquals(queue.size(), 3);
		
		}
		catch(InterruptedException ie)
		{
			
		}
		
		
	}
	
	@Test
	public void testAddOverflow() {
		
		try
		{
			BlockingQueue queue = new BlockingQueue(5);
		Thread t1 = new Thread(()->{queue.add(6);});
		int size;
		t1.start();
		
		
		Thread.sleep(1000);
		assertTrue(t1.getState().equals(Thread.State.WAITING));
		
		t1.interrupt();
		
		assertEquals(queue.size(), 5);
		}
		catch(InterruptedException ie)
		{
			
		}
		
	}
	
	@Test
	public void testRemoveBasic() {
		
		try
		{
			BlockingQueue queue = new BlockingQueue(5);
		Thread t1 = new Thread(()->{queue.add(4);}); //add is already tested
		t1.start();
		t1.join();
		Thread t2 = new Thread(()->{queue.remove(2);});
		t2.start();
		t2.join();
		assertEquals(queue.size(), 2);
		
		}
		catch(InterruptedException ie)
		{
			
		}
		
		
	}
	
	@Test
	public void testRemoveUnderflow() {
		
		try
		{
			BlockingQueue queue = new BlockingQueue(5);
		Thread t1 = new Thread(()->{queue.add(5);}); //add is already tested
		t1.start();
		t1.join();
		Thread t2 = new Thread(()->{queue.remove(8);});
		t2.start();
		Thread.sleep(1000);
		assertTrue(t2.getState().equals(Thread.State.WAITING));
		
		t2.interrupt();
		
		assertEquals(queue.size(), 0);
		
		}
		catch(InterruptedException ie)
		{
			
		}
		
		
	}
	
	@Test
	public void testAddRemoveOverflowHandling() {
		
		try
		{
			BlockingQueue queue = new BlockingQueue(5);
		Thread t1 = new Thread(()->{queue.add(8);}); 
		
		Thread t2 = new Thread(()->{queue.remove(5);});
		t1.start();
		t2.start();
	
		t1.join();
		t2.join();
		
		assertEquals(queue.size(), 3);
		
		}
		catch(InterruptedException ie)
		{
			
		}
		
		
	}
	
	@Test
	public void testAddRemoveUnderflowHandling() {
		
		try
		{
			BlockingQueue queue = new BlockingQueue(5);
		Thread t1 = new Thread(()->{queue.remove(4);}); 
		
		Thread t2 = new Thread(()->{queue.add(5);});
		t1.start();
		t2.start();
	
		t1.join();
		t2.join();
		
		assertEquals(queue.size(), 1);
		
		}
		catch(InterruptedException ie)
		{
			
		}
		
		
	}
	
	@Test
	public void testBrowseBasic() {
		
		try
		{
			BlockingQueue queue = new BlockingQueue(10);
		
		
		Thread t1 = new Thread(()->{queue.add(4);});
		t1.start();
		
	
		t1.join();
		
		Thread t2 = new Thread(()->{queue.browse();});
		t2.start();
		assertTrue(true);
		
		}
		catch(InterruptedException ie)
		{
			assertTrue(false);
		}
			
	}
	
	
	
	
	
	
	
	

	

}
