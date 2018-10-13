/**
 * Runs the program accordingly.
 * Contains the main menu and submenu (inside folder with emails)
 *
 * Mailbox only saves if you quit at the end. Otherwise, all information will
 * be lost.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #5 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 14th, 2017
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Mail implements Serializable{
	/**
	 * Stores the main mailbox that will be used by the application. This
	 * mailbox should be instantiated in the main method.
	 */
	public static Mailbox mailbox;
	/**
	 * The current folder which the submenu is dealing with.
	 */
	public static Folder currentFolder;

	public static void fileRead(){
		try {
			FileInputStream file = new FileInputStream("mailbox.obj");
			ObjectInputStream fin = new ObjectInputStream(file);
			mailbox = (Mailbox) fin.readObject();
			file.close();
			System.out.println("Previous save found, starting with previous mailbox.\n");
		}
		catch (IOException a) {
			mailbox = new Mailbox();
			System.out.println("Previous save not found, starting with an empty mailbox.\n");
		}
		catch (ClassNotFoundException c) {
			mailbox = new Mailbox();
			System.out.println("Previous save not found, starting with an empty mailbox.\n");
		}
	}

	public static void fileWrite(){
		try{
			FileOutputStream file = new FileOutputStream("mailbox.obj");
			ObjectOutputStream fout = new ObjectOutputStream(file);
			fout.writeObject(mailbox);
			fout.close();
			System.out.println("Program succesfully exited and mailbox saved.");
		}
		catch(IOException a){
			System.out.println("Cannot save file.");
		}
		System.exit(0);
	}
	/**
	 * Prints the menu which lets the user know the options. Also prints the
	 * mailbox with all the folders within the <code>Mailbox</code>
	 */
	public static void printMenu(){
		System.out.println("Mailbox:\n--------\n" + mailbox);
		System.out.println("A - Add folder");
		System.out.println("R - Remove folder");
		System.out.println("C - Compose email");
		System.out.println("F - Open folder");
		System.out.println("I - Open Inbox");
		System.out.println("T - Open Trash");
		System.out.println("E - Empty Trash");
		System.out.println("Q - Quit\n");
		System.out.print("Enter a user option: ");
	}

	/**
	 * Prints the submenu which is within the a folder.
	 */
	public static void printFolderMenu(){
		System.out.println("\nM - Move email");
		System.out.println("D - Delete email");
		System.out.println("V - View email contents");
		System.out.println("SA - Sort by subject line in ascending order");
		System.out.println("SD - Sort by subject line in descending order");
		System.out.println("DA - Sort by date in ascending order");
		System.out.println("DD - Sort by date in descending order");
		System.out.println("R - Return to mailbox\n");
		System.out.print("Enter a user option: ");
	}

	/**
	 * Makes sure that when an index is asked, user enters a valid integer.
	 *
	 * When user enters 0, that is not a valid position. Since in the human
	 * world, they start counting at 1. Therefore, 0 will become -1 which is
	 * invalid.
	 *
	 * @return
	 * 	An integer if the user entered an integer.
	 * 	Otherwise, it will return -1 which is invalid.
	 */
	public static int confirmNumber(){
		Scanner stdin = new Scanner(System.in);
		try{
			return (Integer.parseInt(stdin.next()) -1);
		}
		catch(NumberFormatException ex){
			System.out.println("\nThat's not a number.");
			return -2;
		}
	}

	/**
	 * When prompted, the order printed is date descending.
	 * This method continuously prints the current folder name followed by the
	 * emails in this folder. It will also print the subMenu.
	 *
	 * M - Move email
	 * 	Prompts the user to enter the index of the email they want to move.
	 * 	If the index is not valid, it will inform the user and restart from subMenu.
	 * 	It will then, print all the folders and prompt for the move to folder.
	 * 	If that folder does not exist, the email will be moved to inbox.
	 *
	 * D - Delete email
	 * 	Prompts user the index which the email is at. If the index is valid, it
	 * 	will move the email to trash folder. Otherwise, it will inform user the
	 * 	index does not exist.
	 *
	 * C - view email contents
	 * 	Prints the <code>Email</code> accordingly. (toString)
	 *
	 * Sa- sort by subject line in ascending order
	 * SD - sort by subject line in descending order
	 * DA - sort by date in ascending order
	 * DD - sort by date in descending order
	 *
	 * R- return to mailbox
	 * 	Unless this is called, user to remain in the subMenu section of the code.
	 *
	 * If user enters some option not in the given options, they will be
	 * informed and asked again.
	 */
	public static void subMenu(){
		String input;
		currentFolder.sortByDateDescending();

		do{
			Scanner stdin = new Scanner(System.in);
			System.out.println("\n" + currentFolder.getName());
			System.out.println(currentFolder);

			printFolderMenu();
			input = stdin.next().toUpperCase().trim();

			if(input.equals("M")){
				Email tempEmail;

				System.out.print("\nEnter the index of the email to move: ");
				int index = confirmNumber();
				if(currentFolder.getEmails(index) != null && index > -1){
					tempEmail = currentFolder.getEmails(index);
					System.out.println("\nFolders:");
					System.out.println(mailbox);
					System.out.print("Select a folder to move\" " + tempEmail.getSubject() + "\" to: ");
					stdin.nextLine();

					mailbox.moveEmail(tempEmail, mailbox.getFolder(stdin.nextLine().trim()));
					currentFolder.removeEmail(index);
				}
				else if(index != -2){
					System.out.println("\nInvalid Index");
				}
			}
			else if(input.equals("D")){
				Email tempEmail;

				System.out.print("\nEnter the index of the email to move: ");
				int index = confirmNumber();
				if(currentFolder.getEmails(index) != null && index > -1){
					tempEmail = currentFolder.getEmails(index);
					currentFolder.removeEmail(index);
					mailbox.deleteEmail(tempEmail);
				}
				else if(index != -2){
					System.out.println("\nInvalid Index");
				}
			}
			else if(input.equals("V")){
				System.out.print("\nEnter email index: ");

				int index = confirmNumber();
				if(currentFolder.getEmails(index) != null && index > -1){
					System.out.println(currentFolder.getEmails(index));
				}
				else if(index != -2){
					System.out.println("\nInvalid Index");
				}
			}
			else if(input.equals("SA")){
				currentFolder.sortBySubjectAscending();
			}
			else if(input.equals("SD")){
				currentFolder.sortBySubjectDescending();
			}
			else if(input.equals("DA")){
				currentFolder.sortByDateAscending();
			}
			else if(input.equals("DD")){
				currentFolder.sortByDateDescending();
			}
			else if(!input.equals("R")){
				System.out.println("\nNot a menu option.");
			}
		} while( !input.equals("R"));
		System.out.println();
	}

	/**
	 * Calls the fileWrite method which checks if a Mailbox has already been made.
	 * If it is, it will set the mailbox to what was stored in the file, otherwise,
	 * it will start a new email.
	 *
	 * This method will continuously call the Menu button once an action is
	 * finished and "Q" is not called.
	 *
	 * A - Add Folder
	 * 	Prompts user for the folder name and if it does not exist already, it
	 * 	will add the folder. Otherwise, it will inform user it exists already
	 * 	and go back to menu.
	 *
	 * R - Remove Folder
	 * 	If folder exist and it is not inbox or trash then it will be deleted.
	 * 	Otherwise, it will inform user it is impossible to remove and go back
	 * 	to the menu.
	 *
	 * C - Compose Email
	 * 	Prompt user for the recipient and will continue to if to, cc, and bcc
	 * 	are all enpty. Subject cannot be empty as well.
	 *
	 * F - Open folder
	 * I - Open Inbox
	 * T - Open Trash
	 * 	Opens the folder specified and goes to subMenu for that folder.
	 *
	 * E - Empty Trash
	 * 	Removes everything in Trash.
	 *
	 * Q - Quit
	 * 	Saves the Mailbox and exits the program.
	 */
	public static void main(String[] args){
		// Check if file exist
		fileRead();

		String input;
		Scanner stdin = new Scanner(System.in);

		do{
			printMenu();
			input = stdin.next().toUpperCase().trim();

			switch(input){
			case "A":
				System.out.print("\nEnter folder name: ");
				stdin.nextLine();
				Folder temp = new Folder(stdin.nextLine().trim());
				mailbox.addFolder(temp);
				System.out.println();
				break;
			case "R":
				System.out.print("\nEnter folder to remove: ");
				stdin.nextLine();
				mailbox.deleteFolder(stdin.nextLine().trim());
				break;
			case "C":
				mailbox.composeEmail();
				break;
			case "E":
				mailbox.clearTrash();
				break;
			case "F":
				System.out.print("\nEnter folder name: ");
				stdin.nextLine();
				currentFolder = mailbox.getFolder(stdin.nextLine().trim());
				if(currentFolder != null)
					subMenu();
				else{
					System.out.println();
				}
				break;
			case "I":
				currentFolder = mailbox.getFolder("Inbox");
				subMenu();
				break;
			case "T":
				currentFolder = mailbox.getFolder("Trash");
				subMenu();
				break;
			default:
				if(!input.equals("Q"))
					System.out.println("\nNot a menu option.\n");
				break;
			}

		}while(!input.equalsIgnoreCase("Q"));

		fileWrite();
	}
}
