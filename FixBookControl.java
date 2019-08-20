public class FixBookControl {
	
	private FixBookUI ui;
	// change enum name CONTROL_STATE to ControlState
	private enum ControlState { INITIALISED, READY, FIXING };
	private ControlState state; // variable name StAtE changed to state
	
	// variable name (LIB) changed to library
	// class name library changed to Library
	private Library library; 

	// variable name (Cur_Book) changed to currentBook
	// class name book changed to Book
	private Book currentBook; 

	public FixBookControl() {
		this.library = Library.getInstance(); //  function name INSTANCE() changed to getInstance()
		state = ControlState.INITIALISED;
	}
	
	// function name Set_Ui changed to setUI
	public void setUI(FixBookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		
		// function name Set_State() changed to setState()
		// UI_STATE changed to UiState
		this.ui.setState(FixBookUI.UiState.READY); 
		state = ControlState.READY;		
	}


	// function name Book_scanned changed to bookScanned
	public void bookScanned(int bookId) {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	

		currentBook = library.checkBook(bookId); // method name Book() changed to checkBook()
		
		if (currentBook == null) {
			ui.display("Invalid bookId");
			return;
		}

		// function name IS_Damaged() changed to isDamaged()
		if (!currentBook.isDamaged()) { 
			ui.display("Book has not been damaged");
			return;
		}

		// function name Set_State() changed to setState()
		// UI_STATE changed to UiState
		ui.display(currentBook.toString());
		ui.setState(FixBookUI.UiState.FIXING); 
		state = ControlState.FIXING;		
	}

	// function name FIX_Book changed to fixBook
	public void fixBook(boolean mustFix) {
		if (!state.equals(ControlState.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (mustFix) {
			library.repairBook(currentBook);
		}
		currentBook = null;

		// function name Set_State() changed to setState()
		// UI_STATE changed to UiState
		ui.setState(FixBookUI.UiState.READY);
		state = ControlState.READY;		
	}

	// function name SCannING_COMplete changed to scanningComplete
	public void scanningComplete() {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		// function name Set_State() changed to setState()
		// UI_STATE changed to UiState
		ui.setState(FixBookUI.UiState.COMPLETED);		
	}
}
