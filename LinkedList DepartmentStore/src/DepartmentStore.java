/**
 * The <code>DepartmentStore</code>  will ask user for input and follow the
 * users command.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #2 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 25th, 2017
 */
import java.util.Scanner;

public class DepartmentStore {
	private static Scanner stdin = new Scanner(System.in);
	private static String stringInput;
	/**
	 * Removes all the spaces and tabs at the end of the string. 
	 * 
	 * @param word
	 * 	Word which we are looking at to remove spaces and tabs
	 * 
	 * @return
	 * 	The parameter without the spaces in the end.
	 */
	private static String removeSpace(String word){
		while((word.charAt(word.length()-1) == ' ' 
				|| word.charAt(word.length()-1) == '\t' || word.charAt(word.length()-1) == '\n')){
			word = word.substring(0, word.length()-1);
			if(word == null)
				return null;
		}
		return word;
	}

	/**
	 * Checks for valid name
	 * 
	 * Method keep will asking user for name of product until answer given is a
	 * valid name. (Name cannot be just spaces or tabs)
	 * 
	 * @return
	 * @throws InvalidLengthException
	 */
	private static String getName(){
		boolean validString = false;
		do{
			try{
				System.out.print("Enter the name: ");
				stringInput = stdin.nextLine();
				stringInput = removeSpace(stringInput);

				if(stringInput.equals(null)){
					throw new InvalidLengthException();
				}
				else{
					validString = true;
				}
			}
			catch(InvalidLengthException ex){
				ex.invalidString();
			}
			catch(Exception ex){
				System.out.println("Invalid name.");
			}
		}while(!validString);
		return stringInput;
	}
	/**
	 * Continuously ask the user to input the correct format (double) until
	 * user is correct. Checks to make sure it is a double by passing it in
	 * directly as a double and throwing an exception if it not.
	 * 
	 * @return
	 * 	The users input. price.
	 */
	private static double getPrice(){
		do{
			try{
				System.out.print("Enter the price: ");
				return Double.parseDouble(stdin.nextLine());
			}
			catch(NumberFormatException ex){
				System.out.println("Invalid price.");
			}
		}while(true);
	}
	/**
	 * Continuously ask the user to input a 9 hexadecimal number until user
	 * enters the correct string. Method makes sure user entered it correctly
	 * and passes it onto to rfidTagNumber in the main method.
	 * 
	 * @return
	 * 	rfidTagNumber - correctly
	 */
	private static String getRfidTagNumber(){
		boolean validString = false;
		do{
			try{
				System.out.print("Enter the RFID: ");
				stringInput = stdin.nextLine().toUpperCase();
				stringInput = removeSpace(stringInput);

				if(stringInput.length() != 9){
					throw new InvalidLengthException();
				}
				else if(!stringInput.matches("^[0-9a-fA-F]+$") || stringInput.contains(" ")
						|| stringInput.contains("\t")){
					throw new InvalidCharException();
				}
				else{
					validString = true;
				}
			}
			catch(InvalidLengthException ex){
				ex.rfidError(stringInput);
			}
			catch(InvalidCharException ex){
				ex.hexError();
			}
			catch(Exception ex){
				System.out.println("Invalid RFID.");
			}
		}while(!validString);
		return stringInput;
	}
/**
 * Ask the user for the location. Depending on what is called.
 * 
 * flag = 's' -> s#####
 * flag = 'c' -> c###
 * flag = 'n' -> no "out" yes s##### & c###
 * flag = 'a' -> any of the following including "out"
 * 
 * @param message
 * 	The message to be printed. Ask user to enter location.
 * @param flag
 * 	Which format to check.
 * @return
 * 	The location correctly.
 */
	private static String getLocation(String message, char flag){
		boolean validString = false;
		do{
			try{
				System.out.print(message);
				stringInput = stdin.nextLine().toLowerCase();
				stringInput = removeSpace(stringInput);

				// shelf location only
				if((flag == 's' || flag == 'n' || flag == 'a') && stringInput.length() == 6
						&& Character.toLowerCase(stringInput.charAt(0)) == ('s') 
						&& stringInput.substring(1,6).matches("^[0-9]+$")){
					validString = true;
				}
				// cart location only
				else if((flag == 'c' || flag == 'n' || flag == 'a') && stringInput.length() == 4 && Character.toLowerCase(stringInput.charAt(0)) == ('c')
						&& stringInput.substring(1,4).matches("^[0-9]+$")){
					validString = true;
				}
				// anylocation
				else if(flag == 'a' && stringInput.equalsIgnoreCase("out")){
					validString = true;
				}
				else if(flag == 's' && stringInput.length() != 6 ||
						flag == 'c' && stringInput.length() != 4)
					throw new InvalidLengthException();
				else
					throw new InvalidCharException();
			}
			catch(InvalidLengthException ex){
				if(flag == 's')
					ex.originalLocationError(stringInput);
				else
					ex.invalidString();
			}
			catch(InvalidCharException ex){
				ex.digitError();
			}
			catch(Exception ex){
				System.out.println("Invalid location.");
			}
		}while(!validString);
		return stringInput;
	}

