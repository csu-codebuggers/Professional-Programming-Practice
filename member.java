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
	
	private Map<Integer, loan> LNS;

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lastName = lastName; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		this.firstName = firstName; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		this.emailId = email; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		this.phoneNo = phoneNo; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		this.id = id; // Changed the variable name to lowercase (camelcase) By Sudeep Maharjan
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(id).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(email).append("\n")
		  .append("  Phone: ").append(phoneNo)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines))
		  .append("\n");
		
		for (Loan loan : LNS.values()) {//Changed the class name to Uppercase and variable name to lowercase
			sb.append(loan).append("\n");
		}		  
		return sb.toString();
	}

	
	public int GeT_ID() {
		return ID;
	}

	
	public List<loan> GeT_LoAnS() {
		return new ArrayList<loan>(LNS.values());
	}

	
	public int Number_Of_Current_Loans() {
		return LNS.size();
	}

	
	public double Fines_OwEd() {
		return fines;
	}

	
	public void Take_Out_Loan(loan loan) {
		if (!LNS.containsKey(loan.ID())) {
			LNS.put(loan.ID(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String Get_LastName() {
		return lastName;
	}

	
	public String Get_FirstName() {
		return firstName;
	}


	public void Add_Fine(double fine) {
		fines += fine;
	}
	
	public double Pay_Fine(double AmOuNt) {
		if (AmOuNt < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (AmOuNt > fines) {
			change = AmOuNt - fines;
			fines = 0;
		}
		else {
			fines -= AmOuNt;
		}
		return change;
	}


	public void dIsChArGeLoAn(loan LoAn) {
		if (LNS.containsKey(LoAn.ID())) {
			LNS.remove(LoAn.ID());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
