/**
 * The <code>Tree</code> constructs a tree with the given information.
 * It can addNodes and begin session.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #4 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 8th, 2017
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Tree {
	/** 
	 * The root of the <code>Tree</code>
	 */
	private TreeNode root;

	/**
	 * Empty constructor.
	 * This is not used in this homework.
	 */
	public Tree(){
		root = null;
	}

	/**
	 * Constructor that sets the root of this <code>Tree</code>.
	 * 
	 * @param root
	 * 	The root of the <code>Tree</code>.
	 */
	public Tree(TreeNode root){
		this.root = root;
	}

	/**
	 * Mutator. Sets the root to the given parameter
	 * @param root
	 * 	Sets the root to equal the given parameter.
	 */
	public void setRoot(TreeNode root){
		this.root = root;
	}

	/**
	 * Accessor. Get the root of the object
	 * @return
	 * 	the root
	 */
	public TreeNode getRoot(){
		return this.root;
	}

	/**
	 * Finds the label and return that node.
	 * 
	 * @param label
	 * 	The label to search for.
	 * @return
	 * 	A reference to the node that has the given label.
	 * 	Null if no node has such label.
	 */
	public TreeNode getNodeReference(String label){

		// Return that node if the labels match
		if(root.getLabel().equals(label))
			return root;
		// If there is no more nodes
		else if(root == null)
			return null;

		TreeNode node;
		if(root.getLeft() != null){
			node = new Tree(root.getLeft()).getNodeReference(label);
			if(node != null)
				return node;
		}
		if(root.getMiddle() != null){
			node = new Tree(root.getMiddle()).getNodeReference(label);
			if(node != null)
				return node;
		}
		if(root.getRight() != null){
			node = new Tree(root.getRight()).getNodeReference(label);
			if(node != null)
				return node;
		}
		return null;
	}

	/**
	 * A method to add a TreeNode to the tree. The location will be a child of
	 * parentLabel. The child node should be left justified meaning that it
	 * should first be placed in the left most TreeNode reference, then the
	 * middle, then the right.
	 * 
	 * @param label
	 * 	The "name" of the TreeNode
	 * @param prompt
	 * 	Prompt displayed to the screen as a possible answer to a question.
	 * @param message
	 * 	Message displayed to the screen.
	 * @param parentLabel
	 *  The parent "name" of the current TreeNode
	 * 
	 * @return
	 * 	True if the node was successfully added to the tree. false otherwise.
	 */
	public boolean addNode(String label, String prompt, String message, String parentLabel){
		// Label does not exist and it's not the root.
		if(getNodeReference(parentLabel) == null && !label.equalsIgnoreCase("root"))
			return false;

		// Found the parentLabel, null if label is root
		TreeNode cursor = getNodeReference(parentLabel);
		TreeNode item = new TreeNode(label, prompt, message);
		// same as root == null.
		if(label.equalsIgnoreCase("root")){
			root = item;
		}
		// Add child otherwise
		else{
			if(cursor.getLeft()==null){
				cursor.setLeft(item);
			}
			else if(cursor.getMiddle() == null){
				cursor.setMiddle(item);
			}
			else if(cursor.getRight() == null){
				cursor.setRight(item);
			}
		}
		return true;
	}

	/**
	 * Calls the preOrder method in <code>TreeNode</code> to print the tree in
	 * a preOrder format.
	 */
	public void preOrder(){
		if(root != null){
			System.out.println("Traversing the tree in preorder:");
			root.preorder();
		}
		else
			System.out.println("Load a File.\n");
	}

	/**
	 * Start the question and answer session
	 * Will call the printMessagePrompt method from the <code>TreeNode</code>
	 * class. This method will print the message and prompts of that node.
	 * 
	 * It will keep going until a correct choice is given or 
	 */
	public void beginSession(){
		// Makes sure there is a tree beginning session.
		if(root == null){
			System.out.println("Load a File.\n");
			return;
		}

		System.out.println("Help Session Starting...");
		Scanner input = new Scanner(System.in);

		TreeNode cursor = root;
		// Not a leaf. No resolution to problem
		while(!cursor.isLeaf()){
				cursor.printMessagePrompt();
				String choice = input.next();

				System.out.println();
				switch(choice){
				case "0":
					System.out.println("Thank you for using this automated help service!\n");
					return;
				case "1":
					if(cursor.getLeft() != null)
						cursor = cursor.getLeft();
					else
						System.out.println("Not a choice option.\n");
					break;
				case "2":
					if(cursor.getMiddle() != null)
						cursor = cursor.getMiddle();
					else
						System.out.println("Not a choice option.\n");
					break;
				case "3":
					if(cursor.getRight() != null)
						cursor = cursor.getRight();
					else
						System.out.println("Not a choice option.\n");
					break;
				default:
					System.out.println("Not a choice option.\n");
				}
		}
		// The solution.
		System.out.println(cursor.getMessage());
		System.out.println("\nThank you for using this automated help service!\n");
	}
}