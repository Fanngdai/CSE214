/**
 * The <code>Mailbox</code> represents an email box and contains all the folders
 * and remaining logic.
 *
 * Note this is NOT where the main method is. The main method is at
 * <code>Mail</code> class. This is to prevent the class being too long.
 *
 * @author
 * 		Fanng Dai, SBU ID#
 * 		Fanng.dai@stonybrook.edu
 * <dt><b>Assignment:</b><dd>
 * 		Homework #5 for CSE 214, Summer 2017
 * <dt><b>Date:</b><dd>
 * 		August 14th, 2017
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Mailbox implements Serializable{
	/**
	 * Stores the inbox, which is a special folder which should never be
	 * allowed to be deleted or renamed. All new email go here.
	 */
	private Folder inbox = new Folder("Inbox");
	/**
	 * Stores the trash, which is a special folder which should never be
	 * allowed to be deleted or renamed. When an email is deleted, it is moved
	 * here.
	 */
	private Folder trash = new Folder("Trash");
	/**
	 * Stores all of the custom folders contained in the mailbox.
	 * (It can be any data collection, ArrayList is onlu an example.)
	 */
	private ArrayList<Folder> folders = new ArrayList<Folder>();

	/**
	 *
	 */
	public Mailbox(){
		folders.add(inbox);
		folders.add(trash);
	}

	public void setInbox(Folder inbox){
		this.inbox = inbox;
	}
	public void setTrash(Folder trash){
		this.trash = trash;
	}
	public void setfolder(ArrayList<Folder> folders){
		this.folders = folders;
	}

	public Folder getInbox(){
		return this.inbox;
	}
	public Folder getTrash(){
		return this.trash;
	}
	/**
	 * Gets the folder in folders.
	 * @param name
	 * 	Name of the folder
	 *
	 * @return
	 * 	<code>Folder</code> with the given parameter name
	 */
	public Folder getFolder(String name){
		for(int i=0; i<folders.size(); i++){
			if(folders.get(i).getName().equalsIgnoreCase(name)){
				return folders.get(i);
			}
		}
		System.out.println("\nFolder does not exist.");
		return null;
	}

	public int folderSize(){
		return this.folders.size();
	}
	/**
	 * Adds the given folder to the list of custon folders.
	 * Folders cannot have duplicate names.
	 * @param folder
	 * 	The folder to add into the mailbox
	 */
	public void addFolder(Folder folder){
		for(int i=0; i<folders.size(); i++)
			if(folders.get(i).getName().equalsIgnoreCase(folder.getName())){
				System.out.println("\nThat name is already in use.");
				return;
			}
		folders.add(folder);
	}

	/**
	 * Deletes a folder in the list.
	 * The inbox and trash folders cannot be deleted.
	 *
	 * @param name
	 * 	The name of the folder to be deleted.
	 */
	public void deleteFolder(String name){
		if(name.equalsIgnoreCase("Inbox") || name.equalsIgnoreCase("Trash")){
			System.out.println("\n" + name + " cannot be deleted.\n");
			return;
		}
		for(int i = 0; i < folders.size(); i++)
			if(folders.get(i).getName().equalsIgnoreCase(name)){
				folders.remove(i);
				System.out.println("\n" + name + " has been successfully removed.\n");
				return;
			}
		System.out.println("\n" + name + " does not exist.\n");
	}

	/**
	 * Gets the user input on the contents of the email and adds it to the inbox
	 * To, cc and bcc cannot all be left empty.
	 * Subject cannot be left empty. Body can be null
	 *
	 * Reset the time to when it was sent. (Everything filled out)
	 */
	public void composeEmail(){
		Email temp = new Email();
		Scanner stdin = new Scanner(System.in);
		boolean flag = false;

		do{
			System.out.print("\nEnter recipient (To): ");
			temp.setTo(stdin.nextLine().trim());
			System.out.print("\nEnter carbon copy recipients (CC): ");
			temp.setCc(stdin.nextLine().trim());
			System.out.print("\nEnter blind carbon copy recipients (BCC): ");
			temp.setBcc(stdin.nextLine().trim());
			if(temp.recipientEmpty()){
				System.out.println("\nPlease enter a recipient.");
			}
			else{
				flag = true;
			}
		} while (flag == false);

		do{
			System.out.print("\nEnter subject line: ");
			temp.setSubject(stdin.nextLine().trim());
			if(!temp.getSubject().isEmpty()){
				flag = false;
			}
			else{
				System.out.println("\nSubject cannot be left empty.");
			}
		} while(flag == true);

		System.out.print("\nEnter body: ");
		temp.setBody(stdin.nextLine().trim());

//		if(temp.getTo()==null && temp.getCc()==null && temp.getBcc()==null){
//			System.out.println("No address to send to. Email deleted.");
//		}

		GregorianCalendar cal =  new GregorianCalendar();
		temp.setTimestamp(cal);

		inbox.addEmail(temp);
		System.out.println("\nEmail successfully added to Inbox.\n");
	}

	/**
	 * Moves the Email to the trash. (This method shouldn't remove any emails
	 * from folders, the method removeEmail should be called and then deletedEmail
	 * is called on the result)
	 *
	 * @param email
	 * 	email to delete and move.
	 */
	public void deleteEmail(Email email){
		trash.addEmail(email);
	}

	/**
	 * Removes all emails from the trash folder.
	 */
	public void clearTrash(){
		int itemsInTrash = trash.getEmails().size();
		for(int i=0; i<itemsInTrash; i++)
			trash.removeEmail(i);
		System.out.println("\n" + itemsInTrash + " item(s) successfully deleted.\n");
	}

	/**
	 * Takes the given email and puts in the given folder. If the folder cannot
	 * be found, instead move it to the inbox.
	 *
	 * @param email
	 * 	Email to move
	 * @param target
	 * 	Folder to move to
	 */
	public void moveEmail(Email email, Folder target){
//		try{
			if(target == null){
				target = inbox;
			}
			target.addEmail(email);
			System.out.println("\n\"" + email.getSubject() + "\" successfully moved to "
					+ target.getName() + ".");
//		}
//		catch(NullPointerException ex){
//			System.out.println("Folder does not exist.");
//		}
	}

	/**
	 * Formats the Mailbox so it prints each folder within the mailbox such
	 * that each folder gets its own line.
	 */
	public String toString(){
		String output = "";
		for(int i=0; i<folders.size(); i++)
			output += folders.get(i).getName() + "\n";
		return output;
	}
}
