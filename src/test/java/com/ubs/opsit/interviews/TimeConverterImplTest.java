package com.ubs.opsit.interviews;

import org.junit.*;

import com.ubs.opsit.interviews.Exception.TimeException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TimeConverterImplTest {

	    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	    
	    private TimeConverterImpl timeConverter;

	    @Before
	    public void setUpStreams() {
	        System.setOut(new PrintStream(outContent));
	        System.setErr(new PrintStream(errContent));
	        timeConverter = new TimeConverterImpl();
	    }

	    @Test
	    public void testMinValidBerlinClock() {
	    	timeConverter.convertTime("00:00:00");
//	        new BerlinClock("00:00:00");
	    }

	    @Test
	    public void testMaxValidBerlinClock() {
	    	timeConverter.convertTime("23:59:59");
	    }

	    @Test(expected = TimeException.class)
	    public void testUpperInvalidHours() {
	    	timeConverter.convertTime("25:00:00");
	    }

	    @Test(expected = TimeException.class)
	    public void testUpperInvalidMinutes() {
	    	timeConverter.convertTime("00:60:00");
	    }

	    @Test(expected = TimeException.class)
	    public void testUpperInvalidSeconds() {
	    	timeConverter.convertTime("00:00:60");
	    }

	    @Test(expected = TimeException.class)
	    public void testLowerInvalidHours() {
	    	timeConverter.convertTime("-21:00:00");
	    }

	    @Test(expected = TimeException.class)
	    public void testLowerInvalidMinutes() {
	    	timeConverter.convertTime("00:-21:00");
	    }

	    @Test(expected = TimeException.class)
	    public void testLowerInvalidSeconds() {
	    	timeConverter.convertTime("00:00:-59");
	    }

	    @Test(expected = TimeException.class)
	    public void testInvalidString() {
	    	timeConverter.convertTime("24:59");
	    }

	    @Test(expected = TimeException.class)
	    public void testNullString() {
	    	timeConverter.convertTime(null);
	    }

	    @Test(expected = TimeException.class)
	    public void testEmptyString() {
	    	timeConverter.convertTime("");
	    }

	    @After
	    public void cleanUpStreams() {
	        System.setOut(null);
	        System.setErr(null);
	    }


}
