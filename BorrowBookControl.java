import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI UI;
	
	private library library; // variable name changed from LIBRARY to library
	private member member; // variable name changed from M to member
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private ControlState state; // variable name changed from State to state
	
	private List<book> pendingBooks; // variable name changed from PENDING to pendingBooks
	private List<loan> completed; // variable name changed from COMPLETED to completed
	private book book; // variable name Book changed to book
	
	
	public BorrowBookControl() {
		this.library = library.INSTANCE();
		state = ControlState.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		}
		this.UI = ui;
		ui.Set_State(BorrowBookUI.UI_STATE.READY);
		state = ControlState.READY;		
	}

		
	public void Swiped(int MEMMER_ID) {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		}
		member = library.MEMBER(MEMMER_ID);
		if ( member == null) {
			UI.Display("Invalid memberId");
			return;
		}
		if (library.MEMBER_CAN_BORROW(member)) {
			pendingBooks = new ArrayList<>();
			UI.Set_State(BorrowBookUI.UI_STATE.SCANNING);
			state = ControlState.SCANNING; }
		else {
			UI.Display("Member cannot borrow at this time");
			UI.Set_State(BorrowBookUI.UI_STATE.RESTRICTED); 
		}
	}
	
	
	public void scanned(int bookId) {
		book = null;
		if (!state.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.Book(bookId);
		if (book == null) {
			UI.Display("Invalid bookId");
			return;
		}
		if (!book.AVAILABLE()) {
			UI.Display("Book cannot be borrowed");
			return;
		}
		pendingBooks.add(book);
		for (book B : pendingBooks) {
			UI.Display(B.toString());
		}
		if (library.Loans_Remaining_For_Member(member) - pendingBooks.size() == 0) {
			UI.Display("Loan limit reached");
			complete();
		}
	}
	
	
	public void complete() {
		if (pendingBooks.size() == 0) {
			cancel();
		}
		else {
			UI.Display("\nFinal Borrowing List");
			for (book B : pendingBooks) {
				UI.Display(B.toString());
			}
			completed = new ArrayList<loan>();
			UI.Set_State(BorrowBookUI.UI_STATE.FINALISING);
			state = ControlState.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : pendingBooks) {
			loan LOAN = library.ISSUE_LAON(B, member);
			completed.add(LOAN);			
		}
		UI.Display("Completed Loan Slip");
		for (loan LOAN : completed) {
			UI.Display(LOAN.toString());
		}
		UI.Set_State(BorrowBookUI.UI_STATE.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		UI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);
		state = ControlState.CANCELLED;
	}
	
	
}
