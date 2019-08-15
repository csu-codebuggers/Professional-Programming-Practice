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
			
			switch (state) {  //Changed StAtE to state
			
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): ");  //changed Mem_Str to memStr
				if (memStr.length() == 0) {  //changed Mem_Str to memStr
					control.CaNcEl();  //Changed CoNtRoL variable to control
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();  //changed Mem_Str to memStr and Member_ID to memberId
					control.Card_Swiped(memberId);  //Changed CoNtRoL variable to control and Member_ID to memberId
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;  //changed AmouNT to amount
				String amtStr = input("Enter amount (<Enter> cancels) : ");  //changed Amt_Str to amtStr
				if (amtStr.length() == 0) {
					control.CaNcEl();  //Changed CoNtRoL variable to control
					break;
				}
				try {
					amount = Double.valueOf(amtStr).doubleValue();  //changed AmouNT to amount
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {  //changed AmouNT to amount
					output("Amount must be positive");
					break;
				}
				control.PaY_FiNe(amount);  //Changed CoNtRoL variable to control and changed AmouNT to amount
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
