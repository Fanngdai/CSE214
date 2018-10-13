/**
 * The <code>TreeDriver</code> contains the main method which will prompt the
 * user for information in constructing a tree.
 *
 * Program can accept any amount of nodes. Go forward and back for each node.
 * Print preorder and postorder format.
 * It will check all cases of wrong input. And deal with it accordingly.
 *
 * L- prompt the user for a file naem and build a new Tree. The entire tree
 * will be recreated each time this menu option is chosen.
 * Will throw exception if error with file occurs.
 *
 * H- This option will begin asking questions starting from the root of the
 * tree; if there is no tree set up, dusplay an error message. When displaying
 * with answers, display each answer on a seperate line with a number
 * associated with it, similar to a menu. Include another option, 0(zero) to
 * exit the help session and return to the menu.
 *
 * T - Traverse the tree in pre-order and siplay the label, prompt, and message
 * in each node on a separate line.
 *
 * U - Traverse the tree in post-order and display the label, prompt, and message
 * in each node on a separate line.
 *
 * Q - quit the program
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #4 for CSE 214, Summer 2017
 * 		Extra Credit
 * <dt><b>Date:</b><dd>
 * 		August 8th, 2017
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TreeDriverEC {
	static Scanner stdin;
	static TreeEC tree = new TreeEC();

	/**
	 * Prints the menu and prompt user for a choice.
	 */
	public static void menu(){
		// Look at header for details on each command.
		System.out.println("L - Load a Tree.");
		System.out.println("H - Begin a Help Session.");
		System.out.println("T - Traverse the Tree in preorder.");
		System.out.println("U - Traverse the Tree in postorder.");
		System.out.println("Q - Quit");
		System.out.print("Choice> ");
	}

	/**
	 * Checks to make sure blank lines in file are ignored.
	 *
	 * @return
	 * 	a string which is not empty.
	 */
	public static String validText(){
		String word = "";
		do{
			word = stdin.nextLine().trim();
		}while(word.equals(""));
		return word;
	}

	/**
	 * Prompts user to enter a file name.
	 * If file exists, it is stored as a file otherwise, it notifies the user and exits.
	 *
	 * Stores file label, prompt and message accordingly.
	 * If file is not formatted correctly, program will store the tree up until the error occurs in the file
	 */
	public static void loadFile(){
		boolean successful = true;
		try {
			System.out.print("Enter the file name> ");
			stdin = new Scanner(new File(stdin.next()));

			// Construct the root label, prompt, message. You can put this in the while loop and use addNode.
			tree = new TreeEC(new TreeNodeEC(validText(), validText(), validText()));


			while(stdin.hasNextLine()){
				// Throw argument in case it is not an integer.
				// How many child that root has
				String parentLabel = validText();
				// The last digits before the space
				int amtChild = Integer.parseInt(parentLabel.substring(parentLabel.lastIndexOf(' '), parentLabel.length()).trim());

				// Remove the amtChild from label
				parentLabel = parentLabel.substring(0,parentLabel.lastIndexOf(' '));

				for(int i=1; i<=amtChild; i++){
					if(!tree.addNode(validText(), validText(), validText(), parentLabel))
						successful = false;
				}
			}
			if(!successful){
				System.out.println("\nError detected in file. File is not correctly formatted.");
				System.out.println("Check to make sure all labels are correct.\n");
			}
			else{
				System.out.println("\nTree created successfully!\n");
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("\nFile Not Found.\n");
		}
		// exception will catch it.
		catch(NoSuchElementException e){
			System.out.println("File not correctly formatted.NoSuchElement\n");
		}
		catch(NumberFormatException e){
			System.out.println("File not correctly formatted. NumberFormat\n");
		}
		// Also Number Format exception
		catch(Exception e){
			System.out.println("File not correctly formatted.\n");
		}
	}

	/**
	 * Runs the program which prompts the user for a input which
	 * loads a file and store it as a tree, begin the session,
	 * print the tree in preOrder format, and quits the program.
	 */
	public static void main(String[] args){

		while(true){
			menu();
			stdin = new Scanner(System.in);
			String input = stdin.next().toLowerCase();
			System.out.println();

			switch (input){
			case "l":
				loadFile();
				break;
			case "h":
				tree.beginSession();
				break;
			case "t":
				tree.preorder();
				break;
			case "u":
				tree.postorder();
				break;
			case "q":
				System.out.print("Thank you for using our automated help services!");
				System.exit(0);
			default:
				System.out.println("Invalid menu option.\n");
			}
		}
	}
}
