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


	public void setState(UiState state) { //Changed UI_STATE to UiState and Set_State to setState
		this.state = state; //Changed StAtE to state
	}

	
	public void run() { //changed RuN to run
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { //Changed StAtE to state
			
			case READY:
				String bookStr = input("Scan Book (<enter> completes): "); //changed Book_STR to bookStr
				if (bookStr.length() == 0) { //changed Book_STR to bookStr
					control.scanningComplete(); //changed SCannING_COMplete to scanningComplete
				}
				else {
					try {
						int bookID = Integer.valueOf(bookStr).intValue(); //changed Book_ID to bookID and Book_STR to bookStr
						control.bookScanned(bookID); //changed Book_ID to bookID and Book_scanned to bookScanned
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String answer = input("Fix Book? (Y/N) : "); //changed AnS to answer
				boolean fix = false; //changed FiX to fix
				if (answer.toUpperCase().equals("Y")) { //changed AnS to answer
					fix = true; //changed FiX to fix
				}
				control.fixBook(fix); //changed FiX to fix and FIX_Book to fixBook
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
