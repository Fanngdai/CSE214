/**
 * The <code>Analyzer</code> prompts the user for the probability, floors, elevators and length of simulation.
 * It will then, calculate randomly with its entered data.
 *
 * total Wait Time and total Request will not add up until the person is dropped off.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #3 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 1st, 2017
 */

import java.util.Scanner;

public class Analyzer {
	Scanner stdin = new Scanner(System.in);
	private double prob;
	private int numFloor, numElevator, lengthSimulate;
	Simulator simulator = new Simulator();

	/**
	 * Prompt user for information and call the simulate method that will do everything else.
	 */
	public void promptUser(){
		System.out.println("Welcome to the Elevator simulator!\n");
		System.out.print("Please enter the probability of arrival for Requests: ");
		prob = stdin.nextDouble();
		System.out.print("Please enter the number of floors: ");
		numFloor = stdin.nextInt();
		System.out.print("Please enter the number of elevators: ");
		numElevator = stdin.nextInt();
		System.out.print("Please enter the length of the simulation (in time units): ");
		lengthSimulate = stdin.nextInt();
		simulator.simulate(prob, numFloor, numElevator, lengthSimulate);
	}

	public static void main(String[] args){
		Analyzer action = new Analyzer();
		// Ask user for input
		action.promptUser();
	}
}
