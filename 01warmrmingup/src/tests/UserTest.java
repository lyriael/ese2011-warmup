package tests;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import calendar.Event;
import calendar.User;

@RunWith(JUnit4.class)

public class UserTest {
	User hans;
	
	@Before
	public void init(){
		String uname = "Hans";
		hans = new User(uname);
	}

	@Test
	public void testUserName(){
		assertEquals("Hans", hans.getName());
	}
	
	@Test
	public void testCalendar(){
		hans.setCalendar("Hans' Kalender");
		assertEquals("Hans' Kalender", hans.getCalendar().getName());
	}
	
	@Test
	public void shouldAddEvent() throws ParseException{
		hans.setCalendar("Hans' Kalender");
		
		SimpleDateFormat simple = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		Date startDate1 = simple.parse("02.12.2011 00:01");
		Date endDate1 = simple.parse("02.12.2011 23:59");
		
		Date startDate2 = simple.parse("20.11.2011 14:00");
		Date endDate2 = simple.parse("05.12.2011 18:00");
		
		Event zahni = new Event("Zahnarzt", startDate1, endDate1, false);
		Event seminar = new Event("Seminar", startDate2, endDate2, true);		
		
		hans.addEvent(zahni);
		hans.addEvent(seminar);

		assertEquals(2, hans.countEvents());
	}
	
}


	