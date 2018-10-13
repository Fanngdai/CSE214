/*
 * Fanng Dai
 * 109684495
 * Fanng.dai@stonybrook.edu
 * CSE214 Rec1
 * Homework #1
 * Extra Credit
 * 
 * Stores all SongRecord objects that belong to a particular playlist.
 * position refers to the position in the PLaylist and not the position inside the array.
 * Position starts at 1 while array starts at 0. array + 1 = position.
 */

public class Playlist implements Cloneable{
	private String playlistName;
	// There is a max of 50 song records allowed in each playlist.
	private static final int CAPACITY = 50;
	private SongRecord[] songRecord;
	// counter for how many songRecords are in the songRecord array
	private int manySongRecord;

	public Playlist(){
		playlistName = "No name";
		manySongRecord = 0;
		songRecord = new SongRecord[manySongRecord];
	}
	public Playlist(String name){
		playlistName = name;
		manySongRecord = 0;
		songRecord = new SongRecord[manySongRecord];
	}
	// Accessors
	public SongRecord[] getSongRecord(){
		return this.songRecord;
	}
	public String getPlaylistName(){
		return this.playlistName;
	}
	public int getCapacity(){
		return this.CAPACITY;
	}
	public int size(){
		return this.manySongRecord;
	}
	
	public void setPlaylistName(String name){
		this.playlistName = name;
	}
	public void setSongRecord(SongRecord[] songRecord){
		this.songRecord = songRecord;
	}

	// Add song to that position and shift every song at that position and after one space back
	public void addSong(SongRecord song, int position) throws FullPlaylistException{
		try{			
			if(manySongRecord == CAPACITY)
				throw new FullPlaylistException();
			else if(position > manySongRecord+1 || position <= 0){
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
	
	// Get song at position
	public SongRecord getSong(int position){
		try{
			if(position <= 0 || position > manySongRecord)
				throw new IllegalArgumentException();
			else
				return this.songRecord[position-1];
		}
		catch(IllegalArgumentException e){
			System.out.println("Invalid position.");
		}
		return null;
	}

	// Returns a playlist with all songs by that artist
	public static Playlist getSongByArtist(Playlist originalList, String artist) throws FullPlaylistException {
		// The return value is null if either originalList or artist is null
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
	
	public static boolean songByArtist(Playlist originalList, String artist) throws FullPlaylistException {
		for(int i=1; i<=originalList.size(); i++){
			if(artist.equalsIgnoreCase(originalList.getSong(i).getArtist()))
				return true;
		}
		return false;
	}
	// Prints all songs
	public void printAllSongs(){
		
		System.out.println(String.format("%5s%10s%17s%16s", "Song#", "Title", "Artist", "Length"));
		System.out.println("------------------------------------------------");
		for(int i=0; i<manySongRecord; i++){
			System.out.print(String.format("%-10s", (i+1)));
			System.out.println(songRecord[i].toString());
		}
	}

	// Copies Playlist and returns it. (Not referenced to the same playlist)
	public Object clone(){ 
		Playlist answer = new Playlist();
		try {
			answer = (Playlist)super.clone();
		}
		catch (CloneNotSupportedException e) {
			System.out.println("Not Cloneable.");
		}
		// Copies each SongRecord individually to the songRecord array
		answer.songRecord = (SongRecord[]) songRecord.clone();
		answer.setPlaylistName("No name");
		return answer;
	}
	// Compare this playlist to another object for equality with the same order, size, title, artist, and length(time)
	public boolean equals(Object obj){
		// if the object is not empty or it is a playlist object
		if(obj != null && obj instanceof Playlist){
			// Set obj to a playlist and extract the songRecords within obj
			Playlist o = (Playlist) obj;
			// The amount of songs are different
			if(o.size() != this.manySongRecord)
				return false;
			// The orders must match array1[1,2,3] is not the same as array2[2,3,1]
			for(int i=0; i<this.manySongRecord; i++){
				if(!this.songRecord[i].equals(o.songRecord[i]))
					return false;
			}
			return true;
		}
		else 
			return false;
	}
	public String toString(){
		return (playlistName + "\n" + super.toString());
	}
}