package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import calendar.Calendar;
import calendar.Event;
import calendar.User;

@RunWith(JUnit4.class)
public class CalendarTest {
	
	User hans;
	User fritz;
	Calendar hanscal;
	Calendar fritzcal;
	
	SimpleDateFormat simple = new SimpleDateFormat("dd.MM.yyyy hh:mm");
	
	@Before
	public void init() throws ParseException{	

		hans = new User("Hans");
		hans.setCalendar("hansCal");	
		Date startDate1hans = simple.parse("02.12.2011 00:01");
		Date endDate1hans = simple.parse("02.12.2011 23:59");	
		Date startDate2hans = simple.parse("20.11.2011 14:00");
		Date endDate2hans = simple.parse("05.12.2011 18:00");		
		Event zahni = new Event("Zahnarzt", startDate1hans, endDate1hans, false);
		Event seminar = new Event("Seminar", startDate2hans, endDate2hans, true);	
		hans.addEvent(zahni);
		hans.addEvent(seminar);
		
		fritz = new User("fritz");
		fritz.setCalendar("fritzCal");
		Date startDate1fritz = simple.parse("20.11.2011 15:00");
		Date endDate1fritz = simple.parse("20.11.2011 17:00");		
		Date startDate2fritz = simple.parse("06.12.2011 18:00");
		Date endDate2fritz = simple.parse("06.12.2011 19:00");	
		Event apero = new Event("Apero", startDate1fritz, endDate1fritz, false);
		Event samichlaus = new Event("Samichlaus", startDate2fritz, endDate2fritz, true);		
		fritz.addEvent(apero);
		fritz.addEvent(samichlaus);
		
	}
	
	@Test 
	public void shouldReturnIteratorSimple() throws ParseException{
		Date date = simple.parse("01.12.2011 00:00");
		Iterator<Event> it = hans.getCalendar().getIterator(hans, date);
		
		Date zahni = simple.parse("02.12.2011 00:01");
		assertEquals(zahni.getTime(), it.next().getStartDate().getTime());
		
		date = simple.parse("03.12.2011 00:00");
		it = hans.getCalendar().getIterator(hans, date);
		assertFalse(it.hasNext());
		
	}
	
	@Test
	public void shouldReturnListSimple() throws Exception{
		Date date = simple.parse("02.12.2011 15:00");	
		ArrayList<Event> list = hans.getCalendar().getList(hans, date);
		assertEquals(2, list.size());
		
		date = simple.parse("03.12.2011 00:00");
		list = hans.getCalendar().getList(hans, date);
		assertEquals(1, list.size());
	}

	@Test
	public void shouldReturnIteratorAdvanced() throws Exception{
		Date date = simple.parse("20.11.2011 00:00");
		Iterator<Event> it = hans.getCalendar().getIterator(fritz, date);
		assertEquals("Seminar", it.next().getName());
		assertFalse(it.hasNext());
		
		it = fritz.getCalendar().getIterator(hans, date);
		assertEquals("Samichlaus", it.next().getName());
		assertFalse(it.hasNext());
		
	}
	
	@Test
	public void shouldReturnListAdvanced() throws Exception{

		Date date = simple.parse("20.11.2011 00:00");
		ArrayList<Event> list = hans.getCalendar().getList(fritz, date);
		assertEquals(1, list.size());
		
		list = fritz.getCalendar().getList(hans, date);
//		assertEquals(1, list.size());
		
//		date = simple.parse("03.12.2011 00:00");
//		list = hans.getCalendar().getList(hans, date);
//		assertEquals(1, list.size());
	}
}
