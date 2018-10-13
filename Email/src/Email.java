/**
 * The <code>Email</code> class contains everything within an email such as who
 * the email is to, the subject, body, and the time sent.
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
import java.util.Comparator;
import java.util.GregorianCalendar;

public class Email implements Serializable, Comparable{
	// Look at constructor for more details on attributes
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String body;
	private GregorianCalendar timestamp;

	/**
	 * An empty constructor which sets everything to null and the current time
	 * the email was asked to be made.
	 */
	public Email(){
		this.to = null;
		this.cc = null;
		this.bcc = null;
		this.subject = null;
		this.body = null;
		GregorianCalendar cal =  new GregorianCalendar();
		this.timestamp = cal;
	}

	/**
	 * A constructor which sets the attributes of <code>Email</code> accordingly.
	 *
	 * @param to
	 * 	"to" field
	 * @param cc
	 * 	"cc" field
	 * @param bcc
	 * 	"bcc" field
	 * @param subject
	 * 	"subject" field, the subject of the <code>Email</code>
	 * @param body
	 * 	"body" field which stores all of the test in the email's body.
	 * @param timepstamp
	 * 	The time the email was asked to be made.
	 */
	public Email(String to, String cc, String bcc, String subject, String body,
			GregorianCalendar timepstamp){
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.body = body;
		this.timestamp = timestamp;
	}

	// Mutators
	public void setTo(String to){
		this.to = to;
	}
	public void setCc(String cc){
		this.cc= cc;
	}
	public void setBcc(String bcc){
		this.bcc = bcc;
	}
	public void setSubject(String subject){
		this.subject = subject;
	}
	public void setBody(String body){
		this.body = body;
	}
	public void setTimestamp(GregorianCalendar timestamp){
		this.timestamp = timestamp;
	}

	// Accessors
	public String getTo(){
		return this.toString();
	}
	public String getCc(){
		return this.cc;
	}
	public String getBcc(){
		return this.bcc;
	}
	public String getSubject(){
		return this.subject;
	}
	public String getBody(){
		return this.body;
	}
	public GregorianCalendar getTimestamp(){
		return this.timestamp;
	}

	/**
	 * Checks if the recipients are empty.
	 * @return
	 * 	True if to & cc & bcc are all empty
	 * 	False otherwise.
	 */
	public boolean recipientEmpty(){
		if(to.isEmpty() && cc.isEmpty() && bcc.isEmpty())
			return true;
		return false;
	}
	/**
	 * Compares the subject of two Email objects.
	 * If the passed in parameter is not an instance of <code>Email</code> it
	 * would return -2. (Which is to be held in the class calling this method.)
	 * Otherwise, it will compare the two strings.
	 *
	 * @param o
	 * 	An object which should be an <code>Email</code> object.
	 *
	 * @return
	 * 	0 if the two strings are the same.
	 * 	-1 if a is smaller than b.
	 *  1 is a is larger than b.
	 *  -2 if <code>Object</code> is not an instanceOf <code>Email</code>
	 */
	public int compareTo(Object o){
		if(o instanceof Email){
			Email test = (Email) o;
			String a = this.subject;
			String b = test.getSubject();
			int compare = a.compareTo(b);
			if(compare > 0)
				return 1;
			else if(compare < 0)
				return -1;
			else
				return 0;
		}
		// This should never be reached in this program b/c we compare within
		// an arraylist filled with Emails only.
		return -2;
	}

	/**
	 * Checks if two emails are equal.
	 *
	 * @param email
	 * 	The email which we are checking if it is equal
	 *
	 * @return
	 * 	True if this <code>Email</code> is the same as the parameter <code>Email</code>.
	 */
	public boolean equals(Email email){
		return(email.getBcc().equalsIgnoreCase(bcc) && email.getBody().equalsIgnoreCase(body)
				&& email.getCc().equalsIgnoreCase(cc) && email.getSubject().equalsIgnoreCase(subject)
				&& email.getTimestamp().equals(timestamp) && email.getTo().equalsIgnoreCase(to));
	}

	/**
	 * Prints the email accordingly in a formatted way.
	 */
	public String toString(){
		return "\nTo: " + to + "\nCC: " + cc + "\nBCC: " + bcc + "\nSubject: " + subject + "\n" + body;
	}
}

/**
 * compares two emails by date ascending order.
 */
class DateAscending implements Comparator{
	public int compare(Object o1, Object o2){
		Email email1 = (Email)o1;
		Email email2 = (Email)o2;
		return (email1.getTimestamp().compareTo(email2.getTimestamp()));
	}
}
/**
 * compares two emails by date descending order.
 */
class DateDescending implements Comparator{
	public int compare(Object o1, Object o2){
		Email email1 = (Email)o1;
		Email email2 = (Email)o2;
		return -(email1.getTimestamp().compareTo(email2.getTimestamp()));
	}
}
/**
 * compares two emails by subject ascending order.
 */
class SubjectAscending implements Comparator{
	public int compare(Object o1, Object o2){
		Email email1 = (Email)o1;
		Email email2 = (Email)o2;
		return (email1.getSubject().compareTo(email2.getSubject()));
	}
}
/**
 * compares two emails by subject descending order.
 */
class SubjectDescending implements Comparator{
	public int compare(Object o1, Object o2){
		Email email1 = (Email)o1;
		Email email2 = (Email)o2;
		return -(email1.getSubject().compareTo(email2.getSubject()));
	}
}
