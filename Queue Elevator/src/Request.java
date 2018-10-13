/**
 * The <code>Request</code> sets up the source floor, destination floor and the
 * time the request was placed.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #3 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 1st, 2017
 */
public class Request {
	// The location to pick up the passenger
	private int sourceFloor;
	// The location the passenger wants to go to
	private int destinationFloor;
	// The time that this request was placed on the queue
	private int timeEntered;

	/**
	 * Constructor which sets the source destination and destination floor to a
	 * random number between 0 and the parameter numFloor.
	 *
	 * @param numFloor
	 * 	The amount of floors in this building
	 */
	public Request(int numFloor){
		sourceFloor = (int)(Math.random() * numFloor + 1);
		destinationFloor = (int)(Math.random() * numFloor + 1);
	}

	// Mutators
	public void setSourceFloor(int sourceFloor){
		this.sourceFloor = sourceFloor;
	}
	public void setDestinationFloor(int destinationFloor){
		this.destinationFloor = destinationFloor;
	}
	public void setTimeEntered(int timeEntered){
		this.timeEntered = timeEntered;
	}

	// Accessors
	public int getSourceFloor(){
		return this.sourceFloor;
	}
	public int getDestinationFloor(){
		return this.destinationFloor;
	}
	public int getTimeEntered(){
		return this.timeEntered;
	}

	public String toString(){
		return "(" + sourceFloor + ", " + destinationFloor + ", " + timeEntered + ")";
	}
}
