/*
 * Fanng Dai
 * 109684495
 * Fanng.dai@stonybrook.edu
 * CSE214 Rec1
 * Homework #1
 * Extra Credit
 * 
 * Sorry not as much comments. Comments are a bit distracting to me.
 */

import java.util.Scanner;

public class PlaylistOperations {
	public static void main(String[] args) throws FullPlaylistException, IllegalArgumentException{
		IPod ipod = new IPod();
		Playlist currentPlaylist = ipod.getPlaylist(0);

		String title, artist, playlistName;
		int min, sec, position = 0, playlistIndex = 0;

		System.out.println("Your first playlist is called \"Default.\"");
		System.out.println("A) Add Song");
		System.out.println("B) Print Songs by Artist");
		System.out.println("C) Copy Playlist");
		System.out.println("D) Display Playlist Names");
		System.out.println("E) Compare Songs");
		System.out.println("G) Get Song");
		System.out.println("N) Create New Playlist");
		System.out.println("R) Remove Song");
		System.out.println("P) Print All Songs");
		System.out.println("S) Size");
		System.out.println("Q) Quit");
		System.out.println("V) Change Current Playlist");

		do{
			String input;
			Scanner stdin = new Scanner(System.in);

			try{
				System.out.println();
				// Let User know the current Playlist they are dealing with
				System.out.println("Current playlist is: " + currentPlaylist.getPlaylistName());
				System.out.print("Select a menu option: ");
				// stores userInput into input and make it upper case
				input = stdin.nextLine().toUpperCase();
				
				switch(input){
				case "A":
					System.out.println();
					System.out.print("Enter the song title: ");
					title = stdin.nextLine();
					System.out.print("Enter the song artist: ");
					artist = stdin.nextLine();

					// If user enters spaces after their title/artist, program will remove it.
					while(title.charAt(title.length()-1) == ' ' || title.charAt(title.length()-1) == '\t'){
						title = title.substring(0, title.length()-1);
					}
					while(artist.charAt(artist.length()-1) == ' ' || artist.charAt(artist.length()-1) == '\t'){
						artist = artist.substring(0, artist.length()-1);
					}
					
					System.out.print("Enter the song length (minutes): ");
					min = stdin.nextInt();
					System.out.print("Enter the song length (seconds): ");
					sec = stdin.nextInt();
					System.out.print("Enter the position: ");
					position = stdin.nextInt();

					SongRecord song = new SongRecord(title,artist,min,sec);

					if(position <= 0 || position > currentPlaylist.size()+1)
						System.out.println("Invalid position for adding the new song.");
					else if(song.getValid())
					{
						// If time not valid, promt user else, add the song
						currentPlaylist.addSong(song, position);
						System.out.println("Song Added: " + song.getTitle() + " By " + song.getArtist());
					}

					break;
				case "B":
					System.out.println();
					System.out.print("Enter the artist: ");
					artist = stdin.nextLine();

					Playlist.getSongByArtist(currentPlaylist, artist).printAllSongs();
					break;
				case "C":
					System.out.println();
					
					if(ipod.size()==0){
						System.out.println("No Playlist.");
						break;
					}
					
					System.out.print("Enter New Playlist Name: ");
					playlistName = stdin.nextLine();

					ipod.Duplicate(playlistName, playlistIndex);
					playlistIndex = ipod.size()-1;
					break;
				case "D":
					System.out.println();
					System.out.print(ipod.allPlaylist());
					break;
				case "E":
					System.out.println("Comparing Playlist: ");
					System.out.print("Enter the name of the playlist: ");
					playlistName = stdin.nextLine();
					int temp = ipod.findIndex(playlistName);
					if(temp>ipod.size())
						break;
					if(currentPlaylist.equals(ipod.getPlaylist(temp))){
						System.out.println(currentPlaylist.getPlaylistName() + " and "
								+ ipod.getPlaylist(temp).getPlaylistName() + " are the same.");
					}
					else{
						System.out.println(currentPlaylist.getPlaylistName() + " and "
								+ ipod.getPlaylist(temp).getPlaylistName() + " are not the same.");
					}
					break;
				case "G":
					System.out.println("Get Song at Position: ");
					System.out.println();
					System.out.print("Enter the position: ");
					position = stdin.nextInt();

					if(position <= 0 || position > currentPlaylist.size())
						System.out.println("No Song at position " + position + ".");
					else{
						System.out.println(String.format("%5s%10s%17s%16s", "Song#", "Title", "Artist", "Length"));
						System.out.println("------------------------------------------------");
						System.out.println(position + "\t" + currentPlaylist.getSong(position));
					}
					break;
				case "N":
					System.out.println();
					System.out.print("Enter New Playlist Name: ");
					ipod.addPlaylist(stdin.nextLine());
					playlistIndex = ipod.size()-1;
					break;
				case "R":
					System.out.println();
					System.out.print("Enter the position to remove song: ");
					position = stdin.nextInt();

					if(position <= 0 || position > currentPlaylist.size())
						System.out.println("No Song at position " + position + " to remove.");
					else
						currentPlaylist.removeSong(position);
					break;
				case "P":
					System.out.println();
					System.out.println("Printing all Song in current the playlist " + currentPlaylist.getPlaylistName());
					System.out.println();
					currentPlaylist.printAllSongs();
					break;
				case "S":
					System.out.println("There are " + currentPlaylist.size() + " song(s) in the currect playlist.");
					break;
				case "Q":
					System.out.println();
					System.out.print("Program terminating normally...");
					System.exit(0);
				case "V":
					System.out.print("Enter playlist name: ");
					playlistName = stdin.nextLine();
					if(ipod.findIndex(playlistName) < ipod.size())
						playlistIndex = ipod.findIndex(playlistName);
					break;
				default:
					System.out.println("Invalid menu option");
				} 
			}
			catch(Exception ex){
				System.out.println("Something happened. Error!");
			}
			currentPlaylist = ipod.getPlaylist(playlistIndex);
		}while(true);
	}
}