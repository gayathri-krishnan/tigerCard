package com.gayathri.tigercard.dto;

import java.time.LocalTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@Document(collection = "FareRule")
public class FareRule {
	public final Time time;
	public final Zone zone;
	public final Day day;	
	public final int rate;
	
	public static FareRule parseInput(final FareRuleInput input) {
		return FareRule.builder().time(new Time(LocalTime.parse(input.getStartTime()), LocalTime.parse(input.getEndTime()))).zone(input.getZone()).day(new Day(input.getStartDay(), input.getEndDay()))
				.rate(input.getRate()).build();
	}
}
