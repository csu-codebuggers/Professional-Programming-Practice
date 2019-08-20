import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI ui; // change variable name UI to ui
	
	// variable name changed from LIBRARY to library
	// Class name changed from library to Library
	private Library library; 
	
	// variable name changed from M to member
	// Class name changed from member to Member
	private Member member; 

	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private ControlState state; // variable name changed from State to state
	
	// variable name changed from PENDING to pendingBooks
	// class name changed from book to Book
	private List<Book> pendingBooks; 

	// variable name changed from COMPLETED to completed
	// class name changed from loan to Loan
	private List<Loan> completed; 

	// variable name Book changed to book
	// Class name changed from book to Book
	private Book book; 
	
	
	public BorrowBookControl() {
		this.library = Library.getInstance(); // changed library to Library and method INSTANCE() to getInstance()
		state = ControlState.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		}
		this.ui = ui;
		this.ui.setState(BorrowBookUI.UiState.READY); // change method Set_State() to setState()
		state = ControlState.READY;		
	}


	// method name Swiped changed to cardSwiped
	// parameter name MEMMER_ID changed to memberId
	public void cardSwiped(int memberId) {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		}
		member = library.checkMember(memberId);
		if ( member == null) {
			ui.display("Invalid memberId"); // method Display changed to display
			return;
		}
		if (library.checkMemberCanBorrow(member)) {
			pendingBooks = new ArrayList<>();
			ui.setState(BorrowBookUI.UiState.SCANNING); // method Set_State changed to setState
			state = ControlState.SCANNING; }
		else {
			ui.display("Member cannot borrow at this time"); // method Display changed to display
			ui.setState(BorrowBookUI.UI_STATE.RESTRICTED); // method Set_State changed to setState
		}
	}
	
	// function name scanned changed to bookScanned
	public void bookScanned(int bookId) {
		book = null;
		if (!state.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.checkBook(bookId);
		if (book == null) {
			ui.display("Invalid bookId"); // method Display changed to display
			return;
		}
		if (!book.isAvailable()) {
			ui.display("Book cannot be borrowed"); // method Display changed to display
			return;
		}
		pendingBooks.add(book);
		// book Changed to Book
		// local variable B changed to pendingBook
		for (Book pendingBook : pendingBooks) {
			ui.display(pendingBook.toString()); // method Display changed to display
		}
		if (library.getLoansRemaining(member) - pendingBooks.size() == 0) {
			ui.display("Loan limit reached"); // method Display changed to display
			complete();
		}
	}
	
	
	public void complete() {
		if (pendingBooks.size() == 0) {
			cancel();
		}
		else {
			ui.display("\nFinal Borrowing List"); // method Display changed to display
			// class name book changed to Book
			// local variable B changed to pendingBook
			for (Book pendingBook : pendingBooks) {
				ui.display(pendingBook.toString()); // method Display changed to display
			}
			completed = new ArrayList<Loan>();
			ui.setState(BorrowBookUI.UiState.FINALISING); // method Set_State changed to setState
			state = ControlState.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		// class name book changed to Book
		// local variable B changed to pendingBook
		for (Book pendingBook : pendingBooks) {
			Loan loan = library.loanIssue(pendingBook, member); // class and variable naming changed
			completed.add(loan);			
		}
		ui.display("Completed Loan Slip"); // method Display changed to display
		for (Loan loan : completed) {
			ui.display(loan.toString()); // method Display changed to display
		}
		ui.setState(BorrowBookUI.UiState.COMPLETED); // method Set_State changed to setState
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UiState.CANCELLED); // method Set_State changed to setState
		state = ControlState.CANCELLED;
	}
	
	
}
