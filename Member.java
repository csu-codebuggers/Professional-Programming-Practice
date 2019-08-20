import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable { // Changed the class name to uppercase

	private String lastName; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
	private String firstName;// Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
	private String email;// Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
	private int phoneNo;// Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
	private int id;// Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
	private double fines;// Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
	
	private Map<Integer, loan> loans;// Changed the variable name to lowercase (camelcase) By Sudeep Maharjan

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lastName = lastName; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		this.firstName = firstName; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		this.emailId = email; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		this.phoneNo = phoneNo; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		this.id = id; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		
		this.loans = new HashMap<>();// Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  Member:  ").append(id).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(email).append("\n")
		  .append("  Phone: ").append(phoneNo)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines))
		  .append("\n");
		
		for (Loan loan : loans.values()) {//Changed the class name to Uppercase and variable name to lowercase
			sb.append(loan).append("\n");
		}		  
		return sb.toString();
	}

	
	public int getId() { // Changed the method name to meaningful name
		return id;
	}

	
	public List<loan> getLoans() {// Changed the method name to meaningful name
		return new ArrayList<loan>(loans.values());
	}

	
	public int numberOfCurrentLoans() {// Changed the method name to meaningful name
		return loans.size();
	}

	
	public double finesOwed() {// Changed the method name to meaningful name
		return fines;
	}

	
	public void takeOutLoan(Loan loan) {// Changed the method name to meaningful name By Sudeep Maharjan
		if (!loans.containsKey(loan.ID())) {
			loans.put(loan.ID(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {// Changed the method name to meaningful name
		return lastName;
	}

	
	public String getFirstName() {// Changed the method name to meaningful name
		return firstName;
	}


	public void addFine(double fine) {// Changed the method name to meaningful name
		fines += fine;
	}
	
	public double payFine(double amount) {// Changed the method name to meaningful name
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) {
			change = amount - fines; //Changed the variable name Amount to amount by Sudeep Maharjan
			fines = 0;
		}
		else {
			fines -= amount;
		}
		return change;
	}


	public void dischargeLoan(Loan loan) {// Changed the method name to meaningful name
		if (loans.containsKey(loan.ID())) {
			loans.remove(loan.ID());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
