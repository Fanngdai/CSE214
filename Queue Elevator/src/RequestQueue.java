/**
 * The <code>RequestQueue</code> class adds/removes a node (Request) to/from
 * the queue.
 * 
 * Prof said we do not need isEmpty and size.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #3 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 1st, 2017
 */
import java.util.Vector;

public class RequestQueue extends Vector <Request>{

	public RequestQueue(){
	}

	/**
	 * Adds a <code>Request</code> object to the end of the vector.
	 * 
	 * @param item
	 * 	Object to be added in to the vector.
	 */
	public void enqueue(Request item){
		add(item);
	}

	/**
	 * Removes the first object in the queue.
	 * Queue being empty has already been checked in simulator class but just
	 * to make sure, leaving it.
	 * 
	 * @return
	 * 	The object removed from the queue.
	 */
	public Request dequeue(){
		Request answer = null;
		try{
			if(firstElement() == null)
				throw new QueueException();
			answer = firstElement();
			removeElementAt(0);
		}
		catch(QueueException ex){
			ex.emptyQueueException();
		}
		return answer;
	}
	
	// Another method that can be used only if the queue is checked to make sure it is not empty.
//	public Request dequeue(){
//		Request answer = firstElement();
//			removeElementAt(0);
//		return answer;
//	}
}