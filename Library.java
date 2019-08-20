
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
	
	private static final String LIBRARY_FILE = "library.obj"; // Changed the name of constants to uppercase and underscore seprated
	private static final int LOAN_LIMIT = 2; // Changed the name of constants to uppercase and underscore seprated
	private static final int LOAN_PERIOD = 2; // Changed the name of constants to uppercase and underscore seprated
	private static final double FINE_PER_DAY = 1.0; // Changed the name of constants to uppercase and underscore seprated
	private static final double MAX_FINES_OWED = 1.0; // Changed the name of constants to uppercase and underscore seprated
	private static final double DAMAGE_FEE = 2.0; // Changed the name of constants to uppercase and underscore seprated
	
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

	public static synchronized Library getInstance() {		
		if (self == null) {
			Path PATH = Paths.get(LIBRARY_FILE);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
			    
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

	
	public static synchronized void save() {
		if (self != null) {
			self.LOAN_DATE = Calendar.INSTANCE().Date();
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {
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


	public Member addMember(String lastName, String firstName, String email, int phoneNo) {	//Changed the method name to more meaningful By Sudeep Maharjan
		Member member = new member(lastName, firstName, email, phoneNo, NextMID());
		members.put(member.getId(), member);// Changed the method name 	
		return member;
	}

	
	public Book addBook(String a, String t, String c) {	//Changed the method name to more meaningful By Sudeep Maharjan	
		Book b = new book(a, t, c, NextBID()); // Changed the class name from lowercase to uppercase
		catalog.put(b.ID(), b);		
		return b;
	}

	
	public Member checkMember(int memberId) { //Changed the method name to more meaningful By Sudeep Maharjan
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public Book checkBook(int bookId) { //Changed the method name to more meaningful By Sudeep Maharjan
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int getLoanLimit() { //Changed the method name to more meaningful By Sudeep Maharjan
		return LOAN_LIMIT;
	}

	
	public boolean checkMemberCanBorrow(Member member) { //Changed the method name to more meaningful By Sudeep Maharjan	
		if (member.numberOfCurrentLoans() == LOAN_LIMIT ){// Changed the method name according to changes made in member class
			return false;
		}
				
		if (member.finesOwed() >= MAX_FINES_OWED) {// Changed the method name according to changes made in member class
			return false;
		}

		for (loan loan : member.getLoans()) {// Changed the method name according to changes made in member class
			if (loan.over_due()) {// Changed the method name 
				return false;
			}
		}
		return true;
	}

	
	public int getLoansRemaining(Book member) { //Changed the method name to more meaningful By Sudeep Maharjan	
		return LOAN_LIMIT - member.numberOfCurrentLoans();// Changed the method name according to changes made in member class
	}

	
	public Loan loanIssue(Book book, Member member) { //Changed the method name to more meaningful By Sudeep Maharjan	
		Date dueDate = Calendar.INSTANCE().Due_Date(LOAN_PERIOD);
		loan loan = new loan(NextLID(), book, member, dueDate);
		member.takeOutLoan(loan);// Changed the method name according to changes made in member class
		book.Borrow();
		loans.put(loan.ID(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public Loan getLoanByBookId(int bookId) { //Changed the method name to more meaningful By Sudeep Maharjan	
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(Loan loan) { //Changed the method name to more meaningful By Sudeep Maharjan	
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.INSTANCE().Get_Days_Difference(loan.Get_Due_Date());
			double fine = daysOverDue * FINE_PER_DAY;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(Loan currentLoan, boolean isDamaged) {//Changed the method name to more meaningful By Sudeep Maharjan	
		Member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	// Changed the method name according to changes made in member class
		
		member.dischargeLoan(currentLoan);// Changed the method name 
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);// Changed the method name according to changes made in member class
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.DiScHaRgE();
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {//Changed the method name to more meaningful By Sudeep Maharjan	
		for (Loan loan : currentLoans.values()) {
			loan.checkOverDue();
		}		
	}

	public void repairBook(Book currentBook) {//Changed the method name to more meaningful By Sudeep Maharjan	
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
}
