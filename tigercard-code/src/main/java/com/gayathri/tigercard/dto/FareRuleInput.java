package com.gayathri.tigercard.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FareRuleInput {
	private String startTime;
	private String endTime;
	private DayOfWeek startDay;
	private DayOfWeek endDay;
	private Zone zone;
	private int rate;

	@Builder
	public FareRuleInput(final String startTime, final String endTime, final DayOfWeek startDay, final DayOfWeek endDay,
			final Zone zone, final int rate) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDay = startDay;
		this.endDay = endDay;
		this.zone = zone;
		this.rate = rate;
	}

	public static FareRuleInput parseRule(final FareRule rule) {
		return FareRuleInput.builder().startTime(rule.getTime().getStartTime().toString())
				.endTime(rule.getTime().getEndTime().toString()).startDay(DayOfWeek.of(rule.getDay().getStart()))
				.endDay(DayOfWeek.of(rule.getDay().getEnd())).zone(rule.zone).rate(rule.getRate()).build();
	}
}