	public static void main(String[] args){
		String name, rfidTagNumber, originalLocation, location;
		double price;

		System.out.println("\tWelcome!");
		ItemList items = new ItemList();

		do{
			System.out.println("\n\tC - Clean store");
			System.out.println("\tI - Insert an item into the list");
			System.out.println("\tL - List by location");
			System.out.println("\tM - Move an item in the store");
			System.out.println("\tO - Checkout");
			System.out.println("\tP - Print all items in store");
			System.out.println("\tR - Print by RFID tag number");
			System.out.println("\tU - Update inventory System");
			System.out.println("\tQ - Exit the program.\n");

			System.out.print("Please select an option: ");
			String input = stdin.nextLine().toUpperCase();

			switch(input) {
			/**
			 * Clean store
			 * 
			 * Goes through all items that are currently on a shelf that isn't
			 * in their original location and place them back to their original
			 * location.
			 * 
			 * This does not affect items in carts or items that are "out."
			 * Print a formatted table of all items that hace been moved in
			 * this process.
			 */
			case "C":
				items.cleanStore();
				break;
				/**
				 * Insert an item into the list
				 *
				 * Promts the user for the information about a new item. If all
				 * the data entered by the user is valid, the entry is to be
				 * inserted into the list into the correct position.
				 */
			case "I":
				name = getName();
				rfidTagNumber = getRfidTagNumber();
				originalLocation = getLocation("Enter the original location: ", 's');
				price = getPrice();

				items.insertInfo(name, rfidTagNumber, price, originalLocation);
				break;
				/**
				 * List by location
				 * 
				 * Prompts the user for a location in the store and prints the
				 * list of all items that are currently in that location,
				 * sorted by rfidTagNumber.
				 */
			case "L":
				location = getLocation("Enter the location: ", 'a');
				items.printByLocation(location);
				break;
				/**
				 * Move an item in the store
				 * 
				 * Prompts the user for an rfidTagNumber, a source location,
				 * and a destination location of an item to move.
				 * 
				 * If the item is not found, report the situation to the user
				 * and do nothing. If there are duplicate items with the same
				 * rfidTagNumber in one location, moving any given one of them
				 * is fine.
				 * 
				 * If the location entered is not a valid location, print an
				 * error message indication that this is the case.
				 */
			case "M":
				rfidTagNumber = getRfidTagNumber();
				originalLocation = getLocation("Enter the original location: ", 'n');
				location = getLocation("Enter the new location: ", 'n');
				if(!items.moveItem(rfidTagNumber, originalLocation, location ))
					System.out.println("Item does not exist.");
				break;
				/**
				 * Checkout
				 * 
				 * Takes a cart number and changes the location of every item
				 * in the corresponding cart to "out."
				 * 
				 * Print a formatted table of all items that have been checked
				 * out in this process. Print a message to the user of the
				 * total cost of all the items that were in the given cart.
				 */
			case "O":
				originalLocation = getLocation("Enter the cart number: ", 'c');
				price = items.checkOut(originalLocation);
				System.out.print("\nThe total cost for all merchandise in cart "
						+ originalLocation.substring(1,4) + " was $");
				System.out.format("%.2f", price);
				System.out.println();
				break;
				/**
				 * Print all items in store
				 * 
				 * Prints the list of all items that are currently stored in
				 * the system, sorted by rfidTagNumber. This includes
				 * everything that is on a shelf, in a cart, or "out."
				 */
			case "P":
				items.printAll();
				break;
				/**
				 * Print by RFID tag number
				 * 
				 * Goes through the store's inventory and prints all items that
				 * have the given rfidTagNumber. The table is to be printed the
				 * same format as the other print commands.
				 */
			case "R":
				rfidTagNumber = getRfidTagNumber();
				items.printByRFid(rfidTagNumber);
				break;
				/**
				 * Update inventory system
				 * 
				 * Goes through the store's inventory and removes all items
				 * that are list as being "out" from the system. Items that are
				 * in carts or shelves are not affected by this command.
				 * 
				 * Print a formatted table of all the items that have been
				 * removed in this process.
				 */
			case "U":
				items.removeAllPurchased();
				break;
				/**
				 * Exit the program
				 */
			case "Q":
				System.out.print("Goodbye!");
				System.exit(0);
			default:
				System.out.println("Invalid input.");
			}

		} while(true);
	}
}
