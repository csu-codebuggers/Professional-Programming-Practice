import java.util.Scanner;


public class PayFineUI {


	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };  //changed UI_State to UiState

	private PayFineControl control; //Changed CoNtRoL variable to control
	private Scanner input;
	private UiState state;  //Changed UI_STATE to UiState and StAtE to state

	
	public PayFineUI(PayFineControl control) {
		this.CoNtRoL = control; //Changed CoNtRoL variable to control
		input = new Scanner(System.in);
		state = UiState.INITIALISED;  //Changed UI_STATE to UiState and StAtE to state
		control.Set_UI(this);
	}
	
	
	public void Set_State(UiState state) {   //changed UI_State to UiState
		this.state = state;  //Changed StAtE to state
	}


	public void RuN() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (StAtE) {
			
			case READY:
				String Mem_Str = input("Swipe member card (press <enter> to cancel): ");
				if (Mem_Str.length() == 0) {
					CoNtRoL.CaNcEl();
					break;
				}
				try {
					int Member_ID = Integer.valueOf(Mem_Str).intValue();
					CoNtRoL.Card_Swiped(Member_ID);
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double AmouNT = 0;
				String Amt_Str = input("Enter amount (<Enter> cancels) : ");
				if (Amt_Str.length() == 0) {
					CoNtRoL.CaNcEl();
					break;
				}
				try {
					AmouNT = Double.valueOf(Amt_Str).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (AmouNT <= 0) {
					output("Amount must be positive");
					break;
				}
				CoNtRoL.PaY_FiNe(AmouNT);
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + StAtE);			
			
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
			

	public void DiSplAY(Object object) {
		output(object);
	}


}
