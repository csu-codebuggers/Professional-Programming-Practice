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
		if (state == LOAN_STATE.CURRENT &&
			Calendar.INSTANCE().Date().after(D)) {
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	
	public boolean OVer_Due() {
		return state == LOAN_STATE.OVER_DUE;
	}

	
	public Integer ID() {
		return ID;
	}


	public Date Get_Due_Date() {
		return D;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.GeT_ID()).append(" : ")
		  .append(M.Get_LastName()).append(", ").append(M.Get_FirstName()).append("\n")
		  .append("  Book ").append(B.ID()).append(" : " )
		  .append(B.TITLE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member Member() {
		return M;
	}


	public book Book() {
		return B;
	}


	public void DiScHaRgE() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
