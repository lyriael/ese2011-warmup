package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import calendar.Event;
import calendar.Calendar;
import calendar.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RunWith(JUnit4.class)
public class EventTest {

	SimpleDateFormat simple = new SimpleDateFormat("dd.MM.yyyy hh:mm");
	Date startDate1;
	Date startDate2;
		
	Date endDate1;
	Date endDate2;
	User hans;
	Calendar cal;

	
	
	@Before
	public void init() throws ParseException{
		
		startDate1 = simple.parse("02.12.2011 00:01");
		endDate1 = simple.parse("02.12.2011 23:59");
		
		startDate2 = simple.parse("20.11.2011 14:00");
		endDate2 = simple.parse("05.12.2011 18:00");
		
		hans = new User("Hans");
		cal = new Calendar("Hans' Kalender", hans);
		
	}
	 
	@Test
	public void settings(){
		Event e = new Event("Ferien", startDate2, endDate2, true);
		assertEquals(true, e.isPulic());
		assertEquals(-1, e.getStartDate().compareTo(e.getEndDate()));
		assertTrue("Ferien".equals(e.getName()));
		e.setCalendar(cal);
		assertEquals(cal, e.getCalendar()); 
	}
	
	@Test
	public void compareTo(){
		Event ferien = new Event("Ferien", startDate2, endDate2, true);
		Event geburi = new Event("Simons Geburi", startDate1, endDate1, false);
		assertTrue(ferien.getStartDate().before(geburi.getStartDate()));
	}
	
}
