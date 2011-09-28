package calendar;



public class User {

	private String uname;
	private Calendar calendar;
	
	/**
	 * Constructor
	 * 
	 * @param uname
	 */
	public User(String uname){
		this.uname = uname;
	}
	
	//should only return a shallow copy of the calendar!
	/**
	 * Sets up a new Calendar for this User.
	 * 
	 * @param cname
	 * @return new created Calendar
	 */
	public void setCalendar(String cname){
		assert this.calendar == null;
		calendar = new Calendar(cname, this);
	}
	
	public void addEvent(Event event){
		assert this.calendar != null;
		calendar.addEvent(event);
	}

	//for testing only
	public int countEvents(){
		return calendar.countEvents();
	}
	
	//for testing only
	public String getName(){
		return uname;
	}
	//for testing only
	public Calendar getCalendar(){
		assert this.calendar != null;
		return calendar;
	}
}
