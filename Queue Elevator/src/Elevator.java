/**
 * The <code>Elevator</code> has the current floor and the command.
 * It will go to the commands source floor to pick up the passenger
 * and drop them off at the destination.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #3 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 1st, 2017
 */
public class Elevator {

	private int elevatorState;
	private int currentFloor;
	private Request request;

	private static final int IDLE = 0;
	// Aynoor Saleem said it is fine to not use it as long as my code works.
	// They are declared but not used at all within my program.
	private static final int TO_SOURCE = 1;
	private static final int TO_DESTINATION = 2;

	public Elevator(){
		this.request = null;
		this.currentFloor = 1;
		this.elevatorState = IDLE;
	}

	// Accessors
	public int getCurrentFloor(){
		return this.currentFloor;
	}
	public int getElevatorState(){
		return this.elevatorState;
	}
	public Request getRequest(){
		return this.request;
	}

	// Mutators
	public void setCurrentFloor(int currentFloor){
		this.currentFloor = currentFloor;
	}
	public void setRequest(Request request){
		this.request = request;
	}

	public void setElevatorState(int e){
		this.elevatorState = e;
	}

	// currentFloor, where it going? , (from,to,time entered)
	public String toString(){
		String output = "[Floor " + currentFloor + ", ";

		if(elevatorState == 0)
			output += "IDLE, ";
		else if(elevatorState == 1)
			output += "TO_SOURCE, ";
		else if(elevatorState == 2)
			output +=  "TO_DESTINATION, ";

		if(request == null)
			output += "---";
		else
			output += request;

		return (output+ "]");
	}
}