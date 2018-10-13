import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The <code>DepartmentStoreGUI</code>  will ask user for input and follow the
 * users command. This will run as a GUI.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #2 for CSE 214, Summer 2017
 * 		Extra Credit GUI
 * <dt><b>Date:</b><dd>
 * 		July 25th, 2017
 */

public class DepartmentStoreGUI extends Application{
	// Text box
	TextField nameT = new TextField();
	TextField priceT = new TextField();
	TextField rfidTagT = new TextField();
	TextField originalLocationT = new TextField();
	TextField newLocationT = new TextField();

	/**
	 * Checks if user entered the correct formatting of the rfid.
	 *
	 * @param word
	 * 	The rfid to be checked
	 *
	 * @return
	 * 	True if the length is exacly 9 and each character is a hexadecimal.
	 * False otherwise.
	 */
	public static boolean checkRFID(String word){
		if(word.length() == 9 && word.matches("^[0-9a-fA-F]+$")){
			return true;
		}
		return false;
	}

	/**
	 * Checks if users input for location is valid
	 *
	 * @param word
	 * 	The word to be checked
	 * @param flag
	 * 	What to check. New location? Original location? Including out?
	 *
	 * @return
	 * 	True if the word is valid, false otherwise.
	 */
	public static boolean checkLocation(String word, char flag){
		// Is an shelf item
		if((flag == 's' || flag == 'n' || flag == 'a') && word.length() == 6
				&& Character.toLowerCase(word.charAt(0)) == ('s')
				&& word.substring(1,6).matches("^[0-9]+$")){
			return true;
		}
		// Is a cart item
		else if((flag == 'c' || flag == 'n' || flag == 'a') && word.length() == 4
				&& Character.toLowerCase(word.charAt(0)) == ('c')
				&& word.substring(1,4).matches("^[0-9]+$")){
			return true;
		}
		//a = anylocation.
		else if(flag == 'a' && word.equalsIgnoreCase("out")){
			return true;
		}
		return false;
	}

	/**
	 * Clears text boxes.
	 * Called after user inputs something
	 */
	public void clearText(){
		nameT.clear();
		priceT.clear();
		rfidTagT.clear();
		originalLocationT.clear();
		newLocationT.clear();
	}

	public String greet(){
		String output = "Welcome!\n\n";
		output += "To add item, please fill out the text boxes name, "
				+ "price, RFID, and the original location.\n\n"
				+ "To Move an item, please fill out original Location and New Location.\n\n"
				+ "To check for an item in a specific location, please "
				+ "enter that location in the original location text box.\n\n"
				+ "To check for items RFID, please enter RFID in the RFID textbox.\n\n"
				+ "Update will remove all items listed as \"out.\"\n\n"
				+ "Clean store will move all items back to their original place.\n\n"
				+ "To check out, please enter the location in \"original location\" text box.\n\n"
				+ "Overall, you should NOT be using new location except for when moving an item.\n\n"
				+ "All text boxes that are not required to fill out can be left blank.\n\n"
				+ "Thank you and enjoy!";

		return output;
	}

