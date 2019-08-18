import java.util.Scanner;


public class BorrowBookUI {
	
	// changed enum name UI_STATE to UiState
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };

	private BorrowBookControl control; // change CONTROL to control
	private Scanner scanner; // changed scanner object name from input to scanner
	private UiState state; // chnage StaTe to state

	
	public BorrowBookUI(BorrowBookControl control) {
		this.control = control;
		scanner = new Scanner(System.in);
		state = UiState.INITIALISED;
		control.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
	// function name changed from Set_State to setState		
	public void setState(UiState state) {
		this.state = state;
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				// local variable MEM_STR changed to memberIdString
				String memberIdString = input("Swipe member card (press <enter> to cancel): ");
				if (memberIdString.length() == 0) {
					control.cancel();
					break;
				}
				try {
					// local variable Member_ID changed to memberId
					int memberId = Integer.valueOf(memberIdString).intValue();
					control.cardSwiped(memberId); // proper function pointed
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel();
				break;
			
				
			case SCANNING:
				// local variable Book_Str changed to bookIdString
				String bookIdString = input("Scan Book (<enter> completes): ");
				if (bookIdString.length() == 0) {
					control.complete(); 
					break;
				}
				try {
					int bookId = Integer.valueOf(bookIdString).intValue();
					control.bookScanned(bookId); // proper function pointed
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				// local variable Ans changed to answer
				String answer = input("Commit loans? (Y/N): ");
				if (answer.toUpperCase().equals("N")) {
					control.cancel();
					
				} else {
					control.commitLoans(); // proper function pointed
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + StaTe);			
			}
		}		
	}

	// function name changed from Display to display
	public void display(Object object) {
		output(object);		
	}


}
