/**
 * The <code>ItemListNode</code> class contains a refernce to an ItemInfo object as
 * well as two other ItemINfoNode objects, referred to as prev and next.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #2 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 25th, 2017
 */
public class ItemInfoNode {
	/**
	 * An <code>ItemInfo</code> object
	 */
	private ItemInfo info;
	/**
	 * The node after this node.
	 */
	private ItemInfoNode next = null;
	/**
	 * The node before this node.
	 */
	private ItemInfoNode prev = null;

	/**
	 * Construct an instance of the <code>ItemInfoNode</code> with default
	 * values.
	 */
	public ItemInfoNode(){
		this.next = null;
		this.prev = null;
	}
	/**
	 * Constructor that starts off with one node.
	 *
	 * @param info
	 * 	An <code>ItemInfo</code> object which is the first node.
	 */
	public ItemInfoNode(ItemInfo info){
		this.info = info;
		this.next = null;
		this.prev = null;
	}
	/**
	 * Mutator. Sets info.
	 *
	 * @param info
	 * 	The information of <code>ItemInfo</code> name, price, rfidTagNumber,
	 * 	originalLocation, and currentLocation.
	 */
	public void setInfo(ItemInfo info){
		this.info = info;
	}
	/**
	 * Mutator. Sets next.
	 *
	 * @param node
	 * 	<code>ItemInfoNode</code> that refers to the node after this node.
	 */
	public void setNext(ItemInfoNode node){
		this.next = node;
	}
	/**
	 * Mutator. Sets prev.
	 *
	 * @param node
	 * 	<code>ItemInfoNode</code> that refers to the node before this node.
	 */
	public void setPrev(ItemInfoNode node){
		this.prev = node;
	}
	/**
	 * Accessor. Returns an <code>ItemInfo</code> object.
	 *
	 * @return
	 * 	<code>ItemInfo</code> object
	 */
	public ItemInfo getInfo(){
		return this.info;
	}
	/**
	 * Accessor. Returns an <code>ItemInfoNode</code> of the node after this
	 * node.
	 *
	 * @return
	 * 	<code>ItemInfoNode</code> of the node after this node.
	 */
	public ItemInfoNode getNext(){
		return this.next;
	}
	/**
	 * Accessor. Returns an <code>ItemInfoNode</code> of the node before this
	 * node.
	 *
	 * @return
	 * 	<code>ItemInfoNode</code> of the node before this node.
	 */
	public ItemInfoNode getPrev(){
		return this.prev;
	}
	/**
	 * Returns the String represented by this <code>ItemInfoNode</code> object,
	 * which is a neatly formatted table.
	 *
	 * @return
	 * 	Returns the string Representation of this <code>ItemInfoNode</code>
	 * 	object.
	 */
	public String toString(){
		return String.format("%-15s %-15s %-15s %-15s %-6.2f", info.getName(),
				info.getRfidTagNumber(), info.getOriginalLocation(),
				info.getCurrentLocation(), info.getPrice());
	}
}
