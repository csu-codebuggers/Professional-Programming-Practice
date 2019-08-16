import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; // changed from self to self
	private static java.util.Calendar calendar; // changed from CaLeNdAr to calendar
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();
	}
	
	public static Calendar INSTANCE() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void Set_dATE(Date date) {
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
	public synchronized Date Date() {
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

	public synchronized Date Due_Date(int loanPeriod) {
		Date now = Date(); // change local variable NoW to now
		calendar.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = calendar.getTime(); // change local variable name DuEdAtE to dueDate
		calendar.setTime(now);
		return dueDate;
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long diffMillSecond = Date().getTime() - targetDate.getTime(); // change local variable from Diff_Millis to diffMillSecond
	    long diffDays = TimeUnit.DAYS.convert(diffMillSecond, TimeUnit.MILLISECONDS); // change local variable from Diff_Days to diffDays
	    return diffDays;
	}

}
