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
					control.Scanning_Complete();  //changed CoNtRoL to control
				}
				else {
					try {
						int Book_Id = Integer.valueOf(Book_STR).intValue();
						CoNtRoL.Book_scanned(Book_Id);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean Is_Damaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					Is_Damaged = true;
				}
				CoNtRoL.Discharge_loan(Is_Damaged);
			
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
