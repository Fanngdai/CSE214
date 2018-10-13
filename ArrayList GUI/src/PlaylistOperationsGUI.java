/**
 * Fanng Dai
 * 109684495
 * Fanng.dai@stonybrook.edu
 * CSE214 Rec1
 * Homework #1
 * Extra Credit
 * 
 * Run Gui For fakeTunes
 * Program will print in console because of IPod, Playlist, or SongRecord
 * being called. The reason is because of the class PlaylistOperations. If you
 * wish to stop printing on the console you may go back to the other classes
 * and delete the print commands. But I must warn you, PlaylistOperations will 
 * not work properly afterwards.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;

public class PlaylistOperationsGUI extends Application{

	Label songL = new Label();
	Label titleL = new Label();
	Label artistL = new Label();
	Label lengthL = new Label();
	Label songL2 = new Label();	
	Label titleL2 = new Label();
	Label artistL2 = new Label();
	Label lengthL2 = new Label();
	Label label;


	private void clearText(){
		songL.setText("");
		titleL.setText("");
		artistL.setText("");
		lengthL.setText("");
		songL2.setText("");
		titleL2.setText("");
		artistL2.setText("");
		lengthL2.setText("");
		label.setText("");

		songL.setTextFill(Color.BLUE);
		titleL.setTextFill(Color.BLUE);
		artistL.setTextFill(Color.BLUE);
		lengthL.setTextFill(Color.BLUE);
		songL2.setTextFill(Color.BLUE);
		titleL2.setTextFill(Color.BLUE);
		artistL2.setTextFill(Color.BLUE);
		lengthL2.setTextFill(Color.BLUE);
		label.setTextFill(Color.BLUE);
	}

	public void start(Stage primaryStage) throws FullPlaylistException, IllegalArgumentException {
		// SET UP GUI (buttons, textField, etc) But they won't do anything yet
		primaryStage.setTitle("fakeTunes");
		primaryStage.setWidth(660);
		primaryStage.setHeight(680);

		// Set up buttons
		HBox firstRowButton = new HBox();
		firstRowButton.setSpacing(30);

		HBox secondRowButton = new HBox();
		secondRowButton.setSpacing(30);
		HBox thirdRowButton = new HBox();
		thirdRowButton.setSpacing(30);
		HBox fourthRowButton = new HBox();
		fourthRowButton.setSpacing(30);

		// Buttons
		Button addSong = new Button("Add New Song");
		addSong.setMinWidth(200);
		Button removeSong = new Button("Remove Song");
		removeSong.setMinWidth(200);
		Button getSong = new Button("Get Song");
		getSong.setMinWidth(200);

		Button newPlaylist = new Button("Create New Playlist");
		newPlaylist.setMinWidth(200);
		Button removePlaylist = new Button("Remove Playlist");
		removePlaylist.setMinWidth(200);
		Button copyPlaylist = new Button("Copy Playlist");
		copyPlaylist.setMinWidth(200);

		Button compareSongs = new Button("Compare Songs");
		compareSongs.setMinWidth(200);
		Button size = new Button("Size");
		size.setMinWidth(200);
		Button changeCurrentPlaylist = new Button("Change current Playlist");
		changeCurrentPlaylist.setMinWidth(200);

		Button displayPlaylist = new Button("Display Playlist Names");
		displayPlaylist.setMinWidth(200);
		Button printSongsByArtist = new Button("Print Songs by Artist");
		printSongsByArtist.setMinWidth(200);
		Button printAll = new Button("Print All Songs");
		printAll.setMinWidth(200);

		// Set button to location
		firstRowButton.getChildren().addAll(addSong, removeSong, getSong);
		secondRowButton.getChildren().addAll(newPlaylist, removePlaylist, copyPlaylist);
		thirdRowButton.getChildren().addAll(compareSongs, size, changeCurrentPlaylist);
		fourthRowButton.getChildren().addAll(displayPlaylist, printSongsByArtist, printAll);

		// Set textFields
		TextField titleT = new TextField();
		titleT.setPromptText("Title");
		titleT.setMaxWidth(110);
		TextField artistT = new TextField();
		artistT.setPromptText("Artist");
		artistT.setMaxWidth(110);
		TextField minT = new TextField();
		minT.setPromptText("Minutes");
		minT.setMaxWidth(110);
		TextField secT = new TextField();
		secT.setPromptText("Seconds");
		secT.setMaxWidth(110);
		TextField posT = new TextField();
		posT.setPromptText("Position");
		posT.setMaxWidth(110);
		TextField pNameT = new TextField();
		pNameT.setPromptText("Playlist Name");
		pNameT.setMaxWidth(110);

		HBox inputs = new HBox();
		inputs.getChildren().addAll(titleT, artistT, minT, secT, posT, pNameT);

		VBox buttons = new VBox(10);
		buttons.getChildren().addAll(firstRowButton, secondRowButton
				, thirdRowButton, fourthRowButton);

		// PUT ACTION TO GUI (button, textField, ect.)
		IPod ipod = new IPod();
		label = new Label("Welcome to fakeTunes, the world of wonders. \n"
				+ "Wise advice~ \t\t Text box before button~");
		label.setTextFill(Color.CYAN);



		HBox display = new HBox();
		display.setPrefSize(660, 500);
		HBox display2 = new HBox();
		display2.setPrefSize(660, 500);

		display.getChildren().addAll(label, songL, titleL, artistL, lengthL);
		display2.getChildren().addAll(songL2, titleL2, artistL2, lengthL2);

		HBox display3 = new HBox();
		display3.getChildren().addAll(display,display2);

		VBox workspace = new VBox(10);
		workspace.getChildren().addAll(display3, inputs, buttons);
		workspace.setStyle("-fx-background-color: #000000;");
		Scene scene = new Scene(workspace, 200, 200, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();

		addSong.setOnAction(e->{
			clearText();
			int playlistIndex = ipod.getcurrentIndex();
			// If time not valid, promt user else, add the song

			try {
				int min = Integer.parseInt(minT.getText());
				int sec = Integer.parseInt(secT.getText());
				int position = Integer.parseInt(posT.getText());
				String title = titleT.getText();
				String artist = artistT.getText();

				// If user enters spaces after their title/artist, program will remove it.
				while(title.charAt(title.length()-1) == ' ' || title.charAt(title.length()-1) == '\t'){
					title = title.substring(0, title.length()-1);
				}
				while(artist.charAt(artist.length()-1) == ' ' || artist.charAt(artist.length()-1) == '\t'){
					artist = artist.substring(0, artist.length()-1);
				}

				if(title == null || artist == null){
					throw new NullPointerException();
				}
				else if(min < 0 || sec < 0 || sec > 59){
					throw new IllegalArgumentException();
				}
				else if(ipod.getPlaylist(playlistIndex).size() == ipod.getPlaylist(playlistIndex).getCapacity()){
					throw new FullPlaylistException();
				}
				else if(position <= 0 || position > ipod.getPlaylist(playlistIndex).size()+1){
					throw new Exception();
				}
				else{
					SongRecord song = new SongRecord(title,artist,min,sec);
					ipod.getPlaylist(playlistIndex).addSong(song, position);
					label.setText("Song Added: " + song.getTitle() + " By " + song.getArtist());
				}
			}
			catch(FullPlaylistException ex){
				label.setText("Playlist Full.");
				label.setTextFill(Color.RED);
			}
			catch(NumberFormatException ex){
				label.setText("Input Invalid.");
				label.setTextFill(Color.RED);
			}
			catch(NullPointerException ex){
				label.setText("Textbox empty.");
				label.setTextFill(Color.RED);
			}
			catch(IllegalArgumentException ex){
				label.setText("Invalid song length.");
				label.setTextFill(Color.RED);
			}
			catch(Exception ex){
				label.setText("Error. Invalid input!");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		removeSong.setOnAction(e->{
			clearText();
			int playlistIndex = ipod.getcurrentIndex();

			try{
				int position = Integer.parseInt(posT.getText());

				if(position <= 0 || position > ipod.getPlaylist(playlistIndex).size()){
					label.setText("No song at position " + position + " to remove.");
					throw new IllegalArgumentException();
				}
				else{
					ipod.getPlaylist(playlistIndex).removeSong(position);
					label.setText("Song Removed at position: " + position);
				}
			}
			catch(NumberFormatException ex){
				label.setText("Position invalid.");
				label.setTextFill(Color.RED);
			}
			catch(IllegalArgumentException ex){
				label.setTextFill(Color.RED);
			}
			catch(Exception ex){
				label.setText("Error. Invalid input!");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		getSong.setOnAction(e->{
			clearText();
			int playlistIndex = ipod.getcurrentIndex();

			try{
				int position = Integer.parseInt(posT.getText());
				if(position <= 0 || position > ipod.getPlaylist(playlistIndex).size()){
					label.setText("No song at position " + position + ".");
					throw new IllegalArgumentException();
				}
				else{
					label.setText("");
					songL.setText("Song#\n----------\n");
					titleL.setText("Title\n----------------\n");
					artistL.setText("Artist\n---------------\n");
					lengthL.setText("Length\n--------\n");	

					songL.setText(songL.getText() + position);
					titleL.setText(titleL.getText() + ipod.getPlaylist(playlistIndex).getSong(position).getTitle());
					artistL.setText(artistL.getText() + ipod.getPlaylist(playlistIndex).getSong(position).getArtist());
					lengthL.setText(lengthL.getText() + ipod.getPlaylist(playlistIndex).getSong(position).getMinutes());
					if(ipod.getPlaylist(playlistIndex).getSong(position).getSeconds() < 10)
						lengthL.setText(lengthL.getText() + ":0");
					else
						lengthL.setText(lengthL.getText() + ":");
					lengthL.setText(lengthL.getText() + ipod.getPlaylist(playlistIndex).getSong(position).getSeconds());
				}
			}
			catch(NumberFormatException ex){
				label.setText("Position invalid.");
				label.setTextFill(Color.RED);
			}
			catch(IllegalArgumentException ex){
				label.setText("No song at this position.");
				label.setTextFill(Color.RED);
			}
			catch(Exception ex){
				label.setText("Position invalid.");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		newPlaylist.setOnAction(e->{
			clearText();

			try{
				if(pNameT.getText() == null)
					throw new NullPointerException();
				ipod.addPlaylist(pNameT.getText());
				label.setText(pNameT.getText() + " has been made.");
				ipod.setcurrentIndex(ipod.size()-1);
			}
			catch(NullPointerException ex){
				label.setText("No input.");
				label.setTextFill(Color.RED);
			}
			catch(Exception ex){
				label.setText("Error.");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		removePlaylist.setOnAction(e->{
			clearText();

			try{
				int position = Integer.parseInt(posT.getText());

				if(ipod.size()==0){
					// Some very important code 
					label.setText("What you tryna do? You deleted them all.");
					label.setText(label.getText() + "\nNo Playlist available.");
					label.setText(label.getText() + "\nSad life. I know.");
					label.setTextFill(Color.PINK);
				}	
				else if(position <= 0 || position > ipod.size()){
					label.setText("No song at position " + position + " to remove.");
					throw new IllegalArgumentException();
				}
				else{
					ipod.removePlaylist(position);
					label.setText("Playlist Removed at position " + position);
				}
			}
			catch(NumberFormatException ex){
				label.setText("Position invalid.");
				label.setTextFill(Color.RED);
			}
			catch(IllegalArgumentException ex){
				label.setTextFill(Color.RED);
			}
			catch(Exception ex){
				label.setText("Position invalid.");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		copyPlaylist.setOnAction(e->{
			clearText();
			int playlistIndex = ipod.getcurrentIndex();

			try{
				if(ipod.size()==0){
					label.setText("Playlist Empty.");
					label.setTextFill(Color.RED);
				}
				else if(pNameT.getText() == null)
					throw new NullPointerException();
				else{
					ipod.Duplicate(pNameT.getText(), playlistIndex);
					label.setText(pNameT.getText() + " has been made.");
					ipod.setcurrentIndex(ipod.size()-1);
				}
			}
			catch(NullPointerException ex){
				label.setText("No input.");
				label.setTextFill(Color.RED);
			}
			catch(Exception ex){
				label.setText("Error.");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		compareSongs.setOnAction(e->{
			clearText();
			int playlistIndex = ipod.getcurrentIndex();

			try{
				int temp = ipod.findIndex(pNameT.getText());
				if(pNameT.getText() == null)
					throw new NullPointerException();
				else if(ipod.getPlaylist(playlistIndex).equals(ipod.getPlaylist(temp))){
					label.setText(ipod.getPlaylist(playlistIndex).getPlaylistName() + " and "
							+ ipod.getPlaylist(temp).getPlaylistName() + " are the same.");
				}
				else{
					label.setText(ipod.getPlaylist(playlistIndex).getPlaylistName() + " and "
							+ ipod.getPlaylist(temp).getPlaylistName() + " are not the same.");
				}
			}
			catch(NullPointerException ex){
				label.setText("No input.");
				label.setTextFill(Color.RED);
			}
			catch(Exception ex){
				label.setText("Invalid Input.");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		size.setOnAction(e->{
			clearText();

			try{
				int playlistIndex = ipod.getcurrentIndex();
				label.setText("There are " + ipod.getPlaylist(playlistIndex).size() + " song(s) in the currect playlist.");
			}
			catch(Exception ex){
				label.setText("No not this button!!!");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		changeCurrentPlaylist.setOnAction(e->{
			clearText();

			try{
				if(pNameT.getText() == null)
					throw new NullPointerException();
				if(ipod.findIndex(pNameT.getText()) < ipod.size()){
					ipod.setcurrentIndex(ipod.findIndex(pNameT.getText()));
					label.setText(" Playlist is now " + pNameT.getText());
				}
				else{
					throw new IllegalArgumentException();
				}
			}
			catch(NullPointerException ex){
				label.setText("No input.");
				label.setTextFill(Color.RED);
			}
			catch(IllegalArgumentException ex){
				label.setText("Playlist Name Not Found.");
				label.setTextFill(Color.RED);
			}
			catch(Exception ex){
				label.setText("Error.");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		displayPlaylist.setOnAction(e->{
			clearText();

			try{
				label.setText(label.getText() + ipod.allPlaylist());
			}
			catch(Exception ex){
				label.setText("Error.");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});



		printSongsByArtist.setOnAction(e->{
			clearText();
			int playlistIndex = ipod.getcurrentIndex();

			try {
				Playlist temp = Playlist.getSongByArtist(ipod.getPlaylist(playlistIndex), artistT.getText());		

				// No songs found
				if(!Playlist.songByArtist(ipod.getPlaylist(playlistIndex), artistT.getText())){
					throw new Exception();
				}
				else{
					songL.setText("Song#\n----------");
					titleL.setText("Title\n----------------");
					artistL.setText("Artist\n---------------");
					lengthL.setText("Length\n--------");	

					for(int j=1; j<=ipod.getPlaylist(playlistIndex).size() && j<26; j++){
						songL.setText(songL.getText() + "\n" + j);
						titleL.setText(titleL.getText() + "\n" + temp.getSong(j).getTitle());
						artistL.setText(artistL.getText() + "\n" + temp.getSong(j).getArtist());
						lengthL.setText(lengthL.getText() + "\n" +temp.getSong(j).getMinutes());
						if(temp.getSong(j).getSeconds() < 10)
							lengthL.setText(lengthL.getText() + ":0");
						else
							lengthL.setText(lengthL.getText() + ":");
						lengthL.setText(lengthL.getText() + temp.getSong(j).getSeconds());
					} 

					if(ipod.getPlaylist(playlistIndex).size() > 25){
						songL2.setText("Song#\n----------");
						titleL2.setText("Title\n----------------");
						artistL2.setText("Artist\n---------------");
						lengthL2.setText("Length\n--------");	

						for(int j=26; j<=temp.size(); j++){
							songL2.setText(songL2.getText() + "\n" + j);
							titleL2.setText(titleL2.getText() + "\n" + temp.getSong(j).getTitle());
							artistL2.setText(artistL2.getText() + "\n" + temp.getSong(j).getArtist());
							lengthL2.setText(lengthL2.getText() + "\n" +temp.getSong(j).getMinutes());
							if(temp.getSong(j).getSeconds() < 10)
								lengthL2.setText(lengthL2.getText() + ":0");
							else
								lengthL2.setText(lengthL2.getText() + ":");
							lengthL2.setText(lengthL2.getText() + temp.getSong(j).getSeconds());
						} 
					}
				}
			}
			catch (FullPlaylistException e1) {
				label.setText("Playlist Full.");
				label.setTextFill(Color.RED);
			}
			catch(Exception ex){
				label.setText("Error.");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});

		printAll.setOnAction(e->{
			clearText();

			try{
				int playlistIndex = ipod.getcurrentIndex();
				Playlist temp = ipod.getPlaylist(playlistIndex);

				temp.printAllSongs();
				label.setText("");
				songL.setText("Song#\n----------");
				titleL.setText("Title\n----------------");
				artistL.setText("Artist\n---------------");
				lengthL.setText("Length\n--------");			


				for(int j=1; j<=ipod.getPlaylist(playlistIndex).size() && j<26; j++){
					songL.setText(songL.getText() + "\n" + j);
					titleL.setText(titleL.getText() + "\n" + temp.getSong(j).getTitle());
					artistL.setText(artistL.getText() + "\n" + temp.getSong(j).getArtist());
					lengthL.setText(lengthL.getText() + "\n" +temp.getSong(j).getMinutes());
					if(temp.getSong(j).getSeconds() < 10)
						lengthL.setText(lengthL.getText() + ":0");
					else
						lengthL.setText(lengthL.getText() + ":");
					lengthL.setText(lengthL.getText() + temp.getSong(j).getSeconds());
				} 

				if(ipod.getPlaylist(playlistIndex).size() > 25){
					songL2.setText("Song#\n----------");
					titleL2.setText("Title\n----------------");
					artistL2.setText("Artist\n---------------");
					lengthL2.setText("Length\n--------");	

					for(int j=26; j<=temp.size(); j++){
						songL2.setText(songL2.getText() + "\n" + j);
						titleL2.setText(titleL2.getText() + "\n" + temp.getSong(j).getTitle());
						artistL2.setText(artistL2.getText() + "\n" + temp.getSong(j).getArtist());
						lengthL2.setText(lengthL2.getText() + "\n" +temp.getSong(j).getMinutes());
						if(temp.getSong(j).getSeconds() < 10)
							lengthL2.setText(lengthL2.getText() + ":0");
						else
							lengthL2.setText(lengthL2.getText() + ":");
						lengthL2.setText(lengthL2.getText() + temp.getSong(j).getSeconds());
					} 
				}
			}
			catch(Exception ex){
				label.setText("Error.");
				label.setTextFill(Color.RED);
			}
			finally{
				titleT.clear();
				artistT.clear();
				minT.clear();
				secT.clear();
				posT.clear();
				pNameT.clear();
			}
		});
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}