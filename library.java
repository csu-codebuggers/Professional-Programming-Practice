
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {// Changed the class name from 'library' to 'Library'
	
	private static final String libraryFile = "library.obj";
	private static final int loanLimit = 2;
	private static final int loanPeriod = 2;
	private static final double finePerDay = 1.0;
	private static final double maxFinesOwed = 1.0;
	private static final double damageFee = 2.0;
	
	private static library self;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	private int bookId;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	private int memberId;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	private int loanId;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	private Date loanDate;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	
	private Map<Integer, book> catalog; // Changed the variable name to lowercase
	private Map<Integer, member> members; // Changed the variable name to lowercase
	private Map<Integer, loan> loans; // Changed the variable name to lowercase
	private Map<Integer, loan> current_loans;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
	private Map<Integer, book> damaged_books;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
	

	private library() {
		catalog = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		members = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		loans = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		current_loans = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		damaged_books = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		bookId = 1;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		memberId = 1;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan			
		loanId = 1;	// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
	}

	
	public static synchronized library INSTANCE() {		
		if (SeLf == null) {
			Path PATH = Paths.get(libraryFile);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					SeLf = (library) LiF.readObject();
					Calendar.INSTANCE().Set_dATE(SeLf.LOAN_DATE);
					LiF.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else SeLf = new library();
		}
		return SeLf;
	}

	
	public static synchronized void SAVE() {
		if (SeLf != null) {
			SeLf.LOAN_DATE = Calendar.INSTANCE().Date();
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				LoF.writeObject(SeLf);
				LoF.flush();
				LoF.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int BookID() {
		return bookId;// Changed variable name from BOOK_ID to bookId by Sudeep Maharjan
	}
	
	
	public int MemberID() {
		return memberId;// Changed variable name from MEMBER_ID to memberId by Sudeep Maharjan
	}
	
	
	private int NextBID() {
		return bookId++;// Changed variable name from BOOK_ID to bookId by Sudeep Maharjan
	}

	
	private int NextMID() {
		return memberId++;// Changed variable name from Member_Id to memberId by Sudeep Maharjan
	}

	
	private int NextLID() {
		return loanId++;// Changed variable name from Loan_id to loanId by Sudeep Maharjan
	}

	
	public List<member> members() {		
		return new ArrayList<member>(members.values()); 
	}


	public List<book> BOOKS() {		
		return new ArrayList<book>(catalog.values()); 
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(current_loans.values());
	}


	public member Add_mem(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, NextMID());
		members.put(member.GeT_ID(), member);		
		return member;
	}

	
	public book Add_book(String a, String t, String c) {		
		book b = new book(a, t, c, NextBID());
		catalog.put(b.ID(), b);		
		return b;
	}

	
	public member MEMBER(int memberId) {
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public book Book(int bookId) {
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int LOAN_LIMIT() {
		return loanLimit;
	}

	
	public boolean MEMBER_CAN_BORROW(member member) {		
		if (member.Number_Of_Current_Loans() == loanLimit ) 
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.GeT_LoAnS()) 
			if (loan.OVer_Due()) 
				return false;
			
		return true;
	}

	
	public int Loans_Remaining_For_Member(member member) {		
		return loanLimit - member.Number_Of_Current_Loans();
	}

	
	public loan ISSUE_LAON(book book, member member) {
		Date dueDate = Calendar.INSTANCE().Due_Date(loanPeriod);
		loan loan = new loan(NextLID(), book, member, dueDate);
		member.Take_Out_Loan(loan);
		book.Borrow();
		loans.put(loan.ID(), loan);
		current_loans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan LOAN_BY_bookId(int bookId) {
		if (current_loans.containsKey(bookId)) {
			return current_loans.get(bookId);
		}
		return null;
	}

	
	public double CalculateOverDueFine(loan loan) {
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.INSTANCE().Get_Days_Difference(loan.Get_Due_Date());
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void Discharge_loan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.Add_Fine(overDueFine);	
		
		member.dIsChArGeLoAn(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.Add_Fine(damageFee);
			damaged_books.put(book.ID(), book);
		}
		currentLoan.DiScHaRgE();
		current_loans.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : current_loans.values()) {
			loan.checkOverDue();
		}		
	}


	public void Repair_BOOK(book currentBook) {
		if (damaged_books.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damaged_books.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
