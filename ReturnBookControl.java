public class ReturnBookControl {

	private ReturnBookUI Ui;
	private enum ControlState { INITIALISED, READY, INSPECTING }; //changed CONTROL_STATE to ControlState
	private ControlState state; //Changed CONTROL_STATE TO ControlState and sTaTe to state
	
	private Library library; //changed library to Library and library
	private Loan currentLoan; //Changed loan to Load and CurrENT_loan to currentLoan
	

	public ReturnBookControl() {
		this.library = library.INSTANCE(); //changed lIbRaRy to library 
		state = ControlState.INITIALISED; //changed sTaTe to state, CONTROL_STATE to ControlState
	}
	
	
	public void setUi(ReturnBookUI ui) { //changed SET_UI to setUi
		if (!state.equals(ContorlState.INITIALISED)) { //changed sTaTe to state and CONTROL_STATE to ControlState
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui; //changed Ui to ui
		ui.Set_State(ReturnBookUI.UI_STATE.READY);
		state = ContorlState.READY; //changed sTaTe to state and CONTROL_STATE to ContorlState		
	}


	public void Book_scanned(int Book_ID) {
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book CUR_book = lIbRaRy.Book(Book_ID);
		
		if (CUR_book == null) {
			Ui.display("Invalid Book Id");
			return;
		}
		if (!CUR_book.On_loan()) {
			Ui.display("Book has not been borrowed");
			return;
		}		
		CurrENT_loan = lIbRaRy.LOAN_BY_BOOK_ID(Book_ID);	
		double Over_Due_Fine = 0.0;
		if (CurrENT_loan.OVer_Due()) {
			Over_Due_Fine = lIbRaRy.CalculateOverDueFine(CurrENT_loan);
		}
		Ui.display("Inspecting");
		Ui.display(CUR_book.toString());
		Ui.display(CurrENT_loan.toString());
		
		if (CurrENT_loan.OVer_Due()) {
			Ui.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		}
		Ui.Set_State(ReturnBookUI.UI_STATE.INSPECTING);
		sTaTe = CONTROL_STATE.INSPECTING;		
	}


	public void Scanning_Complete() {
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		Ui.Set_State(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void Discharge_loan(boolean isDamaged) {
		if (!sTaTe.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		lIbRaRy.Discharge_loan(CurrENT_loan, isDamaged);
		CurrENT_loan = null;
		Ui.Set_State(ReturnBookUI.UI_STATE.READY);
		sTaTe = CONTROL_STATE.READY;				
	}


}
