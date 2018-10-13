/*
 * Fanng Dai
 * SBUID
 * Fanng.dai@stonybrook.edu
 * CSE214 Rec1
 * Homework #1
 * Extra Credit
 *
 * Contains information about a particular audio file.
 */

public class SongRecord{
	private String title;
	private String artist;
	private int minutes;
	private int seconds;
	private boolean valid = false;

	public SongRecord(){

	}
	public SongRecord(String t, String a, int m, int s)
	{
		try{
			this.title = t;
			this.artist = a;
			if(m<0)
				throw new IllegalArgumentException();
			else
				this.minutes = m;
			if(s<0 || s>59)
				throw new IllegalArgumentException();
			else
				this.seconds = s;
			valid = true;
		}
		catch(IllegalArgumentException e){
			System.out.println("Invalid song length");
		}
	}

	// Accessor
	public String getTitle(){
		return this.title;
	}
	public String getArtist(){
		return this.artist;
	}
	public int getMinutes(){
		return this.minutes;
	}
	public int getSeconds(){
		return this.seconds;
	}
	public boolean getValid(){
		return this.valid;
	}

	// Mutators
	public void setTitle(String title){
		this.title = title;
	}
	public void setArtist(String artist){
		this.artist = artist;
	}
	public void setMinutes(int min){
		try{
			if(min<0)
				throw new IllegalArgumentException();
			else
				this.minutes = min;
		}
		catch(IllegalArgumentException e){
			System.out.println("Invalid song length.");
		}
	}
	public void setSeconds(int sec){
		try{
			if(sec<0 || sec>59)
				throw new IllegalArgumentException();
			else
				this.seconds = sec;
		}
		catch(IllegalArgumentException e){
			System.out.println("Invalid song length.");
		}
	}

	public boolean equals(Object obj){
		if(obj == null || !(obj instanceof SongRecord))
			return false;
		SongRecord check = (SongRecord) obj;
		return(this.title.equalsIgnoreCase(check.getTitle()) && this.artist.equalsIgnoreCase(check.getArtist())
				&& this.minutes==check.getMinutes() && this.seconds==check.getSeconds());
	}
	public String toString(){
		// For PlaylistOperation (NOT GUI)
		String timeLength = this.minutes + ":";
		if(seconds < 10){
			timeLength += "0" + this.seconds;
		}
		else{
			timeLength += this.seconds;
		}

		String output = String.format("%-16s%-16s%-6s", this.title, this.artist, timeLength);
		return output;
	}
}
