/**
 * The <code>QueueException</code> lets the user know when the queue is empty.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #3 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 1st, 2017
 */
public class QueueException extends Exception{
	public QueueException(){
		
	}
	public void emptyQueueException(){
		System.out.println("Empty Queue");
	}
}