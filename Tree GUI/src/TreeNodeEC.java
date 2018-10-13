/**
 * The <code>TreeNode</code> is a node of the tree which has label, message
 * and prompt.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #4 for CSE 214, Summer 2017
 * 		Extra Credit
 * <dt><b>Date:</b><dd>
 * 		August 8th, 2017
 */
import java.util.ArrayList;

public class TreeNodeEC {
	// Children of this node
	private ArrayList<TreeNodeEC> node;
	// The "name" of this node. It will be used when constructing the <code>Tree</code>.
	private String label;
	// Message displayed to the screen.
	private String message;
	// Prompt displayed to the screen as a possible answer to a question.
	private String prompt;

	/**
	 * Empty constructor which declares a new <code>TreeNode</code> object.
	 */
	public TreeNodeEC(){
		this.label = null;
		this.message = null;
		this.prompt = null;
		node = new ArrayList<TreeNodeEC>();
	}
	/**
	 * Constructor that sets the root of the <code>Tree</code>.
	 * 
	 * @param label
	 * 	The label of the node.
	 * @param prompt
	 * 	The prompt of the node.
	 * @param message
	 * 	The message of the node.
	 */
	public TreeNodeEC(String label, String prompt, String message){
		this.label = label;
		this.message = message;
		this.prompt = prompt;
		node = new ArrayList<TreeNodeEC>();
	}

	// Mutators
	public void setNode(int i, TreeNodeEC node){
		this.node.set(i, node);
	}
	public void setLabel(String label){
		this.label = label;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public void setPrompt(String prompt){
		this.prompt = prompt;
	}

	// Accessor
	public ArrayList<TreeNodeEC> getNode(){
		return this.node;
	}
	public String getLabel(){
		return this.label;
	}
	public String getMessage(){
		return this.message;
	}
	public String getPrompt(){
		return this.prompt;
	}

	/**
	 * Prints the preorder of the given tree.
	 */
	public void preorder(){
		System.out.println(this);
		for(int i=0; i<node.size(); i++)
			if(node.get(i) != null)
				node.get(i).preorder();
	}

	/**
	 * Prints the postorder of the given tree.
	 */
	public void postorder(){
		for(int i=0; i<node.size(); i++)
			if(node.get(i) != null)
				node.get(i).postorder();
		System.out.println(this);
	}

	/**
	 * Checks if this <code>TreeNode</code> has any children.
	 * 
	 * @return
	 * 	True if it is a leaf, false otherwise.
	 */
	public boolean isLeaf(){
		for(int i=0; i<node.size(); i++)
			if(node.get(i) != null)
				return false;
		return true;
	}

	/**
	 * Used to print the message and the prompts of the given tree.
	 * 
	 * <dt><b>Precondition:</b><dd>
	 * 	Node will never reach leaf.
	 */
	public void printMessagePrompt(){
		if(isLeaf())
			return;

		// message
		System.out.println(this.message);
		// prompt label is the last num in label
		int count = 1;
		for(int i=0; i<node.size(); i++)
			if(node.get(i) != null)
				System.out.println(count++ + ") " + node.get(i).prompt);
		// go back &quit & choice
		System.out.print("-1) Back.\n0) Exit Session.\nChoice> ");
	}
	/**
	 * Returns the Label followed by the prompt followed by the message.
	 * 
	 * @return
	 * 	the Label followed by the prompt followed by the message.
	 */
	public String toString(){
		String output = "";
		output += "Label: " + getLabel();
		output += "\nPrompt: " + getPrompt();
		output += "\nMessage: " + getMessage() + "\n";
		return output;
	}
}