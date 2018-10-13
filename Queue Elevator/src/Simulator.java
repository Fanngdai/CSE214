/**
 * The <code>Simulator</code> adds in the passenger to the queue, puts them in
 * a elevator if the elevator is empty, thus, removing them from the queue.
 * Taking them to their destination. Calculates the amount of time they waited
 * and the total amout of passengers the elevator picked up.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #3 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 1st, 2017
 */
public class Simulator {	
	/**
	 * A method which adds and removes passengers from a queue, puts them in
	 * the elevator if empty, and calcuates the total amount of time they
	 * waited and the amount of passengers serviced.
	 * 
	 * @param prob
	 * 	The probability a passenger will come.
	 * @param numFloor
	 * 	The total numbers of floor in that building
	 * @param numElevator
	 * 	The total amount of elevators
	 * @param lengthSimulation
	 * 	How many steps does the user want?
	 */
	public static void simulate(double prob, int numFloor, int numElevator, int lengthSimulation){

		// If datas are not correct return
		if(prob<0 || prob>1 || numFloor<2 || numElevator<1 || lengthSimulation<1){
			System.out.println("No Simulation.");
			return;
		}

		// Declare the amt of elevator
		Elevator[] movingMachine = new Elevator[numElevator];
		// Used to store the amount of time waited. Because it is added up if
		// the passenger arrives to their location before the steps are up.
		int[] waitTime = new int[numElevator];
		for(int j=0; j<numElevator; j++){
			movingMachine[j] = new Elevator();
			waitTime[j] = 0;
		}

		// The queue which is the line of people waiting to be serviced
		RequestQueue people = new RequestQueue();
		// The probability
		BooleanSource arrival = new BooleanSource(prob);

		// The total amount of wait. All passengers. And how manny served.
		int totalWaitTime = 0;
		int totalRequest = 0;

		for(int i = 1; i <= lengthSimulation; i++){
			System.out.print("\nStep " + i + ": ");

			// Person arrived? Add to queue.
			if(arrival.requestArrived()){
				Request temp = new Request(numFloor);
				temp.setTimeEntered(i);
				people.enqueue(temp);
				System.out.println("A request arrives from Floor " + temp.getSourceFloor()
				+ " to Floor " + temp.getDestinationFloor());
			}
			else{
				System.out.println("Nothing Arrives");
			}

			// Is someone waiting at the elevator and the elevator is empty? Remove from queue put in elevator.
			for(int j=0; j<numElevator && !people.isEmpty(); j++){
				if(movingMachine[j].getElevatorState()==0){
					Request temp = people.dequeue();
					waitTime[j] = Math.abs(temp.getSourceFloor() - movingMachine[j].getCurrentFloor());
					movingMachine[j].setRequest(temp);
					// Moving to source now.
					movingMachine[j].setElevatorState(1);
				}
			}

			// Moving towards source or destination.
			for(int j=0; j<numElevator; j++){
				Elevator temp = movingMachine[j];
				// Do nothing if elevator does not have a request.
				if(temp.getElevatorState() == 0){
					continue;
				}
				// Is elevator moving towards source? If not at source move closer to source
				else if(temp.getElevatorState() == 1){
					if(temp.getCurrentFloor() < temp.getRequest().getSourceFloor())
						temp.setCurrentFloor(temp.getCurrentFloor()+1);
					// If elevator is higher than source, move down
					else if(temp.getRequest().getSourceFloor() < temp.getCurrentFloor())
						temp.setCurrentFloor(temp.getCurrentFloor()-1);
					// If you picked up person, change to destination.
					if(temp.getCurrentFloor() == temp.getRequest().getSourceFloor())
						temp.setElevatorState(2);
				}
				// If moving towards destination and elevator not there yet.
				else if(temp.getElevatorState() == 2){
					// If elevator is lower than source, move up
					if(temp.getCurrentFloor() < temp.getRequest().getDestinationFloor())
						temp.setCurrentFloor(temp.getCurrentFloor()+1);
					// If elevator is higher than source, move down
					else if(temp.getRequest().getDestinationFloor() < temp.getCurrentFloor())
						temp.setCurrentFloor(temp.getCurrentFloor()-1);
					if(temp.getCurrentFloor() == temp.getRequest().getDestinationFloor()){
						temp.setElevatorState(0);
						temp.setRequest(null);
						totalWaitTime += waitTime[j];
						waitTime[j] = 0;
						totalRequest++;
					}
				}		
			}
			
			// Print at the end of each step to inform user the data as of that step.
			System.out.println("Total Wait Time = " + totalWaitTime
					+ ", Total Requests = " + totalRequest);
			System.out.print("Requests: " + people);

			System.out.print("\nElevators: ");
			for(int k=0; k<numElevator; k++)
				System.out.print(movingMachine[k] + " ");
			System.out.println();
		}

		System.out.println("\nTotal Wait Time: " + totalWaitTime);
		System.out.println("Total Requests: " + totalRequest);
		System.out.print("Average Wait Time: ");
		System.out.println(String.format("%.2f" , (double)(totalWaitTime)/totalRequest));
	}
}