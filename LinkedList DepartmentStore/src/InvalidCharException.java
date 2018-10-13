/**
 * The <code>InvalidCharException</code> is called when a character does not
 * belong at a certain index of a String.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #2 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 25th, 2017
 */
public class InvalidCharException extends Exception{
	/**
	 * If a char is supposed to be a hexidecimal but it is not
	 */
	public void hexError(){
		System.out.println("Not hexadecimal formatted.");
	}
	/**
	 * If a char is supposed to be a digit but it is not
	 */
	public void digitError(){
		System.out.println("Wrong format.");
	}
}
