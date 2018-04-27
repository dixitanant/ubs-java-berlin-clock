package com.ubs.opsit.interviews;

import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.lang.StringUtils;

import com.ubs.opsit.interviews.Exception.TimeException;

public class TimeConverterImpl implements TimeConverter {
	
	public TimeConverterImpl() {
	}
	
	public String processTime(String aTime) {
		validateInputTime(aTime);
		String[] timeArray = aTime.split(":");
		int hours = convertStringToInteger(timeArray[0]);
		int minutes = convertStringToInteger(timeArray[1]);
		int seconds = convertStringToInteger(timeArray[2]);
		
		if (hours < 0 || hours > 24) {
			throw new TimeException("Hours must be in range [0-24].");
		}
        if (minutes < 0 || minutes > 59) {
        	throw new TimeException("Minutes must be in range [0-59].");
        }
        if (seconds < 0 || seconds > 59) {
        	throw new TimeException("Seconds must be in range [0-59].");
        }
        
        String row1 = (seconds % 2 == 0) ? "Y" : "O";
        String row2 = rowValue(hours / 5, 4, "R");
        String row3 = rowValue(hours % 5, 4, "R");
        String row4 = rowValue(minutes / 5, 11, "Y").replaceAll("YYY", "YYR");
        String row5 = rowValue(minutes % 5, 4, "Y");
        
        return String.join(System.getProperty("line.separator"), Arrays.asList(row1, row2, row3, row4, row5));
	}

	private String rowValue(int used, int max, String type) {
		int unused = max - used;
        String useStr = String.join("", Collections.nCopies(used, type));
        String unUsedStr = String.join("", Collections.nCopies(unused, "O"));

        return useStr + unUsedStr;
	}

	private int convertStringToInteger(String value) {
		int result = 0;
		try {
			result = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new TimeException("Value must be decimal", e);
		}
		return result;
	}

	private void validateInputTime(String aTime) {
		if(StringUtils.isEmpty(aTime)) {
			throw new TimeException("Input time is not provided.");
		}
		String[] timeArray = aTime.split(":", 3);
		if (timeArray.length != 3) {
			throw new TimeException("Input time string is not in correct format.");
		}
	}

	@Override
	public String convertTime(String aTime) {
		return processTime(aTime);
	}

}
