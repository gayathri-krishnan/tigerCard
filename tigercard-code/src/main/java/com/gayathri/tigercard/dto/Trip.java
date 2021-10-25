package com.gayathri.tigercard.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {
	private String time;
	private int fromZone;
	private int toZone;
	private String date;	
	
	public int getSecondOfDay() {
		return LocalTime.parse(time).toSecondOfDay();
	}
	
	public LocalDate getTripDate() {
		return LocalDate.parse(date);
	}
	
	public int getAbsoluteDistance() {
		return Math.abs(this.toZone - this.fromZone);
	}
}