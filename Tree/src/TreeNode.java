/**
 * The <code>TreeNode</code> is a node of the tree which has label, message
 * and prompt.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #4 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 8th, 2017
 */
public class TreeNode {
	// Children of this node
	private TreeNode left;
	private TreeNode middle;
	private TreeNode right;
	// The "name" of this node. It will be used when constructing the <code>Tree</code>.
	private String label;
	// Message displayed to the screen.
	private String message;
	// Prompt displayed to the screen as a possible answer to a question.
	private String prompt;

	/**
	 * Empty constructor which declares a new <code>TreeNode</code> object.
	 * This is not used in this homework.
	 */
	public TreeNode(){
		this.label = null;
		this.message = null;
		this.prompt = null;
		this.left = null;
		this.middle = null;
		this.right = null;
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
	public TreeNode(String label, String prompt, String message){
		this.label = label;
		this.message = message;
		this.prompt = prompt;
		this.left = null;
		this.middle = null;
		this.right = null;
	}

	// Mutators
	public void setMiddle(TreeNode middle){
		this.middle = middle;
	}
	public void setLeft(TreeNode left){
		this.left = left;
	}
	public void setRight(TreeNode right){
		this.right = right;
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
	public TreeNode getMiddle(){
		return this.middle;
	}
	public TreeNode getLeft(){
		return this.left;
	}
	public TreeNode getRight(){
		return this.right;
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
		if(left != null)
			left.preorder();
		if(middle != null)
			middle.preorder();
		if(right != null)
			right.preorder();
	}

	/**
	 * Checks if this <code>TreeNode</code> has any children.
	 *
	 * @return
	 * 	True if it is a leaf, false otherwise.
	 */
	public boolean isLeaf(){
		if(this.left == null && this.middle == null && this.right == null)
			return true;
		return false;
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
		if(left!=null)
			System.out.println("1 " + left.prompt);
		if(middle!=null)
			System.out.println("2 " + middle.prompt);
		if(right!=null)
			System.out.println("3 " + right.prompt);
		// quit & choice
		System.out.print("0 Exit Session.\nChoice> ");
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
