/**
 * The <code>ItemList</code> is a list of ItemInfo nodes which is stored in a
 * list. This class will get the link within the nodes. Remove if needed and
 * add if needed.
 *
 * The complexity for insertInfo, removeAllPurchased, moveItem, printAll,
 * printByLocation, cleanStore and checkOut are all O(n) because you have
 * to loop through the whole list for the most part. (Worst case)
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #2 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 25th, 2017
 */
public class ItemList{
	/**
	 * Points to the first node in the linked list.
	 */
	private ItemInfoNode head;
	/**
	 * Points to the last node in the linked list.
	 */
	private ItemInfoNode tail;
	/**
	 * Points to the current node in the linked list.
	 */
	private ItemInfoNode cursor;

	/**
	 * Constructor for ItemList.
	 * Set all attributes to default.
	 */
	public ItemList(){
		head = null;
		tail = null;
		cursor = null;
	}
	/**
	 * Returns the size of the list.
	 * Not needed for this homework.
	 *
	 * @return
	 * 	size of list
	 */
	public int listLength(){
		ItemInfoNode nodePtr = head;
		int answer = 0;
		while (nodePtr != null){
			answer++;
			nodePtr = nodePtr.getNext();
		}
		return answer;
	}
	public boolean isEmpty(){
		return (head == null);
	}
	/**
	 * Prints the header of the table
	 */
	public void header(){
		System.out.println(String.format("%-15s %-15s %-15s %-15s %-6s", "",
				"", "Original", "Current", ""));
		System.out.println(String.format("%-15s %-15s %-15s %-15s %-6s", "Item Name",
				"   RFID", "Location", "Location", "Price"));
		System.out.println(String.format("%-15s %-15s %-15s %-15s %-6s", "---------",
				"---------", "---------", "---------", "------"));
	}
	/**
	 * The header of the table for gui.
	 *
	 * @return
	 * 	the header of the list in a neatly formatted way
	 */
	public String headerGUI(){
		String output = String.format("%-20s %-23s %-17s %-20s %-6s", "",
				"", "Original", "Current", "") + "\n";
		output += String.format("%-15s %-15s %-15s %-15s %-6s", "Item Name",
				"   RFID", "Location", "Location", "Price") + "\n";
		output += String.format("%-15s %-15s %-15s %-15s %-6s", "---------",
				"---------", "---------", "---------", "------") + "\n\n";
		return output;
	}
	/**
	 * Accessor. Returns the cursor information.
	 * If the cursor is null, meaning the list is empty, it will throw an
	 * exception.
	 *
	 * @return
	 * 	The ItemInfo at the current cursor.
	 */
	public ItemInfo getCursor(){
		try{
			if(cursor == null){
				throw new IllegalArgumentException();
			}
		}
		catch(IllegalArgumentException ex){
			System.out.println("Empty Cursor.");
		}
		return cursor.getInfo();
	}

	/**
	 * Inserts the info into the list in its correct position based on its
	 * rfidTagNumber.
	 *
	 * Set the new information as a node. Then check if the newItem is null.
	 * Go through all the nodes and check if that nodes rfidTag matches or is
	 * greater than the passed in rfidTag. If it is, put the new node before
	 * it. Otherwise, set it after the list which is the tail.
	 *
	 * In this method, there is setting up the list, adding before the cursor,
	 * and adding after the cursor.
	 *
	 * The complexity is O(n).
	 *
	 * @param name
	 * 	The name of the <code>ItemInfo</code> object.
	 * @param rfidTag
	 * 	The radio frequency of the <code>ItemInfo</code> object.
	 * @param price
	 * 	The price of the <code>ItemInfo</code> object.
	 * @param initPosition
	 * 	The initial position of <code>ItemInfo</code> object.
	 */
	public void insertInfo(String name, String rfidTag, double price, String initPosition){
		ItemInfo newItem = new ItemInfo(name, price, rfidTag, initPosition, initPosition);
		ItemInfoNode newNode = new ItemInfoNode(newItem);

		boolean flag = true;

		// If list is empty. head = tail = cursor = newNode
		if(head == null){
			head = newNode;
			tail = newNode;
			cursor = newNode;
		}
		// Place in front of head. Because list is sorted and rfidTag is smaller than head
		else if(head.getInfo().getRfidTagNumber().compareTo(rfidTag) >= 0){
			// nothing - newNode - n setting the n to be after newNode
			newNode.setNext(head);
			head.setPrev(newNode);
			newNode.setPrev(null);
			cursor = newNode;
			// If nothing-c then the head is cursor.
			head = cursor;
		}
		// rfidtag is greater than tail rfidTag
		else if(tail.getInfo().getRfidTagNumber().compareTo(rfidTag) <= 0){
			// p-c-n set p to the previous node
			newNode.setPrev(tail);
			tail.setNext(newNode);
			// p-c-n n = null because nothing after c
			newNode.setNext(null);
			tail = newNode;
			// Now the current new node we want to add is equal to cursor
			cursor = newNode;
		}
		// rfid is somewhere in the middle. put in front
		else{
			// For loop goes through the list
			for(cursor = head; cursor != null && flag; cursor = cursor.getNext()){
				if(cursor.getInfo().getRfidTagNumber().compareTo(rfidTag) >= 0){
					// p- newNode - n setting the n to be after newNode
					newNode.setNext(cursor);
					cursor.getPrev().setNext(newNode);
					// p - newNode - n. setting the p to be before New Node
					newNode.setPrev(cursor.getPrev());
					cursor.setPrev(newNode);
					cursor = newNode;
					flag = false;
				}
			}
		}
	}

