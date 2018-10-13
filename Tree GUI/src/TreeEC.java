/**
 * The <code>Tree</code> constructs a tree with the given information.
 * It can addNodes and begin session.
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

import java.util.Scanner;

public class TreeEC {
	/**
	 * The root of the <code>Tree</code>
	 */
	private TreeNodeEC root;

	/**
	 * Empty constructor.
	 * This is not used in this homework.
	 */
	public TreeEC(){
		root = null;
	}
	/**
	 * Constructor that sets the root of this <code>Tree</code>.
	 *
	 * @param root
	 * 	The root of the <code>Tree</code>.
	 */
	public TreeEC(TreeNodeEC root){
		this.root = root;
	}
	/**
	 * Mutator. Sets the root to the given parameter
	 * @param root
	 * 	Sets the root to equal the given parameter.
	 */
	public void setRoot(TreeNodeEC root){
		this.root = root;
	}
	/**
	 * Accessor. Get the root of the object
	 * @return
	 * 	the root
	 */
	public TreeNodeEC getRoot(){
		return this.root;
	}

	/**
	 * Finds the label and returns that node.
	 *
	 * @param label
	 * 	The label to search for.
	 * @return
	 * 	A reference to the node that has the given label.
	 * 	Null if no node has such label.
	 */
	public TreeNodeEC getNodeReference(String label){
		//		System.out.println("GetNodeReference label " + label);
		// Return that node if the labels match
		if(root.getLabel().equals(label))
			return root;
		// If there is no more nodes
		else if(root == null)
			return null;

		for(int i=0; i < root.getNode().size(); i++)
			//			TreeNode node;
			if(root.getNode().get(i) != null){
				TreeNodeEC node = new TreeEC(root.getNode().get(i)).getNodeReference(label);
				//				System.out.println("GetNodeReference node " + node);
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
		TreeNodeEC cursor = getNodeReference(parentLabel);
		TreeNodeEC item = new TreeNodeEC(label, prompt, message);
		// same as root == null.
		if(label.equalsIgnoreCase("root")){
			root = item;
		}
		// Add child otherwise
		else{
			cursor.getNode().add(item);
		}
		return true;
	}

	/**
	 * Calls the preOrder method in <code>TreeNode</code> to print the tree in
	 * a preOrder format.
	 */
	public void preorder(){
		if(root != null){
			System.out.println("Traversing the tree in preorder:");
			root.preorder();
		}
		else
			System.out.println("Load a File.\n");
	}

	/**
	 * Calls the postorder method in <code>TreeNode</code> to print the tree in
	 * a postorder format.
	 */
	public void postorder(){
		if(root != null){
			System.out.println("Traversing the tree in postorder:");
			root.postorder();
		}
		else
			System.out.println("Load a File.\n");
	}

	/**
	 * Finds the label and returns that node.
	 *
	 * @param label
	 * 	The label to search for.
	 *
	 * <dt><b>precondition</b><dd>
	 * 	Root is never a leaf.
	 *
	 * @return
	 * 	A reference to the node that has the given label.
	 * 	Null if no node has such label.
	 */
	public TreeNodeEC getNodeReferenceParent(String label){
		//		System.out.println("GetNodeReference label " + label);

		for(int i=0; i < root.getNode().size(); i++)
			if(root.getNode().get(i) == null)
				return null;
			else if(root.getNode().get(i).getLabel().equals(label))
				return root;
		// If there is no more nodes

		//			TreeNode node;
			else if(root.getNode().get(i) != null){
				TreeNodeEC node = new TreeEC(root.getNode().get(i)).getNodeReferenceParent(label);
				//				System.out.println("GetNodeReference node " + node);
				if(node != null)
					return node;
			}
		return null;
	}

	public boolean isDigits(String num){
		try{
			Integer.parseInt(num);
			return true;
		}
		catch(NumberFormatException e){
			System.out.println("\nEnter a digit.\n");
			return false;
		}
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

		//		TreeNode parentCursor = root;
		TreeNodeEC cursor = root;
		// Not a leaf. No resolution to problem
		while(!cursor.isLeaf()){
			cursor.printMessagePrompt();
			String choiceW = input.next();
			if(isDigits(choiceW)){
				System.out.println();
				int choice = Integer.parseInt(choiceW);
				// Go back and it is not the root
				if(choice == -1){
					if(cursor == root)
						System.out.println("At root.\n");
					else{
						cursor = getNodeReferenceParent(cursor.getLabel());
					}
				}
				// quit
				else if(choice == 0){
					System.out.println("Thank you for using this automated help service!\n");
					return;
				}
				else if(choice < 0 || choice > cursor.getNode().size()){
					System.out.println("Not a choice option.\n");
				}
				else{
					// Because choice starts at 1. But array starts at 0
					choice -= 1;
					if(cursor.getNode().get(choice) != null){
						//					parentCursor = cursor;
						cursor = cursor.getNode().get(choice);
					}
					else
						System.out.println("Not a choice option.\n");
				}
			}
		}
		// The solution.
		System.out.println(cursor.getMessage());
		System.out.println("\nThank you for using this automated help service!\n");
	}
}