	public void start(Stage primaryStage){
		// Put the gray words in text boxs to inform user what to type
		nameT.setPromptText("Name");
		priceT.setPromptText("Price");
		rfidTagT.setPromptText("RFID");
		originalLocationT.setPromptText("Original Location");
		newLocationT.setPromptText("New Location");

		// button
		Button addItem = new Button("Add Item");
		addItem.setMinWidth(150);
		Button moveItem = new Button("Move Item");
		moveItem.setMinWidth(150);
		Button location = new Button("Item in Location");
		location.setMinWidth(150);
		Button rfid = new Button("Item with rfid");
		rfid.setMinWidth(150);
		Button update = new Button("Update");
		update.setMinWidth(150);
		Button cleanStore = new Button("Clean Store");
		cleanStore.setMinWidth(150);
		Button checkout = new Button("Check Out");
		checkout.setMinWidth(150);
		Button printAll = new Button("Print All");
		printAll.setMinWidth(150);

		VBox left = new VBox();
		left.setSpacing(20);
		left.getChildren().addAll(nameT, priceT, rfidTagT, originalLocationT, newLocationT
				,addItem, moveItem, location, rfid, update, cleanStore, checkout, printAll );

		Label display = new Label();
		display.setMinSize(640, 3000);
		display.setMaxSize(640, 3000);
		display.setAlignment(Pos.TOP_LEFT);
		display.setTextFill(Color.BLUE);

		// Make the scroll bar for the Items by RFID
		ScrollBar scrollBar = new ScrollBar();
		scrollBar.setOrientation(Orientation.VERTICAL);
		scrollBar.setMin(0);
		scrollBar.setMax(3000);
		scrollBar.valueProperty().addListener(event -> {
			display.setTranslateY(-scrollBar.getValue());
		});

		HBox workspace = new HBox();
		workspace.setSpacing(20);
				workspace.setStyle("-fx-background-color: #000000;");

//		BackgroundImage bImage = new BackgroundImage(new Image("file:///Users/fannydai/Documents/workspace/DepartmentStore/20562847_1507796599283351_841584405_n.jpg"),
//				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
//				BackgroundSize.DEFAULT);
//		Background background = new Background(bImage);
//		workspace.setBackground(background);

		workspace.getChildren().addAll(left, display, scrollBar);
		final Scene scene = new Scene(workspace, 850, 700);
		primaryStage.setTitle("Department Store");
		primaryStage.setWidth(scene.getWidth());
		primaryStage.setHeight(scene.getHeight());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		/**
		 *  Actions for buttons
		 */
		ItemList items = new ItemList();
		display.setText(greet());

		/**
		 * Add an Item
		 */
		addItem.setOnAction(e->{
			try{
				String name = nameT.getText();
				double price = Double.parseDouble(priceT.getText());
				String rfidTag = rfidTagT.getText();
				String original = originalLocationT.getText();

				if(name.equals(null))
					throw new NullPointerException();
				else if(!checkRFID(rfidTag)){
					display.setText("RFID must contain 9 hexadecimal characters!");
				}
				else if(!checkLocation(original, 's')){
					display.setText("Original Location must be 6 characters\n"
							+ "The first letter must start with 's' followed by exactly"
							+ " 5 digits!");
				}
				else{
					items.insertInfo(name, rfidTag, price, original);
					display.setText("Item was successfully added!\n\n");
					display.setText(display.getText() + items.printAllGUI());
					clearText();
				}
			}
			catch(NullPointerException ex){
				display.setText("Did you enter something for name, RFID and original location?");
			}
			catch(NumberFormatException ex){
				display.setText("Please enter a double value for price.");
			}
			catch(Exception ex){
				display.setText("Uh oh! Something went wrong for addItem.");
			}
		});

		/**
		 * Moves item from one location to another
		 */
		moveItem.setOnAction(e->{
			try{
				String rfidTag = rfidTagT.getText();
				String original = originalLocationT.getText();
				String newLocation = newLocationT.getText();

				if(items.isEmpty()){
					display.setText("No items in list to remove!");
				}
				else if(!checkRFID(rfidTag)){
					display.setText("RFID must contain 9 hexadecimal characters!");
				}
				else if(!checkLocation(original, 'n') || !checkLocation(newLocation, 'n') ){
					display.setText("Location must be 6 characters or 4 characters! \n"
							+ "The first letter must start with 's' followed by exactly"
							+ " 5 digits or it must start with 'c' followed by 3 digits!");
				}
				else if(!items.moveItem(rfidTag, original, newLocation)){
					display.setText("Item does not exist.");
				}
				else{
					display.setText("Item was successfully moved!\n");
					display.setText(display.getText() + items.printAllGUI());
					clearText();
				}
			}
			catch(Exception ex){
				display.setText("Uh oh! Something went wrong for moveItem.");
			}
		});

		/**
		 * Prints the items in this location
		 */
		location.setOnAction(e->{
			try{
				String loc = originalLocationT.getText();

				if(!checkLocation(loc, 's')){
					display.setText("Original Location must be 6 characters\n"
							+ "The first letter must start with 's' followed by exactly"
							+ " 5 digits!");
				}
				else{
					display.setText("Items at location " + loc + "\n");
					display.setText(items.printByLocationGUI(loc));
					clearText();
				}
			}
			catch(Exception ex){
				display.setText("Uh oh! Something went wrong for location.");
			}
		});

		/**
		 * Prints the items by rfid
		 */
		rfid.setOnAction(e->{
			try{
				String rfidTag = rfidTagT.getText();

				if(!checkRFID(rfidTag)){
					display.setText("RFID must contain 9 hexadecimal characters!");
				}
				else{
					display.setText("Items with RFID " + rfidTag + "\n");
					display.setText(items.printByRFidGUI(rfidTag));
					clearText();
				}
			}
			catch(Exception ex){
				display.setText("Uh oh! Something went wrong for rfid.");
			}
		});

		update.setOnAction(e->{
			display.setText("The following item(s) have been removed from the system:\n\n"
					+ items.removeAllPurchasedGUI());
		});

		cleanStore.setOnAction(e->{
			display.setText(items.cleanStoreGUI());
		});

		checkout.setOnAction(e->{
			try{
				String loc = originalLocationT.getText();

				if(!checkLocation(loc, 'c')){
					display.setText("Location must be 4 digits.\n"
							+ "It must start with 'c' followed by 3 digits");
				}
				else{
					display.setText(items.checkOutGUI(loc));
					clearText();
				}
			}
			catch(Exception ex){
				display.setText("Uh oh! Something went wrong at cart.");
			}
		});

		printAll.setOnAction(e->{
			try{
				display.setText(items.printAllGUI());
			}
			catch(Exception ex){
				display.setText("Uh oh! Something went wrong at printAll.");
			}
		});
	}
	public static void main(String[] args){
		Application.launch(args);
	}
}
