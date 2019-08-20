import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class loan implements Serializable {
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // Changed the variable name of enum from LOAN_STATE to LoanState
	
	private int id;// Changed the variable name from ID to id
	private Book book;// Change the class name to uppercase(book to Book) and variable name from B to book
	private Member member; // Changed the class name to uppercase(member to Member) and variable name from M to member
	private Date date; //Changed the class name from date to Date
	private LoanState state;//Changed the variable name from LOAN_STATE to LoanState

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.id = loanId;// Changed the variable ID to id By Sudeep Maharjan
		this.book = book;// Changed the variable B to book By Sudeep Maharjan
		this.member = member;// Changed the variable M to member By Sudeep Maharjan
		this.date = dueDate;// Changed the variable D to date By Sudeep Maharjan
		this.state = LoanState.CURRENT;// Changed the enum variable LOAN_STATE to LoanState
	}

	
	public void checkOverDue() {
		if (state == LoanState.CURRENT &&
			Calendar.getInstance().Date().after(date)) { //Changed the variable name from D to date by Sudeep Maharjan and changed the variable name from INSTANCE to getInstance from calender class
			this.state = LoanState.OVER_DUE;//Changed the variable name from LOAN_STATE to LoanState by Sudeep Maharjan			
		}
	}

	
	public boolean overDue() { //Changed the method name from OVer_Due to overDue by Sudeep Maharjan
		return state == LoanState.OVER_DUE; // Changed the variable name from LOAN_STATE to LoanState By Sudeep Maharjan
	}

	
	public Integer getId() { //Changed the method name from ID to getId
		return id; // Changed the variable name from ID to id
	}


	public Date Get_Due_Date() { //Changed the method name Get_Due_Date to getDueDate by Sudeep Maharjan
		return date; // Changed the variable name D to date by Sudeep Maharjan
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(member.getId()).append(" : ")
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n")// Changed the method name of member class and book class by Sudeep maharjan
		  .append("  Book ").append(book.getId()).append(" : " )
		  .append(book.getTitle()).append("\n")
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member getMember() { //Changed the method name from Member to getMember
		return member; //Changed the variable name from M to member by Sudeep Maharjan
	}


	public book getBook() { //Changed the method name from Book to getBook
		return book;//Changed the variable name from M to member by Sudeep Maharjan
	}


	public void discharge() { //Changed the method name from DiScHaRgE to discharge 
		state = LoanState.DISCHARGED;	//Changed the variable name from LOAN_STATE to member by Sudeep Maharjan	
	}

}
