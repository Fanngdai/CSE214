/*
 * Fanng Dai
 * sbuid
 * Fanng.dai@stonybrook.edu
 * CSE214 Rec1
 * Homework #1
 * Extra Credit
 *
 * No capacity to amt of playlist you can make.
 */
public class IPod {
	private Playlist[] playlist=null;
	private int manyPlaylist;
	private static int playlistIndex = 0;

	public IPod(){
		manyPlaylist = 1;
		playlist = new Playlist[manyPlaylist];
		 // The first array which is zero
		playlist[manyPlaylist-1] = new Playlist("Default");
	}
	// Accessors
	// Get playlist at position
	public Playlist getPlaylist(int n){
		return this.playlist[n];
	}
	public Playlist[] getPlaylist(){
		return this.playlist;
	}
	public int size(){
		return this.manyPlaylist;
	}
	public int getcurrentIndex(){
		return playlistIndex;
	}
	public String getPlaylistName(int i){
		return playlist[i].getPlaylistName();
	}

	public void setPlaylist(Playlist[] p){
		playlist = p;
	}
	public void setcurrentIndex(int i){
		playlistIndex = i;
	}

	// The location of the playlist
	public int findIndex(String name){
		try{
			for(int i=0; i<manyPlaylist; i++){
				if(playlist[i].getPlaylistName().equalsIgnoreCase(name))
					return i;
			}
			throw new IllegalArgumentException();
		}
		catch(IllegalArgumentException ex){
			System.out.println("That playlist name does not exist!");
		}
		return (manyPlaylist+1);
	}
	public String allPlaylist(){
		String output = "";
		for(int i=0; i<playlist.length; i++){
			output += playlist[i].getPlaylistName() + "\n";
		}
		return output;
	}
	// Add a new playlist. Goes to end of list.
	public void addPlaylist(String name){
		try{
			this.manyPlaylist ++;
			// Add a playlist.
			Playlist[] biggerArray = new Playlist[manyPlaylist];
			System.arraycopy(playlist, 0, biggerArray, 0, manyPlaylist-1);
			// New playlist is empty
			biggerArray[manyPlaylist-1] = new Playlist(name);
			this.playlist = biggerArray;
			System.out.println("A new playlist has been added");
		}
		catch(Exception e){
			System.out.println("Cannot add playlist!");
		}
	}
	public void removePlaylist(int position){
		try{
			// The position is not valid. It is not within the array where there is a SongRecord
			if(position<=0 || position > manyPlaylist){
				throw new IllegalArgumentException();
			}
			else{
				this.manyPlaylist--;
				Playlist[] trimmedArray = new Playlist[manyPlaylist];
				System.arraycopy(playlist, 0, trimmedArray, 0, position-1);
				System.arraycopy(playlist, position, trimmedArray, position-1, manyPlaylist-position+1);
				playlist = trimmedArray;
				System.out.println("Playlist Removed at position " + position);
			}
		}
		catch(IllegalArgumentException e){
			System.out.println("No Playlist at position " + position + " to remove.");
		}
	}
	public void Duplicate(String name, int position){
		try{
			this.manyPlaylist ++;
			Playlist[] biggerArray = new Playlist[manyPlaylist];
			System.arraycopy(playlist, 0, biggerArray, 0, manyPlaylist-1);
			biggerArray[manyPlaylist-1] = (Playlist) biggerArray[position].clone();
			biggerArray[manyPlaylist-1].setPlaylistName(name);;
			this.playlist = biggerArray;
			System.out.println("A new playlist has been added");
		}
		catch(Exception e){
			System.out.println("Cannot add playlist!");
		}
	}
	public String toString(){
		return super.toString();
	}
}
