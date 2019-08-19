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
	
			for (member m : lib.members//changed LIB to lib and MEMBERS to members
				output(m);
			}
			output(" ");
			for (book b : lib.books()) { //changed LIB to lib and BOOKS to books
				output(b);
			}
						
			menu = getMenu(); //changed MENU to menu and Get_menu to getMenu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + sdf.format(cal.Date())); //Changed SDF to sdf and CAL to cal
				String c = input(menu); //changed MENU to menu
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember(); //changed ADD_MEMBER to addMember
					break;
					
				case "LM": 
					members(); //changed MEMBERS to members
					break;
					
				case "B": 
					addBook(); //changed ADD_BOOK to addBook
					break;
					
				case "LB": 
					books(); //changed BOOKS to books
					break;
					
				case "FB": 
					fixBooks(); //changed FIX_BOOKS to fixBooks
					break;
					
				case "L": 
					burrowBook(); //changed BORROW_BOOK to burrowBook
					break;
					
				case "R": 
					returnBook(); //changed RETURN_BOOK to returnBook
					break;
					
				case "LL": 
					currentLoans(); //changed CURRENT_LOANS to currentLoans
					break;
					
				case "P": 
					fines(); //changed FINES to fines
					break;
					
				case "T": 
					incrementDate(); //changed INCREMENT_DATE to incrementDate
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.save(); //changed SAVE to save
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void fines() { //changed FINES to fines
		new PayFineUI(new PayFineControl()).run();	//changed RuN to run
	}


	private static void currentLoans() { //changed CURRENT_LOANS to currentLoans
		output("");
		for (loan loan : lib.CurrentLoans()) { //changed LIB to lib
			output(loan + "\n");
		}		
	}



	private static void books() { //changed BOOKS to books
		output(""); 
		for (book book : lib.books()) { //changed LIB to lib and BOOKS to books
			output(book + "\n");
		}		
	}



	private static void members() { //changed MEMBERS to members
		output("");
		for (member member : lib.members()) { //changed LIB to lib and MEMBERS to members
			output(member + "\n");
		}		
	}



	private static void borrowBook() { //changed BORROW_BOOK to borrowBook
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() { //changed RETURN_BOOK to returnBook
		new ReturnBookUI(new ReturnBookControl()).run();	 //changed RuN to run
	}


	private static void fixBook() { //changed FIX_BOOKS to fixBooks
		new FixBookUI(new FixBookControl()).run();		//changed RuN to run
	}


	private static void incrementDate) { //changed INCREMENT_DATE to incrementDate
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			cal.incrementDate(days); //changed CAL to cal
			lib.checkCurrentLoans(); //changed LIB to lib
			output(sdf.format(cal.Date())); //changed CAL to cal and SDF to sdf
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() { //changed ADD_BOOK to addBook
		
		String author = input("Enter author: "); //changed A to author
		String title  = input("Enter title: "); //changed T to title
		String call = input("Enter call number: "); //changed C to call
		book book = lib.Add_book(author, title, call); //changed LIB to lib and B to book and A to author and T to title and C to call
		output("\n" + book + "\n"); //changed B to book
		
	}

	
	private static void addMember() { //changed ADD_MEMBER to addMember
		try {
			String lastName = input("Enter last name: ");  //changed LN to lastName
			String firstName  = input("Enter first name: "); //changed FN to firstName
			String email = input("Enter email: "); //changed EM to email
			int phone = Integer.valueOf(input("Enter phone number: ")).intValue(); //changed PN to phone
			member member = lib.Add_mem(lastName, firstName, email, phone); //changed LIB to lib and LN to lastName and FN to firstName and EM to email and PN to phone and M to member
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
