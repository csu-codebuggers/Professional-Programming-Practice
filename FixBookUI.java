import java.util.Scanner;


public class FixBookUI {

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };  //Changed UI_STATE to UiState

	private FixBookControl control;  //Changed CoNtRoL variable to control
	private Scanner input;
	private UiState state;  //Changed UI_STATE to UiState and StAtE to state

	
	public FixBookUI(FixBookControl control) {
		this.control = control; //Changed CoNtRoL variable to control
		input = new Scanner(System.in);
		state = UiState.INITIALISED; //Changed UI_STATE to UiState and StAtE to state
		control.setUi(this); //Changed CoNtRoL variable to control and Set_Ui to setUi
	}


	public void Set_State(UiState state) { //Changed UI_STATE to UiState
		this.state = state; //Changed StAtE to state
	}

	
	public void run() { //changed RuN to run
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { //Changed StAtE to state
			
			case READY:
				String Book_STR = input("Scan Book (<enter> completes): ");
				if (Book_STR.length() == 0) {
					control.SCannING_COMplete();
				}
				else {
					try {
						int Book_ID = Integer.valueOf(Book_STR).intValue();
						control.Book_scanned(Book_ID);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String AnS = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				if (AnS.toUpperCase().equals("Y")) {
					FiX = true;
				}
				control.FIX_Book(FiX);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);	//Changed StAtE to state		
			
			}		
		}
		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
