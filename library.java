
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
	
	private static Library self;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	private int bookId;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	private int memberId;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	private int loanId;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	private Date loanDate;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan
	
	private Map<Integer, book> catalog; // Changed the variable name to lowercase
	private Map<Integer, member> members; // Changed the variable name to lowercase
	private Map<Integer, loan> loans; // Changed the variable name to lowercase
	private Map<Integer, loan> currentLoans;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
	private Map<Integer, book> damagedBooks;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
	

	private Library() {
		catalog = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		members = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		loans = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		currentLoans = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		damagedBooks = new HashMap<>();// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		bookId = 1;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
		memberId = 1;// Changed the variable name to lowercase (camelback) by Sudeep Maharjan			
		loanId = 1;	// Changed the variable name to lowercase (camelback) by Sudeep Maharjan	
	}

	public static synchronized Library INSTANCE() {		
		if (self == null) {
			Path PATH = Paths.get(libraryFile);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					self = (Library) LiF.readObject();
					Calendar.INSTANCE().Set_dATE(self.LOAN_DATE);
					LiF.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();
		}
		return self;
	}

	
	public static synchronized void SAVE() {
		if (self != null) {
			self.LOAN_DATE = Calendar.INSTANCE().Date();
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				LoF.writeObject(self);
				LoF.flush();
				LoF.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookID() { //Changed the method name to more meaningful By Sudeep Maharjan
		return bookId;// Changed variable name from BOOK_ID to bookId by Sudeep Maharjan
	}
	
	
	public int getMemberID() { //Changed the method name to more meaningful By Sudeep Maharjan
		return memberId;// Changed variable name from MEMBER_ID to memberId by Sudeep Maharjan
	}
	
	
	private int getNextBID() { //Changed the method name to more meaningful By Sudeep Maharjan
		return bookId++;// Change variable name from BOOK_ID to bookId by Sudeep Maharjan
	}

	
	private int getNextMID() { //Changed the method name to more meaningful By Sudeep Maharjan
		return memberId++;// Changed variable name from Member_Id to memberId by Sudeep Maharjan
	}

	
	private int getNextLID() { //Changed the method name to more meaningful By Sudeep Maharjan
		return loanId++;// Changed variable name from Loan_id to loanId by Sudeep Maharjan
	}

	
	public List<member> getMembers() { //Changed the method name to more meaningful By Sudeep Maharjan		
		return new ArrayList<member>(members.values()); 
	}


	public List<book> getBooks() {	//Changed the method name to more meaningful By Sudeep Maharjan	
		return new ArrayList<book>(catalog.values()); 
	}


	public List<loan> getCurrentLoans() { //Changed the method name to more meaningful By Sudeep Maharjan
		return new ArrayList<loan>(currentLoans.values());
	}


	public member addMember(String lastName, String firstName, String email, int phoneNo) {	//Changed the method name to more meaningful By Sudeep Maharjan
		member member = new member(lastName, firstName, email, phoneNo, NextMID());
		members.put(member.GeT_ID(), member);		
		return member;
	}

	
	public book addBook(String a, String t, String c) {	//Changed the method name to more meaningful By Sudeep Maharjan	
		book b = new book(a, t, c, NextBID());
		catalog.put(b.ID(), b);		
		return b;
	}

	
	public member checkMember(int memberId) { //Changed the method name to more meaningful By Sudeep Maharjan
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public book checkBook(int bookId) { //Changed the method name to more meaningful By Sudeep Maharjan
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int getLoanLimit() { //Changed the method name to more meaningful By Sudeep Maharjan
		return loanLimit;
	}

	
	public boolean checkMemberCanBorrow(member member) { //Changed the method name to more meaningful By Sudeep Maharjan	
		if (member.Number_Of_Current_Loans() == loanLimit ) 
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.GeT_LoAnS()) 
			if (loan.OVer_Due()) 
				return false;
			
		return true;
	}

	
	public int getLoansRemaining(member member) { //Changed the method name to more meaningful By Sudeep Maharjan	
		return loanLimit - member.Number_Of_Current_Loans();
	}

	
	public loan loanIssue(book book, member member) { //Changed the method name to more meaningful By Sudeep Maharjan	
		Date dueDate = Calendar.INSTANCE().Due_Date(loanPeriod);
		loan loan = new loan(NextLID(), book, member, dueDate);
		member.Take_Out_Loan(loan);
		book.Borrow();
		loans.put(loan.ID(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan getLoanByBookId(int bookId) { //Changed the method name to more meaningful By Sudeep Maharjan	
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) { //Changed the method name to more meaningful By Sudeep Maharjan	
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.INSTANCE().Get_Days_Difference(loan.Get_Due_Date());
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {//Changed the method name to more meaningful By Sudeep Maharjan	
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.Add_Fine(overDueFine);	
		
		member.dIsChArGeLoAn(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.Add_Fine(damageFee);
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.DiScHaRgE();
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {//Changed the method name to more meaningful By Sudeep Maharjan	
		for (loan loan : currentLoans.values()) {
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {//Changed the method name to more meaningful By Sudeep Maharjan	
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
