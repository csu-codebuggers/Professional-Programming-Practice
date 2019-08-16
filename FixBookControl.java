public class FixBookControl {
	
	private FixBookUI UI;
	// change enum name CONTROL_STATE to ControlState
	private enum ControlState { INITIALISED, READY, FIXING };
	private ControlState state; // variable name StAtE changed to state
	
	private library library; // variable name (LIB) changed to library
	private book currentBook; // variable name (Cur_Book) changed to currentBook

	public FixBookControl() {
		this.library = library.INSTANCE();
		state = ControlState.INITIALISED;
	}
	
	public void Set_Ui(FixBookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.UI = ui;
		ui.Set_State(FixBookUI.UI_STATE.READY);
		state = ControlState.READY;		
	}


	public void Book_scanned(int bookId) {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			UI.display("Invalid bookId");
			return;
		}
		if (!currentBook.IS_Damaged()) {
			UI.display("Book has not been damaged");
			return;
		}
		UI.display(currentBook.toString());
		UI.Set_State(FixBookUI.UI_STATE.FIXING);
		state = ControlState.FIXING;		
	}


	public void FIX_Book(boolean MUST_fix) {
		if (!state.equals(ControlState.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) {
			library.Repair_BOOK(currentBook);
		}
		currentBook = null;
		UI.Set_State(FixBookUI.UI_STATE.READY);
		state = ControlState.READY;		
	}

	
	public void SCannING_COMplete() {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		UI.Set_State(FixBookUI.UI_STATE.COMPLETED);		
	}
}
