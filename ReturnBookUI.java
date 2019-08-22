import java.util.Scanner;


public class ReturnBookUi { // Changed class ReturnBookUi in camelBlack formate

	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED }; //changed UI_STATE to UiState
	private ReturnBookControl control; //changed CoNtRoL to co
	private Scanner input;
	private UiState state; // changed UI_STATE to UiState and StATe to state

	
	public ReturnBookUi(ReturnBookControl control) { // changed class name 
		this.control = control; //changed CoNtRoL to control
		input = new Scanner(System.in);
		state = UiState.INITIALISED; //changed StATe to state and UI_STATE to UiState
		control.setUi(this); //changed Set_UI to setUi
	}


	public void run() {	  //changed RuN to run	
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {   //changed StATe to state
 			
			case INITIALISED:
				break;
				
			case READY:
				String bookStr = input("Scan Book (<enter> completes): "); //changed Book_STR to bookStr
				if (bookStr.length() == 0) { // changed Book_STR to bookStr
					control.scanningComplete();  //changed CoNtRoL to control and Scanning_Complete to scanningComplete
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue(); //changed Book_Id to bookId and Book_STR to bookStr
						control.bookScanned(bookId); //changed CoNtRoL to control, Book_scanned to bookScanned  and Book_Id to bookId
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false; //changed Is_Damaged to isDamaged
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true; // changed Is_Damaged to isDamaged
				}
				control.dischargeLoan(isDamaged); //changed CoNtRoL to control, Is_Damaged to isDamaged and Discharge_loan to dischargeLoan
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
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
	
	public void Set_State(UI_STATE state) {
		this.StATe = state;
	}

	
}