	/**
	 * Removes all nodes in the list that have current location listed as "out"
	 * and displays a list of all items that have been removed in this fashion.
	 *
	 * The complexity is O(n).
	 */
	public void removeAllPurchased(){
		System.out.println("\nThe following item(s) have been removed from the system: \n");
		header();
		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			if(cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")){
				System.out.println(cursor.toString());
				// If cursor is the only node left
				if(cursor.getPrev() == null && cursor.getNext() == null){
					cursor = null;
				}
				// cursor is the head(cursor.getPrev() == null) You must replace head to the next node.
				else if(cursor == head){
					// Set the current node to null
					cursor.getNext().setPrev(null);
					// Set the next cursor to head
					head = cursor.getNext();
					cursor = head;
				}
				// cursor is the tail.(cursor.getNext() == null) You must replace tail with the prev node.
				else if(cursor == tail){
					cursor.getPrev().setNext(null);
					cursor = cursor.getPrev();
					tail = cursor;
				}
				// If cursor is n-c-p which n & p != null
				else{
					cursor.getNext().setPrev(cursor.getPrev());
					cursor.getPrev().setNext(cursor.getNext());
					cursor = cursor.getNext();
				}
			}
		}
	}
	/**
	 * Does the same as removeAllPurchase method but for the gui
	 */
	public String removeAllPurchasedGUI(){
		String output = headerGUI();

		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			if(cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")){
				output += cursor.toString() + "\n";
				if(cursor.getPrev() == null && cursor.getNext() == null){
					cursor = null;
				}
				else if(cursor == head){
					cursor.getNext().setPrev(null);
					head = cursor.getNext();
					cursor = head;
				}
				else if(cursor == tail){
					cursor.getPrev().setNext(null);
					cursor = cursor.getPrev();
					tail = cursor;
				}
				else{
					cursor.getNext().setPrev(cursor.getPrev());
					cursor.getPrev().setNext(cursor.getNext());
					cursor = cursor.getNext();
				}
			}
		}
		return output;
	}
	/**
	 * Moves an item with a given rfidTagNumber from a source location to a
	 * destination location.
	 *
	 * The complexity is O(n).
	 *
	 * @param rfidTag
	 * 	Radio frequency
	 * @param source
	 * 	Location to check for RFRID
	 * @param dest
	 * 	Move item to this location
	 * @throws
	 * 	Destination is not in the correct format. (cart, shelf, "out").
	 * 	If source is equal to "out".
	 *
	 * @return
	 * 	True if an item of the given rfidTagNumber was found at the given
	 * 	source location, and moved successfully to dest. False otherwise.
	 */
	public boolean moveItem(String rfidTag, String source, String dest){
		try{
			if(source.equalsIgnoreCase("out") || dest.equalsIgnoreCase("out"))
				throw new IllegalLocationException();
			for(cursor = head; cursor != null; cursor = cursor.getNext()){
				// If you find the rfidTag and the location. Move item
				if(cursor.getInfo().getCurrentLocation().equalsIgnoreCase(source)
						&& cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTag)){
					// cursor.getInfo().setOriginalLocation(source);	// If you want to change originalLocation
					cursor.getInfo().setCurrentLocation(dest);
					return true;
				}
			}
		}
		catch(IllegalLocationException ex){
			ex.illegalSource();
		}
		return false;
	}
	/**
	 * Prints a neatly formatted list of all items currently in list.
	 *
	 * The complexity is O(n).
	 */
	public void printAll(){
		header();
		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			System.out.println(cursor.toString());
		}
		System.out.println();
	}
	/**
	 * Does the same as printAll but for GUI
	 */
	public String printAllGUI(){
		String output = headerGUI();
		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			output += cursor.toString() + "\n";
		}
		return output;
	}
	/**
	 * Prints a neatly formatted list of all items in a specified current
	 * location.
	 *
	 * The complexity is O(n).
	 *
	 * @param location
	 * 	Location to look for item
	 */
	public void printByLocation(String location){
		header();
		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			if(location.equalsIgnoreCase(cursor.getInfo().getCurrentLocation())){
				System.out.println(cursor);
			}
		}
	}

	/**
	 * Does the same as printByLocation but instead, it returns a String which
	 * will be displayed in the GUI
	 *
	 * @param location
	 * 	Location to look for item
	 *
	 * @return
	 * 	A string of the header and all the items in this location
	 */
	public String printByLocationGUI(String location){
		String output = headerGUI();
		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			if(location.equalsIgnoreCase(cursor.getInfo().getCurrentLocation())){
				output += cursor + "\n";
			}
		}
		return output;
	}
	/**
	 * Prints a neatly formatted list of all items with the same rFidTagNumber
	 *
	 * The complexity is O(n).
	 *
	 * @param location
	 * 	Location to look for item
	 */
	public void printByRFid(String rfidTag){
		header();
		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			if(rfidTag.equalsIgnoreCase(cursor.getInfo().getRfidTagNumber())){
				System.out.println(cursor);
			}
		}
	}

	public String printByRFidGUI(String rfidTag){
		String output = headerGUI();
		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			if(rfidTag.equalsIgnoreCase(cursor.getInfo().getRfidTagNumber())){
				output += cursor + "\n";
			}
		}
		return output;
	}
	/**
	 * Take every item that is currently in the store and on the wrong shelf
	 * and places it back where it belongs (original location). Items that are
	 * "out" or currently in a cart are not affected by this command.
	 * Display a table for all out of place items moved in this fashion.
	 *
	 * The complexity is O(n).
	 */
	public void cleanStore(){
		ItemList temp = new ItemList();

		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			String cLocation = cursor.getInfo().getCurrentLocation();
			String oLocation = cursor.getInfo().getOriginalLocation();
			// If the current location is on a shelf. and it is not at it's original location
			if(cLocation.charAt(0) == 's' && !cLocation.equalsIgnoreCase(oLocation) ){
				temp.insertInfo(cursor.getInfo().getName(), cursor.getInfo().getRfidTagNumber()
						, cursor.getInfo().getPrice(), oLocation);
				temp.getCursor().setCurrentLocation(cursor.getInfo().getCurrentLocation());
				cursor.getInfo().setCurrentLocation(oLocation);
			}
		}
		System.out.println("The following item(s) have been moved back to their original locations:\n");
		temp.printAll();
	}

	/**
	 * Does the same as cleanStore method but is used for GUI so it does not
	 * print at the console but instead in the GUI.
	 */
	public String cleanStoreGUI(){
		ItemList temp = new ItemList();
		String output = "";

		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			String cLocation = cursor.getInfo().getCurrentLocation();
			String oLocation = cursor.getInfo().getOriginalLocation();
			// If the current location is on a shelf. and it is not at it's original location
			if(cLocation.charAt(0) == 's' && !cLocation.equalsIgnoreCase(oLocation) ){
				temp.insertInfo(cursor.getInfo().getName(), cursor.getInfo().getRfidTagNumber()
						, cursor.getInfo().getPrice(), oLocation);
				temp.getCursor().setCurrentLocation(cursor.getInfo().getCurrentLocation());
				cursor.getInfo().setCurrentLocation(oLocation);
			}
		}
		output += "The following item(s) have been moved back to their original locations:\n";
		output += temp.printAllGUI();
		return output;
	}
	/**
	 * Goes through a given cart and checks out each item (changes its location
	 * to "out"). A neatly formatted list of the items checked out is to be
	 * printed and it must be sorted in order of rfidTagNumber.
	 *
	 * The complexity is O(n).
	 *
	 * @throws
	 * 	Invalid cart numbers
	 *
	 * @return
	 * 	Total cost for the items that were in the cart.
	 */
	public double checkOut(String cartNumber){
		double total = 0;
		//		ItemList temp = new ItemList();
		header();

		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			// If you find the rfidTag and the location. Move item
			if(cursor.getInfo().getCurrentLocation().equalsIgnoreCase(cartNumber)){
				total += cursor.getInfo().getPrice();
				System.out.println(cursor);
				//				temp.insertInfo(cursor.getInfo().getName(), cursor.getInfo().getRfidTagNumber()
				//						, cursor.getInfo().getPrice(), cursor.getInfo().getOriginalLocation());
				cursor.getInfo().setCurrentLocation("out");
				//				// Move temp
				//				temp.getCursor().setCurrentLocation(cartNumber);
			}
		}
		//		temp.printAll();

		return total;
	}

	public String checkOutGUI(String cartNumber){
		double total = 0;
		String output = headerGUI();

		for(cursor = head; cursor != null; cursor = cursor.getNext()){
			// If you find the rfidTag and the location. Move item
			if(cursor.getInfo().getCurrentLocation().equalsIgnoreCase(cartNumber)){
				total += cursor.getInfo().getPrice();
				output += cursor + "\n";
				cursor.getInfo().setCurrentLocation("out");
			}
		}
		String answer = "\nThe total cost for all merchandise in cart "
				+ cartNumber.substring(1,4) + " was $" + String.format("%.2f", total);
		return output + answer;
	}
	/**
	 * Compares rfidTagNumber
	 *
	 * @param o
	 * 	The object which we are comparing it's rfidTagNumber to
	 *
	 * @return
	 * 	-1 if a is smaller than b
	 * 	1 if a is larger than b
	 * 	0 if a and b are equal
	 */
	public int compareTo(Object o){
		ItemList test = (ItemList) o;
		String a = this.cursor.getInfo().getRfidTagNumber();
		String b = test.getCursor().getRfidTagNumber();
		if(a.compareTo(b) > 0)
			return 1;
		else if(a.compareTo(b) < 0)
			return -1;
		else
			return 0;
	}
}
