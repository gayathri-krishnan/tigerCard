package com.gayathri.tigercard.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Time {
	private int start;
	private int end;

	public Time(LocalTime start, LocalTime end) {
		this.start = start.toSecondOfDay();
		this.end = end.toSecondOfDay();
	}

	public Time(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public LocalTime getStartTime() {
		return LocalTime.ofSecondOfDay(start);
	}

	public LocalTime getEndTime() {
		return LocalTime.ofSecondOfDay(end);
	}

}
