/**
 * The <code>ItemInfo</code> class contains information about a specific item
 * that can or has been sold in a given department store.
 *
 * Each attribute is checked in the <code> DepartmentStore </code> class when
 * the user passes it in.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #2 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		July 25th, 2017
 */
public class ItemInfo {
	/**
	 *  Product name
	 */
	private String name;
	/**
	 *  Product price
	 */
	private double price;
	/**
	 * Encodes the radio frequency for scanning the item.
	 */
	private String rfidTagNumber;
	/**
	 * Encodes the original shelf position of the item.
	 */
	private String originalLocation;
	/**
	 * Represents the location of the item at the current time.
	 */
	private String currentLocation;

	/**
	 * Construct an instance of the <code>ItemInfo</code> with the given
	 * parameters.
	 *
	 * @param name
	 * 	The product name.
	 * @param price
	 * 	The product price
	 * @param rfidTagNumber
	 * 	Encodes the radio frequency for scanning the item
	 * @param originalLocation
	 * 	Encodes the original shelf position of the item.
	 * @param currentLocation
	 * 	Encodes the location of the item at the current time.
	 *
	 * <dt><b>Precondition:</b><dd>
	 * 	rfidTagNumber is exactly 9 hexi-decimal characters.
	 * 	originalLocation starts with s followed by exactly 5 digits.
	 * 	currentLocation starts with s followed by exactly 5 digits or
	 * 		starts with c followed by exactly 3 letters or is "out."
	 */
	public ItemInfo(String name, double price, String rfidTagNumber,
			String originalLocation, String currentLocation){
		this.name = name;
		this.price = price;
		this.rfidTagNumber = rfidTagNumber;
		this.originalLocation = originalLocation;
		this.currentLocation = currentLocation;

	}

	/**
	 * Mutator. Returns name of the product.
	 *
	 * @param name
	 * 	The name of the product to be changed to.
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Mutator. Returns price of the product.
	 *
	 * @param price
	 * 	The price of the product to be changed to.
	 */
	public void setPrice(double price){
		this.price = price;
	}
	/**
	 * Mutator. Sets rfidTagNumber.
	 *
	 * @param rfidTagNumber
	 * 	Encodes the radio frequency for scanning the item
	 *
	 * <dt><b>Precondition:</b><dd>
	 * 	rfidTagNumber is exactly 9 hexi-decimal characters.
	 */
	public void setRfidTagNumber(String rfidTagNumber){
		this.rfidTagNumber = rfidTagNumber;
	}
	/**
	 * Mutator. Sets originalLocation.
	 *
	 * calls the setCurrentLocation to set the currentLocation to
	 * originalLocation.
	 *
	 * @param originalLocation
	 * 	Encodes the original shelf position of the item.
	 *
	 * <dt><b>Precondition:</b><dd>
	 * 	originalLocation starts with s followed by exactly 5 digits.
	 */
	public void setOriginalLocation(String originalLocation){
		this.originalLocation = originalLocation;
		this.setCurrentLocation(originalLocation);
	}
	/**
	 * Mutator. Sets currentLocation.
	 *
	 * @param currentLocation
	 * 	Represents the location of the item at the current time.
	 *
	 * <dt><b>Precondition:</b><dd>
	 * 	currentLocation starts with s followed by exactly 5 digits or
	 * 		starts with c followed by exactly 3 letters or is "out."
	 */
	public void setCurrentLocation(String currentLocation){
		this.currentLocation = currentLocation;
	}
	/**
	 *  Accessor. Returns the name of the product.
	 *
	 * @return
	 * 	name of the product.
	 */
	public String getName(){
		return this.name;
	}
	/**
	 *  Accessor. Returns the price of the product.
	 *
	 * @return
	 * 	price of the product.
	 */
	public double getPrice(){
		return this.price;
	}
	/**
	 *  Accessor. Returns the radio frequency of the product.
	 *
	 * @return
	 * 	radio frequency
	 */
	public String getRfidTagNumber(){
		return this.rfidTagNumber;
	}
	/**
	 * Accessor. Returns the original shelf position of the item.
	 *
	 * @return
	 * 	original shelf position of the item.
	 */
	public String getOriginalLocation(){
		return this.originalLocation;
	}
	/**
	 * Accessor. Returns the location of the item at the current time.
	 *
	 * @return
	 * 	location of the item at the current time.
	 */
	public String getCurrentLocation(){
		return this.currentLocation;
	}
	/**
	 * Returns the String represented by this <code>ItemInfo</code> object,
	 * which is a neatly formatted table.
	 *
	 * @return
	 * 	Returns the string Representation of this <code>ItemInfo</code> object.
	 */
	public String toString(){
		return String.format("%-15s %-15s %-15s %-15s %-6.2f", this.name,
				this.rfidTagNumber, this.originalLocation,
				this.currentLocation, this.price );
	}
}
