/**
 * The <code>BooleanSource</code> randomly returns true or false according the
 * probability the user entered.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #3 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 1st, 2017
 */
public class BooleanSource {
	private double probability;

	// Check to make sure the probability is valid.
	public BooleanSource(double p){
		try{
			if(p < 0.0 || p > 1.0)
				throw new IllegalArgumentException();
			this.probability = p;
		}
		catch(IllegalArgumentException ex){
			System.out.println("Invalid Probability.");
		}
	}
	
	// Returns true or false according to the probability.
	public boolean requestArrived(){
		return (Math.random() < probability);
	}
}