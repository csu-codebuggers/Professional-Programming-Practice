import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable { 
	
	private String title;  // changed to lowercase
	private String author; // changed to lowercase
	private String callNo; // matched as camelBlack format
	private int id; // changed to lowercase
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED }; //changed STATE to State
	private State state; // changed State to state and STATE to State
	
	
	public book(String author, String title, String callNo, int id) { // Variable changed 
		this.author = author; // changed AUTHOR to author
		this.title = title;   
		
		this.callNo = callNo; // changed CALLNO to callNo
		this.id = id;  // changed ID to id
		this.State = state.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n") // changed ID to id
		  .append("  Title:  ").append(title).append("\n") // changed title to Title 
		  .append("  Author: ").append(author).append("\n") // changed AUTHOR to author
		  .append("  CallNo: ").append(callNo).append("\n") // changed CallNo to callNo
		  .append("  State:  ").append(state); // changed State to state
		
		return sb.toString();
	}

	public Integer id() { // changed ID to id
		return id;	// changed ID to id
	}

	public String title() {
		return title;
	}


	
	public boolean isAvailable() {	//changed method AVAILABLE to isAvailable
		return State == state.AVAILABLE; //changed STATE TO state
	}

	
	public boolean On_loan() {
		return State == STATE.ON_LOAN;
	}

	
	public boolean IS_Damaged() {
		return State == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (State.equals(STATE.AVAILABLE)) {
			State = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
	}


	public void Return(boolean DAMAGED) {
		if (State.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				State = STATE.DAMAGED;
			}
			else {
				State = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	
	public void Repair() {
		if (State.equals(STATE.DAMAGED)) {
			State = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
	}


}
