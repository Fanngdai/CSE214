/**
 * The <code>InvalidLengthException</code> is called when the users input is
 * not formatted correctly.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #2 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 25th, 2017
 */
public class InvalidLengthException extends Exception{
	/**
	 * Error when the originalLocation input is not the correct length.
	 *
	 * @param word
	 * 	The string we are checking
	 */
	public void originalLocationError(String word){
		if(word.length() < 6){
			tooShort();
		}
		else{
			tooLong();
		}
	}
	/**
	 * Error when the rfidTagNumber input is not the correct length.
	 *
	 * @param word
	 * 	The string we are checking
	 */
	public void rfidError(String word){
		if(word.length() < 9){
			tooShort();
		}
		else{
			tooLong();
		}
	}
	/**
	 * A word is too short.
	 */
	public void tooShort(){
		System.out.println("Length too short.");
	}
	/**
	 * A word is too long
	 */
	public void tooLong(){
		System.out.println("Length too long.");
	}
	/**
	 * User did not input anything or string is invalid.
	 */
	public void invalidString(){
		System.out.println("Invalid String.");
	}
}
