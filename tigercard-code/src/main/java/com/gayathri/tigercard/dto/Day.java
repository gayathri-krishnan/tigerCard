package com.gayathri.tigercard.dto;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Day {
	private int start;
	private int end;
	
	@Builder
	public Day(DayOfWeek startDay, DayOfWeek endDay ) {		
		this.start = startDay.getValue();
		this.end = endDay.getValue();
	}
	
	public Day(int startDay, int endDay ) {		
		this.start = startDay;
		this.end = endDay;
	}
	
	public String getStartDay(TextStyle style, Locale locale) {
		return DayOfWeek.of(start).getDisplayName(style, locale);
	}
	
	public String getEndDay(TextStyle style, Locale locale) {
		return DayOfWeek.of(end).getDisplayName(style, locale);
	}
}
