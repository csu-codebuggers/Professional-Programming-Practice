import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable { // 
	
	private String title;  // changed to lowercase
	private String author; // changed to lowercase
	private String callNo; // matched as camelBlack format
	private int id; // changed to lowercase
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED }; //changed STATE to State
	private State state; // changed State to state and STATE to State
	
	
	public book(String author, String title, String callNo, int id) {
		this.AUTHOR = author;
		this.title = title;
		
		this.CALLNO = callNo;
		this.ID = id;
		this.State = STATE.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(ID).append("\n")
		  .append("  title:  ").append(title).append("\n")
		  .append("  Author: ").append(AUTHOR).append("\n")
		  .append("  CallNo: ").append(CALLNO).append("\n")
		  .append("  State:  ").append(State);
		
		return sb.toString();
	}

	public Integer ID() {
		return ID;
	}

	public String title() {
		return title;
	}


	
	public boolean AVAILABLE() {
		return State == STATE.AVAILABLE;
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
