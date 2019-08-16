import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; // changed from self to self
	private static java.util.Calendar calendar; // changed from CaLeNdAr to calendar
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();
	}
	
	// functio name (INSTANCE) renamed to getInstance 
	public static Calendar getInstance() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);		
	}
	
	// function name renamed from Set_dATE to setDate
	public synchronized void setDate(Date date) {
		try {
			calendar.setTime(date);
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	// function name Date renamed  to date
	public synchronized Date date() {
		try {
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	// function name Due_Date changed to dueDate 
	public synchronized Date dueDate(int loanPeriod) {
		Date now = date(); // change local variable NoW to now
		calendar.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = calendar.getTime(); // change local variable name DuEdAtE to dueDate
		calendar.setTime(now);
		return dueDate;
	}
	
	// function name Get_Days_Difference changed to getDaysDifference 
	public synchronized long getDaysDifference(Date targetDate) {
		
		long diffMillSecond = date().getTime() - targetDate.getTime(); // change local variable from Diff_Millis to diffMillSecond
	    long diffDays = TimeUnit.DAYS.convert(diffMillSecond, TimeUnit.MILLISECONDS); // change local variable from Diff_Days to diffDays
	    return diffDays;
	}

}
