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

	
	public boolean onLoan() { //changed ON_LOAN to onLoan
		return State == STATE.onLoan;
	}

	
	public boolean isDamaged() {   //changed Is_Damaged to isDamaged
		return State == STATE.isDamaged; //changed DAMAGED to isDamaged
	}

	
	public void borrow() { //changed Borrow to borrow
		if (State.equals(STATE.AVAILABLE)) {
			State = state.onLoan; //changed STATE to state and ONLOAN to onLoan
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
	}


	public void Return(boolean isDamaged) { // changed DAMAGED to isDamaged
		if (State.equals(state.onLoan)) {// changed STATE.ON_LOAN to state.onLoan
			if (isDamaged) { //changed DAMAGED to isDamaged
				State =  state.isDamaged; //changed STATE.DAMAGED
			}
			else {
				State = state.isAvailable; //changed STATE.AVAILABLE to state.isAvailable
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	
	public void repair() { // changed Repair to repair
		if (State.equals(state.isDamaged)) { //changed STATE.DAMAGED to state.isDamaged
			State = state.isAvailable; //changed STATE.AVAILABLE to state.isAvailable
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
	}


}
