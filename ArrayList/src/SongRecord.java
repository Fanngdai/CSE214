/**
 * The <code>SongRecord</code> contains information about a particular audio file.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #1 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 16th, 2017
 */
public class SongRecord {
	private String title; 	// The title of the song
	private String artist;	// The artist of the song
	private int minutes;	// The length of the song in minutes
	private int seconds;	// The length of the song in seconds
	// Checks to make sure if such SongRecord is allowed. (All variables passed in are correct)
	private boolean valid = false;

	/**
	 * Construct an instance of the <code>SongRecord</code> with default values.
	 */
	public SongRecord(){

	}
	/**
	 * Constructor which sets the title, artist, min and sec of an audio file
	 * to what is passed in.
	 *
	 * @param t
	 * 	The title of the song
	 * @param a
	 * 	The artist of the song
	 * @param m
	 * 	The length of the song in minutes
	 * @param s
	 * 	The length of the song in seconds
	 */
	public SongRecord(String t, String a, int m, int s)
	{
		// Check if min or sec is negative or if sec is over 59.
		try{
			// Sets title and artist to what is passed in.
			this.title = t;
			this.artist = a;
			// If the minute that is passed in is neg, reject.
			if(m<0)
				throw new IllegalArgumentException();
			// Otherwise, minutes is equal to what is passed in
			else
				this.minutes = m;
			// If sec is neg or over 59, reject. (Seconds are between 0 and 60)
			if(s<0 || s>59)
				throw new IllegalArgumentException();
			// Otherwise, seconds is equal to what is passed in.
			else
				this.seconds = s;
			// If you get to this part of the code, min & sec are both valid.
			// In this case, the song is valid.
			valid = true;
		}
		catch(IllegalArgumentException e){
			// If min or sec is not valid, promt user. Valid is false meaning
			// the information given cannot form an SongRecord object.
			System.out.println("Invalid song length.");
		}
	}

	/**
	 * Returns the title of the song.
	 *
	 * @return
	 * 	Returns the title of the song.
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 * Returns the artist of the song.
	 *
	 * @return
	 * 	Returns the artist of the song.
	 */
	public String getArtist(){
		return this.artist;
	}
	/**
	 * Returns the length in minutes of the song.
	 *
	 * @return
	 * 	Returns the length in minutes of the song.
	 */
	public int getMinutes(){
		return this.minutes;
	}
	/**
	 * Returns the length in seconds of the song.
	 *
	 * @return
	 * 	Returns the length in seconds of the song.
	 */
	public int getSeconds(){
		return this.seconds;
	}
	/**
	 * Returns if the song is valid with the passed in parameters.
	 *
	 * @return
	 * 	true if the song is valid with the passed in parameters.
	 * 	false otherwise.
	 */
	public boolean getValid(){
		return this.valid;
	}

	/**
	 * Sets the song title
	 *
	 * @param title
	 * 	The title of the song.
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 * Sets the song artist
	 *
	 * @param artist
	 * 	The artist of the song.
	 */
	public void setArtist(String artist){
		this.artist = artist;
	}
	/**
	 * Sets the length in minutes of the song
	 *
	 * <dt><b>Precondition:</b><dd>
	 * 	The passed in value, min, cannot be negative.
	 *
	 * @param min
	 * 	The length of the song in minutes
	 */
	public void setMinutes(int min){
		try{
			// If minutes is negative, it is not valid and we should reject
			if(min<0)
				throw new IllegalArgumentException();
			// Otherwise, it is valid and minutes is equal to what is passed in
			else
				this.minutes = min;
		}
		catch(IllegalArgumentException e){
			// Promt user that min (passed in value) is invalid.
			System.out.println("Invalid song length.");
		}
	}
	/**
	 * Sets the length in seconds of the song
	 *
	 * <dt><b>Precondition:</b><dd>
	 * 	The passed in value, sec, cannot be negative or larger than 59.
	 *
	 * @param min
	 * 	The length of the song in seconds
	 */
	public void setSeconds(int sec){
		try{
			// If seconds is less than 0 or greater than 59 it is not valid and
			// we should reject
			if(sec<0 || sec>59)
				throw new IllegalArgumentException();
			// Otherwise, it is valid and seconds is equal to what is passed in
			else
				this.seconds = sec;
		}
		catch(IllegalArgumentException e){
			// Promt user that sec (passed in value) is invalid.
			System.out.println("Invalid song length.");
		}
	}

	/**
	 * Compares this SOngRecord to another object for equality.
	 *
	 * @param obj
	 * 	An object in which this <code>SongRecord</code> is compare to.
	 *
	 * @return
	 * 	Returns true if the obj refers to a <code>SongRecord</code> object with
	 * 	the same title, artist, minutes, and seconds. False otherwise.
	 */
	public boolean equals(Object obj){
		if(obj == null || !(obj instanceof SongRecord))
			return false;
		SongRecord check = (SongRecord) obj;
		return(this.title.equals(check.getTitle()) && this.artist.equals(check.getArtist())
				&& this.minutes==check.getMinutes() && this.seconds==check.getSeconds());
	}
	/**
	 * Gets the String represented of this <code>SongRecord</code> object, which
	 * is a neatly formatted single line on the information about the audio file.
	 *
	 * @return
	 * 	Returns the string Representation of this <code>SongRecord</code> object.
	 */
	public String toString(){
		String output = this.title + "\t";
		if(title.length() < 8){
			output += "\t";
		}
		output += this.artist + "\t";
		if(artist.length() < 8){
			output += "\t";
		}
		output += this.minutes + ":";
		if(seconds < 10){
			output += "0" + this.seconds;
		}
		else{
			output += this.seconds;
		}
		return output;
	}
}
