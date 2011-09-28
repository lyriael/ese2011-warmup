package calendar;

import java.util.Date;


public class Event implements Comparable<Event>{


	private boolean isPublic;
	private Date startDate;
	private Date endDate;
	private String name;
	private Calendar calendar;
	
	public Event(String ename, Date sdate, Date edate, boolean isPublic) {
		this.name = ename;
		this.startDate = sdate;
		this.endDate = edate;
		this.isPublic = isPublic;
	}
		
		
	public Date getStartDate(){return this.startDate;}
	public Date getEndDate(){return this.endDate;}

	public boolean isPulic() {
		return isPublic;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	public String getName(){
		return name;
	}


	public Calendar getCalendar() {
		return calendar;
	}


	public int compareTo(Event event) {
		return this.getStartDate().compareTo(event.getStartDate());
	}
	
}
