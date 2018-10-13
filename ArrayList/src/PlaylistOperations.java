/**
 * Constructs an <code>Playlist</code> object according to users input.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #1 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 16th, 2017
 */
import java.util.Scanner;

public class PlaylistOperations {

	/**
	 * Constructs an <code>Playlist</code> object according to users input.
	 *
	 * @param args
	 * @throws FullPlaylistException
	 * 	<code>Playlist</code> has reached max capacity
	 *
	 * @throws IllegalArgumentException
	 * 	Value passed is not valid.
	 */
	public static void main(String[] args) throws FullPlaylistException, IllegalArgumentException{
		// A new playlist with nothing inside.
		Playlist playlist = new Playlist();

		// variables that will store the users input
		String title, artist;
		int min, sec, position;

		// Keep doing until Q is typed to quit.
		do{
			// Informs user the menu options.
			System.out.println("A) Add Song");
			System.out.println("B) Print Songs by Artist");
			System.out.println("G) Get Song");
			System.out.println("R) Remove Song");
			System.out.println("P) Print All Songs");
			System.out.println("S) Size");
			System.out.println("Q) Quit");

			// The users menu option
			String input;
			Scanner stdin = new Scanner(System.in);

			try{
				// Promt user to
				System.out.print("\nSelect a menu option: ");
				// stores userInput into input and make it upper case
				input = stdin.nextLine().toUpperCase();

				switch(input){
				/**
				 * AddSong. Promt user to enter all attributes and store them
				 * accordingly. If user enters spaces or tabs after their
				 * title/artist, program will remove it. If the position, min or
				 * sec is invalid, program will inform user otherwise, Program
				 * will add the song and inform user add has been successfully added.
				 */
				case "A":
					System.out.println("");
					System.out.print("Enter the song title: ");
					title = stdin.nextLine();
					System.out.print("Enter the song artist: ");
					artist = stdin.nextLine();
					System.out.print("Enter the song length (minutes): ");
					min = stdin.nextInt();
					System.out.print("Enter the song length (seconds): ");
					sec = stdin.nextInt();
					System.out.print("Enter the position: ");
					position = stdin.nextInt();
					stdin.nextLine();

					while(title.charAt(title.length()-1) == ' ' || title.charAt(title.length()-1) == '\t'){
						title = title.substring(0, title.length()-1);
					}
					while(artist.charAt(artist.length()-1) == ' ' || artist.charAt(artist.length()-1) == '\t'){
						artist = artist.substring(0, artist.length()-1);
					}

					SongRecord song = new SongRecord(title,artist,min,sec);

					if(!playlist.validPositionAdd(position))
						System.out.println("Invalid position for adding the new song.");

					else if(song.getValid())
					{
						playlist.addSong(song, position);
						System.out.println("Song Added: " + song.getTitle() + " By " + song.getArtist());
					}
					break;
					/**
					 * Prints song by artist.
					 * Ask user for a artist and look in the current
					 * <code>Playlist</code> for artist. Print all songs by this
					 * artist.
					 */
				case "B":
					System.out.println();
					System.out.print("Enter the artist: ");
					artist = stdin.nextLine();

					playlist.getSongByArtist(playlist, artist).printAllSongs();
					break;
					/**
					 * Get song at position. If position is invalid, Inform user.
					 * Otherwise, print heading and song.
					 */
				case "G":
					System.out.println();
					// Promt user to enter position.
					System.out.print("Enter the position: ");
					position = stdin.nextInt();
					stdin.nextLine();

					if(position <= 0 || position > playlist.size())
						System.out.println("No Song at position " + position + ".");
					else{
						System.out.println("Song#\tTitle\t\tArtist\t\tLength");
						System.out.println("------------------------------------------------");
						// Print the song at user's position
						System.out.println(position + "\t" + playlist.getSong(position));
					}
					break;
					/**
					 * Remove song at position. If not valid, inform user.
					 * Otherwise, remove song and inform user song has been
					 * removed.
					 */
				case "R":
					System.out.println();
					// Ask user position to remove song
					System.out.print("Enter the position: ");
					position = stdin.nextInt();
					stdin.nextLine();

					if(position <= 0 || position > playlist.size())
						System.out.println("No Song at position " + position + " to remove.");

					// Remove song at position. If position not valid, it will inform user and nothing else will be done.
					else
						playlist.removeSong(position);
					break;
					/**
					 * Print all songs in playlist.
					 */
				case "P":
					System.out.println();
					playlist.printAllSongs();
					break;
					/**
					 *  Prints the amount of songs in playlist.
					 */
				case "S":
					System.out.println("There are " + playlist.size() + " song(s) in the currect playlist.");
					break;
					/**
					 *  Quit the porgram.
					 */
				case "Q":
					System.out.println("");
					// Inform user program did not break down but closed obeying his command.
					System.out.print("Program terminating normally...");
					System.exit(0);
					// If user types in wrong menu option, it will inform user and go back to the top of do-while loop.
				default:
					System.out.println("Invalid menu option");
				}
			}
			catch(Exception ex){
				// Should not happen. But if error occurs.
				System.out.println("Something happened. Error!");
			}
			finally{
				System.out.println();
			}
		}while(true);
	}

	//	// Tester for clone and equals. Since main method above does not check them.
	//	public static void main(String[] args) throws FullPlaylistException, IllegalArgumentException{
	//		Playlist playlist = new Playlist();
	//
	//		// variables that will store the users input
	//		String title, artist;
	//		int min, sec, position;
	//
	//		playlist.addSong(new SongRecord("Radioactive","Imagine Dragons",4,28), 1);
	//		playlist.addSong(new SongRecord("It's Time","Imagine Dragons",5,24), 1);
	//
	//		// Same playlist as original referred differently
	//		Playlist playlistDuplicate = (Playlist) playlist.clone();
	//
	//		playlistDuplicate.addSong(new SongRecord("Push","MatchBox 20",3,59), 1);
	//
	//		if(playlistDuplicate.equals(playlist)){
	//			System.out.println("Playlist are the same. (Wrong)");
	//		}
	//		else{
	//			System.out.println("Playlist are not the same. (Correct)");
	//		}
	//
	//		playlistDuplicate.removeSong(1);
	//
	//		printHeader();
	//		playlist.printAllSongs();
	//		printHeader();
	//		playlistDuplicate.printAllSongs();
	//
	//		if(playlistDuplicate.equals(playlist)){
	//			System.out.println("Playlist are the same. (Correct)");
	//		}
	//		else{
	//			System.out.println("Playlist are not the same. (Wrong)");
	//		}
	//
	//		playlist.removeSong(1);
	//		playlist.addSong(new SongRecord("It's Time","Imagine Dragons",5,24), 2);
	//
	//		printHeader();
	//		playlist.printAllSongs();
	//		printHeader();
	//		playlistDuplicate.printAllSongs();
	//
	//		if(playlistDuplicate.equals(playlist)){
	//			System.out.println("Playlist are the same. (Wrong)");
	//		}
	//		else{
	//			System.out.println("Playlist are not the same. (Correct)");
	//		}
	//	}
}
