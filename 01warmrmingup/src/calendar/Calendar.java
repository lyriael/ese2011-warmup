package calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 
 * @author lyriael
 *
 */
public class Calendar {

	private String name;
	private User owner;
	private PriorityQueue<Event> events;

	/**
	 * Constructor
	 * 
	 * @param cname
	 * @param owner
	 */
	public Calendar(String name, User owner) {
		this.name = name;
		this.owner = owner;
		events = new PriorityQueue<Event>();
	}

	public void addEvent(Event event) {
		event.setCalendar(this);
		events.add(event);	
	}
	
	/**
	 * If User = owner of Calendar, then all events are returned. <br>
	 * If User = stranger, then only public events are returned.
	 * 
	 * @param user User who wants to see events
	 * @return iterator<Event> 
	 */
	public Iterator<Event> getIterator(User user, Date date){
		//TODO schachteln
		PriorityQueue<Event> afterDate = new PriorityQueue<Event>();
		
		for(Event event:events){
			if(event.getStartDate().getTime() >= date.getTime()){
					afterDate.add(event);
			}	
		}
		
		if (this.owner == user){
			return afterDate.iterator();
		}
		
		else {
			for(Event event:afterDate){
				if (!event.isPulic()) afterDate.remove(event);
			}
			return afterDate.iterator();
		}
	}
	
	
	public ArrayList<Event> getList(User user, Date date) throws Exception{
		
		ArrayList<Event> atDate = new ArrayList<Event>();
		
		Date thisDay = getDay(date);
		Date startEvent;
		Date endEvent;
		
		for(Event event:events){
			startEvent = getDay(event.getStartDate());
			endEvent = getDay(event.getEndDate());
			if(startEvent.getTime() <= thisDay.getTime() && thisDay.getTime() <= endEvent.getTime())
				atDate.add(event);
		}
		
		if (this.owner == user){
			return atDate;
		}
		
		else {
			ArrayList<Event> temp = new ArrayList<Event>();
			for(Event event:atDate){
				if (event.isPulic()) {
					temp.add(event);
				}
			}
			return temp;
		}
	}	
	
	
	/**
	 * Sets the time of a Date to 00:00 so that it is possible to be compared with
	 * other days ignoring the time.
	 * 
	 * @param d Date
	 * @return d Date
	 * @throws Exception
	 */
	private Date getDay(Date d) throws Exception{
		
		SimpleDateFormat simple = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy hh:mm");
//		System.out.println(simple.parse(form.format(d).substring(0,10)+" 00:00"));
		return simple.parse(form.format(d).substring(0,10)+" 00:00");
			
	}
	
	//for testing only
	public String getName() {
		return this.name;
	}
	//for testing only
	public int countEvents() {
		return events.size();
	}


}
