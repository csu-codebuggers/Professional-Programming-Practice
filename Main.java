import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner in; //changed IN to in
	private static library lib; //changed LIB to lib
	private static String menu; //changed MENU to menu
	private static Calendar cal; //changed CAL to cal
	private static SimpleDateFormat sdf; //Changed SDF to sdf
	
	
	private static String getMenu() { //changed Get_menu to getMenu
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
						
			menu = getMenu(); //changed MENU to menu and Get_menu to getMenu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + sdf.format(cal.Date())); //Changed SDF to sdf and CAL to cal
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

		private static void fines() { //changed FINES to fines
		new PayFineUI(new PayFineControl()).RuN();		
	}


	private static void currentLoans() { //changed CURRENT_LOANS to currentLoans
		output("");
		for (loan loan : lib.CurrentLoans()) { //changed LIB to lib
			output(loan + "\n");
		}		
	}



	private static void books() { //changed BOOKS to books
		output(""); 
		for (book book : lib.BOOKS()) { //changed LIB to lib
			output(book + "\n");
		}		
	}



	private static void members() { //changed MEMBERS to members
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
			output(sdf.format(cal.Date())); //changed CAL to cal and SDF to sdf
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void ADD_BOOK() {
		
		String author = input("Enter author: "); //changed A to author
		String title  = input("Enter title: "); //changed T to title
		String call = input("Enter call number: "); //changed C to call
		book book = lib.Add_book(author, title, call); //changed LIB to lib and B to book and A to author and T to title and C to call
		output("\n" + book + "\n"); //changed B to book
		
	}

	
	private static void ADD_MEMBER() {
		try {
			String lastName = input("Enter last name: ");  //changed LN to lastName
			String firstName  = input("Enter first name: "); //changed FN to firstName
			String email = input("Enter email: "); //changed EM to email
			int phone = Integer.valueOf(input("Enter phone number: ")).intValue(); //changed PN to phone
			member member = lib.Add_mem(LN, FN, EM, PN); //changed LIB to lib and LN to lastName and FN to firstName and EM to email and PN to phone and M to member
			output("\n" + member + "\n"); //changed M to member
			
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
