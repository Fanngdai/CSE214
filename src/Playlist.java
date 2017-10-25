/**
 * The <code>Playlist</code> class implements <code>Cloneable</code>
 * which makes a clone of the passed in object.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #1 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 16th, 2017
 */

public class Playlist implements Cloneable{
	// An array holding SongRecord objects.
	private SongRecord[] songRecord;
	// The number of items currently in the Playlist
	private int manySongRecord;
	private static final int CAPACITY = 50;

	/**
	 * Construct an instance of the <code>Playlist</code> class with no 
	 * SongRecords objects in it.
	 *
	 * <dt><d>Postcondition:</b><dd>
	 * 	This <code>Playlist</code> has been initialized to an empty list of 
	 * SongRecords.
	 **/
	public Playlist(){
		manySongRecord = 0;
		songRecord = new SongRecord[manySongRecord];
	}

	/**
	 * Returns the songRecords array in the <code>Playlist</code>.
	 * 
	 * @return
	 * 	Returns the songRecord array.
	 */
	public SongRecord[] getSongRecord(){
		return this.songRecord;
	}
	/**
	 * Determines the number of SongRecords currently in this <code>Playlist</code>.
	 * 
	 * <dt><b>Precondition:</b><dd>
	 * 	This <code>SongRecord</code> object has been instantiated.
	 * 
	 * @return
	 * 	Returns the number of SongRecords in this <code>Playlist</code>.
	 */
	public int size(){
		return this.manySongRecord;
	}

	/**
	 * Checks if songRecord array has reached capacity.
	 * 
	 * @param position
	 * 	The index which is compared to check if it is valid to insert an 
	 * element.
	 * 
	 * @return
	 * 	True if position is valid to insert element. False otherwise.
	 */
	public boolean validPositionAdd(int position){
		if(position <= 0 || position > manySongRecord+1){
			return false;
		}
		else
			return true;
	}
	/**
	 * Inserts a new <code>SongRecord</code> into the array.
	 *
	 * @param song
	 * 	The new <code>SongRecord</code> object to add to this <code>Playlist</code>.
	 * @param position
	 * 	The position in the playlist where the <code>SongRecord</code> objects 
	 * in this <code>Playlist</code> is less than capacity.
	 *
	 * <dt><b>Precondition:</b><dd>
	 *   This <code>SongRecord</code> object has been instantiated and 
	 *   1<= position <= manySongRecord + 1. The number of <code>SongRecord</code>
	 *   objects in this <code>Playlist</code> is less than CAPACITY.
	 *   
	 * <dt><b>Postcondition:</b><dd>
	 * 	The new song is now stored at the desired position in the 
	 * 	<code>Playlist</code>. All SongRecords that were originally in position
	 * 	greater than or equal ot positopn are moved back one position.
	 * 
	 * @throws <FullPlaylistException>
	 * 	Indicates that songRecord array is full.
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates that <code>position</code> is not less than capacity.
	 **/
	public void addSong(SongRecord song, int position) throws FullPlaylistException{
		try{			
			if(manySongRecord == CAPACITY)
				throw new FullPlaylistException();
			else if(!validPositionAdd(position)){
				throw new IllegalArgumentException();
			}
			else{	
				this.manySongRecord++;
				SongRecord[] biggerArray = new SongRecord[manySongRecord];

				System.arraycopy(songRecord, 0, biggerArray, 0, position-1);
				System.arraycopy(songRecord, position-1, biggerArray, position, manySongRecord - position);
				biggerArray[position-1] = song;

				songRecord = biggerArray;
			}
		}
		catch(FullPlaylistException e){
			System.out.println("Playlist Full.");
		}
		catch(IllegalArgumentException e){
			System.out.println("Invalid position for adding the new song.");
		}
	}

	/**
	 * Removes a <code>SongRecord</code> from the array.
	 * 
	 * @param position
	 * 	The position in the playlist where the song will be removed
	 * 
	 * <dt><b>Precondition:</b><dd>
	 * 	This <code>SongRecord</code> object has been instanted and 1 <= position
	 * 	<= manySongRecord.
	 * 
	 * <dt><b>Postcondition:</b><dd>
	 *  The <code>SongRecord</code> at the desired position in the <code>Playlist</code>
	 *  has been removed. All SongRecords that were originally in positions 
	 *  greater or equal to position are moved forward one position.
	 *  
	 * @exception IllegalArgumentException
	 * 	Indicates that position is not less than capacity.
	 */
	public void removeSong(int position){
		try{
			if(position <= 0 || position > manySongRecord){
				throw new IllegalArgumentException();
			}
			else{
				this.manySongRecord--;
				SongRecord[] trimmedArray = new SongRecord[manySongRecord];
				System.arraycopy(songRecord, 0, trimmedArray, 0, position-1);
				System.arraycopy(songRecord, position, trimmedArray, position-1, manySongRecord-position+1);
				songRecord = trimmedArray;
				System.out.println("Song Removed at position " + position);
			}
		}
		catch(IllegalArgumentException e){
			System.out.println("No song at position " + position + " to remove.");
		}
	}

