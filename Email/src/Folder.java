/**
 * The <code>Folder</code> represents an email folder and will handle all of the
 * logic for adding and removing emails.
 * It also sorts the emails accordingly.
 * 
 * @author
 * 		Fanng Dai, SBU ID#109684495
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #5 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 14th, 2017
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;


public class Folder implements Serializable{
	// Stores all of the emails contained in this folder.
	private ArrayList<Email> emails;
	// Stores the name of the folder.
	private String name;
	// Stores the current sorting method
	private String currentSortingMethod;

	/**
	 * Empty constructor. Starts the folder fresh
	 */
	public Folder(String name){
		this.emails = new ArrayList<Email>();
		this.name = name;
		this.currentSortingMethod = "DD";
	}
	/**
	 * Constructor that takes in a list of emails and puts it in the folder.
	 * It changes the folder to a certain, given name and sets the
	 * currentSortingMethod to whatever is passed in.
	 * 
	 * @param emails
	 * 	List of emails to put into folder
	 * @param name
	 * 	The name of the folder
	 * @param currentSortingMethod
	 * 	Changes the sorting method
	 */
	public Folder(ArrayList<Email> emails, String name, String currentSortingMethod){
		this.emails = emails;
		this.name = name;
		this.currentSortingMethod = currentSortingMethod;
	}

	// Mutators
	public void setEmails(ArrayList<Email> emails){
		this.emails = emails;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setCurrentSortingMethod(String currentSortingMethod){
		this.currentSortingMethod = currentSortingMethod;
	}

	// Accessors
	public ArrayList<Email> getEmails(){
		return this.emails;
	}
	/**
	 * Checks to make sure the passed in index is valid. Returns an
	 * <code>Email</code> from the emails <code>ArrayList</code> in the
	 * specified position.
	 * 
	 * @param index
	 * 	Location in emails to return the <code>Email</code>
	 * 
	 * @return
	 * 	<code>Email</code> at the specified index.
	 * 	Null if the index is invalid.
	 */
	public Email getEmails(int index){
		if(index < 0 || index > emails.size()-1){
			return null;
		}	
		return this.emails.get(index);
	}
	public String getName(){
		return this.name;
	}
	public String getCurrentSortingMethod(){
		return this.currentSortingMethod;
	}

	/**
	 * Adds an email to the folder according to the current sorting method.
	 * If it is the first email. No sorting required! Otherwise, continue reading
	 * 
	 * @param email
	 * 	the email to add to the folder
	 */
	public void addEmail(Email email){
		if(emails.isEmpty()){
			emails.add(email);
			return;
		}

		emails.add(email);
		int lastIndex = emails.size()-1;
		int j = emails.size()-1;
		
		while(j>0 && email.getTimestamp().compareTo(emails.get(j-1).getTimestamp()) < 0)
			emails.set(j, emails.get(--j));
		emails.set(j, email);
	}

	/**
	 * Removes an email from the folder by index.
	 * 
	 * @param index
	 * 	location of arraylist to remove email
	 * @return
	 * 	The email which was removed
	 */
	public Email removeEmail(int index){
		if(index < -1 || index > emails.size()-1){
			return null;
		}
		return emails.remove(index);
	}

	/**
	 * Sorts the emails in this folder in date ascending order.
	 */
	public void sortByDateAscending(){
		Collections.sort(emails, new DateAscending());
		currentSortingMethod = "DA";
	}
	/**
	 * Sorts the emails in this folder in date descending order.
	 */
	public void sortByDateDescending(){
		Collections.sort(emails, new DateDescending());
		currentSortingMethod = "DD";
	}
	/**
	 * Sorts the emails in this folder in subject ascending order.
	 */
	public void sortBySubjectAscending(){
		Collections.sort(emails, new SubjectAscending());
		currentSortingMethod = "SA";
	}
	/**
	 * Sorts the emails in this folder in subject descending order.
	 */
	public void sortBySubjectDescending(){
		Collections.sort(emails, new SubjectDescending());
		currentSortingMethod = "SD";
	}

	/**
	 * Formats the date so that it looks like the example print out.
	 * 
	 * @param calendar
	 * 	The date which the <code>Email</code> object was made.
	 * 
	 * @return
	 * 	A string of the date which the email was made.
	 */
	public static String dateFormat(GregorianCalendar calendar){
		String hour;
		String date;
		date = (calendar.get(GregorianCalendar.MONTH)+1) + "/" + calendar.get(GregorianCalendar.DAY_OF_MONTH)
		+ "/" + calendar.get(GregorianCalendar.YEAR);
		hour = calendar.get(GregorianCalendar.HOUR) + ":" +
				(calendar.get(GregorianCalendar.MINUTE) < 10? "0" + calendar.get(GregorianCalendar.MINUTE) : calendar.get(GregorianCalendar.MINUTE)) 
				+ (calendar.get(GregorianCalendar.AM_PM) == 0?"AM":"PM");
		return hour + " " + date;
	}

	/**
	 * Checks to see if the Folders are the same.
	 */
	public boolean equals(Object o){
		boolean flag = true;
		if(o instanceof Folder){
			Folder tempFolder = (Folder) o;
			if(tempFolder.getEmails().size() != emails.size()){
				return false;
			}
			for(int i=0; i<emails.size(); i++){
				if(tempFolder.getEmails().get(i).equals(emails.get(i)))
					flag = false;
			}
			return (this.name.equals(tempFolder.getName()) && flag);
		}
		else
			return false;
	}

	/**
	 * Returns a string which has all the emails in this folder.
	 */
	public String toString(){
		if(emails.size() == 0)
			return "\n" + this.name + " is empty.";

		String output = String.format("\n%-6s|%-8s%-11s| %-7s\n", "Index", "", "Time", "Subject");
		for(int i=0; i<35; i++){
			output += "-";
		}
		for(int i=0; i<emails.size(); i++){
			output += String.format("\n  %-4s|  %-17s| %-7s", i+1, dateFormat(emails.get(i).getTimestamp()), emails.get(i).getSubject());
		}
		return output;
	}
}