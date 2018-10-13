/**
 * An exception thrown in the <CODE>Playlist</CODE> class to indicate that the
 * attribute array has reached its max capacity.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #1 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 16th, 2017
 */

public class FullPlaylistException extends Exception {
	/**
	 * Default constructor for an <CODE>FullPlaylistException</CODE> that
	 * passes a default string to the <CODE>Exception</CODE> class constructor.
	 *
	 * <dt><b>Postcondition:</b><dd>
	 *    The object is created and contains the default message.
	 */
	public FullPlaylistException(){
		super("Playlist Full.");
	}

	/**
	 * Second constructor for the <CODE>FullplaylistException</CODE> that
	 * passes a provided string to the <CODE>Exception</CODE> class constructor.
	 *
	 * @param message
	 *    the message that the object is to contain
	 *    
	 * <dt><b>Postcondition:</b><dd>
	 *    The object is created and contains the provided message.
	 */
	public FullPlaylistException(String message){
		// Passed message
		super(message);
	}
}