	/**
	 * Get the <code>SongRecord</code> at the given position in this 
	 * <code>Playlist</code> object.
	 * 
	 * @param position
	 * 	position of the <code>SongRecord</code> to retrieve
	 * 
	 * <dt><b>Precondition:</b><dd>
	 * 	This <code>Playlist</code> object has been instantiated and 1 <= position <=
	 *	manySongRecords.
	 * 
	 * @return
	 * 	The <code>SongRecord</code> at the specified position in this 
	 * <code>Playlist</code> object.
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates that position is not within the valid range.
	 */
	public SongRecord getSong(int position){
		try{
			if(position <= 0 || position > manySongRecord)
				throw new IllegalArgumentException();
			else
				return this.songRecord[position-1];
		}
		catch(IllegalArgumentException e){
			System.out.println("No Song at position " + position + ".");
		}
		return null;
	}

	/**
	 * Generates a new <code>Playlist</code> containing all SongRecords in 
	 * the original <code>Playlist</code> performed by the specified artist.
	 * 
	 * @param originalList
	 * 	the original <code>Playlist</code>
	 * 
	 * @param artist
	 * 	the name of the artist
	 * 
	 * <dt><d>Precondition:<//b><dd>
	 * 	The <code>Playlist</code> referred to by originalList has been instantiated.
	 * 
	 * @return
	 * 	A new <code>Playlist</code> object containing all SongRecords in the original 
	 * 	<code>Playlist</code> performed by the specified artist.
	 * 
	 * @throws FullPlaylistException
	 * 	Passed in through addSong method stating that there are over the capacity
	 * 	of SongRecords in array.
	 */
	public static Playlist getSongByArtist(Playlist originalList, String artist) throws FullPlaylistException {
		if(originalList == null || artist == null){
			return null;
		}
		Playlist answer = new Playlist();
		int count = 1;
		for(int i=1; i<=originalList.size(); i++){
			if(artist.equalsIgnoreCase(originalList.getSong(i).getArtist())){
				answer.addSong(originalList.getSong(i), count++);
			}
		}
		return answer;
	}
	/**
	 * Prints a neatly formatted table of each <code>SongRecord</code> in the 
	 * <code>Playlist</code> on its own line with its position number.
	 * 
	 * <dt><b>Precondition:</b><dd>
	 * 	This <code>SongRecord</code> object has been instantiated.
	 * 
	 * <dt><b>Postcondition:</b><dd>
	 * 	A neatly formatted table of each <code>SongRecord</code> in the Playlist 
	 * 	on its own line with its position number has been displayed to the user.
	 */
	public void printAllSongs(){
		System.out.println("Song#\tTitle\t\tArtist\t\tLength");
		System.out.println("------------------------------------------------");
		for(int i=0; i<manySongRecord; i++){
			System.out.println((i+1) + "\t" + songRecord[i].toString());
		}
	}

	/**
	 * Generate a copy of this <code>Playlist</code>.
	 * 
	 * @return
	 * 	The return value is a copy of this <code>Playlist</code>. Subsequent
	 * 	changes to the copy will not affect original, nor vice versa. (It is 
	 * 	not referenced to the same address.) Note that the return value must
	 * 	be typecast to a <code>Playlist</code> before it can be used.
	 */
	public Object clone(){ 
		Playlist answer = new Playlist();
		try {
			answer = (Playlist)super.clone(); 
		}
		catch (CloneNotSupportedException e) {
			System.out.println("Not cloneable.");
		}
		answer.songRecord = (SongRecord[]) songRecord.clone();
		return answer;
	}

	/**
	 * Compares this <code>Playlist</code> to another object for equality.
	 * 
	 * @param obj
	 * 	An object in which this <code>Playlist</code> is compared to.
	 * 
	 * @return
	 * 	Returns true if the obj refers to a <code>Playlist</code> object with
	 * 	the same SongRecords in the same order as this <code>Playlist</code>.
	 * False otherwise.
	 */
	public boolean equals(Object obj){
		if(obj != null && obj instanceof Playlist){
			Playlist o = (Playlist) obj;
			if(o.size() != this.manySongRecord)
				return false;
			for(int i=0; i<this.manySongRecord; i++){
				if(!this.songRecord[i].equals(o.songRecord[i]))
					return false;
			}
			return true;
		}
		else 
			return false;
	}
	/**
	 * Gets the String represented of this <code>Playlist</code> object, which
	 * is a neatly formatted table of each <code>SongRecord</code> in the 
	 * <code>Playlist</code> on its own line.
	 * 
	 * @return
	 * 	Returns the string Representation of this <code>Playlist</code> object.
	 */
	public String toString(){
		return super.toString();
	}
}