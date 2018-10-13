/*
 * Fanng Dai
 * sbuid
 * Fanng.dai@stonybrook.edu
 * CSE214 Rec1
 * Homework #1
 * Extra Credit
 *
 * If the playlist is full this class is called which will let the user know no more songRecords
 * can be stored in the playlist.
 */

public class FullPlaylistException extends Exception {
	public FullPlaylistException(){
		super();
	}
	public FullPlaylistException(String message){
		super(message);
	}
}
