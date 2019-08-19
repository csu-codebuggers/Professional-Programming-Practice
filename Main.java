import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner in; //changed IN to in
	private static library lib; //changed LIB to lib
	private static String menu; //changed MENU to menu
	private static Calendar cal; //changed CAL to cal
	private static SimpleDateFormat sdf; //Changed SDF to sdf
	
	
	private static String Get_menu() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			in = new Scanner(System.in); //changed IN to in
			lib = library.INSTANCE(); //changed LIB to lib
			cal = Calendar.INSTANCE(); //changed CAL to cal
			sdf = new SimpleDateFormat("dd/MM/yyyy"); //Changed SDF to sdf
	
			for (member m : lib.MEMBERS()) { //changed LIB to lib
				output(m);
			}
			output(" ");
			for (book b : lib.BOOKS()) { //changed LIB to lib
				output(b);
			}
						
			menu = Get_menu(); //changed MENU to menu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + sdf.format(CAL.Date())); //Changed SDF to sdf and CAL to cal
				String c = input(menu); //changed MENU to menu
				
				switch (c.toUpperCase()) {
				
				case "M": 
					ADD_MEMBER();
					break;
					
				case "LM": 
					MEMBERS();
					break;
					
				case "B": 
					ADD_BOOK();
					break;
					
				case "LB": 
					BOOKS();
					break;
					
				case "FB": 
					FIX_BOOKS();
					break;
					
				case "L": 
					BORROW_BOOK();
					break;
					
				case "R": 
					RETURN_BOOK();
					break;
					
				case "LL": 
					CURRENT_LOANS();
					break;
					
				case "P": 
					FINES();
					break;
					
				case "T": 
					INCREMENT_DATE();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void FINES() {
		new PayFineUI(new PayFineControl()).RuN();		
	}


	private static void CURRENT_LOANS() {
		output("");
		for (loan loan : lib.CurrentLoans()) { //changed LIB to lib
			output(loan + "\n");
		}		
	}



	private static void BOOKS() {
		output(""); 
		for (book book : lib.BOOKS()) { //changed LIB to lib
			output(book + "\n");
		}		
	}



	private static void MEMBERS() {
		output("");
		for (member member : lib.MEMBERS()) { //changed LIB to lib
			output(member + "\n");
		}		
	}



	private static void BORROW_BOOK() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void RETURN_BOOK() {
		new ReturnBookUI(new ReturnBookControl()).RuN();		
	}


	private static void FIX_BOOKS() {
		new FixBookUI(new FixBookControl()).RuN();		
	}


	private static void INCREMENT_DATE() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			cal.incrementDate(days); //changed CAL to cal
			lib.checkCurrentLoans(); //changed LIB to lib
			output(SDF.format(cal.Date())); //changed CAL to cal
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void ADD_BOOK() {
		
		String A = input("Enter author: ");
		String T  = input("Enter title: ");
		String C = input("Enter call number: ");
		book B = lib.Add_book(A, T, C); //changed LIB to lib
		output("\n" + B + "\n");
		
	}

	
	private static void ADD_MEMBER() {
		try {
			String LN = input("Enter last name: ");
			String FN  = input("Enter first name: ");
			String EM = input("Enter email: ");
			int PN = Integer.valueOf(input("Enter phone number: ")).intValue();
			member M = lib.Add_mem(LN, FN, EM, PN); //changed LIB to lib
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return in.nextLine(); //changed IN to in
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
